package by.vadarod.E_Library.user.service;

import by.vadarod.E_Library.user.dto.UserCreateDto;
import by.vadarod.E_Library.user.dto.UserUppDto;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.mapper.UserMapper;
import by.vadarod.E_Library.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserUppDto> getAllUsers() {
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        return userEntityList.stream().map(userMapper::toUserUppDto).toList();

    }

    public void dellById(long id) {
        userRepository.deleteById(id);
    }

    public UserUppDto getById(long id) {
        return userMapper.toUserUppDto(userRepository.getById(id));
    }

    public void saveUser(UserCreateDto userCreateDto) {
        UserEntity userEntity = userMapper.toUserEntity(userCreateDto);
        userRepository.save(userEntity);
    }

    public void saveUppUser(UserUppDto userUppDto) {
        UserEntity userEntity = userMapper.toUserUppEntity(userUppDto);
        userRepository.save(userEntity);
    }
}
