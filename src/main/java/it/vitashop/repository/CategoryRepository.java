package it.vitashop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.vitashop.model.Category;
import it.vitashop.model.Categories;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Optional<Category> findByName(Categories categoryName);
}
