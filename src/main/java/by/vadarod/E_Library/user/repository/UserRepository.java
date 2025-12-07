package by.vadarod.E_Library.user.repository;

import by.vadarod.E_Library.user.entity.RoleEntity;
import by.vadarod.E_Library.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public List<UserEntity> findByRole (RoleEntity role);
    public List<UserEntity> findByLoginIgnoreCaseAndIdNot (String login, Long id);
    public List<UserEntity> findByLoginIgnoreCase (String login);
}
