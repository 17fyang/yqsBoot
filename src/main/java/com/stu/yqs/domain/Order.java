package com.stu.yqs.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Order implements ObjectDomain{
    private Integer id;

    private Integer customerId;

    private Integer sellerId;

    private Integer goodId;
    
    private Integer customerAddress;
    
    private Integer sellerAddress;

    private Short status;
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date establishTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }
    
    public Integer getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Integer customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Integer getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(Integer sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(Date establishTime) {
        this.establishTime = establishTime;
    }
}