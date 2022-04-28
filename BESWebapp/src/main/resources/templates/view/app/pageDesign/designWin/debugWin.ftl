<#--新增按钮弹窗-->
<div id="design_addBtnWin" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">按钮名称</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_btn_name" placeholder="请输入按钮名称" maxlength="20" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">按钮宽度</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_btn_width" value="80" placeholder="请输入按钮宽度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">按钮高度</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_btn_height" value="40" placeholder="请输入按钮高度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="DebugBtn.addDebugBtn()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="DebugBtn.closeAddDebugWin()">取消</button>
        </div>
    </div>
</div>
<#--修改按钮弹窗-->
<div id="design_editBtnWin" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">按钮名称</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_btn_name" placeholder="请输入按钮名称" maxlength="20" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">按钮宽度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_btn_width" value="80" placeholder="请输入按钮宽度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">按钮高度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_btn_height" value="40" placeholder="请输入按钮高度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="DebugBtn.editDebugBtn()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="DebugBtn.closeEditDebugWin()">取消</button>
        </div>
    </div>
</div>

<#--按钮关联点弹窗-->
<div id="design_debug_btn_relative" style="display: none" class="design_relative_win">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">点名称</label>
            <div class="layui-input-block">
                <input type="hidden" id="design_debug_btn_relative_id">
                <input type="text" id="design_debug_btn_relative_name" placeholder="" readonly autocomplete="off" class="layui-input relativePointName">
                <button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="DebugBtn.choosePoint()">选择点</button>
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="DebugBtn.relativeBtn()"><strong>关联</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="DebugBtn.closeRelativeWin()">取消</button>
        </div>
    </div>
</div>