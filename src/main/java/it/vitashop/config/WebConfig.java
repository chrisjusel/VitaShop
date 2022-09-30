package it.vitashop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.vitashop.model.dto.cartitem.converter.CartItemRequestToCartItem;
import it.vitashop.model.dto.customer.converter.CustomerRequestToCustomer;
import it.vitashop.model.dto.product.converter.ProductRequestToProduct;
import it.vitashop.model.dto.product.converter.ProductToProductResponse;
import it.vitashop.model.dto.shoppingcart.converter.ShoppingCartToShoppingCartResponse;
import it.vitashop.security.model.converter.UserRequestToUser;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private ProductRequestToProduct productRequestToProduct;
	
	@Autowired
	private CartItemRequestToCartItem cartItemRequestToCartItem;
	
	@Autowired
	private ShoppingCartToShoppingCartResponse shoppingCartToShoppingCartResponse;
	
	@Autowired
	private UserRequestToUser userRequestToUser;
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CustomerRequestToCustomer());
        registry.addConverter(productRequestToProduct);
        registry.addConverter(new ProductToProductResponse());
        registry.addConverter(cartItemRequestToCartItem);
        registry.addConverter(shoppingCartToShoppingCartResponse);
        registry.addConverter(userRequestToUser);
    }
}
