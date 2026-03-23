package org.thales;

import org.thales.enums.MaritalStatus;
import org.thales.model.Agency;
import org.thales.model.Bank;
import org.thales.model.accounts.Account;
import org.thales.model.accounts.StandardAccount;
import org.thales.model.employees.Manager;
import org.thales.model.holders.IndividualHolder;

import java.io.IOException;
import java.math.BigDecimal;

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


            account.deposit(new BigDecimal("300"));
            account.deposit(new BigDecimal("500"));
            account2.deposit(new BigDecimal("200"));

        //System.out.println(agency.showAccountDetails(account2.getAccountNumber()));
        //agency.showAccountsByBalance();
        //agency.showAccountsByNameAsc();
        account.generateBankStatement(account);
        account.showStatement();
    }
}
