package com.efounder.JEnterprise.model.dataAnalysises.exportAnalysis;

import com.framework.common.utils.ExcelVOAttribute;

/**
 * @author 杨超--YangChao
 * @version 创建时间：2018年12月10日 下午6:26:02
 * @parameter 测试实体类
 * @version 1.0
 */
public class BESAnalysis {

    @ExcelVOAttribute(name = "序号", column = "A")
    private int id;

    @ExcelVOAttribute(name = "姓名", column = "B", isExport = true)
    private String name;

    @ExcelVOAttribute(name = "年龄", column = "C", prompt = "年龄保密哦!", isExport = true)
    private int age;

    @ExcelVOAttribute(name = "班级", column = "D", combo = { "五期提高班", "六期提高班", "七期提高班" })
    private String clazz;

    @ExcelVOAttribute(name = "公司", column = "E")
    private String company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", company=" + company + ", age=" + age + ", clazz=" + clazz
                + "]";
    }
}
