package ${packageUrl};

import com.gdyunst.datacenter.framework.restfulapi.dto.Result;
import com.gdyunst.datacenter.framework.service.IBaseService;
import ${entityUrl};

/**
 * 功能描述:${tableComment}的业务逻辑处理接口类
 * 
 * @author ${author}
 * @date ${datetime}
 */
public interface I${entName}Service extends IBaseService<${entName}> {
	
	/**
	 * 功能描述:根据id判断保存或更新数据 
	 * @param ${entName}
	 * @return Result
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract int saveOrUpdate(${entName} ${entity});
	
	/**
	 * 功能描述:根据id查询数据
	 * @param Integer
	 * @return ${entName}
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract ${entName} getById(${idType} id);

	/**
	 * 功能描述:批量删除数据 (逻辑删除)
	 * @param int[]
	 * @return Result
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract int delete${entName}(List<Integer> ids);

	/**
	 * 功能描述:条件查询列表数据,不分页
	 * @param ${entName}
	 * @return List<${entName}>
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract List<${entName}> get${entName}(${entName} condition);
	
	/**
	 * 功能描述:条件查询列表数据,分页
	 * @param ${entName}
	 * @return Result
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract Result get${entName}(${entName} condition,Integer page,Integer limit);

		
}