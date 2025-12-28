package by.vadarod.E_Library.user.rest;

import by.vadarod.E_Library.user.dto.SubscriptionUserCreateDto;
import by.vadarod.E_Library.user.dto.SubscriptionUserUppDto;
import by.vadarod.E_Library.user.service.SubscriptionUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "subscription_user")
@Slf4j
public class SubscriptionUserController {
    private final SubscriptionUserService subscriptionUserService;


    @PostMapping(value = "/create_user_subscription", consumes = "application/json")
    public SubscriptionUserUppDto createSubscriptionUser (@Validated @RequestBody SubscriptionUserCreateDto subscriptionUserCreateDto, @AuthenticationPrincipal UserDetails userDetails)
    {
        log.info("Добавляем подписку {} пользователю {}", subscriptionUserCreateDto.getSubscriptionId(), subscriptionUserCreateDto.getUserId());
        return subscriptionUserService.saveSubscriptionUser(subscriptionUserCreateDto, userDetails.getUsername());
    }

    @GetMapping(value = "/subscription_of_user")
    public List<SubscriptionUserUppDto> getSubscriptionsOfUser(@AuthenticationPrincipal UserDetails userDetails) {
        return subscriptionUserService.getSubscriptionsOfUser(userDetails.getUsername());
    }
}
