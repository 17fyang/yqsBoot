package com.stu.yqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stu.yqs.aspect.LogicException;

@Controller
@CrossOrigin
@RequestMapping("/yqs")
public interface GoodController {
	@RequestMapping("/newTransaction")
	 public @ResponseBody String newTransaction(MultipartFile file[],
			 String name,String describe,String tag,Double price,Double originalPrice,
			 Double postage,String isNew,String freeShipping)throws LogicException;
	
	@RequestMapping("/good/fileUpload")
	 public @ResponseBody String fileUpload(MultipartFile file)throws LogicException;
	@RequestMapping("/deleteTransaction")
	 public @ResponseBody String deleteTransaction(Integer transactionId)throws LogicException;
	@RequestMapping("/getTransaction")
	 public @ResponseBody String getTransaction(Integer startId,Integer range,String academy,String keyword,String tag)throws LogicException;
	@RequestMapping("/getTransactionDetail")
	public @ResponseBody String getTransactionDetail(Integer id,Integer startId,Integer range) throws LogicException;
	
	@RequestMapping("/good/mySalingAction")
	 public @ResponseBody String mySalingAction(Integer startId, Integer range)throws LogicException;
	@RequestMapping("/good/mySaledAction")
	 public @ResponseBody String mySaledAction(Integer startId, Integer range)throws LogicException;
	@RequestMapping("/good/myTotalAction")
	 public @ResponseBody String myTotalAction(Integer startId, Integer range)throws LogicException;
	@RequestMapping("/good/myBoughtAction")
	 public @ResponseBody String myBoughtAction(Integer startId, Integer range)throws LogicException;
}
