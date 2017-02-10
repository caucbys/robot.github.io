package ${packageUrl};

/**
 * 功能描述:${tableComment}的访问接口控制类
 * 
 * @author ${author}
 * @date ${datetime}
 */
@Api(value="${entName}Controller", description="${tableComment}接口管理")
@RestController
@RequestMapping("/${entName}Controller")
public class ${entName}Controller extends BaseController<${entName}>
{	
	// 成员属性;
	private Result result;
	@Autowired
	private I${entName}Service ${entity}Service;
	
	/**
	 * 功能描述:查询记录数据;
	 * @param 条件 查询条件对象,支持分页
	 * @param page 页码
	 * @param limit 每页显示数据条数
	 * @return Result 返回值封装
	 * @Author:${author}
	 * @Date:${datetime}
	 */
	@ApiOperation(httpMethod="GET", value="分页查询${entName}记录数据", notes="查询${entName}记录数据")
	@RequestMapping(value="/query${entName}ByCondition", method=RequestMethod.GET)
	@ApiImplicitParams(value={
			@ApiImplicitParam(paramType="query", name="id", dataType="string", required=false, value="id"),
			@ApiImplicitParam(paramType="query", name="num", dataType="string", required=false, value="单号"),
			@ApiImplicitParam(paramType="query", name="status", dataType="int", required=false, value="状态")
		})
	public Result query${entName}ByCondition(@RequestParam(value="${entName}") ${entName} condition,
			@RequestParam(value="page",required=false) Integer page, 
			@RequestParam(value="limit",required=false) Integer limit){
		
		// 1、参数校验;
		if(condition == null){
			Result result = new Result();
			result.setMsg("condition参数不能为null");
			return result;
		}
		// 2、执行业务逻辑;
		return ${entity}Service.query${entName}ByCondition(codition, page, limit);
	}
	
	
}