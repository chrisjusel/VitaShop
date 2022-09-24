package it.vitashop.model.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductSaveRequest {

	private String name;
	private String descrption;
	private Double price;
	private String imageFile;
	private String category;
}
