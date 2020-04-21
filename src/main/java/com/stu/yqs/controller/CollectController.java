package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.yqs.aspect.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs/collect")
public interface CollectController {
	@RequestMapping("/addAction")
	 public @ResponseBody String addCollect(Integer goodId)throws LogicException;
	@RequestMapping("/listAction")
	 public @ResponseBody String listAction(Integer startId,Integer range)throws LogicException;
	@RequestMapping("/deleteAction")
	 public @ResponseBody String deleteCollect(Integer goodId)throws LogicException;
}
