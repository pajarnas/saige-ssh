package com.lyn.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
    private Stage stage;
    
    @Enumerated(EnumType.STRING)
    private TaskType task_type;
    
    private String message;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Product product;
	
    private String priority;

	private TaskType type;
    
	
	/**
	 * @param id
	 * @param name
	 * @param date
	 * @param progress
	 * @param stage
	 * @param task_type
	 * @param message
	 * @param user
	 * @param product
	 * @param priority
	 * @param type
	 */
	public Task(long id, String name, String date, Progress progress, Stage stage, TaskType task_type, String message,
			User user, Product product, String priority, TaskType type) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.progress = progress;
		this.stage = stage;
		this.task_type = task_type;
		this.message = message;
		this.user = user;
		this.product = product;
		this.priority = priority;
		this.type = type;
	}

	
}