package com.company.models;

import com.company.models.Department;

import java.math.BigDecimal;
import java.util.Objects;

public class Workers{
    private int id;
    private String surname;
    private String name;
    private BigDecimal salary;
    private Department department;


    @Override
    public String toString() {
        return "Workers{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Workers workers)) return false;
        return getId() == workers.getId() && Objects.equals(getSurname(), workers.getSurname()) && Objects.equals(getName(), workers.getName()) && Objects.equals(getSalary(), workers.getSalary()) && Objects.equals(getDepartment(), workers.getDepartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSurname(), getName(), getSalary(), getDepartment());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Workers() {
    }

    public Workers(int id, String surname, String name, BigDecimal salary, Department department) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
}