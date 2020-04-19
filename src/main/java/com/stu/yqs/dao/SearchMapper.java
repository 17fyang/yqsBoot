package com.stu.yqs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.stu.yqs.domain.Search;
@Repository
@Mapper
public interface SearchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Search record);

    int insertSelective(Search record);

    Search selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Search record);

    int updateByPrimaryKey(Search record);
}