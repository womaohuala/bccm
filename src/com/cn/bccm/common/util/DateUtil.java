package com.cn.bccm.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	
	public static Date stringTimeToDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			log.error(e, e);
			return null;
		}
	}
	
	public static String toDateTimeStringBySeconde(int date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(
				(long) date * 1000));
	}

	public static String toDateTimeString(long date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(
				date));
	}

	public static String toDateStringBySeconde(int date) {
		return new SimpleDateFormat("yyyy年MM月dd日").format(new Date(
				(long) date * 1000));
	}

	public static int toInt(String date) {
		int t = 0;
		try {
			t = (int) (new SimpleDateFormat("yyyy-MM-dd H:m:s").parse(date)
					.getTime() / 1000);
		} catch (ParseException e) {
			try {
				t = (int) (new SimpleDateFormat("yyyy-MM-dd").parse(date)
						.getTime() / 1000);
			} catch (ParseException ee) {
				ee.printStackTrace();
			}
		}
		return t;
	}

	public static String getNowDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static String getNowDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static int getNowDateSeconde() {
		try {
			return (int) (new SimpleDateFormat("yyyy-MM-dd")
					.parse(getNowDate()).getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long t = System.currentTimeMillis();
		t -= t % 86400000 + 28800000;
		return (int) (t / 1000);
	}
	public static int getNowTimeSeconde() {
		
		return toInt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	public static Date fmtStrTime(String dt, String pattern) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		try {
			date = sdf.parse(dt);
		} catch (ParseException e) {
			return null;
		}
		return date;

	}
	
	public static long getDayFirstTime(int dayAdd) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH),
				gc.get(GregorianCalendar.DAY_OF_MONTH), 0, 0, 0);
		gc.add(GregorianCalendar.DAY_OF_MONTH, dayAdd);
		return (int) (gc.getTimeInMillis() / 1000);
	}
	public static String toDateTimeStringNoBySeconde(int date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(
				 date * 1000L));
	}
	public static  String getTimeString(int time){
		   String timeStr="";
		    int hour=time/3600;
		    int minutes=time%3600/60;
		    int second=time%3600%60;
			if(hour!=0)
				timeStr=timeStr+hour+"时";
		    if(minutes!=0)
		    	timeStr=timeStr+minutes+'分';
		    if(second!=0)
		    	timeStr=timeStr+second+'秒';
				
			if(time==0){
				timeStr=timeStr+"";
			}
		    return timeStr;
	}


}
