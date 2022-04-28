package com.efounder.JEnterprise.service.key.impl;

import com.efounder.JEnterprise.config.db.table.Key;
import com.efounder.JEnterprise.mapper.key.KeyMapper;
import com.efounder.JEnterprise.service.key.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



/** 
 *  
 * 类名称：KeyServiceImpl
 * 类描述：主键生成
 * 创建人：wujf
 * 修改人：wujf
 * 修改时间：2018年1月3日 下午4:51:06
 * 修改备注：
 * @version 1.0.0 
 *
 */
@Service("keyService")
public class KeyServiceImpl implements KeyService {

    @Autowired
    private KeyMapper keyMapper;

    public void setKeyMapper(KeyMapper keyMapper) {
        this.keyMapper = keyMapper;
    }

    @Override
    public List<Key> getTableValues(List<Key> keys) {
        List<Key> keyList = new ArrayList<Key>();
        try {
            keyList = keyMapper.getTableValues(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyList;
    }

    @Override
    public List<Key> getTables() {
        List<Key> keyList = new ArrayList<Key>();
        try {
            keyList = keyMapper.getTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyList;
    }
}
