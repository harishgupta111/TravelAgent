package com.travel.agent.dao.service;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.StateMaster;

public interface IStateMasterDaoService extends IBaseDaoService<StateMaster, String> {
	
	public StateMaster getStateAndLocation(String id) throws TASystemException;

}
