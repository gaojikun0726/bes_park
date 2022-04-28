<#--新增按钮弹窗-->
<div id="design_addFlowTypeWin" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label" style ="width:100px;">流程图名称</label>
            <div class="layui-input-block" style ="width:210px;">
                <input type="text" id="add_flowTypeName" placeholder="请输入流程图名称" maxlength="20" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddFlowType.addFlowType()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddFlowType.closeAddFlowTypeWin()">取消</button>
        </div>
    </div>
</div>
<#--修改流程图弹窗-->
<div id="design_editFlowTypeWin" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label" style ="width:100px;">流程图名称</label>
            <div class="layui-input-block" style ="width:210px;">
                <input type="text" id="edit_flowTypeName" placeholder="请输入流程图名称" maxlength="20" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddFlowType.editFlowType()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddFlowType.closeEditFlowTypeWin()">取消</button>
        </div>
    </div>
</div>

