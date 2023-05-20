package com.epam.bank;

import com.epam.dto.BankCard;
import com.epam.dto.BankCardType;
import com.epam.dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType cardType);
}
