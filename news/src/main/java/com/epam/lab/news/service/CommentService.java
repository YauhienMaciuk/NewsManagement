package com.epam.lab.news.service;

import java.util.List;
import com.epam.lab.news.entity.Comment;

/**
 * The Interface CommentService.
 */
public interface CommentService {

	/**
	 * Receive all.
	 *
	 * @return the sets the
	 */
	public List<Comment> receiveAll(int offset, int limit, Long idNews);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	public void delete(Long id);

	/**
	 * Adds the.
	 *
	 * @param comment
	 *            the comment
	 */
	public Comment add(Comment comment, Long idNews);

}
