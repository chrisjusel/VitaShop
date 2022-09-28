package it.vitashop.model.dto.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import it.vitashop.model.dto.cartitem.CartItemResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCartResponse {

	private Long id;
	private Double totalPrice;
	private List<CartItemResponse> cartItems = new ArrayList<>();

}
