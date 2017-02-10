package ${packageUrl};



/**
 * 功能描述:${tableComment}的业务逻辑处理接口类
 * 
 * @author ${author}
 * @date ${datetime}
 */
public interface I${entName}Service extends IBaseService<${entName}> {
	
	/**
	 * 功能描述:查询${entName}数据,不限定查询条件,支持模糊查询,分页持查询;
	 * @param condition 查询条件对象
	 * @param page 页码
	 * @param limit 每页数据条数
	 * @return Result对象集合
	 * @Date:${datetime}
	 * @Author ${author}
	 */
	public Result query${entName}ByCondition(${entName} condition, Integer page, Integer limit);
	
}