package com.epam.lab.news.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "COMMENTS")
public class Comment implements Serializable {

	private static final long serialVersionUID = -4903305541947101600L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
	@SequenceGenerator(name = "COMMENT_SEQ", sequenceName = "COMMENT_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@NotBlank(message = "{NotBlank.comment.text}")
	@Size(max = 300, message = "{Size.comment.text")
	@Column(name = "TEXT")
	private String text;

	@Column(name = "CREATION_DATE")
	private Timestamp creationDate;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_NEWS", nullable = false)
	private News news;

	public Comment() {
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Comment(Long id, String text, Timestamp creationDate, News news) {
		super();
		this.id = id;
		this.text = text;
		this.creationDate = creationDate;
		this.news = news;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "news");
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "news");
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "news");
	}

}
