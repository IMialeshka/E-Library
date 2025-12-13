package by.vadarod.E_Library.book.service;


import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.BookFileEntity;
import by.vadarod.E_Library.book.mapper.BookMapper;
import by.vadarod.E_Library.book.repository.AuthorRepository;
import by.vadarod.E_Library.book.repository.BookFileRepository;
import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.book.repository.ReviewRepository;
import by.vadarod.E_Library.tools.exception.model.FileLoadingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

    public void dellFile(String fileName) {
        File file = new File(getPathToLibrary(), fileName);
        if (!file.exists()) {
            file.delete();
        }
    }

    public void uploadFileForBook(Long id, MultipartFile file) throws FileLoadingException {
        BookEntity bookEntity = bookRepository.getById(id);
        BookFileEntity bookFileEntity = new BookFileEntity();
        bookFileEntity.setFileName(uploadFileBook(file));
        bookFileEntity.setBook(bookEntity);
        bookFileRepository.save(bookFileEntity);
    }

    public String getFileNameForOpen (Long id) {
        return bookFileRepository.getById(id).getFileName();

    }


}
