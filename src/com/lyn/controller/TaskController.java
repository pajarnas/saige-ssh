package com.lyn.controller;

import java.util.List;

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

import com.lyn.model.Task;
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
	
	//��TaskService�ӿڣ���ֻ��һ��ʵ����̳нӿ�ʱ������ʹ��@Autowired���ӿ�ʵ����@Serviceע�⼴�ɡ�
	//�����ʱ��ʹ��@Resource(name="taskService2")��ָ�����Ǹ��ӽӿڣ��ӿ�ʵ����@Service("taskService2")ע�⼴�ɡ�
//	@Autowired
//	private TaskService taskService;
//	
	@Resource(name="taskService")
	private TaskService taskService;


	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "addTask")  
    public ModelAndView addTask2(Task task){
		ModelAndView model = new ModelAndView("redirect:/task/taskList.do");
		this.taskService.addTask(task);
	    model.addObject("task",task);
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
	
}
