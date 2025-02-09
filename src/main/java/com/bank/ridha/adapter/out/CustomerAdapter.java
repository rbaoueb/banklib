package com.bank.ridha.adapter.out;

import com.bank.ridha.adapter.dao.CustomerDao;
import com.bank.ridha.adapter.dao.EntityMemoryTransaction;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.port.out.CreateCustomerOutPort;
import com.bank.ridha.domain.port.out.DeleteCustomerOutPort;
import com.bank.ridha.domain.port.out.FindCustomerOutPort;
import com.bank.ridha.domain.port.out.UpdateCustomerOutPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomerAdapter implements CreateCustomerOutPort, DeleteCustomerOutPort, FindCustomerOutPort, UpdateCustomerOutPort {

    private final EntityMemoryTransaction<Customer, Integer> customerDao;
    private static CustomerAdapter instance;

    public static CustomerAdapter getInstance() {
        if (instance == null) {
            synchronized (CustomerAdapter.class) {
                if (instance == null) {
                    instance = new CustomerAdapter(CustomerDao.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public Customer create(Customer customer) {
        //in case of persistence repo, we need to convert Customer to CustomerEntity and do the mapping in dedicated layer for SRE
        return customerDao.create(customer);
    }

    @Override
    public void remove(Integer customerId) {
        customerDao.delete(customerId);
    }

    @Override
    public Optional<Customer> find(Integer customerId) {
        return customerDao.find(customerId);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }
}
