package by.vadarod.E_Library.user.repository;

import by.vadarod.E_Library.user.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    public RoleEntity getRoleByName(String name);
}
