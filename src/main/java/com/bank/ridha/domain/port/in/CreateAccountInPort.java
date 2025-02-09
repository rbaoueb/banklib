package com.bank.ridha.domain.port.in;

import com.bank.ridha.domain.model.Account;

@FunctionalInterface
public interface CreateAccountInPort {

    void create(Integer customerId, Account account);

}
