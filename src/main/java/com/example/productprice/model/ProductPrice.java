package com.example.productprice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "ProductPrice")
public class ProductPrice {
	@Id
	int id;
	Price current_price;

	public ProductPrice(int id, Price current_price) {
		this.id = id;
		this.current_price = current_price;
	}

	@JsonProperty("id")
	public int getId() {
		return this.id;
	}

	@JsonProperty("current_price")
	public Price getPrice() {
		return this.current_price;
	}
}
