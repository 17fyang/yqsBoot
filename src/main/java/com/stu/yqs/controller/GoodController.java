package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stu.yqs.exception.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs")
public interface GoodController {
	@RequestMapping("/newTransaction")
	 public @ResponseBody String newTransaction(MultipartFile file[],String name,String describe)throws LogicException;
	
	@RequestMapping("/deleteTransaction")
	 public @ResponseBody String deleteTransaction(Integer transactionId)throws LogicException;
	
	@RequestMapping("/getTransaction")
	 public @ResponseBody String getTransaction(Integer startId,Integer range,String academy,String keyword)throws LogicException;
	
}
