
<#--修改图片弹窗-->
<div id="design_edit_cad_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">图片宽度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_cad_width" value="80" placeholder="请输入按钮宽度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">图片高度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_cad_height" value="40" placeholder="请输入按钮高度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="ImportCad.editConfirm()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="ImportCad.closeEditWin()">取消</button>
        </div>
    </div>
</div>
