package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IItineraryMasterDao;
import com.travel.agent.dao.service.IItineraryDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ItineraryMaster;

@Transactional(readOnly=true)
@Component("iItineraryDaoService")
public class ItineraryDaoServiceImpl implements IItineraryDaoService {
	
	private static Logger logger = Logger.getLogger(ItineraryDaoServiceImpl.class);

	@Autowired
	private IItineraryMasterDao iItineraryMasterDao;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public ItineraryMaster create(ItineraryMaster t) throws TASystemException {
		logger.debug("before creating entity");
		return this.iItineraryMasterDao.createEntity(t);
	}

	@Override
	public Set<ItineraryMaster> getAll() throws TASystemException {
		return this.iItineraryMasterDao.findAll();
	}

	@Override
	public ItineraryMaster getById(String id) throws TASystemException {
		return this.iItineraryMasterDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public ItineraryMaster updateEntity(ItineraryMaster t)
			throws TASystemException {
		return this.iItineraryMasterDao.updateEntity(t);
	}

}
