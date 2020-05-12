package com.stu.yqs.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.dao.UserMapper;
import com.stu.yqs.domain.Good;
import com.stu.yqs.domain.User;

@Service
public class GoodUtil {
	public final static int SECOND_HAND=0;
	public final static int CHARITY_SELL=1;
	public final static String ON_SALE="1";
	public final static String FINISH="2";
	public final static String DELETED="4";
	@Autowired
	private GoodMapper goodMapper;
	@Autowired
	private UserMapper userMapper;

	//批量添加评论者信息
	public JSONArray addReviewerMessageAll(JSONArray arr) {
		JSONArray array=new JSONArray();
		for(int i=0;i<arr.size();i++) {
			JSONObject json=(JSONObject)arr.get(i);
			json.remove("goodId");
			array.add(this.addReviewerMessage(json));
		}
		return array;
	}

	//单个添加评论者信息
	public JSONObject addReviewerMessage(JSONObject json) {
		int reviewerId=json.getInteger("reviewerId");
		User user=userMapper.selectPartByPrimaryKey(reviewerId);
		json.put("name", user.getName());
		json.put("reviewerImage", user.getHeadImage());
		json.put("academy", user.getAcademy());
		return json;
	}
	//批量添加上物主信息
	public JSONArray addOwnerMessageAll(JSONArray arr) {
		return this.addOwnerMessageAll(arr, "ownerId");
	}
	//批量添加上物主信息
	public JSONArray addOwnerMessageAll(JSONArray arr,String key) {
		JSONArray array=new JSONArray();
		for(int i=0;i<arr.size();i++) {
			JSONObject json=this.addOwnerMessage((JSONObject) arr.get(i),key);
			array.add(json);
		}
		return array;
	}
	//单个添加上物主信息
	public JSONObject addOwnerMessage(JSONObject json,String key) {
		User user=userMapper.selectPartByPrimaryKey(json.getInteger(key));
		json.put("sellerName", user.getName());
		json.put("sellerImage", user.getHeadImage());
		return json;
	}

	//检测状态是否正常
	public Good stateNormal(Integer goodId) throws LogicException {
		if(goodId==null)	throw new LogicException(509,"空参数");
		Good good=goodMapper.selectByPrimaryKey(goodId);
		if(good==null)	throw new LogicException(501,"不存在该商品");
		if(good.getState().equals("2"))	throw new LogicException(501,"该商品已被购买");
		if(good.getState().equals("4"))	throw new LogicException(501,"该商品已下架");
		return good;
	}

	//检测该商品是否存在
	public int hasGood(Integer goodId) throws LogicException {
		if(goodId==null)	throw new LogicException(509,"空参数");
		Good good=goodMapper.selectByPrimaryKey(goodId);
		if(good==null)	throw new LogicException(501,"不存在该商品");
		return good.getOwnerId();
	}
}
