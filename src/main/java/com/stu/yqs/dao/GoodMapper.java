package com.stu.yqs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.stu.yqs.domain.Good;
import com.stu.yqs.domain.search.GoodSearch;
import com.stu.yqs.domain.search.SimilarGood;
@Repository
@Mapper
public interface GoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Good record);

    int insertSelective(Good record);

    Good selectByPrimaryKey(Integer id);
    
    List<Good> searchGoods(GoodSearch record);
    
    List<String> searchLikeGoods(SimilarGood similar);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);
}