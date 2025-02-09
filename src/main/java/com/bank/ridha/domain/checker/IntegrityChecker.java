package com.bank.ridha.domain.checker;

import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.problem.BankDomainProblem;
import com.bank.ridha.util.Money;
import lombok.experimental.UtilityClass;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@UtilityClass
public class IntegrityChecker {

    public static final BiConsumer<Integer, Account> checkNotPresentAccount = (customerId, account) -> {
        if(account != null)
        throw new BankDomainProblem("Customer %s already has an account.".formatted(customerId));
    };

    public static final BiConsumer<Integer, Account> checkPresentAccount = (customerId, account) -> {
        if(account == null)
        throw new BankDomainProblem("Customer %s does not have any account".formatted(customerId));
    };

    public static final BiConsumer<Integer, Customer> checkPresentCustomer = (customerId, customer) -> {
        if(customer == null)
        throw new BankDomainProblem("No customer found for id %s".formatted(customerId));
    };

    public static final Consumer<Money> checkPositiveAmount = (money) -> {
        if(money == null || money.amountInCents()< 0)
        throw new BankDomainProblem("Money should be positive amount");
    };

    public static final BiConsumer<Integer, Integer> checkAmountBeforeWithdrawal = (actualAmount, newAmount) -> {
        if(actualAmount < newAmount)
        throw new BankDomainProblem("Account does not have cash enough");
    };

}
