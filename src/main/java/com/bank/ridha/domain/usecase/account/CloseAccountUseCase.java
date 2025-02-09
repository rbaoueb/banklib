package com.bank.ridha.domain.usecase.account;

import com.bank.ridha.adapter.out.AccountAdapter;
import com.bank.ridha.domain.port.in.CloseAccountInPort;
import com.bank.ridha.domain.port.out.CloseAccountOutPort;

public class CloseAccountUseCase implements CloseAccountInPort {

    private final CloseAccountOutPort closeAccountOutPort = AccountAdapter.getInstance();

    private static CloseAccountUseCase instance;

    public static CloseAccountUseCase getInstance() {
        if (instance == null) {
            synchronized (CloseAccountUseCase.class) {
                if (instance == null) {
                    instance = new CloseAccountUseCase();
                }
            }
        }
        return instance;
    }

    @Override
    public void execute(Integer customerId) {
        closeAccountOutPort.close(customerId);
    }
}
