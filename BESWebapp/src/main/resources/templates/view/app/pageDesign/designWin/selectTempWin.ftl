<#--字体设置弹窗-->

<div id="design_type_controller_win" style="display: none" class="designWin">
    <div class="layui-form">
        <#-- id重复 textSettingTable-->
        <table style = "width:100%;">
            <#--温控器调试窗口-->
            <#include "view/app/pageDesign/designWin/systemSetting.ftl"/>
            <#--调试事件-->
            <script src="${ctx}/static/pageDesign/module/show/tempIconEvent.js" type="text/javascript"></script>
            <tr>
                <td>温控器界面</td>
                <td>
                    <input type="radio" id="low" name = "conditioner_level" value = "low" title="低档温控器" checked>
                    <input type="radio" id="middle" name = "conditioner_level" value = "middle" title="中档温控器">
                </td>
            </tr>
            <tr>
                <td>呈现方式</td>
                <td>
                    <input type="radio" id="icons" name = "tempType" value = "icons" checked title="图标">
                    <input type="radio" id="listInfo" name = "tempType" value = "listInfo" title="列表">
                </td>
            </tr>

        </table>

        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="ControllerTempLabel.confirmControllerType()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal" onclick="ControllerTempLabel.closeSelectTypeWin()">取消</button>
        </div>
    </div>

</div>
