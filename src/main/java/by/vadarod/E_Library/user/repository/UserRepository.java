package by.vadarod.E_Library.user.repository;

import by.vadarod.E_Library.user.entity.RoleEntity;
import by.vadarod.E_Library.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public List<UserEntity> findByRole (RoleEntity role);
    public Optional<UserEntity> findByLoginAndIdNot (String login, Long id);
    public Optional<UserEntity>  findByLogin (String login);
    public List<UserEntity> findByIdIn (Set<Long> ids);
}
