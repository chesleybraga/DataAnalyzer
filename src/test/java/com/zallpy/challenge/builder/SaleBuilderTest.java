package com.zallpy.challenge.builder;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.zallpy.challenge.vo.Item;
import com.zallpy.challenge.vo.Sale;

/**
 * @author Chesley Braga
 */
public class SaleBuilderTest {

    private SaleBuilder builder = new SaleBuilder();

    @SuppressWarnings("deprecation")
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void buildTest() throws Exception {
	String expectedCode = "003";
	String expectedId = "10";
	int expected1Quantity = 10;
	double expected1Price = 100;
	int expected2Quantity = 30;
	double expected2Price = 2.5;
	int expected3Quantity = 40;
	double expected3Price = 3.10;
	String expectedSalesmanName = "Pedro";
	String expectedToString = "Sale [code=003, id=10, items=[Item [id=1, quantity=10, price=100.0], Item [id=2, quantity=30, price=2.5], Item [id=3, quantity=40, price=3.1]], salesmanName=Pedro]";
	String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

	Sale actual = builder.build(line);
	Assert.assertEquals(expectedCode, actual.getCode());
	Assert.assertEquals(expectedId, actual.getId());

	Collection<Item> items = actual.getItems();
	Assert.assertTrue(items != null);
	Assert.assertTrue(items.size() == 3);

	for (Item eachItem : items) {
	    if ("1".equals(eachItem.getId())) {
		Assert.assertEquals(expected1Quantity, eachItem.getQuantity());
		Assert.assertEquals(expected1Price, eachItem.getPrice(), 0);
	    } else if ("2".equals(eachItem.getId())) {
		Assert.assertEquals(expected2Quantity, eachItem.getQuantity());
		Assert.assertEquals(expected2Price, eachItem.getPrice(), 0);
	    } else if ("3".equals(eachItem.getId())) {
		Assert.assertEquals(expected3Quantity, eachItem.getQuantity());
		Assert.assertEquals(expected3Price, eachItem.getPrice(), 0);
	    }
	}

	Assert.assertEquals(expectedSalesmanName, actual.getSalesmanName());
	Assert.assertEquals(expectedToString, actual.toString());
    }

    @Test
    public void buildEmptyTest() throws Exception {
	thrown.expect(Exception.class);

	String line = "";
	String expectMessage = "A linha '" + line + "' é vazia ou não válida";
	thrown.expectMessage(expectMessage);

	builder.build(line);
    }

    @Test
    public void buildMissingFieldsTest() throws Exception {
	thrown.expect(Exception.class);

	String line = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]";
	String expectMessage = "A linha '" + line + "' é vazia ou não válida";
	thrown.expectMessage(expectMessage);

	builder.build(line);
    }
}