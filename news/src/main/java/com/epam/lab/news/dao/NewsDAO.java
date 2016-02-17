package com.epam.lab.news.dao;

import java.util.List;

import com.epam.lab.news.entity.News;

/**
 * The Interface NewsDAO.
 */
public interface NewsDAO {

	/**
	 * Adds the.
	 *
	 * @param news
	 *            the news
	 * @return the news
	 */
	public News add(News news);
	
	/**
	 * Adds the list news.
	 *
	 * @param listNews the list news
	 */
	public void addListNews(List<News> listNews);

	/**
	 * Receive.
	 *
	 * @param id
	 *            the id
	 * @return the news
	 */
	public News receive(Long id);

	/**
	 * Update.
	 *
	 * @param news
	 *            the news
	 * @return the news
	 */
	public News update(News news);

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	public boolean delete(Long id);

	/**
	 * Receive list.
	 *
	 * @param offset
	 *            the offset
	 * @param limit
	 *            the limit
	 * @return the list
	 */
	public List<News> receiveList(int offset, int limit);

	/**
	 * Receive list by author.
	 *
	 * @param offset
	 *            the offset
	 * @param limit
	 *            the limit
	 * @param idAuthor
	 *            the id author
	 * @return the list
	 */
	public List<News> receiveListByAuthor(int offset, int limit, Long idAuthor);

	/**
	 * Receive list by tag.
	 *
	 * @param offset
	 *            the offset
	 * @param limit
	 *            the limit
	 * @param idTag
	 *            the id tag
	 * @return the list
	 */
	public List<News> receiveListByTag(int offset, int limit, String idTag);

	/**
	 * Receive news by top comment.
	 *
	 * @param offset
	 *            the offset
	 * @param limit
	 *            the limit
	 * @return the list
	 */
	public List<News> receiveNewsByTopComment(int offset, int limit);

}
