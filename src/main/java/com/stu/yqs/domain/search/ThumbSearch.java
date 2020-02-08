package com.stu.yqs.domain.search;

public class ThumbSearch {
	private Integer thumberId;
	private Integer goodId;
	public ThumbSearch() {
	}
	public ThumbSearch(Integer thumberId, Integer goodId) {
		super();
		this.thumberId = thumberId;
		this.goodId = goodId;
	}
	public Integer getId() {
		return thumberId;
	}
	public void setId(Integer thumberId) {
		this.thumberId = thumberId;
	}
	public Integer getGoodId() {
		return goodId;
	}
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}
	
}
