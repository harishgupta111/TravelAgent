package com.travel.agent.dao;

import java.io.Serializable;
import java.util.Set;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.SABaseEntity;

public interface IBaseDao<T extends SABaseEntity, P extends Serializable> {

	public T createEntity(T t) throws TASystemException;

	public Set<T> findAll() throws TASystemException;

	public T findById(P id) throws TASystemException;

	public T updateEntity(T t) throws TASystemException;
}
