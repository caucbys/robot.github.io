package com.gdyunst.coderobot.framework.service;

public interface ICodeRobotServiceV3 {

	
	/**
	 * 
	* 功能描述:生成实体对象;
	* @param tarPath 
	* @param tName
	* @param author
	* @return 方法返回值说明
	* @Author baiqing
	* @Date 2017年1月11日上午9:57:58
	 */
	public String createEntity(String tableName,String author,String entpackageType,String workspace);
	public String createDomain(String tableName,String author,String dmpackageType,String entpackageType,String workspace);
	public String createService(String tarPath,String tableName,String author);
	public String createXmlMapper(String tableName,String author,String entpackageType,String dmpackageType,String workspace);
	

	
	
}
