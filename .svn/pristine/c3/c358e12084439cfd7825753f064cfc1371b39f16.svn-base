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
	public abstract Result saveOrUpdate(${entName} ${entity});
	
	/**
	 * 功能描述:根据id查询数据
	 * @param Integer
	 * @return Result
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract Result getById(${idType} id);

	/**
	 * 功能描述:根据查询一个类数据
	 * @param ${entName}
	 * @return Result
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract Result get${entName}(${entName} ${entity});

	/**
	 * 功能描述:批量删除数据;
	 * @param int[]
	 * @return Result
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract Result delete${entName}(int[] ids);

	/**
	 * 功能描述:查询所有数据;
	 * @return Result
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract Result getAll();
	
	/**
	 * 功能描述:查询列表数据;
	 * @param ${entName}
	 * @return Result
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract Result findList(${entName} ${entity});

	/**
	 * 功能描述:根据id删除数据  (逻辑删除)
	 * @param int
	 * @return Result
	 * @Author ${author}
	 * @Date:${datetime}
	 */
	public abstract Result delete${entName}(${idType} id);
	
	/**
	 * 删除数据方法(物理删除)
	 * @param ${entName}
	 * @return int
	 * @author ${author}
	 * @Date: ${datetime}
	 */
	public Result delete${entName}(${entName} ${entity});
	
}