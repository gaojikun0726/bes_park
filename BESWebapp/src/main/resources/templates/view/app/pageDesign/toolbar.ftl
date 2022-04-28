<#--工具栏-->
<div class="design_toolbar_div">
    <div class="modal-header ui-draggable-handle">
        <h4 class="modal-title addIcon"><#--<img src="${ctx}/static/pageDesign/icon/toolbar/toolbar.png">-->&nbsp; 工具栏
            <#--属性页控制图标隐藏-->
            <#--<img title="切换属性页显示或隐藏" class="design_attr_circle" src="${ctx}/static/pageDesign/icon/toolbar/floatBall.png">-->
            <div class="layui-form switchDiv" title="显示名称">
                <#-- lay-text="ON|OFF" -->
                <input type="checkbox" name="switch" lay-skin="switch" title="显示名称" lay-filter="switch">
                    <#--显示名称-->
            </div>
        </h4>
    </div>
    <div class="modal-body">
        <div class="mainToolDiv">
            <a title="添加调试按钮" class="toolItem" onclick="DebugBtn.openAddBtnWin()">  <img src="${ctx}/static/pageDesign/icon/toolbar/debug.png">
                <span>调试</span></a>
            <a title="添加单通道按钮" class="toolItem" onclick="ChannelBtn.openAddBtnWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/channel.png"><span>单通道</span></a>
            <a title="添加场景按钮" class="toolItem" onclick="SceneBtn.openAddBtnWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/scene.png"><span>场景</span></a>
            <a title="添加点位置按钮" class="toolItem" onclick="PointBtn.openAddBtnWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/point.png"><span>点位置</span></a>
            <a title="添加文本框" class="toolItem" onclick="AddTextbox.addTextbox()"><img src="${ctx}/static/pageDesign/icon/toolbar/textbox2.png"><span>文本框</span></a>
            <a title="添加标签" class="toolItem" onclick="AddLabel.openAddWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/label2.png"><span>标签</span></a>

            <a title="添加图片" class="toolItem" id="design_add_img" onclick="AddImg.openRelativeWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/img3.png"><span>图片</span></a>
            <a title="温控器" class="toolItem"  onclick="ControllerTempLabel.addContrTempWin()"><img src="${ctx}/static/pageDesign/images/air_conditioner/low/conditioner8.png" width="30px"><span>温控器</span></a>
            <a title="电动窗帘" class="toolItem"  onclick="AddCurtain.addCurtain()"><img src="${ctx}/static/pageDesign/icon/legend/curtain_blue.png" width="30px"><span>电动窗帘</span></a>
            <a title="添加设计图" class="toolItem" id="design_import_cad"><img src="${ctx}/static/pageDesign/icon/toolbar/import_img.png"><span>设计图</span></a>
             <a title="添加背景图片" class="toolItem" id="design_back_img_judge" onclick="BackImg.backImgExist()"><img src="${ctx}/static/pageDesign/icon/toolbar/back_img.png"><span>背景图</span></a>
            <a style="display: none" id="design_add_back_img"></a>
            <a title="添加背景区域" class="toolItem" onclick="BackArea.openAddWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/back_area.png"><span>背景区域</span></a>
            <a title="添加表格" class="toolItem" id="addTableBtn" onclick="AddTable.openAddWin()" ><img src="${ctx}/static/pageDesign/icon/toolbar/table.png"><span>表格</span></a>
            <a title="添加流程图点位" class="toolItem" onclick="FlowLable.openAddBtnWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/flow.png"><span>流程图</span></a>
            <#--<a title="字体设置" onclick=""><img src="${ctx}/static/pageDesign/icon/toolbar/font_set4.png"></a>
            <a title="字体变大" onclick=""><img src="${ctx}/static/pageDesign/icon/toolbar/font_size_add.png"></a>
            <a title="字体变小" onclick=""><img src="${ctx}/static/pageDesign/icon/toolbar/font_size_down3.png"></a>-->

        <#--<a title="画线" onclick="DrawTool.drawLine()"><img src="${ctx}/static/pageDesign/icon/toolbar/draw_line.png"></a>-->
        <#--<a title="画圆" onclick="DrawTool.drawCircle()"><img src="${ctx}/static/pageDesign/icon/toolbar/draw_circle.png"></a>-->
        <#--<a title="画矩形" onclick="DrawTool.drawRect()"><img src="${ctx}/static/pageDesign/icon/toolbar/draw_rectangle2.png"></a>-->
            <a title="字体设置" class="toolItem"  id="design_text_setting" onclick="TextSettingLabel.openTextSettingWinNew()"><img src="${ctx}/static/pageDesign/icon/toolbar/textsetting.png"><span>字体</span></a>
            <a title="字体变大" class="toolItem"  id="design_text_zoomIn" onclick="EnlargeTextLabel.enlargeTextNew()"><img src="${ctx}/static/pageDesign/icon/toolbar/enlarge.png"><span>字体变大</span></a>
            <a title="字体变小" class="toolItem"  id="design_text_zoomOut" onclick="ReduceTextLabel.reduceTextNew()"><img src="${ctx}/static/pageDesign/icon/toolbar/reduce.png"><span>字体变小</span></a>


