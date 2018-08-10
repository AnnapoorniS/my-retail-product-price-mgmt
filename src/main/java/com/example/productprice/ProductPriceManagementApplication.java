package com.example.productprice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.productprice.dao.ProductPriceRepository;
import com.example.productprice.model.Price;
import com.example.productprice.model.ProductPrice;

@SpringBootApplication
public class ProductPriceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductPriceManagementApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
