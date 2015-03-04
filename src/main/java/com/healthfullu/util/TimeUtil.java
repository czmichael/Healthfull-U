package com.healthfullu.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

// Move this to client side, use javascript to calculate the days instead!!!




public class TimeUtil {
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getDaysSinceUnixEpoch(String dateString) {
		MutableDateTime epoch = new MutableDateTime();
		epoch.setDate(0); 
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dateTime = formatter.parseDateTime(dateString);
		
		
		Days days = Days.daysBetween(epoch, dateTime);
		return days.getDays();
	}

}
