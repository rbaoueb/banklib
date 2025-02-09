package com.bank.ridha.adapter.dao;

import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.model.AccountType;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.util.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoTest {

    private final CustomerDao customerDao = CustomerDao.getInstance();

    @Test
    void create_customer_test() {
        //GIVEN
        var customerToCreate = Customer.of(null,"Ridha","BAOUEB", Account.of("123456", AccountType.PERSONAL, Money.cents(1000), null));

        //WHEN
        var createdCustomer = customerDao.create(customerToCreate);

        //THEN
        assertNotNull(createdCustomer);
        assertNotNull(createdCustomer.id());
    }

    @Test
    void update_customer_test() {
        //GIVEN
        var customerToCreate = Customer.of(null,"Ridha","BAOUEB", Account.of("123456", AccountType.PERSONAL, Money.cents(1000), null));
        var createdCustomer = customerDao.create(customerToCreate);

        //WHEN
        var updatedCustomer = customerDao.update(Customer.of(createdCustomer.id(),"Ridha","Crespo", Account.of("78910", AccountType.PERSONAL, Money.cents(1000), null)));

        //THEN
        assertNotNull(updatedCustomer);
        assertNotNull(updatedCustomer.account());
        assertNotNull(updatedCustomer.id());
        assertEquals(updatedCustomer.id(), createdCustomer.id());
        assertEquals(updatedCustomer.lastName(), "Crespo");
        assertEquals(updatedCustomer.account().number(), "78910");
    }

    @Test
    void delete_customer_test() {
        //GIVEN
        var customerToCreate = Customer.of(null,"Ridha","BAOUEB", Account.of("123456", AccountType.PERSONAL, Money.cents(1000), null));
        var createdCustomer = customerDao.create(customerToCreate);

        //WHEN
        customerDao.delete(createdCustomer.id());

        //THEN
        assertTrue(customerDao.find(createdCustomer.id()).isEmpty());
    }
}