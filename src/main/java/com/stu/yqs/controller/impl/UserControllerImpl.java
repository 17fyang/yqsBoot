package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.UserController;
import com.stu.yqs.service.UserService;
@Controller
public class UserControllerImpl implements UserController{
	@Autowired
	private UserService  userService;
	
	@Override
	public @ResponseBody String GetInfo()throws LogicException {
		return userService.getInfo().toJSONString();
	}
	@Override
	public @ResponseBody String Login(@NecessaryPara String phoneNumber,@NecessaryPara String password)throws LogicException {
		return userService.login(phoneNumber,password).toJSONString();
	}
	@Override
	public @ResponseBody String Register(@NecessaryPara String phoneNumber,@NecessaryPara String password,@NecessaryPara String academy,@NecessaryPara String verification)throws LogicException {
		return userService.register(phoneNumber,password,academy,verification).toJSONString();
	}
	@Override
	public @ResponseBody String verificationCode(@NecessaryPara String phoneNumber)throws LogicException {
		return userService.verificationCode(phoneNumber).toJSONString();
	}

	@Override
	public String modifyPassword(@NecessaryPara String phoneNumber, @NecessaryPara String newPassword, @NecessaryPara String verification) throws LogicException {
		return userService.modifyPassword(phoneNumber,newPassword,verification).toJSONString();
	}
	@Override
	public String logout() throws LogicException {
		return userService.logout().toJSONString();
	}
	@Override
	public String modifyHeadImage(@NecessaryPara MultipartFile file) throws LogicException {
		return userService.modifyHeadImage(file).toJSONString();
	}
	@Override
	public String modifyInfo(String name, String emailNumber, String academy) throws LogicException {
		return userService.modifyInfo(name,emailNumber,academy).toJSONString();
	}
}
