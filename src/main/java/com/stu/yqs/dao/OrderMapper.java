package com.stu.yqs.dao;

import java.util.List;

import com.stu.yqs.domain.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);
    
    List<Order> selectByUserId(Integer customerId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}