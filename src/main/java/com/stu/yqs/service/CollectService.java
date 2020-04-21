package com.stu.yqs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.CollectMapper;
import com.stu.yqs.domain.Collect;
import com.stu.yqs.domain.Good;
import com.stu.yqs.domain.search.CollectSearch;
import com.stu.yqs.utils.GoodUtil;
import com.stu.yqs.utils.IdentityUtil;
/*
 * author：yf
 * date：2020.4.19
 * detail：收藏有关的接口
 */
@Service
public class CollectService {
	@Autowired
	private CollectMapper collectMapper;
	@Autowired
	private IdentityUtil identityUtil;
	@Autowired
	private GoodUtil goodUtil;
	
	public JSONObject addCollect(Integer goodId) throws LogicException {
		int id=identityUtil.isLogin();
		goodUtil.stateNormal(goodId);
		Collect collect=collectMapper.selectByGoodId(goodId);
		if(collect!=null)	throw new LogicException(501,"不可重复收藏");
		
		collect=new Collect();
		collect.setUserId(id);
		collect.setGoodId(goodId);
		collectMapper.insertSelective(collect);
		return (JSONObject)JSONObject.toJSON(collect);
	}
	public JSONObject listAction(Integer startId,Integer range) throws LogicException {
		int id=identityUtil.isLogin();
		CollectSearch search=new CollectSearch();
		search.setUserId(id);
		search.setStartId(startId);
		search.setRange(range);
		List<Good> list=collectMapper.selectCollect(search);
		JSONArray arr=(JSONArray)JSONArray.toJSON(list);
		JSONObject json=new JSONObject();
		json.put("length", list.size());
		boolean isEnd=false;
		if((range==null && list.size()<search.getDefaultRange()) || (range!=null && list.size()<range))	isEnd=true;
		json.put("isEnd",isEnd);
		if(!isEnd)		json.put("nextId",list.get(list.size()-1).getId());
		json.put("arr", arr);
		return json;
	}
	public JSONObject deleteCollect(Integer goodId) throws LogicException {
		int ownerId=goodUtil.hasGood(goodId);
		int id=identityUtil.isLogin();
		if(ownerId!=id)throw new LogicException(501,"只能操作自己的收藏");
		collectMapper.deleteByPrimaryKey(goodId);
		return new JSONObject();
	}
}
