package com.travel.agent.dao.service;

import java.util.Set;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.UserAuthorityMaster;
import com.travel.agent.model.UserMaster;

public interface IUserAuthorityMasterDaoService extends
		IBaseDaoService<UserAuthorityMaster, String> {
	
	public Set<UserAuthorityMaster> findByUserMaster(UserMaster userMaster) throws TASystemException;

}
