package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IVehicleMasterDao;
import com.travel.agent.dao.service.IVehicleMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.VehicleMaster;

@Transactional(readOnly=true)
@Component("iVehicleMasterDaoService")
public class VehicleMasterDaoServiceImpl implements IVehicleMasterDaoService {
	
	private static Logger logger = Logger.getLogger(VehicleMasterDaoServiceImpl.class);
		
	@Autowired
	private IVehicleMasterDao iVehicleMasterDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public VehicleMaster create(VehicleMaster t) throws TASystemException {
		logger.debug("Before creating object");
		return this.iVehicleMasterDao.createEntity(t);
	}

	@Override
	public Set<VehicleMaster> getAll() throws TASystemException {
		return this.iVehicleMasterDao.findAll();
	}

	@Override
	public VehicleMaster getById(String id) throws TASystemException {
		return this.iVehicleMasterDao.findById(id);
	}

	@Override
	public VehicleMaster updateEntity(VehicleMaster t) throws TASystemException {
		return this.iVehicleMasterDao.updateEntity(t);
	}



}
