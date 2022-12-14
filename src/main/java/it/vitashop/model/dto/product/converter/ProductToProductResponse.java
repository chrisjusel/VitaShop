package it.vitashop.model.dto.product.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.vitashop.model.Product;
import it.vitashop.model.dto.product.ProductResponse;

@Component
public class ProductToProductResponse implements Converter<Product, ProductResponse> {

	@Override
	public ProductResponse convert(Product source) {
		ProductResponse target = new ProductResponse();
		target.setId(source.getId());
		target.setName(source.getName());
		target.setPrice(source.getPrice());
		target.setImageFile(source.getImageFile());
		target.setDescrption(source.getDescrption());
		target.getCategory().setId(source.getCategory().getId());
		target.getCategory().setName(source.getCategory().getName());
		return target;
	}
}
