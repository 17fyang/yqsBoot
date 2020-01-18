package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.controller.UserController;
import com.stu.yqs.service.UserService;
@Controller
public class UserControllerImpl implements UserController{
	@Autowired
	private UserService  userService;
	
	public @ResponseBody String GetInfo()throws Exception {
		JSONObject json=userService.getInfo();
		return json.toJSONString();
	}
	
	public @ResponseBody String Login(String number,String password)throws Exception {
		JSONObject json=userService.login(number,password);
		return json.toJSONString();
	}
	
	public @ResponseBody String Register(String number,String password,String academy,String verification)throws Exception {
		JSONObject json=userService.register(number,password,academy,verification);
		return json.toJSONString();
	}
	
	public @ResponseBody String verificationCode(String number)throws Exception {
		JSONObject json=userService.verificationCode(number);
		return json.toJSONString();
	}
}
