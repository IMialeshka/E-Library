package by.vadarod.E_Library.user.mapper;

import by.vadarod.E_Library.book.repository.BookRepository;
import by.vadarod.E_Library.user.dto.UserCreateDto;
import by.vadarod.E_Library.user.dto.UserUppDto;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.repository.RoleRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "favorites", expression = "java(MappingRulesForUsersDomain.mapIdListToBookEntityList(userDto.getFavoritesIdList(), bookRepository))")

    UserEntity toUserEntity(UserCreateDto userDto, @Context RoleRepository roleRepository, @Context BookRepository bookRepository);

    @Mapping(target = "password", constant = "")
    @Mapping(target = "roleId", expression = "java(userEntity.getRole().getId())")
    @Mapping(target = "favoritesIdList", expression = "java(MappingRulesForUsersDomain.mapIdBookToIdList(userEntity.getFavorites()))")
    UserUppDto toUserUppDto(UserEntity userEntity);

    @Mapping(target = "role", expression = "java(MappingRulesForUsersDomain.mapIdToRole(userUppDto.getRoleId(), roleRepository))")
    @Mapping(target = "favorites", expression = "java(MappingRulesForUsersDomain.mapIdListToBookEntityList(userUppDto.getFavoritesIdList(), bookRepository))")
    UserEntity toUserUppEntity(UserUppDto userUppDto, @Context RoleRepository roleRepository, @Context BookRepository bookRepository);
}
