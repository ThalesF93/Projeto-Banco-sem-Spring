package org.thales.model.accounts;

import org.thales.enums.FeeType;
import org.thales.enums.OperationType;
import org.thales.exceptions.InsufficientBalanceException;
import org.thales.model.Transaction;
import org.thales.model.holders.Holder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class GoldAccount extends Account{

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT)
            .withLocale(Locale.US);

    private static final DecimalFormat US_FORMATTER = new DecimalFormat("¤#,##0.00",
            new DecimalFormatSymbols(Locale.US));

    private BigDecimal overdraft = new BigDecimal("10000");


    public GoldAccount(Holder holder) {
        super(holder);
        this.balance = getBalance().add(overdraft);
    }

    public BigDecimal getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(BigDecimal overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    protected void balanceValidation(BigDecimal amount) {
        if (getBalance().compareTo(amount) > 0){
            throw new InsufficientBalanceException("Unauthorized operation! Withdraw must not be more than balance");
        };
    }

    @Override
    public void transference(Account destinationAccount, BigDecimal amount) {
        amountValidation(amount);

        BigDecimal fee = calculateFee(FeeType.GOLD_ACCOUNT_TRANSFER.getRate(), amount);

        balanceValidation(amount.add(fee));
        this.balance = this.balance.subtract(amount).subtract(fee);

        destinationAccount.deposit(amount);
        addTransactions((new Transaction(OperationType.TRANSFER, amount)));
        addTransactions((new Transaction(OperationType.FEE, fee)));
    }

    @Override
    public void generateBankStatement() {
        File statements = new File("C:\\Users\\User\\OneDrive\\Área de Trabalho\\Extratos\\teste.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(statements))){

            writer.write("- Bank Statement \n");
            writer.write("-----------------------------------------------------\n");
            writer.write("- Statement's Date: " + LocalDateTime.now().format(DATE_TIME_FORMATTER) + "\n");
            writer.write("- Account number " + this.getAccountNumber() + "\n");
            writer.write("- Holder:  " + getHolder().getName() + "\n");
            writer.write("- Overdraft available: " + US_FORMATTER.format(getOverdraft()) + "\n");
            writer.write("- Transactions: " + "\n");
            int transactionNumber = 1;
            for (Transaction transaction : this.getTransactions()){
                writer.write( String.format("Date: %s%n #%d -  Transaction: %s, value: %s%n%n", transaction.getDateTime().format(DATE_TIME_FORMATTER), transactionNumber++, transaction.getType(), US_FORMATTER.format(transaction.getAmount()) ));
            }
            writer.write("- Updated Balance: " + US_FORMATTER.format(this.getBalance()));
        }  catch (IOException e) {
            throw new RuntimeException("Failed to generate bank statement for account "
                    + this.getAccountNumber(), e);
        }
        super.showStatement();
    }

    public BigDecimal calculateFee(BigDecimal tax, BigDecimal amount) {
        return amount.multiply(tax);
    }

}
