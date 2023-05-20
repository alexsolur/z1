package com.epam.bank.cloud;

import com.epam.dto.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CloudBankTest {
    private CloudBank bank;
    private final User testUser = new User("", "", LocalDate.now());


    @Before
    public void setUp() {
        bank = new CloudBank();
    }

    @Test
    public void createCreditBankCard() {
        BankCard card = bank.createBankCard(testUser, BankCardType.CREDIT);
        assertTrue("Card is Credit", card instanceof CreditBankCard);
        assertEquals("Card for testUser", testUser, card.getUser());
        assertEquals("Card has number", 16, card.getNumber().length());
    }

    @Test
    public void createDebitBankCard() {
        BankCard card = bank.createBankCard(testUser, BankCardType.DEBIT);
        assertTrue("Card is Credit", card instanceof DebitBankCard);
        assertEquals("Card for testUser", testUser, card.getUser());
        assertEquals("Card has number", 16, card.getNumber().length());
    }
}