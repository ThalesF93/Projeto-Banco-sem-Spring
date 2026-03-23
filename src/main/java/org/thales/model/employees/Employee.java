package org.thales.model.employees;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Employee {

    protected String name;
    protected final String cpf;
    protected String email;
    protected String address;
    protected BigDecimal salary;


    public Employee(String name, String cpf, String email, String address, BigDecimal salary) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.address = address;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

   public abstract String getRole();


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(cpf, employee.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}
