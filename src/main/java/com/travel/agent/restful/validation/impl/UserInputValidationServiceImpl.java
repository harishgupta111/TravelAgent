package com.travel.agent.restful.validation.impl;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import com.travel.agent.model.SABaseEntity;
import com.travel.agent.restful.response.dto.RestResponseConstraintVoilationWrapper;
import com.travel.agent.restful.validation.UserInputValidationService;

@Component("userInputValidationService")
public class UserInputValidationServiceImpl<T extends SABaseEntity> implements
		UserInputValidationService<T> {

	private static Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	@Override
	public RestResponseConstraintVoilationWrapper<T> validateInput(T t) {

		Set<ConstraintViolation<T>> constraintViolations = validator
				.validate(t);
		Set<String> voilations = new HashSet<String>();
		if (constraintViolations != null && constraintViolations.size() > 0) {

			for (ConstraintViolation<T> i : constraintViolations)
				voilations.add(i.getMessage());

			RestResponseConstraintVoilationWrapper<T> constraintVoilationWrapper = new RestResponseConstraintVoilationWrapper.Builder<T>()
					.voilationSet(voilations).status(Status.NOT_ACCEPTABLE)
					.build();

			return constraintVoilationWrapper;

		}
		else
			return null;

	}
}
