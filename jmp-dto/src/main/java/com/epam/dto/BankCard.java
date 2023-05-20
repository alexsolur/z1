package com.epam.dto;

public abstract class BankCard {
    String number;
    User user;

    public BankCard(String number, User user) {
        this.number = number;
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "BankCard with number " + number + " for " + user;
    }
}