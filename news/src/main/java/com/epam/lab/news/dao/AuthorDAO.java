package com.epam.lab.news.dao;

import java.util.List;

import com.epam.lab.news.entity.Author;
import com.epam.lab.news.entity.AuthorVO;

// TODO: Auto-generated Javadoc
/**
 * The Interface AuthorDAO.
 */
public interface AuthorDAO {
	
	/**
	 * Adds the.
	 *
	 * @param author the author
	 * @return the author
	 */
	public Author add(Author author);
	
	/**
	 * Receive all.
	 *
	 * @return the list
	 */
	
	public List<AuthorVO> receiveAll();

	
}

