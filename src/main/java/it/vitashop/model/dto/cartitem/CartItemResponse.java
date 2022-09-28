package it.vitashop.model.dto.cartitem;

import it.vitashop.model.dto.product.ProductResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemResponse {

	private Long id;

	private ProductResponse product;

	private int quantity;

	private Double subtotal;

}
