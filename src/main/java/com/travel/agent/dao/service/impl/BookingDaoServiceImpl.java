package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IBookingDao;
import com.travel.agent.dao.service.IBookingDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.Booking;

@Transactional(readOnly=true)
@Component("iBookingDaoService")
public class BookingDaoServiceImpl implements IBookingDaoService {
	
	private static Logger logger = Logger.getLogger(BookingDaoServiceImpl.class);

	@Autowired
	private IBookingDao iBookingDao;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public Booking create(Booking t) throws TASystemException {
		logger.debug("Before persisting object");
		return this.iBookingDao.createEntity(t);
	}

	@Override
	public Set<Booking> getAll() throws TASystemException {
		return this.iBookingDao.findAll();
	}

	@Override
	public Booking getById(String id) throws TASystemException {
		return this.iBookingDao.findById(id);
	}

	@Override
	public Booking updateEntity(Booking t) throws TASystemException {
		return this.iBookingDao.updateEntity(t);
	}

}
