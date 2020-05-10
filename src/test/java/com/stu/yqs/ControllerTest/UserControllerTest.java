package com.stu.yqs.ControllerTest;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

import com.stu.yqs.YqsBootApplicationTests;
import com.stu.yqs.controller.UserController;


public class UserControllerTest extends YqsBootApplicationTests{
	@Autowired
	private UserController userController;
	
	@Ignore
	public void registerTest() throws Exception {
		String phoneNumber="1234514522";
		String password="123456";
		String academy="修远书院";
		String verification="123456";
		System.out.println("userController测试：");
		userController.verificationCode(phoneNumber);
		System.out.println(userController.Register(phoneNumber, password, academy, verification).toString());
		System.out.println("-----------------------------");
	}
	@Ignore
	public void verificationCode() throws Exception{
		String phoneNumber="18476301719";
		System.out.println("verificationCode测试：");
		System.out.println(userController.verificationCode(phoneNumber));
		System.out.println("-----------------------------");
	}
	@Ignore
	public void modifyPassword() throws Exception{
		String phoneNumber="18476301719";
		String password="1234569";
		String verification="123456";
		System.out.println("modifyPassword测试：");
		userController.verificationCode(phoneNumber);
		System.out.println(userController.modifyPassword(phoneNumber, password, verification));
		System.out.println("-----------------------------");
	}
}
