<#--添加流程节点-->
<div id="design_add_flow_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">流程节点</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_flow_name" placeholder="请输入流程节点" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="FlowLable.addFlowBtn()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="FlowLable.closeAddFlowWin()">取消</button>
        </div>
    </div>
</div>

<#--修改流程节点弹窗-->
<div id="design_edit_flow_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">流程节点</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_flow_name" placeholder="请输入流程节点" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="FlowLable.editFlowBtn()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="FlowLable.closeEditFlowWin()">取消</button>
        </div>
    </div>
</div>



<div id="design_flow_btn_relative" style="display: none" class="design_relative_win label_relative_win">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">点名称</label>
            <div class="layui-input-block">
                <input type="hidden" id="design_flow_btn_relative_id">
                <input type="text" readonly id="design_flow_btn_relative_name" placeholder="" autocomplete="off" class="layui-input relativePointName">
                <button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="FlowLable.choosePoint()">选择点</button>
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="FlowLable.relativeBtn()"><strong>关联</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="FlowLable.closeRelativeWin()">取消</button>
        </div>
    </div>
</div>