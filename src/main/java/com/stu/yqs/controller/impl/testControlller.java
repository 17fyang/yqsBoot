package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class testControlller {
	@Value("${serverUrl}")
	private String serverUrl;
	
	@RequestMapping("/yqs/test")
	public @ResponseBody String test() {
		System.out.println(serverUrl);
		return new JSONObject().toJSONString();
	}
}
