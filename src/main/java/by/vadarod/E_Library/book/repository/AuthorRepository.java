package by.vadarod.E_Library.book.repository;

import by.vadarod.E_Library.book.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
