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

import com.travel.agent.dao.IItineraryDetailDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ItineraryDetail;

@Transactional(readOnly = true)
@Repository("iItineraryDetailDao")
public class ItineraryDetailDaoHibernateImpl extends
		BaseDaoHibernateSupport<ItineraryDetail, String> implements
		IItineraryDetailDao {

	private static Logger logger = Logger
			.getLogger(ItineraryDetailDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.ta_ItineraryDetail",
			"entity.ta_ItineraryDetail" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public ItineraryDetail createEntity(ItineraryDetail t)
			throws TASystemException {
		ItineraryDetail created = null;
		try {
			t.setItineraryDetailID(UUID.randomUUID().toString());
			created = this.insert(t, true);
			logger.debug("created entity with ID" + t.getItineraryDetailID());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;
	}

	@Override
	@Cacheable(value = "entity.ta_ItineraryDetail", key = "{#root.methodName,#id}")
	public Set<ItineraryDetail> findAll() throws TASystemException {
		String strSQL = "Select c from ItineraryDetail c";
		LinkedHashSet<ItineraryDetail> set = null;
		try {

			@SuppressWarnings("unchecked")
			List<ItineraryDetail> list = (List<ItineraryDetail>) this
					.executeQuery(strSQL);
			set = new LinkedHashSet<ItineraryDetail>(
					new LinkedList<ItineraryDetail>(list));
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return set;

	}

	@Override
	@Cacheable(value = "entity.ta_ItineraryDetail", key = "{#root.methodName,#id}")
	public ItineraryDetail findById(String id) throws TASystemException {
		String strSQL = "Select c from ItineraryDetail c where c.itineraryDetailID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<ItineraryDetail> list = (List<ItineraryDetail>) this.executeQuery(strSQL,
					map);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return null;

	}

	@Override
	@CacheEvict(value = { "entity.ta_ItineraryDetail",
			"entity.ta_ItineraryDetail" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public ItineraryDetail updateEntity(ItineraryDetail t)
			throws TASystemException {
		ItineraryDetail obj = null;
		try {
			obj = super.update(t, true);
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return obj;
	}

}
