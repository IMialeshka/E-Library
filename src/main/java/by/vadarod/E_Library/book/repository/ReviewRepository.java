package by.vadarod.E_Library.book.repository;

import by.vadarod.E_Library.book.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
