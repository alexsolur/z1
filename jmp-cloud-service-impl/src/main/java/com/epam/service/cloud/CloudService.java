package com.epam.service.cloud;

import com.epam.dto.BankCard;
import com.epam.dto.Subscription;
import com.epam.dto.User;
import com.epam.service.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class CloudService implements Service {
    private final Map<User, List<Subscription>> subsMap = new HashMap<>();

    @Override
    public void subscribe(BankCard card) {
        subsMap.computeIfAbsent(card.getUser(), u -> new LinkedList<>()).add(new Subscription(card.getNumber(), LocalDate.now()));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return getSubscriptionStream(subscription -> subscription.bankcard().equals(number)).findAny();
    }

    @Override
    public List<User> getAllUsers() {
        return subsMap.keySet().stream().toList(); // same as collect(Collectors.toUnmodifiableList())
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return getSubscriptionStream(condition).toList();
    }

    private Stream<Subscription> getSubscriptionStream(Predicate<Subscription> condition) {
        return subsMap.values().parallelStream().flatMap(Collection::stream).filter(condition);
    }
}
