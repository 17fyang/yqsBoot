package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.yqs.aspect.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs/order")
public interface OrderController {
	@RequestMapping("/submitAction")
	 public @ResponseBody String submitAction(Integer goodId,Integer addressId)throws LogicException;
	@RequestMapping("/updateAction")
	 public @ResponseBody String updateAction(Integer orderId,Short status)throws LogicException;
	@RequestMapping("/getListAction")
	 public @ResponseBody String getListAction(Integer startId,Integer range,Integer sellerId,
			 Integer customerId,Integer status)throws LogicException;
	@RequestMapping("/detailAction")
	 public @ResponseBody String detailAction(Integer orderId)throws LogicException;
	@RequestMapping("/deleteAction")
	 public @ResponseBody String deleteAction(Integer orderId)throws LogicException;
}
