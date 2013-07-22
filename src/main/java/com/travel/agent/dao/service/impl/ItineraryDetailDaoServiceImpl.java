package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IItineraryDetailDao;
import com.travel.agent.dao.service.IItineraryDetailDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ItineraryDetail;

@Transactional(readOnly=true)
@Component("iItineraryDetailDaoService")
public class ItineraryDetailDaoServiceImpl implements
		IItineraryDetailDaoService {
	
	private static Logger logger = Logger.getLogger(ContactUsDaoServiceImpl.class);
	
	@Autowired
	private IItineraryDetailDao iItineraryDetailDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public ItineraryDetail create(ItineraryDetail t) throws TASystemException {
		logger.debug("Before persisting object");
		return this.iItineraryDetailDao.createEntity(t);
	}

	@Override
	public Set<ItineraryDetail> getAll() throws TASystemException {
		return this.iItineraryDetailDao.findAll();
	}

	@Override
	public ItineraryDetail getById(String id) throws TASystemException {
		return this.iItineraryDetailDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public ItineraryDetail updateEntity(ItineraryDetail t)
			throws TASystemException {
		return this.iItineraryDetailDao.updateEntity(t);
	}

}
