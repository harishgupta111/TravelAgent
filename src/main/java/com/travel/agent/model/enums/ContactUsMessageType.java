package com.travel.agent.model.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ContactUsMessageType {
	FEEDBACK(0), QUERY(1), COMPLAIN(2), REPORT_ABUSE(3), NEED_GUIDANCE(4);
	
	private static Map<Integer, ContactUsMessageType> lookup = new HashMap<Integer, ContactUsMessageType>();

	static {
		for (ContactUsMessageType s : EnumSet.allOf(ContactUsMessageType.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private ContactUsMessageType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ContactUsMessageType get(int code) {
		return lookup.get(code);
	}

	public static Map<Integer, ContactUsMessageType> returnMap() {
		return lookup;
	}

}
