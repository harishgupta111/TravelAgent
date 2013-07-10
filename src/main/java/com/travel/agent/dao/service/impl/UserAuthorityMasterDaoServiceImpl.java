package com.travel.agent.dao.service.impl;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IUserAuthorityMasterDao;
import com.travel.agent.dao.service.IUserAuthorityMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.UserAuthorityMaster;
import com.travel.agent.model.UserMaster;

@Transactional(readOnly=true)
@Component("iUserAuthorityMasterDaoService")
public class UserAuthorityMasterDaoServiceImpl  implements IUserAuthorityMasterDaoService {
	
	private static Logger logger = Logger.getLogger(UserAuthorityMasterDaoServiceImpl.class);

	@Autowired
	private IUserAuthorityMasterDao iUserAuthorityMasterDao;

	@Override
	public UserAuthorityMaster create(UserAuthorityMaster t)
			throws TASystemException {
		logger.debug("Before persisting object");
		return this.iUserAuthorityMasterDao.createEntity(t);
	}

	@Override
	public Set<UserAuthorityMaster> getAll() throws TASystemException {
		return this.iUserAuthorityMasterDao.findAll();
	}

	@Override
	public UserAuthorityMaster getById(String id) throws TASystemException {
		return this.iUserAuthorityMasterDao.findById(id);
	}

	@Override
	public UserAuthorityMaster updateEntity(UserAuthorityMaster t)
			throws TASystemException {
		return this.iUserAuthorityMasterDao.updateEntity(t);
	}

	@Override
	public Set<UserAuthorityMaster> findByUserMaster(UserMaster userMaster)
			throws TASystemException {
		return this.iUserAuthorityMasterDao.findByUserMaster(userMaster);
	}

}
