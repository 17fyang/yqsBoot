package com.stu.yqs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.CollectMapper;
import com.stu.yqs.domain.Collect;
import com.stu.yqs.domain.search.CollectSearch;
import com.stu.yqs.utils.GoodUtil;
import com.stu.yqs.utils.IdentityUtil;
import com.stu.yqs.utils.OutputUtil;
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
	@Autowired
	private OutputUtil outputUtil;
	
	public JSONObject addCollect(Integer goodId) throws LogicException {
		int id=identityUtil.isLogin();
		goodUtil.stateNormal(goodId);
		Collect collect=collectMapper.selectByGoodId(new CollectSearch(id,goodId));
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
		JSONArray arr=(JSONArray) JSONArray.toJSON(collectMapper.searchCollect(search));
		arr=goodUtil.addGoodMessageAll(arr);
		arr=goodUtil.addThumbConditionAll(arr,"goodId");
		arr=goodUtil.addOwnerMessageAll(arr);
		return outputUtil.lazyLoading(arr, range);
	}
	
	
	public JSONObject deleteCollect(Integer goodId) throws LogicException {
		goodUtil.hasGood(goodId);
		int id=identityUtil.isLogin();
		Collect collect=collectMapper.selectByGoodId(new CollectSearch(id,goodId));
		if(collect==null)	throw new LogicException(501,"暂无收藏数据");
		collectMapper.deleteByPrimaryKey(collect.getId());
		return new JSONObject();
	}
}
