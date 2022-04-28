package com.efounder.JEnterprise.mapper.key;

import com.efounder.JEnterprise.config.db.table.Key;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface KeyMapper {

    /**
     * @return 返回key集合
     */
    public List<Key> getTableValues(List<Key> keys);

    /**
     * @return 返回key集合(只存储表名)
     */
    public List<Key> getTables();

}
