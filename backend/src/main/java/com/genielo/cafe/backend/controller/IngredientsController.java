package com.genielo.cafe.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genielo.cafe.backend.model.Ingredient;
import com.genielo.cafe.backend.model.Item;
import com.genielo.cafe.backend.repository.IngredientRepository;
import com.genielo.cafe.backend.repository.ItemRepository;

@Controller
public class IngredientsController {

	
	@Autowired
	IngredientRepository ingredientRepository;
	
	public String getIngredients(Model model) {
			
			List<Ingredient> ingredients = ingredientRepository.findAll();
			model.addAttribute("ingredients", ingredients);
			return "adminIngredients" ;
					
		
		}
	
	@GetMapping("/ingredients/new")
	public String addItems(Model model) {
		
		Ingredient ingredient = new Ingredient();
		
		model.addAttribute("ingredient", ingredient);
		return "ingredientForm";
	}
	
	@PostMapping("/ingredients/save")
	public String saveItem(Ingredient ingredient, RedirectAttributes redirectAttributes) {
		
		try {
			ingredientRepository.save(ingredient);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		
		return "redirect:/ingredients/all";
	}
}
