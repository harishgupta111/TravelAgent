package com.travel.agent.dao;

import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;

public interface IRateMasterDao extends IBaseDao<RateMaster, String> {
	
	public RateMaster findByLocationPairAndRateType(String originLocationCode, String destinationLocationCode, RateType rateType, Boolean activeIndicator);
	
}
