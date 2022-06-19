package com.gokhantamkoc.javabootcamp.odevhafta3.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	private final static SimpleDateFormat UTC_DATE = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static long convertToMillisTime(String time) {
		long timeMillis = 0;
		long hours = Long.parseLong(time.substring(0, 2));
		long minutes = Long.parseLong(time.substring(3, 5));
		long seconds = Long.parseLong(time.substring(6, 8));
		long millis = 0;
		if (time.length() == 12) {
			millis = Long.parseLong(time.substring(9, 12));
		}
		timeMillis = (hours * 60 + minutes) * 60 * 1000 + seconds * 1000 + millis;
		return timeMillis;
	}

	public static Date convertToDate(long millis) {
		// Bu metodu doldurmanizi bekliyoruz.
	}
}
