package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESEquipmentBrandMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEquipmentBrand;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BES_EquipmentBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/** 
 * 类名称：BESEquipmentBrandServiceImpl
 * 类描述：品牌字典service接口实现类
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年6月26日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Service("BES_EquipmentBrandService")
public class BESEquipmentBrandServiceImpl implements BES_EquipmentBrandService {
	private static final Logger log = LoggerFactory.getLogger(BESEquipmentBrandServiceImpl.class);

	@Autowired
	private BESEquipmentBrandMapper equipmentBrandMapper;
	@Override
	public PageInfo<BESEquipmentBrand> findEquipmentBrandByPage(Integer bars,Integer pageNum,String keywords) {
		// request: url?pageNum=1&pageSize=10
				// 支持 ServletRequest,Map,POJO 对象，需要配合 params 参数
				if (pageNum == null)
					pageNum = 1;
				if (bars == null) {
					bars = Constants.PAGE_SIZE;
				}
				PageHelper.startPage(pageNum,bars );
				// 紧跟着的第一个select方法会被分页
				List<BESEquipmentBrand> equipmenttype = equipmentBrandMapper.findEquipmentBrandByPage(keywords);
				// 用PageInfo对结果进行包装
				PageInfo<BESEquipmentBrand> page = new PageInfo<BESEquipmentBrand>(equipmenttype);
				// 测试PageInfo全部属性
				// PageInfo包含了非常全面的分页属性
				log.info("# 查询默认数据库 page.toString()={}", page.toString());
				return page;
	}

	@Override
	public List<BESEquipmentBrand> findAllEquipmentBrand() {
		log.info("#equipmentBrandMapper获取表中全部信息");
		return equipmentBrandMapper.findAllEquipmentBrand();
	}

	@Override
	public BESEquipmentBrand findEquipmentBrandByBrandbh(String id) {
		log.info("#equipmentBrandMapper根据品牌编号查询信息");
		return equipmentBrandMapper.findEquipmentBrandByBrandbh(id);
	}

	@Override
	public List<BESEquipmentBrand> findEquipmentBrandByBrandmc(String f_brandmc) {
		log.info("#equipmentBrandMapper根据品牌名称查询信息");
		return equipmentBrandMapper.findEquipmentBrandByBrandmc(f_brandmc);
	}

	@Override
	public List<BESEquipmentBrand> findEquipmentBrandByKeywords(String keywords) {
		log.info("#equipmentBrandMapper根据关键字查询信息");
		return equipmentBrandMapper.findEquipmentBrandByKeywords(keywords);
	}

	@Override
	public List<BESEquipmentBrand> loadAll() {
		log.info("#equipmentBrandMapper加载信息");
		return equipmentBrandMapper.loadAll();
	}

	@Override
	public boolean addEquipmentBrand(BESEquipmentBrand equipmentBrand) {
		log.info("#equipmentBrandMapper添加信息");
		return equipmentBrandMapper.addEquipmentBrand(equipmentBrand);
	}

	@Override
	public boolean deleteByBrandbh(BESEquipmentBrand equipmentBrand) {
		log.info("#equipmentBrandMapper删除信息");
		return equipmentBrandMapper.deleteByBrandbh(equipmentBrand);
	}

	@Override
	public boolean upDateEquipmentBrand(BESEquipmentBrand equipmentBrand) {
		log.info("#equipmentBrandMapper修改信息");
		return equipmentBrandMapper.upDateEquipmentBrand(equipmentBrand);
	}

	@Override
	public String findMaxBrandbh() {
		log.info("#equipmentBrandMapper查询编号最大值");
		return equipmentBrandMapper.findMaxBrandbh();
	}
	/**
	 * 获取设备品牌列表
	 */
	@Override
	public ISSPReturnObject getEquipmentBrandList() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESEquipmentBrand> list = equipmentBrandMapper.getEquipmentBrandList();
			returnObject.setList(list);
			returnObject.setMsg("查询设备品牌列表成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("查询设备品牌列表失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	/**
	 * 获取品牌名称
	 */
	@Override
	public List<BESEquipmentBrand> getBrand() {
		// TODO Auto-generated method stub
		return equipmentBrandMapper.getBrand();
	}
}
