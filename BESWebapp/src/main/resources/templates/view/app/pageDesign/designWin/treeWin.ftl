<#--设备树弹窗-->
<div id="design_point_tree_win" style="display: none">
<#--设备类型树-->
    <#-- style="height: 300px!important;"-->
    <div  style="max-height:300px;overflow-y: scroll;">
        <div id="design_point_tree" class="Information_area ztree"></div>
    </div>

    <div class="design_win_btn_div">
        <button class="btn btn-md btn-primary" type="button" onclick="PointTree.confirmBtn()"><strong>确定</strong></button>
        <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal" onclick="PointTree.closeTreeWin()">取消</button>
    </div>
</div>


<#--多选设备树弹窗-->
<div id="design_checkbox_tree_win" style="display: none">
<#--设备类型树-->
<#-- style="height: 300px!important;"-->
    <div  style="max-height:300px;overflow-y: auto;">
        <div id="design_checkbox_tree" class="Information_area ztree"></div>
    </div>

    <div class="design_win_btn_div">
        <button class="btn btn-md btn-primary" type="button" onclick="CheckboxPointTree.confirmBtn()"><strong>确定</strong></button>
        <button class="btn btn-white m-l-sm" type="button" data-dismiss="modal" onclick="CheckboxPointTree.closeTreeWin()">取消</button>
    </div>
</div>