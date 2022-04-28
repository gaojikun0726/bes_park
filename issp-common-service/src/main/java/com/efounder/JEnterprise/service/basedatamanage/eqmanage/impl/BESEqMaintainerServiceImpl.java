package com.efounder.JEnterprise.service.basedatamanage.eqmanage.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.constants.Constants;
import com.efounder.JEnterprise.mapper.basedatamanage.eqmanage.BESEqMaintainerMapper;
import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEqMaintainer;
import com.efounder.JEnterprise.service.basedatamanage.eqmanage.BESEqMaintainerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 维护商字典
 * @author liujoujun
 *
 */
@Service("BESEqMaintainerService")
public class BESEqMaintainerServiceImpl implements BESEqMaintainerService{
	
	@Autowired
	private BESEqMaintainerMapper beSqMaintainerMapper;

	
	
	@Override
	public PageInfo<BESEqMaintainer> findByPage(Integer bars,Integer pageNum, String keywords) {
		if (pageNum == null)
			pageNum = 1;
		if (bars == null) {
			bars = Constants.PAGE_SIZE;
		}
		PageHelper.startPage(pageNum,bars );
		
		List<BESEqMaintainer> list = beSqMaintainerMapper.findByPage(pageNum,keywords);
		
		PageInfo<BESEqMaintainer> page = new PageInfo<BESEqMaintainer>(list);
		return page;
	}

	@Override
	public ISSPReturnObject addBESEqMaintainer(BESEqMaintainer vo) {
		ISSPReturnObject returnData = new ISSPReturnObject();
			try{
				boolean flag = beSqMaintainerMapper.insert(vo);
			if(flag){
				/*returnData.setData(vo);*/
				returnData.setStatus("1");
				returnData.setMsg("添加成功！");
			}else{
				returnData.setStatus("0");
				returnData.setMsg("添加失败！");
			}
			}catch (Exception e) {
				returnData.setStatus("0");
				returnData.setMsg("添加失败！");
			}
		return returnData;
	}

	@Override
	public ISSPReturnObject updBESEqMaintainer(BESEqMaintainer vo) {
		ISSPReturnObject returnData = new ISSPReturnObject();
		boolean flag = beSqMaintainerMapper.update(vo);
		try{
		
			if (flag) {
				returnData.setStatus("1");
				returnData.setMsg("修改成功！");
			} else {
				returnData.setStatus("0");
				returnData.setMsg("修改失败！");
			}
		}catch (Exception e) {
			returnData.setStatus("0");
			returnData.setMsg("修改失败！");
		}
		return returnData;
	}

	@Override
	public ISSPReturnObject delBESEqMaintainer(String f_id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try{
			boolean flag = beSqMaintainerMapper.delByFid(f_id);
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setMsg("删除成功！");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("删除失败！");
			}
		}catch (Exception e) {
			// TODO: handle exception
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败！");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject findBESEqMaintainerByFid(String f_id) {
		ISSPReturnObject returnData = new ISSPReturnObject();
		try{
			BESEqMaintainer data = beSqMaintainerMapper.findByFid(f_id);
			if(data !=null){
			returnData.setData(data);
			returnData.setStatus("1");
			}else{
				returnData.setStatus("0");
				returnData.setMsg("信息获取失败！");
			}
		}catch (Exception e){
			returnData.setStatus("0");
			returnData.setMsg("信息获取失败！");
		}
		return returnData;
	}

}
