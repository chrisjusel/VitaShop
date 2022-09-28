package it.vitashop.model.dto.cartitem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.vitashop.model.CartItem;
import it.vitashop.model.dto.cartitem.CartItemResponse;
import it.vitashop.model.dto.product.converter.ProductToProductResponse;

@Component
public class CartItemToCartItemResponse implements Converter<CartItem, CartItemResponse>{

	@Autowired
	private ProductToProductResponse conversion;
	
	@Override
	public CartItemResponse convert(CartItem source) {
		CartItemResponse target = new CartItemResponse();
		target.setId(source.getId());
		target.setProduct(conversion.convert(source.getProduct()));
		target.setQuantity(source.getQuantity());
		target.setSubtotal(source.getSubtotal());
		return target;
	}

}
