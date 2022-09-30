package it.vitashop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.exception.CartItemNotFoundException;
import it.vitashop.model.CartItem;
import it.vitashop.repository.CartItemRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	public CartItem save(CartItem cartItem) {
		log.info("Adding new cart item");
		log.info("New cart item '" + cartItem.getId() + "' addedd");
		cartItem.setSubtotal(calculateSubtotal(cartItem));
		return cartItemRepository.save(cartItem);
	}

	public CartItem update(Long id, CartItem cartItem) {
		log.info("Updating cart item...");
		Optional<CartItem> cartItemResult = cartItemRepository.findById(id);

		if (cartItemResult.isPresent()) {
			CartItem cartItemUpdated = new CartItem();
			cartItemUpdated.setQuantity(cartItem.getQuantity());
			cartItemUpdated.setSubtotal(calculateSubtotal(cartItem));
			return cartItemUpdated;
		} else {
			throw new CartItemNotFoundException("No cart items found with id " + id);
		}
	}
	
	public void delete(Long id) {
		log.info("Removing cart item...");
		if(cartItemRepository.findById(id).isPresent()) {
			cartItemRepository.deleteById(id);
			log.info("Cart item " + id + "removed");
		} else {
			throw new CartItemNotFoundException("No cart items found with id " + id);
		}
	}
	
	public CartItem findById(Long id) {
		Optional<CartItem> cartItemResult = cartItemRepository.findById(id);
		log.info("Recovering cart item...");
		
		if(cartItemResult.isPresent()) {
			log.info("Cart item " + id + " recovered");
			return cartItemResult.get();
		} else {
			throw new CartItemNotFoundException("No cart items found with id " + id);
		}
	}
	
	private Double calculateSubtotal(CartItem cartItem) {
		return cartItem.getProduct().getPrice() * cartItem.getQuantity();
	}
}
