package it.vitashop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.vitashop.exception.CustomerNotFoundException;
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
		log.info("New customer '" + customer.getName() + "' addedd");
		return customerRepository.save(customer);
	}

	public Customer findById(Long id) {
		Optional<Customer> customerResult = customerRepository.findById(id);
		log.info("Revering customer by id " + id);

		if (customerResult.isPresent()) {
			return customerResult.get();
		} else {
			throw new CustomerNotFoundException("No customers are found with id " + id);
		}
	}
}
