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

	/*
	 * @Bean public UserDetailsService userDetailsService() throws Exception { //
	 * ensure the passwords are encoded properly
	 * 
	 * UserBuilder users = User.withDefaultPasswordEncoder();
	 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 * manager.createUser(users.username("user").password("password").roles("USER").
	 * build());
	 * manager.createUser(users.username("admin").password("password").roles("USER",
	 * "ADMIN").build()); return manager; }
	 */
}

@Component
class GroupInitializer implements CommandLineRunner {

	private final ProductPriceRepository productPriceRepository;

	GroupInitializer(ProductPriceRepository productPriceRepository) {
		this.productPriceRepository = productPriceRepository;
	}

	@Transactional
	void createProduct(ProductPrice productPrice) {
		ProductPrice response = productPriceRepository.save(productPrice);
		System.out.println("success  " + response.getId() + "  " + response.getPrice().getValue() + "  "
				+ response.getPrice().getCurrencyCode());
	}

	@Override
	public void run(String... args) throws Exception {

		// Create Application
		// createApplication(Constants.application_AccessManagementSystemMainApplication);
		// createApplication(Constants.application_TestApplicationToManageResourceTypeOrder);
		Price price = new Price(9.99, "INR");
		ProductPrice productPrice = new ProductPrice(1235, price);
		// createProduct(product);

	}
}