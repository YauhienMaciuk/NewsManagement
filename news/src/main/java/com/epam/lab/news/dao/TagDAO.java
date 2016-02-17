package com.epam.lab.news.dao;

import java.util.List;

import com.epam.lab.news.entity.TagVO;
import com.epam.lab.news.entity.Tag;

/**
 * The Interface TagDAO.
 */
public interface TagDAO {

	/**
	 * Adds the.
	 *
	 * @param tag
	 *            the tag
	 * @return the tag
	 */
	public Tag add(Tag tag);

	/**
	 * Receive all.
	 *
	 * @return the list
	 */
	public List<TagVO> receiveAll();

}
