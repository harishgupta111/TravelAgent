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

import com.travel.agent.dao.IStateMasterDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.StateMaster;

@Transactional(readOnly = true)
@Repository("iStateMasterDao")
public class StateMasterHibernateImpl extends BaseDaoHibernateSupport<StateMaster, String> implements IStateMasterDao {
	
	private static Logger logger = Logger.getLogger(PassengerDetailDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = "entity.ta_vehicleMaster", key="{#root.methodName,#t.stateMasterID}")
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public StateMaster createEntity(StateMaster t) {
		StateMaster created = null;
		try {
			t.setStateMasterID(UUID.randomUUID().toString());
			created = this.insert(t, true);
			logger.debug("created entity with ID" + t.getStateMasterID());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;

	}

	@Override
	@Cacheable(value = "entity.ta_StateMaster", key="{#root.methodName}")
	public Set<StateMaster> findAll() throws TASystemException {
		String strSQL = "Select c from StateMaster c";
		LinkedHashSet<StateMaster> set = null;
		try {

			@SuppressWarnings("unchecked")
			List<StateMaster> list = (List<StateMaster>) this.executeQuery(strSQL);
			set = new LinkedHashSet<StateMaster>(new LinkedList<StateMaster>(list));
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return set;

	}

	@Override
	@Cacheable(value = "entity.ta_StateMaster", key="{#root.methodName,#id}")
	public StateMaster findById(String id) throws TASystemException {
		String strSQL = "Select c from StateMaster c where c.stateMasterID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<StateMaster> list = (List<StateMaster>) this.executeQuery(strSQL, map);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return null;

	}

	@Override
	@Cacheable(value = "entity.ta_StateMaster", key="{#root.methodName,#id}")
	public StateMaster getStateAndLocation(String id)
			throws TASystemException {
		String strSQL = "Select c from StateMaster c left join fetch c.locationMasterSet where c.stateMasterID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<StateMaster> list = (List<StateMaster>) this.executeQuery(strSQL, map);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return null;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	@CacheEvict(value = "entity.ta_vehicleMaster", key="{#root.methodName,#t.stateMasterID}")
	public StateMaster updateEntity(StateMaster t) throws TASystemException {
		StateMaster obj = null;
		try {
			obj = super.update(t, true);
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return obj;
	}

}
