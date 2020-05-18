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
	public String submitAction(@NecessaryPara Integer goodId,@NecessaryPara Integer addressId) throws LogicException {
		return orderService.submitAction(goodId,addressId).toJSONString();
	}
	@Override
	public String updateAction(@NecessaryPara Integer orderId,@NecessaryPara Short status) throws LogicException {
		return orderService.updateAction(orderId, status).toJSONString();
	}
	@Override
	public String deleteAction(@NecessaryPara Integer orderId) throws LogicException {
		return orderService.deleteAction(orderId).toJSONString();
	}
	@Override
	public String detailAction(@NecessaryPara Integer orderId) throws LogicException {
		return orderService.detailAction(orderId).toJSONString();
	}
	@Override
	public String customerAction(Integer startId, Integer range) throws LogicException {
		return orderService.customerAction(startId,range).toJSONString();
	}
	@Override
	public String sellerAction(Integer startId, Integer range) throws LogicException {
		return orderService.sellerAction(startId,range).toJSONString();
	}
	@Override
	public String totalAction(Integer startId, Integer range) throws LogicException {
		return orderService.totalAction(startId,range).toJSONString();
	}
}
