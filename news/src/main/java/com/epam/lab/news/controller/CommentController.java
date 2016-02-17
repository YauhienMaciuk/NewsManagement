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

import com.epam.lab.news.entity.Comment;
import com.epam.lab.news.service.CommentService;

/**
 * The Class CommentController.
 */
@RestController
public class CommentController {

	/** The comment service. */
	@Autowired
	private CommentService commentService;

	/**
	 * Receive all.
	 *
	 * @return the sets the
	 */
	@RequestMapping(value = "news/{idNews}/comment", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Comment> receiveAll(@PathVariable Long idNews, @RequestParam int offset,
			@RequestParam int limit) {
		return commentService.receiveAll(offset, limit, idNews);
	}

	/**
	 * Delete.
	 *
	 * @param id
	 *            the id
	 */
	@RequestMapping(value = "news/{idNews}/comment/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		commentService.delete(id);
	}

	/**
	 * Adds the.
	 *
	 * @param comment
	 *            the comment
	 */
	@RequestMapping(value = "news/{idNews}/comment", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Comment add(@Valid @RequestBody Comment comment, @PathVariable Long idNews) {
		return commentService.add(comment, idNews);
	}

}
