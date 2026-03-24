package org.thales.interfaces;

import org.thales.model.accounts.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Billable {

    void payBill(Account account, BigDecimal amount, String description);

    void schedulePayment(Account account, BigDecimal amount, LocalDate date, String description);
}


