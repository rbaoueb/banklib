package com.bank.ridha.domain.usecase.account;

import com.bank.ridha.adapter.dao.CustomerDao;
import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.model.AccountType;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.port.in.CloseAccountInPort;
import com.bank.ridha.domain.port.in.CreateAccountInPort;
import com.bank.ridha.domain.port.in.CreateCustomerInPort;
import com.bank.ridha.domain.problem.BankDomainProblem;
import com.bank.ridha.domain.usecase.customer.CreateCustomerUseCase;
import com.bank.ridha.util.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountUseCaseTest {

    private final CreateAccountInPort createAccountInPort = CreateAccountUseCase.getInstance();
    private final CreateCustomerInPort createCustomerInPort = CreateCustomerUseCase.getInstance();
    private final CustomerDao customerDao = CustomerDao.getInstance();

    @ParameterizedTest
    @MethodSource("customersToTest")
    void create_account_it_test(Customer customerToCreate, Account account) {
        //GIVEN
        var createdCustomer = createCustomerInPort.execute(customerToCreate);

        //WHEN
        createAccountInPort.create(createdCustomer.id(), account);

        //THEN
        var fetchedCustomer = customerDao.find(createdCustomer.id()).orElse(null);
        assertNotNull(fetchedCustomer);
        assertNotNull(fetchedCustomer.account());
        assertEquals(fetchedCustomer.account().number(),"78910");
    }

    @ParameterizedTest
    @MethodSource("customersFailToTest")
    void create_account_it_fail_test(Customer customerToCreate, Account account) {
        //GIVEN
        var createdCustomer = createCustomerInPort.execute(customerToCreate);

        //WHEN
        //THEN
        assertThrows(BankDomainProblem.class, () -> createAccountInPort.create(createdCustomer.id(), account));
    }

    @Test
    void create_account_it_without_customer_test() {
        //GIVEN
        //WHEN
        //THEN
        assertThrows(BankDomainProblem.class, () -> createAccountInPort.create(999, Account.of("123456", AccountType.PERSONAL, Money.cents(1000), null)));
    }

    private static Stream<Arguments> customersToTest() {
        return Stream.of(
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", null),  Account.of("78910", AccountType.PERSONAL, Money.cents(1000), null))
        );
    }

    private static Stream<Arguments> customersFailToTest() {
        return Stream.of(
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", Account.of("123456", AccountType.PERSONAL, Money.cents(1000), null)),  Account.of("78910", AccountType.PERSONAL, Money.cents(1000), null))
        );
    }
}