package com.bank.ridha.domain.port.in;

import com.bank.ridha.util.Money;

@FunctionalInterface
public interface WithdrawInPort {

    void execute(Integer customerId, Money amount);

}
