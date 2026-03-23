package org.thales.model.employees;

import java.math.BigDecimal;

public class Manager extends Employee {

    private String agencyNumber;
    private BigDecimal bonus;

    public Manager(String name, String cpf, String email, String address, BigDecimal salary) {
        super(name, cpf, email, address, salary);
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(String agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    @Override
   public String getRole() {
        return "Manager";
    }

    @Override
    public String toString() {
        return "Manager{" +
                "agencyNumber='" + agencyNumber + '\'' +
                ", bonus=" + bonus +
                '}';
    }


}
