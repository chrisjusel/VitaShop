package it.vitashop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.vitashop.model.dto.customer.converter.CustomerRequestToCustomer;
import it.vitashop.model.dto.product.converter.ProductRequestToProduct;
import it.vitashop.model.dto.product.converter.ProductToProductResponse;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private ProductRequestToProduct productRequestToProduct;
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CustomerRequestToCustomer());
        registry.addConverter(productRequestToProduct);
        registry.addConverter(new ProductToProductResponse());
    }
}
