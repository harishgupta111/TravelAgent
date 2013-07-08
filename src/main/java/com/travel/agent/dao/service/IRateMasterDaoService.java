package com.travel.agent.dao.service;

import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;

public interface IRateMasterDaoService extends
		IBaseDaoService<RateMaster, String> {
	
	public RateMaster findByLocationPairAndRateType(String originLocationCode, String destinationLocationCode, RateType rateType, Boolean activeIndicator);
}