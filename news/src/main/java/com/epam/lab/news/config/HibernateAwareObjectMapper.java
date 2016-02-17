package com.epam.lab.news.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * The Class HibernateAwareObjectMapper.
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4480450788425721946L;

	/**
	 * Instantiates a new hibernate aware object mapper .
	 */
	public HibernateAwareObjectMapper() {
	    Hibernate4Module hm = new Hibernate4Module();
	    registerModule(hm);  
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    dateFormat.setTimeZone(TimeZone.getDefault());
	    setDateFormat(dateFormat);
    }
	
}