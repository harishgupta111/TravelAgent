package com.travel.agent.test.service;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.travel.agent.dao.service.IJourneyMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.JourneyMaster;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class JourneyMasterDaoServiceTest {
	
	@Autowired
	private IJourneyMasterDaoService iJourneyMasterDaoService;
	
	@Test
	public void getAll() throws TASystemException {
		Set<JourneyMaster> set = this.iJourneyMasterDaoService.getAll();
		Assert.assertEquals(1, set.size());
	}


}
