package com.travel.agent.test.service;

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

import com.travel.agent.dao.service.ILocationMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.LocationMaster;
import com.travel.agent.model.LocationMaster.LocationMasterBuilder;
import com.travel.agent.model.enums.RecordCreatorType;

@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class LocationMasterDaoServiceTest {

	@Autowired
	private ILocationMasterDaoService iLocationMasterDaoService;

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void shouldCreate() throws TASystemException {
		LocationMaster lm = new LocationMaster();
		LocationMasterBuilder lmb = lm.new LocationMasterBuilder();
		lm = lmb.locationCode("TEST")
				.locationName("TEST").locationPin("0000").createDate(new Date())
				.createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST).buildNew();
		LocationMaster created = iLocationMasterDaoService.create(lm);
		Assert.assertEquals(created, lm);
	}

	@Test
	public void getAll() throws TASystemException {
		Set<LocationMaster> set = this.iLocationMasterDaoService.getAll();
		Assert.assertEquals(5, set.size());
	}

	@Test
	public void shouldGetByID() throws TASystemException {
		LocationMaster lm = this.iLocationMasterDaoService.getById("1");
		Assert.assertNotNull(lm);
	}

	@Test
	public void shouldGetByCode() throws TASystemException {
		LocationMaster lm = this.iLocationMasterDaoService.getLocationByCode("AK");
		Assert.assertNotNull(lm);
	}

}
