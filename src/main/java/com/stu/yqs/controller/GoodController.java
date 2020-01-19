package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.stu.yqs.exception.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs")
public interface GoodController {
	@RequestMapping("/newTransaction")
	 public String newTransaction(MultipartFile file[],String name,String decribe)throws LogicException;
	
	@RequestMapping("/deleteTransaction")
	 public String deleteTransaction(int transactionId)throws LogicException;
	
	@RequestMapping("/getTransaction")
	 public String getTransaction(int startId,int range,String academy)throws LogicException;
	
	@RequestMapping("/searchTransaction")
	 public String searchTransaction(int startId,int range,String keyword)throws LogicException;
}
