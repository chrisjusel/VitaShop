package it.vitashop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.controller.CartItemService;
import it.vitashop.exception.ShoppingCartNotFoundException;
import it.vitashop.model.CartItem;
import it.vitashop.model.ShoppingCart;
import it.vitashop.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	public ShoppingCart addCartItem(CartItem cartItem) {
		Long idShoppingCart = cartItem.getShoppingCart().getId();
		Optional<ShoppingCart> shoppingCartResult = shoppingCartRepository.findById(idShoppingCart);
		
		if(shoppingCartResult.isPresent()) {
			ShoppingCart shoppingCart = shoppingCartResult.get();
			shoppingCart.getCartItems().add(cartItem);
			cartItemService.save(cartItem);
			return shoppingCart;
		} else {
			throw new ShoppingCartNotFoundException("No shopping carts are found with id " + idShoppingCart);
		}
	}
}
