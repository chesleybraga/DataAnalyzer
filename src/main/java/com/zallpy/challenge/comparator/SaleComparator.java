package com.zallpy.challenge.comparator;

import java.util.Comparator;

import com.zallpy.challenge.vo.Sale;

/**
 * @author Chesley Braga
 */
public class SaleComparator implements Comparator<Sale> {

    @Override
    public int compare(Sale sale, Sale otherSale) {
	int compare = 0;

	if (sale.getTotal() < otherSale.getTotal()) {
	    compare = -1;
	} else if (sale.getTotal() > otherSale.getTotal()) {
	    compare = 1;
	}

	return compare;
    }
}