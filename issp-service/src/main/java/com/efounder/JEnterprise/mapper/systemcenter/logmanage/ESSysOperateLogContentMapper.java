package com.efounder.JEnterprise.mapper.systemcenter.logmanage;

import com.efounder.JEnterprise.model.systemcenter.logmanage.ESSysOperateLogContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 操作日志查看详细信息
 * 
 * @author zc_liuhoujun
 *
 */
@Mapper
public interface ESSysOperateLogContentMapper {

	// 显示系统操作日志详细信息
	List<ESSysOperateLogContent> loadsysOperatelogContentByKey(@Param("f_id") String f_id);
}
