package it.vitashop.model.dto.product;

import it.vitashop.model.Categories;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {

	@Getter
	@Setter
	@NoArgsConstructor
	public class CategoryResponse {
		private Long id;
		private Categories name;
	}

	private Long id;
	private String name;
	private String descrption;
	private Double price;
	private String imageFile;

	private CategoryResponse category = new CategoryResponse();
}
