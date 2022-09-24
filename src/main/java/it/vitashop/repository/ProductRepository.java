package it.vitashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.vitashop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