<#--
            <a title="添加能效按钮"  onclick="AddEnergyEfficiency.openEnergyEfficiencyWin()" ><img src="${ctx}/static/pageDesign/icon/toolbar/energyEfficiency.png"></a>
-->
            <a title="保存" class="toolItem" onclick="PageDesign.saveContent()"><img src="${ctx}/static/pageDesign/icon/toolbar/save2.png"><span>保存</span></a>
        </div>

        <div class="controlDiv">
            <div class="controlBtn" style="text-align: right;">
                <a id="showMore" title="更多" onclick="PageDesign.showMore()"><i class="layui-icon">&#xe61a;</i>  </a>
                <a id="hideMore" title="收起" onclick="PageDesign.hideMore()" style="display:none;"><i class="layui-icon">&#xe619;</i>  </a>
            </div>

            <div class="moreToolDiv" style="display:none;">
                <#--暂时隐藏-->
                <#--<a title="模板" class="toolItem" onclick="CopyModuleLabel.openCopyModuleWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/template.png" width="30px"><span>模板</span></a>-->
                <div class="layui-inline bgTool">
                    <a title="改变背景颜色" class="toolItem" id="design_bgColor_btn" ><img src="${ctx}/static/pageDesign/icon/toolbar/back_color2.png"><span>背景颜色</span></a>
                    <a title="改变背景颜色" class="toolItem" id="design_bgColor" ><img src="${ctx}/static/pageDesign/icon/toolbar/back_color2.png"></a>
                </div>
                <a title="画线" class="toolItem" id="lineDraw"><img src="${ctx}/static/pageDesign/icon/toolbar/draw_line.png"><span>画线</span></a>
                <a title="画圆" class="toolItem" id="circleDraw"><img src="${ctx}/static/pageDesign/icon/toolbar/draw_circle.png"><span>画圆</span></a>
                <a title="画矩形" class="toolItem" id="rectDraw"><img src="${ctx}/static/pageDesign/icon/toolbar/draw_rectangle2.png"><span>画矩形</span></a>
                <a title="放大" class="toolItem" id="zoomIn" onclick="enlargePage.showEnlargeWin()"><img src="${ctx}/static/pageDesign/icon/toolbar_more/zoom_in.png"><span>放大</span></a>
                <#--<a title="缩小" class="toolItem" id="zoomOut" onclick="enlargePage.showReduceWin()"><img src="${ctx}/static/pageDesign/icon/toolbar_more/zoom_out2.png"><span>缩小</span></a>-->
            </div>
        </div>


    </div>
</div>

<#--<div class="toolbar" id="design_toolbar" style="display: none">-->
<#--<input type="hidden" id="design_add_back_img">-->
<#--<div><button class="uploadText">添加背景图片</button><input id="design_upload_back_img" class="uploadBtn design_upload_back_img" type="file" style="width:60px" onchange="handleUploadBackImg()"></div>-->
<#--  <a title="添加背景图片" onclick="addBackgoundImage()"><img style="width:32px;height:32px" src="${ctx}/static/pageDesign/icon/bigicon/back_color.png"></a>
           <a title="添加背景区域" onclick="addDiv()"><img style="width:32px;height:32px" src="${ctx}/static/pageDesign/icon/bigicon/back_area.png"></a>-->


<#--<button type="button" id="full">改变背景颜色</button>-->

<#--<button type="button" onclick="saveContent()">保存</button>-->

<#--</div>-->
