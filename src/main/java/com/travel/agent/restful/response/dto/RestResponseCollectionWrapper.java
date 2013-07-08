package com.travel.agent.restful.response.dto;

import java.util.Collection;

import com.travel.agent.model.SABaseEntity;

public class RestResponseCollectionWrapper<T extends SABaseEntity> {
	
	private String result;
	private Integer resultCode;
	private Collection<T> collection;
	
	public static class Builder<T extends SABaseEntity> {
		private String result;
		private Integer resultCode;
		private Collection<T> collection;

		public Builder() {
		}

		public Builder<T> result(String val) {
			result = val;
			return this;
		}

		public Builder<T> resultCode(Integer val) {
			resultCode = val;
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
		result = builder.result;
		resultCode = builder.resultCode;
	}


	public Collection<T> getCollection() {
		return collection;
	}

	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

}
