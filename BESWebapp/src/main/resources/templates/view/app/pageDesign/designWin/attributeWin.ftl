<#--属性页弹窗-->
<div id="attributeWin" class="design_toolbar_div" style="width: 400px;display:none;">
    <div class="modal-header bg-primary ui-draggable-handle">
        <h4 class="modal-title" style="margin-left:-5px">
        <#--<img src="${ctx}/static/pageDesign/icon/toolbar/toolbar.png">-->&nbsp;
            <span id="attrTitle" class="attrTitle">组件属性</span>
            <#--<span style="margin-left: 10px;margin-right: 10px;">|</span>-->
            <#--<span id="relativeTitle" class="attrTitle">关联配置</span>-->
            <#--<i class="layui-icon layui-icon-reduce-circle" style="float:right">&#xe616;</i>-->
            <#--<i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe60f;</i>-->
            <div class="attr_close" style="float:right;cursor:pointer;">
                <span class="glyphicon glyphicon-minus-sign"></span>
                <#--<span class="glyphicon glyphicon-minus"></span>-->
            </div>

        </h4>
    </div>
    <div class="modal-body">
        <#--组件属性-->
        <div id="attrDiv" style="height: 70%;overflow-x: hidden;overflow-y: auto;">
            <form  class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="moduleName">组件名称：</label>
                    <div class="col-sm-8">
                        <input type="text" id="moduleName" autocomplete="off" class="form-control" maxlength="20">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="moduleWidth">组件宽度：</label>
                    <div class="col-sm-8">
                        <input type="number" step="1" id="moduleWidth" autocomplete="off" class="layui-input" max="5000">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="moduleHeight">组件高度：</label>
                    <div class="col-sm-8">
                        <input type="number" id="moduleHeight" class="layui-input" autocomplete="off" max="5000">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="moduleLeft">距离左侧：</label>
                    <div class="col-sm-8">
                        <input type="number" id="moduleLeft" autocomplete="off" class="layui-input" max="5000">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="moduleTop">距离上方：</label>
                    <div class="col-sm-8">
                        <input type="number" id="moduleTop" autocomplete="off" class="layui-input" max="5000">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="moduleLayout">布局方式：</label>
                    <div class="col-sm-8">
                        <select id="moduleLayout" class="form-control">
                            <#--<option value="">默认</option>-->
                            <option value="">自由定位</option>
                            <option value="1">居中对齐</option>
                            <option value="2">左对齐</option>
                            <option value="3">右对齐</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="moduleFontSize">字体大小：</label>
                    <div class="col-sm-8">
                        <input type="number" id="moduleFontSize" min="8" max="50" class="layui-input" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="moduleBgColor">背景颜色：</label>
                    <div class="col-sm-8">
                        <#--<input type="text" id="moduleBgColor" class="form-control">-->
                        <div id="moduleBgColor"></div>
                    </div>
                </div>
            <#--<div class="form-group m-t-sm">-->
                    <#--<div class="col-sm-6 col-sm-push-4 display_flex">-->
                        <#--<button class="btn btn-md btn-primary " type="submit">-->
                            <#--<strong>确定</strong>-->
                        <#--</button>-->
                        <#--<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>-->
                    <#--</div>-->
                <#--</div>-->
            </form>
        </div>
        <#--&lt;#&ndash;关联配置&ndash;&gt;-->
        <#--<div id="relativeDiv" style="display: none;height: 70%;overflow-x: hidden;overflow-y: auto;">-->
            <#--<form  class="form-horizontal">-->
                <#--<div class="form-group">-->
                    <#--<label class="col-sm-3 control-label" for="moduleName">点名称：</label>-->
                    <#--<div class="col-sm-8">-->
                        <#--<input type="text" id="design_btn_relative_name" readonly autocomplete="off" class="form-control relativePointName" >-->
                        <#--<input type="hidden" id="design_btn_relative_id">-->
                        <#--<button id="selectPoint" class="btn btn-md btn-primary" type="button" onclick="AttributeWin.choosePoint()">选择点</button>-->
                    <#--</div>-->
                <#--</div>-->
            <#--<div class="form-group m-t-sm">-->
            <#--<div class="col-sm-6 col-sm-push-4 display_flex">-->
            <#--<button class="btn btn-md btn-primary " type="button" onclick="AttributeWin.relativeBtn()">-->
            <#--<strong>关联</strong>-->
            <#--</button>-->
            <#--<button type="button" class="btn btn-white m-l-sm" onclick="AttributeWin.clearRelativeWin()">取消</button>-->
            <#--</div>-->
            <#--</div>-->
            <#--</form>-->
        <#--</div>-->
    </div>
</div>

<#--属性页控制按钮隐藏-->
<#--<div class="design_attr_circle" title="切换属性页显示或隐藏">-->
<#--<img src="${ctx}/static/pageDesign/icon/toolbar/floatBall.png">-->
<#--</div>-->


