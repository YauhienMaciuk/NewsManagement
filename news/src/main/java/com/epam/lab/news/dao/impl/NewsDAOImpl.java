package com.epam.lab.news.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.epam.lab.news.dao.NewsDAO;
import com.epam.lab.news.entity.News;

// TODO: Auto-generated Javadoc
/**
 * The Class NewsDAOImpl.
 */
@Repository
public class NewsDAOImpl implements NewsDAO {

	/** The entity manager. */
	@PersistenceContext(name = "entityManager")
	protected EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.NewsDAO#receive(java.lang.Long)
	 */
	public News receive(Long id) {
		return entityManager.find(News.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.NewsDAO#delete(java.lang.Long)
	 */
	@Override
	public boolean delete(Long id) {
		News news = entityManager.getReference(News.class, id);
		if (news != null) {
			entityManager.remove(news);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.NewsDAO#add(com.epam.lab.news.entity.News)
	 */
	@Override
	public News add(News news) {
		entityManager.persist(news);
		return entityManager.find(News.class, news.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.NewsDAO#receiveNewsByTopComment(int, int)
	 */
	public List<News> receiveNewsByTopComment(int offset, int limit) {
		TypedQuery<News> typedQuery = entityManager.createNamedQuery("getNewsByComments", News.class);
		return typedQuery.setFirstResult(offset).setMaxResults(limit).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.NewsDAO#receiveList(int, int)
	 */
	@Override
	public List<News> receiveList(int offset, int limit) {
		TypedQuery<News> typedQuery = entityManager.createNamedQuery("getAllNews", News.class);
		return typedQuery.setFirstResult(offset).setMaxResults(limit).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.NewsDAO#receiveListByAuthor(int, int,
	 * java.lang.Long)
	 */
	public List<News> receiveListByAuthor(int offset, int limit, Long idAuthor) {
		TypedQuery<News> typedQuery = entityManager.createNamedQuery("getNewsByAuthor", News.class);
		typedQuery.setParameter("idAuthor", idAuthor);
		return typedQuery.setFirstResult(offset).setMaxResults(limit).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.NewsDAO#receiveListByTag(int, int,
	 * java.lang.String)
	 */
	public List<News> receiveListByTag(int offset, int limit, String idTag) {
		TypedQuery<News> typedQuery = entityManager.createNamedQuery("getNewsByTag", News.class);
		typedQuery.setParameter("tag", idTag);
		return typedQuery.setFirstResult(offset).setMaxResults(limit).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.NewsDAO#update(com.epam.lab.news.entity.News)
	 */
	@Override
	public News update(News news) {
		return entityManager.merge(news);
	}

	/* (non-Javadoc)
	 * @see com.epam.lab.news.dao.NewsDAO#addListNews(java.util.List)
	 */
	@Override
	public void addListNews(List<News> listNews) {
		for (News news : listNews) {
			entityManager.persist(news);
		}
	}

}
