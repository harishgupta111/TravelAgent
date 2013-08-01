package com.travel.agent.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

public enum RateType {
	PER_DAY(0), PER_KM(1), FROM_TO(2);

	private static Map<Integer, RateType> enumRateTypeMap= new HashMap<Integer, RateType>();

	static {
		for (RateType s : EnumSet.allOf(RateType.class))
			enumRateTypeMap.put(s.getCode(), s);
	}

	private int code;

	private RateType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static RateType get(int code) {
		return enumRateTypeMap.get(code);
	}

	@Bean(name="enumRateTypeMap")
	public static Map<Integer, RateType> returnMap() {
		return enumRateTypeMap;
	}
}
