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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.lyn.model.User;
import com.lyn.service.UserService;


@Controller
@RequestMapping("/jsp/user")
public class UserController {

	@Resource(name="userService")
	private UserService userService;

	

	

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "login")
    public ModelAndView login(HttpServletRequest request,String id, String password, HttpServletResponse response){
    	ModelAndView model =null;
		User user = this.userService.findUser(Integer.parseInt(id));
		if(user==null) 
		{
		     model = new ModelAndView("forward:/jsp/user/sign_in_error.jsp");
		     return model;
		}
		System.out.println(user.getPassword()+user.getName());
		System.out.println(password);
		if(!user.getPassword().equals(password)) 
			{
			     model = new ModelAndView("forward:/jsp/user/sign_in_error.jsp");
			     return model;
			}
		HttpSession session = request.getSession();
        session.setAttribute("userid",id);
       Cookie useridCookie = new Cookie("userid",id);
       useridCookie.setMaxAge(500);
       useridCookie.setPath("/lyn-ssh");
       response.addCookie(useridCookie);

       Cookie[] cookies = request.getCookies();
       System.out.println("external SessionId:"+session.getId());
       for (Cookie cookie:cookies){
           if(cookie.getName().equals("JSESSIONID")){
               System.out.println("Cookie inside session id:"+session.getId());
               cookie.setValue(session.getId());
               cookie.setPath("/lyn-ssh");
               cookie.setMaxAge(500);
               response.addCookie(cookie);
           }
       }
	    
 
       switch(user.getRole()) {
       case 主管经理:
    	   model = new ModelAndView("redirect:/jsp/manager/index.do");break;
       case 采购用料经理:
    	   model = new ModelAndView("redirect:/jsp/purchaser/index.do");break;
       case 仓库经理:
    	   model = new ModelAndView("redirect:/jsp/stock/index.do");break;
       case 销售经理:
    	   model = new ModelAndView("redirect:/jsp/sale/index.do");break;
       case 加工经理:
    	   model = new ModelAndView("redirect:/jsp/process/index.do");break;
       default:
    	   model = new ModelAndView("redirect:profile.do");break;
       }									
		model.addObject(user);
		return model;
		
    }
	@ResponseBody
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "updateProfile")  
    public ModelAndView updateProfile(@ModelAttribute("user") User user,Long id){
		ModelAndView model = new ModelAndView("redirect:/jsp/user/logout.do");
		this.userService.upadteUser(user);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,value="profile.do")
	ModelAndView profileHandler(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("forward:profile.jsp");
		HttpSession session = request.getSession();
		User u = userService.findUser(Long.parseLong(String.valueOf(session.getAttribute("userid"))));
		
		model.addObject(u);
		return model;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
        //删除cookie
        Cookie useridCookie = new Cookie("userid","");
        System.out.println("remove cookie");
        useridCookie.setMaxAge(0);
        useridCookie.setPath("/");
        response.addCookie(useridCookie);
        request.getSession().removeAttribute("userid");
        request.getSession().removeAttribute("password");
        ModelAndView model =null;
        model = new ModelAndView("redirect:/jsp/user/sign_in.jsp");
		return model;
    }

	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "signUp")  
    public ModelAndView addUser(User user){
		ModelAndView model = new ModelAndView("forward:sign_up_succ.jsp");
		this.userService.addUser(user);
	    model.addObject("user",user);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "addUser")  
    public ModelAndView addUser2(User user){
		ModelAndView model = new ModelAndView("redirect:/user/userList.do");
		this.userService.addUser(user);
	    model.addObject("user",user);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "userList")  
    public ModelAndView userList(User user){
		ModelAndView model = new ModelAndView("forward:/jsp/manager/manager_user.jsp");
		List<User> users = userService.getUserList();
	    model.addObject("users",users);
        return model;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "delUser")  
    public ModelAndView delUser(int id){
		ModelAndView model = new ModelAndView("forward:/user/userList.do");
		this.userService.delUser(this.userService.findUser(id));
        return model;
    }

	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "updateUser")  
    public ModelAndView updateUser(User user){
		ModelAndView model = new ModelAndView("redirect:/user/userList.do");
		this.userService.upadteUser(user);
        return model;
    }
	
	@RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");
	      return "hello";
	   }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "logincookie")
    public String autoLogin(HttpServletRequest request,HttpServletResponse response){
        System.out.println("Enter Controller!");
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return "redirect:login";
        }
        HttpSession session = request.getSession(false);
        String sessionId = session.getId();
 
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("JSESSIONID")) {
                if(!cookie.getValue().equals(sessionId)){
                    return "redirect:login";
                }
            }
        }
 
        for (Cookie cookie2:cookies){
            if(cookie2.getName().equals("username")&&cookie2.getValue()!=null){
                int cookieUsername = Integer.parseInt(cookie2.getValue());
                try{
                    String realPassword = userService.findUser(cookieUsername).getPassword();
                    if (session.getAttribute("password").equals(realPassword)){
                        return "welcome";
                    }else{
                        return "redirect:login.jsp";
                    }
 
                }catch (NullPointerException e){
                    return "redirect:login.jsp";
                }
 
            }
        }
 
        return "redirect:login.jsp";
    }
}
