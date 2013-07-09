package com.travel.agent.restful.response.dto;

import java.util.Collection;

import javax.ws.rs.core.Response.Status;

import com.travel.agent.model.SABaseEntity;

public class RestResponseCollectionWrapper<T extends SABaseEntity> {
	
	private Status status;
	private Collection<T> collection;
	
	public static class Builder<T extends SABaseEntity> {
		private Status status;
		private Collection<T> collection;

		public Builder() {
		}

		public Builder<T> status(Status val) {
			status = val;
			return this;
		}

		public Builder<T> collection(Collection<T> val) {
			collection = val;
			return this;
		}

		public RestResponseCollectionWrapper<T> build() {
			return new RestResponseCollectionWrapper<T>(this);
		}
	}

	private RestResponseCollectionWrapper(Builder<T> builder) {
		collection = builder.collection;
		status = builder.status;
	}


	public Collection<T> getCollection() {
		return collection;
	}

	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
}
