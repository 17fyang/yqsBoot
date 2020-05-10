package com.stu.yqs.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Browse implements ObjectDomain{
    private Integer id;

    private Integer goodId;

    private Integer browserId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date browseTime;

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

    public Integer getBrowserId() {
        return browserId;
    }

    public void setBrowserId(Integer browserId) {
        this.browserId = browserId;
    }

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }
}