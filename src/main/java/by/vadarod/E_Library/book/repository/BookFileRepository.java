package by.vadarod.E_Library.book.repository;


import by.vadarod.E_Library.book.entity.BookFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookFileRepository extends JpaRepository<BookFileEntity, Long> {
    Optional<BookFileEntity> findByBookId(long id);
}
