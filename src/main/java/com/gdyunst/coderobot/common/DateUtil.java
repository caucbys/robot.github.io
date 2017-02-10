package com.gdyunst.coderobot.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述:时间日期处理工具类;
 * 创建日期:2016年9月6日
 * @author liujun
 * Created by Eclipse
 */
public abstract class DateUtil
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);  // 日志工具;
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";           // 默认日期格式;
	private static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";  // 默认时间格式;
	
	// G: Era designator;
	// y: Year;
	// M: Month in Year;
	// w: Week in Year;
	// W: Week in month;
	// M: Month in year;
	// D: Day in year;
	// d: Day in month;
	// E: Day name in week;
	// u: Day number of week;
	// a: Am/pm marker;
	// H: Hour in day(0-23);
	// k: Hour in day(1-24);
	// K: Hour in am/pm(0-11);
	// h: Hour in am/pm(1-12);
	// m: Minute in hour;
	// s: Second in minute;
	// S: Millisecond;
	// z: Time zone;
	// Z: Time zone(RFC822);
	// X: Time zone;
	
	/**
	 * 功能描述:将java.util.Date类型的日期对象格式化为字符串;
	 * @param date java.util.Date对象
	 * @return 'yyyy-MM-dd'格式的字符串
	 * 创建时间:2016年9月7日
	 * @Author liujun
	 */
	public static String toString(Date date)
	{
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
	}
	
	/**
	 * 功能描述:将java.util.Date对象格式化为给定格式的字符串;
	 * @param date java.util.Date对象
	 * @param format 日期格式
	 * @return 给定格式的日期字符串
	 * 创建时间:2016年9月8日
	 * @Author liujun
	 */
	public static String toString(Date date, String format)
	{
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 功能描述:将日期字符串格式化为java.util.Date对象;
	 * @param dateStr 'yyyy-MM-dd HH:mm:ss'格式的日期字符串
	 * @return 转换成功返回java.util.Date对象,转换失败返回null
	 * 创建时间:2016年9月8日
	 * @Author liujun
	 */
	public static Date toDate(String dateStr)
	{
		try
		{
			return new SimpleDateFormat(DEFAULT_TIME_FORMAT).parse(dateStr);
		}
		catch(ParseException e)
		{
			LOGGER.warn("日期格式错误");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 功能描述:将日期字符串转换为java.util.Date对象;
	 * @param dateStr 日期字符串
	 * @param format 日期格式
	 * @return 转换成功返回java.util.Date对象,转换失败返回null
	 * 创建时间:2016年9月8日
	 * @Author liujun
	 */
	public static Date toDate(String dateStr, String format)
	{
		try
		{
			return new SimpleDateFormat(format).parse(dateStr);
		}
		catch(ParseException e)
		{
			LOGGER.warn("日期格式错误");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 功能描述:将日期字符串转换为java.util.Date对象;
	 * @param dateStr 日期字符串
	 * @param format 日期格式
	 * @return 转换成功返回java.util.Date对象,转换失败返回null
	 * 创建时间:2016年9月8日
	 * @Author liujun
	 */
	public static Date toDate(String dateStr, Locale uk)
	{
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
	    try
	    {
	    	Date date=sdf1.parse(dateStr);
	    	return date;
	    }
	    catch (ParseException e)
	    {
	    	LOGGER.warn("日期格式错误");
			e.printStackTrace();
			return null;
	    }
	}
	
	/**
	 * 功能描述:
	 * @param date
	 * @param format
	 * @return
	 * 创建时间:2016年9月8日
	 * @Author liujun
	 */
	@Deprecated
	public static String toDateStr(Date date,String format)
	{
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 功能描述:将字符串格式的日期转换为java.util.Date对象
	 * @param dateStr 日期字符串
	 * @param format 日期格式
	 * @return 转换成功,返回java.util.Date对象;转换失败,返回null
	 * 创建时间:2016年9月7日
	 * @Author liujun
	 */
	public static Date parse(String dateStr, String format)
	{
		DateFormat df = new SimpleDateFormat(format);
		try 
		{
			return df.parse(dateStr);
		} 
		catch(ParseException e) 
		{
			// 记录异常信息;
			LOGGER.error("日期格式错误!!!");
			return null;
		}
	}
	
	/**
	 * 功能描述:获取当前系统 时间;
	 * @return java.util.Date对象
	 * 创建时间:2016年9月7日
	 * @Author liujun
	 */
	public static Date now() 
	{
		return new Date(System.currentTimeMillis());
	}
	
}
