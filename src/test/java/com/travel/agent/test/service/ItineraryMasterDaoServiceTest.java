package com.travel.agent.test.service;

import java.util.Date;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.service.IItineraryMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ItineraryMaster;
import com.travel.agent.model.ItineraryMaster.ItineraryMasterBuilder;
import com.travel.agent.model.enums.RecordCreatorType;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class ItineraryMasterDaoServiceTest {

	@Autowired
	private IItineraryMasterDaoService iItineraryMasterDaoService;

	@Test
	public void getAll() throws TASystemException {
		Set<ItineraryMaster> set = this.iItineraryMasterDaoService.getAll();
		Assert.assertEquals(2, set.size());
	}

	@Test
	public void getByID() throws TASystemException {
		ItineraryMaster im = this.iItineraryMasterDaoService.getById("1");
		Assert.assertEquals("AK", im.getDestinationLocationCode());
	}

	@Test
	@Rollback(value = true)
	@Transactional(propagation = Propagation.REQUIRED)
	public void shouldCreate() throws TASystemException {
		ItineraryMaster c = new ItineraryMaster();
		ItineraryMasterBuilder cb = c.new ItineraryMasterBuilder();
		c = cb.createDate(new Date()).createdBy(RecordCreatorType.TEST)
				.dayOfWeek(1).destinationLocationCode("AK").nonStopStatus(true)
				.originLocationCode("BAS")
				.updatedBy(RecordCreatorType.TEST).buildNew();
		ItineraryMaster created = iItineraryMasterDaoService.create(c);
		Assert.assertEquals(created, c);
	}
	
	@Test
	@Rollback(value = true)
	@Transactional(propagation = Propagation.REQUIRED)
	public void shouldCreateUpdate() throws TASystemException {
		ItineraryMaster c = this.iItineraryMasterDaoService.getById("1");
		ItineraryMasterBuilder cb = c.new ItineraryMasterBuilder(c);
		c = cb.destinationLocationCode("BAS")
				.updatedBy(RecordCreatorType.TEST).update();
		ItineraryMaster updated = iItineraryMasterDaoService.updateEntity(c);
		Assert.assertEquals("BAS", updated.getDestinationLocationCode());
	}

}
