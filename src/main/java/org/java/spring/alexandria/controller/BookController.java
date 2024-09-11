package org.java.spring.alexandria.controller;

import java.util.List;

import org.java.spring.alexandria.model.Book;
import org.java.spring.alexandria.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController 
{
	// repository field con autowired per d.i.
	
	
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

	
	
	
	
	
}