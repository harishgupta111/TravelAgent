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

import com.travel.agent.dao.IAvailableVehicleDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.AvailableVehicle;

@Transactional(readOnly = true)
@Repository("iAvailableVehicleDao")
public class AvailableVehicleDaoHibernateImpl extends
		BaseDaoHibernateSupport<AvailableVehicle, Long> implements
		IAvailableVehicleDao {

	private static Logger logger = Logger
			.getLogger(AvailableVehicleDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.ta_AvailableVehicle", "entity.ta_AvailableVehicle" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public AvailableVehicle createEntity(AvailableVehicle availableVehicle)
			throws TASystemException {
		AvailableVehicle created = null;
		try {
			availableVehicle.setAvailableVehicleID(UUID.randomUUID().toString());
			created = this.insert(availableVehicle, true);
			logger.debug("created entity with ID"
					+ availableVehicle.getAvailableVehicleID());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;
	}

	@Override
	@Cacheable(value = "entity.ta_AvailableVehicle")
	public Set<AvailableVehicle> findAll() throws TASystemException {
		String strSQL = "Select c from AvailableVehicle c";
		Set<AvailableVehicle> set = null;
		try {

			@SuppressWarnings("unchecked")
			List<AvailableVehicle> list = (List<AvailableVehicle>) this
					.executeQuery(strSQL);
			set = new LinkedHashSet<AvailableVehicle>(
					new LinkedList<AvailableVehicle>(list));
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return set;
	}

	@Override
	@Cacheable(value = "entity.ta_AvailableVehicle", key="{#root.methodName,#id}")
	public AvailableVehicle findById(String id) throws TASystemException {
		String strSQL = "Select c from AvailableVehicle c where c.availableVehicleID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<AvailableVehicle> list = (List<AvailableVehicle>) this.executeQuery(
					strSQL, map);
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
	@CacheEvict(value = "entity.ta_AvailableVehicle", allEntries = true, beforeInvocation = false)
	public AvailableVehicle updateEntity(AvailableVehicle t) throws TASystemException {
		AvailableVehicle vm = null;
		try {
			vm = super.update(t, true);
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return vm;
	}
}
