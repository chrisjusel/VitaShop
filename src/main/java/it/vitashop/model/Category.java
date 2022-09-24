package it.vitashop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Enumerated(EnumType.STRING)
	private CategoryType categoryType;
    
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
    
    public Category(CategoryType categoryType) {
    	this.categoryType = categoryType;
    }
}
