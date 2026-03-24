package org.thales.model.accounts;

import org.thales.enums.FeeType;
import org.thales.enums.OperationType;
import org.thales.model.Transaction;
import org.thales.model.holders.Holder;

import java.math.BigDecimal;

public class StandardAccount extends Account{
    public StandardAccount(Holder holder) {
        super(holder);
    }

    @Override
    public void transference(Account destinationAccount, BigDecimal amount) {
        amountValidation(amount);
        BigDecimal fee = calculateFee(FeeType.CURRENT_ACCOUNT_TRANSFER.getRate(), amount);
        balanceValidation(amount.add(fee));
        this.balance = this.balance.subtract(amount).subtract(fee);
        destinationAccount.deposit(amount);
        addTransactions((new Transaction(OperationType.TRANSFER, amount.add(fee))));
        addTransactions((new Transaction(OperationType.FEE, fee)));
    }



    public BigDecimal calculateFee(BigDecimal tax, BigDecimal amount) {
        return amount.multiply(tax);
    }
}
