package com.travel.agent.dao.service;

import java.io.Serializable;
import java.util.Set;

import com.travel.agent.exception.TASystemException;
import com.travel.agent.model.SABaseEntity;

interface IBaseDaoService<T extends SABaseEntity, P extends Serializable> {

	public T create(T t) throws TASystemException;

	public Set<T> getAll() throws TASystemException;

	public T getById(P id) throws TASystemException;

	public T updateEntity(T t) throws TASystemException;

}
