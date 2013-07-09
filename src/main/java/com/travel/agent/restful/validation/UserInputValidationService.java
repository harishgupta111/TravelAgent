package com.travel.agent.restful.validation;

import com.travel.agent.model.SABaseEntity;
import com.travel.agent.restful.response.dto.RestResponseConstraintVoilationWrapper;

public interface UserInputValidationService<T extends SABaseEntity> {
	
	public RestResponseConstraintVoilationWrapper<T> validateInput(T t);

}