/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gdyunst.coderobot.framework.restfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdyunst.coderobot.framework.service.ICodeRobotServiceV3;
import com.gdyunst.coderobot.framework.service.INewCodeRobotService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 代码机器人
 * @author Jieyq
 * @Time 2016-09-15
 */
@Api(value = "CodeRobotController", description = "代码机器人相关API")
@RestController
public class CodeRobotController  {
//	@Autowired
//	private ICodeRobotService codeRobotService;
	
	@Autowired
	private ICodeRobotServiceV3 codeRobotServiceV3;

	@Autowired
	private INewCodeRobotService newcodeRobotService;
//	/**
//	 * 根据数据表创建实体类与Mapper类接口
//	 * @param picdata
//	 * @param name 不同的图片会存到不同路径  主要按：车牌照
//	 * @return
//	 */
//	
//	@ApiOperation(httpMethod = "POST", value = "创建实体类与Mapper类接口",response=Result.class)
//	@ApiImplicitParams({
//    	@ApiImplicitParam(paramType="query",name="tarPath",dataType="String",required=true,value="目标路径"),
//    	@ApiImplicitParam(paramType="query",name="tableName",dataType="String",required=true,value="库表名称"),
//    	@ApiImplicitParam(paramType="query",name="author",dataType="String",required=true,value="作者(如：Jieyq)")
//    })
//    @ApiResponses({
//    	@ApiResponse(code=400,message="请求参数没填好"),
//    	@ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
//    })
//    @RequestMapping("/codeRobot")
//    public String upload(@RequestParam(value="tarPath",required=true) String tarPath,
//    		@RequestParam(value="tableName",required=true) String tableName,
//    		@RequestParam(value="author",required=true) String author){
//		
//		String str=codeRobotService.createCode(tarPath,tableName,author);
//		return str;
//    }
//	
//	
//	/**
//	 * 根据数据表创建实体类与Mapper类接口
//	 * @param picdata
//	 * @param name 不同的图片会存到不同路径  主要按：车牌照
//	 * @return
//	 */
//	
//	@ApiOperation(httpMethod = "GET", value = "一键生成控制器层和服务层基础代码")
//    @RequestMapping("/createCode")
//    public String createCode(@RequestParam(value="tarPath") String tarPath,
//    		@RequestParam(value="tableName") String tableName,
//    		@RequestParam(value="author") String author){
//		
//		String str=codeRobotService.createCodeRobot(tarPath,tableName,author);
//		return str;
//    }
//	
	
	/**
	 * 功能描述:生成entity
	 * @param tarPath
	 * @param tableName
	 * @param author
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年12月21日 下午1:51:19
	 *//*
	@ApiOperation(httpMethod = "GET", value = "一键生成实体类")
    @RequestMapping("/createEntity")
    public String createEntity(@RequestParam(value="tarPath") String tarPath,
    		@RequestParam(value="tableName") String tableName,
    		@RequestParam(value="author") String author){
		if(!tarPath.contains("domain.entity.")){
			return "生成失败,输入的包名必须含有.domain.entity.";
		}
		String str=codeRobotServiceV3.createEntity(tarPath,tableName,author,entpackageType,dmpackageType);
		
		return str;
    }*/
	/**
	 * 功能描述:生成domain
	 * @param tarPath
	 * @param tableName
	 * @param author
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年12月21日 下午1:51:19
	 */
//	@ApiOperation(httpMethod = "GET", value = "一键生成实体类")
//	@RequestMapping("/createDomain")
//	public String createDomain(@RequestParam(value="tarPath") String tarPath,
//			@RequestParam(value="tableName") String tableName,
//			@RequestParam(value="author") String author,
//    		@RequestParam(value="dmpackageType") String dmpackageType){
//		if(!tarPath.contains("domain.entity.")){
//			return "生成失败,输入的包名必须含有.domain.entity.";
//		}
//		String str=codeRobotServiceV3.createDomain(tarPath,tableName,author,dmpackageType);
//		return str;
//	}
	
	/**
	 * 功能描述:生成mapper
	 * @param tarPath
	 * @param tableName
	 * @param author
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年12月21日 下午2:49:02
	 */
	@ApiOperation(httpMethod = "GET", value = "")
    @RequestMapping("/createAll")
    public String createMapper(/*@RequestParam(value="tarPath") String tarPath,*/
    		@RequestParam(value="tableName") String tableName,
    		@RequestParam(value="author") String author,
    		@RequestParam(value="workspace") String workspace){
		String entpackageType="";
		String dmpackageType="";
		String servieDm="com.gdyunst.kckpservice.framework.domain";
		String servieEn="com.gdyunst.kckpcomm.entity.service";
		String adminDm="com.gdyunst.kckpadmin.framework.domain";
		String adminEn="com.gdyunst.kckpcomm.entity.admin";
		String evalDm="com.gdyunst.kckpeval.framework.domain";
		String evalEn="com.gdyunst.kckpcomm.entity.eval";
		if(tableName==null||"".equals(tableName.trim())||tableName.indexOf("_")<=0){
			return "tableName不正正确";
		}
		String system=tableName.split("_")[0];
		if(system!=null&&!"".equals(system.trim())){
			if("admin".equals(system.trim().toLowerCase())){
				dmpackageType=adminDm;
				entpackageType=adminEn;
			}else if("service".equals(system.trim().toLowerCase())){
				dmpackageType=servieDm;
				entpackageType=servieEn;
			}else if("eval".equals(system.trim().toLowerCase())){
				dmpackageType=evalDm;
				entpackageType=evalEn;
			}else{
				return "system参数现在只支持\"admin\"或者\"service\"两个系统";
			}
		}else{
			return "system参数现在只支持\"admin\"或者\"service\"两个系统";
		}
		String str=codeRobotServiceV3.createXmlMapper(tableName,author,entpackageType,dmpackageType,workspace);
		str += codeRobotServiceV3.createEntity(tableName, author, entpackageType,workspace);
		str += codeRobotServiceV3.createDomain(tableName, author, dmpackageType,entpackageType,workspace);
		
		return str;
    }
	
	
	/**
	 * 功能描述:生成service
	 * @param tarPath
	 * @param tableName
	 * @param author
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年12月21日 下午1:51:56
	 */
//	@ApiOperation(httpMethod = "GET", value = "一键生成service")
//    @RequestMapping("/createService")
//    public String createService(@RequestParam(value="tarPath") String tarPath,
//    		@RequestParam(value="tableName") String tableName,
//    		@RequestParam(value="author") String author){
//		if(!tarPath.contains("domain.entity.")){
//			return "生成失败,输入的包名必须含有.domain.entity.";
//		}
//		String str=codeRobotServiceV3.createService(tarPath, tableName, author);
//		return str;
//    }
	
	
	/**
	 * 功能描述:生成控制器
	 * @param tarPath
	 * @param tableName
	 * @param author
	 * @return
	 * @Author:dengshuai
	 * @Date:2016年12月21日 下午3:08:10
	 */
//	@ApiOperation(httpMethod = "GET", value = "一键生成Controller")
//    @RequestMapping("/createController")
//    public String createController(@RequestParam(value="tarPath") String tarPath,
//    		@RequestParam(value="tableName") String tableName,
//    		@RequestParam(value="author") String author){
//		if(!tarPath.contains("domain.entity.")){
//			return "生成失败,输入的包名必须含有.domain.entity.";
//		}
//		String str=newcodeRobotService.createController(tarPath,tableName,author);
//		return str;
//    }
}
