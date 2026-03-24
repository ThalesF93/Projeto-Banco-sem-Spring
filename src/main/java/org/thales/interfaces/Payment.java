package org.thales.interfaces;

import org.thales.model.accounts.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Payment {
    BigDecimal salaryPayment(BigDecimal amount);

    void pix(Account destinationAccount, BigDecimal amount);

}
