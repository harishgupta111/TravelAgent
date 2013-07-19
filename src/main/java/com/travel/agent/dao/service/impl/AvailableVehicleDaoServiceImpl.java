package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IAvailableVehicleDao;
import com.travel.agent.dao.service.IAvailableVehicleDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.AvailableVehicle;

@Transactional(readOnly=true)
@Component("iAvailableVehicleDaoService")
public class AvailableVehicleDaoServiceImpl implements IAvailableVehicleDaoService {
	
	private static Logger logger = Logger.getLogger(AvailableVehicleDaoServiceImpl.class);
		
	@Autowired
	private IAvailableVehicleDao iAvailableVehicleDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public AvailableVehicle create(AvailableVehicle t) throws TASystemException {
		logger.debug("Before creating object");
		return this.iAvailableVehicleDao.createEntity(t);
	}

	@Override
	public Set<AvailableVehicle> getAll() throws TASystemException {
		return this.iAvailableVehicleDao.findAll();
	}

	@Override
	public AvailableVehicle getById(String id) throws TASystemException {
		return this.iAvailableVehicleDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public AvailableVehicle updateEntity(AvailableVehicle t) throws TASystemException {
		return this.iAvailableVehicleDao.updateEntity(t);
	}



}
