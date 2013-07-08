package com.travel.agent.restful.service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.travel.agent.dao.service.IBookingDaoService;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;

@Controller
@Path("/booking")
public class BookingRestService {
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	@Autowired
	private IBookingDaoService iBookingDaoService;
	 
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	

}
