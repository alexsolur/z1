package com.epam;

import com.epam.bank.Bank;
import com.epam.dto.BankCard;
import com.epam.dto.BankCardType;
import com.epam.dto.User;
import com.epam.service.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) throws NoSubscriberFoundException {
        var alice = new User("Alice", "Doe", LocalDate.of(2023, 5, 20));
        var bob = new User("Bob", "Doe", LocalDate.of(1984, 4, 20));

        Bank bank = ServiceLoader.load(ModuleLayer.boot(), Bank.class).findFirst().orElseThrow();
        Service service = ServiceLoader.load(Service.class).findFirst().orElseThrow();
        System.out.println("Using " + bank + " and " + service + " provided by service loader");

        BankCard creditA = bank.createBankCard(alice, BankCardType.CREDIT);
        BankCard debitA = bank.createBankCard(alice, BankCardType.DEBIT);
        BankCard debitB = bank.createBankCard(bob, BankCardType.DEBIT);
        BankCard[] cards = {creditA, debitA, debitB};

        System.out.println("\nAll cards:");
        Arrays.stream(cards).peek(System.out::println).forEach(service::subscribe);


        System.out.println("\nAverage age of subscribers: " + service.getAverageUsersAge());
        service.getAllUsers().stream()
                .map(user -> user.toString() + (Service.isPayableUser(user) ? " is" : " is not") + " payable")
                .forEach(System.out::println);

        System.out.println("\nAll subscriptions by predicate:");
        service.getAllSubscriptionsByCondition(sub -> sub.bankcard().indexOf('7') < 7).forEach(System.out::println);

        System.out.println("\nSubscriptions by card number:");
        for (String number : new String[]{debitA.getNumber(), "invalid card number"}) {
            System.out.println(service.getSubscriptionByBankCardNumber(number)
                    .orElseThrow(() -> new NoSubscriberFoundException(number)));
        }
    }
}