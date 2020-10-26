package com.zallpy.challenge.builder;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.zallpy.challenge.vo.Item;

/**
 * @author Chesley Braga
 */
public class ItemBuilderTest {

    private ItemBuilder builder = new ItemBuilder();

    @SuppressWarnings("deprecation")
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void buildTest() throws Exception {
	String expectedId = "1";
	int expectedQuantity = 10;
	double expectedPrice = 100;
	String expectedToString = "Item [id=1, quantity=10, price=100.0]";
	String line = "[1-10-100]";

	Collection<Item> items = builder.build(line);
	Assert.assertTrue(items != null);
	Assert.assertTrue(items.size() == 1);

	for (Item eachItem : items) {
	    Assert.assertEquals(expectedId, eachItem.getId());
	    Assert.assertEquals(expectedQuantity, eachItem.getQuantity());
	    Assert.assertEquals(expectedPrice, eachItem.getPrice(), 0);
	    Assert.assertEquals(expectedToString, eachItem.toString());
	}
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

	String line = "[1-10]";
	String expectMessage = "O item '" + line.replace("[", "").replace("]", "") + "' é vazio ou não válido";
	thrown.expectMessage(expectMessage);

	builder.build(line);
    }
}