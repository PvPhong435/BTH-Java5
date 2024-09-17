package com.lab2.model;

public class Products {
	private String name;
	private String price;
	private String maker;
	private String country;
	private String image;
	
	public Products() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Products(String name, String price, String maker, String country, String image) {
		super();
		this.name = name;
		this.price = price;
		this.maker = maker;
		this.country = country;
		this.image = image;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
