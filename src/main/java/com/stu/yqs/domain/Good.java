package com.stu.yqs.domain;

import java.util.Date;

public class Good {
    private Integer id;

    private String name;

    private Integer ownerId;

    private String academy;

    private Date publishTime;

    private String goodDescribe;

    private String image;

    private String state;

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

    public String getGoodDescribe() {
        return goodDescribe;
    }

    public void setGoodDescribe(String goodDescribe) {
        this.goodDescribe = goodDescribe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String[] getImages() {
        return image.split("_split_");
    }
    
    public void setImages(String[] images) {
    	if(images==null || images.length==0)		this.image=null;
    	StringBuilder sb=new StringBuilder();
    	sb.append(images[0]);
    	for(int i=1;i<images.length;i++) {
    		sb.append("_split_");
    		sb.append(images[i]);
    	}
        this.image =sb.toString();
    }
}