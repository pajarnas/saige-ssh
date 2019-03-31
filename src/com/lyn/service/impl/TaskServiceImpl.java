package com.lyn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyn.dao.TaskDao;
import com.lyn.model.OTask;

import com.lyn.model.PTask;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.model.enums.Progress;
import com.lyn.model.enums.Role;
import com.lyn.model.enums.Stage;
import com.lyn.model.enums.TaskType;
import com.lyn.service.ProductService;
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
	
	@Resource(name="productService")
	private ProductService productService;
	
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
	
	public List<Task> getTaskListByUser(User user){
		return this.taskDao.getTaskListByUser(user);
	}


	public void addPTask(PTask ptask) {
		this.taskDao.addPTask(ptask);
		
	}
	public void addOTask(OTask otask) {
		this.taskDao.addOTask(otask);
	}


	public List<PTask> getPTaskList(){
		return this.taskDao.getPTaskList();
	}
	
	public List<PTask> getPTaskListByUser(User user){
		return this.taskDao.getPTaskListByUser(user);
	}
	
	public List<OTask> getOTaskList(){
		return this.taskDao.getOTaskList();
	}
	
	public List<OTask> getOTaskListByUser(User user, PTask ptask){
		return this.taskDao.getOTaskListByUser(user, ptask);

	}
	
	
	public List<OTask> getOTaskListByUser(User user) {
		List<OTask> otasks = new ArrayList<OTask>();
		this.getPTaskList().forEach(x->{
			otasks.addAll(this.taskDao.getOTaskListByUser(user, x));
		});
		return otasks;
	}
	public List<OTask> getOTaskListByPTask( PTask ptask){
		return this.taskDao.getOTaskListByPTask(ptask);
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
	
	public List<OTask> getOTaskListByUserAndTaskType(User user, TaskType task_type){
		List<OTask> otasks_temp = this.getOTaskListByUser(user);
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

	
	
	public List<OTask> checkAllStocked(User user, PTask ptask, TaskType task_type) {
		List<OTask> otasks_temp = this.getOTaskListByUser(user, ptask);
		List<OTask> otasks = otasks_temp.stream()
				.filter(x->x.getType()==task_type)
				.collect(Collectors.toList());
		otasks_temp.clear();
		otasks.forEach(x->{
			if(!x.isStocked()) {
				otasks_temp.add(x);
			}
		});
		return otasks_temp;
		
	}
	
	@SuppressWarnings("incomplete-switch")
	public void resetAllStocked(OTask otask) {
		
		this.getOTaskListByUserAndTaskType(otask.getUser(), otask.getPtask(), otask.getType()).forEach(x->{
	if(x.isStocked()) {
				switch(x.getType()) {
				case 用料:x.getProduct().setQuality(x.getProduct().getQuality()+x.getQuality());x.setStocked(false);break;
				case 加工:x.getProduct().setQuality(x.getProduct().getQuality()-x.getQuality());x.setStocked(false);break;
				case 销售:x.getProduct().setQuality(x.getProduct().getQuality()+x.getQuality());x.setStocked(false);break;
				case 采购:x.getProduct().setQuality(x.getProduct().getQuality()-x.getQuality());x.setStocked(false);break;
				}
				this.productService.upadteProduct(x.getProduct());
				this.upadteTask(x);
			}
		});
	}
	
	public List<Task> getTasksWithUser(User user){
		return this.taskDao.getTasksWithUser(user);
	}

	
}
