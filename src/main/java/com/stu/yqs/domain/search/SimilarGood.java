package com.stu.yqs.domain.search;

public class SimilarGood {
	private Integer goodType;
	private String content;
	private final static Integer DefaultNSearchNumber=6;
	
	public SimilarGood() {}
	public SimilarGood(String content) {
		this.content=content;
	}
	public SimilarGood(Integer goodType,String content) {
		this.content=content;
		this.goodType=goodType;
	}
	public Integer getGoodType() {
		return goodType;
	}
	public void setGoodType(Integer goodType) {
		this.goodType = goodType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static Integer getDefaultnsearchnumber() {
		return DefaultNSearchNumber;
	}
	
}
