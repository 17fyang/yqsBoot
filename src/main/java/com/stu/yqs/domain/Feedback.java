package com.stu.yqs.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Feedback implements ObjectDomain{
    private Integer id;

    private Integer userId;

    private String content;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date establishtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getEstablishtime() {
        return establishtime;
    }

    public void setEstablishtime(Date establishtime) {
        this.establishtime = establishtime;
    }
}