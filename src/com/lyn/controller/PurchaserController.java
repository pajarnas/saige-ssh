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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.lyn.model.OTask;
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
	ModelAndView indexHandler(@SessionAttribute("userid")Long userId) {
		ModelAndView model = new ModelAndView("forward:index.jsp");
		List<Task> tasks = this.taskService.getTasksWithUser(this.userService.findUser(userId));

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
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "updateProfile")  
    public ModelAndView updateProfile(@ModelAttribute("user") User user,Long id){
	
		ModelAndView model = new ModelAndView("redirect:/lyn-ssh/jsp/user/logout.do");
		this.userService.upadteUser(user);
        return model;
    }
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "delTask")  
    public ModelAndView delTask(Long id){
		
		Task t = this.taskService.findTask(id);
		
		ModelAndView mav = new ModelAndView("forward:material_table.do");
		this.taskService.delTask(t);
		return mav;
    }
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "delTask2")  
    public ModelAndView delTask2(Long id){
		
		Task t = this.taskService.findTask(id);
		
		ModelAndView mav = new ModelAndView("forward:pur_table.do");
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
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "submit")  
    public ModelAndView submit(@SessionAttribute("userid")Long userId,Long ptaskid){
		ModelAndView mav = new ModelAndView("forward:succ.jsp");
		User user = this.userService.findUser(userId);
		PTask ptask = this.taskService.findPTask(ptaskid);
		if(ptask.getStage()==Stage.待用料出库) {
			mav.setViewName("forward:error_duplicate.jsp");
		}
		List<OTask> otasks_material =  this.taskService.checkStock(user,ptask,TaskType.用料);
		
		if(!otasks_material.isEmpty()) {
			mav.setViewName("forward:error.jsp");
			
			mav.addObject("otasks", otasks_material);
			return mav;
		}
		ptask.setStage(Stage.待用料出库);
		mav.addObject("message", Stage.待用料出库);
		ptask.setProgress(Progress.前期完成);
		this.taskService.upadteTask(ptask);
		
		return mav;
    }

		
	
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "submitForPur")  
    public ModelAndView submitForPur(@SessionAttribute("userid")Long userId, Long ptaskid){
		ModelAndView mav = new ModelAndView("forward:succ.jsp");
		User user = this.userService.findUser(userId);
		PTask ptask = this.taskService.findPTask(ptaskid);
		if(ptask.getStage()==Stage.待采购入库) {
			mav.setViewName("forward:error_duplicate.jsp");
		}
		List<OTask> otasks =  this.taskService.checkProgress(user,ptask,TaskType.采购);

		if(!otasks.isEmpty()) {
			mav .setViewName("forward:error_pur.jsp");
			mav.addObject("otasks", otasks);
			return mav;
		}
		this.taskService.getOTaskListByUserAndTaskType(user, TaskType.用料).forEach(x->{
			x.setProgress(Progress.即将完成);
			this.taskService.upadteTask(x);
		});
		ptask.setStage(Stage.待采购入库);
		mav.addObject("message", Stage.待采购入库);
		ptask.setProgress(Progress.前期完成);
		this.taskService.upadteTask(ptask);
		
		return mav;
		
    }
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST },value="insert")
	ModelAndView insertHandler(@SessionAttribute("userid") Long userId,@ModelAttribute("otask") OTask otask, Long ptaskid, Long productid, String type) {
		ModelAndView mav = new ModelAndView("forward:insert_task.jsp");
		
		if(otask.getName()==null) {
			
			List<Product> products = this.productService.getProductList();
			mav.addObject("ptaskid", ptaskid);
		    mav.addObject("products",products);
		
			return mav;
		}
		User user = this.userService.findUser(userId);
        PTask ptask = this.taskService.findPTask(ptaskid);
        ptask.setPur_user(user);
       
        if(otask.getType()==TaskType.采购) {
        	ptask.setStage(Stage.待采购);
            this.taskService.upadteTask(ptask);
    		otask.setProduct(this.productService.findById(productid));
    		otask.setProgress(Progress.未开始);
    		
    		otask.setUser(user);
    		otask.setPtask(ptask);
    		this.taskService.addOTask(otask);
    	    this.taskService.upadteTask(ptask);
    		mav.setViewName("forward:ptask_table.do");
    		
    		return mav;
        }
		
		otask.setProduct(this.productService.findById(productid));
		otask.setProgress(Progress.中期完成);
		
		otask.setUser(user);
		otask.setPtask(ptask);
		
		this.taskService.addOTask(otask);
		 this.taskService.upadteTask(ptask);
		mav.setViewName("forward:ptask_table.do");
		return mav;
	}
	

	
	//生产任务与添加用料操作、接管操作视图表
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "ptask_table")  
    public ModelAndView taskList(@SessionAttribute("userid") Long userId){
		ModelAndView model = new ModelAndView("forward:table_task.jsp");
		User u = this.userService.findUser(userId);
		
		List<PTask> ptasks = this.taskService.getPTaskList()
				.stream()
				.filter(x->x.getPur_user()==null||x.getPur_user().getId()==u.getId())
				.collect(Collectors.toList());
		model.addObject("ptasks", ptasks);
        return model;
    }
	
	//用料任务视图表
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "material_table")  
    public ModelAndView tableForMaterialList(@SessionAttribute("userid") Long userId,Long ptaskid){
		ModelAndView model = new ModelAndView("forward:table_material.jsp");
		
		User user = this.userService.findUser(userId);
		PTask ptask = null;
		if(ptaskid != null) {
			List<OTask> otasks = new ArrayList<OTask>();
			ptask = this.taskService.findPTask(ptaskid);
			if(ptask.getStage()==Stage.待用料||ptask.getStage()==Stage.待采购入库||ptask.getStage()==Stage.待采购||ptask.getStage()==Stage.采购已入库) {
				otasks = this.taskService.getOTaskListByUserAndTaskType(ptask.getPur_user(),  TaskType.用料);
			}
			model.addObject("otasks", otasks);
		}
		else {
			List<OTask> otasks = new ArrayList<OTask>();
			this.taskService.getPTaskListByUser(user).forEach(x->{
				if(x.getStage()==Stage.待用料||x.getStage()==Stage.待采购入库||x.getStage()==Stage.待采购||x.getStage()==Stage.采购已入库) {
					otasks.addAll(this.taskService.getOTaskListByUserAndTaskType(x.getPur_user(),  TaskType.用料));
				}
			});
			model.addObject("otasks", otasks);
			
		}
		
		
		
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "pur_table")  
    public ModelAndView tableForPurList(@SessionAttribute("userid") Long userId,Long ptaskid){
	
	ModelAndView model = new ModelAndView("forward:table_pur.jsp");
		
		User user = this.userService.findUser(userId);
		PTask ptask = null;
		if(ptaskid != null) {
			List<OTask> otasks = new ArrayList<OTask>();
			ptask = this.taskService.findPTask(ptaskid);
			if(ptask.getStage()==Stage.待用料||ptask.getStage()==Stage.待采购入库||ptask.getStage()==Stage.待采购) {
				otasks = this.taskService.getOTaskListByUserAndTaskType(ptask.getPur_user(),  TaskType.采购);
				
				//u已入库则不再显示
				otasks = otasks.stream().filter(x->!x.isStocked()).collect(Collectors.toList());
			}
			model.addObject("otasks", otasks);
		}
		else {
			List<OTask> otasks = new ArrayList<OTask>();
			this.taskService.getPTaskListByUser(user).forEach(x->{
				if(x.getStage()==Stage.待用料||x.getStage()==Stage.待采购入库||x.getStage()==Stage.待采购) {
					otasks.addAll(this.taskService.getOTaskListByUserAndTaskType(x.getPur_user(),  TaskType.采购));
					
				}
				
			});
			//u已入库则不再显示
			List<OTask> otasks2 = otasks.stream().filter(y->!y.isStocked()).collect(Collectors.toList());
			model.addObject("otasks", otasks2);
			
		}
		
		
		
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "updateTaskStatus")  
    public ModelAndView updateTask2(int id, int status){
		Task t = this.taskService.findTask(id);
		switch(status) {
		case 0:t.setProgress(Progress.未开始);break;
		case 2:t.setProgress(Progress.中期完成);break;
		case 3:t.setProgress(Progress.即将完成);break;
		case 4:t.setProgress(Progress.完成);break;
		case 1:t.setProgress(Progress.前期完成);break;
	
		}
		ModelAndView model = new ModelAndView("redirect:pur_table.do");
		this.taskService.upadteTask(t);
        return model;
    }
}
