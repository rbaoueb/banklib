package com.bank.ridha.domain.usecase.customer;

import com.bank.ridha.adapter.out.CustomerAdapter;
import com.bank.ridha.domain.model.Customer;
import com.bank.ridha.domain.port.in.CreateCustomerInPort;
import com.bank.ridha.domain.port.out.CreateCustomerOutPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCustomerUseCase implements CreateCustomerInPort {

    private final CreateCustomerOutPort createCustomerOutPort;

    private static CreateCustomerUseCase instance;

    public static CreateCustomerUseCase getInstance() {
        if (instance == null) {
            synchronized (CreateCustomerUseCase.class) {
                if (instance == null) {
                    instance = new CreateCustomerUseCase(CustomerAdapter.getInstance());
                }
            }
        }
        return instance;
    }

    @Override
    public Customer execute(Customer customer) {
        return createCustomerOutPort.create(customer);
    }
}
