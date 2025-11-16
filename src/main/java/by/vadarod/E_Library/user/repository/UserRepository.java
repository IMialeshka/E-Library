package by.vadarod.E_Library.user.repository;

import by.vadarod.E_Library.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
