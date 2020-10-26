package com.zallpy.challenge.builder;

import java.util.Collection;

import com.zallpy.challenge.vo.Item;
import com.zallpy.challenge.vo.Sale;

/**
 * @author Chesley Braga
 */
public class SaleBuilder extends AbstractBuilder<Sale> {

    @Override
    public Sale build(String line) throws Exception {
	validate(line);

	Sale sale = new Sale();
	String[] fields = getFields(line);

	sale.setId(getId(fields));
	sale.getItems().addAll(getItems(fields));
	sale.setSalesmanName(getSalesmanName(fields));

	return sale;
    }

    private String getId(String[] fields) {
	return fields[1];
    }

    private Collection<Item> getItems(String[] fields) throws Exception {
	ItemBuilder itemBuilder = new ItemBuilder();
	return itemBuilder.build(fields[2]);
    }

    private String getSalesmanName(String[] fields) {
	return fields[3];
    }
}