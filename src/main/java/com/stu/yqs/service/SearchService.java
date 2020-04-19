package com.stu.yqs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.dao.SearchMapper;
import com.stu.yqs.utils.IdentityUtil;
/*
 * author:yf
 * date:2020.4.19
 * detail:搜索相关接口
 */
@Service
public class SearchService {
	@Autowired
	private SearchMapper searchMapper;
	@Autowired
	private IdentityUtil identityUtil;
	@Autowired
	private GoodMapper goodMapper;
	
	public JSONArray indexAction() throws LogicException {
		int id=identityUtil.isLogin();
		List<String> list=searchMapper.selectByUserId(id);
		JSONArray arr=(JSONArray) JSONArray.toJSON(list);
		return arr;
	}
	public JSONArray helperAction(String input) throws LogicException {
		List<String> list=goodMapper.searchLikeGoods(input);
		JSONArray arr=(JSONArray) JSONArray.toJSON(list);
		return arr;
	}
	public JSONObject clearHistoryAction() throws LogicException {
		int id=identityUtil.isLogin();
		int result=searchMapper.deleteByUserId(id);
		if(result==0)	throw new LogicException(501,"删除异常");
		return new JSONObject();
	}
}
