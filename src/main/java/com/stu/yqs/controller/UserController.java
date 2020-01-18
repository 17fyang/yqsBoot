package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.yqs.exception.LogicException;

@Controller
@RequestMapping("/yqs")
public interface UserController {
	@RequestMapping("/getInfo")
	public @ResponseBody String GetInfo()throws LogicException;
	
	@RequestMapping("/login")
	public @ResponseBody String Login(String phoneNumber,String password)throws LogicException;
	
	@RequestMapping("/register")
	public @ResponseBody String Register(String phoneNumber,String password,String academy,String verification)throws LogicException;
	
	@RequestMapping("/verificationCode")
	public @ResponseBody String verificationCode(String phoneNumber)throws LogicException;
	
	@RequestMapping("/modifyPassword")
	public @ResponseBody String modifyPassword(String phoneNumber,String newPassword,String verification)throws LogicException;
	
	@RequestMapping("/logout")
	public @ResponseBody String logout()throws LogicException;
}
