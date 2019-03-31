package com.lyn.service;

import java.util.List;

import com.lyn.model.OTask;

import com.lyn.model.PTask;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.model.enums.Role;
import com.lyn.model.enums.TaskType;
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
	
	public OTask findOTask(long id);
	
	public void upadteTask(Task task);
	
	public void delTask(Task task);
	
	public List<Task> getTaskList();
	
	public List<Task> getTaskListByUser(User user);
	
	public List<Task> getTasksWithUser(User user);
	
	
	
	public List<PTask> getPTaskList();
	
	public List<PTask> getPTaskListByUser(User user);
	 
	public List<OTask> getOTaskList();

	public List<OTask> getOTaskListByUser(User user, PTask ptask);
	
	public List<OTask> getOTaskListByUser(User user);
	
	 public List<OTask> getOTaskListByPTask( PTask ptask);
	
	public List<OTask> getOTaskListByUserAndTaskType(User user, PTask ptask,TaskType task_type);
	
	public List<OTask> getOTaskListByUserAndTaskType(User user, TaskType task_type);
	
	public List<OTask> checkProgress(User user,PTask ptask,TaskType task_type);
	
	 public List<OTask> checkStock(User user,PTask ptask,TaskType task_type);
	 
	 public List<OTask> checkAllStocked(User user,PTask ptask,TaskType task_type);
	 
	 public void resetAllStocked(OTask otask);
	
	
}
