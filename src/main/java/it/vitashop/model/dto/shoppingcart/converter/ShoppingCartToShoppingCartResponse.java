package it.vitashop.model.dto.shoppingcart.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.vitashop.model.ShoppingCart;
import it.vitashop.model.dto.cartitem.CartItemResponse;
import it.vitashop.model.dto.cartitem.converter.CartItemToCartItemResponse;
import it.vitashop.model.dto.shoppingcart.ShoppingCartResponse;

@Component
public class ShoppingCartToShoppingCartResponse implements Converter<ShoppingCart, ShoppingCartResponse> {

	@Autowired
	private CartItemToCartItemResponse conversion;

	@Override
	public ShoppingCartResponse convert(ShoppingCart source) {
		ShoppingCartResponse target = new ShoppingCartResponse();
		target.setId(source.getId());
		List<CartItemResponse> cardItemsResponse = source.getCartItems().stream()
				.map(t -> conversion.convert(t)).collect(Collectors.toList());
		target.setCartItems(cardItemsResponse);
		target.setTotalPrice(source.getTotalPrice());
		return target;
	}

}
