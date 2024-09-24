package org.java.spring.alexandria.controller;

import org.java.spring.alexandria.model.Room;
import org.java.spring.alexandria.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping ("/rooms")
public class RoomController 
{
	private @Autowired RoomRepository repository;
	
	@GetMapping
	public String index(Model model)
	{
		model.addAttribute("rooms", repository.findAll());
		return "/rooms/index";
	}
	
	
	//SHOW
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model)
	{
		model.addAttribute("room", repository.findById(id).get());
		return "/rooms/show";
	}
	
	
	//CREATE + STORE
	
	@GetMapping("/create")
	public String create(Model model)
	{
		model.addAttribute("room",new Room());
		return "/rooms/create";
	}
	
	@PostMapping ("/create")
	public String store(@Valid @ModelAttribute("room") Room formRoom, // retrieve the newly populated form as a Room model
						BindingResult bindingResult, // error handling mechanism
						Model model, // the model to be delivered if necessary
						RedirectAttributes redirectAttributes) // redirect messaging management mechanism
	{
		// check if there are errors in sending the form
		if (bindingResult.hasErrors())
		{
			return "/rooms/create";
		}
		
		repository.save(formRoom);
		
		redirectAttributes.addFlashAttribute("typeAlert", "success");
		redirectAttributes.addFlashAttribute("messageAlert", "The room " + formRoom.getName() + " has been created successfully");
		
		return "redirect:/rooms";
	}
	
	
	//EDIT & UPDATE
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model)
	{
		model.addAttribute("room", repository.findById(id).get());
		return "/rooms/edit";
	}
	
	
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("room") Room formRoom,
						BindingResult bindingResult,
						Model model,
						RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasErrors())
		{
			return "/rooms/edit";
		}
		
		repository.save(formRoom);
		
		redirectAttributes.addFlashAttribute("typeAlert", "warning");
		redirectAttributes.addFlashAttribute("messageAlert", "The room " + formRoom.getName() + " has been updated successfully");

		return "redirect:/rooms";
	}
	
	
	//DELTE
	
	@PostMapping ("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes)
	{
		repository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("typeAlert", "danger");
		redirectAttributes.addFlashAttribute("messageAlert", "The room has been removed successfully");
		
		return "redirect:/rooms";
	}
	
	
	
	
	
	
}
