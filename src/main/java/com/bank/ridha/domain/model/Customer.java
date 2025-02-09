package com.bank.ridha.domain.model;

public record Customer(Integer id, String firstName, String lastName, Account account) {

    public static Customer of(Integer id, String firstName, String lastName, Account account) {
        return new Customer(id, firstName, lastName, account);
    }
}
