package com.zallpy.challenge.vo;

import java.util.ArrayList;
import java.util.Collection;

import com.zallpy.challenge.util.Constants;
import com.zallpy.challenge.util.Util;

/**
 * @author Chesley Braga
 */
public class Sale {

    private String code;
    private String id;
    private Collection<Item> items;
    private String salesmanName;

    public Sale() {
	code = Constants.CODE_SALE;
	items = new ArrayList<>();
    }

    public String getCode() {
	return code;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Collection<Item> getItems() {
	return items;
    }

    public String getSalesmanName() {
	return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
	this.salesmanName = salesmanName;
    }

    public double getTotal() {
	double total = 0;

	if (!Util.isEmpty(items)) {
	    for (Item eachItem : items) {
		if (eachItem != null) {
		    total += eachItem.getQuantity() * eachItem.getPrice();
		}
	    }
	}

	return total;
    }

    @Override
    public String toString() {
	return "Sale [code=" + code + ", id=" + id + ", items=" + items + ", salesmanName=" + salesmanName + "]";
    }
}