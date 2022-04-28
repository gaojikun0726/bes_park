
<#--修改图片弹窗-->
<div id="design_editImgWin" style="display: none" class="designWin">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">图片宽度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_img_width" value="80" placeholder="请输入按钮宽度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">图片高度</label>
            <div class="layui-input-block">
                <input type="text" id="design_edit_img_height" value="40" placeholder="请输入按钮高度" autocomplete="off" class="layui-input">
            </div>
        </div>
        <#--保持纵横比功能隐藏-->
        <#--<div class="layui-form-item">-->
            <#--<label class="layui-form-label"></label>-->
            <#--<div class="layui-input-block">-->
                <#--<input type="checkbox" id="design_edit_img_ratio" title="保持纵横比" lay-skin="primary" checked value="1">-->
            <#--</div>-->
        <#--</div>-->
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddImg.editConfirm()"><strong>确定</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddImg.closeEditWin()">取消</button>
        </div>
    </div>
</div>

<#--图片关联点弹窗-->
<div id="design_img_relative" style="display: none" class="design_relative_win">
    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">点名称</label>
            <div class="layui-input-block">
                <input type="hidden" id="design_img_relative_id">
                <input type="text" id="design_img_relative_name" readonly placeholder="" autocomplete="off" class="layui-input relativePointName">
                <button class="btn btn-md btn-primary choosePointBtn" type="button" onclick="AddImg.choosePoint()">选择点</button>
            </div>
        </div>
        <div class="layui-form-item" id = "relativeSettingInfo">
            <label class="layui-form-label" >关联配置</label>
            <#--<table id="design_img_relative_table">-->
                <#--<thead>-->
                <#--<tr>-->
                    <#--<th>点状态</th>-->
                    <#--<th>关系</th>-->
                    <#--<th width="100px">图片</th>-->
                <#--</tr>-->
                <#--</thead>-->
                <#--<tbody>-->

                <#--</tbody>-->
            <#--</table>-->
            <div style="float:right;">
                <label class="layui-form-label">配置类型</label>
                <div class="layui-input-block">
                    <input name="imgConfigType" type="radio" value="1" checked title="状态量" lay-filter="imgConfigType">
                    <input name="imgConfigType" type="radio" value="2" title="区间量" lay-filter="imgConfigType">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="configDiv">
                <div class="stateDiv">
                    <input type="text" value="显示文本" class="form-control text" readonly>
                    <input type="text" value="数值" class="form-control num" readonly>
                <input type="text" value="图片" class="form-control bgColor" readonly>
                    <button class="fa fa-plus btn btn-default plusBtn" type="button" onclick="AddImg.addStateRow()"></button>
                    <button class="fa fa-minus btn btn-default plusBtn" type="button" onclick="AddImg.deleteStateRow()"></button>
                </div>
                <div class="intervalDiv" style="display: none;">
                    <input type="text" value="显示文本" class="form-control text" readonly>
                    <input type="text" value="区间范围" class="form-control num" readonly>
                <input type="text" value="图片" class="form-control bgColor" readonly>
                    <button class="fa fa-plus btn btn-default plusBtn" type="button" onclick="AddImg.addIntervalRow()"></button>
                    <button class="fa fa-minus btn btn-default plusBtn" type="button" onclick="AddImg.deleteIntervalRow()"></button>
                </div>
                <div class="dataDiv">
                    <#--<div class="configRow">-->
                        <#--<input type="text" class="form-control text">-->
                        <#--<input type="text" class="form-control num">-->
                        <#--<div class="bgDiv">-->
                            <#--<div class="uploadDiv">-->
                                <#--<img class="uploadIcon" src="${ctx}/static/pageDesign/icon/menu/upload_img6.png">-->
                                <#--<input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                </div>
                <div class="intervalDataDiv" style="display: none;">
                    <#--<div class="configRow">-->
                        <#--<input type="text" class="form-control text">-->
                        <#--<div class="numDiv">-->
                            <#--<input type="text" class="min">-->
                            <#--<span>-</span>-->
                            <#--<input type="text" class="max">-->
                            <#--<span class="unit"></span>-->
                        <#--</div>-->
                        <#--<div class="bgDiv">-->
                            <#--<div class="uploadDiv">-->
                                <#--<img class="uploadIcon" src="${ctx}/static/pageDesign/icon/menu/upload_img6.png">-->
                                <#--<input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">-->
                            <#--</div>-->
                        <#--</div>-->
                    <#--</div>-->
                </div>
            </div>
        </div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="AddImg.relativeBtnNew()"><strong>关联</strong></button>
            <button class="btn btn-white m-l-sm" type="button" onclick="AddImg.closeRelativeWin()">取消</button>
        </div>
    </div>
</div>

<table id="design_img_default_content" style="display: none;">
    <tbody>
    <tr>
        <td>
            <select class="img_relative_select img_point_state">
                <option value="255" selected="selected">闭合</option>
                <option value="0">断开</option>
            </select>
        </td>
        <td>等于</td>
        <td>
            <div class="uploadDiv">
                <img class="uploadIcon" src="${ctx}/static/pageDesign/icon/menu/upload_img6.png">
                <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <select class="img_relative_select img_point_state">
                <option value="255">闭合</option>
                <option value="0" selected="selected">断开</option>
            </select>
        </td>
        <td>等于</td>
        <td>
            <div class="uploadDiv">
                <img class="uploadIcon" src="${ctx}/static/pageDesign/icon/menu/upload_img6.png">
                <input class="uploadBtn" type="file" title="添加图片" onchange="AddImg.uploadRelativeImg(this)">
            </div>
        </td>
    </tr>
    </tbody>
</table>
<#--背景图片弹窗-->
<div id="design_back_img_win" style="display: none" class="designWin">
    <div class="layui-form">
        <div>背景图片已存在，您是要？</div>
        <div class="design_win_btn_div">
            <button class="btn btn-md btn-primary" type="button" onclick="BackImg.cancel()"><strong>保留</strong></button>
            <button class="btn btn-md btn-primary" type="button" onclick="BackImg.delete()"><strong>删除</strong></button>
            <button class="btn btn-md btn-primary" type="button" onclick="BackImg.change()"><strong>更换</strong></button>
            <#--<button class="btn btn-white m-l-sm" type="button" onclick="AddImg.closeEditWin()">取消</button>-->
        </div>
    </div>
</div>