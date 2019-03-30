package com.lyn.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.lyn.model.PTask;
import com.lyn.model.Product;
import com.lyn.model.ProgressBar;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.model.enums.Progress;
import com.lyn.model.enums.Stage;
import com.lyn.model.enums.TaskType;
import com.lyn.service.UserService;
import com.lyn.service.ProductService;
import com.lyn.service.TaskService;
/**
 * @author    Yaning Liu
 *
 * @filename  ManagerController.java
 *
 * @date      2019-03-01
 *
 */

@Controller
@RequestMapping("/jsp/manager")
public class ManagerController {
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="taskService")
	private TaskService taskService;
	
	@Resource(name="productService")
	private ProductService productService;
    
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="index.do")
	ModelAndView indexHandler() {
		ModelAndView model = new ModelAndView("forward:index.jsp");
		List<Task> tasks = this.taskService.getTaskList();

	    model.addObject("tasks",tasks);
	    List<ProgressBar> progress_bars = new ArrayList<ProgressBar>();
	    for(Task t:tasks) {
	    	progress_bars.add(new ProgressBar(t));
	    }

	    model.addObject("bars",progress_bars);
	   
		
		
		return model;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="profile.do")
	ModelAndView profileHandler(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("forward:profile.jsp");
		HttpSession session = request.getSession();
		User u = userService.findUser(Long.parseLong(String.valueOf(session.getAttribute("userid"))));
		u.setTask_type(TaskType.生产);
		model.addObject(u);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "delTask")  
    public ModelAndView delTask(Long id){
		
		Task t = this.taskService.findTask(id);
		
		ModelAndView mav = new ModelAndView("forward:ptask_table.do");
		this.taskService.delTask(t);
		return mav;
    }
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "error")  
    public ModelAndView displayError(String message){
		
		
		ModelAndView mav = new ModelAndView("forward:error.jsp");
		mav.addObject("message",message);
		return mav;
    }
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "delUser")  
    public ModelAndView delUser(@SessionAttribute("userid") Long userId,Long id){
		
		if(userId == id) {
			ModelAndView mav = new ModelAndView("forward:error.do?message=无法删除当前用户");
			
			return mav;
		}
		
		User u = this.userService.findUser(id);
		
		ModelAndView mav = new ModelAndView("forward:user_table.do");
		this.userService.delUser(u);
		return mav;
    }
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "updateTask")  
    public ModelAndView updateTask(@ModelAttribute("ptask") PTask ptask,Long id,Long productid){
		
		if(ptask.getName()==null) {
			ModelAndView mav = new ModelAndView("forward:update_task.jsp");
			List<Product> products = this.productService.getProductList();
			mav.addObject("products", products);
			
			mav.addObject("task", this.taskService.findTask(id));
			
			return mav;
		}
		//TODO
		// 需要更高级的权限修改阶段和用户
		Task t = this.taskService.findTask(ptask.getId());
		t.setName(ptask.getName());
		t.setPriority(ptask.getPriority());
		t.setProgress(ptask.getProgress());
		t.setDate(ptask.getDate());
		t.setQuality(ptask.getQuality());
	   
		t.setProduct(this.productService.findById(productid));
		ModelAndView mav = new ModelAndView("forward:ptask_table.do");
		this.taskService.upadteTask(t);
		return mav;
    }
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "updateUser")  
    public ModelAndView updateUser(@ModelAttribute("user") User user,Long id){
		if(user.getName()==null) {
			ModelAndView mav = new ModelAndView("forward:update_user.jsp");
			
		
			
			mav.addObject("user", this.userService.findUser(id));
			
			return mav;
		}
		ModelAndView model = new ModelAndView("redirect:index.do");
		this.userService.upadteUser(user);
        return model;
    }
	
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST },value="insert")
	

    
	ModelAndView insertHandler(@SessionAttribute("userid") Long userId,@ModelAttribute("ptask") PTask ptask, Long productid) {

		
		if(ptask.getName()==null) {
			ModelAndView mav = new ModelAndView("forward:insert_task.jsp");
			List<Product> products = this.productService.getProductList();
			mav.addObject("products", products);
		    
			
			return mav;
		}
		User user = this.userService.findUser(userId);

		Task t = new Task();
		ptask.setProduct(this.productService.findById(productid));
		ptask.setProgress(Progress.未开始);
		ptask.setStage(Stage.待用料);
		ptask.setUser(user);
		
	  
		this.taskService.addPTask(ptask);
	    
		ModelAndView mav = new ModelAndView("forward:ptask_table.do");
		return mav;

	}
	
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "ptask_table")  
    public ModelAndView taskList(){
		ModelAndView model = new ModelAndView("forward:table_task.jsp");
		List<Task> ptasks = taskService.getTaskList();
	    model.addObject("ptasks",ptasks);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "user_table")  
    public ModelAndView userList(){
		ModelAndView model = new ModelAndView("forward:table_user.jsp");
		List<User> users = this.userService.getUserList();
	    model.addObject("users",users);
        return model;
    }
	

	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "taskInsert")  
    public ModelAndView taskInsert(User user){
		ModelAndView model = new ModelAndView("forward:insert_task.jsp");
		List<User> users = userService.getUserList();
	    model.addObject("users",users);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "addUser")  
    public ModelAndView addUser2(User user){
		ModelAndView model = new ModelAndView("redirect:user_table.do");
		this.userService.addUser(user);
	    model.addObject("user",user);
        return model;
    }
	

	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "getUser")  
    public ModelAndView getUser(int id){
		ModelAndView model = new ModelAndView("forward:user_update.jsp");
		model.addObject(this.userService.findUser(id));
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "updateTaskStatus")  
    public ModelAndView updateTask2(int id, int status){
		Task t = this.taskService.findTask(id);
		switch(status) {
//		case 0:t.setProgress("Not Started");break;
//		case 2:t.setProgress("In Progress 50%");break;
//		case 3:t.setProgress("In Progress 80%");break;
//		case 4:t.setProgress("Completed");break;
//		case 1:t.setProgress("In Progress 20%");break;
		}
		ModelAndView model = new ModelAndView("redirect:taskList.do");
		this.taskService.upadteTask(t);
        return model;
    }
}
