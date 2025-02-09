package com.bank.ridha.domain.model;

import com.bank.ridha.util.Money;

import java.util.Currency;
import java.util.List;

public record Account(String number, AccountType type, Money balance, List<Operation> operations) {

    public static Account of(String number, AccountType type, Money balance, List<Operation> operations) {
        return new Account(number, type, balance, operations);
    }
}
