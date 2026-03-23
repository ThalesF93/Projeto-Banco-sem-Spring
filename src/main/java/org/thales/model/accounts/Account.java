package org.thales.model.accounts;

import org.thales.enums.TransactionType;
import org.thales.exceptions.InsufficientBalanceException;
import org.thales.exceptions.InvalidAmountException;
import org.thales.model.Transaction;
import org.thales.model.holders.Holder;

import java.io.*;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public abstract class Account {

    private final Holder holder;
    private final String accountNumber;
    protected BigDecimal balance = BigDecimal.ZERO;
    private final List<Transaction> transactions = new ArrayList<>();

    private static final SecureRandom secureRandom = new SecureRandom();

    public Account(Holder holder) {
        this.holder = Objects.requireNonNull(holder, "Holder Cannot be null");
        this.accountNumber = generateAccountNumber();

    }
    public Holder getHolder() {
        return holder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;

                //Collections.unmodifiableList(transactions);
    }

    protected void addTransactions(Transaction transaction) {
        transactions.add(transaction);
    }

    public void withdrawal(BigDecimal amount){
        amountValidation(amount);
        balanceValidation(amount);
        this.balance = this.balance.subtract(amount);
        transactions.add(new Transaction(TransactionType.WITHDRAW, amount));
    }

    public void deposit(BigDecimal amount){
        amountValidation(amount);
        this.balance = this.balance.add(amount);
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount));
    }

    public void transference(Account destinationAccount, BigDecimal amount){
        amountValidation(amount);
        balanceValidation(amount);
        this.balance = this.balance.subtract(amount);
        destinationAccount.deposit(amount);
        transactions.add(new Transaction(TransactionType.TRANSFER, amount));

    }

    protected static void amountValidation(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidAmountException("Amount must more than zero");
        }
    }

    protected void balanceValidation(BigDecimal amount) {
        if (getBalance().compareTo(amount) < 0){
            throw new InsufficientBalanceException("Unauthorized operation! Withdraw must not be more than balance");
        }
    }

    private String generateAccountNumber(){
        int number = secureRandom.nextInt(90_000_000) + 10_000_000;
        return String.valueOf(number);
    }

    public void showStatement(){

        File file = new File("C:\\Users\\User\\OneDrive\\Área de Trabalho\\Extratos\\teste.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha = null;

            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro de I/O", e);
        }
    }

    public void generateBankStatement(Account account)  {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT)
                .withLocale(Locale.US);

        DecimalFormat USFormatter = new DecimalFormat("¤#,##0.00",
                new DecimalFormatSymbols(Locale.US));
        USFormatter.setParseBigDecimal(true);

        File statements = new File("C:\\Users\\User\\OneDrive\\Área de Trabalho\\Extratos\\teste.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(statements))){

            writer.write("- Bank Statement \n");
            writer.write("-----------------------------------------------------\n");
            writer.write("- Statement's Date: " + LocalDateTime.now().format(formatter) + "\n");
            writer.write("- Account number " + account.getAccountNumber() + "\n");
            writer.write("- Holder:  " + getHolder().getName() + "\n");
            writer.write("- Transactions: " + "\n");
            int transactionNumber = 1;
            for (Transaction transaction : account.getTransactions()){
                writer.write( String.format("Date: %s%n #%d -  Transaction: %s, value: %s%n%n", transaction.getDateTime().format(formatter), transactionNumber++, transaction.getType(), USFormatter.format(transaction.getAmount()) ));
            }
            writer.write("- Updated Balance: " + USFormatter.format(account.getBalance()));
          }  catch (IOException e) {
            throw new RuntimeException("Failed to generate bank statement for account "
                    + account.getAccountNumber(), e);
        }

    }

    @Override
    public String toString() {
        return "Account{" +
                "holder='" + holder + '\'' +
                ", accountNumber=" + accountNumber +
                ", amount=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountNumber);
    }


}
