package com.travel.agent.restful.service;

import java.io.IOException;
import java.util.Set;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.dao.service.ILocationMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.LocationMaster;
import com.travel.agent.restful.response.dto.RestResponseCollectionWrapper;
import com.travel.agent.restful.response.dto.RestResponseConstraintVoilationWrapper;
import com.travel.agent.restful.response.dto.RestResponseWrapper;
import com.travel.agent.restful.validation.UserInputValidationService;

@Controller
@Path("/location")
public class LocationMasterRestService {

	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	@Autowired
	private ILocationMasterDaoService iLocationMasterDaoService;

	@Autowired
	private UserInputValidationService<LocationMaster> userInputValidationService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getById(@PathParam("id") String id) throws TASystemException {
		LocationMaster locationMaster = iLocationMasterDaoService.getById(id);
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		RestResponseWrapper<LocationMaster> restResponseWrapper = new RestResponseWrapper.Builder<LocationMaster>()
				.data(locationMaster).status(Status.CREATED).build();

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);

		return Response.status(restResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}

	@GET
	@Path("/code/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getLocationByCode(@PathParam("code") String locationCode)
			throws TASystemException {
		LocationMaster locationMaster = iLocationMasterDaoService
				.getLocationByCode(locationCode);
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		RestResponseWrapper<LocationMaster> restResponseWrapper = new RestResponseWrapper.Builder<LocationMaster>()
				.data(locationMaster).status(Status.CREATED).build();

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);

		return Response.status(restResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getAll() throws TASystemException {
		Set<LocationMaster> set = iLocationMasterDaoService.getAll();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		RestResponseCollectionWrapper<LocationMaster> restResponseWrapper = new RestResponseCollectionWrapper.Builder<LocationMaster>()
				.collection(set).status(Status.CREATED).build();

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);

		return Response.status(restResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response create(String jsonRequest) throws TASystemException {
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		LocationMaster lm = null;
		try {
			lm = mapper.readValue(jsonRequest, LocationMaster.class);
		} catch (IOException e) {
			throw new TASystemException(e);
		}

		RestResponseConstraintVoilationWrapper<LocationMaster> constraintVoilationWrapper = this.userInputValidationService
				.validateInput(lm);

		if (constraintVoilationWrapper != null) {

			String json = this.hibernateObjectMapper.prepareJSON(mapper,
					constraintVoilationWrapper);

			return Response.status(constraintVoilationWrapper.getStatus())
					.header("Content-Type", "application/json").entity(json)
					.build();

		}

		LocationMaster created = iLocationMasterDaoService.create(lm);

		RestResponseWrapper<LocationMaster> restResponseWrapper = new RestResponseWrapper.Builder<LocationMaster>()
				.data(created).status(Status.CREATED).build();

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);

		return Response.status(restResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}

}
