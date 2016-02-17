package com.epam.lab.news.service;

import java.util.List;

import com.epam.lab.news.entity.Author;
import com.epam.lab.news.entity.AuthorVO;

/**
 * The Interface AuthorService.
 */
public interface AuthorService {

	/**
	 * Receive all authors.
	 *
	 * @return the sets the
	 */
	
	public List<AuthorVO> receiveAllAuthors();
	
	/**
	 * Adds the.
	 *
	 * @param author the author
	 * @return the author
	 */
	public Author add(Author author);

}
