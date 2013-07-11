package com.travel.agent.dao;

import com.travel.agent.model.StateMaster;

public interface IStateMasterDao extends IBaseDao<StateMaster, String> {
	
	public StateMaster getStateAndLocation(String id);

}
