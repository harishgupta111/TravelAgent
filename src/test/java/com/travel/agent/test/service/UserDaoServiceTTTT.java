package com.travel.agent.test.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class UserDaoServiceTTTT 
{

//	Logger logger = Logger.getLogger(UserDaoServiceTest.class);
//
//	@Autowired
//	private IUserDaoService iUserDaoService;
//
//	@Test
//	public void shouldGetById() throws TASystemException {
//		logger.debug("STARTING shouldGetById()");
//		Assert.assertNotNull(this.iUserDaoService.getById("1"));
//		logger.debug("BEFORE RETURNING FROM shouldGetById()");
//	}
//
//	@Test
//	public void shouldGetByUsername() throws TASystemException {
//		logger.debug("STARTING shouldGetByUsername()");
//		UserDetails userDetails = this.iUserDaoService
//				.loadUserByUsername("user1");
//		Assert.assertNotNull(userDetails);
//		logger.debug("BEFORE RETURNING FROM shouldGetByUsername()");
//	}

}
