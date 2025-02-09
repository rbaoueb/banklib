package com.bank.ridha.domain.port.in;

@FunctionalInterface
public interface CloseAccountInPort {

    void execute(Integer customerId);

}
