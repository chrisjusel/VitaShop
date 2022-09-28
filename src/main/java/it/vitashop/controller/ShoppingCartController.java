package it.vitashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vitashop.model.CartItem;
import it.vitashop.model.ShoppingCart;
import it.vitashop.model.dto.cartitem.CartItemRequest;
import it.vitashop.model.dto.shoppingcart.ShoppingCartResponse;
import it.vitashop.service.ShoppingCartService;

@RestController
@RequestMapping("/api/customers/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ConversionService conversionService;

	@PostMapping
	public ResponseEntity<ShoppingCartResponse> addCartItem(@RequestBody CartItemRequest request) {
		CartItem cartItem = conversionService.convert(request, CartItem.class);
		ShoppingCart shoppingCart = shoppingCartService.addCartItem(cartItem);
		ShoppingCartResponse response = conversionService.convert(shoppingCart, ShoppingCartResponse.class);
		return new ResponseEntity<ShoppingCartResponse>(response, HttpStatus.CREATED);
	}

}
