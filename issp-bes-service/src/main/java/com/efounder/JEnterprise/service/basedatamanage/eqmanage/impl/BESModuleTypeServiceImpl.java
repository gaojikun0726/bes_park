package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.core.config.db.OperationConfig;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESModuleTypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESModuleType;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESModuleTypeService;
import com.framework.common.utils.OperationLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 模块型号接口实现类
 * @author LvSihan
 *
 */
@Service("BESModuleTypeService")
public class BESModuleTypeServiceImpl implements BESModuleTypeService, ESBaseService {
	private static final Logger log = LoggerFactory.getLogger(BESModuleTypeServiceImpl.class);

	@Autowired
	private BESModuleTypeMapper moduleTypeMapper;
	@Autowired
	private OperationConfig operationConfig;

	/**
	 * 删除
	 */
	@Override
	public ISSPReturnObject delModuleType(BESModuleType moduletype) {
		// TODO Auto-generated method stub
		log.info("#BESModuleTypeMapper删除模块型号信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.delete(moduletype.getfId(), "bes_module_type");
			}
			boolean flag = moduleTypeMapper.delModuleType(moduletype);
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除模块型号信息成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除模块型号信息失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}
	
	/**
	 * 新增
	 */
	@Override
	public ISSPReturnObject addModuleType(BESModuleType moduletype) {
		log.info("#BESModuleTypeMapper新增模块型号信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();

		BESModuleType besModuleType = moduleTypeMapper.selectByPrimaryKey(moduletype.getfModuleType());

		if (null != besModuleType)
		{
			returnObject.setStatus("0");
			returnObject.setMsg("模块型号不能重复");
			return returnObject;
		}

		try {
			//根据已有F_GUID生成
			String maxId = moduleTypeMapper.queryMaxId();
	   		String f_ID =  getAutoIncreaseCol(maxId);
			moduletype.setfId(f_ID);
			boolean flag = moduleTypeMapper.addModuleType(moduletype);
			if("1".equals(operationConfig.getSysDataBaseUseable())){
				OperationLog.insert(moduletype.getfId(), "bes_module_type");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			moduletype.setfChdate(sdf.format(new Date()));
			moduletype.setfCrdate(sdf.format(new Date()));
			if (flag) {
				returnObject.setData(moduletype);
				returnObject.setStatus("1");
				returnObject.setMsg("添加模块型号信息成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("添加模块型号信息失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 更新
	 */
	@Override
	public ISSPReturnObject upModuleType(BESModuleType moduletype) {
		log.info("#BESModuleTypeMapper更新模块型号信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {

		Map<String, Object> startMap = null;
		if("1".equals(operationConfig.getSysDataBaseUseable())){
			startMap = OperationLog.updateStart(moduletype.getfId(), "bes_module_type");
			OperationLog.updateEnd(moduletype.getfId(), "bes_module_type", startMap);
		}
		boolean flag = moduleTypeMapper.upModuleType(moduletype);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		moduletype.setfCrdate(sdf.format(new Date()));
		if (flag) {
			returnObject.setData(moduletype);
			returnObject.setStatus("1");
			returnObject.setMsg("更新模块型号信息成功");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("更新模块型号信息失败");
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObject;
	}

	/**
	 * 查询
	 */
	@Override
	public ISSPReturnObject selectByPrimaryKey(String fModuleType) {
		// TODO Auto-generated method stub
		log.info("#BESModuleTypeMapper更新模块型号信息");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			BESModuleType selectByPrimaryKey = moduleTypeMapper.selectByPrimaryKey(fModuleType);
			returnObject.setData(selectByPrimaryKey);
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setStatus("0");
			returnObject.setMsg(e.getMessage());
		}
		return returnObject;
	}

	/**
	 * 分页
	 */
	@Override
	public PageInfo<BESModuleType> findmtByPage(Integer bars,Integer pageNum, String keywords) {
		// request: url?pageNum=1&pageSize=10
		// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
		if (pageNum == null)
			pageNum = 1;
		if (bars == null)
			bars = Constants.PAGE_SIZE;
		PageHelper.startPage(pageNum,bars );
		// 紧跟着的第一个select方法会被分页
		List<BESModuleType> moduletype = moduleTypeMapper.findmtByPage(keywords);
		// 用PageInfo对结果进行包装
		PageInfo<BESModuleType> page = new PageInfo<BESModuleType>(moduletype);
		// 测试PageInfo全部属性
		// PageInfo包含了非常全面的分页属性
		log.info("# 查询默认数据库 page.toString()={}", page.toString());
		return page;
	}
	
	/**
	 * select列表
	 */
	@Override
	public ISSPReturnObject selectList(BESModuleType moduletype) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESModuleType> list = moduleTypeMapper.selectList(moduletype);
			returnObject.setList(list);
			returnObject.setMsg("获取下拉框列表成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("获取下拉框列表失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public List<BESModuleType> selectPointTypeCl(BESModuleType moduletype) {
		// TODO Auto-generated method stub
		List<BESModuleType> list = moduleTypeMapper.selectPointTypeCl(moduletype);

		return list;
	}
	
	/**
	 * 获取该列加1后的值(当前值以多个0开头时保留前面的多个0)
	 * @param col 该列当前最大值
	 * @return
	 */
	private String getAutoIncreaseCol(String col) {
		if (col == null || "".equals(col)) {
			return "000001";
		}
		String regex = "^([0]+)([\\d]*)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(new StringBuffer(col));
		if (matcher.find()) {
			return matcher.group(1) + (Integer.parseInt(matcher.group(2)) + 1);
		} else {
			return String.valueOf(Integer.parseInt(col) + 1);
		}
	}

}
