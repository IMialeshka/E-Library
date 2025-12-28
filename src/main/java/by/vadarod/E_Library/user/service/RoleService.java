package by.vadarod.E_Library.user.service;

import by.vadarod.E_Library.tools.exception.model.RoleUseWithUsersException;
import by.vadarod.E_Library.user.dto.RoleCreateDto;
import by.vadarod.E_Library.user.dto.RoleUppDto;
import by.vadarod.E_Library.user.entity.RoleEntity;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.mapper.RoleMapper;
import by.vadarod.E_Library.user.repository.RoleRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository  roleRepository;

    private final RoleMapper  roleMapper;

    private final UserRepository userRepository;

    @Secured({"ADMIN"})
    public List<RoleUppDto> getAllRoles() {
       List<RoleEntity> roleEntityList = roleRepository.findAll();
        return roleEntityList.stream().map(roleMapper::roleToRoleUppDto).toList();

    }
    @Secured({"ADMIN"})
    public void dellById(long id) throws RoleUseWithUsersException {
        RoleEntity roleEntity = roleRepository.findById(id).orElse(null);
        if (roleEntity != null) {
            List<UserEntity> userEntityList = userRepository.findByRole(roleEntity);
            if (!userEntityList.isEmpty()) {
                throw new RoleUseWithUsersException("У роли есть пользователи. Удалите или укажите для них другую роль");
            } else {
                roleRepository.deleteById(id);
            }

        }
    }

    @Secured({"ADMIN"})
    public RoleUppDto getById(long id) {
        return roleMapper.roleToRoleUppDto(roleRepository.getById(id));
    }

    @Secured({"ADMIN"})
    public void saveRole(RoleCreateDto roleDto) {
        RoleEntity roleEntity = roleMapper.roleDtoToRole(roleDto, userRepository);
        roleRepository.save(roleEntity);
    }

    @Secured({"ADMIN"})
    public void saveUppRole(RoleUppDto roleDto) {
        RoleEntity roleEntity = roleMapper.roleDtoUppToRole(roleDto, userRepository);
        roleRepository.save(roleEntity);
    }
}
