package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IContactUsDao;
import com.travel.agent.dao.service.IContactUsDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ContactUs;

@Transactional(readOnly=true)
@Component("iContactUsDaoService")
public class ContactUsDaoServiceImpl implements IContactUsDaoService {
	
	private static Logger logger = Logger.getLogger(ContactUsDaoServiceImpl.class);

	@Autowired
	private IContactUsDao iContactUsDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public ContactUs create(ContactUs t) throws TASystemException {
		logger.debug("Before persisting object");
		return this.iContactUsDao.createEntity(t);
	}

	@Override
	public Set<ContactUs> getAll() throws TASystemException {
		return this.iContactUsDao.findAll();
	}

	@Override
	public ContactUs getById(String id) throws TASystemException {
		return this.iContactUsDao.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public ContactUs updateEntity(ContactUs t) throws TASystemException {
		return this.iContactUsDao.updateEntity(t);
	}

}
