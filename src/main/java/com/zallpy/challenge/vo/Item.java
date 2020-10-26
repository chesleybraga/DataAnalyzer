package com.zallpy.challenge.vo;

/**
 * @author Chesley Braga
 */
public class Item {

    private String id;
    private int quantity;
    private double price;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    @Override
    public String toString() {
	return "Item [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
    }
}