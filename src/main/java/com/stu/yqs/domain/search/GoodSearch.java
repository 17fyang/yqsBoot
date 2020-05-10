package com.stu.yqs.domain.search;

public class GoodSearch implements Search{
	private Integer startId;
	private Integer range;
	private Integer goodType;
	private String academy;
	private String keyword;
	private String tag;
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
		this.range =range;
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
	public Integer getGoodType() {
		return goodType;
	}
	public void setGoodType(Integer goodType) {
		this.goodType = goodType;
	}
}
