package by.vadarod.E_Library.user.rest;

import by.vadarod.E_Library.tools.exception.model.SubscriptionWithUsersException;
import by.vadarod.E_Library.user.dto.SubscriptionCreateDto;
import by.vadarod.E_Library.user.dto.SubscriptionUppDto;
import by.vadarod.E_Library.user.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subscriptions")
@RequiredArgsConstructor
public class SubscriptionRestController {
    private final SubscriptionService subscriptionService;

    @PostMapping(value = "/limited/create_subscription", consumes = "application/json")
    public SubscriptionCreateDto saveSubscription (@Validated @RequestBody SubscriptionCreateDto subscriptionCreateDto) {
        return subscriptionService.saveSubscription(subscriptionCreateDto);
    }

    @PostMapping(value = "/limited/upp_subscription", consumes = "application/json")
    public SubscriptionUppDto uppSubscription (@Validated @RequestBody SubscriptionUppDto subscriptionUppDto) {
        return subscriptionService.saveUppSubscription(subscriptionUppDto);
    }

    @PostMapping(value = "/limited/dell_subscription", consumes = "application/json")
    public void dellSubscription (@Validated @RequestBody SubscriptionUppDto subscriptionUppDto) throws SubscriptionWithUsersException {
        subscriptionService.dellById(subscriptionUppDto.getId());
    }

    @GetMapping(value = "/for_all/{id}")
    public SubscriptionUppDto findById (@PathVariable Long id) {
        return subscriptionService.getById(id);
    }

    @GetMapping(value = "/for_all/all_subscriptions")
    public List<SubscriptionUppDto> findAllSubscriptions () {
        return subscriptionService.getAllSubscription();
    }


}
