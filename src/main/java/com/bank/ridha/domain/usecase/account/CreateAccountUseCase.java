package com.bank.ridha.domain.usecase.account;

import com.bank.ridha.adapter.out.AccountAdapter;
import com.bank.ridha.domain.checker.IntegrityChecker;
import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.port.in.CreateAccountInPort;
import com.bank.ridha.domain.port.out.CreateAccountOutPort;
import com.bank.ridha.domain.port.out.FindAccountOfCustomerOutPort;

public class CreateAccountUseCase implements CreateAccountInPort {

    private final CreateAccountOutPort createAccountOutPort = AccountAdapter.getInstance();
    private final FindAccountOfCustomerOutPort findAccountOfCustomerOutPort = AccountAdapter.getInstance();

    private static CreateAccountUseCase instance;

    public static CreateAccountUseCase getInstance() {
        if (instance == null) {
            synchronized (CreateAccountUseCase.class) {
                if (instance == null) {
                    instance = new CreateAccountUseCase();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(Integer customerId, Account account) {
        var currentAccount = findAccountOfCustomerOutPort.find(customerId).orElse(null);
        IntegrityChecker.checkNotPresentAccount.accept(customerId, currentAccount);
        createAccountOutPort.create(customerId, account);
    }
}
