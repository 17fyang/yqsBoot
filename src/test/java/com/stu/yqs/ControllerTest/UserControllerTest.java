package com.stu.yqs.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stu.yqs.YqsBootApplicationTests;
import com.stu.yqs.controller.UserController;

@Component
public class UserControllerTest extends YqsBootApplicationTests{
	@Autowired
	private UserController userController;
	
	
	public void registerTest() throws Exception {
		String number="1234514522";
		String password="123456";
		String academy="修远书院";
		String verification="360506";
		
		System.out.println(userController.Register(number, password, academy, verification).toString());
	}
	
	@Test
	public void verificationCode() throws Exception{
		String phoneNumber="18476301719";
		System.out.println(userController.verificationCode(phoneNumber));
	}
}
