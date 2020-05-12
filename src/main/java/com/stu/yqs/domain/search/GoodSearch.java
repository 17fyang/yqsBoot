package com.stu.yqs.domain.search;

public class GoodSearch implements Search{
	private Integer startId;
	private Integer range;
	private Integer ownerId;
	private Integer goodType;
	private String academy;
	private String keyword;
	private String tag;
	private String state;
	private final static int DEFAULT_NUMBER=15;
	public Integer getStartId() {
		return startId;
	}
	public void setStartId(Integer startId) {
		this.startId = startId;
	}
	public Integer getRange() {
		return range;
	}
	public void setRange(Integer range) {
		this.range = range;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public Integer getGoodType() {
		return goodType;
	}
	public void setGoodType(Integer goodType) {
		this.goodType = goodType;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public static int getDefaultNumber() {
		return DEFAULT_NUMBER;
	}
	
	
}
