package com.zhuoxun.common.enums;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 测试枚举
 */
public enum AgeEnum {
	ONE(1, "一岁"), TWO(2, "二岁");

	private int value;
	private String desc;

	AgeEnum(final int value, final String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Serializable getValue() {
		return this.value;
	}

	@JsonValue
	public String getDesc() {
		return this.desc;
	}
}
