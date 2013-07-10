package com.travel.agent.restful.service;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.dao.service.IContactUsDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.ContactUs;
import com.travel.agent.restful.response.dto.RestResponseConstraintVoilationWrapper;
import com.travel.agent.restful.response.dto.RestResponseWrapper;
import com.travel.agent.restful.validation.IUserInputValidationService;

@Controller
@Path("/contactUs")
public class ContactUsRestService {

	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	@Autowired
	private IContactUsDaoService iContactUsDaoService;

	@Autowired
	private IUserInputValidationService<ContactUs> userInputValidationService;

	@POST
	@Path("/submit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response submit(String jsonRequest) throws TASystemException {
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		ContactUs contactUs = null;
		try {
			contactUs = mapper.readValue(jsonRequest, ContactUs.class);
		} catch (JsonParseException e) {
			throw new TASystemException(e);
		} catch (JsonMappingException e) {
			throw new TASystemException(e);
		} catch (IOException e) {
			throw new TASystemException(e);
		}

		RestResponseConstraintVoilationWrapper<ContactUs> constraintVoilationWrapper = userInputValidationService
				.validateInput(contactUs);

		if (constraintVoilationWrapper != null) {
			String json = this.hibernateObjectMapper.prepareJSON(mapper,
					constraintVoilationWrapper);

			return Response.status(constraintVoilationWrapper.getStatus())
					.header("Content-Type", "application/json").entity(json)
					.build();

		}
		
		ContactUs created = this.iContactUsDaoService.create(contactUs);

		RestResponseWrapper<ContactUs> restResponseWrapper = new RestResponseWrapper.Builder<ContactUs>()
				.data(created).status(Status.CREATED).build();

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);

		return Response.status(restResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}

}
