package com.travel.agent.jackson.mapper.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;

@Component("hibernateObjectMapper")
public class HibernateObjectMapperImpl implements HibernateObjectMapper {

	public ObjectMapper fetchEagerly(boolean forceLazyLoading) {
		ObjectMapper mapper = new ObjectMapper();
		Hibernate4Module mod = new Hibernate4Module();
		mod.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING,
				forceLazyLoading);
		mapper.registerModule(mod);
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		mapper.setDateFormat(df);
		return mapper;
	}

	@Override
	public String prepareJSON(ObjectMapper objectMapper, Object data)
			throws TASystemException {
		String json = null;
		try {
			json = objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(data);
		} catch (JsonProcessingException jpe) {
			throw new TASystemException(jpe.getMessage());
		}

		return json;
	}
}
