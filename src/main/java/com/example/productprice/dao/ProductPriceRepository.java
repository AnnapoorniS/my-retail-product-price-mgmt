package com.example.productprice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.productprice.model.ProductPrice;

@Repository
public interface ProductPriceRepository extends MongoRepository<ProductPrice, Integer> {

}
