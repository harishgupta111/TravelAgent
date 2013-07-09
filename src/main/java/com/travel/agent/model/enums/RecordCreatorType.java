package com.travel.agent.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum RecordCreatorType {
	USER(0), MASTER_DATA(2), ADMIN(2), SYSTEM(3), SCHEDULER(4), TEST(5);
	
	private static Map<Integer, RecordCreatorType> lookup = new HashMap<Integer, RecordCreatorType>();

	static {
		for (RecordCreatorType s : EnumSet.allOf(RecordCreatorType.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private RecordCreatorType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static RecordCreatorType get(int code) {
		return lookup.get(code);
	}

	public static Map<Integer, RecordCreatorType> returnMap() {
		return lookup;
	}

}
