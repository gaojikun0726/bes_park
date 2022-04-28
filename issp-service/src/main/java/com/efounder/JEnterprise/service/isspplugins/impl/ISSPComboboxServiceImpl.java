package com.efounder.JEnterprise.service.isspplugins.impl;

import com.core.common.ISSPReturnObject;
import com.core.common.tree.ISSPTreeBuilder;
import com.core.common.tree.ISSPTreeNode;
import com.efounder.JEnterprise.mapper.isspplugins.ISSPComboboxMapper;
import com.efounder.JEnterprise.service.isspplugins.ISSPComboboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @version 1.0.0
 *
 */
@Service("BESCommonService")
public class ISSPComboboxServiceImpl implements ISSPComboboxService {

	@Autowired
	private ISSPComboboxMapper besCommonMapper;

	/**
	 * 将数据整合为树的格式
	 */
	@Override
	public ISSPReturnObject getData(String tabName, String disCol, String disColId, String conColumn, String conValue) {
		ISSPReturnObject returnObject = new ISSPReturnObject();
		StringBuffer conSql = new StringBuffer("");
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
				if(isContainsArray){
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
		} else if ((conColumn.indexOf(",") == -1) && (conValue.indexOf("','") != -1)) {
			/*conColumn不是数组，且conValue是数组时：筛选列只有一项 */
			conSql = new StringBuffer("");
			conSql.append(conColumn);
			conSql.append(" in ( ");
			conSql.append(conValue);
			conSql.append(" )");
		}
		
		if (!"".equals(conSql.toString())) {
			conColumn = null;
			conValue = null;
		}
		try {
			List<Map<String, String>> list = besCommonMapper.getData(tabName, disCol, disColId, conColumn, conValue, conSql.toString());
			List<ISSPTreeNode> nodes = new ArrayList<ISSPTreeNode>();
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> m = list.get(i);
				ISSPTreeNode isspPTreeNode = new ISSPTreeNode();
				isspPTreeNode.setId(m.get(disColId));
				isspPTreeNode.setPid("root");
				isspPTreeNode.setText(m.get(disCol));
				nodes.add(isspPTreeNode);// 添加到节点容器
			}
			List<ISSPTreeNode> tree = ISSPTreeBuilder.buildTree(nodes);// 调用共同静态类创建树
			returnObject.setList(tree);
			returnObject.setStatus("1");
		} catch (Exception e) {
			returnObject.setStatus("0");
		}
		return returnObject;
	}

}