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

import com.genielo.cafe.backend.model.Command;
import com.genielo.cafe.backend.model.Item;
import com.genielo.cafe.backend.repository.CommandRepository;
import com.genielo.cafe.backend.repository.ItemRepository;


@Controller
public class CommandsController {
	

	@Autowired
	ItemRepository itemRepository;
	CommandRepository commandRepository;
	
	@GetMapping("/commands/new")
	public String addCommand(Model model, RedirectAttributes redirectAttributes) {
		
		Command command = new Command();
		command.setCooked(false);
		command.setServed(false);
		
		model.addAttribute("command", command);
		return "CommandItems";
	}
	
	@GetMapping("/commands/{command_id}/items")
	public String getCommandItems(Model model, @PathVariable("command_id") Long command_id, RedirectAttributes redirectAttributes) {
		
		try {
			Command command = commandRepository.findById(command_id).get();
			List<Item> commandItems = command.getItems();
			model.addAttribute("commandItems", commandItems);
			
		} catch (Exception e) {
		      model.addAttribute("message", e.getMessage());
		}
		
		return "CommandItems";
	}
	
	@PostMapping("/commands/{command_id}/addItem/{item_id}") 
	public String addCommandItem(Model model, @PathVariable("command_id") Long command_id, @PathVariable("item_id") Long item_id, RedirectAttributes redirectAttributes) {
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
	
	@PostMapping("/commands/confirm/{command_id}")
	public String confirmCommand(@PathVariable("command_id") Long command_id, Model model, RedirectAttributes redirectAttributes) {
		
		try {
			Command command = commandRepository.findById(command_id).get();
			command.setConfirmed(true);
		} catch (Exception e) {
		      redirectAttributes.addFlashAttribute("message", e.getMessage());

		}
		
		return "redirect:/";
	}
}

	
