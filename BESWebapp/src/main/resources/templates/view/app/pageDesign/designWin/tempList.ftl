
<#--列表关联温控器模块弹窗-->
<div id="design_temp_list_relative" style="display: none" class="design_relative_win">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">模块名称</label>
            <div class="layui-input-block">
                <input type="hidden" id="design_temp_list_relative_id">
                <input type="text" id="design_temp_list_relative_name" placeholder="" readonly autocomplete="off" class="layui-input relativePointName">
                <button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="TempListController.chooseModule()">选择模块</button>
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="TempListController.relativeTempList()"><strong>关联</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="TempListController.closeRelativeWin()">取消</button>
        </div>
    </div>
</div>