package com.bank.ridha.domain.port.in;

import com.bank.ridha.domain.model.Customer;

@FunctionalInterface
public interface CreateCustomerInPort {

    Customer execute(Customer customer);

}
