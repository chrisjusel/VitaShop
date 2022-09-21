package it.vitashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.model.Customer;
import it.vitashop.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer save(Customer customer) {
		log.info("Adding new customer...");
		log.info("New customer '" + customer.getUsername() + "' addedd");
		return customerRepository.save(customer);
	}
}
