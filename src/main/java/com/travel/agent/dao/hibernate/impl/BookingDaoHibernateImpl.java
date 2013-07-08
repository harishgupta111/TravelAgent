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

import com.travel.agent.dao.IBookingDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.Booking;

@Transactional(readOnly = true)
@Repository("iBookingDao")
class BookingDaoHibernateImpl extends BaseDaoHibernateSupport<Booking, String>
		implements IBookingDao {

	private static Logger logger = Logger.getLogger(BookingDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.ta_Booking", "entity.ta_Booking" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public Booking createEntity(Booking t) throws TASystemException {
		Booking created = null;
		try {
			created = this.insert(t, true);
			logger.debug("created entity with ID" + t.getBookingID());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;

	}

	@Override
	@Cacheable(value = "entity.ta_Booking", key = "#root.methodName")
	public Set<Booking> findAll() throws TASystemException {
		String strSQL = "Select c from Booking c";
		LinkedHashSet<Booking> set = null;
		try {

			@SuppressWarnings("unchecked")
			List<Booking> list = (List<Booking>) this.executeQuery(strSQL);
			set = new LinkedHashSet<Booking>(new LinkedList<Booking>(list));
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return set;

	}

	@Override
	@Cacheable(value = "entity.ta_Booking", key="{#root.methodName,#id}")
	public Booking findById(String id) throws TASystemException {
		String strSQL = "Select c from Booking c where c.bookingID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<Booking> list = (List<Booking>) this.executeQuery(strSQL, map);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor=TASystemException.class, isolation=Isolation.DEFAULT)
	@CacheEvict(value = "entity.ta_Booking", allEntries = true, beforeInvocation = false)
	public Booking updateEntity(Booking t) throws TASystemException {
		Booking obj = null;
		try {
			obj = super.update(t, true);
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return obj;
	}

}
