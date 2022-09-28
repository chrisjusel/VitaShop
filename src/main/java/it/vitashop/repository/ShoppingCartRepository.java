package it.vitashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.vitashop.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

}
