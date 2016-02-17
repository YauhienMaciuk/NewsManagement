package com.epam.lab.news.service;

import java.util.List;

import com.epam.lab.news.entity.News;

// TODO: Auto-generated Javadoc
/**
 * The Interface NewsService.
 */
public interface NewsService {

	/**
	 * Receive.
	 *
	 * @param id
	 *            the id
	 * @return the news
	 */
	public News receive(Long id);
	
	/**
	 * Receive news by top comment.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @return the list
	 */
	public List<News> receiveNewsByTopComment(int offset, int limit);
	
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
	 * Receive list.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @return the list
	 */
	public List<News> receiveList(int offset, int limit);

	/**
	 * Receive list.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @param idAuthor the id author
	 * @return the list
	 */
	public List<News> receiveListByAuthor(int offset, int limit, Long idAuthor);

	/**
	 * Receive list.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @param idTag the id tag
	 * @return the list
	 */
	public List<News> receiveListByTag(int offset, int limit, String idTag);

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
	 */
	public void delete(Long id);

}
