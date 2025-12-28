package by.vadarod.service;

import by.vadarod.E_Library.tools.exception.model.UserLoginException;
import by.vadarod.E_Library.user.dto.SubscriptionUserCreateDto;
import by.vadarod.E_Library.user.dto.SubscriptionUserUppDto;
import by.vadarod.E_Library.user.entity.SubscriptionEntity;
import by.vadarod.E_Library.user.entity.SubscriptionUserEntity;
import by.vadarod.E_Library.user.entity.UserEntity;
import by.vadarod.E_Library.user.mapper.SubscriptionUserMapper;
import by.vadarod.E_Library.user.repository.SubscriptionUserRepository;
import by.vadarod.E_Library.user.repository.UserRepository;
import by.vadarod.E_Library.user.service.SubscriptionUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionUserServiceTest {

    @Mock
    SubscriptionUserMapper subscriptionUserMapper;

    @Mock
    SubscriptionUserRepository subscriptionUserRepository;


    @Mock
    UserRepository userRepository;

    @InjectMocks
    SubscriptionUserService subscriptionUserService;

    @Test
    public void test_add_userSubscription() throws UserLoginException {
        SubscriptionUserEntity subscriptionUserEntity  = new SubscriptionUserEntity();
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setName("test");
        subscriptionEntity.setId(100);
        subscriptionEntity.setPrice(200);
        subscriptionEntity.setLengthDay(30);
        subscriptionEntity.setLengthMonth((short) 0);
        subscriptionUserEntity.setSubscription(subscriptionEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setId(100L);
        userEntity.setPassword("test");
        userEntity.setLogin("test");

        when(userRepository.findByLogin(any())).thenReturn(Optional.of(userEntity));
        when(subscriptionUserMapper.toSubscriptionUserEntity(any(), any())).thenReturn(subscriptionUserEntity);
        when(subscriptionUserRepository.findFirstByUserIdOrderByEndDateDesc(any(Long.class))).thenReturn(Optional.empty());
        when(subscriptionUserRepository.save(any())).thenAnswer(userSub -> {
            SubscriptionUserEntity subUser = userSub.getArgument(0);
            subUser.setId(100);
            return subUser;
        });

        when(subscriptionUserMapper.toSubscriptionUserUppDto(any())).thenAnswer(userSub -> {
            SubscriptionUserEntity subUser = userSub.getArgument(0);
            SubscriptionUserUppDto subscriptionUserUppDto = new SubscriptionUserUppDto();
            subscriptionUserUppDto.setSubscriptionId(subUser.getSubscription().getId());
            subscriptionUserUppDto.setId(subUser.getId());
            subscriptionUserUppDto.setUserId(subUser.getUser().getId());
            return subscriptionUserUppDto;
        });


        SubscriptionUserCreateDto  subscriptionUserCreateDto = new SubscriptionUserCreateDto();
        subscriptionUserCreateDto.setSubscriptionId(100L);
        SubscriptionUserUppDto subscriptionUserUppDto = subscriptionUserService.saveSubscriptionUser(new SubscriptionUserCreateDto(), "test");

        assertEquals(100, subscriptionUserUppDto.getUserId());
    }

    @Test
    public void test_add_userSubscriptionDate() throws UserLoginException {
        SubscriptionUserEntity subscriptionUserEntity  = new SubscriptionUserEntity();
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setName("test");
        subscriptionEntity.setId(100);
        subscriptionEntity.setPrice(200);
        subscriptionEntity.setLengthDay(30);
        subscriptionEntity.setLengthMonth((short) 0);
        subscriptionUserEntity.setSubscription(subscriptionEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setId(100L);
        userEntity.setPassword("test");
        userEntity.setLogin("test");

        when(userRepository.findByLogin(any())).thenReturn(Optional.of(userEntity));
        when(subscriptionUserMapper.toSubscriptionUserEntity(any(), any())).thenReturn(subscriptionUserEntity);
        when(subscriptionUserRepository.findFirstByUserIdOrderByEndDateDesc(any(Long.class))).thenReturn(Optional.empty());
        when(subscriptionUserRepository.save(any())).thenAnswer(userSub -> {
            SubscriptionUserEntity subUser = userSub.getArgument(0);
            subUser.setId(100);
            return subUser;
        });

        when(subscriptionUserMapper.toSubscriptionUserUppDto(any())).thenAnswer(userSub -> {
            SubscriptionUserEntity subUser = userSub.getArgument(0);
            SubscriptionUserUppDto subscriptionUserUppDto = new SubscriptionUserUppDto();
            subscriptionUserUppDto.setSubscriptionId(subUser.getSubscription().getId());
            subscriptionUserUppDto.setId(subUser.getId());
            subscriptionUserUppDto.setUserId(subUser.getUser().getId());
            subscriptionUserUppDto.setStartDate(subUser.getStartDate());
            return subscriptionUserUppDto;
        });


        SubscriptionUserCreateDto  subscriptionUserCreateDto = new SubscriptionUserCreateDto();
        subscriptionUserCreateDto.setSubscriptionId(100L);
        SubscriptionUserUppDto subscriptionUserUppDto = subscriptionUserService.saveSubscriptionUser(new SubscriptionUserCreateDto(), "test");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(sdf.format(new Date()), sdf.format(subscriptionUserUppDto.getStartDate()));
    }

    @Test
    public void test_add_userSubscriptionIsDate() throws UserLoginException {
        SubscriptionUserEntity subscriptionUserEntity  = new SubscriptionUserEntity();
        SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
        subscriptionEntity.setName("test");
        subscriptionEntity.setId(100);
        subscriptionEntity.setPrice(200);
        subscriptionEntity.setLengthDay(30);
        subscriptionEntity.setLengthMonth((short) 0);
        subscriptionUserEntity.setSubscription(subscriptionEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setId(100L);
        userEntity.setPassword("test");
        userEntity.setLogin("test");

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.setTime(new Date());
        calendarBegin.add(Calendar.DAY_OF_MONTH, 30);

        SubscriptionUserEntity subscriptionEntityPrevious = new SubscriptionUserEntity();
        subscriptionEntityPrevious.setId(100L);
        subscriptionEntityPrevious.setStartDate(new Date());
        subscriptionEntityPrevious.setEndDate(calendarBegin.getTime());
        subscriptionEntityPrevious.setSubscription(subscriptionEntity);

        when(userRepository.findByLogin(any())).thenReturn(Optional.of(userEntity));
        when(subscriptionUserMapper.toSubscriptionUserEntity(any(), any())).thenReturn(subscriptionUserEntity);
        when(subscriptionUserRepository.findFirstByUserIdOrderByEndDateDesc(any(Long.class))).thenReturn(Optional.of(subscriptionEntityPrevious));
        when(subscriptionUserRepository.save(any())).thenAnswer(userSub -> {
            SubscriptionUserEntity subUser = userSub.getArgument(0);
            subUser.setId(100);
            return subUser;
        });

        when(subscriptionUserMapper.toSubscriptionUserUppDto(any())).thenAnswer(userSub -> {
            SubscriptionUserEntity subUser = userSub.getArgument(0);
            SubscriptionUserUppDto subscriptionUserUppDto = new SubscriptionUserUppDto();
            subscriptionUserUppDto.setSubscriptionId(subUser.getSubscription().getId());
            subscriptionUserUppDto.setId(subUser.getId());
            subscriptionUserUppDto.setUserId(subUser.getUser().getId());
            subscriptionUserUppDto.setStartDate(subUser.getStartDate());
            return subscriptionUserUppDto;
        });


        SubscriptionUserCreateDto  subscriptionUserCreateDto = new SubscriptionUserCreateDto();
        subscriptionUserCreateDto.setSubscriptionId(100L);
        SubscriptionUserUppDto subscriptionUserUppDto = subscriptionUserService.saveSubscriptionUser(new SubscriptionUserCreateDto(), "test");


        calendarBegin.add(Calendar.DAY_OF_MONTH, 1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(sdf.format(calendarBegin.getTime()), sdf.format(subscriptionUserUppDto.getStartDate()));
    }
}
