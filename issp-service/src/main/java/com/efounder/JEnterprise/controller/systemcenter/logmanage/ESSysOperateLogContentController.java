package com.efounder.JEnterprise.controller.systemcenter.logmanage;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysOperateLogContent;
import com.efounder.JEnterprise.service.systemcenter.logmanage.ESSysOperateLogContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 系统操作日志查看详细信息
 * @author liuhoujun
 * @date 2018/11/1
 */

@RequestMapping(value = "/view/sysmanage/logmanage")
@Controller
public class ESSysOperateLogContentController {
	private static final Logger log = LoggerFactory.getLogger(ESSysOperateLogContentController.class);

    @Autowired
	private ESSysOperateLogContentService esSysOperateLogContentService;
    
    /**
	 * 根据搜索条件分页显示信息  
	 * @return
	 */
	@RequestMapping(value = "/loadsysOperatelogContentlist", method = RequestMethod.POST)
	@ResponseBody
	public ISSPReturnObject loadsysOperatelogContentByKey(String f_id) {
		log.info("#ESSysOperateLogContentController系统操作日志详情加载");
		ISSPReturnObject returnObject = new ISSPReturnObject();
		List<ESSysOperateLogContent> list = esSysOperateLogContentService.loadsysOperatelogContentByKey(f_id);
	/*
	for (ESSysOperateLogContent esSysOperateLogContent : list) {
			String[] getfTbKeyAttr = esSysOperateLogContent.getfTbKey().split("@");
			String[] splitAttr = esSysOperateLogContent.getfTbValue().split("@");
			for(int i =0;i<getfTbKeyAttr.length;i++){
				String string = splitAttr[i];
				log.info(string);
			}
		}*/
		List<ESSysOperateLogContent> newlist = new ArrayList<ESSysOperateLogContent>();
		if(list.size()>0){
			boolean isUpdate = false;//默认false为非修改
			List<String> tbCurrentValuelist = new ArrayList<String>();
			if(list.get(0).getfCurrenttbValue()!=null && !list.get(0).getfCurrenttbValue().equals("")){
				//表字段
				String[] getfTbCurrentValue = list.get(0).getfCurrenttbValue().split("#");
				for (int i = 1; i < getfTbCurrentValue.length; i++) {
					tbCurrentValuelist.add(getfTbCurrentValue[i].substring(1));
				}
				isUpdate = true;
			}
			
			//表字段
			String[] getfTbKey = list.get(0).getfTbKey().split("#");
			List<String> tbkeylist = new ArrayList<String>();
			for (int i = 1; i < getfTbKey.length; i++) {
				tbkeylist.add(getfTbKey[i].substring(1));
			}
			//表字段值
			String[] getTbValue = list.get(0).getfTbValue().split("#");
			List<String> tbValuelist = new ArrayList<String>();
			for (int i = 1; i < getTbValue.length; i++) {
				tbValuelist.add(getTbValue[i].substring(1));
			}
			//表字段注释
			String[] getTbComment = list.get(0).getfComment().split("#");
			List<String> tbCommentlist = new ArrayList<String>();
			for (int i = 1; i < getTbComment.length; i++) {
				tbCommentlist.add(getTbComment[i].substring(1));
			}
			
			for (int i = 0; i < tbValuelist.size(); i++) {
				ESSysOperateLogContent operationObj = new ESSysOperateLogContent();
				operationObj.setfId(list.get(0).getfId());
				operationObj.setfLogId(list.get(0).getfLogId());
				operationObj.setfTbKey(tbkeylist.get(i));
				if(isUpdate==true){//为修改，添加值
					operationObj.setfCurrenttbValue(tbCurrentValuelist.get(i));
				}else{//非修改
					operationObj.setfCurrenttbValue("");
				}
				operationObj.setfTbValue(tbValuelist.get(i));
				operationObj.setfComment(tbCommentlist.get(i));
				operationObj.setfChdate(list.get(0).getfChdate());
				operationObj.setfCrdate(list.get(0).getfCrdate());
				newlist.add(operationObj);
			}
		}
		returnObject.setList(newlist);
		
		return returnObject;
	}
}
