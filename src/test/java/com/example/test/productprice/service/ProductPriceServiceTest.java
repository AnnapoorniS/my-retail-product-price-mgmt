package com.example.test.productprice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.productprice.dao.ProductPriceRepository;
import com.example.productprice.model.Price;
import com.example.productprice.model.ProductPrice;
import com.example.productprice.service.ProductPriceService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ProductPriceService.class })
public class ProductPriceServiceTest {

	@Autowired
	ProductPriceService productPriceService;
	
	@MockBean
	private ProductPriceRepository productPriceRepository;
	
	@Test
	public void createProductTest()
	{
		Price price = new Price(11.01, "USD");
		ProductPrice productPrice = new ProductPrice(1234, price );
		Mockito.when(productPriceRepository.save(productPrice)).thenReturn(productPrice);
		ProductPrice response = productPriceService.createProduct(productPrice);
		assert(response.getPrice().getValue() == productPrice.getPrice().getValue());
		assert(response.getPrice().getCurrencyCode().matches( productPrice.getPrice().getCurrencyCode()));
		assert(response.getId()==productPrice.getId());
	}
	
	@Test
	public void updateProductTest()
	{
		Price price = new Price(11.01, "USD");
		ProductPrice productPrice = new ProductPrice(1234, price );
		Mockito.when(productPriceRepository.save(productPrice)).thenReturn(productPrice);
		Mockito.when(productPriceRepository.existsById(productPrice.getId())).thenReturn(true);
		ProductPrice response = productPriceService.updateProduct(productPrice);
		assert(response.getPrice().getValue() == productPrice.getPrice().getValue());
		assert(response.getPrice().getCurrencyCode().matches( productPrice.getPrice().getCurrencyCode()));
		assert(response.getId()==productPrice.getId());
	}
	
	@Test
	public void getAllProductsTest()
	{
		Price price = new Price(11.01, "USD");
		ProductPrice productPrice = new ProductPrice(1234, price );
		List<ProductPrice> list = new ArrayList<>();
		list.add(productPrice);
		Mockito.when(productPriceRepository.findAll()).thenReturn(list);
		List<ProductPrice> response = productPriceService.getAllProducts();
		assert(response.size()==1);
		assert(response.get(0).getId()==productPrice.getId());
		assert(response.get(0).getPrice().getValue()==productPrice.getPrice().getValue());
		assert(response.get(0).getPrice().getCurrencyCode().matches(productPrice.getPrice().getCurrencyCode()));
		
	}
	
}
