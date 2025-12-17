package by.vadarod.E_Library.user.service;


import by.vadarod.E_Library.user.dto.*;
import by.vadarod.E_Library.user.entity.SubscriptionUserEntity;;
import by.vadarod.E_Library.user.mapper.SubscriptionUserMapper;
import by.vadarod.E_Library.user.repository.SubscriptionRepository;
import by.vadarod.E_Library.user.repository.SubscriptionUserRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionUserService {
    private final SubscriptionUserRepository subscriptionUserRepository;
    private final SubscriptionUserMapper subscriptionUserMapper;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;


    public void saveSubscriptionUser(SubscriptionUserCreateDto subscriptionUserCreateDto) {
        SubscriptionUserEntity subscriptionUserEntity = subscriptionUserMapper.toSubscriptionUserEntity(subscriptionUserCreateDto, userRepository,  subscriptionRepository);

        Date startSubscriptionDate = new Date();
        SubscriptionUserEntity lastSubscription = subscriptionUserRepository.findFirstByUserIdOrderByEndDateDesc(subscriptionUserEntity.getUser().getId());

        if (lastSubscription != null && startSubscriptionDate.before(lastSubscription.getEndDate())) {
            startSubscriptionDate = lastSubscription.getEndDate();
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
        subscriptionUserRepository.save(subscriptionUserEntity);
    }


    public List<SubscriptionUserUppDto> getSubscriptionsOfUser(long userId) {
        List<SubscriptionUserUppDto> subscriptionUserUppDto = new ArrayList<>();
        List<SubscriptionUserEntity> subscriptionUserEntities = subscriptionUserRepository.findByUserId(userId);
        for (SubscriptionUserEntity subscriptionUserEntity : subscriptionUserEntities) {
            subscriptionUserUppDto.add(subscriptionUserMapper.toSubscriptionUserUppDto(subscriptionUserEntity));
        }
        return subscriptionUserUppDto;
    }

}
