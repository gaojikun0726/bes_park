package com.efounder.JEnterprise.model.strongAndWeakElectricityIntegration;

import com.core.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;
@Data
public class GljlModel implements BaseEntity<Serializable> {

    private static final long serialVersionUID = 5799362772943887072L;

    private String f_id;//日志ID

    private String f_username;//用户名

    private String f_type;//操作类型（0，是维护 1 是通信）

    private String f_type_name;//操作类型（0，是维护 1 是通信）

    private String f_remark;//操作说明

    private String f_gltime;//操作时间

    private String f_crdate;//创建时间

    private String f_chdate;//修改时间
}
