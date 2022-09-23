package it.vitashop.model.dto.customer.converter;

import org.springframework.core.convert.converter.Converter;

import it.vitashop.model.Customer;
import it.vitashop.model.dto.customer.CustomerResponse;

public class CustomerToCustomerResponse implements Converter<Customer, CustomerResponse>{

	@Override
	public CustomerResponse convert(Customer source) {
		CustomerResponse target = new CustomerResponse();
		target.setId(source.getId());
		target.setLastName(source.getLastName());
		target.setName(source.getName());
		target.setPhoneNUmber(source.getPhoneNumber());
		target.setUsername(source.getUsername());
		return target;
	}

}
