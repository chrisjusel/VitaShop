package it.vitashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import it.vitashop.model.Customer;
import it.vitashop.model.dto.annotation.DTO;
import it.vitashop.model.dto.customer.CustomerRequest;
import it.vitashop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping
	public ResponseEntity<Customer> save(@DTO(CustomerRequest.class) Customer customer) {
		return new ResponseEntity<Customer>(customerService.save(customer), HttpStatus.CREATED);
	}
}
