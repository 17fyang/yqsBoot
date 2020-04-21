package com.stu.yqs.dao;

import java.util.List;

import com.stu.yqs.domain.Address;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);
    
    List<Address> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
}