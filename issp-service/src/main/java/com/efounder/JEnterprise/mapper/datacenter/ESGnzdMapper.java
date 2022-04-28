package com.efounder.JEnterprise.mapper.datacenter;

import com.efounder.JEnterprise.mapper.BaseMapper;
import com.efounder.JEnterprise.model.datacenter.ESGnzd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description 功能字典mapper接口
 * @author gongfanfei
 */
@Mapper
public interface ESGnzdMapper extends BaseMapper<String, ESGnzd> {
	List<ESGnzd> findGnzdByMC(@Param("gnmc") String gnmc);

	boolean deleteByBH(@Param("gnbh") String gnbh, @Param("mkbh") String mkbh);

	/**
	 * 删除功能字典数据
	 * @param map 参数
	 * @return
	 */
	Integer delGnzdList(Map map);

	/**
	 * 删除前查询功能字典数据
	 * @param map 参数
	 * @return
	 */
	List<String> selectGnzdId(Map map);


	List<ESGnzd> findChildrenByBH(@Param("pgnbh") String pgnbh, @Param("mkbh") String mkbh);

	boolean updateMx(@Param("gnbh") String gnbh, @Param("mkbh") String mkbh, @Param("mx") String mx);
	
	int updateBygnbh(ESGnzd gnzd);
	
	int insertGnzd(ESGnzd gnzd);
	
	List<ESGnzd> loadGnzdList();
	/**
	 * 根据模块编号查询列表
	 * @param mkbh
	 * @return
	 */
	List<ESGnzd> loadGnzdListByMkuuid(String mkbh);
	/**
	 * 对应用户功能权限
	 * 根据模块编号查询列表
	 * @param mkbh
	 * @return
	 */
	List<ESGnzd> loadGnzdListByMkuuidAndTableAndSh(@Param("mkbh")String mkbh,@Param("tablename")String tablename,@Param("fYhbh")String fYhbh);
	/**
	 * 对应角色功能权限
	 * 根据模块编号查询列表
	 * @param mkbh
	 * @return
	 */
	List<ESGnzd> loadGnzdListByMkuuidAndTableAndShAndRole(@Param("mkbh")String mkbh,@Param("tablename")String tablename,@Param("fRolebh")String fRolebh);
	/**
	 * 根据模块编号和父节点为空作为条件查询功能字典列表
	 * @param mkbh
	 * @return
	 */
	List<ESGnzd> getGnzdByMkbhAndNull(String mkbh);
	/**
	 * 根据功能编号查询
	 * @param gnbh
	 * @return
	 */
	List<ESGnzd> loadGnzdListByGnbh(String gnbh);
	/**
	 * 根据父功能编号查询
	 * @param gnbh
	 * @return
	 */
	List<ESGnzd> loadGnzdListByPgnbh(String gnbh);
	/**
	 * 根据父功能编号查询
	 * @param gnbh
	 * @return
	 */
	List<ESGnzd> getGnzdListBygnzdlist(@Param("list")List<String> gnzdList);

	List<ESGnzd> loadGnzdListByMkuuidAndTableAndShAndGroup(@Param("guid")String guid,@Param("classifygnqxTb")String classifygnqxTb,@Param("f_zbh")String f_zbh);

	List<ESGnzd> loadGnzdListByMkuuidAndTableAndShAndPost(@Param("guid")String guid,@Param("classifygnqxTb")String classifygnqxTb,@Param("f_gwguid")String f_gwguid);
	
}
