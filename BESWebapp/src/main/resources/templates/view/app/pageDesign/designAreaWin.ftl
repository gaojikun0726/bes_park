<#--新增/修改弹窗-->
<div id="infoWin" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">上级区域</label>
            <div class="layui-input-block">
                <input id="parentId" type="hidden">
                <input type="text" id="parentName" readonly placeholder="" autocomplete="off" class="layui-input" style = "width:76%;">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">区域名称</label>
            <div class="layui-input-block">
                <input id="areaIdAdd" type="hidden">
                <input type="text" id="areaName" placeholder="请输入区域名称" maxlength="32" autocomplete="off" class="layui-input" style = "width:76%;">
            </div>
        </div>

        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="DesignArea.save()"><strong>保存</strong></button>
            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal" onclick="DesignArea.closeInfoWin()">取消</button>
        </div>
    </div>
</div>

<#--复制弹窗-->

<div id="copyWin" style="display: none" class="designWin">
    <div class="layui-form">
        <input type="hidden" id="copyFromId">
        <input type="hidden" id="copyFromName">
        <div id="areaCopyTree"></div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="DesignArea.copy()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal" onclick="DesignArea.closeCopyWin()">取消</button>
        </div>
    </div>
</div>