package com.stu.yqs.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.dao.GoodMapper;
import com.stu.yqs.dao.OrderMapper;
import com.stu.yqs.domain.Good;
import com.stu.yqs.domain.Order;
import com.stu.yqs.domain.search.GoodSearch;
import com.stu.yqs.domain.search.OrderSearch;
import com.stu.yqs.utils.GoodUtil;
import com.stu.yqs.utils.IdentityUtil;
import com.stu.yqs.utils.OutputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GoodService extends FatherGoodService {
    @Autowired
    private IdentityUtil identityUtil;

    @Autowired
    private GoodUtil goodUtil;

    @Autowired
    private OutputUtil outputUtil;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodMapper goodMapper;

    //发布一个交易
    public JSONObject newTransaction(MultipartFile[] file, String name, String describe, String tag, Double price, Double originalPrice, Double postage, String freeShipping) throws LogicException {
        return super.newTransaction(file, name, describe, tag, price, originalPrice, postage, freeShipping, GoodUtil.SECOND_HAND);
    }

    //获取一些交易，可选择按书院或关键字筛选
    public JSONObject getTransaction(Integer startId, Integer range, String academy, String keyword, String tag) throws LogicException {
        return super.getTransaction(startId, range, academy, keyword, tag, GoodUtil.SECOND_HAND);
    }

    //获取待售出的商品
    public JSONObject mySalingAction(Integer startId, Integer range) throws LogicException {
        int id = identityUtil.isLogin();
        GoodSearch search = new GoodSearch();
        search.setOwnerId(id);
        search.setRange(range);
        search.setStartId(startId);
        search.setState(GoodUtil.ON_SALE);

        List<Good> goodList = super.searchGood(search);
        JSONArray arr = (JSONArray) JSONArray.toJSON(goodList);
        JSONArray newArr = goodUtil.addOwnerMessageAll(arr);
        newArr = goodUtil.addThumbConditionAll(newArr);
        return outputUtil.lazyLoading(newArr, range);
    }

    //获取已售出的商品
    public JSONObject mySaledAction(Integer startId, Integer range) throws LogicException {
        int id = identityUtil.isLogin();
        GoodSearch search = new GoodSearch();
        search.setOwnerId(id);
        search.setRange(range);
        search.setStartId(startId);
        search.setState(GoodUtil.FINISH);
        System.out.println("sdasd");
        List<Good> goodList = super.searchGood(search);
        JSONArray arr = (JSONArray) JSONArray.toJSON(goodList);
        JSONArray newArr = goodUtil.addOwnerMessageAll(arr);
        newArr = goodUtil.addThumbConditionAll(newArr);
        return outputUtil.lazyLoading(newArr, range);
    }

    //获取发布过的商品
    public JSONObject myTotalAction(Integer startId, Integer range) throws LogicException {
        int id = identityUtil.isLogin();
        GoodSearch search = new GoodSearch();
        search.setOwnerId(id);
        search.setRange(range);
        search.setStartId(startId);
        search.setState(GoodUtil.FINISH);

        List<Good> totalList = super.searchGood(search);
        search.setState(GoodUtil.ON_SALE);
        List<Good> salingList = super.searchGood(search);
        totalList.addAll(salingList);

        JSONArray arr = (JSONArray) JSONArray.toJSON(totalList);
        JSONArray newArr = goodUtil.addOwnerMessageAll(arr);
        newArr = goodUtil.addThumbConditionAll(newArr);
        System.out.println(newArr.size() + "sdasdadssdad");
        return outputUtil.lazyLoading(newArr, range);
    }

    //获取买过的商品
    public JSONObject myBoughtAction(Integer startId, Integer range) throws LogicException {
        int id = identityUtil.isLogin();
        OrderSearch search = new OrderSearch();
        search.setCustomerId(id);
        search.setStartId(startId);
        search.setRange(range);
        List<Order> orderList = orderMapper.searchOrder(search);
        JSONArray arr = new JSONArray();
        for (Order order : orderList) {
            int orderId = order.getId();
            JSONObject json = (JSONObject) JSONObject.toJSON(goodMapper.selectByPrimaryKey(order.getGoodId()));
            json.put("goodId", json.getIntValue("id"));
            json.put("id", orderId);
            json.put("customerId", order.getCustomerId());
            json.put("establishTime", order.getEstablishTime());
            arr.add(json);
        }
        JSONArray newArr = goodUtil.addOwnerMessageAll(arr, "customerId");
        newArr = goodUtil.addThumbConditionAll(newArr, "goodId");
        return outputUtil.lazyLoading(newArr, range);
    }

}
