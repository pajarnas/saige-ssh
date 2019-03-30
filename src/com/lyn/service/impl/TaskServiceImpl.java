package com.lyn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyn.dao.TaskDao;
import com.lyn.model.OTask;
import com.lyn.model.PTask;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.model.enums.Role;
import com.lyn.service.TaskService;

/**
 * @author    Yaning Liu
 *
 * @filename  TaskServiceImpl.java
 *
 * @date      2019-02-16
 *
 */

@Service("taskService")
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskDao taskDao;
	
	public void addTask(Task task) {
		System.out.println("**********TaskService.addTask***********");
		taskDao.addTask(task);
	}

	public Task findTask(long id) {
		return taskDao.findTask(id);
	}
	
	public PTask findPTask(long id) {
		return taskDao.findPTask(id);
	}

	public void upadteTask(Task task) {
		taskDao.upadteTask(task);		
	}
    
	public void delTask(Task task) {
		taskDao.delTask(task);
	}
	
	public List<Task> getTaskList(){
		return this.taskDao.getTaskList();
	}



	public void addPTask(PTask ptask) {
		this.taskDao.addPTask(ptask);
		
	}
	public void addOTask(OTask otask) {
		this.taskDao.addOTask(otask);
	}

	public User getRelatedUser(PTask ptask, Role role) {
		return this.taskDao.getRelatedUser(ptask, role);
	}
	
	public List<PTask> getPTaskList(){
		return this.taskDao.getPTaskList();
	}

}
