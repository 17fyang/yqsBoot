package com.stu.yqs.dao;

import java.util.List;

import com.stu.yqs.domain.Collect;
import com.stu.yqs.domain.search.CollectSearch;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);
    
    Collect selectByGoodId(CollectSearch search);
    
    List<Collect> searchCollect(CollectSearch search);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}