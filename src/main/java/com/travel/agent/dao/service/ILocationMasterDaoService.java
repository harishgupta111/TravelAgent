package com.travel.agent.dao.service;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.LocationMaster;

public interface ILocationMasterDaoService extends IBaseDaoService<LocationMaster, String> {
	
	public LocationMaster getLocationByCode(String locationCode) throws TASystemException;

}
