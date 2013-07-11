package com.travel.agent.restful.response.dto;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;

public class RestResponseWrapper<T extends Serializable> {

	private Status status;
	private T data;
	
	public static class Builder<T extends Serializable> {
		private Status status;
		private T data;

		public Builder() {
		}

		public Builder<T> status(Status val) {
			status = val;
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
		status = builder.status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
