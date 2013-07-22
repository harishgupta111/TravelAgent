package com.travel.agent.dao.service;

import java.util.Set;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ItineraryMaster;

public interface IItineraryMasterDaoService extends IBaseDaoService<ItineraryMaster, String> {
	
	public int createUsingSet(Set<ItineraryMaster> set) throws TASystemException;

}
