
<#--温控器关联模块弹窗-->
<div id="design_low_conditioner_relative" style="display: none" class="design_relative_win">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">模块名称</label>
            <div class="layui-input-block">
                <input type="hidden" id="design_low_conditioner_relative_id">
                <input type="text" id="design_low_conditioner_relative_name" placeholder="" readonly autocomplete="off" class="layui-input relativePointName">
                <button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="lowConditioner.choosePoint()">选择模块</button>
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="lowConditioner.relativeBtn()"><strong>关联</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="lowConditioner.closeRelativeWin()">取消</button>
        </div>
    </div>
</div>