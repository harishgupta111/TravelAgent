package com.travel.agent.restful.exception.mapper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.restful.response.dto.RestResponseExceptionWrapper;

@Provider
@Component
public class TASystemExceptionMapper implements
		ExceptionMapper<TASystemException> {
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public  @ResponseBody Response toResponse(TASystemException arg0) {
		
		RestResponseExceptionWrapper<TASystemException> restResponseExceptionWrapper = new RestResponseExceptionWrapper.Builder<TASystemException>().exception(arg0)
		.result(arg0.getMessage()).resultCode(Status.INTERNAL_SERVER_ERROR
				.getStatusCode()).build();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		String json = null;
		try {
			json = this.hibernateObjectMapper.prepareJSON(mapper, restResponseExceptionWrapper);
		} catch (TASystemException e) {
			e.printStackTrace();
		}
		return Response.status(restResponseExceptionWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

}
