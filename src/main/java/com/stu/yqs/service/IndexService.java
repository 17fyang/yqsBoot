package com.stu.yqs.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.FeedbackMapper;
import com.stu.yqs.dao.IndexRecommendMapper;
import com.stu.yqs.domain.Feedback;
import com.stu.yqs.domain.IndexRecommend;
import com.stu.yqs.utils.IdentityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * date：2020.4.19
 * author：yf
 * detail：
 */
@Service
public class IndexService {
    @Autowired
    private IndexRecommendMapper indexMapper;
	
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private IdentityUtil identityUtil;

    public JSONArray recommend() throws LogicException {
        List<IndexRecommend> list = indexMapper.selectAll();
        JSONArray arr = (JSONArray) JSONArray.toJSON(list);
        return arr;
    }

    public JSONObject feedback(String content) throws LogicException {
        int id = 0;
        try {
            id = identityUtil.isLogin();
        } catch (LogicException e) {
        }
        Feedback feedback = new Feedback();
        feedback.setContent(content);
        feedback.setId(id);
        feedbackMapper.insertSelective(feedback);
        return new JSONObject();
    }
}
