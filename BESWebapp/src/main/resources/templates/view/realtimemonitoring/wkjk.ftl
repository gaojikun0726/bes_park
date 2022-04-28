<link href="${ctx}/static/css/wkjk.css" rel="stylesheet">
<!-- 左侧设备树start -->
<div id="wkjk_div" class="leftarea information_left">
    <div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
                                        aria-hidden="true"></i>&nbsp;请选择监控位置>>>
		</span>
    </div>
    <div id="wkjz_sbdy_tree" class="Information_area"></div>
</div>
<!-- 左侧设备树end -->
<!-- 右侧监控界面Start -->
<div class="information_right">
    <div class="information_size">
        <div class="information-model">
			<span class="Subtitle"> <i class="fa fa-th-list"
                                       aria-hidden="true"></i>&nbsp;监控界面>>>
			</span>
        </div>
        <div id="wkjk_page" style="height:96%;width:100%"></div>

    </div>
</div>
<!-- 右侧监控界面End -->


<!-- 模式 模态框start -->
<div class="modal fade" id="modal-form-wkjk" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;模式设定</h4>
            </div>

            <div class="modal-body">
                <form role="form" id="debugms" name="debugms" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">开关机</label>
                        <div class="col-sm-8">
                            <#--<input type="text" id="ms_f_kgj" name="ms_f_kgj" class="form-control" >-->
                            <select id="ms_f_kgj" class="wk_select"  onchange="dataAnalysis_wkjk.setTemperaturePointSelect(this)">
                                <option value="255">开机</option>
                                <option value="0">关机</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">模式</label>
                        <div class="col-sm-8">
                            <#--<input type="text" id="ms_f_ms" name="ms_f_ms" class="form-control" >-->
                            <select id="ms_f_ms" class="wk_select" onchange="dataAnalysis_wkjk.setTemperaturePointSelect(this)">
                                <option value="255">制冷</option>
                                <option value="0">制热</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">风速</label>
                        <div class="col-sm-8">
                            <#--<input type="text" id="ms_f_fs" name="ms_f_fs" class="form-control" >-->
                            <select id="ms_f_fs" class="wk_select" onchange="dataAnalysis_wkjk.setTemperaturePointSelect(this)">
                                <#--<option value="0">自动</option>-->
                                <option value="1">低速</option>
                                <option value="2">中速</option>
                                <option value="3">高速</option>
                                <option value="4">自动</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">锁定</label>
                        <div class="col-sm-8">
                            <select id="ms_f_sd" class="wk_select" onchange="dataAnalysis_wkjk.setTemperaturePointSelect(this)">
                                <option value="0">正常</option>
                                <option value="1">锁定温度</option>
                                <option value="2">锁定按键</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设定温度</label>
                        <div class="col-sm-8">
                            <div style="float: left">
                                <input type="number" class="wk_input" id="ms_f_sdwd" name="ms_f_sdwd" class="form-control" >
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">室内温度</label>
                        <div class="col-sm-8">
                            <input type="text" class="wk_input" id="ms_f_snwd" name="ms_f_snwd" class="form-control">
                        </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <#--<button class="btn btn-md btn-primary" type="button" onclick="dataAnalysis_wkjk.setWkjkData('ms','')"><strong>执行</strong></button>-->
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                        <div style="padding-left: 2vh;">
                                <span class="input-group-btn" >
                                    <button type="button" id="sdwd_btn" class="btn btn-primary" onclick="dataAnalysis_wkjk.setTemperaturePoint(this)">设置</button>
                                </span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 模式 模态框end -->
