package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.yqs.aspect.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs/search")
public interface SearchController {
	@RequestMapping("/indexAction")
	 public @ResponseBody String indexAction()throws LogicException;
	@RequestMapping("/helperAction")
	 public @ResponseBody String helperAction(String input,String tag)throws LogicException;
	@RequestMapping("/clearHistoryAction")
	 public @ResponseBody String clearHistoryAction()throws LogicException;
}
