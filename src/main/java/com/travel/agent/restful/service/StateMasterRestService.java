package com.travel.agent.restful.service;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.dao.service.IStateMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.StateMaster;
import com.travel.agent.restful.response.dto.RestResponseCollectionWrapper;
import com.travel.agent.restful.response.dto.RestResponseConstraintVoilationWrapper;
import com.travel.agent.restful.response.dto.RestResponseWrapper;

@Controller
@Path("/state")
public class StateMasterRestService {
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	@Autowired
	private IStateMasterDaoService iStateMasterDaoService;
	 
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody Response getById(@PathParam("id") String id) throws TASystemException
	{
		StateMaster stateMaster = iStateMasterDaoService.getById(id);
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		
		RestResponseWrapper<StateMaster> restResponseWrapper = new RestResponseWrapper.Builder<StateMaster>()
				.data(stateMaster).result(Status.CREATED.name())
				.resultCode(Status.CREATED.getStatusCode()).build();
		
		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);
		
		return Response.status(restResponseWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}
	
	@GET
	@Path("/locations/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody Response getStateAndLocation(@PathParam("id") String id) throws TASystemException
	{
		StateMaster stateMaster = iStateMasterDaoService.getStateAndLocation(id);
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		
		RestResponseWrapper<StateMaster> restResponseWrapper = new RestResponseWrapper.Builder<StateMaster>()
				.data(stateMaster).result(Status.CREATED.name())
				.resultCode(Status.CREATED.getStatusCode()).build();
		
		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);
		
		return Response.status(restResponseWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody Response getAll() throws TASystemException
	{
		Set<StateMaster> set = iStateMasterDaoService.getAll();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		
		RestResponseCollectionWrapper<StateMaster> restResponseWrapper = new RestResponseCollectionWrapper.Builder<StateMaster>()
				.collection(set).result(Status.CREATED.name())
				.resultCode(Status.CREATED.getStatusCode()).build();
		
		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);
		
		return Response.status(restResponseWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}
	
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody Response create(String jsonRequest) throws TASystemException, JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		StateMaster sm = mapper.readValue(jsonRequest, StateMaster.class);
		Set<ConstraintViolation<StateMaster>> constraintViolations = validator.validate(sm);
		
		if (constraintViolations != null
				&& constraintViolations.size() > 0) {

			RestResponseConstraintVoilationWrapper<StateMaster> constraintVoilationWrapper = new RestResponseConstraintVoilationWrapper.Builder<StateMaster>()
					.collection(constraintViolations)
					.result(Status.NOT_ACCEPTABLE.name())
					.resultCode(Status.CREATED.getStatusCode()).build();

			String json = this.hibernateObjectMapper.prepareJSON(mapper,
					constraintVoilationWrapper);

			return Response
					.status(constraintVoilationWrapper.getResultCode())
					.header("Content-Type", "application/json")
					.entity(json).build();

		}
		
		StateMaster created = iStateMasterDaoService.create(sm);
		
		RestResponseWrapper<StateMaster> restResponseWrapper = new RestResponseWrapper.Builder<StateMaster>()
				.data(created).result(Status.CREATED.name())
				.resultCode(Status.CREATED.getStatusCode()).build();
		
		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);
		
		return Response.status(restResponseWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}

}
