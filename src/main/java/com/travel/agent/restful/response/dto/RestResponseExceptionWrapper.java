package com.travel.agent.restful.response.dto;

import com.travel.agent.exception.TASystemException;

public class RestResponseExceptionWrapper<T extends TASystemException> {
	
	private String result;
	private Integer resultCode;
	private T exception;
	
	public static class Builder<T extends TASystemException> {
		private String result;
		private Integer resultCode;
		private T exception;

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

		public Builder<T> exception(T val) {
			exception = val;
			return this;
		}

		public RestResponseExceptionWrapper<T> build() {
			return new RestResponseExceptionWrapper<T>(this);
		}
	}

	private RestResponseExceptionWrapper(Builder<T> builder) {
		exception = builder.exception;
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
	public T getException() {
		return exception;
	}
	public void setException(T exception) {
		this.exception = exception;
	}
	
	

}
