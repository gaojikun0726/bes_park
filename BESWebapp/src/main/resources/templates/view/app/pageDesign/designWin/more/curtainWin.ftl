<#--&lt;#&ndash;新增窗帘弹窗&ndash;&gt;-->
<#--<div id="design_add_curtain_win" style="display: none" class="designWin">-->
    <#--<div class="layui-form">-->
        <#--<div class="layui-form-item">-->
            <#--<label class="layui-form-label">宽度</label>-->
            <#--<div class="layui-input-block">-->
                <#--<input type="text" id="design_add_curtain_width" value="30" placeholder="请输入宽度" autocomplete="off" class="layui-input">-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="layui-form-item">-->
            <#--<label class="layui-form-label">高度</label>-->
            <#--<div class="layui-input-block">-->
                <#--<input type="text" id="design_add_curtain_height" value="30" placeholder="请输入高度" autocomplete="off" class="layui-input">-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="design_win_btn_div">-->
            <#--<button class="btn btn-md btn-primary" type="button" onclick="AddCurtain.addConfirm()"><strong>确定</strong></button>-->
            <#--<button class="btn btn-white m-l-sm" type="button" onclick="AddCurtain.closeAddWin()">取消</button>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->


<#--按钮关联点弹窗-->
<div id="design_curtain_relative" style="display: none" class="design_relative_win">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">点名称</label>
            <div class="layui-input-block">
                <input type="hidden" id="design_curtain_relative_id">
                <input type="text" id="design_curtain_relative_name" placeholder="" readonly autocomplete="off" class="layui-input relativePointName">
                <button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="AddCurtain.choosePoint()">选择点</button>
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddCurtain.relativeBtn()"><strong>关联</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddCurtain.closeRelativeWin()">取消</button>
        </div>
    </div>
</div>
