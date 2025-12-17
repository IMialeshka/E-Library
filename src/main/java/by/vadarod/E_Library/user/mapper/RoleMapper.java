package by.vadarod.E_Library.user.mapper;

import by.vadarod.E_Library.user.dto.RoleCreateDto;
import by.vadarod.E_Library.user.dto.RoleUppDto;
import by.vadarod.E_Library.user.entity.RoleEntity;
import by.vadarod.E_Library.user.repository.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RoleMapper {


    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "userEntityList" , expression = "java(MappingRulesForUsersDomain.mapListIdToListUserEntity(roleDto.getUserIdList(), userRepository))")
    RoleEntity roleDtoToRole(RoleCreateDto roleDto, @Context UserRepository userRepository);

    @Mapping(target = "userIdList" , expression = "java(MappingRulesForUsersDomain.mapListEntityToListId(role.getUserEntityList()))")
    RoleUppDto roleToRoleUppDto(RoleEntity role);

    @Mapping(target = "userEntityList" , expression = "java(MappingRulesForUsersDomain.mapListIdToListUserEntity(roleDto.getUserIdList(), userRepository))")
    RoleEntity roleDtoUppToRole(RoleUppDto roleDto, @Context UserRepository userRepository);

}





