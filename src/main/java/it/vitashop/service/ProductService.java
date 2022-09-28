package it.vitashop.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.vitashop.exception.CategoryNotFoundException;
import it.vitashop.exception.ProductNotFoundException;
import it.vitashop.model.Categories;
import it.vitashop.model.Category;
import it.vitashop.model.Product;
import it.vitashop.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	public Product save(Product product) {
		log.info("Adding new product...");
		log.info("New product '" + product.getName() + "' addedd");
		return productRepository.save(product);
	}

	@Transactional
	public Product update(Long id, Product product) {
		Optional<Product> productResult = productRepository.findById(id);
		log.info("Updating product...");

		if (productResult.isPresent()) {
			Product productUpdated = productResult.get();
			productUpdated.setId(id);
			productUpdated.setCategory(product.getCategory());
			productUpdated.setDescrption(product.getDescrption());
			productUpdated.setImageFile(product.getImageFile());
			productUpdated.setName(product.getName());
			productUpdated.setPrice(product.getPrice());
			log.info("Product '" + id + "' updated");
			return productUpdated;
		} else {
			throw new ProductNotFoundException("No products are present with id " + id);
		}
	}

	public void delete(Long id) {
		log.info("Removing product...");
		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
			log.info("Product '" + id + "' deleted");
		} else {
			throw new ProductNotFoundException("No products are present with id " + id);
		}
	}

	public Product findById(Long id) {
		Optional<Product> productResult = productRepository.findById(id);
		log.info("Recovering product...");

		if (productResult.isPresent()) {
			log.info("Product '" + id + "' recovered");
			return productResult.get();
		} else {
			throw new ProductNotFoundException("No products are present with id " + id);
		}
	}

	public Page<Product> findAll(Pageable pageable) {
		log.info("Recovering all products...");
		log.info("All products recovered");
		return productRepository.findAll(pageable);
	}

	public Page<Product> findByCategory(String category, Pageable pageable) {
		log.info("Recovering all products by category : " + category + "...");
		log.info("All products recovered");
		try {
			Category existingCategory = categoryService.findByCategoryName(Categories.valueOf(category));
			return productRepository.findByCategory(existingCategory, pageable);
		} catch (Exception e) {
			throw new CategoryNotFoundException("No categories are present with name " + category);
		}

	}
}
