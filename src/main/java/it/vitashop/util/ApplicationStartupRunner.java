package it.vitashop.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import it.vitashop.model.Categories;
import it.vitashop.model.Category;
import it.vitashop.model.Product;
import it.vitashop.model.Role;
import it.vitashop.model.Roles;
import it.vitashop.model.User;
import it.vitashop.security.model.UserRequest;
import it.vitashop.security.service.RoleService;
import it.vitashop.security.service.UserService;
import it.vitashop.service.CategoryService;
import it.vitashop.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {

	private static final String PRODUCTS_FILE = "src/main/resources/static/products.csv";

	@Value("${it.VitaShop.populate.database}")
	private boolean populateDatabase;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		initCategories();
		initRoles();
		initUser();
		if (populateDatabase) {
			initProducts();
		}

	}

	private void initCategories() {
		for (Categories categoryType : Categories.values()) {
			Category saveToDb = new Category();
			saveToDb.setName(categoryType);
			categoryService.save(saveToDb);
		}
	}

	private void initProducts() {
		try {
			log.info("Reading all products from CSV");
			FileReader fileReader = new FileReader(PRODUCTS_FILE);
			ProductCsvReader.read(fileReader).stream().map(t -> conversionService.convert(t, Product.class))
					.collect(Collectors.toList()).forEach(t -> productService.save(t));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initRoles() {
		for (Roles role : Roles.values()) {
			Role saveToDb = new Role();
			saveToDb.setRoleName(role);
			roleService.save(saveToDb);
		}
	}
	
	private void initUser() {
		UserRequest userRequest = new UserRequest();
		userRequest.setUsername("test");
		userRequest.setPassword("test");
		userRequest.setEmail("test@test.com");
		User user = conversionService.convert(userRequest, User.class);
		userService.save(user, false);
	}
}
