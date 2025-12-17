package by.vadarod.E_Library.user.mapper;

import by.vadarod.E_Library.user.dto.SubscriptionUserCreateDto;
import by.vadarod.E_Library.user.dto.SubscriptionUserUppDto;
import by.vadarod.E_Library.user.entity.SubscriptionUserEntity;
import by.vadarod.E_Library.user.repository.SubscriptionRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionUserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", expression = "java(MappingRulesForUsersDomain.mapIdToUser(subscriptionUserDto.getUserId(), userRepository))")
    @Mapping(target = "subscription", expression = "java(MappingRulesForUsersDomain.mapIdToSubscription(subscriptionUserDto.getSubscriptionId(), subscriptionRepository))")
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    SubscriptionUserEntity toSubscriptionUserEntity(SubscriptionUserCreateDto subscriptionUserDto, @Context UserRepository userRepository, @Context SubscriptionRepository subscriptionRepository);

    @Mapping(target = "user", expression = "java(MappingRulesForUsersDomain.mapIdToUser(subscriptionUserUppDto.getUserId(), userRepository))")
    @Mapping(target = "subscription", expression = "java(MappingRulesForUsersDomain.mapIdToSubscription(subscriptionUserUppDto.getSubscriptionId(), subscriptionRepository))")
    SubscriptionUserEntity toSubscriptionUserEntityUpp(SubscriptionUserUppDto subscriptionUserUppDto , @Context UserRepository userRepository, @Context SubscriptionRepository subscriptionRepository);

    @Mapping(target = "userId", expression = "java(subscriptionUserEntity.getUser().getId())")
    @Mapping(target = "subscriptionId", expression = "java(subscriptionUserEntity.getSubscription().getId())")
    SubscriptionUserUppDto toSubscriptionUserUppDto(SubscriptionUserEntity subscriptionUserEntity);
}
