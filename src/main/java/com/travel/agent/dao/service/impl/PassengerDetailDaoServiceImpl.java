package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IPassengerDetailDao;
import com.travel.agent.dao.service.IPassengerDetailDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.PassengerDetail;

@Transactional(readOnly=true)
@Component("iPassengerDetailDaoService")
public class PassengerDetailDaoServiceImpl implements IPassengerDetailDaoService {
	
	private static Logger logger = Logger.getLogger(PassengerDetailDaoServiceImpl.class);
		
	@Autowired
	private IPassengerDetailDao iPassengerDetailDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public PassengerDetail create(PassengerDetail t) throws TASystemException {
		logger.debug("Before persisting object");
		return this.iPassengerDetailDao.createEntity(t);
	}

	@Override
	public Set<PassengerDetail> getAll() throws TASystemException {
		return this.iPassengerDetailDao.findAll();
	}

	@Override
	public PassengerDetail getById(String id) throws TASystemException {
		return this.iPassengerDetailDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public PassengerDetail updateEntity(PassengerDetail t)
			throws TASystemException {
		return this.iPassengerDetailDao.updateEntity(t);
	}

}
