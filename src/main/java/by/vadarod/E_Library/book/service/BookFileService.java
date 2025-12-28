package by.vadarod.E_Library.book.service;


import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.BookFileEntity;
import by.vadarod.E_Library.book.repository.BookFileRepository;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.tools.exception.model.FileLoadingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BookFileService {

    private final BookFileRepository bookFileRepository;
    private final BookRepository bookRepository;

    @Value("${file.upload-dir}")
    private String libraryDir;

    public String getPathToLibrary() {
        return libraryDir.replace("/", File.separator);
    }

    private String uploadFileBook(MultipartFile file) throws FileLoadingException {
        String nameBook = UUID.randomUUID()+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String pathToBook = getPathToLibrary()+ File.separator + nameBook;
        try(BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(pathToBook)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
        } catch (FileNotFoundException e) {
            throw new FileLoadingException("Файл не загрузился");
        } catch (IOException e) {
            throw new FileLoadingException("Файл не открылся");
        }
        return nameBook;
    }

    private String uploadFileBookForShow(String nameBookFile) throws FileLoadingException {
        String pathToBook = getPathToLibrary()+ File.separator + nameBookFile;
        String pngFileName = UUID.randomUUID() + ".jpeg";
        try(PDDocument pdDocumentStart = Loader.loadPDF(new File(pathToBook))) {
            int pagesCount = (int) Math.floor(pdDocumentStart.getPages().getCount()/ 20);

            if (pagesCount <=  0) {
                pagesCount = 1;
            }

            PDFRenderer renderer = new PDFRenderer(pdDocumentStart);

            int totalHeight = 0;
            int maxWidth = 0;

            BufferedImage[] pageImages = new BufferedImage[pagesCount];
            for (int i = 0; i < pagesCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 300);
                pageImages[i] = image;
                totalHeight += image.getHeight();
                if (image.getWidth() > maxWidth) {
                    maxWidth = image.getWidth();
                }
            }

            BufferedImage combinedImage = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = combinedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            int currentY = 0;
            for (BufferedImage pageImage : pageImages) {
                g2d.drawImage(pageImage, 0, currentY, null);
                currentY += pageImage.getHeight();
            }
            g2d.dispose();

            File outputFile = new File(getPathToLibrary(), pngFileName);
            ImageIO.write(combinedImage, "jpeg", outputFile);
        } catch (IOException e) {
            throw new FileLoadingException("Файл не открылся");
        }
        return pngFileName;
    }

    public void dellFile(String fileName) {
        File file = new File(getPathToLibrary(), fileName);
        if (!file.exists()) {
            file.delete();
        }
    }

    public void uploadFileForBook(Long id, MultipartFile file) throws FileLoadingException {
        BookEntity bookEntity = bookRepository.getById(id);
        BookFileEntity bookFileEntity = new BookFileEntity();
        String filePdf = uploadFileBook(file);
        bookFileEntity.setFileName(filePdf);
        bookFileEntity.setPreviewFileName(uploadFileBookForShow(filePdf));
        bookFileEntity.setBook(bookEntity);
        bookFileRepository.save(bookFileEntity);
    }

    public String getFileNameForRead (Long id) {
        Optional<BookFileEntity> bookFileEntity = bookFileRepository.findByBookId(id);
        if (bookFileEntity.isPresent()) {
            return getPathToLibrary()+ File.separator + bookFileRepository.findByBookId(id).get().getPreviewFileName();
        } else {
            throw new RuntimeException("Нет файла для предпросмотра");
        }
    }


}
