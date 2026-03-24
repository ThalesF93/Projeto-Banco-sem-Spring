package org.thales.payments;

import org.thales.enums.OperationType;
import org.thales.model.Transaction;
import org.thales.model.accounts.Account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduledPayment {

    private final Account account;
    private final BigDecimal totalAmount;
    private final int installments;
    private final LocalDate startDate;
    private final String description;

    public ScheduledPayment(Account account, BigDecimal totalAmount,
                            int installments, LocalDate startDate,
                            String description) {
        this.account = account;
        this.totalAmount = totalAmount;
        this.installments = installments;
        this.startDate = startDate;
        this.description = description;
    }

    public BigDecimal calculateInstallment() {
        return totalAmount.divide(
                BigDecimal.valueOf(installments), 2, RoundingMode.HALF_UP
        );
    }

    public List<LocalDate> generatePaymentDates() {
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < installments; i++) {
            dates.add(startDate.plusMonths(i));
        }
        return dates;
    }

    public void execute() {
        BigDecimal installmentValue = calculateInstallment();
        for (LocalDate date : generatePaymentDates()) {
            account.payment(installmentValue);
            account.addTransactions(new Transaction(OperationType.PAYMENT, installmentValue, description, date.atStartOfDay()));
        }
    }
}
