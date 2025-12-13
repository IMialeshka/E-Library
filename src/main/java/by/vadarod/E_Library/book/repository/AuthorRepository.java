package by.vadarod.E_Library.book.repository;

import by.vadarod.E_Library.book.entity.AuthorEntity;
import by.vadarod.E_Library.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByIdIn(Set<Long> idList);
}
