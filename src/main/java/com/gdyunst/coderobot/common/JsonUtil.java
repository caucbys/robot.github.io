package com.gdyunst.coderobot.common;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 功能描述:JSON处理工具类;
 * 创建日期:2016年9月7日
 * @author liujun
 * Created by Eclipse
 */
public abstract class JsonUtil
{
	// 成员属性;
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);  // 日志工具;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	// 成员方法;
	/**
	 * 功能描述:将Java对象转换为JSON字符串;
	 * @param object Java对象
	 * @return 转换成功返回JSON字符串,转换失败返回空串
	 * 创建时间:2016年9月8日
	 * @Author liujun
	 */
	public static String toJson(Object object)
	{
		try(Writer strWriter = new StringWriter())
		{
			MAPPER.writeValue(strWriter, object);
			return strWriter.toString();
		}
		catch(JsonGenerationException e)
		{
			LOGGER.warn("JSON转换失败!!!");
			e.printStackTrace();
		
			return "";
		}
		catch(JsonMappingException e)
		{
			LOGGER.warn("JSON转换失败!!!");
			e.printStackTrace();
			return "";
		}
		catch(IOException e)
		{
			LOGGER.warn("JSON转换失败!!!");
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 功能描述:将JSON格式字符串转换为给定类型的Java对象;
	 * @param jsonStr JSON字符串
	 * @param clazz Java对象
	 * @return 转换成功返回Java对象,转换失败返回null
	 * 创建时间:2016年9月8日
	 * @Author liujun
	 */
	public static <T> T toObject(String jsonStr, Class<T> clazz) 
	{
		try
		{
			return MAPPER.readValue(jsonStr, clazz);
		}
		catch(JsonParseException e)
		{
			LOGGER.warn("JSON转换失败!!!");
			e.printStackTrace();
			return null;
		}
		catch(JsonMappingException e)
		{
			LOGGER.warn("JSON转换失败!!!");
			e.printStackTrace();
			return null;
		}
		catch(IOException e)
		{
			LOGGER.warn("JSON转换失败!!!");
			e.printStackTrace();
			return null;
		}
	}
	
}
