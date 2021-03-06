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

import com.travel.agent.dao.IVehicleMasterDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.VehicleMaster;

@Transactional(readOnly = true)
@Repository("iVehicleMasterDao")
public class VehicleMasterDaoHibernateImpl extends
		BaseDaoHibernateSupport<VehicleMaster, Long> implements
		IVehicleMasterDao {

	private static Logger logger = Logger
			.getLogger(VehicleMasterDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.ta_vehicleMaster", "entity.ta_vehicleMaster" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public VehicleMaster createEntity(VehicleMaster vehicleMaster)
			throws TASystemException {
		VehicleMaster created = null;
		try {
			vehicleMaster.setVehicleMasterID(UUID.randomUUID().toString());
			created = this.insert(vehicleMaster, true);
			logger.debug("created entity with ID"
					+ vehicleMaster.getVehicleMasterID());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;
	}

	@Override
	@Cacheable(value = "entity.ta_vehicleMaster")
	public Set<VehicleMaster> findAll() throws TASystemException {
		String strSQL = "Select c from VehicleMaster c";
		Set<VehicleMaster> set = null;
		try {

			@SuppressWarnings("unchecked")
			List<VehicleMaster> list = (List<VehicleMaster>) this
					.executeQuery(strSQL);
			set = new LinkedHashSet<VehicleMaster>(
					new LinkedList<VehicleMaster>(list));
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return set;
	}

	@Override
	@Cacheable(value = "entity.ta_vehicleMaster", key="{#root.methodName,#id}")
	public VehicleMaster findById(String id) throws TASystemException {
		String strSQL = "Select c from VehicleMaster c where c.vehicleMasterID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<VehicleMaster> list = (List<VehicleMaster>) this.executeQuery(
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
	@CacheEvict(value = "entity.ta_vehicleMaster", allEntries = true, beforeInvocation = false)
	public VehicleMaster updateEntity(VehicleMaster t) throws TASystemException {
		VehicleMaster vm = null;
		try {
			vm = super.update(t, true);
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return vm;
	}
}
