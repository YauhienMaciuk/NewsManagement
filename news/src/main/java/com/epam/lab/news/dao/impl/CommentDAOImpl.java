package com.epam.lab.news.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.epam.lab.news.dao.CommentDAO;
import com.epam.lab.news.entity.Comment;
import com.epam.lab.news.entity.News;

/**
 * The Class CommentDAOImpl.
 */
@Repository
public class CommentDAOImpl implements CommentDAO {

	/** The entity manager. */
	@PersistenceContext(name = "entityManager")
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.CommentDAO#receiveAll(int, int,
	 * com.epam.lab.news.entity.News)
	 */
	@Override
	public List<Comment> receiveAll(int offset, int limit, News news) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Comment> query = criteriaBuilder.createQuery(Comment.class);
		Root<Comment> root = query.from(Comment.class);
		Predicate whereClause = criteriaBuilder.equal(root.get("news").get("id"), news.getId());
		query.select(root).where(whereClause).orderBy(criteriaBuilder.desc(root.get("creationDate")));
		TypedQuery<Comment> typedQuery = entityManager.createQuery(query);
		typedQuery.setFirstResult(offset).setMaxResults(limit);
		return typedQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.CommentDAO#delete(java.lang.Long)
	 */
	@Override
	public boolean delete(Long id) {
		Comment comment = entityManager.find(Comment.class, id);
		if (comment != null) {
			entityManager.remove(comment);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.lab.news.dao.CommentDAO#add(com.epam.lab.news.entity.Comment,
	 * java.lang.Long)
	 */
	@Override
	public Comment add(Comment comment) {
		return entityManager.merge(comment);
	}

}
