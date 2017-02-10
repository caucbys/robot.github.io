package com.gdyunst.coderobot.common;

/**
 * 
* 功能描述:数据字典;
* @Author:baiqing
* @Date:2017年1月10日下午5:51:53
 */
public class DictionaryUtil {
	

	
	//公有的
	/**
	 * 公有的：
	 * 传入的数据库表名
	 */
	public static final String TNAME = "${tName}"; 
	/**
	 * 公有的：
	 * 作者名
	 */
	public static final String AUTHOR = "${author}"; 
	/**
	 * 公有的：
	 * 读取包的url(有entity、domain、service。。)
	 */
	public static final String PACKAGEURL = "${packageUrl}"; 
	/**
	 * 公有的：
	 * 类名
	 */
	public static final String ENTNAME = "${entName}"; 
	
	/**
	 * 公有的：
	 * 日期时间 类型"yyyy年MM月dd日 HH:mm:ss"
	 */
	public static final String DATETIME = "${datetime}"; 
	
	
	//Service
	/**
	 * Service：
	 * mapperUrl
	 */
	public static final String MAPPERURL = "${mapperUrl}";
	/**
	 * Service：
	 * serviceUrl
	 */
	public static final String SERVICEURL = "${serviceUrl}";
	/**
	 * Service：
	 * entityUrl
	 */
	public static final String ENTITYURL = "${entityUrl}";
	/**
	 * Service：
	 * 主键的类型
	 */
	public static final String IDTYPE = "${idType}";
	
	
	//xmlmapper
	/**
	 * xmlmapper：
	 * 主键
	 */
	public static final String ID = "${id}";
	/**
	 * xmlmapper：
	 * 查询SQL
	 */
	public static final String SELECTSQL = "${selectSql}";
	/**
	 * xmlmapper：
	 * 修改SQL
	 */
	public static final String UPDATESQL = "${updateSql}";
	/**
	 * xmlmapper：
	 * 增加SQL
	 */
	public static final String INSERTVALUE = "${insertValue}";
	/**
	 * xmlmapper：
	 * 批量增加SQL
	 */
	public static final String ITEMALLCOLUM = "${itemAllColum}";
	/**
	 * xmlmapper：
	 * 全部字段名
	 */
	public static final String ALLCOLUM = "${allColum}";
	/**
	 * xmlmapper：
	 * 实体包名
	 */
	public static final String DMPACKAGE1 = "${dmPackage}";
	/**
	 * xmlmapper：
	 * 引入url
	 */
	public static final String IMPORTURL = "${importUrl}";
	/**
	 * xmlmapper：
	 * 类名
	 */
	public static final String CLASSNAME = "${className}";
	/**
	 * xmlmapper：
	 * 实体对象
	 */
	public static final String ENTITY = "${entity}";
	
	
	
	
	
	
	//createEntity
	/**
	 * createEntity：
	 * 表的详情
	 */
	public static final String TABLECOMMENT = "${tableComment}"; 
	
	
	/**
	 * createEntity：
	 * baseCrud包的路径
	 */
	public static final String BASECRUDPACKAGE1 = "${baseCrudPackage}"; 
	/**
	 * createEntity：
	 * baseCrud类名
	 */
	public static final String BASECRUD1 = "${baseCrud}"; 
	/**
	 * createEntity：
	 * entPackage
	 */
	public static final String ENTPACKAGE1 = "${entPackage}"; 
	
	

	
	
	
	//readDB
	/**
	 * readDB：
	 * baseCrud包的路径
	 */
	public static final String BASECRUDPACKAGE = "baseCrudPackage"; 
	/**
	 * readDB：
	 * columnId
	 */
	public static final String COLUMNID = "columnId"; 
	/**
	 * readDB：
	 * baseCrud包的路径
	 */
	public static final String ENTPACKAGE = "entpackage"; 
	/**
	 * readDB：
	 * baseCrud类名
	 */
	public static final String BASECRUD = "baseCrud"; 
//	/**
//	 * readDB
//	 * 添加Sql
//	 */
//	public static final String INSERTSQL = "insertSql"; 
//	/**
//	 * readDB
//	 * 修改Sql
//	 */
//	public static final String UPDATESQL = "updateSql";
//	/**
//	 * readDB
//	 * 删除Sql
//	 */
//	public static final String DELETESQL = "deleteSql"; 
//	/**
//	 * readDB
//	 * 根据id查询Sql
//	 */
//	public static final String SELECTONESQL = "selectOneSql"; 
//	/**
//	 * readDB
//	 * 查询所有Sql
//	 */
//	public static final String SELECTALLSQL = "selectAllSql"; 
	/**
	 * readDB：
	 * 读取的表名
	 */
	public static final String TABLENAME = "tableName";
	/**
	 * readDB：
	 * 读取的表的详情
	 */
	public static final String TABLECOMM = "tableComm";
	/**
	 * readDB：
	 * entity类的注解
	 */
	public static final String PMAP = "pmap"; 
	/**
	 * readDB：
	 * entity的注释
	 */
	public static final String CMAP = "cmap";
	/**
	 * readDB：
	 * plist包含了注解、属性名
	 */
	public static final String PLIST = "plist"; 
	/**
	 * readDB：
	 * demoMapperTpl1
	 */
	public static final String DEMOMAPPERTPL1 = "demoMapperTpl1";
	/**
	 * readDB：
	 * dmPackage
	 */
	public static final String DMPACKAGE = "dmPackage";
	
	
	
	
	
	
	
	
	
	//模板url
	
	/**
	 * 模板url：
	 * entity模板的url
	 * */
	public static final String DOMETPL = "/src/main/resources/public/template/entity/model/EntityV1.tpl";  
//	public static final String DOMETPL = "/src/main/resources/public/template/entity/model/Demo.tpl";  
	/**
	 * 模板url：
	 * serviceimpl模板的url
	 * */
	public static final String DEMOSERVICEIMPLTPL = "/src/main/resources/public/template/service/impl/DemoServiceImpl.tpl";
	/**
	 * 模板url：
	 * iservice模板的url
	 * */
	public static final String IDEMOSERVICETPL = "/src/main/resources/public/template/service/IDemoService.tpl";
	/**
	 * 模板url：
	 * Mapper模板的url
	 * */
	public static  String DEMOMAPPERTPL = "/src/main/resources/public/template/entity/xmlMapperV2.tpl";
	/**
	 * 模板url：
	 * Controller模板的url
	 * */
	public static final String DEMOCONTROLLERTPL = "/src/main/resources/public/template/controller/DemoController.tpl";
	/**
	 * 模板url：
	 * Domain模板的url
	 * */
	public static final String DEMOTPL = "/src/main/resources/public/template/entity/model/domain.tpl";
}
