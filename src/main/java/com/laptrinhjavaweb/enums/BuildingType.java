package com.laptrinhjavaweb.enums;

public enum BuildingType {

	TANG_TRET("Tầng Trệt"),
	NGUYEN_CAN("Nguyên Căn"), 
	NOI_THAT("Nội Thất");

	private String value;

	private BuildingType(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
