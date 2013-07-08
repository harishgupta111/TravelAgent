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

import com.travel.agent.dao.ILocationMasterDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.LocationMaster;

@Transactional(readOnly = true)
@Repository("iLocationMasterDao")
public class LocationDaoHibernateImpl extends
		BaseDaoHibernateSupport<LocationMaster, String> implements
		ILocationMasterDao {

	private static Logger logger = Logger.getLogger(LocationDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.ta_LocationMaster",
			"entity.ta_LocationMaster" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public LocationMaster createEntity(LocationMaster t) throws TASystemException {
		LocationMaster created = null;
		try {
			t.setLocationMasterID(UUID.randomUUID().toString());
			created = this.insert(t, true);
			logger.debug("created entity with ID" + t.getLocationMasterID());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;
	}

	@Override
	@Cacheable(value = "entity.ta_LocationMaster", key="{#root.methodName,#id}")
	public Set<LocationMaster> findAll() throws TASystemException {
		String strSQL = "Select c from LocationMaster c";
		LinkedHashSet<LocationMaster> set = null;
		try {

			@SuppressWarnings("unchecked")
			List<LocationMaster> list = (List<LocationMaster>) this
					.executeQuery(strSQL);
			set = new LinkedHashSet<LocationMaster>(
					new LinkedList<LocationMaster>(list));
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return set;
	}

	@Override
	@Cacheable(value = "entity.ta_LocationMaster", key="{#root.methodName,#id}")
	public LocationMaster findById(String id) throws TASystemException {
		String strSQL = "Select c from LocationMaster c where c.locationMasterID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<LocationMaster> list = (List<LocationMaster>) this
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
	@Cacheable(value = "entity.ta_LocationMaster", key="{#root.methodName,#locationCode}")
	public LocationMaster getLocationByCode(String locationCode)
			throws TASystemException {
		String strSQL = "Select c from LocationMaster c where c.locationCode = :locationCode";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("locationCode", locationCode);
		try {
			@SuppressWarnings("unchecked")
			List<LocationMaster> list = (List<LocationMaster>) this
					.executeQuery(strSQL, map);
			if (list != null && list.size() > 0) {
				LocationMaster locationMaster = list.get(0);
				return locationMaster;
			}
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return null;
	}

	@Override
	public LocationMaster updateEntity(LocationMaster t)
			throws TASystemException {
		return super.update(t, true);
	}

}
