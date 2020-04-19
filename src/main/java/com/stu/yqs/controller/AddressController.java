package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.yqs.aspect.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs/address")
public interface AddressController {
	@RequestMapping("/addAction")
	 public @ResponseBody String addAction(String content)throws LogicException;
	@RequestMapping("/updateAction")
	 public @ResponseBody String updateAction(String content)throws LogicException;
	@RequestMapping("/getListAction")
	 public @ResponseBody String getListAction()throws LogicException;
	@RequestMapping("/deleteAction")
	 public @ResponseBody String deleteAction(Integer addressId)throws LogicException;
}
