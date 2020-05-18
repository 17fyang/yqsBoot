package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.yqs.aspect.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs/CharitySell")
public interface CharitySellController {
	@RequestMapping("/newAction")
	 public @ResponseBody String newTransaction(String name,String describe,String tag,Double price,Double originalPrice,
			 Double postage,String isNew,String freeShipping)throws LogicException;
	
	@RequestMapping("/deleteAction")
	 public @ResponseBody String deleteTransaction(Integer transactionId)throws LogicException;
	@RequestMapping("/getAction")
	 public @ResponseBody String getTransaction(Integer startId,Integer range,String academy,String keyword,String tag)throws LogicException;
	@RequestMapping("/detailAction")
	public @ResponseBody String getTransactionDetail(Integer id,Integer startId,Integer range) throws LogicException;
}
