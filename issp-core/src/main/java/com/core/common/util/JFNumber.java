package com.core.common.util;

import java.math.BigDecimal;

public class JFNumber {

	/**
	 * JFNumber 构造子注解。
	 */
	public JFNumber() {
		super();
	}

	public static double round(double value, int dec) {
		double rounddec = 1.00d, i;
		double dttt = 0.0d;
		for (i = 0; i < dec; i++) {
			rounddec *= 10.00d;
		}
		dttt = Math.round(value * rounddec) / rounddec;
		return dttt;
	}

	public static double round2(double anumber, int place) {
		String sValue = String.valueOf(anumber);
		BigDecimal bigValue = new BigDecimal(sValue);
		return bigValue.setScale(place, 4).doubleValue();
	}
}
