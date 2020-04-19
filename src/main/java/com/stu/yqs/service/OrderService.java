package com.stu.yqs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.OrderMapper;
@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;
	
	public JSONObject submitAction(Integer goodId) throws LogicException {
		return null;
	}
	public JSONObject updateAction(Integer goodId, Integer status) throws LogicException {
		return null;
	}
	public JSONArray getListAction(Integer startId, Integer range, Integer sellerId, Integer customerId, Integer status)
			throws LogicException {
		return null;
	}
}
