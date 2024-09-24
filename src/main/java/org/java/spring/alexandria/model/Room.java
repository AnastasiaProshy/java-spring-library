package org.java.spring.alexandria.model;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "rooms")
public class Room 
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	@Size(min = 3, max = 255)
	@Column (length = 255)
	private String name;

	@NotNull
	private Boolean available;
	
	@NotNull
	@Min(8)
	@Max(100)
	private Integer capacity;
	
	@UpdateTimestamp
	private Timestamp updatedAt;

	@CreationTimestamp
	private Timestamp createdAt;
	
	@Transient // will not be saved in DB
	private DateTimeFormatter dateFromatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the available
	 */
	public Boolean getAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	/**
	 * @return the capacity
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the updatedAt
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
	public String getFormatterCreatedAt()
	{
		if(createdAt != null)
		{
			return createdAt.toLocalDateTime().format(dateFromatter);
		}
		return null;
	}

	public String getFormatterUpdatedAt()
	{
		if(updatedAt != null)
		{
			return updatedAt.toLocalDateTime().format(dateFromatter);
		}
		return null;
		
	}

}
