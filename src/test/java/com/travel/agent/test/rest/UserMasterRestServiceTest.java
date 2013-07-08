package com.travel.agent.test.rest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.jackson.mapper.HibernateObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/jerseyApplicationContext-test.xml" })
@Transactional
public class UserMasterRestServiceTest extends SpringAwareJerseyTests {
	
	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	

	public UserMasterRestServiceTest() {
		super();
	}
	
	@Test
	public void shouldCreate()
	{
		Assert.assertEquals(1, 1);
	}

}
