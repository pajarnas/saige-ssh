package com.lyn.model;

import com.lyn.model.Task;
import com.lyn.model.enums.Priority;
import com.lyn.model.enums.ProductType;
import com.lyn.model.enums.Progress;
import com.lyn.model.enums.TaskType;

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
public class ProgressBar {
	private String width;
	private String color;
	private String name;
   private String user_name;
   private String date;
   private Priority priority;
	private long task_id;
	private TaskType type;

	/**
	 * @param width
	 * @param color
	 * @param name
	 * @param task_id
	 */
	public ProgressBar(Task task) {
		super();
		this.width = this.getWidth2(task.getProgress());
		this.color = this.getColor2(task.getPriority());
		this.name = task.getName();
		this.task_id = task.getId();
		this.date = task.getDate();
		this.priority = task.getPriority();
		this.user_name = task.getUser().getName();
		this.type=task.getType();
	}
	
	
	

	public String getWidth2(Progress value){
		
		switch(value) {
		case 前期完成:return "20";
		case 未开始:return "0";
		case 中期完成:return "50";
		case 即将完成:return "80";
		case 完成:return "100";
		}
		return "0";
	}
	

	public String getColor2(Priority value) {
		switch(value) {
		case high:return "warning";
		case low:return "success";
		case medium:return "info";
		case urgent:return "danger";
		}
		return "0";
	}
	
	
	
}
