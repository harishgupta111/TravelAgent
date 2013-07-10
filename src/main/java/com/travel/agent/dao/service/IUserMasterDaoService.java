package com.travel.agent.dao.service;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.UserMaster;
import com.travel.agent.model.enums.UserRole;

public interface IUserMasterDaoService extends IBaseDaoService<UserMaster, String> {

	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws TASystemException 
	 */
	public String getAllJSon() throws JsonParseException, JsonMappingException,
			IOException, TASystemException;
	
	/**
	 * 
	 * @param userRole
	 * @return
	 */
	public Set<UserMaster> getAllUsersByRole(UserRole userRole) throws TASystemException;
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws TASystemException 
	 */
	public UserMaster login(String userName, String password) throws TASystemException;
	
}
