package org.ace.business.dto.ddc;

/**
 * 获取场景信息里的模式数据
 * @author sunzhiyuan
 * @Data 2020/12/18 11:11
 */
public class SceneModeParamDDC {

    private Integer sceneId; // 场景的ID

    private Integer modeId; // 模式的ID

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }


    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

}
