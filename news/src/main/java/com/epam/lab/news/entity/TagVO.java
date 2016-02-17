package com.epam.lab.news.entity;

public class TagVO {

	private String name;
	
	private Long quantityNews;

	public TagVO(String name, Long quantityNews) {
		super();
		this.name = name;
		this.quantityNews = quantityNews;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQuantityNews() {
		return quantityNews;
	}

	public void setQuantityNews(Long quantityNews) {
		this.quantityNews = quantityNews;
	}

	public TagVO() {
		super();
	}
	
}
