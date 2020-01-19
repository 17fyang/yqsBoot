package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.controller.UserController;
import com.stu.yqs.exception.LogicException;
import com.stu.yqs.service.UserService;
@Controller
public class UserControllerImpl implements UserController{
	@Autowired
	private UserService  userService;
	@Override
	public @ResponseBody String GetInfo()throws LogicException {
		JSONObject json=userService.getInfo();
		return json.toJSONString();
	}
	@Override
	public @ResponseBody String Login(String phoneNumber,String password)throws LogicException {
		JSONObject json=userService.login(phoneNumber,password);
		return json.toJSONString();
	}
	@Override
	public @ResponseBody String Register(String phoneNumber,String password,String academy,String verification)throws LogicException {
		JSONObject json=userService.register(phoneNumber,password,academy,verification);
		return json.toJSONString();
	}
	@Override
	public @ResponseBody String verificationCode(String phoneNumber)throws LogicException {
		JSONObject json=userService.verificationCode(phoneNumber);
		return json.toJSONString();
	}

	@Override
	public String modifyPassword(String phoneNumber, String newPassword, String verification) throws LogicException {
		JSONObject json=userService.modifyPassword(phoneNumber,newPassword,verification);
		return json.toJSONString();
	}
	@Override
	public String logout() throws LogicException {
		JSONObject json=userService.logout();
		return json.toJSONString();
	}
	@Override
	public String modifyHeadImage(MultipartFile file) throws LogicException {
		JSONObject json=userService.modifyHeadImage(file);
		return json.toJSONString();
	}
	@Override
	public String modifyInfo(String name, String emailNumber, String academy) throws LogicException {
		JSONObject json=userService.modifyInfo(name,emailNumber,academy);
		return json.toJSONString();
	}
}
