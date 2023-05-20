package com.epam.dto;

import java.time.LocalDate;

public record Subscription(String bankcard,
                           LocalDate startDate) {

    @Override
    public String toString() {
        return "Subscription of bankcard number " + bankcard + " from " + startDate;
    }
}
