package ${packageUrl};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import com.gdyunst.datacenter.framework.restfulapi.dto.Result;
import com.gdyunst.datacenter.common.utils.JsonUtil;
import com.gdyunst.datacenter.framework.restfulapi.controller.BaseController;
import ${entityUrl};
import ${serviceUrl};

/**
 * 功能描述:${tableComment}的访问接口控制类
 * 
 * @author ${author}
 * @date ${datetime}
 */
@Api(value="${entName}Controller", description="${tableComment}接口管理")
@RestController
@RequestMapping("/${model}/${entity}")
public class ${entName}Controller extends BaseController<${entName}>
{	
	// 成员属性;
	@Autowired
	private I${entName}Service ${entity}Service;
	
	// 成员方法;
	
	/**
	 * 功能描述:新增或修改数据 
	 * @param ${entName}
	 * @return String
	 * @Author ${author}
	 * @Date: ${datetime}
	 */
	@ApiOperation(httpMethod="POST", value="新增或修改数据", response=${entName}.class,notes="新增或修改数据")
	@ApiImplicitParams({
    	${apiImplicitParams}
    })
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public int update(${entName} ${entity})
	{
		//1.参数验证
		if(${entity} == null)
		{
			return 0;
		}
		
		// 2、执行业务逻辑;
		return ${entity}Service.saveOrUpdate(${entity});
		
	}
	
	/**
	 * 功能描述:根据id查询数据
	 * @param ${entName}
	 * @return String
	 * @Author ${author}
	 * @Date: ${datetime}
	 */
	@ApiOperation(httpMethod="GET", value="根据id查询数据", notes="")
	@RequestMapping(value="/get${entName}ById", method=RequestMethod.GET)
	public ${entName} get${entName}ById(@RequestParam(value="id")${idType} id)
	{
		// 1、参数验证;
		if(id == null)
		{
			return null;
		}
		// 2、执行业务逻辑;
		return ${entity}Service.getById(id);
		
	}
	
	/**
	 * 功能描述:删除数据 
	 * @param ${idType}[]
	 * @return String
	 * @Author ${author}
	 * @Date: ${datetime}
	 */
	@ApiOperation(httpMethod="POST", value="删除数据", response=Result.class, notes="")
	@ApiImplicitParams(value={
		@ApiImplicitParam(name="ids", value="序列号id 数组数据", dataType="String", required=true, paramType="query")
	})
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public int delete(@RequestParam(value="ids") List<${idType}> ids)
	{
		// 1、参数验证;
		if(ids == null || ids.size()==0)
		{
			return 0;
		}
		// 2、执行业务逻辑;
		
		return ${entity}Service.delete${entName}(ids);
	}
	
	
	/**
	 * 功能描述:条件查询不分页 
	 * @param ${entName}
	 * @return String
	 * @Author ${author}
	 * @Date: ${datetime}
	 */
	@ApiOperation(httpMethod="GET", value="查询数据", response=${entName}.class, notes="")
	@ApiImplicitParams(value={
		${apiImplicitParams}
	})
	@RequestMapping(value="/viewAll", method=RequestMethod.GET)
	public List<${entName}> get${entName}(${entName} condition)
	{
		// 1、参数验证;
		if(condition == null)
		{
			return null;
		}
		// 2、执行业务逻辑;
		return ${entity}Service.get${entName}(condition);
		
	}
	
	/**
	 * 功能描述:条件查询数据分页 
	 * @param ${entName}
	 * @return String
	 * @Author ${author}
	 * @Date: ${datetime}
	 */
	@ApiOperation(httpMethod="GET", value="查询数据", notes="")
	@ApiImplicitParams(value={
		${apiImplicitParams}
	})
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public Result get${entName}(${entName} condition,Integer page,Integer limit)
	{
		// 1、参数验证;
		if(condition == null)
		{
			return null;
		}
		// 2、执行业务逻辑;
		return ${entity}Service.get${entName}(condition,page,limit);
		
	}
	
    
}