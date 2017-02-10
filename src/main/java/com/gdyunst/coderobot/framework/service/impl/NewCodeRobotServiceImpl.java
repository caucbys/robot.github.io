package com.gdyunst.coderobot.framework.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.filechooser.FileSystemView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdyunst.coderobot.common.DateUtil;
import com.gdyunst.coderobot.config.RobotConfig;
import com.gdyunst.coderobot.framework.service.INewCodeRobotService;

@Service
public class NewCodeRobotServiceImpl implements INewCodeRobotService {

	@Autowired
	RobotConfig rbConfig;
	
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String pwd = rbConfig.getDbpassword();
			String user = rbConfig.getDbusername();
			
			String url = rbConfig.getDburl();
			Properties props = new Properties();
			props.setProperty("user", user);
			props.setProperty("password", pwd);
			props.setProperty("remarks", "true"); // 设置可以获取remarks信息
			props.setProperty("useInformationSchema", "true");// 设置可以获取tables
																// remarks信息
			conn = DriverManager.getConnection(url, props);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private Map readDB(String tName) {
		Map map = new HashMap();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		// String path=fsv.getHomeDirectory().toString();//获取当前用户桌面路径
		Connection getConnection = null;
		DatabaseMetaData dbmd = null;
		ResultSet resultSet = null;
		try {
			getConnection = getConnection();
			dbmd = getConnection.getMetaData();
			resultSet = dbmd.getTables(null, "%", tName, new String[] { "TABLE" });
			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				String tableComm = resultSet.getString("REMARKS");
				// System.out.println("表名："+tableName+"\t\n表字段信息：");
				ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
				String strColumn = "";
				String strType = "";
				String strUpdateSql = "";
				List<String> plist = new LinkedList<String>();
				Map<String, String> pmap = new LinkedHashMap<String, String>();
				Map<String, String> cmap = new LinkedHashMap<String, String>();
				while (rs.next()) {
					// System.out.println("字段名："+rs.getString("COLUMN_NAME")+"\t字段注释："+rs.getString("REMARKS")+"\t字段数据类型："+rs.getString("TYPE_NAME"));
					String type = "";
					if ("INT".equals(rs.getString("TYPE_NAME")) || "INTEGER".equals(rs.getString("TYPE_NAME"))) {
						type = "Integer";
					} else if ("TIMESTAMP".equals(rs.getString("TYPE_NAME"))
							|| "DATETIME".equals(rs.getString("TYPE_NAME"))
							|| "DATE".equals(rs.getString("TYPE_NAME"))) {
						type = "Date";
					} else if ("FLOAT".equals(rs.getString("TYPE_NAME"))
							|| "DOUBLE".equals(rs.getString("TYPE_NAME"))) {
						type = "Double";
					} else {
						type = "String";
					}
					pmap.put(rs.getString("COLUMN_NAME"), type);
					cmap.put(rs.getString("COLUMN_NAME"), type + "," + rs.getString("REMARKS"));
					if ("id".equals(rs.getString("COLUMN_NAME"))) {
						plist.add("@Id");
					} else {
						plist.add("@Column(name=\"" + rs.getString("COLUMN_NAME") + "\")");
					}
					String propertyStr = "private " + type + " " + rs.getString("COLUMN_NAME") + ";\t//"
							+ rs.getString("REMARKS");
					plist.add(propertyStr);
					// System.out.println(rs.getString("TYPE_NAME"));
					if (strColumn == "") {
						strColumn += rs.getString("COLUMN_NAME");
					} else {
						strColumn += "," + rs.getString("COLUMN_NAME");
					}
					if (strType == "") {
						strType += "#{" + rs.getString("COLUMN_NAME") + "}";
					} else {
						strType += ",#{" + rs.getString("COLUMN_NAME") + "}";
					}
					if (strUpdateSql == "") {
						strUpdateSql += rs.getString("COLUMN_NAME") + "=#{" + rs.getString("COLUMN_NAME") + "}";
					} else {
						strUpdateSql += "," + rs.getString("COLUMN_NAME") + "=#{" + rs.getString("COLUMN_NAME") + "}";
					}
				}
				String insertSql = "INSERT INTO " + tableName.toUpperCase() + " (" + strColumn + ") VALUES (" + strType
						+ ")";
				strUpdateSql = "UPDATE " + tableName.toUpperCase() + " SET " + strUpdateSql.replace("id=#{id},", "")
						+ " WHERE id=#{id}";
				String deleteSql = "UPDATE " + tableName.toUpperCase() + " SET delStatus=1 WHERE id=#{id}";
				String selectOneSql = "SELECT " + strColumn + " FROM " + tableName.toUpperCase() + " WHERE id=#{id}";
				String selectAllSql = "SELECT " + strColumn + " FROM " + tableName.toUpperCase() + " WHERE delStatus=0";
				map.put("plist", plist);
				map.put("insertSql", insertSql);
				map.put("updateSql", strUpdateSql);
				map.put("deleteSql", deleteSql);
				map.put("selectOneSql", selectOneSql);
				map.put("selectAllSql", selectAllSql);
				map.put("tableName", tName);
				map.put("tableComm", tableComm);
				map.put("pmap", pmap);
				map.put("cmap", cmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (getConnection != null) {
					getConnection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public String createEntity(String tarPath, String tName, String author) {
		final String demoTpl = "/src/main/resources/public/template/entity/model/Demo.tpl";
		String rootPath = "";
		String entNewPath = "";
		String entName = "";
		String entity = "";
		String suffix = ".java";
		Map map = readDB(tName);

		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		rootPath = path.replace("/target/classes", "");
		tName = (String) map.get("tableName");
		entity = tName.split("_")[1];
		entName = entity.substring(0, 1).toUpperCase() + entity.substring(1);

		BufferedReader br = null;
		String line = null;
		StringBuffer buf = new StringBuffer();
		BufferedWriter bw = null;

		String entityPath = rootPath + "src/main/java" + "/" + tarPath.replace(".", "/");

		entNewPath = entityPath.replace("/", "\\") + "\\" + entName + suffix;
		File file = new File(entNewPath);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "生成Entity文件失败！";
			}
		} else {
			return "Entity文件已存在！不再生成覆盖原文件。";
		}

		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(new File(rootPath + demoTpl)), "UTF-8");
			// 根据文件路径创建缓冲输入流
			br = new BufferedReader(read);

			// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
			while ((line = br.readLine()) != null) {
				// 此处根据实际需要修改某些行的内容
				line = line.replace("${packageUrl}", tarPath);
				line = line.replace("${tableComment}", (String) map.get("tableComm"));
				line = line.replace("${author}", author);
				line = line.replace("${datetime}", DateUtil.toString(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
				line = line.replace("${tName}", tName);
				line = line.replace("${className}", entName);
				if (line.indexOf("//属性") >= 0) {
					buf.append(line).append("\r\n");
					List<String> plist = (List<String>) map.get("plist");
					for (String s : plist) {
						buf.append("\t" + s + "\r\n");
					}

				} else if (line.indexOf("//方法") >= 0) {
					buf.append(line).append("\r\n");
					LinkedHashMap<String, String> pmap = (LinkedHashMap<String, String>) map.get("pmap");
					for (String key : pmap.keySet()) {
						String value = pmap.get(key);
						String newkey = key.substring(0, 1).toUpperCase() + key.substring(1);
						buf.append("\tpublic " + value + " get" + newkey + "() {\r\n");
						buf.append("\t\treturn " + key + ";\r\n");
						buf.append("\t}\r\n");
						buf.append("\r\n");
						buf.append("\tpublic void set" + newkey + "(" + value + " " + key + ") {\r\n");
						buf.append("\t\tthis." + key + " = " + key + ";\r\n");
						buf.append("\t}\r\n");
						buf.append("\r\n");
					}
				}
				// 如果不用修改, 则按原来的内容回写
				else {
					buf.append(line).append("\r\n");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "生成Entity文件失败！";
		} finally {
			// 关闭流
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
				}
			}
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(write);
			// 将内容写入文件中
			bw.write(buf.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return "生成Entity文件失败！";
		} finally {
			// 关闭流
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					bw = null;
				}
			}
		}
		return "生成Entity文件成功！";

	}

	@Override
	public String createService(String tarPath, String tName, String author) {
		final String demoServiceImplTpl = "/src/main/resources/public/template/service/impl/DemoServiceImpl.tpl";
		final String idemoServiceTpl = "/src/main/resources/public/template/service/IDemoService.tpl";
		String rootPath = "";
		String serNewPath = "";
		String entName = "";
		String entity = "";
		String iseNewPath = "";
		String suffix = ".java";
		Map map = readDB(tName);

		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		rootPath = path.replace("/target/classes", "");
		tName = (String) map.get("tableName");
		entity = tName.split("_")[1];
		entName = entity.substring(0, 1).toUpperCase() + entity.substring(1);
		BufferedReader br = null;
		String line = null;
		BufferedReader ibr = null;
		String iline = null;
		StringBuffer buf = new StringBuffer();
		StringBuffer ibuf = new StringBuffer();
		BufferedWriter bw = null;
		BufferedWriter ibw = null;

		String claName = entName + "ServiceImpl";
		String iclaName = "I" + entName + "Service";
		String serPath = tarPath.replace("domain.entity", "service") + ".impl";
		String servicePath = rootPath + "src/main/java/" + serPath.replace(".", "/");
		String msg = "";

		// System.out.println(servicePath);
		serNewPath = servicePath.replace("/", "\\") + "\\" + claName + suffix;
		File file = new File(serNewPath);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "生成Service文件失败！";
			}
		} else {
			msg += "ServiceImpl文件已存在！不再生成覆盖原文件。";
		}
		iseNewPath = servicePath.replace("/impl", "").replace("/", "\\") + "\\" + iclaName + suffix;
		File ifile = new File(iseNewPath);
		if (!ifile.exists()) {
			try {
				ifile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "生成Service文件失败！";
			}
		} else {
			msg += "IService文件已存在！不再生成覆盖原文件。";
		}

		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(new File(rootPath + demoServiceImplTpl)),
					"UTF-8");
			// 根据文件路径创建缓冲输入流
			br = new BufferedReader(read);
			Map pmap = (Map) map.get("pmap");
			// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
			while ((line = br.readLine()) != null) {
				// 此处根据实际需要修改某些行的内容
				line = line.replace("${packageUrl}", serPath);
				line = line.replace("${entityUrl}", tarPath + "." + entName);
				line = line.replace("${mapperUrl}",
						tarPath.substring(0, tarPath.indexOf(".entity.")) + "." + entName + "Mapper");
				line = line.replace("${serviceUrl}", serPath.replace(".impl", "") + "." + iclaName);
				line = line.replace("${tableComment}", (String) map.get("tableComm"));
				line = line.replace("${author}", author);
				line = line.replace("${datetime}", DateUtil.toString(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
				line = line.replace("${entName}", entName);
				line = line.replace("${idType}", (String) pmap.get("id"));
				line = line.replace("${entity}", entity);
				buf.append(line).append("\r\n");
			}
			InputStreamReader iread = new InputStreamReader(new FileInputStream(new File(rootPath + idemoServiceTpl)),
					"UTF-8");
			ibr = new BufferedReader(iread);
			while ((iline = ibr.readLine()) != null) {
				// 此处根据实际需要修改某些行的内容
				iline = iline.replace("${packageUrl}", serPath.replace(".impl", ""));
				iline = iline.replace("${entityUrl}", tarPath + "." + entName);
				iline = iline.replace("${tableComment}", (String) map.get("tableComm"));
				iline = iline.replace("${author}", author);
				iline = iline.replace("${datetime}", DateUtil.toString(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
				iline = iline.replace("${entName}", entName);
				iline = iline.replace("${idType}", (String) pmap.get("id"));
				iline = iline.replace("${entity}", entity);
				ibuf.append(iline).append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "生成Service文件失败！";
		} finally {
			// 关闭流
			try {
				if (br != null) {
					br.close();
				}
				if (ibr != null) {
					ibr.close();
				}
			} catch (IOException e) {
				br = null;
				ibr = null;
			}
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(write);
			// 将内容写入文件中
			bw.write(buf.toString());
			msg += "生成ServiceImpl文件成功！";
			OutputStreamWriter iwrite = new OutputStreamWriter(new FileOutputStream(ifile), "UTF-8");
			ibw = new BufferedWriter(iwrite);
			ibw.write(ibuf.toString());
			msg += "生成IService文件成功！";
		} catch (Exception e) {
			e.printStackTrace();
			return "生成Service文件失败！";
		} finally {
			// 关闭流
			try {
				if (bw != null) {
					bw.close();
				}
				if (ibw != null) {
					ibw.close();
				}
			} catch (IOException e) {
				bw = null;
				ibw = null;
			}
		}
		return msg;
	}

	@Override
	public String createMapper(String tarPath, String tableName, String author) {
		String demoMapperTpl = "/src/main/resources/public/template/entity/DemoMapper.tpl";
		final String suffix = ".java";
		String path = "";
		String rootPath = "";
		String tName = "";
		String entity = "";
		String entName = "";
		String mapNewPath = "";
		Map map = readDB(tableName);
		if (map == null || map.get("tableName") == null) {
			return "您输入的表名不存在，生成失败。";
		}
		path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		rootPath = path.replace("/target/classes", "");
		tName = (String) map.get("tableName");
		entity = tName.split("_")[1];
		entName = entity.substring(0, 1).toUpperCase() + entity.substring(1);
		BufferedReader br = null;
		String line = null;
		StringBuffer buf = new StringBuffer();
		BufferedWriter bw = null;

		String claName = entName + "Mapper";
		String mapperPath = rootPath + "src/main/java/"
				+ tarPath.substring(0, tarPath.indexOf(".entity.")).replace(".", "/");
		mapNewPath = mapperPath.replace("/", "\\") + "\\" + claName + suffix;
		File file = new File(mapNewPath);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "生成Mapper文件失败！";
			}
		} else {
			return "Mapper文件已存在！不再生成覆盖原文件。";
		}

		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(new File(rootPath + demoMapperTpl)),
					"UTF-8");
			// 根据文件路径创建缓冲输入流
			br = new BufferedReader(read);

			// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
			while ((line = br.readLine()) != null) {
				// 此处根据实际需要修改某些行的内容
				line = line.replace("${packageUrl}", tarPath.substring(0, tarPath.indexOf(".entity.")));
				line = line.replace("${tableComment}", (String) map.get("tableComm"));
				line = line.replace("${author}", author);
				line = line.replace("${datetime}", DateUtil.toString(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
				line = line.replace("${importUrl}", tarPath + "." + entName);
				line = line.replace("${className}", claName);
				line = line.replace("${entName}", entName);
				line = line.replace("${entity}", entity);
				buf.append(line).append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "生成Mapper文件失败！";
		} finally {
			// 关闭流
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
				}
			}
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(write);
			// 将内容写入文件中
			bw.write(buf.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return "生成Mapper文件失败！";
		} finally {
			// 关闭流
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					bw = null;
				}
			}
		}
		return "生成Mapper文件成功！";
	}

	@Override
	public String createController(String tarPath, String tableName, String author) {
		String demoControllerTpl = "/src/main/resources/public/template/controller/DemoController.tpl";
		final String suffix = ".java";
		String path = "";
		String rootPath = "";
		String tName = "";
		String entity = "";
		String entName = "";
		String conNewPath = "";
		String modelName = "";
		String mapNewPath = "";
		Map map = readDB(tableName);
		if (map == null || map.get("tableName") == null) {
			return "您输入的表名不存在，生成失败。";
		}
		path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		rootPath = path.replace("/target/classes", "");
		tName = (String) map.get("tableName");
		entity = tName.split("_")[1];
		modelName = tName.split("_")[0];
		entName = entity.substring(0, 1).toUpperCase() + entity.substring(1);
		BufferedReader br = null;
		String line = null;
		StringBuffer buf = new StringBuffer();
		BufferedWriter bw = null;

		String claName = entName + "Controller";
		String conPath = tarPath.replace("domain.entity", "restfulapi.controller");
		String controlPath = rootPath + "src/main/java/" + conPath.replace(".", "/");
		conNewPath = controlPath.replace("/", "\\") + "\\" + claName + suffix;
		File file = new File(conNewPath);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "生成Controller文件失败！";
			}
		} else {
			return "Controller文件已存在！不再生成覆盖原文件。";
		}
		StringBuffer sb = new StringBuffer("");
		LinkedHashMap<String, String> cmap = (LinkedHashMap<String, String>) map.get("cmap");
		for (String key : cmap.keySet()) {
			String value = cmap.get(key);
			if (!"".equals(sb.toString())) {
				sb.append(",\r\n\t\t");
			}
			String type = (value.split(",")[0]).toLowerCase();
			if ("integer".equals(type)) {
				type = "int";
			}
			sb.append("@ApiImplicitParam(paramType=\"query\",name=\"" + key + "\",dataType=\"" + type
					+ "\",required=false,value=\"" + value.split(",")[1] + "\")");
		}

		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(new File(rootPath + demoControllerTpl)),
					"UTF-8");
			// 根据文件路径创建缓冲输入流
			br = new BufferedReader(read);

			// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
			while ((line = br.readLine()) != null) {
				// 此处根据实际需要修改某些行的内容
				line = line.replace("${packageUrl}", conPath);
				line = line.replace("${entityUrl}", tarPath + "." + entName);
				line = line.replace("${serviceUrl}",
						conPath.replace("restfulapi.controller", "service") + ".I" + entName + "Service");
				line = line.replace("${tableComment}", (String) map.get("tableComm"));
				line = line.replace("${author}", author);
				line = line.replace("${datetime}", DateUtil.toString(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
				line = line.replace("${entName}", entName);
				Map pmap = (Map) map.get("pmap");
				String idtype = (String) pmap.get("id");
				if ("Integer".equals(idtype)) {
					idtype = "int";
				}
				line = line.replace("${idType}", idtype);
				line = line.replace("${entity}", entity);
				line = line.replace("${model}", modelName);
				line = line.replace("${apiImplicitParams}", sb.toString());
				buf.append(line).append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "生成Controller文件失败！";
		} finally {
			// 关闭流
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
				}
			}
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(write);
			// 将内容写入文件中
			bw.write(buf.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return "生成Controller文件失败！";
		} finally {
			// 关闭流
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					bw = null;
				}
			}
		}
		return "生成Controller文件成功！";

	}

}
