package com.travel.agent.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum VehicleType {
	
	CAR(0), BUS(1), VAN(2);
	
	private static Map<Integer, VehicleType> lookup = new HashMap<Integer, VehicleType>();

	static {
		for (VehicleType s : EnumSet.allOf(VehicleType.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private VehicleType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static VehicleType get(int code) {
		return lookup.get(code);
	}

	public static Map<Integer, VehicleType> returnMap() {
		return lookup;
	}
}
