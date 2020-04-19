package com.stu.yqs.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.aspect.NecessaryPara;
import com.stu.yqs.controller.OrderController;
import com.stu.yqs.service.OrderService;
@Controller
public class OrderControlerImpl implements OrderController{
	@Autowired
	private OrderService orderService;
	
	@Override
	public String submitAction(@NecessaryPara Integer goodId) throws LogicException {
		return orderService.submitAction(goodId).toJSONString();
	}
	@Override
	public String updateAction(@NecessaryPara Integer goodId,Integer status) throws LogicException {
		return orderService.updateAction(goodId, status).toJSONString();
	}
	@Override
	public String getListAction(Integer startId, Integer range, Integer sellerId, Integer customerId, Integer status)
			throws LogicException {
		return orderService.getListAction(startId, range, sellerId, customerId, status).toJSONString();
	}
}
