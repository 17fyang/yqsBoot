package com.stu.yqs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.stu.yqs.domain.IndexRecommend;
@Repository
@Mapper
public interface IndexRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IndexRecommend record);

    int insertSelective(IndexRecommend record);

    IndexRecommend selectByPrimaryKey(Integer id);
    
    List<IndexRecommend> selectAll();

    int updateByPrimaryKeySelective(IndexRecommend record);

    int updateByPrimaryKey(IndexRecommend record);
}