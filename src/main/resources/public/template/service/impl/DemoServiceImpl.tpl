package ${packageUrl};

import java.util.List;


import ${entityUrl};
import ${mapperUrl};
import ${serviceUrl};

/**
 * 功能描述:${tableComment}的业务逻辑处理实现类
 * 
  @author ${author}
 * @date ${datetime}
 */
@Service("${entity}Service")
public class ${entName}ServiceImpl extends BaseServiceImpl<${entName}> implements I${entName}Service
{
	@Autowired
	private ${entName}Mapper ${entity}Mapper;

	// 成员方法;
	@Override
	public int saveOrUpdate(${entName} ${entity}) {
		
		int row = 0;

		if (${entity}.getId() == null) 
		{
			row = saveEntity(${entity});
		} 
		else 
		{
			// 更新${entName}对象
			row = updateEntity(${entity});
		}
		

		return row;
	}
	
	@Override
	public ${entName} getById(${idType} id)
	{
	
		${entName} ${entity} = findById(id);
		
		return ${entity};
	}

	@Override
	public int delete${entName}(List<Integer> ids) {
		int count = ${entity}Mapper.deleteByIds(NumberUtil.convertToString(ids));
		
		return count;
	}

	@Override
	public Result get${entName}(${entName} condition,Integer page,Integer limit) {
		Result result = new Result();

		// 检测分页参数;
		Integer currentPage = Constant.PAGE_NUMBER;
		Integer pageSize = Constant.PAGE_SIZE;
		if (page != null) {
			currentPage = page;
		}
		if (limit != null) {
			pageSize = limit;
		}
		// 分页查询;
		Example example = new Example(Record.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("delStatus", 0);
		PageHelper.startPage(currentPage, pageSize, true, true);
		List<${entName}> records = ${entity}Mapper.select(condition);

		if (records != null && records.size() > 0) {
			PageInfo<${entName}> pageInfo = new PageInfo<>(records);

			result.setData(records);
			result.setTotal(pageInfo.getTotal());
			result.setSta(Result.RESULT_STA_1);
			result.setMsg("查询成功");
		} else {
			result.setSta(Result.RESULT_STA_0);
			result.setMsg("未查询到数据");
		}

		return result;
	}

	@Override
	public List<${entName}> get${entName}(${entName} condition) {
		if (condition.getDelStatus() == null)
			condition.setDelStatus(0);

		//模糊查询;
		Example example = new Example(Department.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("delStatus", 0);

		// 条件查询
		//if (condition.getStatus() != null) {
		//	criteria.andEqualTo("status", condition.getStatus());
		//}
		

		List<${entName}> resultList = ${entity}Mapper.selectByExample(example);
		
		return resultList;
	}
	

}	
