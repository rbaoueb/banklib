package com.bank.ridha.domain.usecase.account;

import com.bank.ridha.adapter.dao.CustomerDao;
import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.model.AccountType;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.port.in.CloseAccountInPort;
import com.bank.ridha.domain.port.in.CreateCustomerInPort;
import com.bank.ridha.domain.problem.BankDomainProblem;
import com.bank.ridha.domain.usecase.customer.CreateCustomerUseCase;
import com.bank.ridha.util.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CloseAccountUseCaseTest {

    private final CloseAccountInPort closeAccountInPort = CloseAccountUseCase.getInstance();
    private final CreateCustomerInPort createCustomerInPort = CreateCustomerUseCase.getInstance();
    private final CustomerDao customerDao = CustomerDao.getInstance();

    @ParameterizedTest
    @MethodSource("customersToTest")
    void close_account_it_test(Customer customerToCreate) {
        //GIVEN
        var createdCustomer = createCustomerInPort.execute(customerToCreate);

        //WHEN
        closeAccountInPort.execute(createdCustomer.id());

        //THEN
        var fetchedCustomer = customerDao.find(createdCustomer.id()).orElse(null);
        assertNotNull(fetchedCustomer);
        assertNull(fetchedCustomer.account());
    }

    private static Stream<Arguments> customersToTest() {
        return Stream.of(
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", Account.of("123456", AccountType.PERSONAL, Money.cents(1000), null))),
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", null))
        );
    }
}