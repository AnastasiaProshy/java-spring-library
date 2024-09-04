package org.java.spring.alexandria.repo;

import org.java.spring.alexandria.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> 
{
	// Automatically provides all the necessary functionality, 
	// With the option to add custom methods and features if needed
}
