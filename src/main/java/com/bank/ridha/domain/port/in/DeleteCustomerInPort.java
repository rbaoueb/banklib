package com.bank.ridha.domain.port.in;

@FunctionalInterface
public interface DeleteCustomerInPort {

    void execute(Integer customerId);

}
