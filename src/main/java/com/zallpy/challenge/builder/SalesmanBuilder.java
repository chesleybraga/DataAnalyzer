package com.zallpy.challenge.builder;

import com.zallpy.challenge.vo.Salesman;

/**
 * @author Chesley Braga
 */
public class SalesmanBuilder extends AbstractBuilder<Salesman> {

    @Override
    public Salesman build(String line) throws Exception {
	validate(line);

	Salesman salesman = new Salesman();
	String[] fields = getFields(line);

	salesman.setCpf(getCpf(fields));
	salesman.setName(getName(fields));
	salesman.setSalary(getSalary(fields));

	return salesman;
    }

    private String getCpf(String[] fields) {
	return fields[1];
    }

    private String getName(String[] fields) {
	return fields[2];
    }

    private double getSalary(String[] fields) {
	return Double.parseDouble(fields[3]);
    }
}