package com.stu.yqs.daoTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stu.yqs.YqsBootApplicationTests;
import com.stu.yqs.dao.UserMapper;
import com.stu.yqs.domain.User;
import com.stu.yqs.domain.EnumPackage.Academy;

@Component
public class UserMapperTest extends YqsBootApplicationTests{
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void userTest() throws Exception {
		User user=new User();
		user.setAcademy(Academy.stringByValue(2));
		user.setPassword("123456");
		user.setPhoneNumber(11712345679L);
		
		int id=userMapper.insertSelective(user);
		System.out.println("注册账号："+id);
	}
	
}
