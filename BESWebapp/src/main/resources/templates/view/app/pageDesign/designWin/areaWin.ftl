<#--新增背景区域弹窗-->
<div id="design_add_area_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">区域宽度</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_area_width" value="150" placeholder="请输入区域宽度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">区域高度</label>
            <div class="layui-input-block">
                <input type="text" id="design_add_area_height" value="150" placeholder="请输入区域高度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="BackArea.addConfirm()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="BackArea.closeAddWin()">取消</button>
        </div>
    </div>
</div>
<#--修改背景区域弹窗-->

<div id="design_edit_area_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">区域宽度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_area_width" value="80" placeholder="请输入区域宽度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">区域高度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_area_height" value="40" placeholder="请输入区域高度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="BackArea.editConfirm()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="BackArea.closeEditWin()">取消</button>
        </div>
    </div>
</div>

