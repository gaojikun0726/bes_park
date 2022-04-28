<#--字体设置弹窗-->

<div id="design_text_setting_win" style="display: none" class="designWin">
    <div class="layui-form">
        <table id = "textSettingTable" style = "width:100%;">
            <tr>
                <td>字体</td>
                <td>字形</td>
                <td>大小</td>
            </tr>
            <tr>
                <td style = "width: 35%;padding-right: 5%;">
                    <select id="textForm" class="label_relative_select label_state">
                        <option value="">默认</option>
                        <option value="SimSun">宋体</option><#--font-family:SimSun-->
                        <option value="KaiTi">楷体</option>
                        <option value="SimHei">黑体</option>
                        <option value="FangSong">仿宋</option>
                    </select>
                </td>
                <td style = "width: 35%;padding-right: 5%;">
                    <select id="textFont" class="label_relative_select label_state">
                        <option value="">默认</option>
                        <option value="normal">常规</option><#--font-weight:normal-->
                        <option value="normal oblique">倾斜</option><#--font-style: oblique;-->
                        <option value="bold">粗体</option>
                        <option value="bold oblique">粗体 倾斜</option>
                    </select>
                </td>
                <td style = "width: 35%;">
                    <select id="textSize" class="label_relative_select label_state">
                        <option value="">默认</option>
                        <#--<option value="8px">8</option>-->
                        <option value="10px">10</option>
                        <option value="12px">12</option>
                        <option value="14px">14</option><#--font-size:111-->
                        <option value="16px">16</option>
                        <option value="18px">18</option>
                        <option value="20px">20</option>
                        <option value="22px">22</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>颜色</td>
                <td>效果</td>
            </tr>
            <tr>
                <td style = "width: 35%;padding-right: 5%;">
                    <select id="textColor" class="label_relative_select label_state">
                        <option value="">默认</option>
                        <option value="#333333">黑色</option><#--color:black-->
                        <option value="#ffffff">白色</option>
                        <option value="#8fe3f7">蓝色</option>
                        <option value="#ff0000">红色</option>
                        <option value="#3cb371">绿色</option>
                    </select>
                </td>
                <td>
                    <input type="checkbox" id="deleteLine" name = "textLine" value = "line-through" style = "display:block;float: left;">删除线<br>
                    <input type="checkbox" id="underLine" name = "textLine" value = "underline" style = "display:block;float: left;">下划线
                </td>
            </tr>

        </table>

        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="TextSettingLabel.settingConfirmNew()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal" onclick="TextSettingLabel.closeTextSettingWin()">取消</button>
        </div>
    </div>
</div>
