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

import com.lyn.model.OTask;
import com.lyn.model.OTaskTableView;
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
 * @date      2019-03-29
 *
 */

@Controller
@RequestMapping("/jsp/purchaser")
public class PurchaserController {
	
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
	ModelAndView profileHandler(@SessionAttribute("userid") Long userId) {
		ModelAndView model = new ModelAndView("forward:profile.jsp");
		User u = userService.findUser(userId);
		
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
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "takeTask")  
    public ModelAndView takeTask(Long id){
		
		PTask t = this.taskService.findPTask(id);
		
		ModelAndView mav = new ModelAndView("forward:ptask_table.do");
		this.taskService.delTask(t);
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
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST },value="insert")
	

    
	ModelAndView insertHandler(@SessionAttribute("userid") Long userId,@ModelAttribute("otask") OTask otask, Long ptaskid, Long productid) {

		
		if(otask.getName()==null) {
			ModelAndView mav = new ModelAndView("forward:insert_task.jsp");
			List<Product> products = this.productService.getProductList();
			mav.addObject("ptaskid", ptaskid);
		    mav.addObject("products",products);
			
			return mav;
		}
		User user = this.userService.findUser(userId);
        PTask ptask = this.taskService.findPTask(ptaskid);
        
		Task t = new Task();
		otask.setProduct(this.productService.findById(productid));
		otask.setProgress(Progress.未开始);
		otask.setStage(Stage.待用料);
		otask.setUser(user);
		otask.setPtask(ptask);
		this.taskService.addOTask(otask);
	    
		ModelAndView mav = new ModelAndView("forward:ptask_table.do");
		return mav;

	

	}
	
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "ptask_table")  
    public ModelAndView taskList(@SessionAttribute("userid") Long userId){
		ModelAndView model = new ModelAndView("forward:table_task.jsp");
		List<PTask> ptasks = taskService.getPTaskList();
		  List<OTaskTableView> ptasklist = new ArrayList<OTaskTableView>();
		    for(PTask t:ptasks) {
		    	ptasklist.add(new OTaskTableView(t,this.taskService.getRelatedUser(t, this.userService.findUser(userId).getRole())));
		    }

		    model.addObject("ptasklist",ptasklist);
	  
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
