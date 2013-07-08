package com.travel.agent.restful.service;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.dao.service.IUserMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.UserMaster;
import com.travel.agent.restful.response.dto.RestResponseConstraintVoilationWrapper;
import com.travel.agent.restful.response.dto.RestResponseWrapper;


@Controller
@Path("/users")
public class UserMasterRestService implements InitializingBean {
	
	@Autowired
	private IUserMasterDaoService iUserMasterDaoService;
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	private static Validator validator = Validation
	.buildDefaultValidatorFactory().getValidator();
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response createUser(String jsonRequest) throws TASystemException {
		
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		
		UserMaster user = null;
		
		try {
			user = mapper.readValue(jsonRequest, UserMaster.class);
			user.setUserId(UUID.randomUUID().toString());
			Set<ConstraintViolation<UserMaster>> constraintViolationsSet = validator
					.validate(user);

			if (constraintViolationsSet != null
					&& constraintViolationsSet.size() > 0) {

				RestResponseConstraintVoilationWrapper<UserMaster> constraintVoilationWrapper = new RestResponseConstraintVoilationWrapper.Builder<UserMaster>()
						.collection(constraintViolationsSet)
						.result(Status.NOT_ACCEPTABLE.name())
						.resultCode(Status.CREATED.getStatusCode()).build();

				String json = this.hibernateObjectMapper.prepareJSON(mapper,
						constraintVoilationWrapper);

				return Response
						.status(constraintVoilationWrapper.getResultCode())
						.header("Content-Type", "application/json")
						.entity(json).build();

			}

		} catch (JsonParseException e) {
			throw new TASystemException(e);
		} catch (JsonMappingException e) {
			throw new TASystemException(e);
		} catch (IOException e) {
			throw new TASystemException(e);
		}
		
		UserMaster created = this.iUserMasterDaoService.create(user);
		
		RestResponseWrapper<UserMaster> restResponseWrapper = new RestResponseWrapper.Builder<UserMaster>()
				.data(created).result(Status.CREATED.name())
				.resultCode(Status.CREATED.getStatusCode()).build();
		
		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);
		
		return Response.status(restResponseWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();
		
	}
	
	@GET
	@Path("/login/{userName}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody Response login(@PathParam("userName") String userName,
			@PathParam("password") String password) throws TASystemException 
	{
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		UserMaster userMaster = this.iUserMasterDaoService.login(userName, password);
		RestResponseWrapper<UserMaster> restResponseWrapper = new RestResponseWrapper.Builder<UserMaster>()
				.data(userMaster).result(Status.CREATED.name())
				.resultCode(Status.CREATED.getStatusCode()).build();
		
		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);
		
		return Response.status(restResponseWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.iUserMasterDaoService);
		Assert.notNull(this.hibernateObjectMapper);
	}

}
