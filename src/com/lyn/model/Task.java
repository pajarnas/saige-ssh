package com.lyn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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
	
	@ManyToOne
	private User user;

    private String priority;

	private TaskType type;
    

	/**
	 * 
	 */
	public Task() {
	
	}


	/**
	 * @param id
	 * @param name
	 * @param date
	 * @param progress
	 * @param stage
	 * @param type
	 * @param message
	 * @param user
	 * @param priority
	 */
	public Task(long id, String name, String date, Progress progress, Stage stage, TaskType type, String message,
			User user, String priority) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.progress = progress;
		this.stage = stage;
		this.type = type;
		this.message = message;
		this.user = user;
		this.priority = priority;
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}


	/**
	 * @return the progress
	 */
	public Progress getProgress() {
		return progress;
	}


	/**
	 * @param progress the progress to set
	 */
	public void setProgress(Progress progress) {
		this.progress = progress;
	}


	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}


	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}


	/**
	 * @return the type
	 */
	public TaskType getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(TaskType type) {
		this.type = type;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}


	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
