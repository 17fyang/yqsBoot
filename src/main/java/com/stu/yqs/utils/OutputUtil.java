package com.stu.yqs.utils;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Component
public class OutputUtil {
	final static int DEFAULT_RANGE=15;
	public JSONObject lazyLoading(JSONArray list,Integer range) {
		JSONObject json=new JSONObject();
		JSONArray arr=(JSONArray)JSON.toJSON(list);
		json.put("length", list.size());
		boolean isEnd=false;
		if((range==null && list.size()<DEFAULT_RANGE) || (range!=null && list.size()<range))	isEnd=true;
		json.put("isEnd",isEnd);
		if(!isEnd)		json.put("nextId",list.getJSONObject(list.size()-1).get("id"));
		json.put("arr", arr);
		return json;
	}
}
