package com.zallpy.challenge.comparator;

import org.junit.Assert;
import org.junit.Test;

import com.zallpy.challenge.vo.Item;
import com.zallpy.challenge.vo.Sale;

/**
 * @author Chesley Braga
 */
public class SaleComparatorTest {

    private SaleComparator comparator = new SaleComparator();

    @Test
    public void compareEqualToTest() throws Exception {
	Sale sale = new Sale();
	Item item = new Item();
	item.setQuantity(3);
	item.setPrice(10);
	sale.getItems().add(item);

	Sale otherSale = new Sale();
	Item otherItem = new Item();
	otherItem.setQuantity(2);
	otherItem.setPrice(15);
	otherSale.getItems().add(otherItem);

	int expected = 0; // equal to
	int actual = comparator.compare(sale, otherSale);
	Assert.assertEquals(expected, actual);

	double expectedSaleTotal = 30;
	double actualSaleTotal = sale.getTotal();
	Assert.assertEquals(expectedSaleTotal, actualSaleTotal, 0);

	double expectedOtherSaleTotal = 30;
	double actualOtherSaleTotal = otherSale.getTotal();
	Assert.assertEquals(expectedOtherSaleTotal, actualOtherSaleTotal, 0);
    }

    @Test
    public void compareGreaterTest() throws Exception {
	Sale sale = new Sale();
	Item item = new Item();
	item.setQuantity(2);
	item.setPrice(10);
	sale.getItems().add(item);
	sale.getItems().add(item);

	Sale otherSale = new Sale();
	Item otherItem = new Item();
	otherItem.setQuantity(2);
	otherItem.setPrice(15);
	otherSale.getItems().add(otherItem);

	int expected = 1; // greater
	int actual = comparator.compare(sale, otherSale);
	Assert.assertEquals(expected, actual);

	double expectedSaleTotal = 40;
	double actualSaleTotal = sale.getTotal();
	Assert.assertEquals(expectedSaleTotal, actualSaleTotal, 0);

	double expectedOtherSaleTotal = 30;
	double actualOtherSaleTotal = otherSale.getTotal();
	Assert.assertEquals(expectedOtherSaleTotal, actualOtherSaleTotal, 0);
    }

    @Test
    public void compareLessThanTest() throws Exception {
	Sale sale = new Sale();
	Item item = new Item();
	item.setQuantity(1);
	item.setPrice(10);
	sale.getItems().add(item);

	Sale otherSale = new Sale();
	Item otherItem = new Item();
	otherItem.setQuantity(2);
	otherItem.setPrice(15);
	otherSale.getItems().add(otherItem);

	int expected = -1; // less than
	int actual = comparator.compare(sale, otherSale);
	Assert.assertEquals(expected, actual);

	double expectedSaleTotal = 10;
	double actualSaleTotal = sale.getTotal();
	Assert.assertEquals(expectedSaleTotal, actualSaleTotal, 0);

	double expectedOtherSaleTotal = 30;
	double actualOtherSaleTotal = otherSale.getTotal();
	Assert.assertEquals(expectedOtherSaleTotal, actualOtherSaleTotal, 0);
    }
}