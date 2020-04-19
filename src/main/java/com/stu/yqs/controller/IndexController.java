package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.yqs.aspect.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs")
public interface IndexController {
	@RequestMapping("/index/recommend")
	 public @ResponseBody String recommend()throws LogicException;
	@RequestMapping("/feedback/submitAction")
	 public @ResponseBody String feedback(String content)throws LogicException;
}
