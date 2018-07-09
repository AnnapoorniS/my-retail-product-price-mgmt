package com.example.productprice.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.productprice.model.ProductPrice;
import com.example.productprice.security.JwtValidator;
import com.example.productprice.service.ProductPriceService;

@RestController
@RequestMapping("/products/prices")

public class ProductPriceController {

	@Autowired
	ProductPriceService productPriceService;

	@Autowired
	JwtValidator jwtValidator;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductPrice> create(@RequestBody ProductPrice productPrice,
			@RequestHeader("Authorization") String accessToken)
			throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, JSONException {
			ProductPrice response = null;
		if (jwtValidator.verifyJWT(accessToken)) {
			response = productPriceService.createProduct(productPrice);
			if (response == null) {
				return new ResponseEntity<ProductPrice>(response, HttpStatus.CONFLICT);
			} else {
				return new ResponseEntity<ProductPrice>(response, HttpStatus.CREATED);
			}
		} else
			return new ResponseEntity<ProductPrice>(response, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductPrice> read(@PathVariable int id,
			@RequestHeader("Authorization") String accessToken)
			throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, JSONException {
		ProductPrice response = null;
		if (jwtValidator.verifyJWT(accessToken)) {
			response = productPriceService.getProduct(id);
			if (response == null) {
				return new ResponseEntity<ProductPrice>(response, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<ProductPrice>(response, HttpStatus.OK);
			}
		} else
			return new ResponseEntity<ProductPrice>(response, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductPrice>> readAll(@RequestHeader("Authorization") String accessToken)
			throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, JSONException {
		List<ProductPrice> response = null;
		if (jwtValidator.verifyJWT(accessToken)) {
			 response = productPriceService.getAllProducts();
			if (response.isEmpty()) {
				return new ResponseEntity<List<ProductPrice>>(response, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<List<ProductPrice>>(response, HttpStatus.OK);
			}
		} else
			return new ResponseEntity<List<ProductPrice>>(response, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductPrice> update(@RequestBody ProductPrice productPrice,
			@RequestHeader("Authorization") String accessToken)
			throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, JSONException {
		ProductPrice response = null;
		if (jwtValidator.verifyJWT(accessToken)) {
			response = productPriceService.updateProduct(productPrice);
			if (response == null) {
				return new ResponseEntity<ProductPrice>(response, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<ProductPrice>(response, HttpStatus.OK);
			}
		} else
			return new ResponseEntity<ProductPrice>(response, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable int id, @RequestHeader("Authorization") String accessToken)
			throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, JSONException {

		String response = productPriceService.deleteProduct(id);
		if (jwtValidator.verifyJWT(accessToken)) {
			if (response != null) {
				return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("No Product with the ID " + id, HttpStatus.NOT_FOUND);
			}
		} else
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	}
}
