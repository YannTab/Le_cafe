package com.genielo.cafe.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genielo.cafe.backend.repository.ItemRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class IngredientsController {

	
	@Autowired
	ItemRepository ingredientRepository;
	
}
