package com.travel.agent.dao.service.impl;

import java.util.Date;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IRateMasterDao;
import com.travel.agent.dao.service.IRateMasterDaoService;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.RateMaster;
import com.travel.agent.model.enums.RateType;
import com.travel.agent.model.enums.RecordCreatorType;

@Transactional(readOnly = true)
@Component("iRateMasterDaoService")
public class RateMasterDaoServiceImpl implements IRateMasterDaoService {

	private static Logger logger = Logger
			.getLogger(RateMasterDaoServiceImpl.class);

	@Autowired
	private IRateMasterDao iRateMasterDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public RateMaster create(RateMaster t) throws TASystemException {
		RateMaster existing = this.findByLocationPairAndRateType(
				t.getOriginLocationCode(), t.getDestinationLocationCode(),
				t.getRateType(), Boolean.TRUE);
		if (existing != null && !existing.getRate().equals(t.getRate())) {
			RateMaster.RateMasterBuilder rm = existing.new RateMasterBuilder(
					existing);
			existing = rm.activeIndicator(Boolean.FALSE)
					.effectiveEndDate(new Date())
					.updatedBy(RecordCreatorType.ADMIN).update();
			this.iRateMasterDao.updateEntity(existing);
			logger.debug("end dated the entity with ID "
					+ existing.getRateMasterID());
			RateMaster created = this.iRateMasterDao.createEntity(t);
			logger.debug("Created new with ID " + created.getRateMasterID());
			return created;
		} else if (existing == null) {
			RateMaster created = this.iRateMasterDao.createEntity(t);
			logger.debug("Created new with ID " + created.getRateMasterID());
			return created;
		} else {
			logger.debug("Same Rate found for origin "
					+ t.getOriginLocationCode() + " destination "
					+ t.getDestinationLocationCode() + " rate type "
					+ t.getRateType()
					+ ". No need to create new rate in this case.");
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
	public RateMaster updateEntity(RateMaster t) throws TASystemException {
		return this.iRateMasterDao.updateEntity(t);
	}

}
