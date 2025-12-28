package by.vadarod.service;

import by.vadarod.E_Library.user.dto.RoleCreateDto;
import by.vadarod.E_Library.user.entity.RoleEntity;
import by.vadarod.E_Library.user.mapper.RoleMapper;
import by.vadarod.E_Library.user.repository.RoleRepository;
import by.vadarod.E_Library.user.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
    @Mock
    RoleRepository roleRepository;

    @Mock
    RoleMapper roleMapper;

    @InjectMocks
    RoleService roleService;

    @Test
    public void test_add_roll(){
        RoleCreateDto roleCreateDto = new RoleCreateDto();
        roleCreateDto.setName("test");

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("test");
        when(roleMapper.roleDtoToRole(any(), any())).thenReturn(roleEntity);
        when(roleRepository.save(any())).thenReturn(roleEntity);
        roleService.saveRole(roleCreateDto);
        verify(roleRepository, times(1)).save(any());
    }
}
