package com.travel.agent.restful.response.dto;

import com.travel.agent.model.SABaseEntity;

public class RestResponseWrapper<T extends SABaseEntity> {

	private String result;
	private Integer resultCode;
	private T data;
	
	public static class Builder<T extends SABaseEntity> {
		private String result;
		private Integer resultCode;
		private T data;

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

		public Builder<T> data(T val) {
			data = val;
			return this;
		}

		public RestResponseWrapper<T> build() {
			return new RestResponseWrapper<T>(this);
		}
	}

	private RestResponseWrapper(Builder<T> builder) {
		data = builder.data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result
				+ ((resultCode == null) ? 0 : resultCode.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestResponseWrapper other = (RestResponseWrapper) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
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
