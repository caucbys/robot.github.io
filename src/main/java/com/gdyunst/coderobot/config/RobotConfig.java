package com.gdyunst.coderobot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:数据库一些具体状态值的配置
 * 
 * @Author:denghsuai
 * @Date:2016年11月23日 下午1:44:40
 */
@ConfigurationProperties(prefix = "RobotConfig")
@Configuration
public class RobotConfig{

	private String dbdrivername;
	private String dburl;
	private String dbusername;
	private String dbpassword;
	public String getDbdrivername() {
		return dbdrivername;
	}
	public void setDbdrivername(String dbdrivername) {
		this.dbdrivername = dbdrivername;
	}
	public String getDburl() {
		return dburl;
	}
	public void setDburl(String dburl) {
		this.dburl = dburl;
	}
	public String getDbusername() {
		return dbusername;
	}
	public void setDbusername(String dbusername) {
		this.dbusername = dbusername;
	}
	public String getDbpassword() {
		return dbpassword;
	}
	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	
			
}
