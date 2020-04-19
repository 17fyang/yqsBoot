package com.stu.yqs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.stu.yqs.domain.Oder;
@Repository
@Mapper
public interface OderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Oder record);

    int insertSelective(Oder record);

    Oder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Oder record);

    int updateByPrimaryKey(Oder record);
}