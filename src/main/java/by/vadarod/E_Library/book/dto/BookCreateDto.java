package by.vadarod.E_Library.book.dto;

import by.vadarod.E_Library.book.entity.BookFileEntity;
import by.vadarod.E_Library.book.entity.Genre;
import by.vadarod.E_Library.user.dto.UserUppDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
public class BookCreateDto {
    @Getter
    @Setter
    private String name;
    private List<AuthorUppDto> authors;
    @Getter
    @Setter
    private Genre genre;
    @Getter
    @Setter
    private List<BookFileUppDto> bookFiles;
    @Getter
    @Setter
    private List<UserUppDto> users;
    @Getter
    @Setter
    private List<ReviewUppDto> reviewEntityList;
    @Getter
    @Setter
    private double rating;

    public List<AuthorUppDto> getAuthors() {
        if (authors != null) {
            authors.forEach(authorUppDto -> {
                authorUppDto.setBooks(null);
            });
        }
        return authors;
    }

    public void setAuthors(List<AuthorUppDto> authors) {
        if (authors != null) {
            authors.forEach(authorUppDto -> {
                authorUppDto.setBooks(null);
            });
        }
        this.authors = authors;
    }
}
