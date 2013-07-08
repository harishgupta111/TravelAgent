package com.travel.agent.jackson.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.exception.TASystemException;

public interface HibernateObjectMapper {

	public ObjectMapper fetchEagerly(boolean forceLazyLoading);
	
	public String prepareJSON(ObjectMapper objectMapper, Object data) throws TASystemException;
}
