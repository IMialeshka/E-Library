package by.vadarod.E_Library.user.mapper;

import by.vadarod.E_Library.user.dto.UserCreateDto;
import by.vadarod.E_Library.user.dto.UserUppDto;
import by.vadarod.E_Library.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id" , ignore = true)
    UserEntity toUserEntity(UserCreateDto userDto);

    @Mapping(target = "password", constant = "")
    UserUppDto toUserUppDto(UserEntity userEntity);

    UserEntity toUserUppEntity(UserUppDto userUppDto);
}
