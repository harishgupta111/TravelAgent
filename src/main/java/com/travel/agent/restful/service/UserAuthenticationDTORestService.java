package com.travel.agent.restful.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.auth.UserAuthenticationDTO;
import com.travel.agent.auth.manager.IUserAuthenticationDTOService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.restful.response.dto.RestResponseWrapper;


@Component
@Path("/auth")
public class UserAuthenticationDTORestService {
	
	@Autowired
	private IUserAuthenticationDTOService iUserAuthenticationDTOService;
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	@GET
	@Path("/getStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getStatus() throws TASystemException {
		UserAuthenticationDTO authDTO = iUserAuthenticationDTOService.getAuthenticationDTO();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		RestResponseWrapper<Authentication> restResponseWrapper = new RestResponseWrapper.Builder<Authentication>()
				.data(authDTO.getAuthentication()).status(Status.CREATED).build();

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);

		return Response.status(restResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();

	}

}
