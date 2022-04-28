package com.efounder.JEnterprise.service.basedatamanage.energyinformation;

import com.core.common.ISSPReturnObject;
import com.efounder.JEnterprise.model.basedatamanage.energyinformation.BESPark;
import com.efounder.JEnterprise.service.ESBaseService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/** 
 * 类名称：BESParkService
 * 类描述：园区表service接口
 * 创建人：sunhao
 * 修改人：sunhao
 * 修改时间：2018年7月3日 
 * 修改备注：
 * @version 1.0.0 
 *
 */
public interface BESParkService extends ESBaseService{
	/**
	 * 分页查询
	 * @param keywords
	 * @return
	 */
	PageInfo<BESPark> findParkByPage(Integer pageNum, String keywords);
	/**
	 * 查询全部信息
	 * @author sunhao
	 * @param 
	 * @return
	 */
	public List<BESPark> findAllPark();
	/**
	 * 根据编号查询信息
	 * @author sunhao
	 * @param f_yqbh
	 * @return
	 */
	public BESPark findParkByYqbh(String f_yqbh);
	
	/**
	 * 根据编号查询信息
	 * @author yujieying
	 * @param 
	 * @return
	 */
	public List<BESPark> findParkinfoListByYqbh();
	/**
	 * 根据名称查询
	 * @author sunhao
	 * @param f_yqmc
	 * @return
	 */
	public List<BESPark> findParkByYqmc(@Param("f_yqmc") String f_yqmc);
	/**
	 * 模糊查询
	 * @author sunhao
	 * @param keywords
	 * 
	 * @return
	 */
	public List<BESPark> findParkByKeywords(@Param("keywords") String keywords);
	/**
	 * @author sunhao
	 * 加载信息
	 * @return
	 */
	public List<BESPark> loadAll();
	/**
	 * 增加信息
	 * @return
	 */
	public boolean addPark(BESPark park);
	/**
	 * 删除信息
	 * @param f_yqbh
	 * @return
	 */
	public ISSPReturnObject deleteByYqbh(BESPark park);
	/**
	 * 修改信息
	 * @param 
	 * @return
	 */
	public boolean upDatePark(BESPark park);
	/**
	 * 查询编号最大值
	 * @param 
	 * @return
	 */
	public String findMaxYqbh();
}
