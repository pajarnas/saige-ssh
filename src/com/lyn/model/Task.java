package com.lyn.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lyn.model.enums.Priority;
import com.lyn.model.enums.Progress;

import com.lyn.model.enums.TaskType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * @author    Yaning Liu
 *
 * @filename  Task.java
 *
 * @date      2019-02-16
 *
 */


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="_Task")
@DiscriminatorColumn(name = "task_type")

@Getter @Setter @NoArgsConstructor 

public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false,unique=true)
	private long id;
	
	private String name;
    
    private String date;
    
    @Enumerated(EnumType.STRING)
    private Progress progress;
    
   
    
    @Enumerated(EnumType.STRING)
    private TaskType type;
  
    @Enumerated(EnumType.STRING)
    private Priority priority;

    private int quality;

	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	
	private User user;
	
	@OneToOne
	private Product product;





	/**
	 * @param id
	 * @param name
	 * @param date
	 * @param progress
	 * @param stage
	 * @param task_type
	 * @param priority
	 * @param message
	 * @param quality
	 * @param user
	 * @param product
	 * @param type
	 */
	public Task(long id, String name, String date, Progress progress, 
			Priority priority, int quality, User user, Product product,TaskType type) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.progress = progress;
		
	    this.type = type;
		this.priority = priority;
	
		this.quality = quality;
		this.user = user;
		this.product = product;
		
	}
    

	

}