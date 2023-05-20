package com.epam;

public class NoSubscriberFoundException extends Exception {
    public NoSubscriberFoundException(String cardNumber) {
        super("No subscriber found for card: " + cardNumber);
    }
}
