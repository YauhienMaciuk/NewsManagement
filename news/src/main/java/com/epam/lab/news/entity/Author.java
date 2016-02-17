package com.epam.lab.news.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "AUTHORS")
@NamedQuery(name = "getAllAuthors", query = "SELECT a.id, a.name, COUNT(n.id) FROM Author a LEFT JOIN a.listNews n GROUP BY a.id, a.name ORDER BY a.name")
public class Author implements Serializable {

	private static final long serialVersionUID = 7434046726863651506L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_SEQ")
	@SequenceGenerator(name = "AUTHOR_SEQ", sequenceName = "AUTHOR_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@NotBlank(message = "{NotBlank.author.lastName}")
	@Size(max = 20, message = "{Size.author.firstName}")
	@Column(name = "NAME")
	private String name;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listAuthors")
	private Set<News> listNews;

	public Author() {
	}

	public Author(Long id, String firstName, String lastName, Set<News> listNews) {
		super();
		this.id = id;
		this.name = lastName;
		this.listNews = listNews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<News> getListNews() {
		return listNews;
	}

	public void setListNews(Set<News> listNews) {
		this.listNews = listNews;
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "listNews");
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "listNews");
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "listNews");
	}

}
