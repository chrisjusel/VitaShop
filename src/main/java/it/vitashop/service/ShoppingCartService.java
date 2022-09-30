package it.vitashop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.exception.ShoppingCartNotFoundException;
import it.vitashop.model.CartItem;
import it.vitashop.model.ShoppingCart;
import it.vitashop.repository.ShoppingCartRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private CartItemService cartItemService;

	public ShoppingCart addCartItem(CartItem cartItem) {
		log.info("Adding cart item " + cartItem.getId() + "...");
		Long idShoppingCart = cartItem.getShoppingCart().getId();
		Optional<ShoppingCart> shoppingCartResult = shoppingCartRepository.findById(idShoppingCart);

		if (shoppingCartResult.isPresent()) {
			ShoppingCart shoppingCart = shoppingCartResult.get();
			shoppingCart.getCartItems().add(cartItem);
			cartItemService.save(cartItem);
			log.info("Cart item " + cartItem.getId() + "addedd");
			return shoppingCart;
		} else {
			throw new ShoppingCartNotFoundException("No shopping carts are found with id " + idShoppingCart);
		}
	}
	
	public void removeCartItem(Long id) {
		cartItemService.delete(id);
	}

//	private ShoppingCart findByCustomer(Long id) {
//		Optional<ShoppingCart> shoppingCartResult = shoppingCartRepository.findById(id);
//		log.info("Recovering shopping cart...");
//
//		if (shoppingCartResult.isPresent()) {
//			log.info("Shopping cart " + id);
//			return shoppingCartResult.get();
//		} else {
//			throw new ShoppingCartNotFoundException("No shopping carts are found with id " + id);
//		}
//	}
}
