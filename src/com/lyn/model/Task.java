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

<<<<<<< HEAD
import com.lyn.model.enums.Priority;
import com.lyn.model.enums.Progress;
import com.lyn.model.enums.Stage;
import com.lyn.model.enums.TaskType;

=======
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
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
    
<<<<<<< HEAD
    @Enumerated(EnumType.STRING)
    private Priority priority;
=======
    private String message;
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
	
<<<<<<< HEAD
    private int quality;
    
=======
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
	@OneToOne
	private User user;
	
	@OneToOne
	private Product product;
<<<<<<< HEAD

=======
	
    private String priority;

	private TaskType type;
    
	
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
	/**
	 * @param id
	 * @param name
	 * @param date
	 * @param progress
	 * @param stage
	 * @param task_type
<<<<<<< HEAD
=======
	 * @param message
	 * @param user
	 * @param product
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
	 * @param priority
<<<<<<< HEAD
	 * @param message
	 * @param quality
	 * @param user
	 * @param product
=======
	 * @param type
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
	 */
<<<<<<< HEAD
	public Task(long id, String name, String date, Progress progress, Stage stage, TaskType task_type,
			Priority priority, int quality, User user, Product product) {
=======
	public Task(long id, String name, String date, Progress progress, Stage stage, TaskType task_type, String message,
			User user, Product product, String priority, TaskType type) {
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.progress = progress;
		this.stage = stage;
		this.task_type = task_type;
<<<<<<< HEAD
=======
		this.message = message;
		this.user = user;
		this.product = product;
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
		this.priority = priority;
<<<<<<< HEAD
		this.quality = quality;
		this.user = user;
		this.product = product;
=======
		this.type = type;
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
	}
<<<<<<< HEAD
=======

>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
	
<<<<<<< HEAD
   
    

	
=======
>>>>>>> branch 'master3' of https://github.com/pajarnas/saige-ssh.git
}