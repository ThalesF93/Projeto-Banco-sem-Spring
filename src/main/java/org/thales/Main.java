package org.thales;

import org.thales.enums.MaritalStatus;
import org.thales.model.Agency;
import org.thales.model.Bank;
import org.thales.model.accounts.Account;
import org.thales.model.accounts.GoldAccount;
import org.thales.model.accounts.StandardAccount;
import org.thales.model.employees.Manager;
import org.thales.model.holders.IndividualHolder;
import org.thales.payments.ScheduledPayment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        Bank bank = new Bank();
        Manager manager =
                new Manager("Thales", "123.456.789-10",
                        "thales@gmail.com",
                        "Rua Das Couves 59"
                        , new BigDecimal("3000"));

        Agency agency = bank.openAgency("Macuco - Santos", "Avenia Afonso pena 148", manager);

        manager.setAgencyNumber(agency.getAgencyNumber());

        Account account = agency.openAccount(
                new StandardAccount(
                        new IndividualHolder(
                                "João Silva",
                                "Rua das Flores 123",
                                "joao@email.com",
                                "111.222.333-44",
                                MaritalStatus.CASADO)));

        Account account2 = agency.openAccount(
                new StandardAccount(
                        new IndividualHolder(
                                "Maria Santos",
                                "Avenida Central 456",
                                "maria@email.com",
                                "555.666.777-88",
                                MaritalStatus.SOLTEIRO)));

        Account account3 = agency.openAccount(
                new StandardAccount(
                        new IndividualHolder(
                                "Arnaldo Ribeiro",
                                "Avenida Brasil 666",
                                "Arnaldo@email.com",
                                "999.000.111-22",
                                MaritalStatus.VIUVO)));

        Account account4 = agency.openAccount(
                new GoldAccount(
                        new IndividualHolder(
                                "Edson Arantes",
                                "Vila Belmiro, 1000",
                                "Pelé@email.com",
                                "999.000.111-22",
                                MaritalStatus.DIVORCIADO)));
        account2.deposit(new BigDecimal("1500"));
        account2.transference(account4,new BigDecimal("1000"));
        account4.deposit(new BigDecimal("100"));




        ScheduledPayment payment = new ScheduledPayment(account4, new BigDecimal("500"), 4, LocalDate.now(), "Health Insurance");
        payment.execute();
        account4.withdraw(new BigDecimal("200"));

        account4.deposit(new BigDecimal("1200"));
        account4.generateBankStatement();

    }
}
