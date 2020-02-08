package com.stu.yqs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.stu.yqs.domain.Review;
import com.stu.yqs.domain.search.ReviewSearch;
@Repository
@Mapper
public interface ReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(Integer id);
    
    List<Review> searchReview(ReviewSearch reviewSearch);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
}