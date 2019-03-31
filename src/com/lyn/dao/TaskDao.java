package com.lyn.dao;

import java.util.List;

import com.lyn.model.OTask;
import com.lyn.model.OTaskTableView;
import com.lyn.model.PTask;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.model.enums.Role;



/**
 * @author    Yaning Liu
 *
 * @filename  TaskDao.java
 *
 * @date      2019-02-16
 *
 */


public interface TaskDao {

	public void addTask(Task task);
	
	public void addPTask(PTask ptask);
	
	public void addOTask(OTask otask);
	
	public PTask findPTask(long id);
	
	public Task findTask(long id);
	
	public OTask findOTask(long id);
	
	public void upadteTask(Task task);
	
	public void delTask(Task task);
	
	public List<OTaskTableView> getRelatedUser( User user);
	
	public List<Task> getTaskList();
	
	public List<PTask> getPTaskList();
	
	public List<OTask> getOTaskList();
	
	public List<OTask> getOTaskListByUser(User user, PTask ptask);

}
