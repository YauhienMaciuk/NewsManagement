package com.epam.lab.news.entity;

public class AuthorVO {
	
	private String name;
	
	private Long quantityNews;
	
	private Long id;

	public AuthorVO(String name, Long quantityNews, Long id) {
		super();
		this.name = name;
		this.quantityNews = quantityNews;
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthorVO() {
		super();
	}


}
