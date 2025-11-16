package by.vadarod.E_Library.user.mapper;

import by.vadarod.E_Library.user.dto.SubscriptionUserCreateDto;
import by.vadarod.E_Library.user.dto.SubscriptionUserUppDto;
import by.vadarod.E_Library.user.entity.SubscriptionUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionUserMapper {

    @Mapping(target = "id", ignore = true)
    SubscriptionUserEntity toSubscriptionUserEntity(SubscriptionUserCreateDto subscriptionUserDto);

    SubscriptionUserEntity toSubscriptionUserEntity(SubscriptionUserUppDto subscriptionUserUppDto);

    SubscriptionUserUppDto toSubscriptionUserUppDto(SubscriptionUserEntity subscriptionUserEntity);
}
