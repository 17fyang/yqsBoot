package com.stu.yqs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.CollectMapper;
/*
 * author：yf
 * date：2020.4.19
 * detail：收藏有关的接口
 */
@Service
public class CollectService {
	@Autowired
	private CollectMapper collectMapper;
	
	public JSONObject addCollect(Integer goodId) throws LogicException {
		
		return null;
	}
	public JSONArray listAction() throws LogicException {
		return null;
	}
	public JSONObject deleteCollect(Integer goodId) throws LogicException {
		return null;
	}
}
