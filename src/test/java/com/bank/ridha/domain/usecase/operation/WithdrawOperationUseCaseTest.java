package com.bank.ridha.domain.usecase.operation;

import com.bank.ridha.adapter.dao.CustomerDao;
import com.bank.ridha.domain.model.Account;
import com.bank.ridha.domain.model.AccountType;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.port.in.CreateCustomerInPort;
import com.bank.ridha.domain.port.in.DepositInPort;
import com.bank.ridha.domain.port.in.WithdrawInPort;
import com.bank.ridha.domain.problem.BankDomainProblem;
import com.bank.ridha.domain.usecase.customer.CreateCustomerUseCase;
import com.bank.ridha.util.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawOperationUseCaseTest {

    private final WithdrawInPort withdrawInPort = WithdrawOperationUseCase.getInstance();
    private final CreateCustomerInPort createCustomerInPort = CreateCustomerUseCase.getInstance();
    private final CustomerDao customerDao = CustomerDao.getInstance();

    @ParameterizedTest
    @MethodSource("paramsToTest")
    void withdraw_it_test(Customer customerToCreate) {
        //GIVEN
        var createdCustomer = createCustomerInPort.execute(customerToCreate);

        //WHEN
        withdrawInPort.execute(createdCustomer.id(), Money.cents(500));

        //THEN
        var fetchedCustomer = customerDao.find(createdCustomer.id()).orElse(null);
        assertNotNull(fetchedCustomer);
        assertNotNull(fetchedCustomer.account());
        assertEquals(fetchedCustomer.account().operations().size(),1);
        assertEquals(fetchedCustomer.account().operations().get(0).amount(),Money.cents(500));
        assertEquals(fetchedCustomer.account().balance(),Money.cents(createdCustomer.account().balance().amountInCents()-500));
    }

    @ParameterizedTest
    @MethodSource("invalidMoneyParamsToTest")
    void withdraw_it_with_invalid_money_test(Customer customerToCreate, Money money) {
        //GIVEN
        var createdCustomer = createCustomerInPort.execute(customerToCreate);

        //WHEN
        //THEN
        assertThrows(BankDomainProblem.class, () -> withdrawInPort.execute(createdCustomer.id(), money));
    }


    @ParameterizedTest
    @MethodSource("invalidCustomerParamsToTest")
    void without_it_without_account_test(Customer customerToCreate, Money money) {
        //GIVEN
        var createdCustomer = createCustomerInPort.execute(customerToCreate);
        //WHEN
        //THEN
        assertThrows(BankDomainProblem.class, () ->  withdrawInPort.execute(createdCustomer.id(), money));
    }


    @Test
    void withdraw_it_without_customer_test() {
        //GIVEN
        //WHEN
        //THEN
        assertThrows(BankDomainProblem.class, () ->  withdrawInPort.execute(999, Money.cents(5000)));
    }


    private static Stream<Arguments> paramsToTest() {
        return Stream.of(
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", Account.of("78910", AccountType.PERSONAL, Money.cents(1000), null))),
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", Account.of("78910", AccountType.PERSONAL, Money.cents(1000), new ArrayList<>())))
        );
    }

    private static Stream<Arguments> invalidMoneyParamsToTest() {
        return Stream.of(
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", Account.of("78910", AccountType.PERSONAL, Money.cents(1000), null)), Money.cents(-1)),
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", Account.of("78910", AccountType.PERSONAL, Money.cents(1000), null)), Money.cents(5000)),
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", Account.of("78910", AccountType.PERSONAL, Money.cents(1000), new ArrayList<>())), null)
        );
    }

    private static Stream<Arguments> invalidCustomerParamsToTest() {
        return Stream.of(
                Arguments.of(Customer.of(null,"Ridha","BAOUEB", null), Money.cents(5000))
        );
    }
}