package by.vadarod.E_Library.user.rest;

import by.vadarod.E_Library.user.dto.SubscriptionUserCreateDto;
import by.vadarod.E_Library.user.dto.SubscriptionUserUppDto;
import by.vadarod.E_Library.user.service.SubscriptionUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "subscription_user")
@Slf4j
public class SubscriptionUserController {
    private final SubscriptionUserService subscriptionUserService;


    @PostMapping(value = "/create_user_subscription", consumes = "application/json")
    public void createSubscriptionUser (@RequestBody SubscriptionUserCreateDto subscriptionUserCreateDto)
    {
        log.info("Добавляем подписку {} пользователю {}", subscriptionUserCreateDto.getSubscriptionId(), subscriptionUserCreateDto.getUserId());
        subscriptionUserService.saveSubscriptionUser(subscriptionUserCreateDto);
    }

    @GetMapping(value = "/{id}")
    public List<SubscriptionUserUppDto> getSubscriptionsOfUser(@PathVariable long id) {
        return subscriptionUserService.getSubscriptionsOfUser(id);
    }
}
