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

import com.epam.lab.news.entity.TagVO;
import com.epam.lab.news.entity.Tag;
import com.epam.lab.news.service.TagService;

/**
 * The Class TagController.
 */
@RestController
@RequestMapping("/tag")
public class TagController {

	/** The tag service. */
	@Autowired
	private TagService tagService;

	/**
	 * Adds the.
	 *
	 * @param tag the tag
	 * @return the tag
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Tag add(@Valid @RequestBody Tag tag) {
		return tagService.add(tag);
	}

	/**
	 * Receive all.
	 *
	 * @return the list
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<TagVO> receiveAll() {
		return tagService.receiveAll();
	}

}
