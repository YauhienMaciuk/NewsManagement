package com.epam.lab.news.service;

import java.util.List;

import com.epam.lab.news.entity.TagVO;
import com.epam.lab.news.entity.Tag;

public interface TagService {

	public Tag add(Tag tag);

	public List<TagVO> receiveAll();
	
}
