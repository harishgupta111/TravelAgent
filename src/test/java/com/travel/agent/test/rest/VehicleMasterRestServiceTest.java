package com.travel.agent.test.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.VehicleMaster;
import com.travel.agent.model.VehicleMaster.VehicleMasterBuilder;
import com.travel.agent.model.enums.RecordCreatorType;
import com.travel.agent.model.enums.VehicleType;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/jerseyApplicationContext-test.xml" })
@Transactional
public class VehicleMasterRestServiceTest extends SpringAwareJerseyTests {

	private static Logger logger = Logger.getLogger(VehicleMasterRestServiceTest.class);
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	public VehicleMasterRestServiceTest() {
		super();
	}

	@Test
	public void testGetAll() {
		logger.info("Entering testGetAll");
		WebResource webResource = resource();
		ClientResponse clientResponse = (ClientResponse) webResource.path(
				"/vehicle/getAll").get(ClientResponse.class);
		Assert.assertNotNull(clientResponse);

		System.out.println("*******************************************");
		System.out.println(clientResponse.getEntity(String.class));
		System.out.println("*******************************************");
		Assert.assertTrue(
				"clientResponse found as " + clientResponse.getStatus(),
				clientResponse.getStatus() == 201);
		logger.info("Closing testGetAll");
	}

	@Test
	public void shouldGetByID() throws Exception {
		logger.info("Entering ");
		WebResource webResource = resource();
		ClientResponse clientResponse = (ClientResponse) webResource.path(
				"/vehicle/1").get(ClientResponse.class);
		Assert.assertNotNull(clientResponse);

		System.out.println("*******************************************");
		System.out.println(clientResponse.getEntity(String.class));
		System.out.println("*******************************************");
		Assert.assertTrue(
				"clientResponse found as " + clientResponse.getStatus(),
				clientResponse.getStatus() == 201);
		logger.info("Closing shouldGetByID");
	}

	@Test
	public void shouldCreate() throws Exception, TASystemException {
		logger.info("Entering shouldCreate");
		WebResource webResource = resource();
		VehicleMaster vehicleMaster = new VehicleMaster();
		VehicleMasterBuilder vmb = vehicleMaster.new VehicleMasterBuilder();
		vehicleMaster = vmb.vehicleType(VehicleType.BUS).make("Maruti")
				.modelName("A-Star").modelYear(2010).noOfSeats(4)
				.plateNumber("JK-21 2419").vehicleCount(1)
				.createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST).buildNew();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				vehicleMaster);

		ClientResponse clientResponse = webResource.path("/vehicle/create")
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, json);

		Assert.assertNotNull(clientResponse);

		System.out.println("*******************************************");
		System.out.println(clientResponse.getEntity(String.class));
		System.out.println("*******************************************");
		Assert.assertTrue(
				"clientResponse found as " + clientResponse.getStatus(),
				clientResponse.getStatus() == 201);
		logger.info("Closing shouldCreate");
	}

	@Test
	public void shouldNotCreate() throws Exception, TASystemException {
		logger.info("Entering shouldNotCreate");
		WebResource webResource = resource();
		VehicleMaster vehicleMaster = new VehicleMaster();
		VehicleMasterBuilder vmb = vehicleMaster.new VehicleMasterBuilder();
		vehicleMaster = vmb.createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST).buildNew();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				vehicleMaster);

		ClientResponse clientResponse = webResource.path("/vehicle/create")
				.accept(MediaType.APPLICATION_JSON)
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, json);

		Assert.assertNotNull(clientResponse);

		System.out.println("*******************************************");
		System.out.println(clientResponse.getEntity(String.class));
		System.out.println("*******************************************");
		Assert.assertTrue(
				"clientResponse found as " + clientResponse.getStatus(),
				clientResponse.getStatus() == Status.NOT_ACCEPTABLE
						.getStatusCode());
		logger.info("Closing shouldNotCreate");
	}

}
