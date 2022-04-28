<link href="${ctx}/static/css/ktjk.css" rel="stylesheet">
<!-- 左侧设备树start -->
<div id="ktjk_div" class="leftarea information_left">
    <div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
                                        aria-hidden="true"></i>&nbsp;请选择监控位置>>>
		</span>
    </div>
    <div id="ktjk_sbdy_tree" class="Information_area"></div>
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
        <div id="ktjk2" style="height:96%;width:100%"></div>

    </div>
</div>
<!-- 右侧监控界面End -->

<script type="text/javascript">
    var dataAnalysis_ktjk2 = (function($, window, document, undefined) {
        var _ctx = '${ctx}';
        var selected_treeview = null;//treeview上选中的节点
        var nodePage = "";//节点对应的页面
        var clock = window.setInterval("dataAnalysis_ktjk2.refresh()",4000);
        //配置楼宇tree
        var treeList = new Array();
        var pageIsHide = false; // 记录页面是否是隐藏状态
    /*    var ddcNames = "";
        var runState = true;*/
        $(function() {

            treeList.push(
                    {nodeTreeId:"01",ddc: "YWA2_DDC1,YWA2_DDC2", pid:"",text:"1#A2空调监控",id:"YWA2_DDC1_M103,YWA2_DDC1_M104,YWA2_DDC1_M105,YWA2_DDC1_M106,YWA2_DDC1_M107,YWA2_DDC1_M108,YWA2_DDC1_M1011,YWA2_DDC1_M1012,YWA2_DDC1_M1013,YWA2_DDC1_M208,YWA2_DDC1_M209,YWA2_DDC1_M2010,YWA2_DDC1_M2011,YWA2_DDC1_M2012,YWA2_DDC1_M2013,YWA2_DDC2_M104,YWA2_DDC2_M105,YWA2_DDC2_M106,YWA2_DDC2_M107,YWA2_DDC2_M108,YWA2_DDC2_M109,YWA2_DDC2_M1010,YWA2_DDC2_M1011,YWA2_DDC2_M204,YWA2_DDC2_M205,YWA2_DDC2_M206,YWA2_DDC2_M208,YWA2_DDC2_M209,YWA2_DDC2_M2010",rootId:"01A2",leaf:false,nodes: null, nodePage:"2ktA2"},
                    {nodeTreeId:"02",ddc: "YWA5_DDC1,YWA5_DDC2", pid:"",text:"1#A5空调监控",id:"YWA5_DDC1_M103,YWA5_DDC1_M104,YWA5_DDC1_M105,YWA5_DDC1_M106,YWA5_DDC1_M107,YWA5_DDC1_M108,YWA5_DDC1_M1011,YWA5_DDC1_M1012,YWA5_DDC1_M1013,YWA5_DDC1_M208,YWA5_DDC1_M209,YWA5_DDC1_M2010,YWA5_DDC1_M2011,YWA5_DDC1_M2012,YWA5_DDC1_M2013,YWA5_DDC2_M104,YWA5_DDC2_M105,YWA5_DDC2_M106,YWA5_DDC2_M107,YWA5_DDC2_M108,YWA5_DDC2_M109,YWA5_DDC2_M1010,YWA5_DDC2_M1011,YWA5_DDC2_M204,YWA5_DDC2_M205,YWA5_DDC2_M206,YWA5_DDC2_M208,YWA5_DDC2_M209,YWA5_DDC2_M2010",rootId:"01A5",leaf:false,nodes: null, nodePage:"2ktA5"},
                    {nodeTreeId:"03",ddc: "YWA7_DDC1,YWA7_DDC2", pid:"",text:"1#A7空调监控",id:"YWA7_DDC1_M103,YWA7_DDC1_M104,YWA7_DDC1_M105,YWA7_DDC1_M106,YWA7_DDC1_M107,YWA7_DDC1_M108,YWA7_DDC1_M1011,YWA7_DDC1_M1012,YWA7_DDC1_M1013,YWA7_DDC1_M208,YWA7_DDC1_M209,YWA7_DDC1_M2010,YWA7_DDC1_M2011,YWA7_DDC1_M2012,YWA7_DDC1_M2013,YWA7_DDC2_M104,YWA7_DDC2_M105,YWA7_DDC2_M106,YWA7_DDC2_M107,YWA7_DDC2_M108,YWA7_DDC2_M109,YWA7_DDC2_M1010,YWA7_DDC2_M1011,YWA7_DDC2_M204,YWA7_DDC2_M205,YWA7_DDC2_M206,YWA7_DDC2_M208,YWA7_DDC2_M209,YWA7_DDC2_M2010",rootId:"01A7",leaf:false,nodes: null, nodePage:"2ktA7"},
                    {nodeTreeId:"04",ddc: "EW_DDC1,EW_DDC2", pid:"",text:"2#空调监控",id:"EW_DDC1_M103,EW_DDC1_M104,EW_DDC1_M105,EW_DDC1_M106,EW_DDC1_M107,EW_DDC1_M108,EW_DDC1_M1011,EW_DDC1_M1012,EW_DDC1_M1013,EW_DDC1_M208,EW_DDC1_M209,EW_DDC1_M2010,EW_DDC1_M2011,EW_DDC1_M2012,EW_DDC1_M2013,EW_DDC2_M104,EW_DDC2_M105,EW_DDC2_M106,EW_DDC2_M107,EW_DDC2_M108,EW_DDC2_M109,EW_DDC2_M1010,EW_DDC2_M1011,EW_DDC2_M204,EW_DDC2_M208,EW_DDC2_M304,EW_DDC2_M305,EW_DDC2_M306,EW_DDC2_M307,EW_DDC2_M308,EW_DDC2_M309,EW_DDC2_M3010,EW_DDC2_M3011",rootId:"02",leaf:false,nodes: null, nodePage:"2kt2"},
                    {nodeTreeId:"05",ddc: "SAW_DDC1,SAW_DDC2", pid:"",text:"3#空调监控",id:"SAW_DDC2_M103,SAW_DDC2_M104,SAW_DDC2_M105,SAW_DDC2_M106,SAW_DDC2_M107,SAW_DDC2_M108,SAW_DDC2_M1011,SAW_DDC2_M1012,SAW_DDC2_M1013,SAW_DDC2_M208,SAW_DDC2_M209,SAW_DDC2_M2010,SAW_DDC2_M2011,SAW_DDC2_M2012,SAW_DDC2_M2013,SAW_DDC1_M104,SAW_DDC1_M105,SAW_DDC1_M106,SAW_DDC1_M108,SAW_DDC1_M109,SAW_DDC1_M1010,SAW_DDC1_M204,SAW_DDC1_M205,SAW_DDC1_M206,SAW_DDC1_M207,SAW_DDC1_M208,SAW_DDC1_M209,SAW_DDC1_M2010,SAW_DDC1_M2011,SAW_DDC1_M304,SAW_DDC1_M305,SAW_DDC1_M308,SAW_DDC1_M309,SAW_DDC1_M404,SAW_DDC1_M405,SAW_DDC1_M406,SAW_DDC1_M407,SAW_DDC1_M408,SAW_DDC1_M409,SAW_DDC1_M4010,SAW_DDC1_M4011,SAW_DDC1_M504,SAW_DDC1_M505,SAW_DDC1_M508,SAW_DDC1_M509",rootId:"03",leaf:false,nodes: null, nodePage:"2kt3"},
                    {nodeTreeId:"06",ddc: "SIW_DDC1,SIW_DDC2", pid:"",text:"4#空调监控",id:"SIW_DDC1_M103,SIW_DDC1_M104,SIW_DDC1_M105,SIW_DDC1_M106,SIW_DDC1_M107,SIW_DDC1_M108,SIW_DDC1_M1011,SIW_DDC1_M1012,SIW_DDC1_M1013,SIW_DDC1_M208,SIW_DDC1_M209,SIW_DDC1_M2010,SIW_DDC1_M2011,SIW_DDC1_M2012,SIW_DDC1_M2013,SIW_DDC2_M104,SIW_DDC2_M105,SIW_DDC2_M106,SIW_DDC2_M107,SIW_DDC2_M108,SIW_DDC2_M109,SIW_DDC2_M1010,SIW_DDC2_M1011,SIW_DDC2_M204,SIW_DDC2_M205,SIW_DDC2_M206,SIW_DDC2_M208,SIW_DDC2_M209,SIW_DDC2_M2010,SIW_DDC2_M304,SIW_DDC2_M305,SIW_DDC2_M306,SIW_DDC2_M307,SIW_DDC2_M308,SIW_DDC2_M309,SIW_DDC2_M3010,SIW_DDC2_M3011",rootId:"04",leaf:false,nodes: null, nodePage:"2kt4"},
                    {nodeTreeId:"07",ddc: "WW_DDC1,WW_DDC2", pid:"",text:"5#C1C2空调监控",id:"WW_DDC1_M103,WW_DDC1_M104,WW_DDC1_M105,WW_DDC1_M106,WW_DDC1_M107,WW_DDC1_M108,WW_DDC1_M1011,WW_DDC1_M1012,WW_DDC1_M1013,WW_DDC1_M208,WW_DDC1_M209,WW_DDC1_M2010,WW_DDC1_M2011,WW_DDC1_M2012,WW_DDC1_M2013,WW_DDC2_M104,WW_DDC2_M105,WW_DDC2_M106,WW_DDC2_M107,WW_DDC2_M108,WW_DDC2_M109,WW_DDC2_M1010,WW_DDC2_M1011,WW_DDC2_M204,WW_DDC2_M205,WW_DDC2_M208,WW_DDC2_M209,WW_DDC2_M304,WW_DDC2_M305,WW_DDC2_M306,WW_DDC2_M307,WW_DDC2_M308,WW_DDC2_M309,WW_DDC2_M3010,WW_DDC2_M3011,WW_DDC2_M404,WW_DDC2_M405,WW_DDC2_M408,WW_DDC2_M409,WW_DDC2_M504,WW_DDC2_M505,WW_DDC2_M506,WW_DDC2_M507,WW_DDC2_M508,WW_DDC2_M509,WW_DDC2_M5010,WW_DDC2_M5011,WW_DDC2_M604,WW_DDC2_M605,WW_DDC2_M608,WW_DDC2_M609",rootId:"05",leaf:false,nodes: null, nodePage:"2ktC1C2"},
					{nodeTreeId:"08",ddc: "WW_DDC7,WW_DDC8", pid:"",text:"5#C7C8空调监控",id:"WW_DDC7_M103,WW_DDC7_M104,WW_DDC7_M105,WW_DDC7_M106,WW_DDC7_M107,WW_DDC7_M108,WW_DDC7_M1011,WW_DDC7_M1012,WW_DDC7_M1013,WW_DDC7_M208,WW_DDC7_M209,WW_DDC7_M2010,WW_DDC7_M2011,WW_DDC7_M2012,WW_DDC7_M2013,WW_DDC8_M104,WW_DDC8_M105,WW_DDC8_M106,WW_DDC8_M107,WW_DDC8_M108,WW_DDC8_M109,WW_DDC8_M1010,WW_DDC8_M1011,WW_DDC8_M204,WW_DDC8_M205,WW_DDC8_M208,WW_DDC8_M209,WW_DDC8_M304,WW_DDC8_M305,WW_DDC8_M306,WW_DDC8_M307,WW_DDC8_M308,WW_DDC8_M309,WW_DDC8_M3010,WW_DDC8_M3011",rootId:"05",leaf:false,nodes: null, nodePage:"2ktC7C8"},
                    {nodeTreeId:"09",ddc: "FLOWER_4_1_E", pid:"",text:"4#1楼东组合空调监控",id:"AirUnitRunStopCmd_4_1_1,SetWorkMode_4_1_1,RunStopStatus_4_1_1,ManualAutoStatus_4_1_1,AlarmStatus_4_1_1,WorkMode_4_1_1",rootId:"0411",leaf:false,nodes: null, nodePage:"4kt1"},
                    {nodeTreeId:"10",ddc: "FLOWER_4_2_SN", pid:"",text:"4#2楼组合空调监控",id:"AirUnitRunStopCmd_4_2_1,AirUnitRunStopCmd_4_2_2,SetWorkMode_4_2_1,SetWorkMode_4_2_2,RunStopStatus_4_2_1,RunStopStatus_4_2_2,ManualAutoStatus_4_2_1,ManualAutoStatus_4_2_2,AlarmStatus_4_2_1,AlarmStatus_4_2_2,WorkMode_4_2_1,WorkMode_4_2_2",rootId:"04",leaf:false,nodes: null, nodePage:"4kt2"},
                    {nodeTreeId:"11",ddc: "FLOWER_1_A8_2", pid:"",text:"1#A8区2F组合空调监控",id:"AirUnitRunStopCmd_1_A8_1,SetWorkMode_1_A8_1,RunStopStatus_1_A8_1,ManualAutoStatus_1_A8_1,AlarmStatus_1_A8_1,WorkMode_1_A8_1",rootId:"01A8",leaf:false,nodes: null, nodePage:"1ktA8"},
                    {nodeTreeId:"12",ddc: "YWA2_DDC2", pid:"",text:"1#A2区面板1水阀机组监控",id:"K_1_A2_1_KGJKZ,K_1_A2_1_MSKZ,K_1_A2_1_1_KGJ,K_1_A2_1_1_XSBGZMS,K_1_A2_1_1_ZLJSSZWD,K_1_A2_1_1_GZBZ,K_1_A2_1_1_GZMS,K_1_A2_1_1_KTJSWD,K_1_A2_1_1_KTCSWD,K_1_A2_1_1_WHJWD,K_1_A2_1_2_GZMS,K_1_A2_1_2_KTJSWD,K_1_A2_1_2_KTCSWD,K_1_A2_1_2_WHJWD,K_1_A2_1_3_GZMS,K_1_A2_1_3_KTJSWD,K_1_A2_1_3_KTCSWD,K_1_A2_1_3_WHJWD,K_1_A2_1_4_GZMS,K_1_A2_1_4_KTJSWD,K_1_A2_1_4_KTCSWD,K_1_A2_1_4_WHJWD,K_1_A2_1_5_GZMS,K_1_A2_1_5_KTJSWD,K_1_A2_1_5_KTCSWD,K_1_A2_1_5_WHJWD,K_1_A2_1_6_GZMS,K_1_A2_1_6_KTJSWD,K_1_A2_1_6_KTCSWD,K_1_A2_1_6_WHJWD,K_1_A2_1_7_GZMS,K_1_A2_1_7_KTJSWD,K_1_A2_1_7_KTCSWD,K_1_A2_1_7_WHJWD,K_1_A2_1_8_GZMS,K_1_A2_1_8_KTJSWD,K_1_A2_1_8_KTCSWD,K_1_A2_1_8_WHJWD,K_1_A2_1_9_GZMS,K_1_A2_1_9_KTJSWD,K_1_A2_1_9_KTCSWD,K_1_A2_1_9_WHJWD,K_1_A2_1_10_GZMS,K_1_A2_1_10_KTJSWD,K_1_A2_1_10_KTCSWD,K_1_A2_1_10_WHJWD,K_1_A2_1_11_GZMS,K_1_A2_1_11_KTJSWD,K_1_A2_1_11_KTCSWD,K_1_A2_1_11_WHJWD,K_1_A2_1_12_GZMS,K_1_A2_1_12_KTJSWD,K_1_A2_1_12_KTCSWD,K_1_A2_1_12_WHJWD,K_1_A2_1_13_GZMS,K_1_A2_1_13_KTJSWD,K_1_A2_1_13_KTCSWD,K_1_A2_1_13_WHJWD,K_1_A2_1_14_GZMS,K_1_A2_1_14_KTJSWD,K_1_A2_1_14_KTCSWD,K_1_A2_1_14_WHJWD",rootId:"12",leaf:false,nodes: null, nodePage:"1ktA2"},
                    {nodeTreeId:"13",ddc: "YWA5_DDC2", pid:"",text:"1#A5区面板1水阀机组监控",id:"K_1_A5_1_1_KGJ,K_1_A5_1_1_XSBGZMS,K_1_A5_1_1_ZLJSSZWD,K_1_A5_1_1_GZBZ,K_1_A5_1_1_GZMS,K_1_A5_1_1_KTJSWD,K_1_A5_1_1_KTCSWD,K_1_A5_1_1_WHJWD,K_1_A5_1_2_GZMS,K_1_A5_1_2_KTJSWD,K_1_A5_1_2_KTCSWD,K_1_A5_1_2_WHJWD,K_1_A5_1_3_GZMS,K_1_A5_1_3_KTJSWD,K_1_A5_1_3_KTCSWD,K_1_A5_1_3_WHJWD,K_1_A5_1_4_GZMS,K_1_A5_1_4_KTJSWD,K_1_A5_1_4_KTCSWD,K_1_A5_1_4_WHJWD,K_1_A5_1_5_GZMS,K_1_A5_1_5_KTJSWD,K_1_A5_1_5_KTCSWD,K_1_A5_1_5_WHJWD,K_1_A5_1_6_GZMS,K_1_A5_1_6_KTJSWD,K_1_A5_1_6_KTCSWD,K_1_A5_1_6_WHJWD,K_1_A5_1_7_GZMS,K_1_A5_1_7_KTJSWD,K_1_A5_1_7_KTCSWD,K_1_A5_1_7_WHJWD,K_1_A5_1_8_GZMS,K_1_A5_1_8_KTJSWD,K_1_A5_1_8_KTCSWD,K_1_A5_1_8_WHJWD,K_1_A5_1_9_GZMS,K_1_A5_1_9_KTJSWD,K_1_A5_1_9_KTCSWD,K_1_A5_1_9_WHJWD,K_1_A5_1_10_GZMS,K_1_A5_1_10_KTJSWD,K_1_A5_1_10_KTCSWD,K_1_A5_1_10_WHJWD,K_1_A5_1_11_GZMS,K_1_A5_1_11_KTJSWD,K_1_A5_1_11_KTCSWD,K_1_A5_1_11_WHJWD,K_1_A5_1_12_GZMS,K_1_A5_1_12_KTJSWD,K_1_A5_1_12_KTCSWD,K_1_A5_1_12_WHJWD,K_1_A5_1_13_GZMS,K_1_A5_1_13_KTJSWD,K_1_A5_1_13_KTCSWD,K_1_A5_1_13_WHJWD,K_1_A5_1_14_GZMS,K_1_A5_1_14_KTJSWD,K_1_A5_1_14_KTCSWD,K_1_A5_1_14_WHJWD,K_1_A5_1_KGJKZ,K_1_A5_1_MSKZ,K_1_A5_1_15_GZMS,K_1_A5_1_15_KTJSWD,K_1_A5_1_15_KTCSWD,K_1_A5_1_15_WHJWD,K_1_A5_1_16_GZMS,K_1_A5_1_16_KTJSWD,K_1_A5_1_16_KTCSWD,K_1_A5_1_16_WHJWD",rootId:"13",leaf:false,nodes: null, nodePage:"1ktA5"},
					{nodeTreeId:"14",ddc: "YWA7_DDC2", pid:"",text:"1#A7区面板1水阀机组监控",id:"K_1_A7_1_1_KGJ,K_1_A7_1_1_XSBGZMS,K_1_A7_1_1_ZLJSSZWD,K_1_A7_1_1_GZBZ,K_1_A7_1_1_GZMS,K_1_A7_1_1_KTJSWD,K_1_A7_1_1_KTCSWD,K_1_A7_1_1_WHJWD,K_1_A7_1_2_GZMS,K_1_A7_1_2_KTJSWD,K_1_A7_1_2_KTCSWD,K_1_A7_1_2_WHJWD,K_1_A7_1_3_GZMS,K_1_A7_1_3_KTJSWD,K_1_A7_1_3_KTCSWD,K_1_A7_1_3_WHJWD,K_1_A7_1_4_GZMS,K_1_A7_1_4_KTJSWD,K_1_A7_1_4_KTCSWD,K_1_A7_1_4_WHJWD,K_1_A7_1_5_GZMS,K_1_A7_1_5_KTJSWD,K_1_A7_1_5_KTCSWD,K_1_A7_1_5_WHJWD,K_1_A7_1_6_GZMS,K_1_A7_1_6_KTJSWD,K_1_A7_1_6_KTCSWD,K_1_A7_1_6_WHJWD,K_1_A7_1_7_GZMS,K_1_A7_1_7_KTJSWD,K_1_A7_1_7_KTCSWD,K_1_A7_1_7_WHJWD,K_1_A7_1_8_GZMS,K_1_A7_1_8_KTJSWD,K_1_A7_1_8_KTCSWD,K_1_A7_1_8_WHJWD,K_1_A7_1_9_GZMS,K_1_A7_1_9_KTJSWD,K_1_A7_1_9_KTCSWD,K_1_A7_1_9_WHJWD,K_1_A7_1_10_GZMS,K_1_A7_1_10_KTJSWD,K_1_A7_1_10_KTCSWD,K_1_A7_1_10_WHJWD,K_1_A7_1_11_GZMS,K_1_A7_1_11_KTJSWD,K_1_A7_1_11_KTCSWD,K_1_A7_1_11_WHJWD,K_1_A7_1_12_GZMS,K_1_A7_1_12_KTJSWD,K_1_A7_1_12_KTCSWD,K_1_A7_1_12_WHJWD,K_1_A7_1_13_GZMS,K_1_A7_1_13_KTJSWD,K_1_A7_1_13_KTCSWD,K_1_A7_1_13_WHJWD,K_1_A7_1_14_GZMS,K_1_A7_1_14_KTJSWD,K_1_A7_1_14_KTCSWD,K_1_A7_1_14_WHJWD,K_1_A7_1_KGJKZ,K_1_A7_1_MSKZ",rootId:"14",leaf:false,nodes: null, nodePage:"1ktA7"},
					{nodeTreeId:"15",ddc: "YWA2_DDC1", pid:"",text:"1#A2区面板1水泵机组监控",id:"B_1_A2_SZDZD,B_1_A2_SCQSZS,B_1_A2_YLBGZZS,B_1_A2_AGBZS,B_1_A2_AYXZS,B_1_A2_AYZGZZS,B_1_A2_BYXZS,B_1_A2_BGBZS,B_1_A2_BYZGZZS,B_1_A2_CYXZS,B_1_A2_CGBZS,B_1_A2_CYZGZZS,B_1_A2_BPGZZS,B_1_A2_ABPZS,B_1_A2_BBPZS,B_1_A2_CBPZS,B_1_A2_BPPL,B_1_A2_SDYL",rootId:"15",leaf:false,nodes: null, nodePage:"1ktA21"},
					{nodeTreeId:"16",ddc: "YWA5_DDC1", pid:"",text:"1#A5区面板1水泵机组监控",id:"B_1_A5_SZDZD,B_1_A5_SCQSZS,B_1_A5_YLBGZZS,B_1_A5_AGBZS,B_1_A5_AYXZS,B_1_A5_AYZGZZS,B_1_A5_BYXZS,B_1_A5_BGBZS,B_1_A5_BYZGZZS,B_1_A5_CYXZS,B_1_A5_CGBZS,B_1_A5_CYZGZZS,B_1_A5_BPGZZS,B_1_A5_ABPZS,B_1_A5_BBPZS,B_1_A5_CBPZS,B_1_A5_BPPL,B_1_A5_SDYL",rootId:"16",leaf:false,nodes: null, nodePage:"1ktA51"},
					{nodeTreeId:"17",ddc: "YWA7_DDC1", pid:"",text:"1#A7区面板1水泵机组监控",id:"B_1_A7_SZDZD,B_1_A7_SCQSZS,B_1_A7_YLBGZZS,B_1_A7_AGBZS,B_1_A7_AYXZS,B_1_A7_AYZGZZS,B_1_A7_BYXZS,B_1_A7_BGBZS,B_1_A7_BYZGZZS,B_1_A7_CYXZS,B_1_A7_CGBZS,B_1_A7_CYZGZZS,B_1_A7_BPGZZS,B_1_A7_ABPZS,B_1_A7_BBPZS,B_1_A7_CBPZS,B_1_A7_BPPL,B_1_A7_SDYL",rootId:"17",leaf:false,nodes: null, nodePage:"1ktA71"},
					{nodeTreeId:"18",ddc: "EW_DDC2", pid:"",text:"2#A1区机组1水阀机组监控",id:"K_2_A1_1_1_KGJ,K_2_A1_1_1_XSBGZMS,K_2_A1_1_1_ZLJSSZWD,K_2_A1_1_1_GZBZ,K_2_A1_1_1_GZMS,K_2_A1_1_1_KTJSWD,K_2_A1_1_1_KTCSWD,K_2_A1_1_1_WHJWD,K_2_A1_1_2_GZMS,K_2_A1_1_2_KTJSWD,K_2_A1_1_2_KTCSWD,K_2_A1_1_2_WHJWD,K_2_A1_1_3_GZMS,K_2_A1_1_3_KTJSWD,K_2_A1_1_3_KTCSWD,K_2_A1_1_3_WHJWD,K_2_A1_1_4_GZMS,K_2_A1_1_4_KTJSWD,K_2_A1_1_4_KTCSWD,K_2_A1_1_4_WHJWD,K_2_A1_1_5_GZMS,K_2_A1_1_5_KTJSWD,K_2_A1_1_5_KTCSWD,K_2_A1_1_5_WHJWD,K_2_A1_1_6_GZMS,K_2_A1_1_6_KTJSWD,K_2_A1_1_6_KTCSWD,K_2_A1_1_6_WHJWD,K_2_A1_1_7_GZMS,K_2_A1_1_7_KTJSWD,K_2_A1_1_7_KTCSWD,K_2_A1_1_7_WHJWD,K_2_A1_1_8_GZMS,K_2_A1_1_8_KTJSWD,K_2_A1_1_8_KTCSWD,K_2_A1_1_8_WHJWD,K_2_A1_1_9_GZMS,K_2_A1_1_9_KTJSWD,K_2_A1_1_9_KTCSWD,K_2_A1_1_9_WHJWD,K_2_A1_1_10_GZMS,K_2_A1_1_10_KTJSWD,K_2_A1_1_10_KTCSWD,K_2_A1_1_10_WHJWD,K_2_A1_2_KGKZ,K_2_A1_2_MSKZ",rootId:"18",leaf:false,nodes: null, nodePage:"2ktA1"},
					{nodeTreeId:"28",ddc: "EW_DDC2", pid:"",text:"2#A1区机组2水阀机组监控",id:"K_2_A1_2_1_KGJ,K_2_A1_2_1_XSBGZMS,K_2_A1_2_1_ZLJSSZWD,K_2_A1_2_1_GZBZ,K_2_A1_2_1_GZMS,K_2_A1_2_1_KTJSWD,K_2_A1_2_1_KTCSWD,K_2_A1_2_1_WHJWD,K_2_A1_2_2_GZMS,K_2_A1_2_2_KTJSWD,K_2_A1_2_2_KTCSWD,K_2_A1_2_2_WHJWD,K_2_A1_2_3_GZMS,K_2_A1_2_3_KTJSWD,K_2_A1_2_3_KTCSWD,K_2_A1_2_3_WHJWD,K_2_A1_2_4_GZMS,K_2_A1_2_4_KTJSWD,K_2_A1_2_4_KTCSWD,K_2_A1_2_4_WHJWD,K_2_A1_2_5_GZMS,K_2_A1_2_5_KTJSWD,K_2_A1_2_5_KTCSWD,K_2_A1_2_5_WHJWD,K_2_A1_2_6_GZMS,K_2_A1_2_6_KTJSWD,K_2_A1_2_6_KTCSWD,K_2_A1_2_6_WHJWD,K_2_A1_2_7_GZMS,K_2_A1_2_7_KTJSWD,K_2_A1_2_7_KTCSWD,K_2_A1_2_7_WHJWD,K_2_A1_2_8_GZMS,K_2_A1_2_8_KTJSWD,K_2_A1_2_8_KTCSWD,K_2_A1_2_8_WHJWD",rootId:"28",leaf:false,nodes: null, nodePage:"2ktA21"},
					{nodeTreeId:"19",ddc: "EW_DDC1", pid:"",text:"2#A1区水泵机组监控",id:"B_2_A1_SZDZD,B_2_A1_SCQSZS,B_2_A1_YLBGZZS,B_2_A1_AGBZS,B_2_A1_AYXZS,B_2_A1_AYZGZZS,B_2_A1_BYXZS,B_2_A1_BGBZS,B_2_A1_BYZGZZS,B_2_A1_CYXZS,B_2_A1_CGBZS,B_2_A1_CYZGZZS,B_2_A1_BPGZZS,B_2_A1_ABPZS,B_2_A1_BBPZS,B_2_A1_CBPZS,B_2_A1_BPPL,B_2_A1_SDYL",rootId:"19",leaf:false,nodes: null, nodePage:"2ktA12"},
					{nodeTreeId:"20",ddc: "SAW_DDC1", pid:"",text:"3#A1区机组1水阀机组监控",id:"K_3_A1_1_1_KGJ,K_3_A1_1_1_XSBGZMS,K_3_A1_1_1_ZLJSSZWD,K_3_A1_1_1_GZBZ,K_3_A1_1_1_GZMS,K_3_A1_1_1_KTJSWD,K_3_A1_1_1_KTCSWD,K_3_A1_1_1_WHJWD,K_3_A1_1_2_GZMS,K_3_A1_1_2_KTJSWD,K_3_A1_1_2_KTCSWD,K_3_A1_1_2_WHJWD,K_3_A1_1_3_GZMS,K_3_A1_1_3_KTJSWD,K_3_A1_1_3_KTCSWD,K_3_A1_1_3_WHJWD,K_3_A1_1_4_GZMS,K_3_A1_1_4_KTJSWD,K_3_A1_1_4_KTCSWD,K_3_A1_1_4_WHJWD,K_3_A1_1_5_GZMS,K_3_A1_1_5_KTJSWD,K_3_A1_1_5_KTCSWD,K_3_A1_1_5_WHJWD,K_3_A1_1_6_GZMS,K_3_A1_1_6_KTJSWD,K_3_A1_1_6_KTCSWD,K_3_A1_1_6_WHJWD,K_3_A1_1_KGKZ,K_3_A1_1_MSKZ",rootId:"18",leaf:false,nodes: null, nodePage:"3ktA1"},
					{nodeTreeId:"29",ddc: "SAW_DDC1", pid:"",text:"3#A1区机组2水阀机组监控",id:"K_3_A1_2_1_KGJ,K_3_A1_2_1_XSBGZMS,K_3_A1_2_1_ZLJSSZWD,K_3_A1_2_1_GZBZ,K_3_A1_2_1_GZMS,K_3_A1_2_1_KTJSWD,K_3_A1_2_1_KTCSWD,K_3_A1_2_1_WHJWD,K_3_A1_2_2_GZMS,K_3_A1_2_2_KTJSWD,K_3_A1_2_2_KTCSWD,K_3_A1_2_2_WHJWD,K_3_A1_2_3_GZMS,K_3_A1_2_3_KTJSWD,K_3_A1_2_3_KTCSWD,K_3_A1_2_3_WHJWD,K_3_A1_2_4_GZMS,K_3_A1_2_4_KTJSWD,K_3_A1_2_4_KTCSWD,K_3_A1_2_4_WHJWD,K_3_A1_2_5_GZMS,K_3_A1_2_5_KTJSWD,K_3_A1_2_5_KTCSWD,K_3_A1_2_5_WHJWD,K_3_A1_2_6_GZMS,K_3_A1_2_6_KTJSWD,K_3_A1_2_6_KTCSWD,K_3_A1_2_6_WHJWD,K_3_A1_2_7_GZMS,K_3_A1_2_7_KTJSWD,K_3_A1_2_7_KTCSWD,K_3_A1_2_7_WHJWD,K_3_A1_2_8_GZMS,K_3_A1_2_8_KTJSWD,K_3_A1_2_8_KTCSWD,K_3_A1_2_8_WHJWD,K_3_A1_2_9_GZMS,K_3_A1_2_9_KTJSWD,K_3_A1_2_9_KTCSWD,K_3_A1_2_9_WHJWD,K_3_A1_2_10_GZMS,K_3_A1_2_10_KTJSWD,K_3_A1_2_10_KTCSWD,K_3_A1_2_10_WHJWD,K_3_A1_2_11_GZMS,K_3_A1_2_11_KTJSWD,K_3_A1_2_11_KTCSWD,K_3_A1_2_11_WHJWD,K_3_A1_2_12_GZMS,K_3_A1_2_12_KTJSWD,K_3_A1_2_12_KTCSWD,K_3_A1_2_12_WHJWD",rootId:"29",leaf:false,nodes: null, nodePage:"3ktA21"},
					{nodeTreeId:"30",ddc: "SAW_DDC1", pid:"",text:"3#A1区机组3水阀机组监控",id:"K_3_A1_3_1_KGJ,K_3_A1_3_1_XSBGZMS,K_3_A1_3_1_ZLJSSZWD,K_3_A1_3_1_GZBZ,K_3_A1_3_1_GZMS,K_3_A1_3_1_KTJSWD,K_3_A1_3_1_KTCSWD,K_3_A1_3_1_WHJWD,K_3_A1_3_2_GZMS,K_3_A1_3_2_KTJSWD,K_3_A1_3_2_KTCSWD,K_3_A1_3_2_WHJWD,K_3_A1_3_3_GZMS,K_3_A1_3_3_KTJSWD,K_3_A1_3_3_KTCSWD,K_3_A1_3_3_WHJWD,K_3_A1_3_4_GZMS,K_3_A1_3_4_KTJSWD,K_3_A1_3_4_KTCSWD,K_3_A1_3_4_WHJWD,K_3_A1_3_5_GZMS,K_3_A1_3_5_KTJSWD,K_3_A1_3_5_KTCSWD,K_3_A1_3_5_WHJWD,K_3_A1_3_6_GZMS,K_3_A1_3_6_KTJSWD,K_3_A1_3_6_KTCSWD,K_3_A1_3_6_WHJWD,K_3_A1_3_7_GZMS,K_3_A1_3_7_KTJSWD,K_3_A1_3_7_KTCSWD,K_3_A1_3_7_WHJWD,K_3_A1_3_8_GZMS,K_3_A1_3_8_KTJSWD,K_3_A1_3_8_KTCSWD,K_3_A1_3_8_WHJWD,K_3_A1_3_9_GZMS,K_3_A1_3_9_KTJSWD,K_3_A1_3_9_KTCSWD,K_3_A1_3_9_WHJWD,K_3_A1_3_10_GZMS,K_3_A1_3_10_KTJSWD,K_3_A1_3_10_KTCSWD,K_3_A1_3_10_WHJWD,K_3_A1_3_11_GZMS,K_3_A1_3_11_KTJSWD,K_3_A1_3_11_KTCSWD,K_3_A1_3_11_WHJWD,K_3_A1_3_12_GZMS,K_3_A1_3_12_KTJSWD,K_3_A1_3_12_KTCSWD,K_3_A1_3_12_WHJWD",rootId:"30",leaf:false,nodes: null, nodePage:"3ktA31"},
					{nodeTreeId:"21",ddc: "SAW_DDC2", pid:"",text:"3#A1区水泵机组监控",id:"B_3_A1_SZDZD,B_3_A1_SCQSZS,B_3_A1_YLBGZZS,B_3_A1_AGBZS,B_3_A1_AYXZS,B_3_A1_AYZGZZS,B_3_A1_BYXZS,B_3_A1_BGBZS,B_3_A1_BYZGZZS,B_3_A1_CYXZS,B_3_A1_CGBZS,B_3_A1_CYZGZZS,B_3_A1_BPGZZS,B_3_A1_ABPZS,B_3_A1_BBPZS,B_3_A1_CBPZS,B_3_A1_BPPL,B_3_A1_SDYL",rootId:"19",leaf:false,nodes: null, nodePage:"3ktA12"},
					{nodeTreeId:"22",ddc: "SIW_DDC2", pid:"",text:"4#A1区机组1水阀机组监控",id:"K_4_A1_1_1_KGJ,K_4_A1_1_1_XSBGZMS,K_4_A1_1_1_ZLJSSZWD,K_4_A1_1_1_GZBZ,K_4_A1_1_1_GZMS,K_4_A1_1_1_KTJSWD,K_4_A1_1_1_KTCSWD,K_4_A1_1_1_WHJWD,K_4_A1_1_2_GZMS,K_4_A1_1_2_KTJSWD,K_4_A1_1_2_KTCSWD,K_4_A1_1_2_WHJWD,K_4_A1_1_3_GZMS,K_4_A1_1_3_KTJSWD,K_4_A1_1_3_KTCSWD,K_4_A1_1_3_WHJWD,K_4_A1_1_4_GZMS,K_4_A1_1_4_KTJSWD,K_4_A1_1_4_KTCSWD,K_4_A1_1_4_WHJWD,K_4_A1_1_5_GZMS,K_4_A1_1_5_KTJSWD,K_4_A1_1_5_KTCSWD,K_4_A1_1_5_WHJWD,K_4_A1_1_6_GZMS,K_4_A1_1_6_KTJSWD,K_4_A1_1_6_KTCSWD,K_4_A1_1_6_WHJWD,K_4_A1_1_7_GZMS,K_4_A1_1_7_KTJSWD,K_4_A1_1_7_KTCSWD,K_4_A1_1_7_WHJWD,K_4_A1_1_8_GZMS,K_4_A1_1_8_KTJSWD,K_4_A1_1_8_KTCSWD,K_4_A1_1_8_WHJWD,K_4_A1_1_9_GZMS,K_4_A1_1_9_KTJSWD,K_4_A1_1_9_KTCSWD,K_4_A1_1_9_WHJWD,K_4_A1_1_10_GZMS,K_4_A1_1_10_KTJSWD,K_4_A1_1_10_KTCSWD,K_4_A1_1_10_WHJWD,K_4_A1_1_KGKZ,K_4_A1_1_MSKZ",rootId:"22",leaf:false,nodes: null, nodePage:"4ktA1"},
					{nodeTreeId:"31",ddc: "SIW_DDC2", pid:"",text:"4#A1区机组2水阀机组监控",id:"K_4_A1_2_1_KGJ,K_4_A1_2_1_XSBGZMS,K_4_A1_2_1_ZLJSSZWD,K_4_A1_2_1_GZBZ,K_4_A1_2_1_GZMS,K_4_A1_2_1_KTJSWD,K_4_A1_2_1_KTCSWD,K_4_A1_2_1_WHJWD,K_4_A1_2_2_GZMS,K_4_A1_2_2_KTJSWD,K_4_A1_2_2_KTCSWD,K_4_A1_2_2_WHJWD,K_4_A1_2_3_GZMS,K_4_A1_2_3_KTJSWD,K_4_A1_2_3_KTCSWD,K_4_A1_2_3_WHJWD,K_4_A1_2_4_GZMS,K_4_A1_2_4_KTJSWD,K_4_A1_2_4_KTCSWD,K_4_A1_2_4_WHJWD,K_4_A1_2_5_GZMS,K_4_A1_2_5_KTJSWD,K_4_A1_2_5_KTCSWD,K_4_A1_2_5_WHJWD,K_4_A1_2_6_GZMS,K_4_A1_2_6_KTJSWD,K_4_A1_2_6_KTCSWD,K_4_A1_2_6_WHJWD,K_4_A1_2_7_GZMS,K_4_A1_2_7_KTJSWD,K_4_A1_2_7_KTCSWD,K_4_A1_2_7_WHJWD,K_4_A1_2_8_GZMS,K_4_A1_2_8_KTJSWD,K_4_A1_2_8_KTCSWD,K_4_A1_2_8_WHJWD,K_4_A1_2_9_GZMS,K_4_A1_2_9_KTJSWD,K_4_A1_2_9_KTCSWD,K_4_A1_2_9_WHJWD,K_4_A1_2_10_GZMS,K_4_A1_2_10_KTJSWD,K_4_A1_2_10_KTCSWD,K_4_A1_2_10_WHJWD,K_4_A1_2_11_GZMS,K_4_A1_2_11_KTJSWD,K_4_A1_2_11_KTCSWD,K_4_A1_2_11_WHJWD,K_4_A1_2_12_GZMS,K_4_A1_2_12_KTJSWD,K_4_A1_2_12_KTCSWD,K_4_A1_2_12_WHJWD",rootId:"31",leaf:false,nodes: null, nodePage:"4ktA21"},
					{nodeTreeId:"23",ddc: "SIW_DDC1", pid:"",text:"4#A1区水泵机组监控",id:"B_4_A1_SZDZD,B_4_A1_SCQSZS,B_4_A1_YLBGZZS,B_4_A1_AGBZS,B_4_A1_AYXZS,B_4_A1_AYZGZZS,B_4_A1_BYXZS,B_4_A1_BGBZS,B_4_A1_BYZGZZS,B_4_A1_CYXZS,B_4_A1_CGBZS,B_4_A1_CYZGZZS,B_4_A1_BPGZZS,B_4_A1_ABPZS,B_4_A1_BBPZS,B_4_A1_CBPZS,B_4_A1_BPPL,B_4_A1_SDYL",rootId:"23",leaf:false,nodes: null, nodePage:"4ktA12"},
					{nodeTreeId:"24",ddc: "WW_DDC8", pid:"",text:"5#南A1区面板1水阀机组监控",id:"K_5_A1_1_1_KGJ,K_5_A1_1_1_XSBGZMS,K_5_A1_1_1_ZLJSSZWD,K_5_A1_1_1_GZBZ,K_5_A1_1_1_GZMS,K_5_A1_1_1_KTJSWD,K_5_A1_1_1_KTCSWD,K_5_A1_1_1_WHJWD,K_5_A1_1_2_GZMS,K_5_A1_1_2_KTJSWD,K_5_A1_1_2_KTCSWD,K_5_A1_1_2_WHJWD,K_5_A1_1_3_GZMS,K_5_A1_1_3_KTJSWD,K_5_A1_1_3_KTCSWD,K_5_A1_1_3_WHJWD,K_5_A1_1_4_GZMS,K_5_A1_1_4_KTJSWD,K_5_A1_1_4_KTCSWD,K_5_A1_1_4_WHJWD,K_5_A1_1_5_GZMS,K_5_A1_1_5_KTJSWD,K_5_A1_1_5_KTCSWD,K_5_A1_1_5_WHJWD,K_5_A1_1_6_GZMS,K_5_A1_1_6_KTJSWD,K_5_A1_1_6_KTCSWD,K_5_A1_1_6_WHJWD,K_5_A1_1_7_GZMS,K_5_A1_1_7_KTJSWD,K_5_A1_1_7_KTCSWD,K_5_A1_1_7_WHJWD,K_5_A1_1_8_GZMS,K_5_A1_1_8_KTJSWD,K_5_A1_1_8_KTCSWD,K_5_A1_1_8_WHJWD,K_5_A1_1_9_GZMS,K_5_A1_1_9_KTJSWD,K_5_A1_1_9_KTCSWD,K_5_A1_1_9_WHJWD,K_5_A1_1_10_GZMS,K_5_A1_1_10_KTJSWD,K_5_A1_1_10_KTCSWD,K_5_A1_1_10_WHJWD,K_5_A1_1_11_GZMS,K_5_A1_1_11_KTJSWD,K_5_A1_1_11_KTCSWD,K_5_A1_1_11_WHJWD,K_5_A1_1_12_GZMS,K_5_A1_1_12_KTJSWD,K_5_A1_1_12_KTCSWD,K_5_A1_1_12_WHJWD,K_5_A1_1_KGKZ,K_5_A1_1_MSKZ",rootId:"24",leaf:false,nodes: null, nodePage:"5ktA1"},
					{nodeTreeId:"32",ddc: "WW_DDC8", pid:"",text:"5#南A1区面板2水阀机组监控",id:"K_5_A1_2_1_KGJ,K_5_A1_2_1_XSBGZMS,K_5_A1_2_1_ZLJSSZWD,K_5_A1_2_1_GZBZ,K_5_A1_2_1_GZMS,K_5_A1_2_1_KTJSWD,K_5_A1_2_1_KTCSWD,K_5_A1_2_1_WHJWD,K_5_A1_2_2_GZMS,K_5_A1_2_2_KTJSWD,K_5_A1_2_2_KTCSWD,K_5_A1_2_2_WHJWD,K_5_A1_2_3_GZMS,K_5_A1_2_3_KTJSWD,K_5_A1_2_3_KTCSWD,K_5_A1_2_3_WHJWD,K_5_A1_2_4_GZMS,K_5_A1_2_4_KTJSWD,K_5_A1_2_4_KTCSWD,K_5_A1_2_4_WHJWD,K_5_A1_2_5_GZMS,K_5_A1_2_5_KTJSWD,K_5_A1_2_5_KTCSWD,K_5_A1_2_5_WHJWD,K_5_A1_2_6_GZMS,K_5_A1_2_6_KTJSWD,K_5_A1_2_6_KTCSWD,K_5_A1_2_6_WHJWD,K_5_A1_2_7_GZMS,K_5_A1_2_7_KTJSWD,K_5_A1_2_7_KTCSWD,K_5_A1_2_7_WHJWD,K_5_A1_2_8_GZMS,K_5_A1_2_8_KTJSWD,K_5_A1_2_8_KTCSWD,K_5_A1_2_8_WHJWD,K_5_A1_2_9_GZMS,K_5_A1_2_9_KTJSWD,K_5_A1_2_9_KTCSWD,K_5_A1_2_9_WHJWD,K_5_A1_2_10_GZMS,K_5_A1_2_10_KTJSWD,K_5_A1_2_10_KTCSWD,K_5_A1_2_10_WHJWD,K_5_A1_2_11_GZMS,K_5_A1_2_11_KTJSWD,K_5_A1_2_11_KTCSWD,K_5_A1_2_11_WHJWD,K_5_A1_2_12_GZMS,K_5_A1_2_12_KTJSWD,K_5_A1_2_12_KTCSWD,K_5_A1_2_12_WHJWD",rootId:"32",leaf:false,nodes: null, nodePage:"5ktA21"},
					{nodeTreeId:"25",ddc: "WW_DDC1", pid:"",text:"5#A1区水泵机组监控",id:"B_5_A1_SZDZD,B_5_A1_SCQSZS,B_5_A1_YLBGZZS,B_5_A1_AGBZS,B_5_A1_AYXZS,B_5_A1_AYZGZZS,B_5_A1_BYXZS,B_5_A1_BGBZS,B_5_A1_BYZGZZS,B_5_A1_CYXZS,B_5_A1_CGBZS,B_5_A1_CYZGZZS,B_5_A1_BPGZZS,B_5_A1_ABPZS,B_5_A1_BBPZS,B_5_A1_CBPZS,B_5_A1_BPPL,B_5_A1_SDYL",rootId:"25",leaf:false,nodes: null, nodePage:"5ktA12"},
					{nodeTreeId:"26",ddc: "WW_DDC2", pid:"",text:"5#中A2区面板1水阀机组监控",id:"K_5_A2_1_1_KGJ,K_5_A2_1_1_XSBGZMS,K_5_A2_1_1_ZLJSSZWD,K_5_A2_1_1_GZBZ,K_5_A2_1_1_GZMS,K_5_A2_1_1_KTJSWD,K_5_A2_1_1_KTCSWD,K_5_A2_1_1_WHJWD,K_5_A2_1_2_GZMS,K_5_A2_1_2_KTJSWD,K_5_A2_1_2_KTCSWD,K_5_A2_1_2_WHJWD,K_5_A2_1_3_GZMS,K_5_A2_1_3_KTJSWD,K_5_A2_1_3_KTCSWD,K_5_A2_1_3_WHJWD,K_5_A2_1_4_GZMS,K_5_A2_1_4_KTJSWD,K_5_A2_1_4_KTCSWD,K_5_A2_1_4_WHJWD,K_5_A2_1_5_GZMS,K_5_A2_1_5_KTJSWD,K_5_A2_1_5_KTCSWD,K_5_A2_1_5_WHJWD,K_5_A2_1_6_GZMS,K_5_A2_1_6_KTJSWD,K_5_A2_1_6_KTCSWD,K_5_A2_1_6_WHJWD,K_5_A2_1_7_GZMS,K_5_A2_1_7_KTJSWD,K_5_A2_1_7_KTCSWD,K_5_A2_1_7_WHJWD,K_5_A2_1_8_GZMS,K_5_A2_1_8_KTJSWD,K_5_A2_1_8_KTCSWD,K_5_A2_1_8_WHJWD,K_5_A2_1_9_GZMS,K_5_A2_1_9_KTJSWD,K_5_A2_1_9_KTCSWD,K_5_A2_1_9_WHJWD,K_5_A2_1_10_GZMS,K_5_A2_1_10_KTJSWD,K_5_A2_1_10_KTCSWD,K_5_A2_1_10_WHJWD,K_5_A2_1_KGKZ,K_5_A2_1_MSKZ",rootId:"26",leaf:false,nodes: null, nodePage:"5ktA2"},
					{nodeTreeId:"33",ddc: "WW_DDC2", pid:"",text:"5#中A2区面板2水阀机组监控",id:"K_5_A2_2_1_KGJ,K_5_A2_2_1_XSBGZMS,K_5_A2_2_1_ZLJSSZWD,K_5_A2_2_1_GZBZ,K_5_A2_2_1_GZMS,K_5_A2_2_1_KTJSWD,K_5_A2_2_1_KTCSWD,K_5_A2_2_1_WHJWD,K_5_A2_2_2_GZMS,K_5_A2_2_2_KTJSWD,K_5_A2_2_2_KTCSWD,K_5_A2_2_2_WHJWD,K_5_A2_2_3_GZMS,K_5_A2_2_3_KTJSWD,K_5_A2_2_3_KTCSWD,K_5_A2_2_3_WHJWD,K_5_A2_2_4_GZMS,K_5_A2_2_4_KTJSWD,K_5_A2_2_4_KTCSWD,K_5_A2_2_4_WHJWD,K_5_A2_2_5_GZMS,K_5_A2_2_5_KTJSWD,K_5_A2_2_5_KTCSWD,K_5_A2_2_5_WHJWD,K_5_A2_2_6_GZMS,K_5_A2_2_6_KTJSWD,K_5_A2_2_6_KTCSWD,K_5_A2_2_6_WHJWD,K_5_A2_2_7_GZMS,K_5_A2_2_7_KTJSWD,K_5_A2_2_7_KTCSWD,K_5_A2_2_7_WHJWD,K_5_A2_2_8_GZMS,K_5_A2_2_8_KTJSWD,K_5_A2_2_8_KTCSWD,K_5_A2_2_8_WHJWD,K_5_A2_2_9_GZMS,K_5_A2_2_9_KTJSWD,K_5_A2_2_9_KTCSWD,K_5_A2_2_9_WHJWD,K_5_A2_2_10_GZMS,K_5_A2_2_10_KTJSWD,K_5_A2_2_10_KTCSWD,K_5_A2_2_10_WHJWD",rootId:"33",leaf:false,nodes: null, nodePage:"5ktA23"},
					{nodeTreeId:"34",ddc: "WW_DDC2", pid:"",text:"5#中A2区面板3水阀机组监控",id:"K_5_A2_3_1_KGJ,K_5_A2_3_1_XSBGZMS,K_5_A2_3_1_ZLJSSZWD,K_5_A2_3_1_GZBZ,K_5_A2_3_1_GZMS,K_5_A2_3_1_KTJSWD,K_5_A2_3_1_KTCSWD,K_5_A2_3_1_WHJWD,K_5_A2_3_2_GZMS,K_5_A2_3_2_KTJSWD,K_5_A2_3_2_KTCSWD,K_5_A2_3_2_WHJWD,K_5_A2_3_3_GZMS,K_5_A2_3_3_KTJSWD,K_5_A2_3_3_KTCSWD,K_5_A2_3_3_WHJWD,K_5_A2_3_4_GZMS,K_5_A2_3_4_KTJSWD,K_5_A2_3_4_KTCSWD,K_5_A2_3_4_WHJWD,K_5_A2_3_5_GZMS,K_5_A2_3_5_KTJSWD,K_5_A2_3_5_KTCSWD,K_5_A2_3_5_WHJWD,K_5_A2_3_6_GZMS,K_5_A2_3_6_KTJSWD,K_5_A2_3_6_KTCSWD,K_5_A2_3_6_WHJWD,K_5_A2_3_7_GZMS,K_5_A2_3_7_KTJSWD,K_5_A2_3_7_KTCSWD,K_5_A2_3_7_WHJWD,K_5_A2_3_8_GZMS,K_5_A2_3_8_KTJSWD,K_5_A2_3_8_KTCSWD,K_5_A2_3_8_WHJWD,K_5_A2_3_9_GZMS,K_5_A2_3_9_KTJSWD,K_5_A2_3_9_KTCSWD,K_5_A2_3_9_WHJWD,K_5_A2_3_10_GZMS,K_5_A2_3_10_KTJSWD,K_5_A2_3_10_KTCSWD,K_5_A2_3_10_WHJWD",rootId:"34",leaf:false,nodes: null, nodePage:"5ktA24"},
					{nodeTreeId:"27",ddc: "WW_DDC7", pid:"",text:"5#A2区水泵机组监控",id:"B_5_A2_SZDZD,B_5_A2_SCQSZS,B_5_A2_AGBZS,B_5_A2_AYXZS,B_5_A2_AYZGZZS,B_5_A2_BYXZS,B_5_A2_BGBZS,B_5_A2_BYZGZZS,B_5_A2_CYXZS,B_5_A2_CGBZS,B_5_A2_CYZGZZS,B_5_A2_BPPL,B_5_A2_SDYL",rootId:"27",leaf:false,nodes: null, nodePage:"5ktA22"}
					);

            $('#ktjk_sbdy_tree').treeview({
                data : treeList, // 数据源
                highlightSelected : true, //是否高亮选中
                levels : 2,
                enableLinks : true,//必须在节点属性给出href属性
                wrapNodeText : true,
                showImage : false,
                color : "#4a4747",
                onNodeSelected : function(event, nodeData) {//节点选中事件
                    nodePage = nodeData.nodePage;
                    //ddcNames = nodeData.ddc;
                    ids = nodeData.id;
                    //加载节点对应的监控界面
                    loadJkPage(nodePage, nodeData.id);
                    selected_treeview = $('#ktjk_sbdy_tree').treeview('getSelected');
                }
            });
            if(selected_treeview == null){
                var firstNode = $("#ktjk_sbdy_tree").treeview('findNodes',[treeList[0].id,'id']);
                $("#ktjk_sbdy_tree").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
                //ddcNames = treeList[0].ddc;
                //getRvscanData();
            }
        });

        function loadJkPage(nodePage, id){

            if(!nodePage){
                return;
            }
            var variableUrl = nodePage;
            $.issp_ajax({//加载树节点信息
                isShowLoader : false,
                type : "POST",
                url : _ctx + "/view/realtimemonitoring/BESKtjz/" + variableUrl,
                success : function(returnObject) {
                    $("#ktjk2").html(returnObject);
                    dataAnalysis_ktjk2.refreshIcon(id);
                },
                error : function(returnObject) {
                    swal(returnObject.msg, "", "error");
                }
            });
        }

/*        var timer = setInterval(function () {

            var el = $('#ktjk2');

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

            runState= true;

            if(pageIsHide){
                runState= false;
                return;
            }

            if(!ddcNames || ddcNames.trim() === ''){
                setTimeout(function () {
                    getRvscanData();
                }, 10000);
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
                        updatePageData(result.data);
                        getRvscanData();
                    }else {
                        setTimeout(function () {
                            getRvscanData();
                        }, 10000)
                    }
                }
            })
            
        }*/

        function updatePageData(data) {

            if(!Array.isArray(data)){
                console.warn('data 是非法参数！');
                return;
            }

            /*            for (var i = 0; i < data.length; i++) {

                            if(data[i].f_type == "10"){//AI点
                                $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val);
                            }else if(data[i].f_type == "11"){//AO点
                                if(data[i].f_sys_name.indexOf('108')){
                                    $("#KT_" + data[i].f_sys_name).val(data[i].f_init_val);
                                }else{
                                    $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val);
                                }
                            }else if(data[i].f_type == "12"){//DI点
                                if(data[i].f_nick_name.indexOf("RunStopStatus")>-1){
                                    $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'停机':(data[i].f_init_val =='0'?'停机':(data[i].f_init_val =='1'?'开机中':(data[i].f_init_val =='2'?'停机中':'运行中'))));
                                }else if(data[i].f_nick_name.indexOf("ManualAutoStatus")>-1){
                                    $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'远程':(data[i].f_init_val !='0'?'就地':'远程'));
                                }else if(data[i].f_nick_name.indexOf("AlarmStatus")>-1){
                                    $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val !='0'?'故障':'正常'));
                                }else{
                                    $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'通风':(data[i].f_init_val == '0'?'供冷':(data[i].f_init_val == '1'?'通风':'供热')));
                                }
                            }else if(data[i].f_type == "16"){//DO点
                                if(data[i].f_nick_name.indexOf("AirUnitRunStopCmd_4_2_1")>-1){
                                    $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'关':(data[i].f_init_val =='0'?'关':'开'));
                                }else {
                                    $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'通风':(data[i].f_init_val == '0'?'供冷':(data[i].f_init_val == '1'?'通风':'供热')));
                                }

                            }
                            //$("#KT_" + data[i].f_sys_name).val(data[i].f_init_val);
                        }*/

            for (var i = 0; i < data.length; i++) {

                if(data[i].f_type == "10"){//AI点
                    $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val);
                }else if(data[i].f_type == "11"){//AO点
                    if(data[i].f_sys_name.indexOf('108')){
                        $("#KT_" + data[i].f_sys_name).val(data[i].f_init_val);
                    }else{
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val);
                    }
                }else if(data[i].f_type == "12"){//DI点
                    if(data[i].f_nick_name.indexOf("RunStopStatus")>-1){
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'停机':(data[i].f_init_val =='0'?'停机':(data[i].f_init_val =='1'?'开机中':(data[i].f_init_val =='2'?'停机中':'运行中'))));
                    }else if(data[i].f_nick_name.indexOf("ManualAutoStatus")>-1){
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'远程':(data[i].f_init_val !='0'?'就地':'远程'));
                    }else if(data[i].f_nick_name.indexOf("AlarmStatus")>-1){
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val !='0'?'故障':'正常'));
                    }else{
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'通风':(data[i].f_init_val == '0'?'供冷':(data[i].f_init_val == '1'?'通风':'供热')));
                    }
                }else if(data[i].f_type == "16"){//DO点
                    if(data[i].f_sys_name.indexOf("WorkMode")>-1 && data[i].f_sys_name.indexOf("SetWorkMode")<0){//机组工作模式反馈
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'供冷':(data[i].f_init_val == '0'?'供冷':(data[i].f_init_val == '1'?'通风':'供热')));
                    }else if(data[i].f_sys_name.indexOf("RunStopStatus")>-1){//机组运行状态
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'停机':(data[i].f_init_val == '0'?'停机':(data[i].f_init_val == '1'?'开机中':(data[i].f_init_val =='2'?'停机中':'运行中'))));
                    }else if(data[i].f_sys_name.indexOf("ManualAutoStatus")>-1){//就地远程状态
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'远程':(data[i].f_init_val == '0'?'远程':'就地'));
                    }else if(data[i].f_sys_name.indexOf("AlarmStatus")>-1){//综合故障报警
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val == '0'?'正常':'故障'));
                    }else if(data[i].f_sys_name.indexOf("SZDZD")>-1){//手自动指示
                        if(data[i].f_sys_name == 'B_1_A5_SZDZD'||data[i].f_sys_name == 'B_1_A7_SZDZD'){//A运行指示
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'关闭':(data[i].f_init_val == '0'?'关闭':'运行'));
                        }else{
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'自动':(data[i].f_init_val == '0'?'自动':'手动'));
                        }
                    }else if(data[i].f_sys_name.indexOf("SCQSZS")>-1){//水池缺水指示
                        if(data[i].f_sys_name == 'B_1_A5_SCQSZS'||data[i].f_sys_name == 'B_1_A7_SCQSZS'){//手自动指示
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'自动':(data[i].f_init_val == '0'?'自动':'手动'));
                        }else{
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val == '0'?'正常':'缺水'));
                        }
                    }else if(data[i].f_sys_name.indexOf("AYZGZZS")>-1||data[i].f_sys_name.indexOf("BYZGZZS")>-1||data[i].f_sys_name.indexOf("CYZGZZS")>-1){//A严重故障指示、B严重故障指示、C严重故障指示
                        if(data[i].f_sys_name == 'B_1_A5_AYZGZZS'||data[i].f_sys_name == 'B_1_A7_AYZGZZS'){
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val == '0'?'正常':'缺水'));
                        }else if(data[i].f_sys_name == 'B_1_A5_BYZGZZS'||data[i].f_sys_name == 'B_1_A7_BYZGZZS'){
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'运行':(data[i].f_init_val == '0'?'运行':'关闭'));
                        }else{
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val == '0'?'正常':'严重故障'));
                        }
                    }else if(data[i].f_sys_name.indexOf("AYXZS")>-1||data[i].f_sys_name.indexOf("BYXZS")>-1||data[i].f_sys_name.indexOf("CYXZS")>-1){//A运行指示、B运行指示、C运行指示
                        if(data[i].f_sys_name =="B_1_A5_AYXZS"||data[i].f_sys_name =="B_1_A7_AYXZS"){
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'运行':(data[i].f_init_val == '0'?'运行':'关闭'));
                        }else if(data[i].f_sys_name.indexOf("BYXZS")>-1||data[i].f_sys_name.indexOf("CYXZS")>-1){
                            if(data[i].f_sys_name =="B_1_A5_BYXZS"||data[i].f_sys_name =="B_1_A7_BYXZS"){
                                $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'关闭':(data[i].f_init_val == '0'?'关闭':'运行'));
                            }else{
                                $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'运行':(data[i].f_init_val == '0'?'运行':'关闭'));
                            }
                        }else if(data[i].f_sys_name=='B_1_A5_CYXZS'||data[i].f_sys_name=='B_1_A7_CYXZS'){
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val == '0'?'正常':'故障'));
                        }else{
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'关闭':(data[i].f_init_val == '0'?'关闭':'运行'));
                        }
                    }else if(data[i].f_sys_name.indexOf("AGBZS")>-1||data[i].f_sys_name.indexOf("BGBZS")>-1||data[i].f_sys_name.indexOf("CGBZS")>-1){//A关闭指示、B关闭指示、C关闭指示
                        if(data[i].f_sys_name =="B_1_A5_CGBZS"||data[i].f_sys_name =="B_1_A7_CGBZS"){
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'关闭':(data[i].f_init_val == '0'?'关闭':'运行'));
                        }else{
                            $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val == '0'?'正常':'严重故障'));
                        }
                    }else if(data[i].f_sys_name.indexOf("BPPL")>-1||data[i].f_sys_name.indexOf("SDYL")>-1){//A关闭指示、B关闭指示、C关闭指示
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val);
                    }else if(data[i].f_sys_name =="B_1_A5_YLBGZZS"||data[i].f_sys_name=="B_1_A7_YLBGZZS"){//A关闭指示（1号楼A5、A7）
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'关闭':(data[i].f_init_val == '0'?'关闭':'运行'));
                    }else if(data[i].f_sys_name.indexOf("KGJ")>-1&&data[i].f_sys_name.indexOf("KGJKZ")<0){
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'关机':(data[i].f_init_val == '0'?'关机':'开机'));
                    }else if(data[i].f_sys_name.indexOf("XSBGZMS")>-1){
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'制冷模式':(data[i].f_init_val == '1'?'制冷模式':(data[i].f_init_val == '2'?'制热模式':'手动除霜')));
                    }else if(data[i].f_sys_name.indexOf("ZLJSSZWD")>-1||data[i].f_sys_name.indexOf("KTJSWD")>-1||data[i].f_sys_name.indexOf("KTCSWD")>-1||data[i].f_sys_name.indexOf("WHJWD")>-1||data[i].f_sys_name.indexOf("KTJSWD")>-1||data[i].f_sys_name.indexOf("KTCSWD")>-1||data[i].f_sys_name.indexOf("WHJWD")>-1){
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'':(data[i].f_init_val));
                    }else if(data[i].f_sys_name.indexOf("GZBZ")>-1){
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'无故障':(data[i].f_init_val == '0'?'无故障':'有故障'));
                    }else if(data[i].f_sys_name.indexOf("GZMS")>-1){
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'关机':(data[i].f_init_val == '0'?'关机':(data[i].f_init_val == '1'?'制冷':(data[i].f_init_val =='2'?'制热':(data[i].f_init_val =='3'?'除霜运行':(data[i].f_init_val =='4'?'冬天自动防冻结':'制冷热回收'))))));
                    }else if(data[i].f_sys_name.indexOf("YLBGZZS")>-1||data[i].f_sys_name.indexOf("BPGZZS")>-1){
                    	if(data[i].f_sys_name =="B_1_A5_YLBGZZS"||data[i].f_sys_name=="B_1_A7_YLBGZZS"){
                    		$("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'关闭':(data[i].f_init_val == '0'?'关闭':'运行'));
                    	}else{
                    		$("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val == '0'?'正常':'故障'));
                    	}
                    }else if(data[i].f_sys_name.indexOf("ABPZS")>-1||data[i].f_sys_name.indexOf("BBPZS")>-1||data[i].f_sys_name.indexOf("CBPZS")>-1){
                        $("#KT_" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'工频':(data[i].f_init_val == '0'?'工频':'变频'));
                    }else{
                        $("#KT_" + data[i].f_sys_name).val(data[i].f_init_val);
                    }

                }
                //$("#KT_" + data[i].f_sys_name).val(data[i].f_init_val);
            }

        }


        return {
            refreshIcon: function(id){
                var idArray = new Array(); //定义一数组
                idArray = id.split(",");
                $.issp_ajax({//加载树节点信息
                    type : "post",
                    data:{
                        sysName: id
                    },
                    //url : _ctx + "/view/basedatamanage/eqmanage/getAllPointInfoBySysName",
                    url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
                    success : function(returnObject) {
                        // if(returnObject.status == '1'){
                            //var updateInfo = returnObject.data;
                            var updateInfo = returnObject.list;

                            updatePageData(updateInfo);
                    /*        if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){
                                for (var i = 0; i < updateInfo.length; i++) {
                                    if(updateInfo[i].f_type == "10"){//AI点
                                        $("#XF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_type == "11"){//AO点
                                        $("#szXF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_type == "12"){//DI点
                                        $("#XF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'正常':(updateInfo[i].f_init_val != '0'?'异常':'正常'));
                                    }else if(updateInfo[i].f_type == "13"){//DO点
                                        $("#XF" + updateInfo[i].f_sys_name).val(updateInfo[i].f_init_val);

                                    }
                                }
                            }*/
                        // }
                    },
                    error : function(returnObject) {
                        swal(returnObject.msg, "", "error");
                    },
                });
            },
            //刷新开关状态
            refresh : function() {
                var el = $('#ktjk2');
                if(el.length <= 0){
                    clearInterval(clock);
                    return;
                }

                if(judgeActive("ktjk2")){//页面活动时
                    //加载树节点信息
                    $.issp_ajax({
                        type : "post",
                        url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
                        data:{
                            sysName: ids
                        },
                        isShowLoader : false,
                        success : function(returnObject) {
                            if(returnObject.hasOwnProperty('list')){
                                var updateInfo = returnObject.list;
                                if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){
                                    updatePageData(updateInfo);
                                }
                            }
                        },
                        error : function(returnObject) {
                            swal(returnObject.msg, "", "error");
                        }
                    });
                }
            },
            setLampPoint: function(obj){
                var f_init_val = "";
                var pointID = obj.id.replace("KT_","");
                if(obj.value != "0"){
                    f_init_val = "255";
                }else{
                    f_init_val = "0";
                }
                $.issp_ajax({
                    url : _ctx + "/api/debugPointInfo",
                    type : "post",
                    contentType : "application/json; charset=utf-8",
                    dataType : "json",
                    //isShowLoader : true,
                    data : JSON.stringify({
                        f_sys_name : pointID,
                        f_work_mode : "1",//模式“0”自动
                        f_init_val : f_init_val,
                        tabname : "bes_digit_ouput",
                        nodeLevel : "7",//用来判定属于哪个系统(楼控系统为7，照明系统为5)
                        f_node_attribution : "1",
                    }),
                    success : function(result) {
                        if(result.status == '1'){
                            swal(result.msg, "操作成功", "success");
                            $("#KT_"+ pointID ).val(obj.value);
                        }else{
                            $("#KT_"+ pointID ).val(f_init_val);
                            swal(result.msg, "", "error");
                        }
                    },
                    error : function(result) {
                        swal(result.msg, "", "error");
                    }
                });
            },
            
              setLampVPoint: function(obj){
                var f_init_val = "";
                var pointID = obj.id.replace("KT_","");
                if(pointID.indexOf("WorkMode") != -1){//工作模式
                	if(obj.value != "1"&&obj.value != "2"){
                    	f_init_val = "0";
	                 }else if(obj.value != "0"&&obj.value != "2"){
	                    f_init_val = "1";
	                 }else{
	                 	 f_init_val = "2";
	                 }
                }else if(pointID.indexOf("AirUnitRunStopCmd") != -1||pointID.indexOf("KGJKZ") != -1 || pointID.indexOf("KGKZ") != -1){//机组启停控制 、面板开关机控制
                	 if(obj.value != "0"){
                    	f_init_val = "255";
	                 }else{
	                    f_init_val = "0";
	                 }
                }else if(pointID.indexOf("MSKZ") != -1){//面板工作模式控制
                	if(obj.value != "1"&&obj.value != "2"){
                    	f_init_val = "3";
	                 }else if(obj.value != "3"&&obj.value != "2"){
	                    f_init_val = "1";
	                 }else{
	                 	 f_init_val = "2";
	                 }
                }
                $.issp_ajax({
                    url : _ctx + "/api/debugPointInfo",
                    type : "post",
                    contentType : "application/json; charset=utf-8",
                    dataType : "json",
                    //isShowLoader : true,
                    data : JSON.stringify({
                        f_sys_name : pointID,
                        f_init_val : f_init_val,
                        tabname : "bes_vpoint",
                        nodeLevel : "7",//用来判定属于哪个系统(楼控系统为7，照明系统为5)
                        f_node_attribution : "1",
                    }),
                    success : function(result) {
                        if(result.status == '1'){
                            swal(result.msg, "操作成功", "success");
                            $("#KT_"+ pointID ).val(obj.value);
                        }else{
                            $("#KT_"+ pointID ).val(f_init_val);
                            swal(result.msg, "", "error");
                        }
                    },
                    error : function(result) {
                        swal(result.msg, "", "error");
                    }
                });
            },
            setWaterPoint: function(obj){
                var ids = obj.attributes[2].nodeValue;
                var f_init_val = "";
                var f_work_mode =  "1";
                f_init_val = $("#KT_"+ids).val();
                var f_sys_name = ids.replace('KT_',"");
                if(f_init_val>100||f_init_val<0){
                    swal("水阀输入区间错误(1-100)", "", "error");
                    $("#"+ids).val("");
                    return false;
                }
                $.issp_ajax({
                    url : _ctx + "/api/debugPointInfo",
                    type : "post",
                    contentType : "application/json; charset=utf-8",
                    dataType : "json",
                    isShowLoader : true,
                    data : JSON.stringify({
                        f_sys_name : f_sys_name,
                        f_work_mode : f_work_mode,
                        f_init_val : f_init_val,
                        f_node_attribution : "1",
                        nodeLevel: 7
                    }),
                    success : function(result) {
                        swal("操作成功", "", "success");
                    },
                    error : function(result) {
                        swal("操作失败", "", "error");
                    }
                });
            },
            setWaterPointXD: function(obj){
                var ids = obj.attributes[1].nodeValue;
                var f_init_val = "";
                //var f_work_mode =  "1";
                f_init_val = $("#KT_"+ids).val();
                var f_sys_name = ids.replace('KT_',"");
                /*if(f_init_val>100||f_init_val<0){
                    swal("水阀输入区间错误(1-100)", "", "error");
                    $("#"+ids).val("");
                    return false;
                }*/
                $.issp_ajax({
                    url : _ctx + "/api/debugPointInfoxd",
                    type : "post",
                    contentType : "application/json; charset=utf-8",
                    dataType : "json",
                    isShowLoader : true,
                    data : JSON.stringify({
                        f_sys_name : f_sys_name,
                        //f_work_mode : f_work_mode,
                        f_init_val : f_init_val,
                        f_node_attribution : "1",
                        nodeLevel: 7
                    }),
                    success : function(result) {
                        swal("操作成功", "", "success");
                    },
                    error : function(result) {
                        swal("操作失败", "", "error");
                    }
                });
            }
        }

    })(jQuery, window, document);

</script>