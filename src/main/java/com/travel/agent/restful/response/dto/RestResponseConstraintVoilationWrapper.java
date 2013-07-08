package com.travel.agent.restful.response.dto;

import java.util.Collection;

import javax.validation.ConstraintViolation;

import com.travel.agent.model.SABaseEntity;

public class RestResponseConstraintVoilationWrapper<T extends SABaseEntity> {
	
	private String result;
	private Integer resultCode;
	private Collection<ConstraintViolation<T>> collection;
	
	public static class Builder<T extends SABaseEntity> {
		private String result;
		private Integer resultCode;
		private Collection<ConstraintViolation<T>> collection;

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

		public Builder<T> collection(Collection<ConstraintViolation<T>> val) {
			collection = val;
			return this;
		}

		public RestResponseConstraintVoilationWrapper<T> build() {
			return new RestResponseConstraintVoilationWrapper<T>(this);
		}
	}

	private RestResponseConstraintVoilationWrapper(Builder<T> builder) {
		collection = builder.collection;
		result = builder.result;
		resultCode = builder.resultCode;
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
	public Collection<ConstraintViolation<T>> getCollection() {
		return collection;
	}
	public void setCollection(Collection<ConstraintViolation<T>> collection) {
		this.collection = collection;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((collection == null) ? 0 : collection.hashCode());
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result
				+ ((resultCode == null) ? 0 : resultCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		RestResponseConstraintVoilationWrapper other = (RestResponseConstraintVoilationWrapper) obj;
		if (collection == null) {
			if (other.collection != null)
				return false;
		} else if (!collection.equals(other.collection))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (resultCode == null) {
			if (other.resultCode != null)
				return false;
		} else if (!resultCode.equals(other.resultCode))
			return false;
		return true;
	}
	
	

}
