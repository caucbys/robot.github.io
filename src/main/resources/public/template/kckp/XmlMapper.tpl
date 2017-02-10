<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${dmName}">
	
	<!-- 实体持久化 -->
	<insert id="create${dmName}" parameterType="${entPackage}.${entName}">
		<![CDATA[
			INSERT INTO ${tName}
				(${allColum})
			VALUES 
				(${insertValue})
		]]>
	</insert>
	<!-- 更新实体 -->
	<update id="update${dmName}" parameterType="${entPackage}.${entName}">
		<![CDATA[
			UPDATE ${tName} SET note=note
		]]>
		<!-- 注意字符串类型加空串判断 -->
		${updateSql}
		<![CDATA[
			WHERE id=#{id}
		]]>
	</update>

	<!-- 删除单个实体 -->
	<delete id="remove${dmName}" parameterType="${dmPackage}.${dmName}">
		delete from ${tName} where 
		<if test="ids!=null">id in
			<foreach collection="ids" item="item" open="(" close=")" separator=",">#{item}</foreach>
		</if>
		<if test="id!=null and id!=''">id=#{id}</if>
	</delete>
	
	<!-- 查找单个实体 -->
	<select id="load${dmName}" resultType="${entPackage}.${entName}"
		parameterType="${entPackage}.${entName}">
		SELECT * FROM ${tName} WHERE 1=1
		<!-- 注意字符串类型加空串判断,id放最后,即字段倒叙 -->
		${selectSql}
	</select>
	
	<!-- 列表查询 -->
	<select id="list${dmName}" resultType="${dmPackage}.${dmName}"
		parameterType="${dmPackage}.${dmName}">
	  SELECT * FROM ${tName} WHERE 1=1
		<!-- 注意字符串类型加空串判断,id放最后,即字段倒叙 -->
		${selectSql}
	  ORDER BY indexOrder DESC
	</select>
	
	<!-- 批量插入 -->
	<insert id="insertBatch${dmName}" parameterType="java.util.List">
		INSERT INTO ${tName} 
			(${allColum})
		VALUES 
		<foreach collection="list" item="item" index="index" separator="," >
			(${itemAllColum})
		</foreach>  
	</insert>


</mapper>