package it.vitashop;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.vitashop.model.dto.customer.converter.CustomerRequestToCustomer;
import it.vitashop.model.dto.product.converter.ProductSaveRequestToProduct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CustomerRequestToCustomer());
        registry.addConverter(new ProductSaveRequestToProduct());
    }
}
