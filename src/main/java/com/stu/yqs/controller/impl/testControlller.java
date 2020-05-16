package com.stu.yqs.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.dao.AddressMapper;
import com.stu.yqs.dao.OrderMapper;
import com.stu.yqs.domain.Address;
import com.stu.yqs.domain.Order;
import com.stu.yqs.domain.search.OrderSearch;

@Controller
public class testControlller {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private AddressMapper addressMapper;
	
	@RequestMapping("/yqs/test")
	public @ResponseBody String test() {
		//seller
		OrderSearch search=new OrderSearch();
		search.setRange(200);
		List<Order> list=orderMapper.searchOrder(search);
		for(Order  order:list) {
			int sellerId=order.getSellerId();
			Address seller=addressMapper.selectDefaultByUserId(sellerId);
			order.setSellerAddress(seller.getId());
			int customerId=order.getCustomerId();
			Address customer=addressMapper.selectDefaultByUserId(customerId);
			order.setCustomerAddress(customer.getId());
			orderMapper.updateByPrimaryKeySelective(order);
		}
		return new JSONObject().toJSONString();
	}
}
