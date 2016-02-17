package com.epam.lab.news.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "NEWS")
@NamedQueries({ @NamedQuery(name = "getAllNews", query = "SELECT n FROM News n ORDER BY n.modificationTime DESC"),
		@NamedQuery(name = "getNewsByAuthor", query = "SELECT n FROM News n LEFT JOIN n.listAuthors a WHERE a.id = :idAuthor ORDER BY n.modificationTime DESC"),
		@NamedQuery(name = "getNewsByTag", query = "SELECT n FROM News n LEFT JOIN n.listTags t WHERE t.name = :tag ORDER BY n.modificationTime DESC"),
		@NamedQuery(name = "getNewsByComments", query = "SELECT n FROM News n LEFT JOIN n.listComments c GROUP BY n.id, n.title, n.description, n.fullText, n.creationTime, n.modificationTime ORDER BY COUNT(c.news) DESC") })
public class News implements Serializable {

	private static final long serialVersionUID = 8227925894734706603L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_SEQ")
	@SequenceGenerator(name = "NEWS_SEQ", sequenceName = "NEWS_SEQ", allocationSize = 1)
	private Long id;

	@NotBlank(message = "{NotBlank.news.title}")
	@Size(max = 50, message = "{Size.news.title}")
	private String title;

	@NotBlank(message = "{NotBlank.news.description}")
	@Size(max = 200, message = "{Size.news.description}")
	private String description;

	@Column(name = "full_text")
	@NotBlank(message = "{NotBlank.news.fullText}")
	@Size(max = 2000, message = "{Size.news.description}")
	private String fullText;

	@Column(name = "creation_date")
	private Timestamp creationTime;

	@Column(name = "modification_date")
	private Timestamp modificationTime;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "NEWS_AUTHORS", joinColumns = @JoinColumn(name = "NEWS_ID", referencedColumnName = "ID") , inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID") )
	@Size(min = 1, max = 3, message = "{Size.news.listAuthors}")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<Author> listAuthors;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "NEWS_TAG", joinColumns = @JoinColumn(name = "NEWS_ID", referencedColumnName = "ID") , inverseJoinColumns = @JoinColumn(name = "TAG", referencedColumnName = "NAME") )
	@Size(min = 1, message = "{Size.news.listTags}")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<Tag> listTags;

	@OneToMany(mappedBy = "news", fetch = FetchType.LAZY)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<Comment> listComments;

	public News() {

	}

	public News(Long id, String title, String description, String fullText) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.fullText = fullText;
	}
	
	

	public News(String title) {
		super();
		this.title = title;
	}

	public Set<Author> getListAuthors() {
		return listAuthors;
	}

	public void setListAuthors(Set<Author> listAuthors) {
		this.listAuthors = listAuthors;
	}

	public Set<Tag> getListTags() {
		return listTags;
	}

	public void setListTags(Set<Tag> listTags) {
		this.listTags = listTags;
	}

	public Set<Comment> getListComments() {
		return listComments;
	}

	public void setListComments(Set<Comment> listComments) {
		this.listComments = listComments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Timestamp modificationTime) {
		this.modificationTime = modificationTime;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "listAuthors", "listTags", "listComments");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "listAuthors", "listTags", "listComments");
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "listAuthors", "listTags", "listComments");
	}
}
