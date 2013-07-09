package com.travel.agent.restful.service;

import java.io.IOException;
import java.util.HashSet;
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
import com.travel.agent.dao.service.IVehicleMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.UserMaster;
import com.travel.agent.model.VehicleMaster;
import com.travel.agent.restful.response.dto.RestResponseCollectionWrapper;
import com.travel.agent.restful.response.dto.RestResponseConstraintVoilationWrapper;
import com.travel.agent.restful.response.dto.RestResponseWrapper;
import com.travel.agent.restful.validation.UserInputValidationService;

@Controller
@Path("/vehicle")
public class VehicleMasterRestService {

	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	@Autowired
	private IVehicleMasterDaoService iVehicleDaoService;

	@Autowired
	private UserInputValidationService<VehicleMaster> userInputValidationService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getById(@PathParam("id") String id) throws TASystemException {
		VehicleMaster vehicle = iVehicleDaoService.getById(id);
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		RestResponseWrapper<VehicleMaster> restResponseWrapper = new RestResponseWrapper.Builder<VehicleMaster>()
				.data(vehicle).status(Status.CREATED).build();

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
		Set<VehicleMaster> set = iVehicleDaoService.getAll();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		RestResponseCollectionWrapper<VehicleMaster> restResponseWrapper = new RestResponseCollectionWrapper.Builder<VehicleMaster>()
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
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response create(String jsonRequest) throws TASystemException {
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		VehicleMaster vm = null;

		try {
			vm = mapper.readValue(jsonRequest, VehicleMaster.class);
		} catch (IOException e) {
			throw new TASystemException(e);
		}

		RestResponseConstraintVoilationWrapper<VehicleMaster> constraintVoilationWrapper = this.userInputValidationService
				.validateInput(vm);
		if (constraintVoilationWrapper != null) {
			String json = this.hibernateObjectMapper.prepareJSON(mapper,
					constraintVoilationWrapper);

			return Response.status(constraintVoilationWrapper.getStatus())
					.header("Content-Type", "application/json").entity(json)
					.build();

		}

		VehicleMaster created = iVehicleDaoService.create(vm);

		RestResponseWrapper<VehicleMaster> restResponseWrapper = new RestResponseWrapper.Builder<VehicleMaster>()
				.data(created).status(Status.CREATED).build();

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);

		return Response.status(restResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}

}
