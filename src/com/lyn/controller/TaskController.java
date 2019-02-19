package com.lyn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.lyn.model.PTask;
import com.lyn.model.ProgressBar;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.service.PTaskService;
import com.lyn.service.TaskService;

/**
 * @author    Yaning Liu
 *
 * @filename  TaskController.java
 *
 * @date      2019-02-16
 *
 */


@Controller
@RequestMapping("/task")
public class TaskController {
	
	//对TaskService接口，当只有一个实现类继承接口时，可以使用@Autowired，接口实现类@Service注解即可。
	//多个的时候使用@Resource(name="taskService2")来指定调那个子接口，接口实现类@Service("taskService2")注解即可。
//	@Autowired
//	private TaskService taskService;
//	
	@Resource(name="taskService")
	private TaskService taskService;
    
	@Resource(name="pTaskService")
	private PTaskService pTaskService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "addTask")  
    public ModelAndView addTask2(Task task){
		ModelAndView model = new ModelAndView("redirect:/task/taskList.do");
		this.taskService.addTask(task);
	    model.addObject("task",task);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "addPTask")  
    public ModelAndView addTask3(PTask ptask){
		ModelAndView model = new ModelAndView("redirect:/task/pTaskList.do");
		this.pTaskService.addPTask(ptask);
	    model.addObject("ptask",ptask);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "mindex")  
    public ModelAndView managerIndex(User user){
		ModelAndView model = new ModelAndView("forward:/jsp/manager/manager_index.jsp");
		List<Task> tasks = this.taskService.getTaskList();
		
	    model.addObject("tasks",tasks);
	    List<ProgressBar> progress_bars = new ArrayList<ProgressBar>();
	    for(Task t:tasks) {
	    	progress_bars.add(new ProgressBar(t));
	    }
	    
	    model.addObject("bars",progress_bars);
	    model.addObject("bars_len",progress_bars.size());
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "taskList")  
    public ModelAndView taskList(Task task){
		ModelAndView model = new ModelAndView("forward:/jsp/manager/manager_task.jsp");
		List<Task> tasks = taskService.getTaskList();
	    model.addObject("tasks",tasks);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "taskListP")  
    public ModelAndView taskList2(Task task){
		ModelAndView model = new ModelAndView("forward:/jsp/purchaser/purchaser_task.jsp");
		List<Task> tasks = taskService.getTaskList();
	    model.addObject("tasks",tasks);
	    List<PTask> ptasks = pTaskService.getPTaskList();
	    
	    List<Integer>  col = new ArrayList<Integer>();
	    for(PTask pt : ptasks ){
	       col.add(pt.getListid());
	    }
	    col = col.stream().distinct().collect(Collectors.toList()); 
	    model.addObject("listids",col);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "subTaskListP")  
    public ModelAndView taskList3(int listid){
		
		ModelAndView model = new ModelAndView("forward:/jsp/purchaser/purchase_task2.jsp");
		List<PTask> psubtasks = pTaskService.getSubPTaskList(listid);
	    model.addObject("psubtasks",psubtasks);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "pTaskList")  
    public ModelAndView taskList(PTask ptask){
		ModelAndView model = new ModelAndView("forward:/jsp/purchaser/purchase_task.jsp");
		List<PTask> ptasks = pTaskService.getPTaskList();
	    model.addObject("ptasks",ptasks);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "delTask")  
    public ModelAndView delTask(int id){
		ModelAndView model = new ModelAndView("forward:/task/taskList.do");
		this.taskService.delTask(this.taskService.findTask(id));
        return model;
    }

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "getTask")  
    public ModelAndView getTask(int id){
		ModelAndView model = new ModelAndView("forward:/jsp/task/task_update.jsp");
		model.addObject(this.taskService.findTask(id));
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "updateTask")  
    public ModelAndView updateTask(Task task){
		ModelAndView model = new ModelAndView("redirect:/task/taskList.do");
		this.taskService.upadteTask(task);
        return model;
    }
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "updateTaskListid")  
    public ModelAndView updateTask3(int id, int listid){
		Task t = this.taskService.findTask(id);
		t.setPtaskid(listid);
		this.taskService.upadteTask(t);
		ModelAndView model = new ModelAndView("redirect:/task/taskListP.do");
		
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "updateTaskStatus")  
    public ModelAndView updateTask2(int id, int status){
		Task t = this.taskService.findTask(id);
		switch(status) {
		case 0:t.setProgress("Not Started");break;
		case 2:t.setProgress("In Progress 50%");break;
		case 3:t.setProgress("In Progress 80%");break;
		case 4:t.setProgress("Completed");break;
		case 1:t.setProgress("In Progress 20%");break;
		}
		ModelAndView model = new ModelAndView("redirect:/task/taskList.do");
		this.taskService.upadteTask(t);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "updatePTaskStatus")  
    public ModelAndView updatePTask(int id, int status){
		System.out.print(id);
		if(this.pTaskService.findPTask(id)==null)
			System.out.print("222");
		PTask pt = this.pTaskService.findPTask(id);
		switch(status) {
		case 0:pt.setProgress("Not Started");break;
		case 2:pt.setProgress("In Progress 50%");break;
		case 3:pt.setProgress("In Progress 80%");break;
		case 4:pt.setProgress("Completed");break;
		case 1:pt.setProgress("In Progress 20%");break;
		}
		ModelAndView model = new ModelAndView("redirect:/task/pTaskList.do");
		this.pTaskService.upadtePTask(pt);
        return model;
    }
	
}
