package com.epam.lab.news.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lab.news.dao.NewsDAO;
import com.epam.lab.news.entity.News;
import com.epam.lab.news.exception.NoSuchEntityException;
import com.epam.lab.news.service.NewsService;

// TODO: Auto-generated Javadoc
/**
 * The Class NewsService.
 */
@Service
public class NewsServiceImpl implements NewsService {

	/** The news dao. */
	@Autowired
	private NewsDAO newsDAO;

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Search news.
	 *
	 * @param id
	 *            the id
	 * @return the news
	 */
	@Transactional
	public News receive(Long id) {
		News news = newsDAO.receive(id);
		if (news == null) {
			throw new NoSuchEntityException(
					messageSource.getMessage("message.no_such_news", null, LocaleContextHolder.getLocale()));
		}
		news.getListTags().size();
		news.getListAuthors().size();
		return news;
	}

	/**
	 * Adds the news.
	 *
	 * @param news
	 *            the news
	 * @return the news
	 */
	@Transactional
	public News add(News news) {
		news.setCreationTime(new Timestamp(new Date().getTime()));
		news.setModificationTime(new Timestamp(new Date().getTime()));
		return newsDAO.add(news);
	}

	/**
	 * Update news.
	 *
	 * @param news
	 *            the news
	 * @return the news
	 */
	@Transactional
	public News update(News news) {
		news.setModificationTime(new Timestamp(new Date().getTime()));
		News existNews = newsDAO.receive(news.getId());
		if (existNews == null) {
			throw new NoSuchEntityException(
					messageSource.getMessage("message.no_such_news", null, LocaleContextHolder.getLocale()));
		}
		news.setCreationTime(existNews.getCreationTime());
		return newsDAO.update(news);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.service.NewsService#delete(java.lang.Long)
	 */
	@Transactional
	public void delete(Long id) {
		if (!newsDAO.delete(id)) {
			throw new NoSuchEntityException(
					messageSource.getMessage("message.no_such_news", null, LocaleContextHolder.getLocale()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.service.NewsService#receiveNewsByTopComment(int,
	 * int)
	 */
	@Transactional
	public List<News> receiveNewsByTopComment(int offset, int limit) {
		List<News> listNews = newsDAO.receiveNewsByTopComment(offset, limit);
		return listNews;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.service.NewsService#receiveList(int, int)
	 */
	@Override
	@Transactional
	public List<News> receiveList(int offset, int limit) {
		List<News> listNews = newsDAO.receiveList(offset, limit);
		return listNews;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.service.NewsService#receiveListByAuthor(int, int,
	 * java.lang.Long)
	 */
	@Override
	@Transactional
	public List<News> receiveListByAuthor(int offset, int limit, Long idAuthor) {
		return newsDAO.receiveListByAuthor(offset, limit, idAuthor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.service.NewsService#receiveListByTag(int, int,
	 * java.lang.String)
	 */
	@Override
	@Transactional
	public List<News> receiveListByTag(int offset, int limit, String idTag) {
		return newsDAO.receiveListByTag(offset, limit, idTag);
	}

	/* (non-Javadoc)
	 * @see com.epam.lab.news.service.NewsService#addListNews(java.util.List)
	 */
	@Override
	@Transactional(rollbackFor = JpaSystemException.class, propagation = Propagation.REQUIRES_NEW)
	public void addListNews(List<News> listNews) {
		for(News news : listNews){
			news.setCreationTime(new Timestamp(new Date().getTime()));
			news.setModificationTime(new Timestamp(new Date().getTime()));
		}
		newsDAO.addListNews(listNews);
	}

}