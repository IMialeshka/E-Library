package by.vadarod.service;

import by.vadarod.E_Library.user.dto.SubscriptionCreateDto;
import by.vadarod.E_Library.user.entity.SubscriptionEntity;
import by.vadarod.E_Library.user.mapper.SubscriptionMapper;
import by.vadarod.E_Library.user.repository.SubscriptionRepository;
import by.vadarod.E_Library.user.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest {
    @Mock
    SubscriptionRepository subscriptionRepository;

    @Mock
    SubscriptionMapper subscriptionMapper;

    @InjectMocks
    SubscriptionService subscriptionService;

    @Test
    public void test_add_subscription(){
        when(subscriptionMapper.subscriptionDtoToSubscriptionEntity(any())).thenReturn(new SubscriptionEntity());
        when(subscriptionRepository.save(any())).thenReturn(new SubscriptionEntity());
        subscriptionService.saveSubscription(new SubscriptionCreateDto());
        verify(subscriptionRepository, times(1)).save(any());
    }

}
