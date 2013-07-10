package com.travel.agent.test.rest;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import junit.framework.Assert;

import org.apache.log4j.Logger;
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
import com.travel.agent.model.UserMaster;
import com.travel.agent.model.UserMaster.UserMasterBuilder;
import com.travel.agent.model.enums.RecordCreatorType;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/jerseyApplicationContext-test.xml" })
@Transactional
public class UserMasterRestServiceTest extends SpringAwareJerseyTests {
	
	private static Logger logger = Logger.getLogger(UserMasterRestServiceTest.class);

	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;

	public UserMasterRestServiceTest() {
		super();
	}

	@Test
	public void shouldCreate() throws Exception, TASystemException {
		logger.info("Entering shouldCreate");
		WebResource webResource = resource();
		UserMaster um = new UserMaster();
		UserMasterBuilder umb = um.new UserMasterBuilder();
		um = umb.accountNonExpired(true).accountNonLocked(true)
				.createDate(new Date()).credentialsNonExpired(true)
				.email("user1@user.com").enabled(true).password("p123")
				.userId("u123").username("User123").name("My Test User")
				.createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST).buildNew();
		
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);

		String json = this.hibernateObjectMapper.prepareJSON(mapper, um);

		ClientResponse clientResponse = webResource.path("/user/create")
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
	public void shouldLogin() throws Exception {
		logger.info("Entering shouldLogin");
		WebResource webResource = resource();
		ClientResponse clientResponse = (ClientResponse) webResource.path(
				"/user/login/testUsername1/password").get(ClientResponse.class);
		Assert.assertNotNull(clientResponse);

		System.out.println("*******************************************");
		System.out.println(clientResponse.getEntity(String.class));
		System.out.println("*******************************************");
		Assert.assertTrue(
				"clientResponse found as " + clientResponse.getStatus(),
				clientResponse.getStatus() == 201);
		logger.info("Closing shouldLogin");
	}
	
	@Test
	public void shouldNotLogin_WrongPassword() throws Exception {
		logger.info("Entering shouldNotLogin_WrongPassword");
		WebResource webResource = resource();
		ClientResponse clientResponse = (ClientResponse) webResource.path(
				"/user/login/testUsername1/password1").get(ClientResponse.class);
		Assert.assertNotNull(clientResponse);

		System.out.println("*******************************************");
		System.out.println(clientResponse.getEntity(String.class));
		System.out.println("*******************************************");
		Assert.assertTrue(
				"clientResponse found as " + clientResponse.getStatus(),
				clientResponse.getStatus() == 401);
		logger.info("Closing shouldNotLogin_WrongPassword");
	}

	@Test
	public void shouldNotLogin_NonExistingUser() throws Exception {
		logger.info("Entering shouldNotLogin_NonExistingUser");
		WebResource webResource = resource();
		ClientResponse clientResponse = (ClientResponse) webResource.path(
				"/user/login/testUsername/password").get(ClientResponse.class);
		Assert.assertNotNull(clientResponse);

		System.out.println("*******************************************");
		System.out.println(clientResponse.getEntity(String.class));
		System.out.println("*******************************************");
		Assert.assertTrue(
				"clientResponse found as " + clientResponse.getStatus(),
				clientResponse.getStatus() == 401);
		logger.info("Closing shouldNotLogin_NonExistingUser");
	}
	
	

}
