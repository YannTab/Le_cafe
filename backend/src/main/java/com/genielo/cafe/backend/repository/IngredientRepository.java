package com.genielo.cafe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genielo.cafe.backend.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
