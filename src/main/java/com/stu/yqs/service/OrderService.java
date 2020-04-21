package com.stu.yqs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.OrderMapper;
import com.stu.yqs.dao.UserMapper;
import com.stu.yqs.domain.Order;
import com.stu.yqs.domain.User;
import com.stu.yqs.utils.GoodUtil;
import com.stu.yqs.utils.IdentityUtil;
@Service
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IdentityUtil identityUtil;
	@Autowired
	private GoodUtil goodUtil;
	/*
	 * 新增订单，未完成部分：通知卖家
	 */
	public JSONObject submitAction(Integer goodId) throws LogicException {
		int  id=identityUtil.isLogin();
		int sellerId=goodUtil.stateNormal(goodId);
		if(id==sellerId)	throw new LogicException(501,"不能购买自己的商品");
		List<Order> orderList=orderMapper.selectByUserId(id);
		for(Order o:orderList) {
			if(o.getGoodId()==goodId)	throw new LogicException(501,"该订单已存在");
		}
		
		Order order=new Order();
		order.setCustomerId(id);
		order.setSellerId(sellerId);
		order.setGoodId(goodId);
		orderMapper.insertSelective(order);
		
		User user=userMapper.selectByPrimaryKey(sellerId);
		JSONObject json=new JSONObject();
		json.put("phoneNumber", user.getPhoneNumber());
		return json;
	}
	public JSONObject updateAction(Integer goodId, Integer status) throws LogicException {
		return null;
	}
	public JSONArray getListAction(Integer startId, Integer range, Integer sellerId, Integer customerId, Integer status)
			throws LogicException {
		return null;
	}
}
