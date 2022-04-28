package com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.initializer.EnergyTypeCache;
import com.efounder.JEnterprise.mapper.basedatamanage.enegrycollectionmanage.BESEnergyTypeMapper;
import com.efounder.JEnterprise.model.basedatamanage.enegrycollectionmanage.BESEnergyType;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.enegrycollectionmanage.BESEnergyTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * 能源类型接口实现类
 * @author LvSihan
 *
 */
@Service("BESEnergyTypeService")
public class BESEnergyTypeServiceImpl implements BESEnergyTypeService,ESBaseService{
	@Autowired
	private BESEnergyTypeMapper besEnergyTypeMapper;

	@Autowired
	private EnergyTypeCache energyTypeCache;

	/**
	 * 查询全部能源类型定义数据
	 * @return
	 */
	@Override
	public List<BESEnergyType> findAll()
	{
		return besEnergyTypeMapper.getAllEnergyType();
	}

	@Override
	public PageInfo<BESEnergyType> getEnergyTypeList(String keywords, Integer pageNum) {
		if (pageNum == null)
			pageNum = 1;
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		List<BESEnergyType> list = besEnergyTypeMapper.getEnergyTypeList(keywords);
		PageInfo<BESEnergyType> page = new PageInfo<BESEnergyType>(list);		
		return page;
	}

	/**
	 * 新增
	 */
	@Override
	public ISSPReturnObject add_EnergyType(BESEnergyType besEnergyType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		besEnergyType.setfGuid(UUIDUtil.getRandom32BeginTimePK());
		boolean flag = besEnergyTypeMapper.add_EnergyType(besEnergyType);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		besEnergyType.setfCrdate(sdf.format(new Date()));
		besEnergyType.setfChdate(sdf.format(new Date()));
		//获取新增的数据，用于前台展示
		if (flag) {

			// 加入缓存
			energyTypeCache.addOneEnergyTypeCache(besEnergyType);
			returnObject.setData(besEnergyType);
			returnObject.setMsg("添加能源类型成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("添加能源类型失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 删除能源类型
	 */
	@Override
	public ISSPReturnObject energyType_del(String fGuid) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		BESEnergyType besEnergyType = besEnergyTypeMapper.getEnergyType(fGuid);
		if (null == besEnergyType)
		{
			returnObject.setMsg("删除能源类型失败");
			returnObject.setStatus("0");
			return returnObject;
		}
		boolean flag = besEnergyTypeMapper.energyType_del(fGuid);
		if (flag) {
			// 删除当前记录缓存
			energyTypeCache.deleteOneEnergyTypeCache(besEnergyType.getfNybh());
			returnObject.setMsg("删除能源类型成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("删除能源类型失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject queryEnergyType(String fGuid) {
		ISSPReturnObject returnObject = new ISSPReturnObject();		
		try {
			BESEnergyType returnData = besEnergyTypeMapper.getEnergyType(fGuid);
			returnObject.setData(returnData);
			returnObject.setMsg("fNybh 查询能源类型成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("fNybh 查询能源类型失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	/**
	 * 编辑能源类型
	 */
	@Override
	public ISSPReturnObject edit_EnergyType(BESEnergyType besEnergyType) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besEnergyTypeMapper.edit_EnergyType(besEnergyType);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		besEnergyType.setfChdate(sdf.format(new Date()));
		if (flag) {
			// 更新记录缓存
			energyTypeCache.updateOneEnergyTypeCache(besEnergyTypeMapper.getEnergyType(besEnergyType.getfGuid()));
			returnObject.setData(besEnergyType);
			returnObject.setMsg("修改能源类型成功");
			returnObject.setStatus("1");
		} else {
			returnObject.setMsg("修改能源类型失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}


	/**
	 * 新增字段去重
	 * @param fNybh
	 * @param fNymc
	 * @return
	 */
	@Override
	public ISSPReturnObject checkRepeat(String fNybh, String fNymc) {

		ISSPReturnObject returnObject = new ISSPReturnObject();

		int  besEnergyType1 = besEnergyTypeMapper.checkRepeatFNybh(fNybh);
		int  besEnergyType2 = besEnergyTypeMapper.checkRepeatFNymc(fNymc);

		if (besEnergyType1>0){
			returnObject.setData("1");//错误有重复字段
		}else {
			returnObject.setData("0");
		}

		if (besEnergyType2>0){
			returnObject.setMsg("1");//错误有重复字段
		}else {
			returnObject.setMsg("0");//
		}

		return returnObject;
	}

	@Override
	public ISSPReturnObject checkEditRepeat(String fNymc,String fGuid) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		int  besEnergyType = besEnergyTypeMapper.checkEditRepeatFNymc(fNymc,fGuid);
		if (besEnergyType == 1){ //证明只有一条 错误
			returnObject.setData("1");
		}else if (besEnergyType == 0){ //1条以上 或者O 条 可以修改
			returnObject.setData("0");
		}
		return returnObject;
	}


}
