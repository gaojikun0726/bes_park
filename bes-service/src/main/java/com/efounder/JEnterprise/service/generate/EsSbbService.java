package com.efounder.JEnterprise.service.generate;

import com.efounder.JEnterprise.model.generator.EsSbb;

import java.util.List;

public interface EsSbbService {


    /**
     * 查询所有的用户
     * @return
     */
    public List<EsSbb> findUsers();

    public  List<EsSbb> findUsersBykey(String keywords);


    /**
     * 根据id查询设备
     * @param id
     * @return
     */
    public EsSbb findUserById(String id);


    /**
     * 根据组织机构id查询用户列表
     * @param id
     * @return
     */
    public List<EsSbb> selectByPrimarykeywords(String zzjgbh);

    /**
     * 根据用户组关系id查询设备
     * @return
     */
    public EsSbb findsbblId(String id);

    /**
     * 新增设备
     */
    public boolean insertSelective(EsSbb essbb);


    /**
     * 搜索
     */
    List<EsSbb> selectbuyjiji(String sbbkeywords);
    /**
     * 删除设备
     * @return
     */
    public boolean deleteByPrimaryKey(EsSbb essbb);

    /**
     * 更新修改设备
     * @return
     */
    public boolean updateByPrimaryKey(EsSbb essbb);


}
