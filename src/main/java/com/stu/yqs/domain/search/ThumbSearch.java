package com.stu.yqs.domain.search;

public class ThumbSearch{
	private Integer thumberId;
	private Integer goodId;
	private Integer startGoodId;
	private Integer endGoodId;
	public ThumbSearch() {
	}
	public ThumbSearch(Integer thumberId, Integer goodId) {
		super();
		this.thumberId = thumberId;
		this.goodId = goodId;
	}
	public Integer getThumberId() {
		return thumberId;
	}
	public void setThumberId(Integer thumberId) {
		this.thumberId = thumberId;
	}
	public Integer getStartGoodId() {
		return startGoodId;
	}
	public void setStartGoodId(Integer startGoodId) {
		this.startGoodId = startGoodId;
	}
	public Integer getEndGoodId() {
		return endGoodId;
	}
	public void setEndGoodId(Integer endGoodId) {
		this.endGoodId = endGoodId;
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
