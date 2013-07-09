package com.travel.agent.test.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

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
public class VehicleMasterRestServiceTest  extends SpringAwareJerseyTests {
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	public VehicleMasterRestServiceTest() {
		super();
	}
	

	@Test
	public void testGetAll() throws Exception {
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
	}
	
	@Test
	public void shouldGetByID() throws Exception {
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
	}
	
	@Test
	public void shouldCreate() throws Exception, TASystemException {
		WebResource webResource = resource();
		VehicleMaster vehicleMaster = new VehicleMaster();
		VehicleMasterBuilder vmb = vehicleMaster.new VehicleMasterBuilder();
		vehicleMaster = vmb.vehicleType(VehicleType.BUS).make("Maruti")
						.modelName("A-Star").modelYear(2010).noOfSeats(5)
						.plateNumber("JK-21 2419").vehicleCount(1).availableVehicleCount(1)
						.bookingSet(null).createdBy(RecordCreatorType.TEST)
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
	}
	
	@Test
	public void shouldNotCreate() throws Exception, TASystemException {
		WebResource webResource = resource();
		VehicleMaster vehicleMaster = new VehicleMaster();
		VehicleMasterBuilder vmb = vehicleMaster.new VehicleMasterBuilder();
		vehicleMaster = vmb
						.bookingSet(null).createdBy(RecordCreatorType.TEST)
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
				clientResponse.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode());
	
	
	}


}
