package com.epam.lab.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lab.news.dao.AuthorDAO;
import com.epam.lab.news.entity.Author;
import com.epam.lab.news.entity.AuthorVO;
import com.epam.lab.news.service.AuthorService;

/**
 * The Class AuthorServiceImpl.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

	/** The author dao. */
	@Autowired
	private AuthorDAO authorDAO;

	/* (non-Javadoc)
	 * @see com.epam.lab.news.service.AuthorService#receiveAllAuthors()
	 */
	@Override
	@Transactional
	public List<AuthorVO> receiveAllAuthors() {
		List<AuthorVO> listAuthors = authorDAO.receiveAll();
		return listAuthors;
	}
	
	/* (non-Javadoc)
	 * @see com.epam.lab.news.service.AuthorService#add(com.epam.lab.news.entity.Author)
	 */
	@Override
	@Transactional
	public Author add(Author author) {
		Author addedAuthor = authorDAO.add(author);
		return addedAuthor;
	}

}
