package com.bank.ridha.adapter.dao;

import com.bank.ridha.adapter.util.BankUtils;
import com.bank.ridha.domain.model.Customer;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerDao implements EntityMemoryTransaction<Customer, Integer> {

    //we can use persistence repo instead
    ConcurrentHashMap<Integer, Customer> customersMap = new ConcurrentHashMap<>();

    private static CustomerDao instance;

    public static CustomerDao getInstance() {
        if (instance == null) {
            synchronized (CustomerDao.class) {
                if (instance == null) {
                    instance = new CustomerDao();
                }
            }
        }
        return instance;
    }

    @Override
    public Customer create(Customer customer) {
        var customerToPersist =  Customer.of(BankUtils.generateId.get(), customer.firstName(), customer.lastName(), customer.account());
        customersMap.put(customerToPersist.id(), customerToPersist);
        return customerToPersist;
    }

    @Override
    public Customer update(Customer customer) {
        customersMap.put(customer.id(), customer);
        return customer;
    }

    @Override
    public void delete(Integer customerId) {
        customersMap.remove(customerId);
    }

    @Override
    public Optional<Customer> find(Integer customerId) {
        return Optional.ofNullable(customersMap.get(customerId));
    }
}
