package com.zallpy.challenge.vo;

import com.zallpy.challenge.util.Constants;

/**
 * @author Chesley Braga
 */
public class Client {

    private String code;
    private String cnpj;
    private String name;
    private String businessArea;

    public Client() {
	code = Constants.CODE_CLIENT;
    }

    public String getCode() {
	return code;
    }

    public String getCnpj() {
	return cnpj;
    }

    public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getBusinessArea() {
	return businessArea;
    }

    public void setBusinessArea(String businessArea) {
	this.businessArea = businessArea;
    }

    @Override
    public String toString() {
	return "Client [code=" + code + ", cnpj=" + cnpj + ", name=" + name + ", businessArea=" + businessArea + "]";
    }
}