package org.thales.interfaces;

import org.thales.model.accounts.Account;

import java.math.BigDecimal;

public interface Refundable {

    void refund(Account account, BigDecimal amount, String reason);
}
