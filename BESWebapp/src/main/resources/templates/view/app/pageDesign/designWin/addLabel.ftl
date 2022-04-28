<#--新增标签弹窗-->
<div id="design_add_label_win" style="display: none" class="designWin">
    <div class="layui-form">
        <#--<div class="layui-form-item">-->
            <#--<label class="layui-form-label">标签名称</label>-->
            <#--<div class="layui-input-block">-->
                <#--<input type="text" id="design_add_label_name" placeholder="请输入标签名称" autocomplete="off" class="layui-input">-->
            <#--</div>-->
        <#--</div>-->
        <div class="layui-form-item">
            <label class="layui-form-label">标签文本</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_label_text" placeholder="请输入标签文本" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddLabel.addConfirm()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddLabel.closeAddWin()">取消</button>
        </div>
    </div>
</div>
<#--修改标签弹窗-->
<div id="design_edit_label_win" style="display: none" class="designWin">
    <div class="layui-form">
        <#--<div class="layui-form-item">-->
            <#--<label class="layui-form-label">标签名称</label>-->
            <#--<div class="layui-input-block">-->
                <#--<input type="text" id="design_edit_label_name" placeholder="请输入标签名称" autocomplete="off" class="layui-input">-->
            <#--</div>-->
        <#--</div>-->
        <div class="layui-form-item">
            <label class="layui-form-label">标签文本</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_label_text" placeholder="请输入标签文本" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddLabel.editConfirm()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddLabel.closeEditWin()">取消</button>
        </div>
    </div>
</div>


<#--标签关联弹窗-->
<div id="design_label_relative" style="display: none" class="design_relative_win label_relative_win">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">点名称</label>
            <div class="layui-input-block">
                <input type="hidden" id="design_label_relative_id">
                <input type="text" readonly id="design_label_relative_name" placeholder="" autocomplete="off" class="layui-input relativePointName">
                <button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="AddLabel.choosePoint()">选择点</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">关联配置</label>
            <#--<table id="design_label_relative_table" style="display:none">-->

            <#--</table>-->
            <div style="float:right;">
                <label class="layui-form-label">配置类型</label>
                <div class="layui-input-block">
                    <input name="labelConfigType" type="radio" value="1" checked title="状态量" lay-filter="labelConfigType">
                    <input name="labelConfigType" type="radio" value="2" title="区间量" lay-filter="labelConfigType">
                <#--<select id="config_type">-->
                <#--<option value="1">状态量</option>-->
                <#--<option value="2">区间量</option>-->
                <#--</select>-->
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="configDiv">
                <div class="stateDiv">
                    <input type="text" value="显示文本" class="form-control text" readonly>
                    <input type="text" value="数值" class="form-control num" readonly>
                    <#--<input type="text" value="背景颜色" class="form-control bgColor" readonly>-->
                    <button class="fa fa-plus btn btn-default plusBtn" type="button" onclick="AddLabel.addStateRow()"></button>
                    <button class="fa fa-minus btn btn-default plusBtn" type="button" onclick="AddLabel.deleteStateRow()"></button>
                </div>
                <div class="intervalDiv" style="display: none;">
                    <input type="text" value="显示文本" class="form-control text" readonly>
                    <input type="text" value="区间范围" class="form-control num" readonly>
                    <#--<input type="text" value="背景颜色" class="form-control bgColor" readonly>-->
                    <button class="fa fa-plus btn btn-default plusBtn" type="button" onclick="AddLabel.addIntervalRow()"></button>
                    <button class="fa fa-minus btn btn-default plusBtn" type="button" onclick="AddLabel.deleteIntervalRow()"></button>
                </div>
                <div class="dataDiv">
                <#--<div class="configRow">-->
                <#--<input type="text" class="form-control text">-->
                <#--<input type="text" class="form-control num">-->
                <#--<div class="bgDiv"><div class="textboxBgColor"></div></div>-->
                <#--</div>-->
                </div>
                <div class="intervalDataDiv" style="display: none;">
                <#--<div class="configRow">-->
                <#--<input type="text" class="form-control text">-->
                <#--<div class="numDiv">-->
                <#--<input type="text" class="min">-->
                <#--<span>-</span>-->
                <#--<input type="text" class="max">-->
                <#--<span class="unit"></span>-->
                <#--</div>-->
                <#--<div class="bgDiv"><div class="textboxBgColor"></div></div>-->
                <#--</div>-->
                <#--<div class="configRow">-->
                <#--<input type="text" class="form-control text">-->
                <#--<input type="number" class="form-control num min">-->
                <#--<input type="number" class="form-control num max">-->
                <#--</div>-->
                </div>
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddLabel.relativePointBtn()"><strong>关联</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddLabel.closeRelativeWin()">取消</button>
        </div>
    </div>
</div>

<#--默认开关两种状态-->
<div class="label_default_state_config" style="display: none;">
    <div class="configRow">
        <input type="text" class="form-control text" value="开">
        <input type="text" class="form-control num" value="255">
    </div>
    <div class="configRow">
        <input type="text" class="form-control text" value="关">
        <input type="text" class="form-control num" value="0">
    </div>
</div>

<table id="design_label_default_content" style="display: none;">
    <tr>
        <td>点状态</td>
        <td>关系</td>
        <td>显示文本</td>
    </tr>
    <tr>
        <td>
            <select class="label_relative_select label_state">
                <option value="255" selected="selected">闭合</option>
                <option value="0">断开</option>
                <option value="else">其他</option>
            </select>
        </td>
        <td>等于</td>
        <td>
            <input class="layui-input label_text" value="运行">
        </td>
    </tr>
    <tr>
        <td>
            <select class="label_relative_select label_state">
                <option value="255">闭合</option>
                <option value="0" selected="selected">断开</option>
                <option value="else">其他</option>
            </select>
        </td>
        <td>等于</td>
        <td>
            <input class="layui-input label_text" value="停止">
        </td>
    </tr>
</table>


