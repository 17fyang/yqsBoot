package com.stu.yqs.domain;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;


public class User implements ObjectDomain{
    private Integer id;

    private String headImage;
    
    private String emailNumber;

    private String password;

    private String name;

    private String academy;

    private Long phoneNumber;

    private String idType;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;

    public User() {}
    
    public User(Integer id, String headImage, String emailNumber, String password, String name, String academy,
			Long phoneNumber, String idType, Date registerDate) {
		super();
		this.id = id;
		this.headImage = headImage;
		this.emailNumber = emailNumber;
		this.password = password;
		this.name = name;
		this.academy = academy;
		this.phoneNumber = phoneNumber;
		this.idType = idType;
		this.registerDate = registerDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailNumber() {
        return emailNumber;
    }

    public void setEmailNumber(String emailNumber) {
        this.emailNumber = emailNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdType() {
        return idType;
    }

    public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public void setIdType(String idType) {
        this.idType = idType;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
