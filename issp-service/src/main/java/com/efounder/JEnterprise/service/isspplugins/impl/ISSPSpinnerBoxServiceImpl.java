package com.efounder.JEnterprise.service.isspplugins.impl;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.mapper.isspplugins.ISSPSpinnerBoxMapper;
import com.efounder.JEnterprise.service.isspplugins.ISSPSpinnerBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 
 * @version 1.0.0
 *
 */
@Service("ISSPSpinnerBoxService")
public class ISSPSpinnerBoxServiceImpl implements ISSPSpinnerBoxService {

	@Autowired
	private ISSPSpinnerBoxMapper isspSpinnerBoxMapper;

	/**
	 * 加载下拉列表的数据
	 */
	@Override
	public ISSPReturnObject getData(String currId, String dataId, String dataTxt, String dataTabName, String conColumn, String conValue) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		Map<String, List<String>> map = new HashMap<>();
		List<String> ids = new ArrayList<>();
		List<String> txts = new ArrayList<>();
		StringBuffer conSql = new StringBuffer("");
		if (conColumn != null && !"".equals(conColumn) && conValue != null && !"".equals(conValue)) {
			if (conColumn.indexOf(",") != -1) {
				String[] conCls = conColumn.split(",");
				if (conValue.indexOf("','") != -1) {
					String[] conVals = conValue.replaceAll("','", "'_'").split("_");
					boolean isContainsArray = false;
					for (int i = 0; i < conCls.length; i++) {
						if (conVals[i].indexOf(",") != -1) {
							isContainsArray = true;
							break;
						}
						if (i != 0)
							conSql.append(" AND ");
						conSql.append(conCls[i].replaceAll("'", ""));
						conSql.append(" = ");
						conSql.append(conVals[i]);
					}
					if (isContainsArray) {
						conSql = new StringBuffer("");
						for (int i = 0; i < conCls.length; i++) {
							if (i != 0)
								conSql.append(" AND ");
							conSql.append(conCls[i].replaceAll("'", ""));
							conSql.append(" in ( ");
							conSql.append(conVals[i].replaceAll(",", "','"));
							conSql.append(" )");
						}
					}
				}
			} else {
				if (conValue.indexOf(",") != -1) {
					conSql.append(" AND ");
					conSql.append(conColumn);
					conSql.append(" in ( ");
					conSql.append(conValue);
					conSql.append(" )");
				}else{
					conSql.append(" AND ");
					conSql.append(conColumn);
					conSql.append(" = ");
					conSql.append(conValue);
				}
			}
			if (!"".equals(conSql.toString())) {
				conColumn = null;
				conValue = null;
			}
		}
		
		try {
			List<Map<String, String>> list = isspSpinnerBoxMapper.getData(dataId, dataTxt, dataTabName, conColumn, conValue, conSql.toString());
			if (list.size() == 0) {
				returnObject.setStatus("2");
				map.put("currId", Arrays.asList(currId));
				returnObject.setMap(map);
				return returnObject;
			}
			for (Map<String, String> m : list) {
				ids.add(String.valueOf(m.get(String.valueOf(dataId))));
				if(dataTxt.contains("REPLACE")){//REPLACE(F_XLMC, F_XLBH, '') AS F_XLMC
					String strArr[] = dataTxt.split("AS");
					String newColName = strArr[1].replaceAll(" ", "");
					txts.add(String.valueOf(m.get(String.valueOf(newColName))));
				}else{
					txts.add(String.valueOf(m.get(String.valueOf(dataTxt))));
				}

				
			}
			map.put("ids", ids);
			map.put("names", txts);
			map.put("currId", Arrays.asList(currId));
			returnObject.setMap(map);
			returnObject.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			returnObject.setStatus("0");
		}
		return returnObject;
	}

}