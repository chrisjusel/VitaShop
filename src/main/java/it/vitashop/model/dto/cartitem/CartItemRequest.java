package it.vitashop.model.dto.cartitem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemRequest {

	private Long customer;
	private Long product;
	private int quantity;
}
