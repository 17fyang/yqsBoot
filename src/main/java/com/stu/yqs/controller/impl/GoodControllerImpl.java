package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.stu.yqs.controller.GoodController;
import com.stu.yqs.exception.LogicException;
import com.stu.yqs.service.GoodService;
@Controller
public class GoodControllerImpl implements GoodController {
	@Autowired
	private GoodService goodService;
	@Override
	public String newTransaction(MultipartFile[] file, String name, String describe) throws LogicException {
		return goodService.newTransaction(file,name,describe).toJSONString();
	}
	@Override
	public String deleteTransaction(Integer transactionId) throws LogicException {
		return goodService.deleteTransaction(transactionId).toJSONString();
	}
	@Override
	public String getTransaction(Integer startId, Integer range, String academy, String keyword) throws LogicException {
		return goodService.getTransaction(startId,range,academy,keyword).toJSONString();
	}

}
