package by.vadarod.E_Library.book.repository;

import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByGenre(Genre genre);
    List<BookEntity> findByGenreIn(List<Genre> genreList);
}
