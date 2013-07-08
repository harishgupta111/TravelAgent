package com.travel.agent.test.rest;

import javax.ws.rs.core.MediaType;

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
import com.travel.agent.model.StateMaster;
import com.travel.agent.model.StateMaster.StateMasterBuilder;
import com.travel.agent.model.enums.RecordCreatorType;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/jerseyApplicationContext-test.xml" })
@Transactional
public class StateMasterRestServiceTest extends SpringAwareJerseyTests {

	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	public StateMasterRestServiceTest() {
		super();
	}

	@Test
	public void testGetAllStates() throws Exception {
		WebResource webResource = resource();
		ClientResponse clientResponse = (ClientResponse) webResource.path(
				"/state/getAll").get(ClientResponse.class);
		Assert.assertNotNull(clientResponse);

		System.out.println("*******************************************");
		System.out.println(clientResponse.getEntity(String.class));
		System.out.println("*******************************************");
		Assert.assertTrue(
				"clientResponse found as " + clientResponse.getStatus(),
				clientResponse.getStatus() == 201);
	}

	@Test
	public void shouldCreate() throws TASystemException {
		WebResource webResource = resource();
		StateMaster stateMaster = new StateMaster();
		StateMasterBuilder smb = stateMaster.new StateMasterBuilder();
		stateMaster = smb.stateCode("NJ").stateName("New Jersey")
				.unionTerritory(false).createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST).buildNew();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		String json = this.hibernateObjectMapper.prepareJSON(mapper,
				stateMaster);

		ClientResponse clientResponse = webResource.path("/state/create")
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

}
