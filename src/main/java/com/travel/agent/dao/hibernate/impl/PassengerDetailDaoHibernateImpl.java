package com.travel.agent.dao.hibernate.impl;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agent.dao.IPassengerDetailDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.PassengerDetail;

@Transactional(readOnly=true)
@Repository("iPassengerDetailDao")
public class PassengerDetailDaoHibernateImpl extends
		BaseDaoHibernateSupport<PassengerDetail, String> implements
		IPassengerDetailDao {
	
	private static Logger logger = Logger.getLogger(PassengerDetailDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.ta_passengerDetail", "entity.ta_passengerDetail" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public PassengerDetail createEntity(PassengerDetail passengerDetail) throws TASystemException {
		PassengerDetail created = null;
		try{
			created = this.insert(passengerDetail, true);
			logger.debug("created entity with ID" + passengerDetail.getPassengerDetailID());
		}
		catch(HibernateException e)
		{
			throw new TASystemException(e);
		}
		return created;
	}

	@Override
	@Cacheable(value = "entity.ta_rateMaster")
	public Set<PassengerDetail> findAll() {
		String strSQL = "Select c from PassengerDetail c";
		@SuppressWarnings("unchecked")
		Set<PassengerDetail> set = new LinkedHashSet<PassengerDetail>(
				new LinkedList<PassengerDetail>(
						(List<PassengerDetail>) (this.executeQuery(strSQL))));
		return set;
	}

	@Override
	@Cacheable(value = "entity.ta_passengerDetail")
	public PassengerDetail findById(String id) {
		String strSQL = "Select c from PassengerDetail c where c.rateMasterId = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		@SuppressWarnings("unchecked")
		List<PassengerDetail> list = (List<PassengerDetail>) this.executeQuery(
				strSQL, map);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	@CacheEvict(value = { "entity.ta_passengerDetail", "entity.ta_passengerDetail" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public PassengerDetail updateEntity(PassengerDetail t)
			throws TASystemException {
		return super.update(t, true);
	}


}
