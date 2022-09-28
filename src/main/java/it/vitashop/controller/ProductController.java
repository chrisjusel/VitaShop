package it.vitashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.vitashop.model.Product;
import it.vitashop.model.dto.product.ProductRequest;
import it.vitashop.model.dto.product.ProductResponse;
import it.vitashop.model.dto.product.converter.ProductRequestToProduct;
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
	public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest request) {
		log.info(request.getCategory());
		log.info("New '" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());

		Product product = productService.save(conversionService.convert(request, Product.class));
		ProductResponse response = conversionService.convert(product, ProductResponse.class);

		return new ResponseEntity<ProductResponse>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
		log.info("New '" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());

		Product product = productService.update(id, conversionService.convert(request, Product.class));
		ProductResponse response = conversionService.convert(product, ProductResponse.class);

		return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		log.info("New '" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());

		productService.delete(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
		log.info("New '" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());

		Page<ProductResponse> response = productService.findAll(pageable)
				.map(t -> conversionService.convert(t, ProductResponse.class));

		return new ResponseEntity<Page<ProductResponse>>(response, HttpStatus.OK);
	}

	@GetMapping("/category")
	public ResponseEntity<Page<ProductResponse>> findByCategory(@RequestParam String name, Pageable pageable) {
		log.info("New '" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());

		Page<ProductResponse> response = productService.findByCategory(name, pageable)
				.map(t -> conversionService.convert(t, ProductResponse.class));

		return new ResponseEntity<Page<ProductResponse>>(response, HttpStatus.OK);
	}
}
