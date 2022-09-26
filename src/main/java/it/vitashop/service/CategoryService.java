package it.vitashop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.exception.CategoryNotFoundException;
import it.vitashop.model.Category;
import it.vitashop.model.Categories;
import it.vitashop.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category save (Category category) {
		log.info("Adding new product...");
		log.info("New product '" + category.getName() + "' addedd");
		return categoryRepository.save(category);
	}
	
	public Category findByCategoryName(Categories categoryName) {
		Optional<Category> categoryResult = categoryRepository.findByName(categoryName);
		log.info("Recovering category by name " + categoryName);
		
		if(categoryResult.isPresent()) {
			log.info("Category recovered");
			return categoryResult.get();
		} else {
			throw new CategoryNotFoundException("No categories found with this name");
		}
	}
}
