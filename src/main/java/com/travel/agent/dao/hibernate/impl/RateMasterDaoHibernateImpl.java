package com.travel.agent.dao.hibernate.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IRateMasterDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;

@Transactional(readOnly = true)
@Repository("iRateMasterDao")
public class RateMasterDaoHibernateImpl extends
		BaseDaoHibernateSupport<RateMaster, String> implements IRateMasterDao {

	private static Logger logger = Logger
			.getLogger(RateMasterDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.ta_rateMaster", "entity.ta_rateMaster" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public RateMaster createEntity(RateMaster rateMaster) {
		RateMaster created = null;
		try {
			rateMaster.setRateMasterID(UUID.randomUUID().toString());
			created = this.insert(rateMaster, true);
			logger.debug("created entity with ID"
					+ rateMaster.getRateMasterID());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;
	}

	@Override
	@Cacheable(value = "entity.ta_rateMaster", key = "#root.methodName")
	public Set<RateMaster> findAll() {
		String strSQL = "Select c from RateMaster c";
		@SuppressWarnings("unchecked")
		Set<RateMaster> set = new LinkedHashSet<RateMaster>(
				new LinkedList<RateMaster>(
						(List<RateMaster>) (this.executeQuery(strSQL))));
		return set;
	}

	@Override
	@Cacheable(value = "entity.ta_rateMaster")
	public RateMaster findById(String id) {
		String strSQL = "Select c from RateMaster c where c.rateMasterId = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		@SuppressWarnings("unchecked")
		List<RateMaster> list = (List<RateMaster>) this.executeQuery(strSQL,
				map);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	@Cacheable(value = "entity.ta_rateMaster")
	public RateMaster findByLocationPairAndRateType(String originLocationCode,
			String destinationLocationCode, RateType rateType,
			Boolean activeIndicator) {

		String strSQL = "Select c from RateMaster c where c.originLocationCode = :originLocationCode and c.destinationLocationCode = :destinationLocationCode and c.rateType = :rateType and c.activeIndicator = :activeIndicator";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("originLocationCode", originLocationCode);
		map.put("destinationLocationCode", destinationLocationCode);
		map.put("rateType", rateType);
		map.put("activeIndicator", activeIndicator);

		@SuppressWarnings("unchecked")
		List<RateMaster> list = (List<RateMaster>) this.executeQuery(strSQL,
				map);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	@Override
	public RateMaster updateEntity(RateMaster t) throws TASystemException {
		return super.update(t, true);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value = "entity.ta_rateMaster")
	public RateMaster findByLocationPairRateTypeAndEffStartDate(
			String originLocationCode, String destinationLocationCode,
			RateType rateType, Date effectiveStartDate) {
		String strSQL = "Select c from RateMaster c where c.originLocationCode = :originLocationCode and c.destinationLocationCode = :destinationLocationCode and c.rateType = :rateType and c.effectiveStartDate = :effectiveStartDate";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("originLocationCode", originLocationCode);
		map.put("destinationLocationCode", destinationLocationCode);
		map.put("rateType", rateType);
		map.put("effectiveStartDate", effectiveStartDate);
		
		List<RateMaster> list = null;
		try {
			list = (List<RateMaster>) this.executeQuery(strSQL, map);
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	@CacheEvict(value = { "entity.ta_rateMaster", "entity.ta_rateMaster" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public Boolean updateEligibleRates() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date now = sdf.parse(sdf.format((new Date())));
		String strSQL = "Select c from RateMaster c where c.effectiveStartDate = :effectiveStartDate and c.activeIndicator = :activeIndicator";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("effectiveStartDate", now);
		map.put("activeIndicator", false);
		Boolean status = false;
		try {
			@SuppressWarnings("unchecked")
			List<RateMaster> list = (List<RateMaster>) this.executeQuery(strSQL, map);
			if (list != null && list.size() > 0) {
				for(RateMaster newRate : list)
				{
					// find active rate for same orig, destination, ratetype and active = y to be updated
					RateMaster oldRate = findByLocationPairAndRateType(newRate.getOriginLocationCode(), newRate.getDestinationLocationCode(), newRate.getRateType(), true);
					newRate.setActiveIndicator(true);
					oldRate.setActiveIndicator(false);
					oldRate.setUpdateDate(new Date());
					this.update(oldRate, true);
					this.update(newRate, true);
					status = true;
				}
			}	
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		
		return status;
	} 

}
