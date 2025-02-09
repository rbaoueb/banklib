package com.bank.ridha.util;


public record Money(int amountInCents) {

    public static Money cents(int amountInCents) {
        return new Money(amountInCents);
    }

}
