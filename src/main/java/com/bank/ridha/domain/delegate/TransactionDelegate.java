package com.bank.ridha.domain.delegate;

import com.bank.ridha.domain.checker.IntegrityChecker;
import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.model.Operation;
import com.bank.ridha.domain.model.OperationType;
import com.bank.ridha.util.Money;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@UtilityClass
public class TransactionDelegate {

    private static final String DEPOSIT_LABEL = "Send cash";
    private static final String WITHDRAW_LABEL = "Withdraw cash";

    public static Account addAmount(Account account, Money amount) {
        var operations = account.operations() == null ? new ArrayList<Operation>() : account.operations();
        operations.add(Operation.of(DEPOSIT_LABEL, OffsetDateTime.now(), amount, OperationType.DEPOSIT));
        return Account.of(account.number(), account.type(), Money.cents(account.balance().amountInCents()+amount.amountInCents()), operations);
    }



    public static Account substractAmount(Account account, Money amount) {
        IntegrityChecker.checkAmountBeforeWithdrawal.accept(account.balance().amountInCents(),amount.amountInCents());
        var operations = account.operations() == null ? new ArrayList<Operation>() : account.operations();
        operations.add(Operation.of(WITHDRAW_LABEL, OffsetDateTime.now(), amount, OperationType.WITHDRAWAL));
        return Account.of(account.number(), account.type(), Money.cents(account.balance().amountInCents()-amount.amountInCents()), operations);
    }
}
