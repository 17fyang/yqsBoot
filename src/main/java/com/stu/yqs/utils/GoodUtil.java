package com.stu.yqs.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.dao.UserMapper;
import com.stu.yqs.domain.Good;
import com.stu.yqs.domain.Review;
import com.stu.yqs.domain.User;

@Service
public class GoodUtil {
	public final static int ON_SALE=1;
	public final static int FINISH=2;
	public final static int DELETED=4;
	@Autowired
	private GoodMapper goodMapper;
	@Autowired
	private UserMapper userMapper;

	//补上评论列表中评论者id对应的名字
	public JSONArray getFullReviewList(List<Review> reviewList) {
		JSONArray array=new JSONArray();
		for(int i=0;i<reviewList.size();i++) {
			Review review=reviewList.get(i);
			User user=userMapper.selectPartByPrimaryKey(review.getReviewerId());
			JSONObject json=(JSONObject)JSONObject.toJSON(review);
			json.remove("goodId");
			json.put("name", user.getName());
			json.put("reviewerImage", user.getHeadImage());
			json.put("academy", user.getAcademy());
			array.add(json);
		}
		return array;
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

	public JSONArray addMessage(JSONArray arr) {
		JSONArray array=new JSONArray();
		for(int i=0;i<arr.size();i++) {
			JSONObject json=(JSONObject) arr.get(i);
			User user=userMapper.selectPartByPrimaryKey(json.getInteger("ownerId"));
			json.put("sellerName", user.getName());
			json.put("sellerImage", user.getHeadImage());
			array.add(json);
		}
		return array;
	}
}
