package com.efounder.JEnterprise.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @CkassName: BesPz
 * @Author: YangChao
 * @Date: 2019/1/24 11:08
 * @Descruotuib:
 * @Version: 1.0
 **/
@Component
public class BesPzlj {

    // 运行时间
    @Value("${system.parameter.runingTime}")
    private String runingTime;

    @Value("${system.parameter.distinguish}")
    private String PZLJ;

    public String getPZLJ() {
        return PZLJ;
    }

    public void setPZLJ(String pZLJ) {
        PZLJ = pZLJ;
    }

    public String getRuningTime() {

        return runingTime;
    }

    public void setRuningTime(String runingTime) {

        this.runingTime = runingTime;
    }
}
