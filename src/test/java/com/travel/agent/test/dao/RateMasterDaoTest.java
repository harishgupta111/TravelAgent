package com.travel.agent.test.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IRateMasterDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;
import com.travel.agent.model.enums.RecordCreatorType;

@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class RateMasterDaoTest {

	@Autowired
	private IRateMasterDao iRateMasterDao;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void shouldCreate() throws TASystemException {
		RateMaster rateMaster = new RateMaster();
		RateMaster.RateMasterBuilder rm = rateMaster.new RateMasterBuilder();
		rateMaster = rm.activeIndicator(true).destinationLocationCode("Jammu")
				.effectiveStartDate(new Date())
				.originLocationCode("VaishnoDevi").rate(250.00)
				.rateType(RateType.FROM_TO).createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST).buildNew();

		RateMaster created = iRateMasterDao.createEntity(rateMaster);
		Assert.assertEquals(created, rateMaster);
	}

	@Test
	public void getAll() throws TASystemException {
		Set<RateMaster> set = this.iRateMasterDao.findAll();
		Assert.assertEquals(2, set.size());
	}

	@Test
	public void shouldGetByLocationPairAndRateType() {

		RateMaster r = this.iRateMasterDao.findByLocationPairAndRateType("LKO",
				"NDLS", RateType.FROM_TO, true);
		Assert.assertEquals(r.getRate(), 200.00);
	}
	
	@Test
	public void shouldFindByLocationPairRateTypeAndEffStartDate() throws ParseException
	{

		RateMaster r = this.iRateMasterDao.findByLocationPairRateTypeAndEffStartDate("LKO",
				"NDLS", RateType.FROM_TO, sdf.parse("01.06.2013"), true);
		Assert.assertEquals(r.getRate(), 200.00);
	}

}
