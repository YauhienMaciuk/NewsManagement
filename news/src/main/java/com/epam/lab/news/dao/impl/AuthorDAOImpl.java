package com.epam.lab.news.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.epam.lab.news.dao.AuthorDAO;
import com.epam.lab.news.entity.Author;
import com.epam.lab.news.entity.AuthorVO;

/**
 * The Class AuthorDAOImpl.
 */
@Repository
public class AuthorDAOImpl implements AuthorDAO {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.AuthorDAO#receiveAll()
	 */
	@SuppressWarnings("unchecked")
	public List<AuthorVO> receiveAll() {
		Query query = entityManager.createNamedQuery("getAllAuthors");
		List<Object[]> resultList = query.getResultList();
		List<AuthorVO> listInfoAuthor = new ArrayList<AuthorVO>();
		for (Object[] res : resultList) {
			AuthorVO infoAuthor = new AuthorVO();
			infoAuthor.setId((Long) res[0]);
			infoAuthor.setName((String) res[1]);
			infoAuthor.setQuantityNews((Long) res[2]);
			listInfoAuthor.add(infoAuthor);
		}
		return listInfoAuthor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.AuthorDAO#add(com.epam.lab.news.entity.Author)
	 */
	@Override
	public Author add(Author author) {
		entityManager.persist(author);
		return entityManager.find(Author.class, author.getId());
	}

}
