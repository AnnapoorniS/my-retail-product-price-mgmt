package com.example.productprice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productprice.dao.ProductPriceRepository;
import com.example.productprice.model.ProductPrice;

@Service
public class ProductPriceService {

	@Autowired
	private ProductPriceRepository productPriceRepository;

	public ProductPrice createProduct(ProductPrice productPrice) {
		if (productPrice != null) {
			if (productPriceRepository.existsById(productPrice.getId())) {
				System.out.println("The product with same ID exists");
				return null;
			} else {
				System.out.println("New Product Added");
				return productPriceRepository.save(productPrice);
			}
		} else
			return null;
	}

	public ProductPrice updateProduct(ProductPrice productPrice) {
		if (productPrice != null) {
			if (!productPriceRepository.existsById(productPrice.getId())) {
				System.out.println("No product with the given ID exists");
				return null;
			} else {
				System.out.println("Product details updated");
				return productPriceRepository.save(productPrice);
			}
		} else
			return null;
	}

	public ProductPrice getProduct(int id) {
		if (!productPriceRepository.existsById(id)) {
			System.out.println("No product with the given ID exists");
			return null;
		} else {
			return productPriceRepository.findById(id).get();
		}
	}

	public List<ProductPrice> getAllProducts() {
		return productPriceRepository.findAll();
	}

	public String deleteProduct(int id) {
		if (!productPriceRepository.existsById(id)) {
			System.out.println("No product with the given ID exists");
			return null;
		} else {
			System.out.println("Product Deleted");
			productPriceRepository.deleteById(id);
			return "Deleted the product " + id;
		}
	}
}
