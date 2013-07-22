package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IJourneyMasterDao;
import com.travel.agent.dao.service.IJourneyMasterService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.JourneyMaster;

@Transactional(readOnly=true)
@Component("iJourneyMasterService")
public class JourneyMasterServiceImpl implements IJourneyMasterService {
	
	private static Logger logger = Logger.getLogger(JourneyMasterServiceImpl.class);

	@Autowired
	private IJourneyMasterDao iJourneyMasterDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public JourneyMaster create(JourneyMaster t) throws TASystemException {
		logger.debug("Before persisting object");
		return this.iJourneyMasterDao.createEntity(t);

	}

	@Override
	public Set<JourneyMaster> getAll() throws TASystemException {
		return this.iJourneyMasterDao.findAll();
	}

	@Override
	public JourneyMaster getById(String id) throws TASystemException {
		return this.iJourneyMasterDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public JourneyMaster updateEntity(JourneyMaster t) throws TASystemException {
		return this.iJourneyMasterDao.updateEntity(t);
	}

}
