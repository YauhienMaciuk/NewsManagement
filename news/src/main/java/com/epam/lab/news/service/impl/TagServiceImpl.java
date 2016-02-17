package com.epam.lab.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.lab.news.dao.TagDAO;
import com.epam.lab.news.entity.TagVO;
import com.epam.lab.news.entity.Tag;
import com.epam.lab.news.service.TagService;

/**
 * The Class TagServiceImpl.
 */
@Service
public class TagServiceImpl implements TagService {

	/** The tag dao. */
	@Autowired
	private TagDAO tagDAO;

	/* (non-Javadoc)
	 * @see com.epam.lab.news.service.TagService#add(com.epam.lab.news.entity.Tag)
	 */
	@Override
	@Transactional
	public Tag add(Tag tag) {
		Tag addedTag = tagDAO.add(tag);
		return addedTag;
	}

	/* (non-Javadoc)
	 * @see com.epam.lab.news.service.TagService#receiveAll()
	 */
	@Override
	@Transactional
	public List<TagVO> receiveAll() {
		List<TagVO> listTags = tagDAO.receiveAll();
		return listTags;
	}

}
