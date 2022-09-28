package it.vitashop.model.dto.cartitem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import it.vitashop.model.CartItem;
import it.vitashop.model.dto.cartitem.CartItemRequest;
import it.vitashop.service.CustomerService;
import it.vitashop.service.ProductService;

@Component
public class CartItemRequestToCartItem implements Converter<CartItemRequest, CartItem>{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public CartItem convert(CartItemRequest source) {
		CartItem target = new CartItem();
		target.setProduct(productService.findById(source.getProduct()));
		target.setQuantity(source.getQuantity());
		target.setShoppingCart(customerService.findById(source.getCustomer()).getShoppingCart());
		return target;
	}

}
