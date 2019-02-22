package com.sinosoft.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**yyyy-MM-dd HH:mm:ss
 * @author Administrator
 *
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils{

	/**
	 * &#26684;&#24335;&#21270;&#24403;&#21069;&#26102;&#38388;
	 * @param format
	 * @return 日期字串
	 */
	public static String getCurrentDateString(String format){
		SimpleDateFormat f1=new SimpleDateFormat(format);
		return f1.format(new Date());
	}
	/**根据format格式化日期dateString
	 * @param format &#26684;&#24335;&#21270;&#23383;&#31526;&#20018;
	 * @param dateString &#26085;&#26399;&#23383;&#31526;&#20018;
	 * @return 日期字串
	 */
	private static String getFormatDateString(String format,String dateString){
		try {
			SimpleDateFormat f1=new SimpleDateFormat(format);
			 Date date = f1.parse(dateString);
			 return f1.format(date);
		} catch (ParseException e) {
			System.err.println("format:"+format+" dateString:"+dateString);
		}
		return null;
	}
	
	/**
	 * &#20197;&#108;&#111;&#99;&#97;&#108;&#101;&#30340;&#26085;&#26399;&#21644;&#102;&#111;&#114;&#109;&#97;&#116;&#26684;&#24335;&#21270;&#23383;&#31526;&#20018;&#100;&#97;&#116;&#101;&#83;&#116;&#114;&#105;&#110;&#103;
	 * @param format 格式
	 * @param dateString 有效日期这串
	 * @param locale 日期区域对象
	 * @return 日期对象
	 */
	private static Date getFormatDate(String format, String dateString,
			Locale locale) {
		SimpleDateFormat f = null;
		Date date = null;
		try {
			if (locale == null) {
				f = new SimpleDateFormat(format);
				date = f.parse(dateString);

			} else {
				f = new SimpleDateFormat(format, locale);
				date = f.parse(dateString);
			}
		
		} catch (ParseException e) {
			System.err.println("locale:"+locale==null?Locale.getDefault():locale+" format:"+format+" dateString:"+dateString);
		}
		return date;
	}
	/**&#26412;&#26426;&#22120;&#30340;&#102;&#111;&#114;&#109;&#97;&#116;&#26684;&#21270;&#100;&#97;&#116;&#101;&#83;&#116;&#114;&#105;&#110;&#103;
	 * @param format 格式
	 * @param dateString  有效日期这串
	 * @return 日期对象
	 */
	private static Date getFormatDate(String format,String dateString ){
		return getFormatDate(format,dateString,null);
	}
	
	/**&#24403;&#21069;&#26085;&#26399;&#108;&#105;&#107;&#101;&#32;&#39;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#39;
	 * @return &#24403;&#21069;&#26085;&#26399;&#108;&#105;&#107;&#101;&#32;&#39;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#39;
	 */
	public static String getCurrenDateString(){
		return getCurrentDateString("yyyy-MM-dd");
	}
	/**&#24403;&#21069;&#26085;&#26399;&#32;&#108;&#105;&#107;&#101;&#32;&#39;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#32;&#72;&#72;&#58;&#109;&#109;&#58;&#115;&#115;&#39;
	 * @return &#24403;&#21069;&#26085;&#26399;&#32;&#108;&#105;&#107;&#101;&#32;&#39;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#32;&#72;&#72;&#58;&#109;&#109;&#58;&#115;&#115;&#39;
	 */
	public static String getCurrenDateTimeString(){
		return getCurrentDateString("yyyy-MM-dd HH:mm:ss");
	}

	/**&#25226;&#25152;&#26377;&#30340;&#34920;&#31034;&#26085;&#26399;&#32;&#37117;&#26684;&#24335;&#21270;&#20026;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#32;&#72;&#72;&#58;&#109;&#109;&#58;&#115;&#115;
	 * @param htmlStr
	 * @return &#26085;&#26399;&#23383;&#20018;
	 */
	private static String reDate(String htmlStr) {
//		 String regex =
//		 "((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\._年月日\\s]((((0?[13578])|(1[02]))[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\._年月日\\s]((((0?[13578])|(1[02]))[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\._年月日\\s]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\._年月日\\s]((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		// htmlStr = htmlStr.replace("年", "-").replace("月", "-").replace("日",
		// "").replace("时", ":").replace("分", ":").replace("秒", "").replace("：",
		// ":");
		htmlStr = htmlStr.replaceAll("年|月", "-").replaceAll("日|秒|号", "")
				.replaceAll("点|时|分|：", ":");
		// System.out.println(htmlStr);
//		String regex = "((\\d?[\\-\\/\\._\\s]?((((0?[13578])|(1[02]))[\\-\\/\\._\\s]((([1-2][0-9])|(3[01])|0?[1-9])))|(((0?[469])|(11))[\\-\\/\\._\\s]((([1-2][0-9])|(30)|0?[1-9])))|(0?2[\\-\\/\\._\\s](([1-2][0-9])|(0?[1-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\._\\s]((((0?[13578])|(1[02]))[\\-\\/\\._\\s](([1-2][0-9])|(3[01])|(0?[1-9])))|(((0?[469])|(11))[\\-\\/\\._\\s](([1-2][0-9])|(30)|(0?[1-9])))|(0?2[\\-\\/\\._\\s]((1[0-9])|(2[0-8])|(0?[1-9]))))))";
//		String regex = "(([19|20]?\\d{2})[\\-\\/\\._\\s](0?[1-9]|1[012])[\\-\\/\\._\\s](0?[1-9]|[10-31]))";
		String regex = "(((19|20)?\\d{2})[\\-\\/\\._\\s](1[012]|0?[1-9])[\\-\\/\\._\\s](([1-2][0-9])|(3[01])|0?[1-9]))";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		Matcher m = p.matcher(htmlStr);
		String txtDate = "";
		String txtTime = "";
		String str = "";

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowDate = df.format(date);

		if (m.find()) {
			str = m.group().trim();
			str = str.replaceAll("\\/", "-").replaceAll("\\.", "-").replaceAll(
					"_", "-").replace(" ", "-");
			if (str.length() <= 5 && str.indexOf("-") != -1) {
				str = nowDate.substring(0, 5) + str;
			} else if (str.length() < 10
					&& str.substring(0, str.indexOf("-")).length() < 4) {
				str = "20" + str;
			}
			txtDate = str.trim();
		}

		if (!"".equals(txtDate)) {
			String regexTime = "(\\s?(1[0-9]|2[0-3]|0?[1-9])(:[0-5]?\\d){1,2})";
			p = Pattern.compile(regexTime, Pattern.CASE_INSENSITIVE);
			m = p.matcher(htmlStr);
			if (m.find()) {
				txtTime = m.group().trim();
			}
		}

		if ("".equals(txtDate.trim())) {
			txtDate = nowDate;
		} else {
			if (!"".equals(txtTime)) {
				
				txtDate = txtDate + " " + txtTime;
				if(txtTime.length()<=5){
					txtDate=txtDate+":00";
				}
			} else {
				txtDate = txtDate + " 00:00:00";
			}
		}

		try {
			Date date2 = df.parse(txtDate);
			if (date2.getTime() > System.currentTimeMillis()) {
				txtDate = nowDate;
			} else {
				txtDate = df.format(date2);
			}

			// System.out.println(strDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
			txtDate = nowDate;
		}

		return txtDate;
	}

	/**&#25226;&#25152;&#26377;&#30340;&#34920;&#31034;&#26085;&#26399;&#32;&#37117;&#26684;&#24335;&#21270;&#20026;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#32;&#72;&#72;&#58;&#109;&#109;&#58;&#115;&#115;
	 * @param htmlStr
	 * @return 日期字串
	 */
	private static String reDateLink(String htmlStr) {
		htmlStr = htmlStr.replace("年", "-").replace("月", "-").replace("日", "");
		htmlStr = htmlStr.replaceAll("\\/", "-").replaceAll("\\.", "-")
				.replaceAll("_", "-").replace("(", "").replace(")", "")
				.replace("\r", "").replace("\t", "").replace("\n", "").trim();
		return htmlStr;
	}
	/** 得到当前日期
	 * @return
	 */
	public static Date getCurrentDateTime(){
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}
	/** &#23545;&#32;&#100;&#97;&#116;&#97;&#32;&#26684;&#24335;&#21270;&#32;&#36755;&#20986;&#32;
	 * @param date
	 * @param format  &#20026;&#110;&#117;&#108;&#108;&#26102;&#32;&#36755;&#20986;&#26684;&#24335;&#21270;&#32;&#121;&#121;&#121;&#121;&#45;&#77;&#77;&#45;&#100;&#100;&#32;
	 * @return			
	 */
	public static String getDateString(Date date,String format){
		if(format==null){
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * @param dataStr         like(20120513)
	 * @param oldFormat        like (yyyyMMdd)
	 * @param newFormat			like (yyyy-MM-dd)
	 * @return					like(2012-05-13)
	 */
	public static String getDataFormate(String dataStr,String oldFormat,String newFormat){
		String re = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
			Date date = sdf.parse(dataStr);
			SimpleDateFormat sdf1 = new SimpleDateFormat(newFormat);
			re=sdf1.format(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
			
		}
		return re;
	}
	/**&#25226;&#117;&#116;&#105;&#108;&#20013;&#30340;&#100;&#97;&#116;&#101;&#36716;&#20026;&#32;&#115;&#113;&#108;&#20013;&#30340;&#100;&#97;&#116;&#101;
	 * @param date
	 * @return
	 */
	private static java.sql.Date UtilDateTosqlDate(Date date){
		java.sql.Date d = new java.sql.Date(date.getTime());
		return d;
	}
	/**&#24471;&#21040;&#32;&#32;&#100;&#97;&#116;&#97;&#32;&#20559;&#31227;&#20026;&#32;&#111;&#102;&#102;&#115;&#101;&#116;&#32;&#30340;&#25152;&#26377;&#32;&#26085;&#26399;
	 * @param date
	 * @param offset
	 * @return
	 */
	private static Date[] getdateStrList(Date date, int offset) {
		Date[] re = null;
		if (offset != 0) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			re = new Date[Math.abs(offset)+1];
			int flag = 0;
			if (offset < 0) {
				flag = -1;
			} else {
				flag = 1;
			}
			for (int i = 0; i < re.length; i++) {
				if (i != 0) {
					c.add(Calendar.DATE, flag);
				}
				re[i] = c.getTime();
			}
		} else {
			re = new Date[1];
			re[0] = date;
		}
		return re;
	}
	/**&#24471;&#21040;&#32;&#32;&#72;&#111;&#117;&#114;&#32;&#20559;&#31227;&#20026;&#32;&#111;&#102;&#102;&#115;&#101;&#116;&#32;&#30340;&#25152;&#26377;&#32;&#26085;&#26399;
	 * @param date
	 * @param offset
	 * @return
	 */
	private static Date[] getHoursList(Date date, int offset) {
		Date[] re = null;
		if (offset != 0) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			re = new Date[Math.abs(offset)];
			int flag = 0;
			if (offset < 0) {
				flag = -1;
			} else {
				flag = 1;
			}
			for (int i = 0; i < re.length; i++) {
				if (i != 0) {
					c.add(Calendar.HOUR_OF_DAY, flag);
				}
				re[i] = c.getTime();
			}
		} else {
			re = new Date[1];
			re[0] = date;
		}
		return re;
	}
	/**&#20004;&#26085;&#26399;&#30456;&#24046;&#22810;&#23569;&#22825;
	 * @param startDate
	 * @param endDate
	 * @return endDate-startDate
	 */
	public static long getdiffdays(Date startDate,Date endDate){
		long re = 0;
		long l = endDate.getTime() - startDate.getTime();
	    re = l / (1000 * 60 * 60 * 24);
	    if(re==0){
	    	Calendar c = Calendar.getInstance(); 
	    	c.setTime(startDate);
	    	Calendar c2 = Calendar.getInstance(); 
	    	c2.setTime(endDate);
	    	if(c.get(Calendar.DATE)!=c2.get(Calendar.DATE)){
	    		re=1;
	    	}
	    }
	    return re;
	}
	/**&#36820;&#22238;&#26085;&#26399;&#21306;&#22495;&#32;&#30340;&#24320;&#22987;&#26085;&#26399;&#19982;&#32467;&#26463;&#26085;&#26399;
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date[] getdateStartEndData(Date date, int offset) {
		Date[] re = new Date[2];
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		try {
			if (offset!= 0) {
				Calendar end = Calendar.getInstance();
				if (offset < 0) {
					c.add(Calendar.DATE, offset);
					re[0] = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
					end.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date)));
					end.add(Calendar.DAY_OF_MONTH,1);
					end.add(Calendar.SECOND,-1);
					re[1] = end.getTime();
	 
				} else {
					c.add(Calendar.DAY_OF_MONTH,1);
					c.add(Calendar.SECOND,-1);
					re[0] = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
					end.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(date)));
					end.add(Calendar.DATE, offset);
					end.add(Calendar.DAY_OF_MONTH,1);
					end.add(Calendar.SECOND,-1);
					re[1] = end.getTime();
				}
			} else {
				re[0] = new SimpleDateFormat("yyyy-MM-dd")
						.parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
				c.setTime(re[0]);
				c.add(Calendar.DAY_OF_MONTH, 1);
				c.add(Calendar.SECOND, -1);
				re[1] = c.getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return re;
	}
	/**&#32473;&#23450;&#26085;&#26399;&#32;&#36820;&#22238;&#24403;&#21069;&#26085;&#26399;&#30340;&#21608;&#19968;&#21644;&#21608;&#26085;
	 * @param date
	 * @return
	 */
	private static Date[] getStartWeekAndEndWeek(Date date){
		Date[] re = new Date[2];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int   curDay   =   cal.get(Calendar.DAY_OF_WEEK); 
		curDay = curDay-2;
		if(curDay<0){
			curDay=6;
		}
		 cal.add(Calendar.DATE   , 0-curDay  ); 
		 re[0] = cal.getTime();
		 cal.add( Calendar.DATE  ,  6); 
		 re[1] =  cal.getTime();
		return re;
	}
	/**&#32473;&#23450;&#26085;&#26399;&#32;&#36820;&#22238;&#24403;&#21069;&#26085;&#26399;&#30340;&#26412;&#26376;&#30340;&#36215;&#22987;&#21644;&#32467;&#26463;&#26085;&#26399;
	 * @param date
	 * @return
	 */
	public static Date[] getStartMonthAndEndMonth(Date date){
		Date[] re = new Date[2];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day=cal.get(Calendar.DAY_OF_MONTH);
//		System.out.println(day );
		 cal.add(Calendar.DATE   , 0-day+1  ); 
		 re[0] = cal.getTime();
		 cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);   
		// 设置Calendar日期为下一个月一号   
		 cal.set(Calendar.DATE, 1);   
		// 设置Calendar日期减一,为本月末   
		 cal.add(Calendar.DATE, -1);
		 re[1] =  cal.getTime();
		return re;
	}


	/**
	 * &#36820;&#22238;&#24180;&#20221;
	 * 
	 * @param date
	 *            &#26085;&#26399;
	 * @return &#36820;&#22238;&#24180;&#20221;
	 */
	private static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * &#36820;&#22238;&#26376;&#20221;
	 * 
	 * @param date
	 *             &#26085;&#26399;
	 * @return &#36820;&#22238;&#26376;&#20221;
	 */
	private static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * &#36820;&#22238;&#26085;&#20221;
	 * 
	 * @param date
	 *             &#26085;&#26399;
	 * @return &#36820;&#22238;&#26085;&#20221;
	 */
	private static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * &#36820;&#22238;&#23567;&#26102;
	 * 
	 * @param date
	 *            &#26085;&#26399;
	 * @return &#36820;&#22238;&#23567;&#26102;
	 */
	private static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * &#36820;&#22238;&#20998;&#38047;
	 * 
	 * @param date
	 *           &#26085;&#26399;
	 * @return &#36820;&#22238;&#20998;&#38047;
	 */
	private static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 *&#36820;&#22238;&#31186;&#38047;
	 * 
	 * @param date
	 *            &#26085;&#26399;
	 * @return &#36820;&#22238;&#31186;&#38047;
	 */
	private static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * &#36820;&#22238;&#27627;&#31186;
	 * 
	 * @param date
	 *            &#26085;&#26399;
	 * @return &#36820;&#22238;&#27627;&#31186;
	 */
	private static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * &#26085;&#26399;&#30456;&#21152;
	 * 
	 * @param date
	 *            &#26085;&#26399;
	 * @param day
	 *            &#22825;&#25968;
	 * @return &#36820;&#22238;&#30456;&#21152;&#21518;&#30340;&#26085;&#26399;
	 */
	private static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * &#26085;&#26399;&#30456;&#20943;
	 * 
	 * @param date
	 *            &#26085;&#26399;
	 * @param date1
	 *            &#26085;&#26399;
	 * @return &#36820;&#22238;&#30456;&#20943;&#21518;&#30340;&#26085;&#26399;
	 * date-date1
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}
	/**&#26085;&#26399;&#30456;&#20943;&#32;date-date1
	 * @param date &#26085;&#26399;
	 * @param date1 &#26085;&#26399;
	 * @return &#36820;&#22238;&#30456;&#20943;&#21518;&#30340;&#20998;&#38047;
	 */
	public static int diffMinute(Date date, Date date1){
		return (int) ((getMillis(date) - getMillis(date1)) / (60*1000));
	}


	/**
	 * &#35745;&#31639;&#20256;&#20837;&#20540;&#26159;&#21542;&#26143;&#26399;&#20960;
	 * 
	 * @param date &#26085;&#26399;
	 * @param week &#26143;&#26399;&#20960;
	 * @return boolean - &#26159;&#36820;&#22238;true
	 */
	private boolean checkWeekNum(Date date, int week) {
		boolean flag = false;
		int realWeek = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		realWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (realWeek == (week + 1)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Description: &#33719;&#21462;&#71;&#77;&#84;&#56;&#26102;&#38388;
	 * 
	 * @return &#23558;&#24403;&#21069;&#26102;&#38388;&#36716;&#25442;&#20026;&#71;&#77;&#84;&#56;&#26102;&#21306;&#21518;&#30340;Date
	 */
	private static Date getGMT8Time() {
		Date gmt8 = null;
		try {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"),
					Locale.CHINESE);
			Calendar day = Calendar.getInstance();
			
			day.set(Calendar.YEAR, cal.get(Calendar.YEAR));
			day.set(Calendar.MONTH, cal.get(Calendar.MONTH));
			day.set(Calendar.DATE, cal.get(Calendar.DATE));
			day.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
			day.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
			day.set(Calendar.SECOND, cal.get(Calendar.SECOND));
			gmt8 = day.getTime();
		} catch (Exception e) {
			System.out.println("获取GMT8时间 getGMT8Time() error !");
			e.printStackTrace();
			gmt8 = null;
		}
		return gmt8;
	}

	/**
	 * @param date &#26085;&#26399;
	 * @return &#36820;&#22238;&#24403;&#21069;&#30340;&#27627;&#31186;&#32;&#25968;&#20540;
	 */
	private static int getMillisecond(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		return calender.get(Calendar.MILLISECOND);
	}

	/**
	 * @param date &#26085;&#26399;
	 * @param offset &#20559;&#31227;&#22825;&#25968;
	 * @return &#23545;&#24212;&#26085;&#26399;&#32;&#20559;&#31227;&#21518;&#30340;&#26085;&#26399;
	 */
	private static Date getOffsetDate(Date date, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, offset);
		return c.getTime();
	}

    /**&#25226;&#19981;&#21516;&#26684;&#24335;&#26085;&#26399;&#32;&#21435;&#38500;&#32;&#21482;&#20445;&#30041;&#25968;&#23383;&#26085;&#26399;&#32;
     * @param dateStr &#19981;&#21516;&#26684;&#24335;&#26159;&#26085;&#26399;&#32;
     * @return &#24471;&#21040;&#25968;&#23383;&#22411;&#26085;&#26399;&#32;&#32;  
     */
	public static String getDateTimeNum(String dateStr){
    	return dateStr.replaceAll("[^0-9]", "");
    }
    /**&#25226;&#100;&#97;&#116;&#101;&#83;&#116;&#114;&#26085;&#26399;&#32;&#23383;&#20018;&#32;&#25353;&#32;&#102;&#111;&#114;&#109;&#109;&#115;&#116;&#114;&#32;&#26684;&#24335;&#36755;&#20986;
     * @param dateStr &#26085;&#26399;&#23383;&#20018;
     * @param formstr &#26684;&#24335;&#36755;&#20986; &#40664;&#35748;&#26684;&#24335;&#20026;yyyy-MM-dd
     * @return 
     */
    public static String getFormatDateByDateTimeNum(String dateStr,String formstr){
    	Date date = getFormatDateByDateTime(dateStr);
    	return getDateString(date,formstr);
    }
    /**&#36890;&#29992;&#26085;&#26399;&#23383;&#20018;&#26684;&#21270;&#20026;&#68;&#97;&#116;&#101;
     * @param dateStr &#26085;&#26399;&#23383;&#20018;
     * @return 
     */
    public static Date getFormatDateByDateTime(String dateStr){
    	String dateNum = getDateTimeNum(dateStr);
    	String oldFormat = "yyyyMMddHHmmss".substring(0,dateNum.length());
    	return getFormatDate(oldFormat,dateNum);
    }
	
	/** 
	 * @return &#26684;&#24335;&#21270;&#20026;&#32; yyyyMMddHHmmss
	 */
	public static String getCurrentTime(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(c.getTime());
	}
	
	/**
	 * 比较两个日期 差多少年 end-start
	 * @param start 开始时间 
	 * @param end 结束时间
	 * @return
	 */
	public static String getDateCount(String start,String end){
		Calendar calendar = Calendar.getInstance();
		int endY = calendar.get(Calendar.YEAR);
		int endM = calendar.get(Calendar.MONTH) + 1;
		int endD = calendar.get(Calendar.DAY_OF_MONTH);
		int startY = Integer.valueOf(start.substring(0, 4));
		int startM = Integer.valueOf(start.substring(4, 6));
		int startD = Integer.valueOf(start.substring(6));
		String str = null;
		if (startM != endM) {
			if (startM < endM) {
				 str= (endY - startY + 1)+"";
			} else {
				 str=""+(endY - startY);
			}
		} else {
			if (startD <= endD) {
				str=(endY - startY + 1)+"";
			} else if (startD > endD) {
				str=""+(endY - startY);
			}
		}
		return str;
	}
	
	/** 计算年龄 */ 
	public  static String getAge(Date birthDay)  { 
	        Calendar cal = Calendar.getInstance(); 

	        if (cal.before(birthDay)) { 
	            throw new IllegalArgumentException( 
	                "The birthDay is before Now.It's unbelievable!"); 
	        } 

	        int yearNow = cal.get(Calendar.YEAR); 
	        int monthNow = cal.get(Calendar.MONTH)+1; 
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
	        
	        cal.setTime(birthDay); 
	        int yearBirth = cal.get(Calendar.YEAR); 
	        int monthBirth = cal.get(Calendar.MONTH)+1; 
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 

	        int age = yearNow - yearBirth; 

	        if (monthNow <= monthBirth) { 
	            if (monthNow == monthBirth) { 
	                if (dayOfMonthNow < dayOfMonthBirth) { 
	                    age--; 
	                } 
	            } else { 
	                age--; 
	            } 
	        } 
	        return age +""; 
	    }

	/**
	 * 计算年龄
	 * @param birthDay
	 * @return
	 */
	public  static String getAge(String birthDay)  {
		return getAge(getFormatDateByDateTime(birthDay));
	}
	
	/**
	 * 获取当前的时间加上指定的年份
	 * @param str 入参是年份
	 * @return
	 */
	public static String getDateymm(String str){
		if("".equals(str)){
			return "";
		}else{
			int year=Integer.parseInt(str);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
			Calendar c=Calendar.getInstance();
			c.setTime(getCurrentDateTime());
			c.add(Calendar.YEAR, year);
			String date= sdf.format(c.getTime());
			return date;
		}
		
	}
	
	public static void main(String[] args) throws ParseException {
		
		System.out.println(getFormatDateByDateTimeNum("2014uu08","yyyy-MM"));
	}
}
