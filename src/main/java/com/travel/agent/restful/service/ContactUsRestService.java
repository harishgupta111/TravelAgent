package com.travel.agent.restful.service;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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

@Controller
@Path("/contactUs")
public class ContactUsRestService {
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	@Autowired
	private IContactUsDaoService iContactUsDaoService;
	 
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@POST
	@Path("/submit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody Response submit(String jsonRequest) throws TASystemException
	{
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		ContactUs contactUs = null;
		ContactUs created = null; 
		try {
			contactUs = mapper.readValue(jsonRequest, ContactUs.class);
			contactUs.setContactUsId(UUID.randomUUID().toString());
			Set<ConstraintViolation<ContactUs>> constraintViolations = validator.validate(contactUs);
			
			if (constraintViolations != null
					&& constraintViolations.size() > 0) {

				RestResponseConstraintVoilationWrapper<ContactUs> constraintVoilationWrapper = new RestResponseConstraintVoilationWrapper.Builder<ContactUs>()
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
			else
				created = this.iContactUsDaoService.create(contactUs);
			
		} catch (JsonParseException e) {
			throw new TASystemException(e);
		} catch (JsonMappingException e) {
			throw new TASystemException(e);
		} catch (IOException e) {
			throw new TASystemException(e);
		};
		
		RestResponseWrapper<ContactUs> restResponseWrapper = new RestResponseWrapper.Builder<ContactUs>()
				.data(created).result(Status.CREATED.name())
				.resultCode(Status.CREATED.getStatusCode()).build();
		
		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);
		
		return Response.status(restResponseWrapper.getResultCode())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}

}
