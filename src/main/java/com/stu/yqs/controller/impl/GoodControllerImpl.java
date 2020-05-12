package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.GoodController;
import com.stu.yqs.service.GoodService;
@Controller
public class GoodControllerImpl implements GoodController {
	@Autowired
	private GoodService goodService;
	@Override
	public String newTransaction(@NecessaryPara MultipartFile[] file, @NecessaryPara String name, 
			@NecessaryPara String describe, String tag, Double price,Double originalPrice, 
			Double postage,String isNew, String freeShipping) throws LogicException {
		
		return goodService.newTransaction(file,name,describe,tag,price,originalPrice,postage,freeShipping).toJSONString();
	}
	@Override
	public String deleteTransaction(@NecessaryPara Integer transactionId) throws LogicException {
		return goodService.deleteTransaction(transactionId).toJSONString();
	}
	@Override
	public String getTransaction(Integer startId, @NecessaryPara Integer range, String academy, String keyword,String tag) throws LogicException {
		return goodService.getTransaction(startId,range,academy,keyword,tag).toJSONString();
	}
	@Override
	public String getTransactionDetail(@NecessaryPara Integer id,Integer startId,Integer range) throws LogicException {
		return goodService.getTransactionDetail(id,startId,range).toJSONString();
	}
	@Override
	public String mySalingAction(Integer startId, Integer range) throws LogicException {
		return goodService.mySalingAction(startId,range).toJSONString();
	}
	@Override
	public String mySaledAction(Integer startId, Integer range) throws LogicException {
		return goodService.mySaledAction(startId,range).toJSONString();
	}
	@Override
	public String myTotalAction(Integer startId, Integer range) throws LogicException {
		return goodService.myTotalAction(startId,range).toJSONString();
	}
	@Override
	public String myBoughtAction(Integer startId, Integer range) throws LogicException {
		return goodService.myBoughtAction(startId,range).toJSONString();
	}
	
}
