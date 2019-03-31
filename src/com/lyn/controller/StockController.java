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
	
	
	//check all stocked 
	@SuppressWarnings("incomplete-switch")
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "submit")  
    public ModelAndView submitStock(@SessionAttribute("userid")Long userId,Long ptaskid){
		
		PTask ptask = this.taskService.findPTask(ptaskid);
		List<OTask> otasks = new ArrayList<OTask>();
		switch(ptask.getStage()) {
		case 待用料出库:otasks = this.taskService.checkAllStocked(ptask.getPur_user(), ptask, TaskType.用料);break;
		case 待采购入库:otasks = this.taskService.checkAllStocked(ptask.getPur_user(), ptask, TaskType.采购);break;
		case 加工完成:otasks = this.taskService.checkAllStocked(ptask.getPr_user(), ptask, TaskType.加工);break;
		case 待销售出库:break;
		}
		
		
		if(!otasks.isEmpty()) {
			ModelAndView mav = new ModelAndView("forward:error.jsp");
			mav.addObject("otasks", otasks);
			return mav;
		}
		switch(ptask.getStage()) {
		case 待用料出库:ptask.setStage(Stage.用料已出库);break;
		case 待采购入库:ptask.setStage(Stage.采购已入库);break;
		case 加工完成:ptask.setStage(Stage.完成);break;
		case 待销售出库:break;
		}
		this.taskService.upadteTask(ptask);
		ModelAndView mav = new ModelAndView("forward:succ.jsp");
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
				.filter(x->x.getPur_user()==null||x.getPur_user().getId()==u.getId())
				.collect(Collectors.toList());
		model.addObject("ptasks", ptasks);
        return model;
    }
	
	//u子任务视图表
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "otask_table")  
    public ModelAndView tableForMaterialList(@SessionAttribute("userid") Long userId,Long ptaskid){
		ModelAndView model = new ModelAndView("forward:table_material.jsp");
		User user = this.userService.findUser(userId);
		PTask ptask = null;
		if(ptaskid != null) 
			ptask = this.taskService.findPTask(ptaskid);
		
		List<OTask> tmp = taskService.getOTaskList();
		
		// all user related 
		List<OTask> otasks = this.taskService.getOTaskListByUserAndTaskType(user, ptask, TaskType.用料);
		if(ptaskid == null) {
			
			otasks = otasks.stream()
			.filter(x->x.getPtask().getStage()==Stage.待用料||x.getPtask().getStage()==Stage.待采购||x.getPtask().getStage()==Stage.待采购入库)
			.collect(Collectors.toList());
			
		}
		// Produce Task related only
		else {
			
			otasks = otasks.stream()
			.filter(x->x.getPtask().getStage()==Stage.待用料||x.getPtask().getStage()==Stage.待采购||x.getPtask().getStage()==Stage.待采购入库)
			.filter(x->x.getPtask().getId()==ptaskid)
			.collect(Collectors.toList());
		}
		otasks.forEach(x->{
			x.setProgress(Progress.完成);
			x.getPtask().setProgress(Progress.前期完成);
		});
		model.addObject("otasks", otasks);
        return model;
    }
	
	//u产品管理 库存不可更改
}
