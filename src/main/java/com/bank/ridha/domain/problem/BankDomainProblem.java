package com.bank.ridha.domain.problem;

public class BankDomainProblem extends RuntimeException {

    public BankDomainProblem(String message) {
        super("Problem found in banking app: %s".formatted(message));
    }
}
