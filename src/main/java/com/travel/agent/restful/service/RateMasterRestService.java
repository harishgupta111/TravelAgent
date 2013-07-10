package com.travel.agent.restful.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.ws.rs.GET;
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
import com.travel.agent.dao.service.IRateMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;
import com.travel.agent.model.enums.RecordCreatorType;
import com.travel.agent.restful.response.dto.RestResponseCollectionWrapper;
import com.travel.agent.restful.response.dto.RestResponseWrapper;

@Controller
@Path("/rateMaster")
public class RateMasterRestService {

	@Autowired
	private IRateMasterDaoService iRateMasterDaoService;

	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	@GET
	@Path("/getAll/")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response getAll() throws TASystemException {
		Set<RateMaster> set = iRateMasterDaoService.getAll();
		RestResponseCollectionWrapper<RateMaster> restResponseCollectionWrapper = new RestResponseCollectionWrapper.Builder<RateMaster>()
				.collection(set).status(Status.CREATED)
				.build();
		
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		
		String json = this.hibernateObjectMapper.prepareJSON(mapper, set);
		
		return Response.status(restResponseCollectionWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

	@GET
	@Path("/create/{rateType}/{rate}/{effectiveDate}/{origin}/{destination}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response create(@PathParam("rateType") String rateType,
			@PathParam("rate") Double rate,
			@PathParam("effectiveDate") String effectiveDate,
			@PathParam("origin") String origin,
			@PathParam("destination") String destination)
			throws TASystemException, ParseException {

		RateMaster r = new RateMaster();
		RateMaster.RateMasterBuilder rm = r. new RateMasterBuilder(); 
		r = rm.rateType(RateType.valueOf(rateType))
				.rate(rate)
				.effectiveStartDate(sdf.parse(effectiveDate))
				.originLocationCode(origin)
				.destinationLocationCode(destination)
				.activeIndicator(false)
				.effectiveEndDate(sdf.parse("31.12.2099"))
				.createdBy(RecordCreatorType.ADMIN) 
				.updatedBy(RecordCreatorType.ADMIN)
				.buildNew();
		
		r.setRateMasterBuilder(rm);		

		RateMaster created = iRateMasterDaoService.create(r);
		RestResponseWrapper<RateMaster> restResponseWrapper = new RestResponseWrapper.Builder<RateMaster>()
				.data(created).status(Status.CREATED)
				.build();
		
		if(created == null)
		{
			restResponseWrapper = new RestResponseWrapper.Builder<RateMaster>()
					.data(created).status(Status.NOT_ACCEPTABLE)
					.build();
		}
		
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);
		
		return Response.status(restResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

	@GET
	@Path("/byLocationPairAndRateType/{origin}/{destination}/{rateType}/{activeIndicator}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody
	Response findByLocationPairAndRateType(@PathParam("origin") String origin,
			@PathParam("destination") String destination,
			@PathParam("rateType") String rateType,
			@PathParam("activeIndicator") Boolean activeIndicator)
			throws TASystemException, ParseException {

		RateMaster r = iRateMasterDaoService.findByLocationPairAndRateType(
				origin, destination, RateType.valueOf(rateType),
				activeIndicator);
		RestResponseWrapper<RateMaster> restResponseWrapper = new RestResponseWrapper.Builder<RateMaster>()
				.data(r).status(Status.CREATED)
				.build();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				restResponseWrapper);
		return Response.status(restResponseWrapper.getStatus())
				.header("Content-Type", "application/json").entity(json)
				.build();
	}

}
