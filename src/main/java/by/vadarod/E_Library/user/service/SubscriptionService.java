package by.vadarod.E_Library.user.service;

import by.vadarod.E_Library.tools.exception.model.SubscriptionWithUsersException;
import by.vadarod.E_Library.user.dto.*;
import by.vadarod.E_Library.user.entity.SubscriptionEntity;
import by.vadarod.E_Library.user.entity.SubscriptionUserEntity;
import by.vadarod.E_Library.user.mapper.SubscriptionMapper;
import by.vadarod.E_Library.user.repository.SubscriptionRepository;
import by.vadarod.E_Library.user.repository.SubscriptionUserRepository;
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
    private final SubscriptionUserRepository subscriptionUserRepository;

    public List<SubscriptionUppDto> getAllSubscription() {
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository.findAll();
        return subscriptionEntityList.stream().map(subscriptionMapper::subscriptionEntityToSubscriptionUppDto).toList();

    }

    public void dellById(long id) throws SubscriptionWithUsersException {
        SubscriptionUserEntity subscriptionUserEntity = subscriptionUserRepository.findById(id).orElse(null);
        if (subscriptionUserEntity != null) {
            if (!subscriptionUserRepository.findBySubscriptionId(subscriptionUserEntity.getSubscription().getId()).isEmpty()) {
                throw new SubscriptionWithUsersException("Есть пользователи с таким видом подписки");

            } else {
                subscriptionRepository.deleteById(id);
            }
        }
    }

    public SubscriptionUppDto getById(long id) {
        return subscriptionMapper.subscriptionEntityToSubscriptionUppDto(subscriptionRepository.getById(id));
    }

    public SubscriptionCreateDto saveSubscription(SubscriptionCreateDto subscriptionCreateDto) {
        SubscriptionEntity subscriptionEntity = subscriptionMapper.subscriptionDtoToSubscriptionEntity(subscriptionCreateDto);
        subscriptionRepository.save(subscriptionEntity);
        return subscriptionCreateDto;
    }

    public SubscriptionUppDto saveUppSubscription(SubscriptionUppDto subscriptionUppDto) {
        SubscriptionEntity subscriptionEntity = subscriptionMapper.subscriptionDtoToSubscriptionUppDto(subscriptionUppDto);
        subscriptionRepository.save(subscriptionEntity);
        return subscriptionUppDto;
    }

}
