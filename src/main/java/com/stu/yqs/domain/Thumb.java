package com.stu.yqs.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Thumb implements ObjectDomain{
    private Integer id;

    private Integer goodId;

    private Integer thumberId;
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date thumbTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getThumberId() {
        return thumberId;
    }

    public void setThumberId(Integer thumberId) {
        this.thumberId = thumberId;
    }

    public Date getThumbTime() {
        return thumbTime;
    }

    public void setThumbTime(Date thumbTime) {
        this.thumbTime = thumbTime;
    }
}