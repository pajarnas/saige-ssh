package com.lyn.model;

import javax.annotation.Resource;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import com.lyn.model.Task;
import com.lyn.model.enums.Priority;
import com.lyn.model.enums.ProductType;
import com.lyn.model.enums.Progress;
import com.lyn.model.enums.Role;
import com.lyn.model.enums.Stage;
import com.lyn.service.TaskService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author    Yaning Liu
 *
 * @filename  ProgressBar.java
 *
 * @date      2019-02-18
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class OTaskTableView {
	
	private String name;
	private User other_user;
   
   private Priority priority;
private long id;
    
    private Stage stage;
  
    
  
    
    private int quality;

	
	private User user;
	

	private Product product;


	/**
	 * @param task
	 */
	public OTaskTableView(PTask task,User user) {
		super();
		this.id = task.getId();
	
		this.user = task.getUser();
		this.stage = task.getStage();
		
		this.other_user = user;
		this.name = task.getName();
	    this.product = task.getProduct();
	    this.quality = task.getQuality();
		this.priority = task.getPriority();
		
	}
	
	
	

	
}
