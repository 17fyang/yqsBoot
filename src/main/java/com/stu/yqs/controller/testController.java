package com.stu.yqs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.dao.UserMapper;
import com.stu.yqs.domain.User;

@RestController
public class testController {
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/hello")
	public String test() {
		JSONObject json=new JSONObject();
		User user=userMapper.selectByPrimaryKey(3);
		json.put("test",user.getAcademy() );
		return json.toString();
	}
}
