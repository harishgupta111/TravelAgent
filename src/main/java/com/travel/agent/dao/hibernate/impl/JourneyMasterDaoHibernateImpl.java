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

import com.travel.agent.dao.IJourneyMasterDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.JourneyMaster;

@Transactional(readOnly = true)
@Repository("iJourneyMasterDao")
class JourneyMasterDaoHibernateImpl extends
BaseDaoHibernateSupport<JourneyMaster, String> implements IJourneyMasterDao {
	
	private static Logger logger = Logger
			.getLogger(JourneyMasterDaoHibernateImpl.class);


	@Override
	@CacheEvict(value = { "entity.ta_JourneyMaster",
			"entity.ta_JourneyMaster" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public JourneyMaster createEntity(JourneyMaster t) throws TASystemException {
		JourneyMaster created = null;
		try {
			t.setJourneyMasterID(UUID.randomUUID().toString());
			created = this.insert(t, true);
			logger.debug("created entity with ID" + t.getJourneyMasterID());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;
	}

	@Override
	@Cacheable(value = "entity.ta_JourneyMaster", key = "{#root.methodName,#id}")
	public Set<JourneyMaster> findAll() throws TASystemException {
		String strSQL = "Select c from JourneyMaster c";
		LinkedHashSet<JourneyMaster> set = null;
		try {

			@SuppressWarnings("unchecked")
			List<JourneyMaster> list = (List<JourneyMaster>) this
					.executeQuery(strSQL);
			set = new LinkedHashSet<JourneyMaster>(
					new LinkedList<JourneyMaster>(list));
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return set;
	}

	@Override
	@Cacheable(value = "entity.ta_JourneyMaster", key = "{#root.methodName,#id}")
	public JourneyMaster findById(String id) throws TASystemException {
		String strSQL = "Select c from JourneyMaster c where c.journeyMasterID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<JourneyMaster> list = (List<JourneyMaster>) this
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
	@CacheEvict(value = { "entity.ta_JourneyMaster",
	"entity.ta_JourneyMaster" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public JourneyMaster updateEntity(JourneyMaster t) throws TASystemException {
		return super.update(t, true);
	}

}
