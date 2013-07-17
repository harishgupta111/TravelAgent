package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IStateMasterDao;
import com.travel.agent.dao.service.IStateMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.StateMaster;

@Transactional(readOnly=true)
@Component("iStateMasterDaoService")
public class StateMasterDaoServiceImpl implements IStateMasterDaoService {
	
	@Autowired
	private IStateMasterDao iStateMasterDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public StateMaster create(StateMaster t) throws TASystemException {
		return this.iStateMasterDao.createEntity(t);
	}

	@Override
	public Set<StateMaster> getAll() throws TASystemException {
		return this.iStateMasterDao.findAll();
	}

	@Override
	public StateMaster getById(String id) throws TASystemException {
		return this.iStateMasterDao.findById(id);
	}

	@Override
	public StateMaster getStateAndLocation(String id)
			throws TASystemException {
		return this.iStateMasterDao.getStateAndLocation(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	public StateMaster updateEntity(StateMaster t) throws TASystemException {
		return this.iStateMasterDao.updateEntity(t);
	}

}
