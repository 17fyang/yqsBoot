package com.stu.yqs.dao;

import com.stu.yqs.domain.CharitySell;

public interface CharitySellMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CharitySell record);

    int insertSelective(CharitySell record);

    CharitySell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CharitySell record);

    int updateByPrimaryKey(CharitySell record);
}