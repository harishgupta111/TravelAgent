package com.travel.agent.dao.hibernate.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.util.Assert;

/**
 * Abstract Hibernate Data Access Object. All Hibernate DAOs in HES must extend
 * this class. This class provides implementation of generic CRUD operations on
 * a domain entity plus apis for providing higher level abstraction from
 * hibernate session apis. The extending DAOs should provide actual
 * business-related data access operations w.r.t a domain entity.
 *  Why not to extend HibernateDaoSupport ? 
 *  | ------------------------------------------- |
 * http://forum.springsource.org/archive/index.php/t-57854.html
 * http://forum.springsource.org/showthread.php?t=56954
 * http://www.coderanch.com/t/61510/Application-Frameworks/Generic-DAO-using-Spring
 * http://swik.net/Spring/Interface21+Team+Blog/So+should+you+still+use+Spring%27s+HibernateTemplate+and%2For+JpaTemplate%3F%3F/bcw59
 * 
 * @author Abhinav Nigam
 */

public class BaseDaoHibernateSupport<T, PK extends Serializable>{

	private static Logger logger = Logger.getLogger(BaseDaoHibernateSupport.class);
	private final static int MAX_EXPRESSION_LIMIT = 1000;
	private final Class<T> domainClazz;

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoHibernateSupport() {
		domainClazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/*
	 * method to find entity by an id
	 */
	@SuppressWarnings("unchecked")
	public T byId(final PK anId) throws ObjectRetrievalFailureException {
		T t = (T) currentSession().get(domainClazz, anId);
		final T entity = t;
		if (entity == null) {
			throw new ObjectRetrievalFailureException(domainClazz, anId);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public T byId(final PK anId, String eagerlyRequiredProperty, Class<?> clazz) throws ObjectRetrievalFailureException {
		Criteria crit = currentSession().createCriteria(clazz);
		crit.add(Restrictions.eq("id", anId));
		crit.setFetchMode(eagerlyRequiredProperty, FetchMode.JOIN);
		T t = (T)crit.uniqueResult();
		final T entity = t;
		if (entity == null) {
			throw new ObjectRetrievalFailureException(domainClazz, anId);
		}
		return entity;
	}

	
	/*
	 * 
	 */
	public void remove(final T aPersistentObject) {
		remove(aPersistentObject, false);
	}

	/*
	 * 
	 */
	public void remove(final T aPersistentObject, final boolean aImmediately) {
		currentSession().delete(aPersistentObject);
		if (aImmediately) {
			currentSession().flush();
		}
	}

	/*
	 * 
	 */
	public void removeById(final PK anId) {
		remove(byId(anId));
	}

	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<T> fetchAll() throws HibernateException {
		return currentSession().createCriteria( domainClazz.getName()).setCacheable(true)
				.list();
	}
	
	/*
	 * 

	@SuppressWarnings("unchecked")
	public List<T> fetchAllOrdered(Map<String, SortOrder> map)
	{
		Criteria criteria = currentSession().createCriteria(domainClazz);
				
		Iterator<Entry<String, SortOrder>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, SortOrder> pairs = it.next();
	        if( pairs.getValue().equals(SortOrder.ASC))
	        	criteria.addOrder(Order.asc(pairs.getKey().toString()));
	        else
	        	criteria.addOrder(Order.desc(pairs.getKey().toString()));
	        
	    }
	    
		return criteria.list();
	}
	 */
	/*
	 * 
	 */
	public T insert(final T anEntity) throws HibernateException {
		return insert(anEntity, false);
	}

	/*
	 * 
	 */
	public T insert(final T anEntity, final boolean immediately) throws HibernateException {
		currentSession().persist(anEntity);
		if (immediately) {
			currentSession().flush();
		}
		return anEntity;
	}

	/*
	 * 
	 */
	public T update(final T anEntity) {
		return update(anEntity, false);
	}

	/*
	 * 
	 */
	public void updateAll(final Collection<T> anEntities,
			final boolean aImmediate) {
		Assert
				.notEmpty(anEntities,
						"collection passed is either null or empty");
		for (final T entity : anEntities) {
			update(entity, aImmediate);
		}
	}

	/*
	 * 
	 */
	public void updateAll(final Collection<T> anEntities) {
		Assert
				.notEmpty(anEntities,
						"collection passed is either null or empty");
		for (final T entity : anEntities) {
			update(entity, false);
		}
	}

	/**
	 * @param anEntity
	 */
	public void refreshEntity(final T anEntity) {
		currentSession().refresh(anEntity);
	}

	/**
	 * @param anEntities
	 * @param aBatchSize
	 */
	public void batchInsert(final Collection<T> anEntities, final int aBatchSize) {
		int i = 0;
		for (final T entity : anEntities) {
			final Session session = currentSession();
			session.save(entity);
			if (i != 0 && i % aBatchSize == 0) {
				session.flush();
				session.clear();

			}
			i++;
		}
		currentSession().flush();
	}

	/**
	 * @param anEntity
	 * @param aImmediately
	 * @return T
	 */
	public T update(final T anEntity, final boolean aImmediately) {
		currentSession().merge(anEntity);
		if (aImmediately) {
			currentSession().flush();
		}
		return anEntity;
	}


	/**
	 * 
	 * @param anEntity
	 */
	public void removeFromSession(final T anEntity) {
		currentSession().evict(anEntity);
	}

	/**
	 * 
	 * @param aQueryString
	 * @param aQueryParameters
	 * @return
	 */
	public Object executeQueryForUniqueResult(final String aQueryString,
			final Map<String, Object> aQueryParameters) {
		return bindParametersAndExecuteQueryForUniqueResult(aQueryString,
				aQueryParameters);
	}

	/**
	 * This method will be used for delete or Update(i.e DML query)
	 * 
	 * @param String
	 *            queryString
	 * @param Map
	 *            <String, Object> params
	 * @return numberOfRowUpdated number of rows effected.
	 */
	public int executeQueryForDML(final String aQueryString,
			final Map<String, Object> aQueryParameters) {
		int numberOfRowUpdated = 0;
		final Query query = getQuery(aQueryString);
		try {
			bindParametersForDML(query, aQueryParameters);
			numberOfRowUpdated = query.executeUpdate();
		} catch (final Exception e) {
			logger
					.error(
							String
									.format(
											"Error in executing DML query. Query[%s]Params[%s]Entity[%s]",
											aQueryString, aQueryParameters,
											domainClazz.getName()), e);
		}

		return numberOfRowUpdated;
	}

	/**
	 * 
	 * @param aQueryString
	 * @param aQueryParameters
	 * @return
	 * @throws HibernateException
	 */
	public List<?> executeQuery(final String aQueryString,
			final Map<String, Object> aQueryParameters) throws HibernateException {
		final Query query = getQuery(aQueryString);
		query.setCacheable(true);
		logger.debug("Before executing the query " + query.getQueryString());
		final List<?> results = bindParametersAndExecuteQuery(query,
				aQueryParameters);
		return results;
	}

	/**
	 * @param aQueryString
	 * @return List<?>
	 */
	public List<?> executeQuery(final String aQueryString) throws HibernateException{
		return executeQuery(aQueryString, null);
	}

	/**
	 * @param aQueryString
	 * @param aQueryParameters
	 * @param aStartFrom
	 * @param aMaxResults
	 * @return List<?>
	 */
	public List<?> executeQueryForPaginatedResults(final String aQueryString,
			final Map<String, Object> aQueryParameters, final int aStartFrom,
			final int aMaxResults) {
		final Query query = getQuery(aQueryString);
		query.setFirstResult(aStartFrom);
		query.setMaxResults(aMaxResults);
		query.setCacheable(true);
		final List<?> results = bindParametersAndExecuteQuery(query,
				aQueryParameters);
		return results;
	}

	/**
	 * @param aQueryString
	 * @param aQueryParameters
	 * @param aAlias
	 * @param aLockMode
	 * @param LOCK_CAPACITY
	 * @return List<?>
	 */
	public List<?> executeQueryForLocking(final String aQueryString,
			final Map<String, Object> aQueryParameters, final String aAlias,
			final LockMode aLockMode, final int LOCK_CAPACITY) throws HibernateException {
		final Query query = getQuery(aQueryString);
		// query.setMaxResults(LOCK_CAPACITY);
		// query.setLockMode(alias, lockMode);
		final List<?> results = bindParametersAndExecuteQuery(query,
				aQueryParameters);
		return results;
	}

	/**
	 * @param query
	 * @param params
	 * @param applyFilter
	 * @return
	 */
	private List<?> bindParametersAndExecuteQuery(final Query query,
			final Map<String, Object> params) throws HibernateException {
		if (params != null && !params.isEmpty()) {
			final Set<Map.Entry<String, Object>> parameters = params.entrySet();

			for (final Map.Entry<String, Object> parameter : parameters) {
				final Object value = parameter.getValue();
				final Class<?> type = value.getClass();

				if (type.isArray()) {
					query.setParameter(parameter.getKey(), value);
				} else if (Collection.class.isInstance(value)) {
					query
							.setParameter(parameter.getKey(), parameter
									.getValue());
				} else {
					query
							.setParameter(parameter.getKey(), parameter
									.getValue());
				}
			}
		}

		List<?> results = null;

		try {
			results = query.list();
		} catch (final Exception e) {
			logger.error("****** PRINTING STACK TRACE ************ ");
			logger.error(String.format(
					"Error in executing query. Query[%s]Params[%s]Entity[%s]",
					query.toString(), params, domainClazz.getName()), e);
			logger.error("****** END OF STACK TRACE ************ ");
			logger.error(String.format(e.getCause().getLocalizedMessage()));
			throw new HibernateException(String.format(e.getCause().getLocalizedMessage()));
		}

		return results;
	}

	/**
	 * @param aQueryString
	 * @param aQueryParameters
	 * @return
	 */
	private Object bindParametersAndExecuteQueryForUniqueResult(
			final String aQueryString,
			final Map<String, Object> aQueryParameters) {
		Object object = null;
		final Query query = getQuery(aQueryString);
		try {
			if (aQueryParameters != null && !aQueryParameters.isEmpty()) {
				final Set<Map.Entry<String, Object>> parameters = aQueryParameters
						.entrySet();
				for (final Map.Entry<String, Object> parameter : parameters) {
					query
							.setParameter(parameter.getKey(), parameter
									.getValue());
				}
			}
			object = query.list();
		} catch (final Exception ne) {
			logger
					.error(String
							.format(
									"Error in fetching unique result. Multiple row found. Query[%s]Params[%s]Entity[%s]",
									aQueryString, aQueryParameters, domainClazz
											.getName()));
		}

		return object;
	}

	/**
	 * Bind parameter for DML
	 * 
	 * @param Query
	 *            query
	 * @param Map
	 *            <String, Object> params
	 */
	private void bindParametersForDML(final Query query,
			final Map<String, Object> params) {
		if (params != null && !params.isEmpty()) {
			final Set<Map.Entry<String, Object>> parameters = params.entrySet();
			for (final Map.Entry<String, Object> parameter : parameters) {
				final Object value = parameter.getValue();
				final Class<?> type = value.getClass();
				if (type.isArray()) {
					query.setParameter(parameter.getKey(), value);
				} else if (Collection.class.isInstance(value)) {
					query
							.setParameter(parameter.getKey(), parameter
									.getValue());
				} else {
					query
							.setParameter(parameter.getKey(), parameter
									.getValue());
				}
			}
		}
	}

	/**
	 * @param queryName
	 * @return
	 */
	private Query getQuery(final String query) throws HibernateException {
		final Query q = currentSession().createQuery(query);
		return q;
	}

	/*
	 * 
	 */
	private Session currentSession() {
		return getSessionFactory().getCurrentSession();
	}

	/**
	 * @return the sessionFactory
	 */

	/**
	 * Method used to avoid
	 * "ORA-01795: maximum number of expressions in a list is 1000" error
	 * 
	 * @param aNumberList
	 * @param dbColumnName
	 * @param aParameterMap
	 * @return
	 */
	protected String getPartitionedListQueryClause(
			final List<? extends Number> aNumberList,
			final String dbColumnName, final Map<String, Object> aParameterMap) {
		final StringBuilder sbPartitionedQueryClause = new StringBuilder("");
		final String namedParameter = "numberIds";
		final int inputListSize = aNumberList.size();
		if (inputListSize <= MAX_EXPRESSION_LIMIT) {
			// Less or equal to the limit
			sbPartitionedQueryClause.append(dbColumnName + " in (:"
					+ namedParameter + ") ");
			aParameterMap.put(namedParameter, aNumberList);
		} else {
			// Higher than the limit - partition list
			int blockId = 0;
			int startIndex = 0;
			int blocks = inputListSize / MAX_EXPRESSION_LIMIT;
			blocks = blocks * MAX_EXPRESSION_LIMIT == inputListSize ? blocks
					: blocks + 1;
			sbPartitionedQueryClause.append("(");
			for (int i = 0; i < blocks - 1; i++) {
				sbPartitionedQueryClause.append(dbColumnName + " in (:"
						+ namedParameter + blockId + ") OR ");
				aParameterMap
						.put(namedParameter + blockId, aNumberList.subList(
								startIndex, startIndex + MAX_EXPRESSION_LIMIT));
				blockId++;
				startIndex += MAX_EXPRESSION_LIMIT;
			}
			sbPartitionedQueryClause.append(dbColumnName + " in (:"
					+ namedParameter + blockId + ")) ");
			aParameterMap.put(namedParameter + blockId, aNumberList.subList(
					startIndex, inputListSize));
		}
		return sbPartitionedQueryClause.toString();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
