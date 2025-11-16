package by.vadarod.E_Library.user.mapper;

import by.vadarod.E_Library.user.dto.SubscriptionCreateDto;
import by.vadarod.E_Library.user.dto.SubscriptionUppDto;
import by.vadarod.E_Library.user.entity.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    @Mapping(target = "id" , ignore = true)
    SubscriptionEntity subscriptionDtoToSubscriptionEntity(SubscriptionCreateDto subscriptionDto);

    SubscriptionUppDto subscriptionEntityToSubscriptionUppDto(SubscriptionEntity subscriptionEntity);

    SubscriptionEntity subscriptionDtoToSubscriptionUppDto(SubscriptionUppDto subscriptionDto);
}
