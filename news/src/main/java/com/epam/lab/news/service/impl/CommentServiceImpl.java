package com.epam.lab.news.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lab.news.dao.CommentDAO;
import com.epam.lab.news.dao.NewsDAO;
import com.epam.lab.news.entity.Comment;
import com.epam.lab.news.entity.News;
import com.epam.lab.news.exception.NoSuchEntityException;
import com.epam.lab.news.service.CommentService;

/**
 * The Class CommentServiceImpl.
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private NewsDAO newsDAO;

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.service.CommentService#receiveAll()
	 */
	@Override
	@Transactional
	public List<Comment> receiveAll(int offset, int limit, Long idNews) {
		News news = newsDAO.receive(idNews);
		if(news == null){
			throw new NoSuchEntityException(
					messageSource.getMessage("message.no_such_news", null, LocaleContextHolder.getLocale()));
		}
		List<Comment> listComment = commentDAO.receiveAll(offset, limit, news);
		return listComment;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.epam.lab.news.service.CommentService#delete(java.lang.Long)
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		if (!commentDAO.delete(id)) {
			throw new NoSuchEntityException(
					messageSource.getMessage("message.no_such_comment", null, LocaleContextHolder.getLocale()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.epam.lab.news.service.CommentService#add(com.epam.lab.news.entity.
	 * Comment)
	 */
	@Override
	@Transactional
	public Comment add(Comment comment, Long idNews) {
		News news = newsDAO.receive(idNews);
		if (news == null) {
			throw new NoSuchEntityException(
					messageSource.getMessage("message.no_such_news", null, LocaleContextHolder.getLocale()));
		}
		comment.setNews(news);
		comment.setCreationDate(new Timestamp(new Date().getTime()));
		return commentDAO.add(comment);
	}

}
