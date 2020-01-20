package com.stu.yqs.domain;

import java.util.Date;

public class Good {
    private Integer id;

    private String name;

    private Integer ownerId ;

    private Date publishTime;

    private String describe;

    private String image;

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

    public Integer getOwnerId () {
        return ownerId ;
    }

    public void setOwnerId (Integer ownerId ) {
        this.ownerId  = ownerId ;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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