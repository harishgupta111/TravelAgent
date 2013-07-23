package com.travel.agent.dao.service;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.JourneyMaster;

public interface IJourneyMasterDaoService  extends IBaseDaoService<JourneyMaster, String> {
	
	public void createUsingItineraryMaster() throws TASystemException;

}
