package com.travel.agent.dao;

import java.util.Date;

import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;

public interface IRateMasterDao extends IBaseDao<RateMaster, String> {

	public RateMaster findByLocationPairAndRateType(String originLocationCode,
			String destinationLocationCode, RateType rateType,
			Boolean activeIndicator);

	public RateMaster findByLocationPairRateTypeAndEffStartDate(
			String originLocationCode, String destinationLocationCode,
			RateType rateType, Date effectiveStartDate);

}
