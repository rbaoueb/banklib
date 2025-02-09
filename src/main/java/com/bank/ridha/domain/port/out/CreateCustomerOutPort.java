package com.bank.ridha.domain.port.out;

import com.bank.ridha.domain.model.Customer;

public interface CreateCustomerOutPort {

    Customer create(Customer customer);

}
