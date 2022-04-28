package com.efounder.JEnterprise.service.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.exception.BusinessException;
import com.efounder.JEnterprise.mapper.BESEquipmentMoldMapper;
import com.efounder.JEnterprise.model.BESEquipmentMold;
import com.efounder.JEnterprise.service.BESEquipmentMoldService;
import com.efounder.JEnterprise.service.ESBaseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("BESEquipmentMoldService")
public class BESEquipmentMoldServiceImpl implements BESEquipmentMoldService,ESBaseService{
	private static final Logger log = LoggerFactory.getLogger(BESEquipmentMoldServiceImpl.class);

	@Autowired
	private BESEquipmentMoldMapper besEquipmentMoldMapper;
	
	@Override
	public ISSPReturnObject getEquipmentModuleInfo() {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		log.info("#besEquipmentMoldMapper获取设备类型信息");
    	try {
    		List<BESEquipmentMold> equipmentMoldList = besEquipmentMoldMapper.getAllEquipmentMold();//获取设备类型信息
    		returnObject.setStatus("1");
    		returnObject.setList(equipmentMoldList);
		} catch (Exception e) {
			returnObject.setStatus("0");
		}
		return returnObject;
	}
	
	/**
	 * 设备类型树
	 */
	@Override
	public List<BESEquipmentMold> loadAll(){
		log.info("#besEquipmentMoldMapper加载设备类型树");
		 List<BESEquipmentMold> list = besEquipmentMoldMapper.loadAll();
		return list;
		
	}
	
	/**
	  * @Description 查询子类
	  * @author tianjiangwei
	  * @param f_zbh
	  * @return  
	 */
	@Override
	public List<BESEquipmentMold> findChildByZtype(String f_type) {
		List<BESEquipmentMold> eqTypeList = besEquipmentMoldMapper.findChildByZtype(f_type);
		log.info("#ESZzjgMapper获取子节点",eqTypeList.toString());
		return eqTypeList;
	}
	/**
	 * 根据条件查询设备类型信息
	 */
	@Override
	public List<BESEquipmentMold> getEqTypeList(String f_sbmc) {
		// TODO Auto-generated method stub
	        List<BESEquipmentMold> list = besEquipmentMoldMapper.getEqTypeList(f_sbmc);
	        return list;
	}
	
	/**
	 * 新增设备类型信息
	 */
	@Transactional
	@Override  
	public boolean add_eqType(BESEquipmentMold besEquipmentMold) {
		if (besEquipmentMold == null ) {
			throw new BusinessException("besEquipmentMold.registr.error", "新增站点信息错误");
		}

		if (StringUtils.isBlank(besEquipmentMold.getF_type()) || StringUtils.isBlank(besEquipmentMold.getF_sbmc())) {
			
			throw new BusinessException("besEquipmentMold.registr.error", "新增站点信息错误");
		}

		BESEquipmentMold e = besEquipmentMoldMapper.findeqTypeByBh(besEquipmentMold.getF_type());
		if(e!=null){
			throw new BusinessException("besEquipmentMold.registr.error", "设备编号已经存在,eqType="+besEquipmentMold.getF_type());
		}
		return besEquipmentMoldMapper.add_eqType(besEquipmentMold);

		
	}
	/**
	 * 根据设备类型查询设备信息
	 */
	@Override
	public BESEquipmentMold findeqTypeById(String f_type) {
		// TODO Auto-generated method stub
		log.info("#besEquipmentMoldMapper获取设备信息");
		return besEquipmentMoldMapper.findeqTypeByBh(f_type);
	}
	
	@Override
	public boolean upeqType(BESEquipmentMold besEquipmentMold) {
		// TODO Auto-generated method stub
		log.info("#besEquipmentMoldMapper更新修改设备类型信息");
		return besEquipmentMoldMapper.upeqType(besEquipmentMold);
	}
	
	/**
	   * @Description 删除用户组信息
	   * @author yanyonghui
	   * @param json
	   * @return
	   */
	@Override
	public boolean deleqType(BESEquipmentMold besEquipmentMold) {
		if (besEquipmentMold != null) {
			 int flag = besEquipmentMoldMapper.delete(besEquipmentMold.getF_type());
			 if (flag == 1)
	                return true;
	            else
	                return false;
	        } else
	            return false;
	}

	
	/*public List<BESEquipmentMold> getNodesList(String f_type) {
		List<BESEquipmentMold> list = besEquipmentMoldMapper.getNodesList(f_type);
        return list;
	}*/
	/**
	 * 根据（多个）f_type查询设备名称
	 */
	@Override
	public List<BESEquipmentMold> getEquipmentMoldById(String f_type){
		return 	besEquipmentMoldMapper.getEquipmentMoldById(f_type);
	}
	/**
	 *
	 * @Description:查询bes_txt_end表里面有没有当前id关联的txt文本
	 *
	 * @auther: wanghongjie
	 * @date: 17:22 2020/4/29
	 * @param: [f_id]
	 * @return: com.core.common.ISSPReturnObject
	 *
	 */
	@Override
	public ISSPReturnObject selectProgrammInfo(Integer f_id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		String besSbPzStruct = besEquipmentMoldMapper.selectProgrammInfo(f_id);
		returnObject.setStatus(besSbPzStruct);
		return returnObject;
	}
}
