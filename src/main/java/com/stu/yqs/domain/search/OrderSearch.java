package com.stu.yqs.domain.search;

public class OrderSearch {
	private Integer startId;
	private Integer range;
	private Integer sellerId;
	private Integer customerId;
	private Integer status;
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
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
