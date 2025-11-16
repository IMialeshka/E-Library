package by.vadarod.E_Library.user.service;

import by.vadarod.E_Library.user.dto.*;
import by.vadarod.E_Library.user.entity.SubscriptionEntity;
import by.vadarod.E_Library.user.mapper.SubscriptionMapper;
import by.vadarod.E_Library.user.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public List<SubscriptionUppDto> getAllRoles() {
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository.findAll();
        return subscriptionEntityList.stream().map(subscriptionMapper::subscriptionEntityToSubscriptionUppDto).toList();

    }

    public void dellById(long id) {
        subscriptionRepository.deleteById(id);
    }

    public SubscriptionUppDto getById(long id) {
        return subscriptionMapper.subscriptionEntityToSubscriptionUppDto(subscriptionRepository.getById(id));
    }

    public void saveSubscription(SubscriptionCreateDto subscriptionCreateDto) {
        SubscriptionEntity subscriptionEntity = subscriptionMapper.subscriptionDtoToSubscriptionEntity(subscriptionCreateDto);
        subscriptionRepository.save(subscriptionEntity);
    }

    public void saveUppSubscription(SubscriptionUppDto subscriptionUppDto) {
        SubscriptionEntity subscriptionEntity = subscriptionMapper.subscriptionDtoToSubscriptionUppDto(subscriptionUppDto);
        subscriptionRepository.save(subscriptionEntity);
    }

}
