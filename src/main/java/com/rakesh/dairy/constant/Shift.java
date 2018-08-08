package com.rakesh.dairy.constant;

public enum Shift {
	MORNING("morning"), EVENING("evening");

	private String value;

	Shift(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public Shift getShift(String value) {
		for (Shift val : Shift.values()) {
			if (val.equals(value)) {
				return val;
			}
		}

		return null;
	}

}
