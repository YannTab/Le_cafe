package com.genielo.cafe.backend.controller;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.boot.jaxb.mapping.marshall.CacheModeMarshalling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionMessage.ItemsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genielo.cafe.backend.model.*;
import com.genielo.cafe.backend.repository.*;


//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/items/breakfast")
	public String getAllBreakfastItems(Model model){
		
		try {
			List<Item> breakfastItems = new ArrayList<Item>();
			itemRepository.findByType("breakfast").forEach(breakfastItems::add);		
			model.addAttribute("breakfastItems", breakfastItems);
			
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		return "breakfastItems";
	}
	
	@GetMapping("/items")
	public String getAllItems(Model model) {
	    try {
	    	
	    	List<Item> items = new ArrayList<Item>();
			itemRepository.findAll().forEach(items::add);		
			model.addAttribute("allItems", items);
			
	    } catch (Exception e) {
			model.addAttribute("message", e.getMessage());
	
	    }
	    return "allItems";
	    
	//@PostMapping("/commands/{id}/addItem/{id}")
	//public String makeNewCommand(Model model) {
		
	//}
	 
  }
	
	
}
