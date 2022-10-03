package it.vitashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vitashop.model.CartItem;
import it.vitashop.model.ShoppingCart;
import it.vitashop.model.dto.cartitem.CartItemRequest;
import it.vitashop.model.dto.shoppingcart.ShoppingCartResponse;
import it.vitashop.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customers/cart")
@Slf4j
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ConversionService conversionService;

	@PostMapping
	@PreAuthorize(value = "hasRole('ADMIN')"
	        + "or authentication.principal.equals(#request.customer) ")
	public ResponseEntity<ShoppingCartResponse> addCartItem(@RequestBody CartItemRequest request) {
		CartItem cartItem = conversionService.convert(request, CartItem.class);
		ShoppingCart shoppingCart = shoppingCartService.addCartItem(cartItem);
		ShoppingCartResponse response = conversionService.convert(shoppingCart, ShoppingCartResponse.class);
		return new ResponseEntity<ShoppingCartResponse>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeCartItem(@PathVariable Long id) {
		log.info("New '" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());
		shoppingCartService.removeCartItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
