package com.bank.ridha.domain.model;

import com.bank.ridha.util.Money;
import lombok.ToString;

import java.time.OffsetDateTime;

public record Operation(String label, OffsetDateTime dateTime, Money amount, OperationType type) {

    public static Operation of(String label, OffsetDateTime dateTime, Money amount, OperationType type) {
        return new Operation(label, dateTime, amount, type);
    }
}
