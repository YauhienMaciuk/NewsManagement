package com.epam.lab.news.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.epam.lab.news.dao.TagDAO;
import com.epam.lab.news.entity.TagVO;
import com.epam.lab.news.entity.Tag;

/**
 * The Class TagDAOImpl.
 */
@Repository
public class TagDAOImpl implements TagDAO {

	/** The entity manager. */
	@PersistenceContext(name = "entityManager")
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.TagDAO#add(com.epam.lab.news.entity.Tag)
	 */
	@Override
	public Tag add(Tag tag) {
		entityManager.persist(tag);
		return entityManager.find(Tag.class, tag.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.dao.TagDAO#receiveAll()
	 */
	@SuppressWarnings("unchecked")
	public List<TagVO> receiveAll() {
		Query query = entityManager.createNamedQuery("getAllTags");
		List<Object[]> resultList = query.getResultList();
		List<TagVO> listInfoTag = new ArrayList<TagVO>();
		for (Object[] res : resultList) {
			TagVO infoTag = new TagVO();
			infoTag.setName((String) res[0]);
			infoTag.setQuantityNews((Long) res[1]);
			listInfoTag.add(infoTag);
		}
		return listInfoTag;
	}
}
