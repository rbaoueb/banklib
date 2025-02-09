package com.bank.ridha.domain.usecase.customer;

import com.bank.ridha.adapter.out.CustomerAdapter;
import com.bank.ridha.domain.port.in.DeleteCustomerInPort;
import com.bank.ridha.domain.port.out.DeleteCustomerOutPort;

public class DeleteCustomerUseCase implements DeleteCustomerInPort {

    private final DeleteCustomerOutPort deleteCustomerOutPort = CustomerAdapter.getInstance();

    private static DeleteCustomerUseCase instance;

    public static DeleteCustomerUseCase getInstance() {
        if (instance == null) {
            synchronized (DeleteCustomerUseCase.class) {
                if (instance == null) {
                    instance = new DeleteCustomerUseCase();
                }
            }
        }
        return instance;
    }

    @Override
    public void execute(Integer customerId) {
        deleteCustomerOutPort.remove(customerId);
    }
}
