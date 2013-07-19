package com.travel.agent.dao;

import java.util.Set;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ItineraryMaster;

public interface IItineraryMasterDao extends IBaseDao<ItineraryMaster, String> {
	
	public int createUsingSet(Set<ItineraryMaster> set) throws TASystemException;

}
