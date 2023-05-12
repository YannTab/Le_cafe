package com.genielo.cafe.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genielo.cafe.backend.model.*;
import com.genielo.cafe.backend.repository.*;

@Controller
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;
	private CommandRepository commandRepository;
	
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
	    
	@PostMapping("/commands/{command_id}/addItem/{item_id}")
	public String makeNewCommand(Model model, @PathVariable("command_id") Long command_id, @PathVariable("item_id") Long item_id, RedirectAttributes redirectAttributes) {
		try {
			
			Item item = itemRepository.findById(item_id).get();
			List<Item> commandItems = new ArrayList<Item>();
			commandItems.add(item);
			Command command = commandRepository.findById(command_id).get();
			command.setItems(commandItems);
			commandRepository.save(command);
			
		} catch (Exception e) {
			
			redirectAttributes.addAttribute("message", e.getMessage());
		}
		return "redirect:/items";
	
	 
  }
	
	
}
