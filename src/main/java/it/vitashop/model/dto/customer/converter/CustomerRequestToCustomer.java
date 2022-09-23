package it.vitashop.model.dto.customer.converter;

import org.springframework.core.convert.converter.Converter;

import it.vitashop.model.Customer;
import it.vitashop.model.dto.customer.CustomerRequest;

public class CustomerRequestToCustomer implements Converter<CustomerRequest, Customer>{

	@Override
	public Customer convert(CustomerRequest source) {
		Customer target = new Customer();
		target.setUsername(source.getUsername());
		target.setPassword(source.getPassword());
		target.setEmail(source.getEmail());
		target.setName(source.getName());
		target.setLastName(source.getLastName());
		target.setPhoneNumber(source.getPhoneNumber());
		return target;
	}

	
}
