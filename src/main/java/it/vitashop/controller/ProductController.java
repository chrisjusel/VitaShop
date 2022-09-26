package it.vitashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import it.vitashop.model.Product;
import it.vitashop.model.dto.product.ProductRequest;
import it.vitashop.model.dto.product.ProductResponse;
import it.vitashop.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ConversionService conversionService;

	@PostMapping
	public ResponseEntity<Product> save(@RequestBody ProductRequest request) {
		log.info("New '" + new Object() {}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());
		Product response = productService.save(conversionService.convert(request, Product.class));
		return new ResponseEntity<Product>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductResponse>> findByCategory(@RequestParam String category, Pageable pageable){
		log.info("New '" + new Object() {}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());
		Page<ProductResponse> response = productService.findByCategory(category, pageable)
				.map(t -> conversionService.convert(t, ProductResponse.class));
		return new ResponseEntity<Page<ProductResponse>>(response, HttpStatus.OK);
	}
}
