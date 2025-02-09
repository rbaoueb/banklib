package com.bank.ridha.domain.usecase.operation;

import com.bank.ridha.adapter.out.CustomerAdapter;
import com.bank.ridha.domain.checker.IntegrityChecker;
import com.bank.ridha.domain.delegate.TransactionDelegate;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.port.in.DepositInPort;
import com.bank.ridha.domain.port.out.FindCustomerOutPort;
import com.bank.ridha.domain.port.out.UpdateCustomerOutPort;
import com.bank.ridha.util.Money;

public class DepositOperationUseCase implements DepositInPort {

    private final FindCustomerOutPort findCustomerOutPort = CustomerAdapter.getInstance();
    private final UpdateCustomerOutPort updateCustomerOutPort = CustomerAdapter.getInstance();

    private static DepositOperationUseCase instance;

    public static DepositOperationUseCase getInstance() {
        if (instance == null) {
            synchronized (DepositOperationUseCase.class) {
                if (instance == null) {
                    instance = new DepositOperationUseCase();
                }
            }
        }
        return instance;
    }

    @Override
    public void execute(Integer customerId, Money amount) {
        var customer = findCustomerOutPort.find(customerId).orElse(null);
        IntegrityChecker.checkPositiveAmount.accept(amount);
        IntegrityChecker.checkPresentCustomer.accept(customerId, customer);
        IntegrityChecker.checkPresentAccount.accept(customerId, customer.account());
        var updatedAccount = TransactionDelegate.addAmount(customer.account(), amount);
        customer = Customer.of(customer.id(),customer.firstName(),customer.lastName(), updatedAccount);
        updateCustomerOutPort.update(customer);
    }
}
