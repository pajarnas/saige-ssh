package com.lyn.service;

import java.util.List;

import com.lyn.model.PTask;
import com.lyn.model.Task;
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
	
	public Task findTask(long id);
	
	public void upadteTask(Task task);
	
	public void delTask(Task task);
	
	public List<Task> getTaskList();

}
