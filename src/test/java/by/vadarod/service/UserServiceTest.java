package by.vadarod.service;

import by.vadarod.E_Library.jwt.service.JwtService;
import by.vadarod.E_Library.jwt.service.RefreshTokenService;
import by.vadarod.E_Library.tools.exception.model.UserLoginException;
import by.vadarod.E_Library.user.dto.UserCreateDto;
import by.vadarod.E_Library.user.entity.RoleEntity;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.mapper.UserMapper;
import by.vadarod.E_Library.user.repository.RoleRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import by.vadarod.E_Library.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @Mock
    RoleRepository roleRepository;
    @Mock
    JwtService jwtService;

    @Mock
    RefreshTokenService refreshTokenService;

    @InjectMocks
    UserService userService;

    @Mock
    PasswordEncoder byCryptPasswordEncoder;

    @Test
    public void test_add_user() throws UserLoginException {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(100L);
        roleEntity.setName("READER");
        when(userRepository.findByLogin(anyString())).thenReturn(Optional.empty());
        when(userMapper.toUserEntity(any(), any(), any())).thenReturn(new UserEntity());
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
        when(byCryptPasswordEncoder.encode(any())).thenReturn("password");
        when(roleRepository.getRoleByName(any())).thenReturn(roleEntity);
        when(jwtService.generateToken(any())).thenReturn("token");
        when(refreshTokenService.generateRefreshToken(any())).thenReturn("token");
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setLogin("admin");
        userCreateDto.setPassword("test");
        userCreateDto.setName("admin");
        userService.saveUser(userCreateDto);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void test_add_user_exception() {
        when(userRepository.findByLogin(anyString())).thenReturn(Optional.of(new UserEntity()));
        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setLogin("admin");
        userCreateDto.setPassword("test");
        userCreateDto.setName("admin");
        Exception exception = assertThrows(UserLoginException.class, ()->userService.saveUser(userCreateDto));
        assertEquals("Пользователь с таким логином уже существует", exception.getMessage());
    }
}
