package com.bank.ridha.adapter.out;

import com.bank.ridha.adapter.dao.CustomerDao;
import com.bank.ridha.adapter.dao.EntityMemoryTransaction;
import com.bank.ridha.domain.checker.IntegrityChecker;
import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.port.out.*;

import java.util.Optional;

public class AccountAdapter implements CreateAccountOutPort, CloseAccountOutPort, FindAccountOfCustomerOutPort {

    private final EntityMemoryTransaction<Customer, Integer> customerDao = CustomerDao.getInstance();

    private static AccountAdapter instance;

    public static AccountAdapter getInstance() {
        if (instance == null) {
            synchronized (AccountAdapter.class) {
                if (instance == null) {
                    instance = new AccountAdapter();
                }
            }
        }
        return instance;
    }

    @Override
    public void close(Integer customerId) {
        var customer = customerDao.find(customerId).orElse(null);
        IntegrityChecker.checkPresentCustomer.accept(customerId,customer);
        var updatedCustomer = Customer.of(customer.id(), customer.firstName(), customer.lastName(),null);
        customerDao.update(updatedCustomer);
    }

    @Override
    public Account create(Integer customerId, Account account) {
        var customer = customerDao.find(customerId).orElse(null);
        var updatedCustomer = Customer.of(customer.id(), customer.firstName(), customer.lastName(),account);
        customerDao.update(updatedCustomer);
        return account;
    }

    @Override
    public Optional<Account> find(Integer customerId) {
        var customer = customerDao.find(customerId).orElse(null);
        IntegrityChecker.checkPresentCustomer.accept(customerId, customer);
        return Optional.ofNullable(customer.account());
    }
}
