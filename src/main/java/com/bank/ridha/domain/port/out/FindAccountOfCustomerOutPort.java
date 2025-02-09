package com.bank.ridha.domain.port.out;

import com.bank.ridha.domain.model.Account;

import java.util.Optional;

public interface FindAccountOfCustomerOutPort {

    Optional<Account> find(Integer customerId);

}
