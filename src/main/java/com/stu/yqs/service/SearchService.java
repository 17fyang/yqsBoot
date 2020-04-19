package com.stu.yqs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.CollectMapper;
import com.stu.yqs.dao.SearchMapper;
/*
 * author:yf
 * date:2020.4.19
 * detail:搜索相关接口
 * 未完成：添加搜索记录
 */
@Service
public class SearchService {
	@Autowired
	private SearchMapper searchMapper;
	public JSONArray indexAction() throws LogicException {
		return null;
	}
	public JSONArray helperAction() throws LogicException {
		return null;
	}
	public JSONObject clearHistoryAction() throws LogicException {
		return null;
	}
}
