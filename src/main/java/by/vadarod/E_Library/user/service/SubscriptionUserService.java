package by.vadarod.E_Library.user.service;


import by.vadarod.E_Library.user.dto.*;
import by.vadarod.E_Library.user.entity.SubscriptionEntity;
import by.vadarod.E_Library.user.entity.SubscriptionUserEntity;
import by.vadarod.E_Library.user.mapper.SubscriptionMapper;
import by.vadarod.E_Library.user.mapper.UserMapper;
import by.vadarod.E_Library.user.repository.SubscriptionRepository;
import by.vadarod.E_Library.user.repository.SubscriptionUserRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionUserService {
    private final SubscriptionUserRepository subscriptionUserRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;


    public void dellById(long id) {
        subscriptionUserRepository.deleteById(id);
    }

    public void saveSubscriptionUser(long userId, long subscriptionId) {
        SubscriptionUserEntity subscriptionUserEntity = new SubscriptionUserEntity();
        SubscriptionEntity  subscriptionEntity = subscriptionRepository.getById(subscriptionId);
        subscriptionUserEntity.setSubscription(subscriptionEntity);
        subscriptionUserEntity.setUser(userRepository.getById(userId));
        subscriptionUserEntity.setStartDate(new Date());
        Calendar calendar = Calendar.getInstance();
        if (subscriptionEntity.getLengthDay() > 0) {
            calendar.add(Calendar.DAY_OF_MONTH, subscriptionEntity.getLengthDay());
        } else {
            calendar.add(Calendar.MONTH, subscriptionEntity.getLengthMonth());
        }
        subscriptionUserEntity.setEndDate(calendar.getTime());
        subscriptionUserRepository.save(subscriptionUserEntity);
    }



}
