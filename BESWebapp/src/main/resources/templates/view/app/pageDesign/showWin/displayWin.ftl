<#--调试弹窗-->
<div id="debugWin" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">系统名称</label>
            <div class="layui-input-block">
                <input type="text" id="system_name" readonly placeholder="请输入系统名称" autocomplete="off" class="layui-input">
                <input type="hidden" id="f_node_attribution">
            </div>
        </div>
        <div class="layui-form-item debug_switch">
            <label class="layui-form-label">设置</label>
            <div class="layui-input-block">
                <select id="debugSet" class="layui-input">
                    <#--<option value="0">关机</option>-->
                    <#--<option value="255">开机</option>-->
                </select>
            </div>
        </div>
        <div class="layui-form-item debug_input" style="display: none">
            <label class="layui-form-label">设置</label>
            <div class="layui-input-block">
                    <input type="text" id="debug_input_value" placeholder="" autocomplete="off" class="layui-input" maxlength="10">
                    <span class="design_unit"></span>
            </div>
        </div>
        <div class="layui-form-item debug_work_mode">
            <label class="layui-form-label">工作模式</label>
            <div class="layui-input-block">
                <input type="radio" name="work_mode" value="0" title="自动" checked>
                <input type="radio" name="work_mode" value="1" title="手动">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="DebugEvent.debugExecute()"><strong>执行</strong></button>
            <button class="btn btn-md btn-primary btn-white" type="button" onclick="DebugEvent.closeDebugWin()">取消</button>
        </div>
    </div>
</div>
