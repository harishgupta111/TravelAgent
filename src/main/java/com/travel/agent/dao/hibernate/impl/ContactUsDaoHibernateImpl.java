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

import com.travel.agent.dao.IContactUsDao;
import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.ContactUs;

@Transactional(readOnly = true)
@Repository("iContactUsDao")
public class ContactUsDaoHibernateImpl extends
		BaseDaoHibernateSupport<ContactUs, String> implements IContactUsDao {

	private static Logger logger = Logger.getLogger(ContactUsDaoHibernateImpl.class);

	@Override
	@CacheEvict(value = { "entity.ta_ContactUs", "entity.ta_ContactUs" }, allEntries = true, beforeInvocation = false)
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	public ContactUs createEntity(ContactUs t) throws TASystemException {
		ContactUs created = null;
		try {
			t.setContactUsId(UUID.randomUUID().toString());
			created = this.insert(t, true);
			logger.debug("created entity with ID" + t.getContactUsId());
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return created;
	}

	@Override
	@Cacheable(value = "entity.ta_ContactUs")
	public Set<ContactUs> findAll() throws TASystemException {
		String strSQL = "Select c from ContactUs c";
		LinkedHashSet<ContactUs> set = null;
		try {

			@SuppressWarnings("unchecked")
			List<ContactUs> list = (List<ContactUs>) this.executeQuery(strSQL);
			set = new LinkedHashSet<ContactUs>(new LinkedList<ContactUs>(list));
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return set;

	}

	@Override
	@Cacheable(value = "entity.ta_ContactUs", key="{#root.methodName,#id}")
	public ContactUs findById(String id) throws TASystemException {
		String strSQL = "Select c from Booking c where c.bookingID = :id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			@SuppressWarnings("unchecked")
			List<ContactUs> list = (List<ContactUs>) this.executeQuery(strSQL,
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
	@Transactional(readOnly = false, propagation = Propagation.MANDATORY, rollbackFor = TASystemException.class, isolation = Isolation.DEFAULT)
	@CacheEvict(value = "entity.ta_ContactUs", beforeInvocation = false, key="{#root.methodName,#t.contactUsId}")
	public ContactUs updateEntity(ContactUs t) throws TASystemException {
		ContactUs obj = null;
		try {
			obj = super.update(t, true);
		} catch (HibernateException e) {
			throw new TASystemException(e);
		}
		return obj;
	}

}
