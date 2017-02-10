package com.gdyunst.coderobot.framework.service;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 功能描述:接口;
 * @author Jieyq
 * @date 2016年9月15日 下午11:41:36
 */
public interface ICodeRobotService  {

	
	
	
	/**
	 * 生成代码
	 * @param tarPath
	 * @param tableName
	 * @param author
	 * @return
	 */
	public String createCode(String tarPath, String tableName, String author);
	
	
	
	/**
	 * 生成评估中心模块映射代码
	 * @param tarPath  entity目录
	 * @param tableName 表名
	 * @param author 作者
	 * @return
	 */
	public String createEvaluDBCode(String tarPath, String tableName, String author);
	
	
	
	/**
	 * 功能描述:生成controller
	 * @param contrlName
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年12月11日 上午2:20:54
	 */
	public String createCodeRobot(String tarPath,String tableName,String author);



	/**
	 * 功能描述:生成实体类
	 * @param tarPath
	 * @param tableName
	 * @param author
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年12月19日 下午9:07:58
	 */
	public String createEntity(String tarPath, String tableName, String author);
	
	
}