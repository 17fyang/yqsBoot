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
	public String newTransaction(MultipartFile[] file, String name, String decribe) throws LogicException {
		return goodService.newTransaction(file,name,decribe).toJSONString();
	}
	@Override
	public String deleteTransaction(int transactionId) throws LogicException {
		return goodService.deleteTransaction(transactionId).toJSONString();
	}
	@Override
	public String getTransaction(int startId, int range, String academy) throws LogicException {
		return goodService.getTransaction(startId,range,academy).toJSONString();
	}
	@Override
	public String searchTransaction(int startId, int range, String keyword) throws LogicException {
		return goodService.searchTransaction(startId,range,keyword).toJSONString();
	}

}
