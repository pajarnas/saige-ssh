package com.lyn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;


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
 * @filename  StockController.java
 *
 * @date      2019-04-07
 *
 */


@Controller
@RequestMapping("/jsp/stock")
public class StockController {

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
	

	

	//Take 
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "take")  
    public ModelAndView takeTask(@SessionAttribute("userid")Long userId,Long ptaskid){
		
		PTask t = this.taskService.findPTask(ptaskid);
		
		t.setS_user(this.userService.findUser(userId));
		//set user
		ModelAndView mav = new ModelAndView("forward:otask_table.do");
		this.taskService.upadteTask(t);
		return mav;
    }
	
	//accept
	@SuppressWarnings("incomplete-switch")
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "accept")  
    public ModelAndView acceptTask(@SessionAttribute("userid")Long userId,Long otaskid){
		ModelAndView mav = new ModelAndView("forward:otask_table.do");
		OTask t = this.taskService.findOTask(otaskid);
		Product product = this.productService.findById(t.getProduct().getId());
		if (t.getQuality()<=0) {
			mav.setViewName("forward:error.jsp");
			mav.addObject("message", "数量异常，系统拒绝。");
		}else if(t.isStocked()){
			mav.setViewName("forward:error.jsp");
			mav.addObject("message", "不可重复验收，系统拒绝。");
		}
		else {
			
			switch(t.getType()) {
			case 用料:product.setQuality(product.getQuality()-t.getQuality());break;
			case 加工:product.setQuality(product.getQuality()+t.getQuality());break;
			case 销售:product.setQuality(product.getQuality()-t.getQuality());break;
			case 采购:product.setQuality(product.getQuality()+t.getQuality());break;
			}
			this.productService.upadteProduct(product);
			t.setStocked(true);
			this.taskService.upadteTask(t);
			mav.setViewName("forward:otask_table.do?ptaskid="+String.valueOf(t.getPtask().getId()));
		}
		return mav;
    }
	
	//reject, roll back to previous stage 
	@SuppressWarnings("incomplete-switch")
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "reject")  
    public ModelAndView reject(@SessionAttribute("userid")Long userId,Long otaskid){
		OTask otask = this.taskService.findOTask(otaskid);
		PTask ptask = otask.getPtask();
		
		switch(ptask.getStage()) {
		case 待用料出库:ptask.setStage(Stage.待用料);ptask.setProgress(Progress.前期完成);break;
		case 待采购入库:ptask.setStage(Stage.待采购);ptask.setProgress(Progress.前期完成);break;
		case 待成品入库:ptask.setStage(Stage.待加工完成);ptask.setProgress(Progress.即将完成);break;
		case 待销售出库:break;
		}
		
		this.taskService.upadteTask(ptask);
		this.taskService.resetAllStocked(otask);
		
		
		ModelAndView mav = new ModelAndView("forward:succ.jsp");
		mav.addObject("message", ptask.getStage());
		return mav;
    }
	//check all stocked 
	@SuppressWarnings("incomplete-switch")
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "submit")  
    public ModelAndView submit(@SessionAttribute("userid")Long userId,Long ptaskid){
		ModelAndView mav = new ModelAndView();
		PTask ptask = this.taskService.findPTask(ptaskid);
		List<OTask> otasks = new ArrayList<OTask>();
		switch(ptask.getStage()) {
		case 待用料出库:otasks = this.taskService.checkAllStocked(ptask.getPur_user(), ptask, TaskType.用料);break;
		case 待采购入库:otasks = this.taskService.checkAllStocked(ptask.getPur_user(), ptask, TaskType.采购);break;
		case 待成品入库:otasks = this.taskService.checkAllStocked(ptask.getPr_user(), ptask, TaskType.加工);break;
		case 待销售出库:break;
		}
		
		if(!otasks.isEmpty()) {
			mav.setViewName("forward:error_stocked.jsp");
			mav.addObject("otasks", otasks);
			return mav;
		}
		switch(ptask.getStage()) {
		case 待用料出库:ptask.setStage(Stage.用料已出库);ptask.setProgress(Progress.中期完成);break;
		case 待采购入库:ptask.setStage(Stage.采购已入库);ptask.setProgress(Progress.前期完成);break;
		case 待成品入库:ptask.setStage(Stage.完成);ptask.setProgress(Progress.完成);break;
		case 待销售出库:break;
		}
		
		this.taskService.upadteTask(ptask);
		mav.setViewName("forward:succ.jsp");
		mav.addObject("message", ptask.getStage());
		return mav;
    }
	


	
	//u生产任务接 管 操作视图表
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "ptask_table")  
    public ModelAndView taskList(@SessionAttribute("userid") Long userId){
		ModelAndView model = new ModelAndView("forward:table_task.jsp");
		User u = this.userService.findUser(userId);
		
		List<PTask> ptasks = this.taskService.getPTaskList()
				.stream()
				.filter(x->x.getS_user()==null||x.getS_user().getId()==u.getId())
				.collect(Collectors.toList());
		model.addObject("ptasks", ptasks);
        return model;
    }
	
	//u子任务视图表
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "otask_table")  
    public ModelAndView tableForMaterialList(@SessionAttribute("userid") Long userId,Long ptaskid){
		ModelAndView model = new ModelAndView("forward:table_otasks.jsp");
		List<OTask> otasks = new ArrayList<OTask>();
		PTask ptask = this.taskService.findPTask(ptaskid);
		if(ptask.getStage()==Stage.待采购入库) {
			otasks = this.taskService.getOTaskListByUserAndTaskType(ptask.getPur_user(), ptask, TaskType.采购);
		}
		if(ptask.getStage()==Stage.待用料出库) {
			otasks = this.taskService.getOTaskListByUserAndTaskType(ptask.getPur_user(), ptask, TaskType.用料);
		}
		if(ptask.getStage()==Stage.待成品入库) {
			otasks = this.taskService.getOTaskListByUserAndTaskType(ptask.getPr_user(), ptask, TaskType.加工);
		}
		model.addObject("otasks", otasks);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "updateProfile")  
    public ModelAndView updateProfile(@ModelAttribute("user") User user,Long id){
	
		ModelAndView model = new ModelAndView("redirect:/lyn-ssh/jsp/user/logout.do");
		this.userService.upadteUser(user);
        return model;
    }
	
	//u产品管理 库存不可更改
}
