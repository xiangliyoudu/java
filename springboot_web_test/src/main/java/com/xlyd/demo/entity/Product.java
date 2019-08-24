package com.xlyd.demo.entity;

public class Product {
	private int id;
	private String name;
	private double price;
	private int inStore;

	public Product() {
		super();
	}

	public Product(String name, double price, int inStore) {
		super();
		this.name = name;
		this.price = price;
		this.inStore = inStore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInStore() {
		return inStore;
	}

	public void setInStore(int inStore) {
		this.inStore = inStore;
	}

}
