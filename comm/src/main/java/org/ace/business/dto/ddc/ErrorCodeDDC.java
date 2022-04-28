package org.ace.business.dto.ddc;

/**
 *  错误码定义
 * @author xiepufeng
 * @date 2020/6/28 13:47
 */
public class ErrorCodeDDC
{
    private Integer errorCode;

    private Integer id;

    private String moduleName;

    public Integer getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode)
    {
        this.errorCode = errorCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
