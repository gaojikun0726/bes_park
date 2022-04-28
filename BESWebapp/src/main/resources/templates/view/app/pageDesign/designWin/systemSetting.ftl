<#--温控器设置弹窗-->

<div id="tempSettingWin" style="display: none" >
    <div id="settingsTable" class ="design_div">
                <img class ="design_image"  src = "${ctx}/static/pageDesign/icon/toolbar/temp_box.jpg" style = "width:300px;">
                <div id = "settingsChild"  class = "settingsChild">
                <div style="position:absolute;width:50px;text-align:left;top:43px;left: 94px;">
                    <img id = "moduleImg" class="moduleImg" src = "${ctx}/static/pageDesign/icon/toolbar/cool.png" style = "width:26px;">
                    <p id = "module"  class = "module" style="font-size:12px"></p>
                </div>
                <div style="position:absolute;width:50px;text-align:left;top:43px;left: 40px;">
                    <img id = "statusImg" class="statusImg" src = "${ctx}/static/pageDesign/icon/toolbar/communicate.png" style = "width:26px;">
                    <p id = "status"  class = "status" style="font-size:12px">通讯</p>
                </div>
                <div class = "sdDiv" style="position:absolute;width:50px;text-align:left;top:43px;left: 154px;">
                    <img  src = "${ctx}/static/pageDesign/icon/toolbar/sd.png" style = "width:26px;">
                    <p  style="font-size:12px">锁定</p>
                </div>
                <div style="position:absolute;width:50px;text-align:right;top:43px;right: 54px;">
                    <img id = "moduleTypeImg" class = "moduleTypeImg" src = "${ctx}/static/pageDesign/icon/toolbar/selffModule.png" style = "width:26px;">
                    <p id = "moduleType"  class = "moduleType" style="font-size:12px">自动</p>
                </div>
                <div style="position:absolute;width:65px;text-align:left;top:108px;left: 38px;">
                    <p id = "sdwd" class = "sdwd"  style="font-size:12px">设定温度</p>
                    <img id = "sdwdImg" class = "sdwdImg" src = "${ctx}/static/pageDesign/icon/toolbar/snwd.png" style = "width:21px;">
                </div>
                <div style="position:absolute;width:65px;text-align:left;top:154px;left: 46px;">
                    <p id = "sdTempInfo" class = "sdTempInfo"  style="font-size:39px">26</p>
                </div>
                    <div style="position:absolute;width:65px;text-align:left;top:153px;left: 130px;">
                        <p id = "tempIcon" class = "tempIcon"  style="font-size:16px">℃</p>
                    </div>
                <div style="position:absolute;width:65px;text-align:right;top:108px;right: 38px;">
                    <p id = "fs"  class = "fs" style="font-size:12px">节能</p>
                </div>

                <div style="position:absolute;width:65px;text-align:right;top:162px;right: 35px;">
                    <p id = "sjType" class = "sjType"   style="font-size:12px">当前时间</p>
                </div>
                <div style="position:absolute;width:65px;text-align:right;top:179px;right: 48px;">
                    <p id = "sjInfo" class = "sjInfo"   style="font-size:26px"></p>
                </div>
                <div style="position:absolute;width:65px;text-align:right;top:135px;right: 108px;">
                    <img id = "fsImg" class = "fsImg" src = "${ctx}/static/pageDesign/icon/toolbar/fs.png" style = "width:20px;">
                </div>
                <div style="position:absolute;width:65px;text-align:right;top:135px;right: 43px;">
                    <img id = "qdImg" class = "qdImg" src = "${ctx}/static/pageDesign/icon/toolbar/qd.png" style = "width:52px;">
                </div>
                <div style="position:absolute;width:50px;text-align:right;bottom:48px;left: 9px;">
                    <img id = "menu"  class = "menu" src = "${ctx}/static/pageDesign/icon/toolbar/menu.png" style = "width:21px;">
                </div>
                <div style="position:absolute;width:50px;text-align:right;bottom:48px;left: 64px;">
                    <img id = "moduleSel" class = "moduleSel" src = "${ctx}/static/pageDesign/icon/toolbar/moduleSel.png" style = "width:21px;">
                </div>
                <div style="position:absolute;width:50px;text-align:right;bottom:48px;left: 115px;">
                    <img id = "kgj"  class = "kgj" src = "${ctx}/static/pageDesign/icon/toolbar/on-off.png" style = "width:21px;">
                </div>
                <div style="position:absolute;width:50px;text-align:right;bottom:48px;left: 165px;"  class = "highImgDiv">
                    <img id = "highImg" class = "highImg" src = "${ctx}/static/pageDesign/icon/toolbar/high.png" style = "width:21px;">
                </div>
                <div style="position:absolute;width:50px;text-align:right;bottom:48px;left:215px;">
                    <img id = "lowImg" class = "lowImg" src = "${ctx}/static/pageDesign/icon/toolbar/low.png" style = "width:21px;">
                </div>
         </div>
    </div>
</div>
<style>
   .layui-layer-close2{
       z-index: 999999999;
   }
</style>


