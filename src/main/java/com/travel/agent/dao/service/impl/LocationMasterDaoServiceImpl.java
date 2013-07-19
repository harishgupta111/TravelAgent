package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.ILocationMasterDao;
import com.travel.agent.dao.service.ILocationMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.LocationMaster;

@Transactional(readOnly=true)
@Component("iLocationMasterDaoService")
public class LocationMasterDaoServiceImpl implements ILocationMasterDaoService {
	
	private static Logger logger = Logger.getLogger(LocationMasterDaoServiceImpl.class);
	
	@Autowired
	private ILocationMasterDao iLocationMasterDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public LocationMaster create(LocationMaster t) throws TASystemException {
		logger.debug("Before persisting object");
		return this.iLocationMasterDao.createEntity(t);
	}

	@Override
	public Set<LocationMaster> getAll() throws TASystemException {
		return this.iLocationMasterDao.findAll();
	}

	@Override
	public LocationMaster getById(String id) throws TASystemException {
		return this.iLocationMasterDao.findById(id);
	}

	@Override
	public LocationMaster getLocationByCode(String locationCode)
			throws TASystemException {
		return this.iLocationMasterDao.getLocationByCode(locationCode);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public LocationMaster updateEntity(LocationMaster t)
			throws TASystemException {
		return this.iLocationMasterDao.updateEntity(t);
	}

}
