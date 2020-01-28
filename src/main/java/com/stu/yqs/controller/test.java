package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/yqs")
public class test {
	
	@RequestMapping("/test")
	public @ResponseBody String testController() {
		JSONObject json=new JSONObject();
		json.put("test", "test");
		return json.toJSONString();
	}
}
