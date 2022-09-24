package it.vitashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.model.Product;
import it.vitashop.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product save (Product product) {
		log.info("Adding new product...");
		log.info("New product '" + product.getName() + "' addedd");
		return productRepository.save(product);
	}
}
