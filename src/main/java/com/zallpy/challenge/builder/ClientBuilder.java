package com.zallpy.challenge.builder;

import com.zallpy.challenge.vo.Client;

/**
 * @author Chesley Braga
 */
public class ClientBuilder extends AbstractBuilder<Client> {

    @Override
    public Client build(String line) throws Exception {
	validate(line);

	Client cliente = new Client();
	String[] fields = getFields(line);

	cliente.setCnpj(getCnpj(fields));
	cliente.setName(getName(fields));
	cliente.setBusinessArea(getBusinessArea(fields));

	return cliente;
    }

    private String getCnpj(String[] fields) {
	return fields[1];
    }

    private String getName(String[] fields) {
	return fields[2];
    }

    private String getBusinessArea(String[] fields) {
	return fields[3];
    }
}