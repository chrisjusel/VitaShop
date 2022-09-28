package it.vitashop.model.dto.product.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.vitashop.model.Categories;
import it.vitashop.model.Product;
import it.vitashop.model.dto.product.ProductRequest;
import it.vitashop.service.CategoryService;

@Component
public class ProductRequestToProduct implements Converter<ProductRequest, Product> {

	@Autowired
	private CategoryService categoryService;

	@Override
	public Product convert(ProductRequest source) {
		Product target = new Product();

		target.setCategory(categoryService.findByCategoryName(Categories.valueOf(source.getCategory())));
		target.setDescrption(source.getDescrption());
		target.setImageFile(source.getImageFile());
		target.setName(source.getName());
		target.setPrice(source.getPrice());

		return target;
	}

}
