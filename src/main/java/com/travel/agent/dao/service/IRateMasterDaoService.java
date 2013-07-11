package com.travel.agent.dao.service;

import java.text.ParseException;
import java.util.Date;

import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;

/**
 * 
 * @author AbhinavN
 *
 */
public interface IRateMasterDaoService extends
		IBaseDaoService<RateMaster, String> {

	/**
	 * 
	 * @param originLocationCode
	 * @param destinationLocationCode
	 * @param rateType
	 * @param activeIndicator
	 * @return
	 */
	public RateMaster findByLocationPairAndRateType(String originLocationCode,
			String destinationLocationCode, RateType rateType,
			Boolean activeIndicator);

	/**
	 * 
	 * @param originLocationCode
	 * @param destinationLocationCode
	 * @param rateType
	 * @param effectiveStartDate
	 * @return
	 */
	public RateMaster findByLocationPairRateTypeAndEffStartDate(
			String originLocationCode, String destinationLocationCode,
			RateType rateType, Date effectiveStartDate);

	/**
	 * 
	 * @return
	 * @throws ParseException
	 */
	public void updateEligibleRates() throws ParseException;
}