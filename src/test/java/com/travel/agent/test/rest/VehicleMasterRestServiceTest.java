package com.travel.agent.test.rest;

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

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;


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


}
