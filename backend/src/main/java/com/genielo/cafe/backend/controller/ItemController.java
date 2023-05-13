package com.genielo.cafe.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	    
		
}
