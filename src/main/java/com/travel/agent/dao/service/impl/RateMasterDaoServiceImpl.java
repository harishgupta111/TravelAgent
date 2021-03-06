package com.travel.agent.dao.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IRateMasterDao;
import com.travel.agent.dao.service.IRateMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;

@Transactional(readOnly = true)
@Component("iRateMasterDaoService")
@EnableScheduling
public class RateMasterDaoServiceImpl implements IRateMasterDaoService {

	private static Logger logger = Logger
			.getLogger(RateMasterDaoServiceImpl.class);

	@Autowired
	private IRateMasterDao iRateMasterDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public RateMaster create(RateMaster newRate) throws TASystemException {
		if (newRate.getEffectiveStartDate().after(new Date())) {

			RateMaster created = this.iRateMasterDao.createEntity(newRate);
			logger.debug("Created new with ID " + created.getRateMasterID());
			return created;
		} else {
			logger.debug("Back dated rate setup not permissible.");
			return null;
		}

	}

	@Override
	public Set<RateMaster> getAll() throws TASystemException {
		return this.iRateMasterDao.findAll();
	}

	@Override
	public RateMaster getById(String id) throws TASystemException {
		return this.iRateMasterDao.findById(id);
	}

	@Override
	public RateMaster findByLocationPairAndRateType(String originLocationCode,
			String destinationLocationCode, RateType rateType,
			Boolean activeIndicator) {

		return this.iRateMasterDao.findByLocationPairAndRateType(
				originLocationCode, destinationLocationCode, rateType,
				activeIndicator);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public RateMaster updateEntity(RateMaster t) throws TASystemException {
		return this.iRateMasterDao.updateEntity(t);
	}

	@Override
	public RateMaster findByLocationPairRateTypeAndEffStartDate(
			String originLocationCode, String destinationLocationCode,
			RateType rateType, Date effectiveStartDate) {

		return this.iRateMasterDao.findByLocationPairRateTypeAndEffStartDate(
				originLocationCode, destinationLocationCode, rateType,
				effectiveStartDate);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	@Scheduled(cron = "0 55 20 * * ?")
	public void updateEligibleRates() throws ParseException {
		this.iRateMasterDao.updateEligibleRates();
	}

}
