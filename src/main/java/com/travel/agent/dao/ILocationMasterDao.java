package com.travel.agent.dao;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.LocationMaster;

public interface ILocationMasterDao extends IBaseDao<LocationMaster, String> {
	
	public LocationMaster getLocationByCode(String locationCode) throws TASystemException;
	
}
