package it.vitashop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import it.vitashop.model.Category;
import it.vitashop.model.Categories;
import it.vitashop.model.Product;
import it.vitashop.model.dto.product.ProductRequest;
import it.vitashop.model.dto.product.converter.ProductSaveRequestToProduct;
import it.vitashop.service.CategoryService;
import it.vitashop.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ApplicationStartupRunner implements CommandLineRunner {

	@Value("${populate.database}")
	private boolean populateDatabase;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private ProductSaveRequestToProduct conv;

	@Override
	public void run(String... args) throws Exception {
		if (populateDatabase) {
			initCategories();

			ProductRequest prod = new ProductRequest();

			prod.setCategory("ELECTRONICS");
			prod.setDescrption("Il miglior rasoio di napoli");
			prod.setImageFile("Pantalone");
			prod.setName("Rasoio Napoletano");
			prod.setPrice(15.99);

			Product save = conv.convert(prod);

			productService.save(save);
		}

	}

	private void initCategories() {
		for (Categories categoryType : Categories.values()) {
			Category saveToDb = new Category();
			saveToDb.setName(categoryType);
			categoryService.save(saveToDb);
		}
	}

}
