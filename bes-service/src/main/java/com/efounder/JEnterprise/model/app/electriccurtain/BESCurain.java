package com.efounder.JEnterprise.model.app.electriccurtain;

import java.io.Serializable;

/**
 * @Author Sunzhiyuan
 * @Date 2020/5/12 11:38
 */
public class BESCurain implements Serializable {

    private static final long serialVersionUID = 1L;

   //主键
    private Integer id;
    //姓名
    private  String name;
    //地址
    private  String electriccurtain_address;
    //开关
    private String electriccurtain_switch;
    //开关系统名称
    private  String electriccurtain_switch_system;
    //开度控制
    private  String electriccurtain_kdkz_system;
    //开度控制 展示用
    private  String electriccurtain_kdkz;
    //暂停功能系统名称
    private  String electriccurtain_stop_system;
    //暂停功能
    private  String electriccurtain_stop;

    public String getElectriccurtain_kdkz_system() {
        return electriccurtain_kdkz_system;
    }

    public void setElectriccurtain_kdkz_system(String electriccurtain_kdkz_system) {
        this.electriccurtain_kdkz_system = electriccurtain_kdkz_system;
    }

    public String getElectriccurtain_kdkz() {
        return electriccurtain_kdkz;
    }

    public void setElectriccurtain_kdkz(String electriccurtain_kdkz) {
        this.electriccurtain_kdkz = electriccurtain_kdkz;
    }

    public String getElectriccurtain_stop_system() {
        return electriccurtain_stop_system;
    }

    public void setElectriccurtain_stop_system(String electriccurtain_stop_system) {
        this.electriccurtain_stop_system = electriccurtain_stop_system;
    }

    public String getElectriccurtain_stop() {
        return electriccurtain_stop;
    }

    public void setElectriccurtain_stop(String electriccurtain_stop) {
        this.electriccurtain_stop = electriccurtain_stop;
    }

    public BESCurain() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElectriccurtain_address() {
        return electriccurtain_address;
    }

    public void setElectriccurtain_address(String electriccurtain_address) {
        this.electriccurtain_address = electriccurtain_address;
    }

    public String getElectriccurtain_switch() {
        return electriccurtain_switch;
    }

    public void setElectriccurtain_switch(String electriccurtain_switch) {
        this.electriccurtain_switch = electriccurtain_switch;
    }

    public String getElectriccurtain_switch_system() {
        return electriccurtain_switch_system;
    }

    public void setElectriccurtain_switch_system(String electriccurtain_switch_system) {
        this.electriccurtain_switch_system = electriccurtain_switch_system;
    }
}
