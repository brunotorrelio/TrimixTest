package com.trimix.testbackend.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class HibernateAwareObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7742121560645502179L;

	public HibernateAwareObjectMapper() {
		registerModule(new Hibernate5Module());
		registerModule(new JavaTimeModule());
		registerModule(new ParameterNamesModule());
		registerModule(new Jdk8Module());
	}
}
