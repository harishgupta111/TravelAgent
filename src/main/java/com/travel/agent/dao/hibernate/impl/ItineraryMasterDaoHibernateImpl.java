package com.travel.agent.dao.hibernate.impl;

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

import com.travel.agent.dao.IItineraryMasterDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ItineraryMaster;

@Transactional(readOnly = true)
@Repository("iItineraryMasterDao")
public class ItineraryMasterDaoHibernateImpl extends
		BaseDaoHibernateSupport<ItineraryMaster, String> implements
		IItineraryMasterDao {

	private static Logger logger = Logger
			.getLogger(LocationDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.ta_ItineraryMaster",
			"entity.ta_ItineraryMaster" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public ItineraryMaster createEntity(ItineraryMaster t)
			throws TASystemException {
		ItineraryMaster created = null;
		try {
			t.setItineraryMasterID(UUID.randomUUID().toString());
			created = this.insert(t, true);
			logger.debug("created entity with ID" + t.getItineraryMasterID());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;
	}

	@Override
	@CacheEvict(value = { "entity.ta_ItineraryMaster",
			"entity.ta_ItineraryMaster" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public int createUsingSet(Set<ItineraryMaster> set)
			throws TASystemException {
		logger.debug("found " + set.size() + " elements in the supplied set");
		int count = 0;
		for (ItineraryMaster itineraryMaster : set) {
			this.createEntity(itineraryMaster);
			count++;
		}
		return count;
	}

	@Override
	@Cacheable(value = "entity.ta_ItineraryMaster", key = "{#root.methodName,#id}")
	public Set<ItineraryMaster> findAll() throws TASystemException {
		String strSQL = "Select c from ItineraryMaster c";
		LinkedHashSet<ItineraryMaster> set = null;
		try {

			@SuppressWarnings("unchecked")
			List<ItineraryMaster> list = (List<ItineraryMaster>) this
					.executeQuery(strSQL);
			set = new LinkedHashSet<ItineraryMaster>(
					new LinkedList<ItineraryMaster>(list));
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return set;

	}

	@Override
	@Cacheable(value = "entity.ta_ItineraryMaster", key = "{#root.methodName,#id}")
	public ItineraryMaster findById(String id) throws TASystemException {
		String strSQL = "Select c from ItineraryMaster c where c.itineraryMasterID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<ItineraryMaster> list = (List<ItineraryMaster>) this
					.executeQuery(strSQL, map);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return null;
	}

	@Override
	@CacheEvict(value = { "entity.ta_ItineraryMaster",
			"entity.ta_ItineraryMaster" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public ItineraryMaster updateEntity(ItineraryMaster t)
			throws TASystemException {
		return super.update(t, true);
	}

}
