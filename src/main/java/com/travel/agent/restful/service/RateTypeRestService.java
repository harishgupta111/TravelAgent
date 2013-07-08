package com.travel.agent.restful.service;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.enums.RateType;

@Controller
@Path("/rateType")
public class RateTypeRestService {
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	@GET
	@Path("/getAll/")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getAll() throws TASystemException, JsonProcessingException {
		Map<Integer, RateType> map = RateType.returnMap();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		return Response.status(Status.CREATED.getStatusCode())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}


}
