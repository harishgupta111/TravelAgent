package com.travel.agent.restful.service;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.enums.ContactUsMessageType;
import com.travel.agent.model.enums.RateType;
import com.travel.agent.model.enums.RecordCreatorType;
import com.travel.agent.model.enums.VehicleType;

@Controller
@Path("/enum")
public class EnumRestService {
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	private static Logger logger = Logger
			.getLogger(EnumRestService.class);
	
	@GET
	@Path("/rateType/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getAllRateType() throws TASystemException, JsonProcessingException {
		Map<Integer, RateType> map = RateType.returnMap();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		logger.debug("before setting " + json);
		return Response.status(Status.CREATED.getStatusCode())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}
	
	@GET
	@Path("/contactUsMessageType/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getAllContactUsMessageType() throws TASystemException, JsonProcessingException {
		Map<Integer, ContactUsMessageType> map = ContactUsMessageType.returnMap();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		logger.debug("before setting " + json);
		return Response.status(Status.CREATED)
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

	
	@GET
	@Path("/vehicleType/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getAllVehicleType() throws TASystemException, JsonProcessingException {
		Map<Integer, VehicleType> map = VehicleType.returnMap();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		logger.debug("before setting " + json);
		return Response.status(Status.CREATED)
				.header("Content-Type", "application/json").entity(json)
				.build();
	}
	
	@GET
	@Path("/recordCreatorType/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getAllRecordCreatorType() throws TASystemException, JsonProcessingException {
		Map<Integer, RecordCreatorType> map = RecordCreatorType.returnMap();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		logger.debug("before setting " + json);
		return Response.status(Status.CREATED)
				.header("Content-Type", "application/json").entity(json)
				.build();
	}
}
