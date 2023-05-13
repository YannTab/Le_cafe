package com.genielo.cafe.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.genielo.cafe.backend.model.*;
import com.genielo.cafe.backend.repository.*;

@Controller
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
	}
	    
	
	/*public String addItemIngredients(Model model, @PathVariable("item_id") Long item_id) {
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/
	
	@GetMapping("/items/{item_id}")
	public String getItem(Model model, @PathVariable("item_id") Long item_id) {
		Item item = itemRepository.findById(item_id).get();
		model.addAttribute("item", item);
		return "chooseItem";
	}
		
}
