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

import com.travel.agent.dao.service.IItineraryDetailDaoService;
import com.travel.agent.dao.service.IItineraryMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ItineraryDetail;
import com.travel.agent.model.ItineraryDetail.ItineraryDetailBuilder;
import com.travel.agent.model.enums.RecordCreatorType;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class ItineraryDetailDaoServiceTest {

	@Autowired
	private IItineraryDetailDaoService iItineraryDetailDaoService;

	@Autowired
	private IItineraryMasterDaoService iItineraryMasterDaoService;

	@Test
	public void getAll() throws TASystemException {
		Set<ItineraryDetail> set = this.iItineraryDetailDaoService.getAll();
		Assert.assertEquals(4, set.size());
	}

	@Test
	public void getByID() throws TASystemException {
		ItineraryDetail im = this.iItineraryDetailDaoService.getById("1");
		Assert.assertEquals("AK", im.getFromLocationCode());
	}

	@Test
	@Rollback(value = true)
	@Transactional(propagation = Propagation.REQUIRED)
	public void shouldCreate() throws TASystemException {
		ItineraryDetail c = new ItineraryDetail();
		ItineraryDetailBuilder cb = c.new ItineraryDetailBuilder();
		c = cb.createDate(new Date())
				.createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST)
				.fromLocationCode("AK")
				.toLocationCode("BAS")
				.itinerarySeqId(1)
				.minEstimatedTime(120)
				.minStopTime(15)
				.scheduleStartTime(new Date())
				.scheduleEndTime(
						new Date(
								new Long(new Date().getTime()) + (2 * 60 * 60 * 1000)))
				.itineraryMaster(this.iItineraryMasterDaoService.getById("1"))
				.buildNew();
		ItineraryDetail created = iItineraryDetailDaoService.create(c);
		Assert.assertEquals(created, c);
	}

	@Test
	@Rollback(value = true)
	@Transactional(propagation = Propagation.REQUIRED)
	public void shouldCreateUpdate() throws TASystemException {
		ItineraryDetail c = this.iItineraryDetailDaoService.getById("1");
		ItineraryDetailBuilder cb = c.new ItineraryDetailBuilder(c);
		c = cb.minEstimatedTime(180)
				.update();
		ItineraryDetail updated = iItineraryDetailDaoService.updateEntity(c);
		Assert.assertEquals(180, updated.getMinEstimatedTime().intValue());
	}

}
