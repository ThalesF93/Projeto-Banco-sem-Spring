package org.thales.enums;

import java.math.BigDecimal;

public enum FeeType {
    CURRENT_ACCOUNT_TRANSFER(new BigDecimal("0.05") ),
    SAVINGS_ACCOUNT_TRANSFER(new BigDecimal("0.00") ),
    WITHDRAWAL(new BigDecimal("0.02") ),
    PIX(new BigDecimal("0.01"));


    private final BigDecimal rate;

    FeeType(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
