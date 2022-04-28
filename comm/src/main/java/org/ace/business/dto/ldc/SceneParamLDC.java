package org.ace.business.dto.ldc;

/**
 * 场景参数定义
 * @author xiepufeng
 * @date 2020/6/29 8:22
 */
public class SceneParamLDC
{
    // 0 控制场景 1 采集场景
    private Integer sceneType;

    private SceneDataLDC data;

    public Integer getSceneType()
    {
        return sceneType;
    }

    public void setSceneType(Integer sceneType)
    {
        this.sceneType = sceneType;
    }

    public SceneDataLDC getData()
    {
        return data;
    }

    public void setData(SceneDataLDC data)
    {
        this.data = data;
    }
}
