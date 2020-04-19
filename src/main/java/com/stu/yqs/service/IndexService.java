package com.stu.yqs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.IndexRecommendMapper;
import com.stu.yqs.domain.IndexRecommend;
/*
 * date：2020.4.19
 * author：yf
 * detail：
 */
@Service
public class IndexService {
	@Autowired
	private IndexRecommendMapper indexMapper;
	
	public JSONArray recommend() throws LogicException {
		List<IndexRecommend> list=indexMapper.selectAll();
		JSONArray arr=(JSONArray) JSONArray.toJSON(list);
		return arr;
	}
}
