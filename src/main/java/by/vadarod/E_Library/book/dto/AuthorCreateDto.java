package by.vadarod.E_Library.book.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@NoArgsConstructor
public class AuthorCreateDto {
    @Getter
    @Setter
    @NotNull
    private String name;
    private List<BookUppDto> books;

    public List<BookUppDto> getBooks() {
        if (books != null) {
            books.forEach(bookUppDto -> {
                bookUppDto.setAuthors(null);
            });
        }
        return books;
    }

    public void setBooks(List<BookUppDto> books) {
        if (books != null) {
            books.forEach(bookUppDto -> {
                bookUppDto.setAuthors(null);
            });
        }
        this.books = books;
    }
}
