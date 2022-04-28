package com.efounder.JEnterprise.timer;

import com.core.common.util.StringFunction;

import java.util.Arrays;
import java.util.Calendar;

public class TimerUtil {

	@SuppressWarnings("static-access")
	public static boolean isTimeFit(TimerStub timerStub, Calendar calendar) {
		String timerType = timerStub.getTimerType();
		// 循环执行
		if (timerType.equals(timerStub._TIMER_TYPE_LOOP_))			
		return timerStub.getLoopCount() == timerStub.getLoopTimer();

		// 开始执行时间
		String fixedTime = StringFunction.replaceString(timerStub.getFixedTime(), ":", "");
		int hour = Integer.parseInt(fixedTime.substring(0, 2));
		int minute = Integer.parseInt(fixedTime.substring(2, 4));

		// 支持多执行时间 
		String fixedTimes[] = timerStub.getFixedTime().split(",");
		String varTimes[] = new String[fixedTimes.length];
		for (int i = 0; i < fixedTimes.length; i++) {
			fixedTime = StringFunction.replaceString(fixedTimes[i], ":", "");
			hour = Integer.parseInt(fixedTime.substring(0, 2));
			minute = Integer.parseInt(fixedTime.substring(2, 4));
			varTimes[i] = String.valueOf(hour) + ":" + String.valueOf(minute);
		}
		String sysTime = String.valueOf(calendar.get(calendar.HOUR_OF_DAY)) + ":"
				+ String.valueOf(calendar.get(calendar.MINUTE));

		String varTime = timerStub.getVarTime();

		// 固定时间执行,只执行一次
		if (timerType.equals(timerStub._TIMER_TYPE_DATE_)) {
			long t = calendar.getTimeInMillis() - timerStub.getFixedTimer().getTime();
			// 因为每分钟判断一次是否有需要执行的任务,误差控制在59秒
			return t <= 59000 && t > 0;
		}
		// 每天指定时间执行
		if (timerType.equals(timerStub._TIMER_TYPE_EDAY_)) {
			// return calendar.get(calendar.HOUR_OF_DAY) == hour
			// && calendar.get(calendar.MINUTE) == minute;
			//return Arrays.asList(varTimes).contains(sysTime);
			boolean c = Arrays.asList(varTimes).contains(sysTime);
	    	return c;
		}
		// 每周执行
		if (timerType.equals(timerStub._TIMER_TYPE_WEEK_)) {
			String[] days = varTime.split(","); // 每周X
			int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK) - 1; // calendar中周日是1
			if (dayOfWeek == 0)
				dayOfWeek = 7; // 周日
			return days != null && Arrays.asList(days).contains(String.valueOf(dayOfWeek))
					&& Arrays.asList(varTimes).contains(sysTime);
			// && calendar.get(calendar.HOUR_OF_DAY) == hour
			// && calendar.get(calendar.MINUTE) == minute;
		}
		// 每月执行
		if (timerType.equals(timerStub._TIMER_TYPE_MONTH_)) {
			String[] days = varTime.split(","); // 每月X
			int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);
			return days != null && Arrays.asList(days).contains(String.valueOf(dayOfMonth))
					&& Arrays.asList(varTimes).contains(sysTime);
			// && calendar.get(calendar.HOUR_OF_DAY) == hour
			// && calendar.get(calendar.MINUTE) == minute;
		}
		// 每年执行
		if (timerType.equals(timerStub._TIMER_TYPE_YEAR_)) {
			String[] days = varTime.split(","); // 每年X月X日
			int month = calendar.get(calendar.MONTH);
			int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);
			String monthday = String.valueOf(month) + "-" + String.valueOf(dayOfMonth);
			return days != null && Arrays.asList(days).contains(monthday) && Arrays.asList(varTimes).contains(sysTime);
			// && calendar.get(calendar.HOUR_OF_DAY) == hour
			// && calendar.get(calendar.MINUTE) == minute;
		}
		//在前台点击立即执行时，执行一次
		if(timerType.equals(timerStub._TIMER_TYPE_ONCE)) {
			return false;
		}
		return false;
	}

	/**
	 *
	 * @param date
	 *            String
	 * @return Date
	 */
	public static java.util.Date paraseDate(String date) {
		date = StringFunction.replaceString(date, "-", "");
		date = StringFunction.replaceString(date, ":", "");
		date = StringFunction.replaceString(date, "/", "");
		date = StringFunction.replaceString(date, " ", "");
		Calendar c = Calendar.getInstance();
		// 年月日时分秒
		int[] length = new int[] { 4, 2, 2, 2, 2, 2 };
		int[] key = new int[] { Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY,
				Calendar.MINUTE, Calendar.SECOND };
		for (int i = 0; i < length.length; i++) {
			if (date.length() >= length[i]) {
				String n = date.substring(0, length[i]);
				c.set(key[i], Integer.parseInt(n));
				// 月份减一
				if (key[i] == Calendar.MONTH)
					c.set(key[i], Integer.parseInt(n) - 1);
				date = date.substring(length[i]);
			}
		}
		return c.getTime();
	}
}
