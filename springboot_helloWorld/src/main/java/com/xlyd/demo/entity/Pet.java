package com.xlyd.demo.entity;

public class Pet {
	private String name;
	private String category;

	@Override
	public String toString() {
		return "Pet [name=" + name + ", category=" + category + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
