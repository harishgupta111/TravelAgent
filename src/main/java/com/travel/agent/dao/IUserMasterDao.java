package com.travel.agent.dao;

import java.util.Set;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.UserMaster;
import com.travel.agent.model.enums.UserRole;

public interface IUserMasterDao extends IBaseDao<UserMaster, String> {
	
	public UserMaster loadUserByName(String name) throws TASystemException;

	public Set<UserMaster> getAllUsersByRole(UserRole userRole) throws TASystemException;

}
