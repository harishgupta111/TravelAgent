package com.travel.agent.dao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.agent.auth.manager.IAuthenticationManagerService;
import com.travel.agent.auth.manager.IUserAuthenticationDTOService;
import com.travel.agent.dao.IUserMasterDao;
import com.travel.agent.dao.hibernate.impl.BaseDaoHibernateSupport;
import com.travel.agent.dao.service.IUserMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.jackson.mapper.HibernateObjectMapper;
import com.travel.agent.model.UserMaster;
import com.travel.agent.model.enums.UserRole;

@Transactional(readOnly=true)
@Component("iUserMasterDaoService")
public class UserMasterDaoServiceImpl extends BaseDaoHibernateSupport<UserMaster, Long> implements IUserMasterDaoService {
	
	private static Logger logger = Logger.getLogger(UserMasterDaoServiceImpl.class);

	@Autowired
	@Qualifier("iUserMasterDao")
	private IUserMasterDao iUserMasterDao;

	@Autowired
	private HibernateObjectMapper hibernateObjectMapper;
	
	@Autowired
	private IAuthenticationManagerService iAuthenticationManagerService;
	
	@Autowired
	private IUserAuthenticationDTOService iUserAuthenticationDTOService;
	
	/**
	 * 
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=TASystemException.class)
	public UserMaster create(UserMaster user) throws TASystemException {
		logger.debug("Before persisting object");
		return this.iUserMasterDao.createEntity(user);
	}

	/**
	 * @throws TASystemException 
	 * 
	 */
	@Override
	public Set<UserMaster> getAll() throws TASystemException {
		return this.iUserMasterDao.findAll();
	}

	/**
	 * 
	 */
	@Override
	public UserMaster getById(String id) throws TASystemException {
		return this.iUserMasterDao.findById(id);
	}


	@Override
	public String getAllJSon() throws JsonParseException, JsonMappingException,
			IOException, TASystemException {
		Set<UserMaster> set = this.getAll();
		ObjectMapper mapper = this.hibernateObjectMapper.fetchEagerly(false);
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
					set);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		LinkedHashSet<UserMaster> result = mapper
				.readValue(json, LinkedHashSet.class);
		System.out.println(result.size());
		return json;
	}

	@Override
	public Set<UserMaster> getAllUsersByRole(UserRole userRole) throws TASystemException {
		return iUserMasterDao.getAllUsersByRole(userRole);
	}

	@Override
	public UserMaster login(String userName, String password) throws TASystemException {
		boolean boo = false;
		UserMaster userMaster= null; 
	       UsernamePasswordAuthenticationToken token =
			new UsernamePasswordAuthenticationToken(userName, password);

			Authentication auth = this.iAuthenticationManagerService.authenticateToken(token);
			SecurityContext securityContext = new SecurityContextImpl();

			//Places in ThredLocal for future retrieval
			SecurityContextHolder.setContext(securityContext);
			SecurityContextHolder.getContext().setAuthentication(auth);
			boo =  true;
			if(boo){
				logger.info("User Authenticated " + auth.isAuthenticated());
				this.iUserAuthenticationDTOService.setAuthenticate(auth);
				userMaster = loadUserByName(userName);
			}				

		return userMaster;
	}


	@Override
	public UserMaster updateEntity(UserMaster t) throws TASystemException {
		return this.iUserMasterDao.updateEntity(t);
	}
	
	@SuppressWarnings("unchecked")
	public UserMaster loadUserByName(String username) throws TASystemException {
		String strSQL = "Select c from UserMaster c where c.username = :username";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		List<UserMaster> list = null;

		try {
			list = (List<UserMaster>) executeQuery(strSQL, map);
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
