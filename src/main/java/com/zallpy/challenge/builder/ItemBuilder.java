package com.zallpy.challenge.builder;

import java.util.ArrayList;
import java.util.Collection;

import com.zallpy.challenge.util.Constants;
import com.zallpy.challenge.util.Util;
import com.zallpy.challenge.vo.Item;

/**
 * @author Chesley Braga
 */
public class ItemBuilder extends AbstractBuilder<Collection<Item>> {

    @Override
    public Collection<Item> build(String line) throws Exception {
	validate(line);

	Collection<Item> items = new ArrayList<>();
	String[] itemsArray = getItems(line);

	for (String eachItem : itemsArray) {
	    Item item = new Item();
	    String[] fields = getFields(eachItem);

	    item.setId(getId(fields));
	    item.setQuantity(getQuantity(fields));
	    item.setPrice(getPrice(fields));

	    items.add(item);
	}

	return items;
    }

    private String getId(String[] fields) {
	return fields[0];
    }

    private int getQuantity(String[] fields) throws Exception {
	return Integer.parseInt(fields[1]);
    }

    private double getPrice(String[] fields) {
	return Double.parseDouble(fields[2]);
    }

    @Override
    protected void validate(String line) throws Exception {
	String[] items = getItems(line);

	if (Util.isEmpty(items)) {
	    throw new Exception("A linha '" + line + "' é vazia ou não válida");
	}

	for (String eachItem : items) {
	    if (eachItem != null) {
		String[] fields = getFields(eachItem);

		if (Util.isEmpty(fields) || (fields.length != 3)) {
		    throw new Exception("O item '" + eachItem + "' é vazio ou não válido");
		}
	    }
	}
    }

    private String[] getItems(String line) {
	String[] items = null;

	if (!Util.isEmpty(line)) {
	    items = line.replace("[", "").replace("]", "").split(Constants.SEPARATOR_ITEMS);
	}

	return items;
    }

    @Override
    public String[] getFields(String item) {
	String[] fields = null;

	if (!Util.isEmpty(item)) {
	    fields = item.split(Constants.SEPARATOR_ITEM_FIELDS);
	}

	return fields;
    }
}