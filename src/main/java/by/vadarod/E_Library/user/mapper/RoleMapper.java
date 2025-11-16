package by.vadarod.E_Library.user.mapper;

import by.vadarod.E_Library.user.dto.RoleCreateDto;
import by.vadarod.E_Library.user.dto.RoleUppDto;
import by.vadarod.E_Library.user.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id" , ignore = true)
    RoleEntity roleDtoToRole(RoleCreateDto roleDto);

    RoleUppDto roleToRoleUppDto(RoleEntity role);

    RoleEntity roleDtoUppToRole(RoleUppDto roleDto);

}
