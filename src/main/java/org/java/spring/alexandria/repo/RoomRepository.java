package org.java.spring.alexandria.repo;

import org.java.spring.alexandria.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> 
{
	// Automatically provides all the necessary functionality, 
	// With the option to add custom methods and features if needed

}
