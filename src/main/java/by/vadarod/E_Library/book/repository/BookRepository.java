package by.vadarod.E_Library.book.repository;

import by.vadarod.E_Library.book.entity.BookEntity;
import by.vadarod.E_Library.book.entity.Genre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByGenre(Genre genre, Pageable  pageable);
    List<BookEntity> findByGenreIn(List<Genre> genreList, Pageable pageable);
    List<BookEntity> findByIdIn(Set<Long> idList);
    List<BookEntity> findByAuthorsId(Long id, Pageable pageable);
}
