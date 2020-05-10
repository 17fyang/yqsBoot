package com.stu.yqs.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Good implements ObjectDomain{
    private Integer id;

    private String name;

    private String goodDescribe;

    private String tag;

    private Double price;

    private Double originalPrice;

    private Double postage;

    private String isNew;

    private String freeShipping;

    private Integer ownerId;

    private String academy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    private String image;
    
    private Integer thumbNumber;

    private Integer browseNumber;

    private Integer reviewNumber ;
    
    private Integer goodType;

    private String state;

    public Good() {}
    
    public Good(Integer id,String name,String goodDescribe,String tag,Double price,
    		Double originalPrice,Double postage,String isNew,String freeShipping,
    		Integer ownerId,String academy,Date publishTime,String image,String state) {
    	this.id=id;
    	this.name=name;
    	this.goodDescribe=goodDescribe;
    	this.tag=tag;
    	this.price=price;
    	this.originalPrice=originalPrice;
    	this.postage=postage;
    	this.isNew=isNew;
    	this.freeShipping=freeShipping;
    	this.ownerId=ownerId;
    	this.academy=academy;
    	this.publishTime=publishTime;
    	this.image=image;
    	this.state=state;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodDescribe() {
        return goodDescribe;
    }

    public void setGoodDescribe(String goodDescribe) {
        this.goodDescribe = goodDescribe;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPostage() {
        return postage;
    }

    public void setPostage(Double postage) {
        this.postage = postage;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(String freeShipping) {
        this.freeShipping = freeShipping;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String[] getImages() {
    	if(this.image==null || this.image.equals(""))	return null;
        return image.split("_split_");
    }

    public void setImages(String[] images) {
    	if(images==null || images.length==0) {
    		this.image=null;
    		return;
    	}
    	StringBuilder sb=new StringBuilder();
        for(int i=0;i<images.length;i++) {
        	if(i==0)	sb.append(images[i]);
        	else	sb.append("_split_"+images[i]);
        }
        this.image=sb.toString();
    }

    public Integer getThumbNumber() {
        return thumbNumber;
    }

    public void setThumbNumber(Integer thumbNumber) {
        this.thumbNumber = thumbNumber;
    }

    public Integer getBrowseNumber() {
        return browseNumber;
    }

    public void setBrowseNumber(Integer browseNumber) {
        this.browseNumber = browseNumber;
    }

    public Integer getReviewNumber () {
        return reviewNumber ;
    }

    public void setReviewNumber (Integer reviewNumber ) {
        this.reviewNumber  = reviewNumber ;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	public Integer getGoodType() {
		return goodType;
	}

	public void setGoodType(Integer goodType) {
		this.goodType = goodType;
	}
    
}