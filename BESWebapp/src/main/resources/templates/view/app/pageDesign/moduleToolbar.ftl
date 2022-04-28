<#--工具栏-->
<div id  = "moduleToolDiv" class="design_toolbar_div" style="">
    <div class="modal-header bg-primary ui-draggable-handle">
        <h4 class="modal-title addIcon"><#--<img src="${ctx}/static/pageDesign/icon/toolbar/toolbar.png">-->&nbsp; 工具栏</h4>
    </div>
    <div class="modal-body">
        <div style="height: 70%;overflow-x: hidden;overflow-y: auto;">
            <a title="添加调试按钮" onclick="DebugBtn.openAddBtnWin()">  <img src="${ctx}/static/pageDesign/icon/toolbar/debug.png"></a>
            <a title="添加单通道按钮" onclick="ChannelBtn.openAddBtnWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/channel.png"></a>
            <a title="添加场景按钮" onclick="SceneBtn.openAddBtnWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/scene.png"></a>
            <a title="添加点位置按钮" onclick="PointBtn.openAddBtnWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/point.png"></a>
            <#--<a title="添加图片" id="design_add_img"><img src="${ctx}/static/pageDesign/icon/toolbar/img3.png"></a>
             <a title="添加背景图片" id="design_back_img_judge" onclick="BackImg.backImgExist()"><img src="${ctx}/static/pageDesign/icon/toolbar/back_img.png"></a>-->
            <a style="display: none" id="design_add_back_img"></a>
            <a title="添加背景区域" onclick="BackArea.openAddWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/back_area.png"></a>
            <a title="添加文本框" onclick="AddTextbox.addTextbox()"><img src="${ctx}/static/pageDesign/icon/toolbar/textbox2.png"></a>
            <a title="添加标签" onclick="AddLabel.openAddWin()"><img src="${ctx}/static/pageDesign/icon/toolbar/label2.png"></a>


        </div>


    </div>
</div>
