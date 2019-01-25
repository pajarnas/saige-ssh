package com.lyn.controller;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyn.model.User;
import com.lyn.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	//��UserService�ӿڣ���ֻ��һ��ʵ����̳нӿ�ʱ������ʹ��@Autowired���ӿ�ʵ����@Serviceע�⼴�ɡ�
	//�����ʱ��ʹ��@Resource(name="userService2")��ָ�����Ǹ��ӽӿڣ��ӿ�ʵ����@Service("userService2")ע�⼴�ɡ�
	//@Autowired
	//private UserService userService;
	
	@Resource(name="userService")
	private UserService userService;
	
	
	@Resource(name="userService2")
	private UserService userService2;
	
	//����URL��http://localhost:8080/sas1.1/user/addUser.do
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "addUser")
	public String addUser(){
		System.out.println("**********findUser***********");
		JSONObject obj = new JSONObject();
		User user = new User("user", "123456", "����", "1127447798@qq.com");
		userService.addUser(user);
		User user2 = new User("user2", "123456", "����", "1127447798@qq.com");
		userService2.addUser(user2);
		return obj.toString();
	}
}
