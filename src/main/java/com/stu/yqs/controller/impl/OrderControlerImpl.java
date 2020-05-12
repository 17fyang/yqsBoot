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
	public String getListAction(Integer startId, Integer range, Integer sellerId, Integer customerId, Integer status)
			throws LogicException {
		return orderService.getListAction(startId, range, sellerId, customerId, status).toJSONString();
	}
	@Override
	public String deleteAction(@NecessaryPara Integer orderId) throws LogicException {
		return orderService.deleteAction(orderId).toJSONString();
	}
}
