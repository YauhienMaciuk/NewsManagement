package com.epam.lab.news.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import com.epam.lab.news.dao.AuthorDAO;
import com.epam.lab.news.dao.NewsDAO;
import com.epam.lab.news.dao.TagDAO;
import com.epam.lab.news.entity.Author;
import com.epam.lab.news.entity.News;
import com.epam.lab.news.entity.Tag;
import com.epam.lab.news.exception.NoSuchEntityException;

/**
 * The Class NewsServiceImplTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

	/** The news service. */
	@InjectMocks
	private NewsServiceImpl newsService = new NewsServiceImpl();

	/** The message source. */
	@Mock
	private MessageSource messageSource;

	/** The news dao. */
	@Mock
	private NewsDAO newsDAO;

	@Mock
	private AuthorDAO authorDAO;

	@Mock
	private TagDAO tagDAO;

	/** The list news. */
	private List<News> listNews = new ArrayList<News>();

	/** The offset. */
	private int offset = 0;

	/** The limit. */
	private int limit = 20;

	/**
	 * Test add.
	 *
	 */
	@Test
	public void testAdd() {
		News news = new News();
		news.setId(1000L);
		when(newsDAO.add(news)).thenReturn(news);
		News actualNews = newsService.add(news);
		verify(newsDAO).add(news);
		assertNotNull(actualNews);
		assertNotNull(actualNews.getId());
		assertNotNull(actualNews.getCreationTime());
		assertNotNull(actualNews.getModificationTime());
	}

	/**
	 * Test update.
	 *
	 */
	@Test
	public void testUpdate() {
		News news = new News();
		news.setId(1000L);
		when(newsDAO.update(news)).thenReturn(news);
		when(newsDAO.receive(news.getId())).thenReturn(news);
		News actualNews = newsService.update(news);
		verify(newsDAO).update(news);
		assertNotNull(actualNews);
		assertNotNull(actualNews.getId());
		assertNotNull(actualNews.getModificationTime());
	}

	/**
	 * Test update news with null id.
	 *
	 */
	@Test(expected = NoSuchEntityException.class)
	public void testUpdateNewsWithNullId() {
		News news = new News();
		when(newsDAO.update(news)).thenReturn(null);
		newsService.update(news);
		verify(newsDAO).update(news);
	}

	/**
	 * Test delete unexisting.
	 */
	@Test(expected = NoSuchEntityException.class)
	public void testDeleteUnexisting() {
		Long newsId = 0l;
		when(newsDAO.delete(newsId)).thenReturn(false);
		newsService.delete(newsId);
		verify(newsDAO).delete(newsId);
	}

	/**
	 * Test receive.
	 */
	@Test
	public void testReceive() {
		News news = new News();
		Set<Author> listAuthors = new HashSet<Author>();
		Set<Tag> listTags = new HashSet<Tag>();
		news.setListAuthors(listAuthors);
		news.setListTags(listTags);
		news.setId(1L);
		Long newsId = news.getId();
		when(newsDAO.receive(newsId)).thenReturn(news);
		assertEquals(news, newsService.receive(newsId));
		verify(newsDAO).receive(newsId);
	}

	/**
	 * Test failed receive.
	 */
	@Test(expected = NoSuchEntityException.class)
	public void testFailedReceive() {
		Long newsId = 0L;
		when(newsDAO.receive(newsId)).thenReturn(null);
		newsService.receive(newsId);
		verify(newsDAO).receive(newsId);
	}

	/**
	 * Test receive list.
	 */
	@Test
	public void testReceiveList() {
		when(newsDAO.receiveList(offset, limit)).thenReturn(listNews);
		assertEquals(listNews, newsService.receiveList(offset, limit));
		verify(newsDAO).receiveList(offset, limit);
	}

	/**
	 * Test receive news by top comment.
	 */
	@Test
	public void testReceiveNewsByTopComment() {
		when(newsDAO.receiveNewsByTopComment(offset, limit)).thenReturn(listNews);
		assertEquals(listNews, newsService.receiveNewsByTopComment(offset, limit));
		verify(newsDAO).receiveNewsByTopComment(offset, limit);
	}

	/**
	 * Test receive list by author.
	 * 
	 */
	@Test
	public void testReceiveListByAuthor() {
		Long idAuthor = 1000L;
		when(newsDAO.receiveListByAuthor(offset, limit, idAuthor)).thenReturn(listNews);
		assertEquals(listNews, newsService.receiveListByAuthor(offset, limit, idAuthor));
		verify(newsDAO).receiveListByAuthor(offset, limit, idAuthor);
	}

	/**
	 * Test receive list by tag.
	 * 
	 */
	@Test
	public void testReceiveListByTag() {
		String idTag = "World";
		when(newsDAO.receiveListByTag(offset, limit, idTag)).thenReturn(listNews);
		assertEquals(listNews, newsService.receiveListByTag(offset, limit, idTag));
		verify(newsDAO).receiveListByTag(offset, limit, idTag);
	}

	/**
	 * Reset data.
	 *
	 */
	@After
	public void resetData() throws Exception {
		reset(newsDAO, messageSource, authorDAO, tagDAO);
	}

}
