package com.bank.ridha.domain.usecase.customer;

import com.bank.ridha.adapter.dao.CustomerDao;
import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.model.AccountType;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.port.in.CreateCustomerInPort;
import com.bank.ridha.domain.port.in.DeleteCustomerInPort;
import com.bank.ridha.util.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCustomerUseCaseTest {

    private final CreateCustomerInPort createCustomerInPort = CreateCustomerUseCase.getInstance();
    private final DeleteCustomerInPort deleteCustomerInPort = DeleteCustomerUseCase.getInstance();
    private final CustomerDao customerDao = CustomerDao.getInstance();


    @Test
    void delete_customer_it_test() {
        //GIVEN
        var customerToCreate = Customer.of(null,"Ridha","BAOUEB", Account.of("123456", AccountType.PERSONAL, Money.cents(1000), null));
        var createdCustomer = createCustomerInPort.execute(customerToCreate);

        //WHEN
        deleteCustomerInPort.execute(createdCustomer.id());
        //THEN
        var fetchedCustomer = customerDao.find(createdCustomer.id());
        assertTrue(fetchedCustomer.isEmpty());
    }
}