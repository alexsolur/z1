package com.epam.service;

import com.epam.dto.BankCard;
import com.epam.dto.Subscription;
import com.epam.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {
    void subscribe(BankCard card);

    Optional<Subscription> getSubscriptionByBankCardNumber(String number);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);

    default double getAverageUsersAge() {
        return getAllUsers().stream().mapToDouble(user -> user.birthday().until(LocalDate.now(), ChronoUnit.YEARS))
                .average().orElse(0);
    }

    static boolean isPayableUser(User user) {
        return user.birthday().isBefore(LocalDate.now().minusYears(18));
    }
}