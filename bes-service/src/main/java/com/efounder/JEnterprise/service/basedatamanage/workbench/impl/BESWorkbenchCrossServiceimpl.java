package com.efounder.JEnterprise.service.basedatamanage.workbench.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.common.util.UUIDUtil;
import com.efounder.JEnterprise.mapper.basedatamanage.workbench.BESWorkbenchCrossMapper;
import com.efounder.JEnterprise.model.BesWorkbench.BESWorkbenchCrossType;
import com.efounder.JEnterprise.service.ESBaseService;
import com.efounder.JEnterprise.service.basedatamanage.workbench.BESWorkbenchCrossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("BESWorkbenchCrossService")

public class BESWorkbenchCrossServiceimpl implements BESWorkbenchCrossService,ESBaseService{
	
	@Autowired BESWorkbenchCrossMapper besworkbenchcrossmapper;

	public List<BESWorkbenchCrossType> getWorkbenchTableList(String f_gztzzjg_id) {
		if (f_gztzzjg_id == null)
			f_gztzzjg_id = "1";
		List<BESWorkbenchCrossType> list = besworkbenchcrossmapper.getWorkbenchTableList(f_gztzzjg_id);
		return list;
	}

	@Override
	public ISSPReturnObject add_WorkbenchCross(BESWorkbenchCrossType besworkbenchcrosstype) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			String f_id=UUIDUtil.getRandom32BeginTimePK();
			besworkbenchcrosstype.setF_id(f_id);
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String f_addtime = sdf.format(d);
			besworkbenchcrosstype.setF_CRDATE(f_addtime);
			boolean flag = besworkbenchcrossmapper.add_WorkbenchCross(besworkbenchcrosstype);
			if (flag) {
				returnObject.setStatus("1");
				returnObject.setData(besworkbenchcrosstype);
				returnObject.setMsg("操作成功");
			} else {
				returnObject.setStatus("0");
				returnObject.setMsg("操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
			returnObject.setMsg("数据异常！请重新输入");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject edit_WorkbenchCross(BESWorkbenchCrossType besworkbenchcrosstype) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String f_edittime = sdf.format(d);
			besworkbenchcrosstype.setF_CHDATE(f_edittime);
			boolean flag = besworkbenchcrossmapper.edit_WorkbenchCross(besworkbenchcrosstype);
			returnObject.setMsg("操作成功");
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setMsg("操作失败");
			returnObject.setStatus("0");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject del_WorkbenchCross(String id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		boolean flag = besworkbenchcrossmapper.del_WorkbenchCross(id);
		if (flag) {
			returnObject.setStatus("1");
			returnObject.setMsg("删除成功！");
		} else {
			returnObject.setStatus("0");
			returnObject.setMsg("删除失败！");
		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getWorkbenchTable(String f_gztzzjg_id) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		try {
			List<BESWorkbenchCrossType> list = besworkbenchcrossmapper.getWorkbenchTableList(f_gztzzjg_id);
			returnObject.setList(list);
		} catch (Exception e) {

		}
		return returnObject;
	}

	@Override
	public ISSPReturnObject getWorkbench(String bh) {
		ISSPReturnObject object = new ISSPReturnObject();
		try {
			BESWorkbenchCrossType selmo = besworkbenchcrossmapper.getWorkbench(bh);
			object.setData(selmo);
			object.setStatus("1");
		} catch (Exception e) {
			object.setStatus("0");
		}
		return object;
	}
}
