package com.efounder.JEnterprise.mapper.basedatamanage.eqmanage;

import com.efounder.JEnterprise.model.basedatamanage.eqmanage.BESEqMaintainer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 维护商字典
 * @author liujoujun
 *
 */
@Mapper
public interface BESEqMaintainerMapper {

	// 添加一条记录
	public boolean insert(BESEqMaintainer vo);

	// 根据id删除一条记录
	public boolean delByFid(@Param("f_id") String f_id);

	// 更新一条记录
	public boolean update(BESEqMaintainer vo);

	// 根据设备Id获取记录
	public BESEqMaintainer findByFid(@Param("f_id") String f_id);

	public List<BESEqMaintainer> findByPage(@Param("pageNum") Integer pageNum, @Param("keywords") String keywords);
}
