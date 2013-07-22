package com.travel.agent.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.service.IRateMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;

@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class RateMasterDaoServiceTest {

	@Autowired
	private IRateMasterDaoService iRateMasterDaoService;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	@Test
	public void getAll() throws TASystemException {
		Set<RateMaster> set = this.iRateMasterDaoService.getAll();
		Assert.assertEquals(2, set.size());
	}

	@Test
	public void shouldGetByLocationPairAndRateType() {

		RateMaster r = this.iRateMasterDaoService.findByLocationPairAndRateType("LKO",
				"NDLS", RateType.FROM_TO, true);
		Assert.assertEquals(r.getRate(), 200.00);
	}
	
	@Test
	public void shouldFindByLocationPairRateTypeAndEffStartDate() throws ParseException
	{

		RateMaster r = this.iRateMasterDaoService.findByLocationPairRateTypeAndEffStartDate("LKO",
				"NDLS", RateType.FROM_TO, sdf.parse("01.06.2013"));
		Assert.assertEquals(r.getRate(), 200.00);
	}

}
