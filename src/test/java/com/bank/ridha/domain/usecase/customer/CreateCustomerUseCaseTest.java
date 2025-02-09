package com.bank.ridha.domain.usecase.customer;

import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.model.AccountType;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.port.in.CreateCustomerInPort;
import com.bank.ridha.util.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateCustomerUseCaseTest {

    private final CreateCustomerInPort createCustomerInPort = CreateCustomerUseCase.getInstance();

    @Test
    void create_customer_it_test() {
        //GIVEN
        var customerToCreate = Customer.of(null,"Ridha","BAOUEB", Account.of("123456", AccountType.PERSONAL, Money.cents(1000), null));

        //WHEN
        var createdCustomer = createCustomerInPort.execute(customerToCreate);

        //THEN
        assertNotNull(createdCustomer);
        assertNotNull(createdCustomer.id());
    }
}