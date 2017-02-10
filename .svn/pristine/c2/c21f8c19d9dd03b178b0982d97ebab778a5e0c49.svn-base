<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${entName}">
	
	<!-- 实体持久化 -->
	<insert id="create${entName}" parameterType="${entPackage}.${entName}Entity">
		<![CDATA[
			INSERT INTO ${tName}
				(${allColum})
			VALUES 
				(${insertValue})
		]]>
	</insert>
	<!-- 更新实体 -->
	<update id="update${entName}" parameterType="${entPackage}.${entName}Entity">
		<![CDATA[
			UPDATE ${tName} SET note=note
		]]>
		<!-- 注意字符串类型加空串判断 -->
		${updateSql}
		<![CDATA[
			WHERE id=#{id}
		]]>
	</update>
	
	<!-- 查找单个实体 -->
	<select id="load${entName}" resultType="${entPackage}.${entName}Entity"
		parameterType="${entPackage}.${entName}Entity">
		SELECT * FROM ${tName} WHERE 1=1
		<!-- 注意字符串类型加空串判断,id放最后,即字段倒叙 -->
		${selectSql}
	</select>
	
	<!-- 列表查询 -->
	<select id="list${entName}" resultType="${dmPackage}.${entName}"
		parameterType="${dmPackage}.${entName}">
	  SELECT * FROM ${tName} WHERE 1=1
		<!-- 注意字符串类型加空串判断,id放最后,即字段倒叙 -->
		${selectSql}
	  ORDER BY indexOrder DESC
	</select>
	
	<!-- 删除单个实体 -->
	<delete id="remove${dentName}" parameterType="${dmPackage}.${entName}">
		delete from ${tName} where 
		<if test="ids!=null">id in
			<foreach collection="ids" item="item" open="(" close=")" separator=",">#{item}</foreach>
		</if>
		<if test="id!=null and id!=''">id=#{id}</if>
	</delete>
	
	<!-- 根据parentId更新path  用于拖拽树节点 -->
	<update id="updateTree${entName}" parameterType="${entPackage}.${entName}Entity">
		<![CDATA[
			UPDATE admin_menu SET path=CONCAT(#{path},SUBSTR(path , INSTR(path,#{id})+LENGTH(#{id})+1)) 
				WHERE id!=#{id} AND path LIKE CONCAT('%/',#{id},'/%')
		]]>
	</update>
	
	<!-- 根据path删除整棵树  用于树子节点删除 -->
	<delete id="removeTree${entName}" parameterType="${entPackage}.${entName}">
		<![CDATA[
			DELETE FROM ${tName} WHERE path LIKE CONCAT('%',#{path},'%')
		]]>
	</delete>
	
	<!-- 批量插入 -->
	<insert id="insertBatch${entName}" parameterType="java.util.List">
		INSERT INTO ${tName} 
			(${allColum})
		VALUES 
		<foreach collection="list" item="item" index="index" separator="," >
			(${itemAllColum})
		</foreach>  
	</insert>


</mapper>