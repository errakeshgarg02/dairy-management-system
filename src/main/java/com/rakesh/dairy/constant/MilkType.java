package com.rakesh.dairy.constant;

public enum MilkType {
	COW("cow"), BUFFALO("buffalo");

	private String value;

	MilkType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static MilkType getMilkType(String value) {
		for (MilkType val : MilkType.values()) {
			if (val.getValue().equals(value)) {
				return val;
			}
		}

		return null;
	}
}
