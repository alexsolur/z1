package com.epam.bank.cloud;

import com.epam.bank.Bank;
import com.epam.dto.*;

import java.util.function.BiFunction;

public class CloudBank implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        var number = Double.toString(Math.random()).repeat(2).substring(2, 18);
        return getCardByType(cardType).apply(number, user);
    }

    private BiFunction<String, User, BankCard> getCardByType(BankCardType type) {
        return switch (type) {
            case CREDIT -> CreditBankCard::new;
            case DEBIT -> DebitBankCard::new;
        };
    }
}