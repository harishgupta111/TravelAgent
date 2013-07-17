package com.travel.agent.test.dao;

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

import com.travel.agent.dao.IAvailableVehicleDao;
import com.travel.agent.dao.IVehicleMasterDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.AvailableVehicle;
import com.travel.agent.model.AvailableVehicle.AvailableVehicleBuilder;
import com.travel.agent.model.enums.RecordCreatorType;

@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class AvailableVehicleDaoTest {

	@Autowired
	private IAvailableVehicleDao iAvailableVehicleDao;

	@Autowired
	private IVehicleMasterDao iVehicleMasterDao;

	@Test
	public void shouldGetAll() throws TASystemException {
		Set<AvailableVehicle> set = this.iAvailableVehicleDao.findAll();
		Assert.assertEquals(2, set.size());
	}
	
	@Test
	public void shouldFindByID() throws TASystemException {
		AvailableVehicle a = this.iAvailableVehicleDao.findById("1");
		Assert.assertSame(a.getAvailableVehicleCount(), 3);
	}

	@Test
	@Rollback(value = true)
	@Transactional(propagation = Propagation.REQUIRED)
	public void shouldCreate() throws TASystemException {
		AvailableVehicle c = new AvailableVehicle();
		AvailableVehicleBuilder cb = c.new AvailableVehicleBuilder();
		c = cb.activeIndicator(true).availableVehicleCount(3)
				.dateOfRunning(new Date())
				.vehicleMaster(iVehicleMasterDao.findById("1"))
				.createdBy(RecordCreatorType.TEST)
				.updatedBy(RecordCreatorType.TEST).buildNew();
		AvailableVehicle created = iAvailableVehicleDao.createEntity(c);
		Assert.assertEquals(created, c);
	}

}
