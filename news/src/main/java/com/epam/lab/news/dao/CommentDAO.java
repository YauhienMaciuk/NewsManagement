package com.epam.lab.news.dao;

import java.util.List;

import com.epam.lab.news.entity.Comment;
import com.epam.lab.news.entity.News;

/**
 * The Interface CommentDAO.
 */
public interface CommentDAO {
	
	/**
	 * Receive all.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @param news the news
	 * @return the list
	 */
	public List<Comment> receiveAll(int offset, int limit, News news);
	
	/**
	 * Adds the.
	 *
	 * @param comment the comment
	 * @param idNews the id news
	 * @return the comment
	 */
	public Comment add(Comment comment);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean delete(Long id);

}
