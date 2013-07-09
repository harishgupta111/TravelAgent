package com.travel.agent.test.dao.service;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.service.IUserMasterDaoService;
import com.travel.agent.model.UserMaster;

@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class UserMasterDaoServiceTest {
	
	@Autowired
	private IUserMasterDaoService iUserMasterDaoService;
	
	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void shoudlLogin()
	{
		String user = "testUsername1";
		String password = "password";
		
		UserMaster u = this.iUserMasterDaoService.login(user, password);
		Assert.assertNotNull(user);
		Assert.assertNotSame(password, u.getPassword());
	}

}
