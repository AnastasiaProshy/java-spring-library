package org.java.spring.alexandria.controller;

import java.util.List;

import org.java.spring.alexandria.model.Book;
import org.java.spring.alexandria.repo.BookRepository;
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
@RequestMapping("/books")
public class BookController 
{ // repository field con autowired per d.i.
	
	@Autowired
	private BookRepository repo;
	
	@GetMapping() 
	public  String index(Model model) 
	{
		// take the data to deliver to books/index
		List<Book> books = repo.findAll();
		
		// insert them into the model
		model.addAttribute("books", books);
		
		return "/books/index";
	}	
	
	
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model)
	{
		model.addAttribute("book", repo.findById(id).get());
		return "/books/show";
	}


	@GetMapping("/findByTitle/{title}")
	public String findByTitle(@PathVariable("title") String title, Model model)
	{
		model.addAttribute("books", repo.findByTitleContains(title));
		return "/books/index";
	}

	
	
	//CREATE
	
	@GetMapping("/create")
	public String create(Model model)
	{
		model.addAttribute("book", new Book());
		return "/books/create";
	}

	
	
	//STORE
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("book") Book formBook,
						BindingResult bindingResult,
						RedirectAttributes attributes,
						Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "/books/create";
		}
		else
		{
			repo.save(formBook);
			
			attributes.addFlashAttribute("typeAlert", "success");
			attributes.addFlashAttribute("messageAlert", "Great news! '" + formBook.getTitle() + "' has been added successfully");
			//attributes.addFlashAttribute("successAddMessage", "Great news! '" + formBook.getTitle() + "' has been added successfully");
			
			return "redirect:/books";
		}	
	}
	
	
	
	// EDIT()
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model)
	{
		//Book bookToEdit = repo.findById(id).get();
		//model.addAttribute("book", bookToEdit);
		
		model.addAttribute("book", repo.findById(id).get());
		return "/books/edit";
	}
	
	
	
	// UPDATE()
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("book") Book updatedFormBook,
						BindingResult bindingResult,
						RedirectAttributes attributes,
						Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "/books/edit";
		}
		else
		{
			repo.save(updatedFormBook);
			
			attributes.addFlashAttribute("typeAlert", "primary");
			attributes.addFlashAttribute("messageAlert", "Great news! '" + updatedFormBook.getTitle() + "' has been updated successfully");
			//attributes.addFlashAttribute("successUpdateMessage", "Great news! '" + updatedFormBook.getTitle() + "' has been updated successfully");
			
			return"redirect:/books";
		}
	}
	
	
	
	//DELETE
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id,
						RedirectAttributes attributes)
	{
		Book bookToDelete = repo.findById(id).get();
				 
		repo.deleteById(id);
		
		attributes.addFlashAttribute("typeAlert", "danger");
		attributes.addFlashAttribute("messageAlert", "Great news! '" + bookToDelete.getTitle() + "' has been deleted successfully");
		//attributes.addFlashAttribute("successDeleteMessage", "The book '" + bookToDelete.getTitle() + "' has been deleted successfully");
		
		return"redirect:/books";
	}
	
	
	
	
}