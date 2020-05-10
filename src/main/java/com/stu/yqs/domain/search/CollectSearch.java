package com.stu.yqs.domain.search;

public class CollectSearch implements Search{
	final private Integer defaultRange=15;
	private Integer userId;
	private Integer startId;
	private Integer range;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDefaultRange() {
		return defaultRange;
	}
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
	
}
