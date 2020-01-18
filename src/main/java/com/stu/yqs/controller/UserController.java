package com.stu.yqs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/yqs")
public interface UserController {
	@RequestMapping("/getInfo")
	public @ResponseBody String GetInfo()throws Exception;
	
	@RequestMapping("/login")
	public @ResponseBody String Login(String number,String password)throws Exception;
	
	@RequestMapping("/register")
	public @ResponseBody String Register(String number,String password,String academy,String verification)throws Exception;
	
	@RequestMapping("/verificationCode")
	public @ResponseBody String verificationCode(String number)throws Exception;
}
