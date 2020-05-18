package com.stu.yqs.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;


@Service
public class CharitySellService extends FatherGoodService{
	//发布一个交易
	public JSONObject newTransaction(String name, String describe, String tag, Double price, Double originalPrice, Double postage, String freeShipping) throws LogicException {
		return super.newTransaction(name, describe, tag, price, originalPrice, postage, freeShipping, 1);
	}
	//获取一些交易，可选择按书院或关键字筛选
	public JSONObject getTransaction(Integer startId, Integer range, String academy,String keyword, String tag) throws LogicException {
		return super.getTransaction(startId, range, academy, keyword, tag, 1);
	}
}
