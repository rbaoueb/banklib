package com.bank.ridha.domain.port.out;


import com.bank.ridha.domain.model.Account;

public interface CreateAccountOutPort {

    Account create(Integer customerId, Account account);

}
