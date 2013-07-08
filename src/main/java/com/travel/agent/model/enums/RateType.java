package com.travel.agent.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum RateType {
	PER_DAY(0), PER_KM(1), FROM_TO(2);

	private static Map<Integer, RateType> lookup = new HashMap<Integer, RateType>();

	static {
		for (RateType s : EnumSet.allOf(RateType.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private RateType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static RateType get(int code) {
		return lookup.get(code);
	}

	public static Map<Integer, RateType> returnMap() {
		return lookup;
	}
}
