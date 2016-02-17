package com.epam.lab.news.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "TAG")
@NamedQuery(name = "getAllTags", query = "SELECT t.name, COUNT(n.id) FROM Tag t LEFT JOIN t.listNews n GROUP BY t.name ORDER BY t.name")
public class Tag implements Serializable {

	private static final long serialVersionUID = -6774677308618977562L;

	@Id
	@Column(name = "NAME")
	@NotBlank(message = "{NotBlank.tag.name}")
	@Size(max = 20, message = "{Size.tag.name}")
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listTags")
	private Set<News> listNews;

	public Tag() {
	}

	public Tag(String name) {
		super();
		this.name = name;
	}

	public Set<News> getListNews() {
		return listNews;
	}

	public void setListNews(Set<News> listNews) {
		this.listNews = listNews;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this);
	}

}
