package com.naruto.chapter01.vo;

import com.naruto.chapter01.em.Status;

import java.util.Objects;

public class EmployeeVO {

    private String name;

    private int age;

    private double salary;

    private Status status;

    public EmployeeVO() {
    }

    public EmployeeVO(String name) {
        this.name = name;
    }

    public EmployeeVO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public EmployeeVO(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public EmployeeVO(String name, int age, double salary, Status status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeVO that = (EmployeeVO) o;
        return age == that.age &&
                Double.compare(that.salary, salary) == 0 &&
                Objects.equals(name, that.name) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary, status);
    }

    @Override
    public String toString() {
        return "EmployeeVO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
