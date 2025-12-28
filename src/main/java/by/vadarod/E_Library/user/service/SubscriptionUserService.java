package by.vadarod.E_Library.user.service;


import by.vadarod.E_Library.user.dto.*;
import by.vadarod.E_Library.user.entity.SubscriptionUserEntity;;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.mapper.SubscriptionUserMapper;
import by.vadarod.E_Library.user.repository.SubscriptionRepository;
import by.vadarod.E_Library.user.repository.SubscriptionUserRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionUserService {
    private final SubscriptionUserRepository subscriptionUserRepository;
    private final SubscriptionUserMapper subscriptionUserMapper;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;


    @Secured({"READER"})
    public SubscriptionUserUppDto saveSubscriptionUser(SubscriptionUserCreateDto subscriptionUserCreateDto, String login) {
        SubscriptionUserEntity subscriptionUserEntity = subscriptionUserMapper.toSubscriptionUserEntity(subscriptionUserCreateDto,  subscriptionRepository);
        UserEntity user = userRepository.findByLogin(login).get();
        subscriptionUserEntity.setUser(user);
        Date startSubscriptionDate = new Date();
        Optional<SubscriptionUserEntity> lastSubscription = subscriptionUserRepository.findFirstByUserIdOrderByEndDateDesc(subscriptionUserEntity.getUser().getId());

        if (lastSubscription.isPresent() && startSubscriptionDate.before(lastSubscription.get().getEndDate())) {
            startSubscriptionDate = lastSubscription.get().getEndDate();
            Calendar calendarBegin = Calendar.getInstance();
            calendarBegin.setTime(startSubscriptionDate);
            calendarBegin.add(Calendar.DAY_OF_MONTH, 1);
            startSubscriptionDate = calendarBegin.getTime();
        }
        subscriptionUserEntity.setStartDate(startSubscriptionDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startSubscriptionDate);
        if (subscriptionUserEntity.getSubscription().getLengthDay() > 0) {
            calendar.add(Calendar.DAY_OF_MONTH, subscriptionUserEntity.getSubscription().getLengthDay());
        } else {
            calendar.add(Calendar.MONTH, subscriptionUserEntity.getSubscription().getLengthMonth());
        }
        subscriptionUserEntity.setEndDate(calendar.getTime());
        SubscriptionUserEntity subscriptionUserEntityNew = subscriptionUserRepository.save(subscriptionUserEntity);
        return subscriptionUserMapper.toSubscriptionUserUppDto(subscriptionUserEntityNew);
    }


    @Secured({"ADMIN", "READER"})
    public List<SubscriptionUserUppDto> getSubscriptionsOfUser(String login) {
        List<SubscriptionUserUppDto> subscriptionUserUppDto = new ArrayList<>();
        UserEntity user = userRepository.findByLogin(login).get();
        List<SubscriptionUserEntity> subscriptionUserEntities = subscriptionUserRepository.findByUserId(user.getId());
        for (SubscriptionUserEntity subscriptionUserEntity : subscriptionUserEntities) {
            subscriptionUserUppDto.add(subscriptionUserMapper.toSubscriptionUserUppDto(subscriptionUserEntity));
        }
        return subscriptionUserUppDto;
    }

}
