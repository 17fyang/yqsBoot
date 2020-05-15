package com.stu.yqs.domain.search;

public class SimilarGood {
	private Integer goodType;
	private String content;
	private String tag;
	private final static Integer DefaultNSearchNumber=6;
	
	public SimilarGood() {}
	public SimilarGood(String content) {
		this.content=content;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public SimilarGood(Integer goodType,String tag,String content) {
		this.content=content;
		this.tag=tag;
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
