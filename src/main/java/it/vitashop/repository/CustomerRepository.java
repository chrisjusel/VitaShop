package it.vitashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.vitashop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
