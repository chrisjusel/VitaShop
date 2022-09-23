package it.vitashop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vitashop.model.Customer;
import it.vitashop.model.dto.customer.CustomerRequest;
import it.vitashop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ConversionService conversionService;
	
	@PostMapping
	public ResponseEntity<Customer> save(@RequestBody CustomerRequest request){
		log.info("New '" + new Object() {}.getClass().getEnclosingMethod().getName() + "' request to " + this.getClass().getName());
		Customer response = conversionService.convert(request, Customer.class);
		return new ResponseEntity<Customer>(customerService.save(response), HttpStatus.CREATED);
	}
}
