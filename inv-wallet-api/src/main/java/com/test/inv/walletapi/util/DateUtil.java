package com.test.inv.walletapi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class DateUtil {

	private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

	public final static String getToday() {
		String format = "yyyyMMdd";
		return formatDate(new Date(), format);
	}

	public final static String getChineseToday() {
		String format = "MMdd";
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR) - 1911);
		return year + formatDate(c.getTime(), format);
	}

	public final static String getCurrTime() {
		String format = "yyyyMMddHHmmss";
		return formatDate(new Date(), format);
	}

	public final static String formatDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	public final static String reformatDate(String date, String format1, String format2) {
		String result = "";
		try {
			SimpleDateFormat formatter1 = new SimpleDateFormat(format1);
			formatter1.setLenient(false);
			Date d = formatter1.parse(date);
			result = DateUtil.formatDate(d, format2);
		} catch (Exception e) {
			//			System.out.println("DEBUG:DateUtil.reformatDate;Date=" + date + ",format1=" + format1 + ",format2=" + format2 + ";Exception=" + e.toString());
		}
		return result;
	}

	public final static boolean isValidDate(String date) {
		if( date==null || date.length() != 8){
			return false;
		}
		return isValidDate(date, "yyyyMMdd");
	}

	public final static boolean isValidDate(String date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			formatter.setLenient(false);
			formatter.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public final static boolean isValidDateTime(String date) {
		return isValidDate(date, "yyyyMMddHHmmss");
	}

	public final static String formatDate(String date,String p1,String p2,String p3,String p4,String p5) 
	{
		String yyyy = "";
		String MM = "";
		String dd = "";
		String HH = "";
		String mm = "";
		String ss = "";
		if(null != date)
		{
			if(date.length() == 14)
			{
				yyyy = date.substring(0,4);
				MM = date.substring(4,6);
				dd = date.substring(6,8);
				HH = date.substring(8,10);
				mm = date.substring(10,12);
				ss = date.substring(12,14);
				return yyyy + p1 + MM + p2 + dd + p3 + HH + p4 + mm + p5 + ss;
			}
			else
			{
				return date;
			}
		}
		else
		{
			return null;
		}
	}

	public final static String jspViewDate(String date) 
	{
		return formatDate(date,"/","/"," ",":",":");
	}

	/**
	 * �ǤJ yyymmdd �� yyyymmdd , �έn�W� + - month
	 * @throws ParseException
	 */
	public static String dateFlipMonth(String date, int month) throws ParseException
	{
		Calendar c = Calendar.getInstance();
		c.setTime(YYYYMMDD.parse(date));
		c.add(Calendar.MONTH,month);

		return YYYYMMDD.format(c.getTime());
	}

	/**
	 * �ǤJ yyymmdd �� yyyymmdd , �έn�W� + - day
	 * @throws ParseException
	 */
	public static String dateFlipDay(String date, int day) throws ParseException
	{
		Calendar c = Calendar.getInstance();
		c.setTime(YYYYMMDD.parse(date));
		c.add(Calendar.DATE,day);

		return YYYYMMDD.format(c.getTime());
	}

	/**
	 * �ǤJ UTC �æ^�Ƿ�a�ɶ�
	 * @throws Exception
	 */
	public final static Date getDateByUTC(long utc) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(utc);
		return c.getTime();
	}

	/**
	 * �ǤJ UTC �æ^�Ƿ�a�ɶ�
	 * @throws Exception
	 */
	public final static String formatDateByUTC(long utc, String format) {
		return DateUtil.formatDate(getDateByUTC(utc), format);
	}

	/**
	 * �ǤJ ��a�ɶ��æ^��UTC
	 * @throws Exception
	 */
	public final static long dateToUTC(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * �ǤJ ��a�ɶ��æ^��UTC
	 * @throws Exception
	 */
	public final static long dateToUTC(String value, String format) throws Exception {
		return dateToUTC(value, format, null);
	}

	public final static long dateToUTC(String value, String format, TimeZone zone) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		if (zone != null) {
			sdf.setTimeZone(zone);
		}
		Date date = sdf.parse(value);
		return dateToUTC(date);
	}

	//	/**
	//	 * �ǤJ ��a�ɶ��άۮt�ɰ�, �æ^��UTC
	//	 * @throws Exception
	//	 */
	//	//diff: diff in Hour, US Pacific to local --> diff = 16, local to US Pacific --> diff = -16 
	//	public final static long dateToUTC(Date date, int diff) throws Exception {
	//		Calendar c = Calendar.getInstance();
	//		c.setTime(date);
	//		c.add(Calendar.HOUR, diff);
	//		return dateToUTC(c.getTime());
	//	}

	public final static Date parseDate(String date) throws Exception {
		return parseDate(date, "yyyyMMdd");
	}

	public final static Date parseDate(String date, String format) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setLenient(false);
		return formatter.parse(date);
	}

	public final static String[] getMonthDay(Date day,int addMonth) throws Exception{
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(Calendar.MONTH, addMonth);
		calendar.set(Calendar.DATE, 1);//把日期?置??月第一天
		Date fday = calendar.getTime();
		calendar.roll(Calendar.DATE, -1);//日期回?一天，也就是最后一天
		Date lday = calendar.getTime();
		return new String[]{dft.format(fday),dft.format(lday)};
	}
	public final static String addMonth(Date day,int addMonth,int addDay) throws Exception{
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		calendar.add(Calendar.MONTH, addMonth);
		calendar.add(Calendar.DATE, addDay);
		Date fday = calendar.getTime();
		return dft.format(fday);
	}

	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			boolean result = false;
			result = DateUtil.isValidDate("20051301");
			//			System.out.println(DateUtil.getCurrTime());
			result = DateUtil.isValidDateTime("20051301000000");
			//			System.out.println(result);
			//			System.out.println(DateUtil.getChineseToday());
			long utc0 = DateUtil.dateToUTC("20130809", "yyyyMMdd", TimeZone.getTimeZone("America/Los_Angeles"));
			long utc1 = DateUtil.dateToUTC("20130810", "yyyyMMdd", TimeZone.getTimeZone("America/Los_Angeles"));
			String date0 = DateUtil.formatDateByUTC(utc0, "yyyyMMddHHmmss");
			String date1 = DateUtil.formatDateByUTC(utc1, "yyyyMMddHHmmss");
			System.out.println(date0);
			System.out.println(date1);

			String[] day = DateUtil.getMonthDay(new Date(),0);
			System.out.println(day[0]);
			System.out.println(day[1]);

			System.out.println(DateUtil.addMonth(new Date(),1,-1));

		} catch (Exception e) {
			//			e.printStackTrace();
		}
	}

	public static String[] getFirstLastDate(Date date,String formate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		cal.set(java.util.Calendar.DATE, 1);
		Date firstDate = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDate = cal.getTime();
		
		return new String[] {
				formatDate(firstDate, formate),
				formatDate(lastDate, formate)
		};
	}



}
