package com.stu.yqs.dao;

import java.util.List;

import com.stu.yqs.domain.CharitySell;
import com.stu.yqs.domain.search.GoodSearch;

public interface CharitySellMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CharitySell record);

    int insertSelective(CharitySell record);

    CharitySell selectByPrimaryKey(Integer id);
    
    List<CharitySell> searchCharitySells(GoodSearch record);
    
    List<String> searchLikeCharitySells(String input);

    int updateByPrimaryKeySelective(CharitySell record);

    int updateByPrimaryKey(CharitySell record);
}