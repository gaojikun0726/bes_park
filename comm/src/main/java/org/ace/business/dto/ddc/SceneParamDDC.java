package org.ace.business.dto.ddc;

/**
 * 场景参数定义
 * @author xiepufeng
 * @date 2020/6/29 8:22
 */
public class SceneParamDDC
{
    // 0 控制场景 1 采集场景
    private Integer sceneType;

    private SceneDataDDC data;

    public Integer getSceneType()
    {
        return sceneType;
    }

    public void setSceneType(Integer sceneType)
    {
        this.sceneType = sceneType;
    }

    public SceneDataDDC getData()
    {
        return data;
    }

    public void setData(SceneDataDDC data)
    {
        this.data = data;
    }
}
