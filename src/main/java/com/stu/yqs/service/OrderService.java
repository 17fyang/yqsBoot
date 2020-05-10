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
import com.stu.yqs.domain.search.OrderSearch;
import com.stu.yqs.utils.GoodUtil;
import com.stu.yqs.utils.IdentityUtil;
import com.stu.yqs.utils.OutputUtil;
@Service
public class OrderService {
	final static int INIT_ORDER=0;
	final static int FINISH_ORDER=1;
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IdentityUtil identityUtil;
	@Autowired
	private GoodUtil goodUtil;
	@Autowired
	private OutputUtil outputUtil;
	
	/*
	 * 新增订单，未完成部分：通知卖家
	 */
	public JSONObject submitAction(Integer goodId,Integer addressId) throws LogicException {
		int  id=identityUtil.isLogin();
		int sellerId=goodUtil.stateNormal(goodId).getOwnerId();
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
	
	/*
	 * 订单状态调整
	 */
	public JSONObject updateAction(Integer orderId, Short status) throws LogicException {
		Order order=this.hasOrder(orderId);
		if(this.hasStatus(status))	throw new LogicException(501,"状态参数异常");
		order.setStatus(status);
		orderMapper.updateByPrimaryKey(order);
		return new JSONObject();
	}
	public JSONObject getListAction(Integer startId, Integer range, Integer sellerId, Integer customerId, Integer status)
			throws LogicException {
		if(range!=null && range<0)	throw new  LogicException(501,"参数格式异常");
		int id=identityUtil.isLogin();
		if(!((sellerId!=null && id==sellerId) || (customerId!=null && id==customerId)))	throw new  LogicException(501,"权限异常");
		
		OrderSearch search=new OrderSearch();
		search.setStartId(startId);
		search.setCustomerId(customerId);
		search.setRange(range);
		search.setSellerId(sellerId);
		search.setStatus(status);
		JSONArray arr=(JSONArray) JSONArray.toJSON(orderMapper.searchOrder(search));
		return outputUtil.lazyLoading(arr, range);
	}
	
	
	//检测是否存在该订单
	private Order hasOrder(Integer orderId) throws LogicException {
		Order order=orderMapper.selectByPrimaryKey(orderId);
		if(order==null)		throw new LogicException(501,"不存在该订单");
		return order;
	}
	
	//检测订单状态
	private boolean hasStatus(Short status) {
		if(status==INIT_ORDER)	return true;
		if(status==FINISH_ORDER)	return true;
		return false;
	}
}
