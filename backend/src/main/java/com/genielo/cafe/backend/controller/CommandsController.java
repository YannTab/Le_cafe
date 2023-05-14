package com.genielo.cafe.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.genielo.cafe.backend.model.Command;
import com.genielo.cafe.backend.model.Item;
import com.genielo.cafe.backend.repository.CommandRepository;
import com.genielo.cafe.backend.repository.ItemRepository;

import lombok.RequiredArgsConstructor;


@Controller
public class CommandsController {
	

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CommandRepository commandRepository;
	
	@GetMapping("/commands/new")
	public String addCommand(Model model, RedirectAttributes redirectAttributes) {
		
		Command command = new Command();
		command.setCooked(false);
		command.setServed(false);
		commandRepository.save(command);
		
		model.addAttribute("command", command);
		return "CommandItems";
	}
	
	@GetMapping("/commands/{command_id}/items")
	public String getCommandItems(Model model, @PathVariable("command_id") Long command_id, RedirectAttributes redirectAttributes) {
		
		try {
			Command command = commandRepository.findById(command_id).get();
			List<Item> commandItems = command.getItems();
			model.addAttribute("commandItems", commandItems);
			model.addAttribute("command", command);
			
		} catch (Exception e) {
		      model.addAttribute("message", e.getMessage());
		}
		
		return "CommandItems";
	}
	
	@GetMapping("/commands/{command_id}/allItems")
	public String getAllItems(Model model, RedirectAttributes redirectAttributes, @PathVariable("command_id") Long command_id) {
	    try {
	    	
	    	List<Item> items = new ArrayList<Item>();
			itemRepository.findAll().forEach(items::add);		
			model.addAttribute("allItems", items);
			Command command  = commandRepository.findById((long) command_id).get();
			model.addAttribute("command", command);
			redirectAttributes.addAttribute(command_id);
			
	    } catch (Exception e) {
			model.addAttribute("message", e.getMessage());
	
	    }
	    return "allItems";
	}
	
	@GetMapping("/commands/{command_id}/items/breakfast")
	public String getAllBreakfastItems(Model model, RedirectAttributes redirectAttributes, @PathVariable("command_id") Long command_id){
		
		try {
			List<Item> breakfastItems = new ArrayList<Item>();
			itemRepository.findByType("breakfast").forEach(breakfastItems::add);		
			model.addAttribute("breakfastItems", breakfastItems);
			Command command  = commandRepository.findById(command_id).get();
			model.addAttribute("command", command);		
			redirectAttributes.addAttribute(command_id);

			
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
		}
		return "breakfastItems";
	}
	
	@GetMapping
	("/commands/{command_id}/addItem/{item_id}")
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
	
	@GetMapping("/commands/confirm/{command_id}")
	public String confirmCommand(@PathVariable("command_id") Long command_id, Model model, RedirectAttributes redirectAttributes) {
		
		try {
			Command command = commandRepository.findById(command_id).get();
			command.setConfirmed(true);
		} catch (Exception e) {
		      redirectAttributes.addFlashAttribute("message", e.getMessage());

		}
		
		return "redirect:/";
	}
	
	@GetMapping("/commands/toServe")
	public String getCommandToServe(Model model) {
		
		try {
			List<Command> commandsToServe = new ArrayList<Command>();
			commandRepository.findByServed(false).forEach(commandsToServe::add);
			model.addAttribute("commandsToServe", commandsToServe);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());

		}
		return "getCommandsToServe";
		
	}
	
	@GetMapping("/commands/toCook")
	public String getCommandToCook(Model model) {
		
		try {
			List<Command> commandsToCook = new ArrayList<Command>();
			commandRepository.findByCooked(false).forEach(commandsToCook::add);
			model.addAttribute("commandsToCook", commandsToCook);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());

		}
		return "getCommandsToCook";
		
	}
	
	@GetMapping("/commands/serve/{command_id}")
	public String serveCommand(Model model, RedirectAttributes  redirectAttributes, @PathVariable("command_id") Long command_id) {
		
		try {
			Command command = commandRepository.findById(command_id).get();
			command.setServed(true);
		} catch (Exception e) {
		      redirectAttributes.addFlashAttribute("message", e.getMessage());

		}
		
		return "redirect:/commnds/toServe";
	}
	
	@GetMapping("/commands/cook/{command_id}")
	public String cookCommand(Model model, RedirectAttributes  redirectAttributes, @PathVariable("command_id") Long command_id) {
		
		try {
			Command command = commandRepository.findById(command_id).get();
			command.setCooked(true);
		} catch (Exception e) {
		      redirectAttributes.addFlashAttribute("message", e.getMessage());

		}
		
		return "redirect:/commnds/toCook";
	}
	@GetMapping("/commands/{command_id}/items/{item_id}")
	public String getItem(Model model, @PathVariable("item_id") Long item_id, @PathVariable("command_id") Long command_id) {
		Item item = itemRepository.findById(item_id).get();
		model.addAttribute("item", item);
		
		Command command  = commandRepository.findById(command_id).get();
		
		List<Item> commandItems = command.getItems();
		commandItems.add(item);
		command.setItems(commandItems);
		commandRepository.save(command);
		
		model.addAttribute("command", command);
		
		return "chooseItem";
	}
}
//th:object="${command}" th:href="@{'/commands/' + ${command.id} + '/items/breakfast'}"
	
