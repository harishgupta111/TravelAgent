package com.travel.agent.test.service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

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

import com.travel.agent.dao.service.IVehicleMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.VehicleMaster;
import com.travel.agent.model.VehicleMaster.VehicleMasterBuilder;
import com.travel.agent.model.enums.RecordCreatorType;
import com.travel.agent.model.enums.VehicleType;

@Transactional(readOnly = true)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class VehicleMasterDaoServiceTest {

	@Autowired
	private IVehicleMasterDaoService iVehicleMasterDaoService;

	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void shouldCreate() throws TASystemException {
		VehicleMaster vm = new VehicleMaster();
		VehicleMasterBuilder vmb = vm.new VehicleMasterBuilder();
		vm = vmb
				.createDate(new Date()).make("Tata").modelName("Indica")
				.modelYear(2012).noOfSeats(4)
				.plateNumber("ABC 1788").vehicleCount(6)
				.vehicleMasterID(UUID.randomUUID().toString())
				.vehicleType(VehicleType.CAR).updatedBy(RecordCreatorType.TEST)
				.createdBy(RecordCreatorType.TEST).createDate(new Date())
				.buildNew();
		VehicleMaster created = iVehicleMasterDaoService.create(vm);
		Assert.assertNotNull(created);
	}

	@Test
	public void getAllVehicles() throws TASystemException {
		Set<VehicleMaster> set = iVehicleMasterDaoService.getAll();
		Assert.assertEquals(4, set.size());
	}

	@Test
	public void getByID() throws TASystemException {
		VehicleMaster vm = iVehicleMasterDaoService.getById("abc");
		Assert.assertNull(vm);
	}

}
