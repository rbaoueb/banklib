package com.bank.ridha.domain.port.out;


import com.bank.ridha.domain.model.Customer;

import java.util.Optional;

public interface FindCustomerOutPort {

    Optional<Customer> find(Integer customerId);

}
