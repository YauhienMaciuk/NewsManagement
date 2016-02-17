package com.epam.lab.news.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.lab.news.entity.News;
import com.epam.lab.news.service.NewsService;

/**
 * The Class NewsController.
 */
@RestController
@RequestMapping("/news")
public class NewsController {

	/** The news service. */
	@Autowired
	private NewsService newsService;

	/**
	 * Search news.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public News receive(@PathVariable Long id) {
		return newsService.receive(id);
	}

	/**
	 * Adds the news.
	 *
	 * @param news
	 *            the news
	 * @return the response entity
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public News add(@Valid @RequestBody News news) {
		return newsService.add(news);
	}

	/**
	 * Delete news.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		newsService.delete(id);
	}

	/**
	 * Search list news.
	 *
	 * @return the response entity
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<News> receiveList(@RequestParam int offset, @RequestParam int limit,
			@RequestParam String conditionSearch, @RequestParam(required = false) Long idAuthor,
			@RequestParam(required = false) String idTag) {
		switch (conditionSearch) {
		case "page":
			return newsService.receiveList(offset, limit);
		case "author":
			return newsService.receiveListByAuthor(offset, limit, idAuthor);
		case "tag":
			return newsService.receiveListByTag(offset, limit, idTag);
		case "byComment":
			return newsService.receiveNewsByTopComment(offset, limit);
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Update news.
	 *
	 * @param news
	 *            the news
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public News update(@Valid @RequestBody News news, @PathVariable Long id) {
		return newsService.update(news);
	}
}