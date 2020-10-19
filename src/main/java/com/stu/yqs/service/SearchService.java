package com.stu.yqs.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.dao.SearchMapper;
import com.stu.yqs.domain.search.SimilarGood;
import com.stu.yqs.utils.FormatUtil;
import com.stu.yqs.utils.IdentityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private FormatUtil formatUtil;

    public JSONArray indexAction() throws LogicException {
        int id = identityUtil.isLogin();
        List<String> list = searchMapper.selectByUserId(id);
        JSONArray arr = (JSONArray) JSONArray.toJSON(list);
        return arr;
    }

    public JSONArray helperAction(String input, String tag) throws LogicException {
        formatUtil.tag(tag);
        List<String> list = goodMapper.searchLikeGoods(new SimilarGood(0, tag, input));
        JSONArray arr = (JSONArray) JSONArray.toJSON(list);
        return arr;
    }

    public JSONObject clearHistoryAction() throws LogicException {
        int id = identityUtil.isLogin();
        int result = searchMapper.deleteByUserId(id);
        if (result == 0) throw new LogicException(501, "删除异常");
        return new JSONObject();
    }
}
