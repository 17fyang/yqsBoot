package com.stu.yqs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.stu.yqs.domain.Thumb;
import com.stu.yqs.domain.search.ThumbSearch;
@Repository
@Mapper
public interface ThumbMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByGoodId(Integer goodId);

    int insert(Thumb record);

    int insertSelective(Thumb record);

    Thumb selectByPrimaryKey(Integer id);
    
    Thumb selectAppoint(ThumbSearch thumbSearch);

    int updateByPrimaryKeySelective(Thumb record);

    int updateByPrimaryKey(Thumb record);
}