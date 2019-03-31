package com.lyn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyn.dao.TaskDao;
import com.lyn.model.OTask;
import com.lyn.model.OTaskTableView;
import com.lyn.model.PTask;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.model.enums.Progress;
import com.lyn.model.enums.Role;
import com.lyn.model.enums.TaskType;
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
	
	public OTask findOTask(long id) {
		return taskDao.findOTask(id);
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

	public List<OTaskTableView> getRelatedUser(User user){
		return this.taskDao.getRelatedUser(user);
	}
	public List<PTask> getPTaskList(){
		return this.taskDao.getPTaskList();
	}
	
	public List<OTask> getOTaskList(){
		return this.taskDao.getOTaskList();
	}
	
	public List<OTask> getOTaskListByUser(User user, PTask ptask){
		if(ptask!=null)
			return this.taskDao.getOTaskListByUser(user, ptask);
		else {
			List<OTask> otasks = new ArrayList<OTask>();
			this.getPTaskList().forEach(x->{
				otasks.addAll(this.taskDao.getOTaskListByUser(user, x));
			});
			return otasks;
		}
			
	}
	
	public List<OTask> checkProgress(User user,PTask ptask,TaskType task_type) {
		List<OTask> otasks_temp = this.getOTaskListByUser(user, ptask);
		List<OTask> otasks = otasks_temp.stream()
				.filter(x->x.getType()==task_type)
				.collect(Collectors.toList());
		otasks_temp.clear();
		otasks.forEach(x->{
			if(x.getProgress()!=Progress.完成) {
				otasks_temp.add(x);
			}
		});
		return otasks_temp;
	}
	
	public List<OTask> getOTaskListByUserAndTaskType(User user, PTask ptask,TaskType task_type){
		List<OTask> otasks_temp = this.getOTaskListByUser(user, ptask);
		List<OTask> otasks = otasks_temp.stream()
				.filter(x->x.getType()==task_type)
				.collect(Collectors.toList());
		return otasks;
	}
	
    public List<OTask> checkStock(User user,PTask ptask,TaskType task_type) {
		
		List<OTask> otasks_temp = this.getOTaskListByUser(user, ptask);
		List<OTask> otasks = otasks_temp.stream()
				.filter(x->x.getType()==task_type)
				.collect(Collectors.toList());
		otasks_temp.clear();
		otasks.forEach(x->{
			if(x.getQuality()>x.getProduct().getQuality()) {
				otasks_temp.add(x);
			}
		});
		return otasks_temp;
	}
}