<script type="text/javascript">
    ;
    var dataAnalysis_wkjk = (function($, window, document, undefined) {
        var _ctx = '${ctx}';
        var clock = '';//定时任务返回id
        var selected_treeview = null;//treeview上选中的节点
        var tabId = "";//当前tab页的ID
        var nodePage = "";//节点对应的页面

        var editId = "";
        clock = window.setInterval("dataAnalysis_wkjk.refresh()",4000);
        var pageIsHide = false; // 记录页面是否是隐藏状态
        var ddcNames = "";
        var runState = true;
        var ddcList = [];
        var checkedDevices = "";

        $(function() {
            //配置楼宇tree
            var treeList = new Array();
            var treechildList1 = new Array();
            treechildList1.push(
                {nodeTreeId:"w100",ddc: "WKQYKZ1_A8_1F", pid:"w1",text:"QYKZ1_A8_2F",id:"WKQYKZ1_A8_1F0213,WKQYKZ1_A8_1F0215,WKQYKZ1_A8_1F0214,WKQYKZ1_A8_1F0212,WKQYKZ1_A8_1F0216,WKQYKZ1_A8_1F0248,WKQYKZ1_A8_1F0202,WKQYKZ1_A8_1F0211,WKQYKZ1_A8_1F0210,WKQYKZ1_A8_1F0209,WKQYKZ1_A8_1F0208,WKQYKZ1_A8_1F0246,WKQYKZ1_A8_1F0205,WKQYKZ1_A8_1F0205,WKQYKZ1_A8_1F0203,WKQYKZ1_A8_1F0247,WKQYKZ1_A8_1F0206",level:"1",leaf:true,nodePage:"wkjk1_08_02",},
                {nodeTreeId:"w101",ddc: "WKQYKZ1_A1_1F", pid:"w1",text:"QYKZ1_A1_1F",id:"WKQYKZ1_A1_1F0202,WKQYKZ1_A1_1F0203,WKQYKZ1_A1_1F0204,WKQYKZ1_A1_1F0205,WKQYKZ1_A1_1F0206,WKQYKZ1_A1_1F0207,WKQYKZ1_A1_1F0208,WKQYKZ1_A1_1F0209,WKQYKZ1_A1_1F0110,WKQYKZ1_A1_1F0111,WKQYKZ1_A1_1F0112,WKQYKZ1_A1_1F0113,WKQYKZ1_A1_1F0114,WKQYKZ1_A1_1F0115,WKQYKZ1_A1_1F0116,WKQYKZ1_A1_1F0117,WKQYKZ1_A1_1F0118",level:"1",leaf:true,nodePage:"wkjk1_01_01",},
                {nodeTreeId:"w102",ddc: "WKQYKZ1_A2_1F", pid:"w1",text:"QYKZ1_A2_1F",id:"WKQYKZ1_A2_1F0111,WKQYKZ1_A2_1F0112,WKQYKZ1_A2_1F0113,WKQYKZ1_A2_1F0114,WKQYKZ1_A2_1F0115,WKQYKZ1_A2_1F0116,WKQYKZ1_A2_1F0117,WKQYKZ1_A2_1F0118,WKQYKZ1_A2_1F0119,WKQYKZ1_A2_1F0121,WKQYKZ1_A2_1F0122,WKQYKZ1_A2_1F0224,WKQYKZ1_A2_1F0225,WKQYKZ1_A2_1F0226,WKQYKZ1_A2_1F0227,WKQYKZ1_A2_1F0228,WKQYKZ1_A2_1F0229,WKQYKZ1_A2_1F0230,WKQYKZ1_A2_1F0131,WKQYKZ1_A2_1F0132,WKQYKZ1_A2_1F0333",level:"1",leaf:true,nodePage:"wkjk1_02_01",},
                {nodeTreeId:"w103",ddc: "WKQYKZ1_A3_1F", pid:"w1",text:"QYKZ1_A3_1F",id:"WKQYKZ1_A3_1F0102,WKQYKZ1_A3_1F0103,WKQYKZ1_A3_1F0104,WKQYKZ1_A3_1F0105,WKQYKZ1_A3_1F0106,WKQYKZ1_A3_1F0107,WKQYKZ1_A3_1F0208,WKQYKZ1_A3_1F0209,WKQYKZ1_A3_1F0210,WKQYKZ1_A3_1F0211,WKQYKZ1_A3_1F0212,WKQYKZ1_A3_1F0213,WKQYKZ1_A3_1F0214,WKQYKZ1_A3_1F0115,WKQYKZ1_A3_1F0116",level:"1",leaf:true,nodePage:"wkjk1_03_01",},
                {nodeTreeId:"w104",ddc: "WKQYKZ1_A4_1F", pid:"w1",text:"QYKZ1_A4_1F",id:"WKQYKZ1_A4_1F0111,WKQYKZ1_A4_1F0112,WKQYKZ1_A4_1F0113,WKQYKZ1_A4_1F0114,WKQYKZ1_A4_1F0115,WKQYKZ1_A4_1F0116,WKQYKZ1_A4_1F0117,WKQYKZ1_A4_1F0118,WKQYKZ1_A4_1F0204,WKQYKZ1_A4_1F0205,WKQYKZ1_A4_1F0206,WKQYKZ1_A4_1F0207,WKQYKZ1_A4_1F0208,WKQYKZ1_A4_1F0209,WKQYKZ1_A4_1F0210,WKQYKZ1_A4_1F0203,WKQYKZ1_A4_1F0202",level:"1",leaf:true,nodePage:"wkjk1_04_01",},
                {nodeTreeId:"w105",ddc: "WKQYKZ1_A7_1F", pid:"w1",text:"QYKZ1_A7_1F",id:"WKQYKZ1_A7_1F0209,WKQYKZ1_A7_1F0208,WKQYKZ1_A7_1F0207,WKQYKZ1_A7_1F0206,WKQYKZ1_A7_1F0205,WKQYKZ1_A7_1F0204,WKQYKZ1_A7_1F0203,WKQYKZ1_A7_1F0202,WKQYKZ1_A7_1F0116,WKQYKZ1_A7_1F0115,WKQYKZ1_A7_1F0114,WKQYKZ1_A7_1F0113,WKQYKZ1_A7_1F0112,WKQYKZ1_A7_1F0110,WKQYKZ1_A7_1F0111,WKQYKZ1_A7_1F0117,WKQYKZ1_A7_1F0118",level:"1",leaf:true,nodePage:"wkjk1_07_01",},
                {nodeTreeId:"w106",ddc: "WKQYKZ1_A8_1F", pid:"w1",text:"QYKZ1_A8_1F",id:"WKQYKZ1_A8_1F0144,WKQYKZ1_A8_1F0145,WKQYKZ1_A8_1F0136,WKQYKZ1_A8_1F0135,WKQYKZ1_A8_1F0134,WKQYKZ1_A8_1F0132,WKQYKZ1_A8_1F0128,WKQYKZ1_A8_1F0129,WKQYKZ1_A8_1F0130,WKQYKZ1_A8_1F0131,WKQYKZ1_A8_1F0137,WKQYKZ1_A8_1F0138,WKQYKZ1_A8_1F0139,WKQYKZ1_A8_1F0140,WKQYKZ1_A8_1F0141,WKQYKZ1_A8_1F0142,WKQYKZ1_A8_1F0143,WKQYKZ1_A8_1F0127",level:"1",leaf:true,nodePage:"wkjk1_08_01",},
                {nodeTreeId:"w107",ddc: "WKQYKZ1_A9_1F", pid:"w1",text:"QYKZ1_A9_1F",id:"WKQYKZ1_A9_1F0107,WKQYKZ1_A9_1F0106,WKQYKZ1_A9_1F0105,WKQYKZ1_A9_1F0104,WKQYKZ1_A9_1F0103,WKQYKZ1_A9_1F0102,WKQYKZ1_A9_1F0117,WKQYKZ1_A9_1F0118,WKQYKZ1_A9_1F0214,WKQYKZ1_A9_1F0213,WKQYKZ1_A9_1F0212,WKQYKZ1_A9_1F0211,WKQYKZ1_A9_1F0210,WKQYKZ1_A9_1F0208,WKQYKZ1_A9_1F0209,WKQYKZ1_A9_1F0216,WKQYKZ1_A9_1F0215",level:"1",leaf:true,nodePage:"wkjk1_09_01",},
                {nodeTreeId:"w108",ddc: "WKQYKZ1_A1_2F", pid:"w1",text:"QYKZ1_A1_2F",id:"WKQYKZ1_A1_2F0102,WKQYKZ1_A1_2F0103,WKQYKZ1_A1_2F0104,WKQYKZ1_A1_2F0105,WKQYKZ1_A1_2F0106,WKQYKZ1_A1_2F0107,WKQYKZ1_A1_2F0108,WKQYKZ1_A1_2F0209,WKQYKZ1_A1_2F0210,WKQYKZ1_A1_2F0211,WKQYKZ1_A1_2F0212,WKQYKZ1_A1_2F0213,WKQYKZ1_A1_2F0214,WKQYKZ1_A1_2F0215",level:"1",leaf:true,nodePage:"wkjk1_01_02",},
                {nodeTreeId:"w109",ddc: "WKQYKZ1_A2_2F", pid:"w1",text:"QYKZ1_A2_2F",id:"WKQYKZ1_A2_2F0109,WKQYKZ1_A2_2F0110,WKQYKZ1_A2_2F0111,WKQYKZ1_A2_2F0112,WKQYKZ1_A2_2F0113,WKQYKZ1_A2_2F0114,WKQYKZ1_A2_2F0115,WKQYKZ1_A2_2F0116,WKQYKZ1_A2_2F0117,WKQYKZ1_A2_2F0118,WKQYKZ1_A2_2F0119,WKQYKZ1_A2_2F0120,WKQYKZ1_A2_2F0121,WKQYKZ1_A2_2F0122,WKQYKZ1_A2_2F0323,WKQYKZ1_A2_2F0324,WKQYKZ1_A2_2F0325,WKQYKZ1_A2_2F0326,WKQYKZ1_A2_2F0327,WKQYKZ1_A2_2F0328,WKQYKZ1_A2_2F0329,WKQYKZ1_A2_2F0330,WKQYKZ1_A2_2F0231,WKQYKZ1_A2_2F0132,WKQYKZ1_A2_2F0133,WKQYKZ1_A2_2F0134",level:"1",leaf:true,nodePage:"wkjk1_02_02",},
                {nodeTreeId:"w110",ddc: "WKQYKZ1_A3_2F", pid:"w1",text:"QYKZ1_A3_2F",id:"WKQYKZ1_A3_2F0102,WKQYKZ1_A3_2F0103,WKQYKZ1_A3_2F0104,WKQYKZ1_A3_2F0105,WKQYKZ1_A3_2F0106,WKQYKZ1_A3_2F0107,WKQYKZ1_A3_2F0108,WKQYKZ1_A3_2F0209,WKQYKZ1_A3_2F0210,WKQYKZ1_A3_2F0211,WKQYKZ1_A3_2F0212,WKQYKZ1_A3_2F0213,WKQYKZ1_A3_2F0214,WKQYKZ1_A3_2F0215",level:"1",leaf:true,nodePage:"wkjk1_03_02",},
                {nodeTreeId:"w111",ddc: "WKQYKZ1_A4_2F", pid:"w1",text:"QYKZ1_A4_2F",id:"WKQYKZ1_A4_2F0207,WKQYKZ1_A4_2F0206,WKQYKZ1_A4_2F0205,WKQYKZ1_A4_2F0204,WKQYKZ1_A4_2F0203,WKQYKZ1_A4_2F0202,WKQYKZ1_A4_2F0114,WKQYKZ1_A4_2F0113,WKQYKZ1_A4_2F0112,WKQYKZ1_A4_2F0111,WKQYKZ1_A4_2F0110,WKQYKZ1_A4_2F0109,WKQYKZ1_A4_2F0108",level:"1",leaf:true,nodePage:"wkjk1_04_02",},
                {nodeTreeId:"w112",ddc: "WKQYKZ1_A5_2F", pid:"w1",text:"QYKZ1_A5_2F",id:"WKQYKZ1_A5_2F0202,WKQYKZ1_A5_2F0203,WKQYKZ1_A5_2F0204,WKQYKZ1_A5_2F0205,WKQYKZ1_A5_2F0206,WKQYKZ1_A5_2F0207,WKQYKZ1_A5_2F0208,WKQYKZ1_A5_2F0209,WKQYKZ1_A5_2F0211,WKQYKZ1_A5_2F0212,WKQYKZ1_A5_2F0213,WKQYKZ1_A5_2F0214,WKQYKZ1_A5_2F0215,WKQYKZ1_A5_2F0226,WKQYKZ1_A5_2F0225,WKQYKZ1_A5_2F0123,WKQYKZ1_A5_2F0122,WKQYKZ1_A5_2F0121,WKQYKZ1_A5_2F0120,WKQYKZ1_A5_2F0119,WKQYKZ1_A5_2F0118,WKQYKZ1_A5_2F0117,WKQYKZ1_A5_2F0116,WKQYKZ1_A5_2F0224",level:"1",leaf:true,nodePage:"wkjk1_05_02",},
                {nodeTreeId:"w113",ddc: "WKQYKZ1_A6_2F", pid:"w1",text:"QYKZ1_A6_2F",id:"WKQYKZ1_A6_2F0102,WKQYKZ1_A6_2F0103,WKQYKZ1_A6_2F0104,WKQYKZ1_A6_2F0105,WKQYKZ1_A6_2F0106,WKQYKZ1_A6_2F0107,WKQYKZ1_A6_2F0108,WKQYKZ1_A6_2F0213,WKQYKZ1_A6_2F0212,WKQYKZ1_A6_2F0211,WKQYKZ1_A6_2F0210,WKQYKZ1_A6_2F0209,WKQYKZ1_A6_2F0215,WKQYKZ1_A6_2F0214,",level:"1",leaf:true,nodePage:"wkjk1_06_02",},
                {nodeTreeId:"w114",ddc: "WKQYKZ1_A7_2F", pid:"w1",text:"QYKZ1_A7_2F",id:"WKQYKZ1_A7_2F0207,WKQYKZ1_A7_2F0206,WKQYKZ1_A7_2F0205,WKQYKZ1_A7_2F0204,WKQYKZ1_A7_2F0203,WKQYKZ1_A7_2F0202,WKQYKZ1_A7_2F0202,WKQYKZ1_A7_2F0110,WKQYKZ1_A7_2F0111,WKQYKZ1_A7_2F0112,WKQYKZ1_A7_2F0113,WKQYKZ1_A7_2F0114",level:"1",leaf:true,nodePage:"wkjk1_07_02",},
                {nodeTreeId:"w115",ddc: "WKQYKZ1_A9_2F", pid:"w1",text:"QYKZ1_A9_2F",id:"WKQYKZ1_A9_2F0102,WKQYKZ1_A9_2F0104,WKQYKZ1_A9_2F0105,WKQYKZ1_A9_2F0106,WKQYKZ1_A9_2F0107,WKQYKZ1_A9_2F0108,WKQYKZ1_A9_2F0213,WKQYKZ1_A9_2F0212,WKQYKZ1_A9_2F0210,WKQYKZ1_A9_2F0209,WKQYKZ1_A9_2F0214,WKQYKZ1_A9_2F0215",level:"1",leaf:true,nodePage:"wkjk1_09_02",},
                {nodeTreeId:"w116",ddc: "WKQYKZ1_A5_1F", pid:"w1",text:"QYKZ1_A5_1F",id:"WKQYKZ1_A5_1F0213,WKQYKZ1_A5_1F0212,WKQYKZ1_A5_1F0211,WKQYKZ1_A5_1F0210,WKQYKZ1_A5_1F0209,WKQYKZ1_A5_1F0208,WKQYKZ1_A5_1F0207,WKQYKZ1_A5_1F0206,WKQYKZ1_A5_1F0205,WKQYKZ1_A5_1F0204,WKQYKZ1_A5_1F0203,WKQYKZ1_A5_1F0202,WKQYKZ1_A5_1F0214,WKQYKZ1_A5_1F0215,WKQYKZ1_A5_1F0323,WKQYKZ1_A5_1F0322,WKQYKZ1_A5_1F0321,WKQYKZ1_A5_1F0320,WKQYKZ1_A5_1F0319,WKQYKZ1_A5_1F0318,WKQYKZ1_A5_1F0317,WKQYKZ1_A5_1F0316",level:"1",leaf:true,nodePage:"wkjk1_05_01",},
                {nodeTreeId:"w117",ddc: "WKQYKZ1_A6_1F", pid:"w1",text:"QYKZ1_A6_1F",id:"WKQYKZ1_A6_1F0109,WKQYKZ1_A6_1F0110,WKQYKZ1_A6_1F0111,WKQYKZ1_A6_1F0112,WKQYKZ1_A6_1F0113,WKQYKZ1_A6_1F0114,WKQYKZ1_A6_1F0202,WKQYKZ1_A6_1F0203,WKQYKZ1_A6_1F0204,WKQYKZ1_A6_1F0205,WKQYKZ1_A6_1F0206,WKQYKZ1_A6_1F0208,WKQYKZ1_A6_1F0207",level:"1",leaf:true,nodePage:"wkjk1_06_01",}


            );
            var treechildList2 = new Array();
            treechildList2.push(
                {nodeTreeId:"w2B1",ddc: "WKQYKZ2_B1F", pid:"w2",text:"QYKZ2_B1_1F",id:"WKQYKZ2_B1F0102,WKQYKZ2_B1F0103,WKQYKZ2_B1F0104,WKQYKZ2_B1F0105,WKQYKZ2_B1F0106,WKQYKZ2_B1F0107,WKQYKZ2_B1F0108,WKQYKZ2_B1F0109,WKQYKZ2_B1F0110,WKQYKZ2_B1F0111,WKQYKZ2_B1F0112,WKQYKZ2_B1F0113,WKQYKZ2_B1F0114,WKQYKZ2_B1F0115,WKQYKZ2_B1F0116,WKQYKZ2_B1F0117,WKQYKZ2_B1F0118,WKQYKZ2_B1F0119,WKQYKZ2_B1F0120,WKQYKZ2_B1F0121,WKQYKZ2_B1F0122,WKQYKZ2_B1F0123,WKQYKZ2_B1F0124,WKQYKZ2_B1F0125,WKQYKZ2_B1F0126,WKQYKZ2_B1F0127,WKQYKZ2_B1F0128,WKQYKZ2_B1F0129,WKQYKZ2_B1F0130,WKQYKZ2_B1F0131",level:"1",leaf:true,nodePage:"wkjk2_B1_01",},
                {nodeTreeId:"w201",ddc: "WKQYKZ2_1F", pid:"w2",text:"QYKZ2_1F_1F",id:"WKQYKZ2_1F0202,WKQYKZ2_1F0203,WKQYKZ2_1F0204,WKQYKZ2_1F0205,WKQYKZ2_1F0206,WKQYKZ2_1F0207,WKQYKZ2_1F0208,WKQYKZ2_1F0209,WKQYKZ2_1F0210,WKQYKZ2_1F0211,WKQYKZ2_1F0212,WKQYKZ2_1F0213,WKQYKZ2_1F0214,WKQYKZ2_1F0215,WKQYKZ2_1F0216,WKQYKZ2_1F0217,WKQYKZ2_1F0218,WKQYKZ2_1F0219,WKQYKZ2_1F0222,WKQYKZ2_1F0221,WKQYKZ2_1F0220",level:"1",leaf:true,nodePage:"wkjk2_01_01",},
                {nodeTreeId:"w202",ddc: "WKQYKZ2_2F", pid:"w2",text:"QYKZ2_2F_2F",id:"WKQYKZ2_2F0102,WKQYKZ2_2F0103,WKQYKZ2_2F0104,WKQYKZ2_2F0105,WKQYKZ2_2F0106,WKQYKZ2_2F0107,WKQYKZ2_2F0108,WKQYKZ2_2F0109",level:"1",leaf:true,nodePage:"wkjk2_02_02",},
                {nodeTreeId:"w203",ddc: "WKQYKZ2_3F", pid:"w2",text:"QYKZ2_3F_3F",id:"WKQYKZ2_3F0102,WKQYKZ2_3F0103,WKQYKZ2_3F0104,WKQYKZ2_3F0105,WKQYKZ2_3F0106,WKQYKZ2_3F0107,WKQYKZ2_3F0108,WKQYKZ2_3F0109",level:"1",leaf:true,nodePage:"wkjk2_03_03",},
                {nodeTreeId:"w204",ddc: "WKQYKZ2_4F_4F", pid:"w2",text:"QYKZ2_4F_4F",id:"WKQYKZ2_4F_4F0102,WKQYKZ2_4F_4F0103,WKQYKZ2_4F_4F0104,WKQYKZ2_4F_4F0105,WKQYKZ2_4F_4F0106,WKQYKZ2_4F_4F0107,WKQYKZ2_4F_4F0108,WKQYKZ2_4F_4F0109",level:"1",leaf:true,nodePage:"wkjk2_04_04",},
                {nodeTreeId:"w205",ddc: "WKQYKZ2_5F_5F", pid:"w2",text:"QYKZ2_5F_5F",id:"WKQYKZ2_5F_5F0102,WKQYKZ2_5F_5F0103,WKQYKZ2_5F_5F0104,WKQYKZ2_5F_5F0105,WKQYKZ2_5F_5F0106,WKQYKZ2_5F_5F0107,WKQYKZ2_5F_5F0108,WKQYKZ2_5F_5F0109",level:"1",leaf:true,nodePage:"wkjk2_05_05",}//数据问题  还需修改
            );
            var treechildList3 = new Array();
            treechildList3.push(
                {nodeTreeId:"w301",ddc: "WKQYKZ3_C1_1F", pid:"w3",text:"QYKZ3_C1_1F",id:"WKQYKZ3_C1_1F0202,WKQYKZ3_C1_1F0203,WKQYKZ3_C1_1F0204,WKQYKZ3_C1_1F0120,WKQYKZ3_C1_1F0105,WKQYKZ3_C1_1F0106,WKQYKZ3_C1_1F0107,WKQYKZ3_C1_1F0208,WKQYKZ3_C1_1F0209,WKQYKZ3_C1_1F0210,WKQYKZ3_C1_1F0211,WKQYKZ3_C1_1F0212,WKQYKZ3_C1_1F0213,WKQYKZ3_C1_1F0214,WKQYKZ3_C1_1F0215,WKQYKZ3_C1_1F0216,WKQYKZ3_C1_1F0217,WKQYKZ3_C1_1F0218",level:"1",leaf:true,nodePage:"wkjk3_c1_01",},
                {nodeTreeId:"w302",ddc: "WKQYKZ3_C3_2F", pid:"w3",text:"QYKZ3_C3_2F",id:"WKQYKZ3_C3_2F0202,WKQYKZ3_C3_2F0203,WKQYKZ3_C3_2F0204,WKQYKZ3_C3_2F0205,WKQYKZ3_C3_2F0206,WKQYKZ3_C3_2F0207,WKQYKZ3_C3_2F0208,WKQYKZ3_C3_2F0209,WKQYKZ3_C3_2F0210,WKQYKZ3_C3_2F0211,WKQYKZ3_C3_2F0212,WKQYKZ3_C3_2F0113,WKQYKZ3_C3_2F0114,WKQYKZ3_C3_2F0115,WKQYKZ3_C3_2F0116,WKQYKZ3_C3_2F0117,WKQYKZ3_C3_2F0118",level:"1",leaf:true,nodePage:"wkjk3_c3_02",},
                {nodeTreeId:"w303",ddc: "WKQYKZ3_C5_3F", pid:"w3",text:"QYKZ3_C5_3F",id:"WKQYKZ3_C5_3F0117,WKQYKZ3_C5_3F0118,WKQYKZ3_C5_3F0102,WKQYKZ3_C5_3F0103,WKQYKZ3_C5_3F0104,WKQYKZ3_C5_3F0105,WKQYKZ3_C5_3F0106,WKQYKZ3_C5_3F0210,WKQYKZ3_C5_3F0208,WKQYKZ3_C5_3F0209,WKQYKZ3_C5_3F0211,WKQYKZ3_C5_3F0214,WKQYKZ3_C5_3F0216,WKQYKZ3_C5_3F0215,WKQYKZ3_C5_3F0213,WKQYKZ3_C5_3F0212",level:"1",leaf:true,nodePage:"wkjk3_c5_03",},
                {nodeTreeId:"w304",ddc: "WKQYKZ3_C7_4F", pid:"w3",text:"QYKZ3_C7_4F",id:"WKQYKZ3_C7_4F0202,WKQYKZ3_C7_4F0203,WKQYKZ3_C7_4F0204,WKQYKZ3_C7_4F0205,WKQYKZ3_C7_4F0206,WKQYKZ3_C7_4F0207,WKQYKZ3_C7_4F0208,WKQYKZ3_C7_4F0209,WKQYKZ3_C7_4F0112,WKQYKZ3_C7_4F0110,WKQYKZ3_C7_4F0111,WKQYKZ3_C7_4F0113,WKQYKZ3_C7_4F0116,WKQYKZ3_C7_4F0117,WKQYKZ3_C7_4F0118,WKQYKZ3_C7_4F0115,WKQYKZ3_C7_4F0114",level:"1",leaf:true,nodePage:"wkjk3_c7_04",}
            );
            var treechildList4 = new Array();
            treechildList4.push(
                {nodeTreeId:"w401",ddc: "WKQYKZ4_1F_1", pid:"w4",text:"QYKZ4_1F_1",id:"WKQYKZ4_1F_10102,WKQYKZ4_1F_10103,WKQYKZ4_1F_10104,WKQYKZ4_1F_10105,WKQYKZ4_1F_10106,WKQYKZ4_1F_10107,WKQYKZ4_1F_10108,WKQYKZ4_1F_10109,WKQYKZ4_1F_10110,WKQYKZ4_1F_10111,WKQYKZ4_1F_10112,WKQYKZ4_1F_10113,WKQYKZ4_1F_10114,WKQYKZ4_1F_10115,WKQYKZ4_1F_10116,WKQYKZ4_1F_10217,WKQYKZ4_1F_10218,WKQYKZ4_1F_10219,WKQYKZ4_1F_10220,WKQYKZ4_1F_10221,WKQYKZ4_1F_10222",level:"1",leaf:true,nodePage:"wkjk4_01_01",},
                {nodeTreeId:"w402",ddc: "WKQYKZ4_2F_1", pid:"w4",text:"QYKZ4_2F_1",id:"WKQYKZ4_2F_10133,WKQYKZ4_2F_10134,WKQYKZ4_2F_10135,WKQYKZ4_2F_10136,WKQYKZ4_2F_10137,WKQYKZ4_2F_10154,WKQYKZ4_2F_10152,WKQYKZ4_2F_10153,WKQYKZ4_2F_10148,WKQYKZ4_2F_10149,WKQYKZ4_2F_10150,WKQYKZ4_2F_10151,WKQYKZ4_2F_10144,WKQYKZ4_2F_10145,WKQYKZ4_2F_10146,WKQYKZ4_2F_10147,WKQYKZ4_2F_10141,WKQYKZ4_2F_10242,WKQYKZ4_2F_10243,WKQYKZ4_2F_10138,WKQYKZ4_2F_10139,WKQYKZ4_2F_10140,WKQYKZ4_2F_10231,WKQYKZ4_2F_10232,WKQYKZ4_2F_10230,WKQYKZ4_2F_10229,WKQYKZ4_2F_10228",level:"1",leaf:true,nodePage:"wkjk4_02_01",},
                {nodeTreeId:"w403",ddc: "WKQYKZ4_2F_2", pid:"w4",text:"QYKZ4_2F_2",id:"WKQYKZ4_2F_20316,WKQYKZ4_2F_20317,WKQYKZ4_2F_20318,WKQYKZ4_2F_20319,WKQYKZ4_2F_20320,WKQYKZ4_2F_20321,WKQYKZ4_2F_20322,WKQYKZ4_2F_20323,WKQYKZ4_2F_20324,WKQYKZ4_2F_20325,WKQYKZ4_2F_20326,WKQYKZ4_2F_20327,WKQYKZ4_2F_20355,WKQYKZ4_2F_20315,WKQYKZ4_2F_20114,WKQYKZ4_2F_20113,WKQYKZ4_2F_20112,WKQYKZ4_2F_20111,WKQYKZ4_2F_20110,WKQYKZ4_2F_20109,WKQYKZ4_2F_20108,WKQYKZ4_2F_20107,WKQYKZ4_2F_20106,WKQYKZ4_2F_20205,WKQYKZ4_2F_20204,WKQYKZ4_2F_20203,WKQYKZ4_2F_20202",level:"1",leaf:true,nodePage:"wkjk4_02_02",}
            );
            var treechildList5 = new Array();
            treechildList5.push(
                {nodeTreeId:"w501",ddc: "WKQYKZK_E1_1F", pid:"w5",text:"QYKZ5_E1_1F",id:"WKQYKZK_E1_1F0133,WKQYKZK_E1_1F0117,WKQYKZK_E1_1F0216,WKQYKZK_E1_1F0215,WKQYKZK_E1_1F0218,WKQYKZK_E1_1F0219,WKQYKZK_E1_1F0220,WKQYKZK_E1_1F0221,WKQYKZK_E1_1F0222,WKQYKZK_E1_1F0223,WKQYKZK_E1_1F0224,WKQYKZK_E1_1F0225,WKQYKZK_E1_1F0226,WKQYKZK_E1_1F0227,WKQYKZK_E1_1F0228,WKQYKZK_E1_1F0229,WKQYKZK_E1_1F0230,WKQYKZK_E1_1F0221,WKQYKZK_E1_1F0232,WKQYKZK_E1_1F0308,WKQYKZK_E1_1F0314,WKQYKZK_E1_1F0313,WKQYKZK_E1_1F0312,WKQYKZK_E1_1F0311,WKQYKZK_E1_1F0310,WKQYKZK_E1_1F0309,WKQYKZK_E1_1F0349,WKQYKZK_E1_1F0307,WKQYKZK_E1_1F0306,WKQYKZK_E1_1F0305,WKQYKZK_E1_1F0304,WKQYKZK_E1_1F0348,WKQYKZK_E1_1F0303,WKQYKZK_E1_1F0302,WKQYKZ4_2F_10144,WKQYKZ4_2F_10143,WKQYKZ4_2F_10142,WKQYKZ4_2F_10141,WKQYKZ4_2F_10140,WKQYKZ4_2F_10139,WKQYKZ4_2F_10134,WKQYKZ4_2F_10135,WKQYKZ4_2F_10136,WKQYKZ4_2F_10138,WKQYKZ4_2F_10137",level:"1",leaf:true,nodePage:"wkjk5_e1_01",},
                // TODO 数据录入错误（需更改设备树和页面id）
                {nodeTreeId:"w502",ddc: "WKQYKKZ_E2_1F", pid:"w5",text:"QYKZ5_E2_1F",id:"WKQYKKZ_E2_1F0202,WKQYKZ5_E2_1F0344,WKQYKZ5_E2_1F0345,WKQYKZ5_E2_1F0346,WKQYKZ5_E2_1F0347,WKQYKZ5_E2_1F0348,WKQYKKZ_E2_1F0204,WKQYKKZ_E2_1F0205,WKQYKKZ_E2_1F0206,WKQYKKZ_E2_1F0203,WKQYKZ5_E2_1F0359,WKQYKZ5_E2_1F0358,WKQYKZ5_E2_1F0357,WKQYKZ5_E2_1F0356,WKQYKZ5_E2_1F0355,WKQYKZ5_E2_1F0354,WKQYKZ5_E2_1F0353,WKQYKZ5_E2_1F0352,WKQYKZ5_E2_1F0351,WKQYKZ5_E2_1F0350,WKQYKKZ_E2_1F0125,WKQYKKZ_E2_1F0133,WKQYKKZ_E2_1F0124,WKQYKKZ_E2_1F0126,WKQYKKZ_E2_1F0127,WKQYKKZ_E2_1F0128,WKQYKKZ_E2_1F0129,WKQYKKZ_E2_1F0130,WKQYKKZ_E2_1F0131,WKQYKKZ_E2_1F0132,WKQYKKZ_E2_1F0134,WKQYKKZ_E2_1F0135,WKQYKZ5_E2_1F0349,WKQYKKZ_E2_1F0136,WKQYKKZ_E2_1F0137,WKQYKKZ_E2_1F0138,WKQYKKZ_E2_1F0139,WKQYKKZ_E2_1F0140,WKQYKKZ_E2_1F0141,WKQYKKZ_E2_1F0142,WKQYKKZ_E2_1F0143,WKQYKKZ_E2_1F0129,WKQYKKZ_E2_1F0128,WKQYKKZ_E2_1F0127,WKQYKKZ_E2_1F0125,WKQYKKZ_E2_1F0139,WKQYKKZ_E2_1F0223,WKQYKKZ_E2_1F0222,WKQYKKZ_E2_1F0221,WKQYKKZ_E2_1F0220,WKQYKKZ_E2_1F0219,WKQYKKZ_E2_1F0217,WKQYKKZ_E2_1F0216,WKQYKKZ_E2_1F0218,WKQYKKZ_E2_1F0219,WKQYKKZ_E2_1F0220,WKQYKKZ_E2_1F0221,WKQYKKZ_E2_1F0222,WKQYKKZ_E2_1F0223,WKQYKKZ_E2_1F0215,WKQYKKZ_E2_1F0214,WKQYKKZ_E2_1F0213,WKQYKKZ_E2_1F0212,WKQYKKZ_E2_1F0211,WKQYKKZ_E2_1F0210,WKQYKKZ_E2_1F0209,WKQYKKZ_E2_1F0207,WKQYKKZ_E2_1F0208",level:"1",leaf:true,nodePage:"wkjk5_e2_01",},
                {nodeTreeId:"w503",ddc: "WKQYKZ5_E3_1F", pid:"w5",text:"QYKZ5_E3_1F",id:"WKQYKZ5_E3_1F0219,WKQYKZ5_E3_1F0220,WKQYKZ5_E3_1F0221,WKQYKZ5_E3_1F0222,WKQYKZ5_E3_1F0223,WKQYKZ5_E3_1F0224,WKQYKZ5_E3_1F0125,WKQYKZ5_E3_1F0127,WKQYKZ5_E3_1F0126,WKQYKZ5_E3_1F0128,WKQYKZ5_E3_1F0129,WKQYKZ5_E3_1F0118,WKQYKZ5_E3_1F0117,WKQYKZ5_E3_1F0116,WKQYKZ5_E3_1F0115,WKQYKZ5_E3_1F0114,WKQYKZ5_E3_1F0113,WKQYKZ5_E3_1F0112,WKQYKZ5_E3_1F0111,WKQYKZ5_E3_1F0110,WKQYKZ5_E3_1F0109,WKQYKZ5_E3_1F0108,WKQYKZ5_E3_1F0107,WKQYKZ5_E3_1F0106,WKQYKZ5_E3_1F0105,WKQYKZ5_E3_1F0104,WKQYKZ5_E3_1F0103,WKQYKZ5_E3_1F0102",level:"1",leaf:true,nodePage:"wkjk5_e3_01",},
                {nodeTreeId:"w504",ddc: "WKQYKZ5_E5_2F", pid:"w5",text:"QYKZ5_E5_2F",id:"WKQYKZ5_E5_2F0208,WKQYKZ5_E5_2F0211,WKQYKZ5_E5_2F0212,WKQYKZ5_E5_2F0206,WKQYKZ5_E5_2F0207,WKQYKZ5_E5_2F0209,WKQYKZ5_E5_2F0210,WKQYKZ5_E5_2F0213,WKQYKZ5_E5_2F0214,WKQYKZ5_E5_2F0215,WKQYKZ5_E5_2F0220,WKQYKZ5_E5_2F0123,WKQYKZ5_E5_2F0124,WKQYKZ5_E5_2F0125,WKQYKZ5_E5_2F0126,WKQYKZ5_E5_2F0127,WKQYKZ5_E5_2F0216,WKQYKZ5_E5_2F0217,WKQYKZ5_E5_2F0218,WKQYKZ5_E5_2F0219,WKQYKZ5_E5_2F0238,WKQYKZ5_E5_2F0237,WKQYKZ5_E5_2F0236,WKQYKZ5_E5_2F0221,WKQYKZ5_E5_2F0222,WKQYKZ5_E5_2F0128,WKQYKZ5_E5_2F0129,WKQYKZ5_E5_2F0130,WKQYKZ5_E5_2F0131,WKQYKZ5_E5_2F0132,WKQYKZ5_E5_2F0133,WKQYKZ5_E5_2F0134,WKQYKZ5_E5_2F0202,WKQYKZ5_E5_2F0203,WKQYKZ5_E5_2F0204,WKQYKZ5_E5_2F0239,WKQYKZ5_E5_2F0205,WKQYKZ5_E5_2F0235",level:"1",leaf:true,nodePage:"wkjk5_e5_02",},
                {nodeTreeId:"w505",ddc: "WKQYKZ5_E8_3F", pid:"w5",text:"QYKZ5_E8_3F",id:"WKQYKZ5_E8_3F0113,WKQYKZ5_E8_3F0110,WKQYKZ5_E8_3F0114,WKQYKZ5_E8_3F0115,WKQYKZ5_E8_3F0112,WKQYKZ5_E8_3F0111,WKQYKZ5_E8_3F0109,WKQYKZ5_E8_3F0108,WKQYKZ5_E8_3F0107,WKQYKZ5_E8_3F0106,WKQYKZ5_E8_3F0105,WKQYKZ5_E8_3F0104,WKQYKZ5_E8_3F0103,WKQYKZ5_E8_3F0102,WKQYKZ5_E8_3F0136,WKQYKZ5_E8_3F0137,WKQYKZ5_E8_3F0138,WKQYKZ5_E8_3F0135,WKQYKZ5_E8_3F0232,WKQYKZ5_E8_3F0231,WKQYKZ5_E8_3F0230,WKQYKZ5_E8_3F0229,WKQYKZ5_E8_3F0228,WKQYKZ5_E8_3F0139,WKQYKZ5_E8_3F0227,WKQYKZ5_E8_3F0226,WKQYKZ5_E8_3F0225,WKQYKZ5_E8_3F0224,WKQYKZ5_E8_3F0223,WKQYKZ5_E8_3F0222,WKQYKZ5_E8_3F0221,WKQYKZ5_E8_3F0120,WKQYKZ5_E8_3F0119,WKQYKZ5_E8_3F0118,WKQYKZ5_E8_3F0117,WKQYKZ5_E8_3F0116,WKQYKZ5_E8_3F0134,WKQYKZ5_E8_3F0133",level:"1",leaf:true,nodePage:"wkjk5_e8_03",},
                {nodeTreeId:"w506",ddc: "WKQYKZ5_E11_4F", pid:"w5",text:"QYKZ5_E11_4F",id:"WKQYKZ5_E11_4F0209,WKQYKZ5_E11_4F0207,WKQYKZ5_E11_4F0208,WKQYKZ5_E11_4F0210,WKQYKZ5_E11_4F0211,WKQYKZ5_E11_4F0212,WKQYKZ5_E11_4F0213,WKQYKZ5_E11_4F0214,WKQYKZ5_E11_4F0215,WKQYKZ5_E11_4F0216,WKQYKZ5_E11_4F0217,WKQYKZ5_E11_4F0218,WKQYKZ5_E11_4F0219,WKQYKZ5_E11_4F0220,WKQYKZ5_E11_4F0221,WKQYKZ5_E11_4F0222,WKQYKZ5_E11_4F0223,WKQYKZ5_E11_4F0124,WKQYKZ5_E11_4F0125,WKQYKZ5_E11_4F0126,WKQYKZ5_E11_4F0127,WKQYKZ5_E11_4F0128,WKQYKZ5_E11_4F0129,WKQYKZ5_E11_4F0130,WKQYKZ5_E11_4F0131,WKQYKZ5_E11_4F0134,WKQYKZ5_E11_4F0133,WKQYKZ5_E11_4F0132,WKQYKZ5_E11_4F0135,WKQYKZ5_E11_4F0202,WKQYKZ5_E11_4F0203,WKQYKZ5_E11_4F0204,WKQYKZ5_E11_4F0205,WKQYKZ5_E11_4F0206,WKQYKZ5_E11_4F0236,WKQYKZ5_E11_4F0237",level:"1",leaf:true,nodePage:"wkjk5_e11_04",}
            );
            treeList.push(
                {nodeTreeId:"w1", pid:"",text:"1#温控监控",id:"w1",rootId:"w1",leaf:false,nodes:treechildList1},
                {nodeTreeId:"w2", pid:"",text:"2#温控监控",id:"w2",rootId:"w2",leaf:false,nodes:treechildList2},
                {nodeTreeId:"w3", pid:"",text:"3#温控监控",id:"w3",rootId:"w3",leaf:false,nodes:treechildList3},
                {nodeTreeId:"w4", pid:"",text:"4#温控监控",id:"w4",rootId:"w4",leaf:false,nodes:treechildList4},
                {nodeTreeId:"w5", pid:"",text:"5#温控监控",id:"w5",rootId:"w5",leaf:false,nodes:treechildList5}
            );

            $('#wkjz_sbdy_tree').treeview({
                data : treeList, // 数据源
                highlightSelected : true, //是否高亮选中
                levels : 2,
                enableLinks : true,//必须在节点属性给出href属性
                wrapNodeText : true,
                showImage : false,
                color : "#4a4747",
                onNodeSelected : function(event, nodeData) {//节点选中事件
                    ddcNames = nodeData.ddc;
                    nodePage = nodeData.nodePage;
                    ids = nodeData.id;
                    //加载节点对应的监控界面
                    loadJkPage(nodePage, nodeData.id);
                    selected_treeview = $('#wkjz_sbdy_tree').treeview('getSelected');
                }
            });
            if(selected_treeview == null){
                var firstNode = $("#wkjz_sbdy_tree").treeview('findNodes',[treeList[0].nodes[0].id,'id']);
                $("#wkjz_sbdy_tree").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
                ddcNames = treeList[0].nodes[0].ddc;
                //getRvscanData();
            }
        });

        function loadJkPage(nodePage, id){
            var variableUrl = nodePage;
            $.issp_ajax({//加载树节点信息
                isShowLoader : false,
                type : "post",
                url : _ctx + "/view/realtimemonitoring/BESWkjk/" + variableUrl,
                success : function(returnObject) {
                    $("#wkjk_page").html(returnObject);
                    dataAnalysis_wkjk.refreshIcon(id);
                },
                error : function(returnObject) {
                    swal(returnObject.msg, "", "error");
                }
            });
        }


        //温控监控模态框
        $("#modal-form-wkjk").on('show.bs.modal', function(event) {
            //垂直居中显示
            $(this).css('display', 'block');
            var modalHeight=($(window).height()/2) - ($('#modal-form-wkjk .modal-dialog').height()/2);
            $(this).find('.modal-dialog').css('margin-top', modalHeight);
            //模态拖动
            $(this).draggable({
                handle: ".modal-header"     	// 只能点击头部拖动
            });
            $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的

        });

        //关闭编辑模态框清空表单值
        $("#modal-form-wkjk").on('hidden.bs.modal', function (event) {
            $(this).find("input").val("");
            setWkjkInfoFormValidator.resetForm();
            // dataAnalysis_wkjk.openEdit();
            // dataAnalysis_wkjk.openButton();
            // dataAnalysis_wkjk.openTemperature();
            //ms_f_kgj  ms_f_ms ms_f_fs  ms_f_sd   ms_f_sdwd  sdwd_btn
            // $("#ms_f_ms").attr("disabled", "disabled");
            $("#ms_f_fs").removeAttr("disabled");
            $("#ms_f_sd").removeAttr("disabled");
            $("#ms_f_kgj").removeAttr("disabled");
            $("#ms_f_ms").removeAttr("disabled");
            $("#ms_f_sdwd").removeAttr("disabled");
            $("#sdwd_btn").removeAttr("disabled");

            $('#ms_f_kgj').val("255");
            $('#ms_f_ms').val("255");
            $('#ms_f_fs').val("1");
            $('#ms_f_sd').val("0");
            $('#ms_f_snwd').val("");
            $('#ms_f_sdwd').val("");

        });

        var setWkjkInfoFormValidator = $("#modal-form-wkjk").validate({
            submitHandler: function (form) {
                //setWkjkData();
            }
        });

      /*  var timer = setInterval(function () {

            var el = $('#wkjk_page');

            if(el.length <= 0){
                pageIsHide = true;
                clearInterval(timer);
                return;
            }

            var current = el.is(":hidden");

            if(current === false && pageIsHide === true && runState === false){
                pageIsHide = current;
                getRvscanData();
            }

            pageIsHide = current;

        }, 2000);

        function getRvscanData () {

            runState = true;

            if(pageIsHide){
                runState = false;
                return;
            }

            if(!ddcNames || ddcNames.trim() === ''){
                setTimeout(function () {
                    getRvscanData();
                }, 10000)
                return;
            }

            $.ajax({//加载树节点信息
                type: "post",
                url: _ctx + "/view/basedatamanage/eqmanage/getRvscanData",
                data: {
                    sysName: ddcNames
                },
                success: function (result) {

                    if(result.status === '1'){
                        getRvscanData();

                        var data = result.data;

                        if(Array.isArray(data)){
                            ddcList = data;
                            if(!$('#modal-form-wkjk').is(':hidden')){
                                formAssig();
                            }
                        }
                    }else {
                        setTimeout(function () {
                            getRvscanData();
                        }, 10000)
                    }
                }
            })

        }*/

        function formAssig() {

            if(ddcList.length <= 0 || checkedDevices === ''){
                return;
            }

            for(var i =0; i < ddcList.length; i++){

                var item = ddcList[i];

                if(item.f_sys_name === checkedDevices + '00'){
                    $('#ms_f_kgj').val(item.f_init_val);
                }else if(item.f_sys_name === checkedDevices + '01'){
                    $('#ms_f_ms').val(item.f_init_val);
                }else if(item.f_sys_name === checkedDevices + '02'){
                    $('#ms_f_fs').val(item.f_init_val.substring(0,1));
                }else if(item.f_sys_name === checkedDevices + '03'){
                    $('#ms_f_sd').val(item.f_init_val.substring(0,1));
                }else if(item.f_sys_name === checkedDevices + '04'){
                    $('#ms_f_snwd').val(item.f_init_val);
                }else if(item.f_sys_name === checkedDevices + '05'){
                    $('#ms_f_sdwd').val(item.f_init_val);
                }
            }
        }
        
        return {

            //加载模态框
            loadWkjkInfo:function(obj){
                editId =obj.id;
                checkedDevices = editId;
                formAssig();
                $("#ms_f_kgj").attr("ids",editId+"00");// 开关机赋id
                $("#ms_f_ms").attr("ids",editId+"01");// 模式赋id
                $("#ms_f_fs").attr("ids",editId+"02");// 风速赋id
                $("#ms_f_sd").attr("ids",editId+"03");// 锁定赋id
                $("#ms_f_sdwd").attr("ids",editId+"05");// 设定温度赋id
                $("#sdwd_btn").attr("ids",editId+"05");// 设定温度赋id
                $("#modal-form-wkjk").modal('show');
               /* $.issp_ajax({//加载树节点信息
                    type : "post",
                    data:{
                        sysName: editId
                    },
                    url : _ctx + "/view/basedatamanage/eqmanage/getWkjkPointInfoBySysName",
                    success : function(returnObject) {
                        if(returnObject.status == '1'){
                            var updateInfo = returnObject.data;
                            /!*dataAnalysis_wkjk.stopEdit();*!/
                            if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){
                                for (var i = 0; i < updateInfo.length; i++) {
                                    if(updateInfo[i].f_nick_name == "KGJ"){//开关机
                                        $('#ms_f_kgj').val(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_nick_name == "MS"){//模式
                                        $('#ms_f_ms').val(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_nick_name == "FS"){//风速
                                        $('#ms_f_fs').val(updateInfo[i].f_init_val.substring(0,1));
                                    }else if(updateInfo[i].f_nick_name == "SD"){//锁定
                                        $('#ms_f_sd').val(updateInfo[i].f_init_val.substring(0,1));
                                    }else if(updateInfo[i].f_nick_name == "SNWD"){//室内温度
                                        $('#ms_f_snwd').val(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_nick_name == "SDWD"){//设定温度
                                        $('#ms_f_sdwd').val(updateInfo[i].f_init_val);
                                    }
                                }
                            }
                        }
                    },
                    error : function(returnObject) {
                        swal(returnObject.msg, "", "error");
                    },
                });*/
            },
            refreshIcon: function(id){
                $.issp_ajax({//加载树节点信息
                    type : "post",
                    data:{
                        sysName: id
                    },
                    url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
                    success : function(returnObject) {
                        // if(returnObject.status == '1'){
                        // 	var updateInfo = returnObject.data;
                        ddcList = returnObject.list || [];


                    },
                    error : function(returnObject) {
                        swal(returnObject.msg, "", "error");
                    },
                });
            },
            // 温控监控select事件
            setTemperaturePointSelect: function(obj){
                var f_init_val = $('#'+obj.id).val();// 值
                var f_work_mode =  "1";// 手动1  自动0
                //var f_sys_name = obj.attributes[2].nodeValue;
                var f_sys_name = $("#"+obj.id).attr("ids");
                $.ajax({
                    url : _ctx + "/api/debugPointInfo",
                    type : "post",
                    contentType : "application/json; charset=utf-8",
                    dataType : "json",
                    data : JSON.stringify({
                        f_sys_name : f_sys_name,
                        f_work_mode : f_work_mode,
                        f_init_val : f_init_val,
                        f_node_attribution : "1",
                    }),
                    success : function(result) {
                        if(result.status == 1){
                            swal("操作成功", "", "success");
                            if(f_sys_name.indexOf('00')){
                                if(f_init_val == 0){//关机
                                    dataAnalysis_wkjk.stopEdit();
                                }else if(f_init_val == 255){//开机
                                    dataAnalysis_wkjk.openEdit();
                                    dataAnalysis_wkjk.openButton();
                                    dataAnalysis_wkjk.openTemperature();
                                    dataAnalysis_wkjk.operaButtonLfunction(f_sys_name);
                                }
                            }
                            dataAnalysis_wkjk.operaButtonLfunction(f_sys_name);
                        }else{
                            swal(result.msg, "", "error");
                        }
                    },
                    error : function(result) {
                        swal("操作失败", "", "error");
                    }
                });
            },
            //判断锁定状态下按钮是否可以操作
            operaButtonLfunction(f_sys_name){
                if(f_sys_name.indexOf('03')){
                    dataAnalysis_wkjk.openEdit();
                    dataAnalysis_wkjk.openButton();
                    dataAnalysis_wkjk.openTemperature();
                    if($('#ms_f_sd').val() == 1){
                        dataAnalysis_wkjk.lockTemperature();
                    }else if($('#ms_f_sd').val() == 2){
                        dataAnalysis_wkjk.lockButton();
                    }
                }
            },
            // 温控监控设置事件
            setTemperaturePoint: function(obj){
                //var ids = $("#ms_f_sdwd").attr("ids");
                var f_init_val = $("#ms_f_sdwd").val();
                if(f_init_val>35||f_init_val<18){
                    swal("温度输入区间错误(18-35)", "", "error");
                    $("#ms_f_sdwd").val("");
                    return false;
                }
                var f_work_mode =  "1";
                var f_sys_name = $("#"+obj.id).attr("ids");
                $.ajax({
                    url : _ctx + "/api/debugPointInfo",
                    type : "post",
                    contentType : "application/json; charset=utf-8",
                    dataType : "json",
                    data : JSON.stringify({
                        f_sys_name : f_sys_name,
                        f_work_mode : f_work_mode,
                        f_init_val : f_init_val,
                        f_node_attribution : "1",
                    }),
                    success : function(result) {
                        if(result.status == 1){
                            swal("操作成功", "", "success");
                        }else{
                            swal(result.msg, "", "error");
                        }
                    },
                    error : function(result) {
                        swal("操作失败", "", "error");
                    }
                });
            },
            //锁定温度
            /*lockTemperature: function(){
                //ms_f_kgj  ms_f_ms ms_f_fs  ms_f_sd   ms_f_sdwd  sdwd_btn
                $("#ms_f_sdwd").attr("disabled", "disabled");
                $("#sdwd_btn").attr("disabled", "disabled");
            },*/
            /*openTemperature: function(){
                $("#ms_f_sdwd").removeAttr("disabled");
                $("#sdwd_btn").removeAttr("disabled");
            },*/
            //锁定温度
            /*lockButton: function(){
                //ms_f_kgj  ms_f_ms ms_f_fs  ms_f_sd   ms_f_sdwd  sdwd_btn
                // $("#ms_f_ms").attr("disabled", "disabled");
                $("#ms_f_fs").attr("disabled", "disabled");
                $("#ms_f_sd").attr("disabled", "disabled");
                $("#ms_f_kgj").attr("disabled", "disabled");
                $("#ms_f_ms").attr("disabled", "disabled");
            },*/
            /*openButton: function(){
                $("#ms_f_fs").removeAttr("disabled");
                $("#ms_f_sd").removeAttr("disabled");
                $("#ms_f_kgj").removeAttr("disabled");
                $("#ms_f_ms").removeAttr("disabled");
            },*/
            //关机所有按钮不可操作
            /*stopEdit: function(){
                $("#ms_f_ms").attr("disabled", "disabled");
                $("#ms_f_fs").attr("disabled", "disabled");
                $("#ms_f_sd").attr("disabled", "disabled");
                $("#ms_f_sdwd").attr("disabled", "disabled");
                $("#sdwd_btn").attr("disabled", "disabled");
            },*/
            //开机
            openEdit: function(){
                $("#ms_f_ms").removeAttr("disabled");
                $("#ms_f_fs").removeAttr("disabled");
                $("#ms_f_sd").removeAttr("disabled");
                $("#ms_f_sdwd").removeAttr("disabled");
                $("#sdwd_btn").removeAttr("disabled");
            },
            //刷新开关状态
            refresh : function() {

                var el = $('#wkjk_page');
                if(el.length <= 0){
                    clearInterval(clock);
                    return;
                }
                $.issp_ajax({//加载树节点信息
                    type : "post",
                    url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJK",
                    isShowLoader : false,
                    success : function(returnObject) {
                        if(returnObject.hasOwnProperty('list')){
                            var updateInfo = returnObject.list;
                            if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){
                                ddcList = returnObject.list || [];
                                console.log(ddcList)
                                /*        for (var i = 0; i < updateInfo.length; i++) {
                                    console.log(JSON.stringify(updateInfo[i]));
                                    if(updateInfo[i].F_NICK_NAME == "KGJ"){//开关机
                                        $('#ms_f_kgj').val(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_nick_name == "MS"){//模式
                                        $('#ms_f_ms').val(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_nick_name == "FS"){//风速
                                        $('#ms_f_fs').val(updateInfo[i].f_init_val.substring(0,1));
                                    }else if(updateInfo[i].f_nick_name == "SD"){//锁定
                                        $('#ms_f_sd').val(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_nick_name == "SNWD"){//室内温度
                                        $('#ms_f_snwd').val(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_nick_name == "SDWD"){//设定温度
                                        $('#ms_f_sdwd').val(updateInfo[i].f_init_val);
                                    }
                                }*/
                            }
                            /*for (var i = 0; i < updateInfo.length; i++) {
                                if (updateInfo[i] != '' || updateInfo[i] != null) {
                                    if (updateInfo[i].f_init_val == "0"){//关闭
                                        $("#" + updateInfo[i].f_sys_name).val(0);
                                    }else{
                                        $("#" + updateInfo[i].f_sys_name).val(updateInfo[i].f_init_val);

                                    }

                                    if(updateInfo[i].f_sys_name.indexOf('00')){
                                        if(updateInfo[i].f_init_val == 0){//关机
                                            dataAnalysis_wkjk.stopEdit();
                                        }else if(updateInfo[i].f_init_val == 255){//开机
                                            dataAnalysis_wkjk.openEdit();
                                        }
                                    }

                                }
                            }*/

                        }
                    },
                    error : function(returnObject) {
                        swal(returnObject.msg, "", "error");
                    }
                });

            }
        }
    })(jQuery, window, document);
    //dataAnalysis_wkjk.pageInit();
</script>