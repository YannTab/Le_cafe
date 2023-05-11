package com.genielo.cafe.backend.controller;

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
	public String getAllBreakfast(Model model){
		
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
	  public ResponseEntity<Item> createItem(@RequestBody Item item) {
	    try {
	      Item _item = itemRepository
	          .save(new Item(item.getName(), item.getCost(), item.getDescription()));
	      return new ResponseEntity<>(_item, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
  }
	
	
}
