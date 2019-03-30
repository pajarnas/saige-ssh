package com.lyn.service;

import java.util.List;

import com.lyn.model.OTask;
import com.lyn.model.PTask;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.model.enums.Role;
/**
 * @author    Yaning Liu
 *
 * @filename  TaskService.java
 *
 * @date      2019-02-16
 *
 */


public interface TaskService {

	public void addTask(Task task);
	
	public void addPTask(PTask ptask);
	
	public void addOTask(OTask otask);
	
	public Task findTask(long id);
	
	public PTask findPTask(long id);
	
	public void upadteTask(Task task);
	
	public void delTask(Task task);
	
	public List<Task> getTaskList();
	
	public User getRelatedUser( PTask ptask,Role role);
	
	public List<PTask> getPTaskList();
	

}
