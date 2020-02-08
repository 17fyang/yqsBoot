package com.stu.yqs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.stu.yqs.domain.Thumb;
@Repository
@Mapper
public interface ThumbMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Thumb record);

    int insertSelective(Thumb record);

    Thumb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Thumb record);

    int updateByPrimaryKey(Thumb record);
}