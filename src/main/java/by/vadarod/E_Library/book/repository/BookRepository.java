package by.vadarod.E_Library.book.repository;

import by.vadarod.E_Library.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
