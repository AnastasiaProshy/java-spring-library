package org.java.spring.alexandria.repo;

import java.util.List;

import org.java.spring.alexandria.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> 
{
	// Automatically provides all the necessary functionality, 
	// With the option to add custom methods and features if needed
	
	
	public List<Book> findByTitle(String title);
	
	public List<Book> findByTitleContains(String title);
}
