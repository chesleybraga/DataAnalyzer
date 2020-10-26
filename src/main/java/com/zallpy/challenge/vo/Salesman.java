package com.zallpy.challenge.vo;

import com.zallpy.challenge.util.Constants;

/**
 * @author Chesley Braga
 */
public class Salesman {

    private String code;
    private String cpf;
    private String name;
    private double salary;

    public Salesman() {
	code = Constants.CODE_SALESMAN;
    }

    public String getCode() {
	return code;
    }

    public String getCpf() {
	return cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getSalary() {
	return salary;
    }

    public void setSalary(double salary) {
	this.salary = salary;
    }

    @Override
    public String toString() {
	return "Salesman [code=" + code + ", cpf=" + cpf + ", name=" + name + ", salary=" + salary + "]";
    }
}