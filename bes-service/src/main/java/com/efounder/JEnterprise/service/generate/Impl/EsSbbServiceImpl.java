package com.efounder.JEnterprise.service.generate.Impl;

import com.efounder.JEnterprise.mapper.generate.EsSbbMapper;
import com.efounder.JEnterprise.model.generator.EsSbb;
import com.efounder.JEnterprise.service.generate.EsSbbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EsSbbServiceImpl implements EsSbbService {

    private static final Logger log = LoggerFactory.getLogger(EsSbbServiceImpl.class);
    @Resource
    private EsSbbMapper esSbbDao;

    @Override
    public List<EsSbb> findUsers() {
        return null;
    }

    @Override
    public List<EsSbb> findUsersBykey(String keywords) {
        return null;
    }

    @Override
    public EsSbb findUserById(String id) {
        return null;
    }

    /**
     * 根据组织机构获取设备
     * @param zzjgbh
     * @return
     */
    @Override
    public List<EsSbb> selectByPrimarykeywords(String zzjgbh) {
        List<EsSbb> list = esSbbDao.selectByPrimarykeywords(zzjgbh);

        return list;
    }

    @Override
    public EsSbb findsbblId(String id) {
        return esSbbDao.findsbblId(id);
    }

    /**
     * 添加设备
     * @param essbb
     * @return
     */
    @Override
    public boolean insertSelective(EsSbb essbb) {
        return esSbbDao.insertSelective(essbb);
    }

    @Override
    public List<EsSbb> selectbuyjiji(String sbbkeywords) {
        return esSbbDao.selectbuyjiji(sbbkeywords);
    }

    /**
     * 根据ID删除设备
     * @param essbb
     * @return
     */
    @Override
    public boolean deleteByPrimaryKey(EsSbb essbb) {
        return esSbbDao.deleteByPrimaryKey(essbb);
    }

    /**
     * 根据ID修改设备信息
     * @param essbb
     * @return
     */
    @Override
    public boolean updateByPrimaryKey(EsSbb essbb) {
        return esSbbDao.updateByPrimaryKey(essbb);
    }
}
