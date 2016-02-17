package com.epam.lab.news.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.lab.news.entity.Author;
import com.epam.lab.news.entity.AuthorVO;
import com.epam.lab.news.service.AuthorService;

/**
 * The Class AuthorController.
 */
@RestController
@RequestMapping("/author")
public class AuthorController {

	/** The author service. */
	@Autowired
	private AuthorService authorService;

	/**
	 * Search all authors.
	 *
	 * @return the list
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<AuthorVO> searchAllAuthors() {
		return authorService.receiveAllAuthors();
	}
	
	/**
	 * Adds the.
	 *
	 * @param author
	 *            the author
	 * @return the author
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Author add(@Valid @RequestBody Author author) {
		return authorService.add(author);
	}

}
