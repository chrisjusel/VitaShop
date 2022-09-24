package it.vitashop.model.dto.product.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.vitashop.model.CategoryType;
import it.vitashop.model.Product;
import it.vitashop.model.dto.product.ProductSaveRequest;
import it.vitashop.service.CategoryService;

@Component
public class ProductSaveRequestToProduct implements Converter<ProductSaveRequest, Product> {

	@Autowired
	private CategoryService categoryService;

	@Override
	public Product convert(ProductSaveRequest source) {
		Product target = new Product();

		target.setCategory(categoryService.findByCategoryType(CategoryType.valueOf(source.getCategory())));
		target.setDescrption(source.getDescrption());
		target.setImageFile(source.getImageFile());
		target.setName(source.getName());
		target.setPrice(source.getPrice());

		return target;
	}

}
