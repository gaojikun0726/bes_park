<link href="${ctx}/static/css/xfjk.css" rel="stylesheet">
<!-- 左侧设备树start -->
<div id="xfjk_div" class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
										aria-hidden="true"></i>&nbsp;请选择监控位置>>>
		</span>
	</div>
	<div id="xfjz_sbdy_tree" class="Information_area"></div>
</div>
<!-- 左侧设备树end -->
<!-- 右侧监控界面Start -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle"> <i class="fa fa-th-list"
									   aria-hidden="true"></i>&nbsp;监控界面>>>
			</span>
			<#--<a id="addControlInfo1" href="javascript:void(-1);" onclick="dataAnalysis_xfjk.getControlInfo()" class="btn btn-primary toLeft">
				<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>自动导入
			</a>-->
		</div>
		<div id="xfjk_page" style="height:96%;width:100%"></div>

	</div>
</div>
<!-- 右侧监控界面End -->


<script type="text/javascript">
	;
	var dataAnalysis_xfjk = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var clock = '';//定时任务返回id
		var selected_treeview = null;//treeview上选中的节点
		var tabId = "";//当前tab页的ID
		var nodePage = "";//节点对应的页面
		clock = window.setInterval("dataAnalysis_xfjk.refresh()",4000);
     /*   var pageIsHide = false; // 记录页面是否是隐藏状态
        var ddcNames = "";
        var runState = true;*/

        //配置楼宇tree
        var treeList = new Array();
        $(function() {
			var treechildList1 = new Array();
			treechildList1.push(
					{nodeTreeId:"0101",ddc: "Y1F_DDC1", pid:"01",text:"Y1F_DDC1-1F新风机房",id:"Y1F_DDC1_M100,Y1F_DDC1_M103,Y1F_DDC1_M104,Y1F_DDC1_M105,Y1F_DDC1_M106,Y1F_DDC1_M107,Y1F_DDC1_M108,Y1F_DDC1_M1011,Y1F_DDC1_M1012,Y1F_DDC1_M1013,Y1F_DDC1_M1014,Y1F_DDC1_M1015,Y1F_DDC1_M200,Y1F_DDC1_M201,Y1F_DDC1_M2010,Y1F_DDC1_M2011",level:"1",leaf:true,nodePage:"xfjk1_01_01",},
					{nodeTreeId:"0102",ddc: "Y1F_DDC2", pid:"01",text:"Y1F_DDC2A5-1F新风机房",id:"Y1F_DDC2_M100,Y1F_DDC2_M103,Y1F_DDC2_M104,Y1F_DDC2_M105,Y1F_DDC2_M106,Y1F_DDC2_M107,Y1F_DDC2_M108,Y1F_DDC2_M1011,Y1F_DDC2_M1012,Y1F_DDC2_M1013,Y1F_DDC2_M1014,Y1F_DDC2_M1015,Y1F_DDC2_M200,Y1F_DDC2_M201,Y1F_DDC2_M2010,Y1F_DDC2_M2011",level:"1",leaf:true,nodePage:"xfjk1_01_02",},
					{nodeTreeId:"0103",ddc: "Y2F_DDC1", pid:"01",text:"Y2F_DDC1新风机房",id:"Y2F_DDC1_M100,Y2F_DDC1_M103,Y2F_DDC1_M104,Y2F_DDC1_M105,Y2F_DDC1_M106,Y2F_DDC1_M107,Y2F_DDC1_M108,Y2F_DDC1_M1011,Y2F_DDC1_M1012,Y2F_DDC1_M1013,Y2F_DDC1_M1014,Y2F_DDC1_M1015,Y2F_DDC1_M200,Y2F_DDC1_M201,Y2F_DDC1_M2010,Y2F_DDC1_M2011",level:"1",leaf:true,nodePage:"xfjk1_02_01",},
					{nodeTreeId:"0104",ddc: "Y2F_DDC2", pid:"01",text:"Y2F_DDC2A3-2F新风机房",id:"Y2F_DDC2_M100,Y2F_DDC2_M103,Y2F_DDC2_M104,Y2F_DDC2_M105,Y2F_DDC2_M106,Y2F_DDC2_M107,Y2F_DDC2_M108,Y2F_DDC2_M1011,Y2F_DDC2_M1012,Y2F_DDC2_M1013,Y2F_DDC2_M1014,Y2F_DDC2_M1015,Y2F_DDC2_M200,Y2F_DDC2_M201,Y2F_DDC2_M2010,Y2F_DDC2_M2011",level:"1",leaf:true,nodePage:"xfjk1_02_02",},
					{nodeTreeId:"0105",ddc: "Y2F_DDC3", pid:"01",text:"Y2F_DDC3A4-2F新风机房",id:"Y2F_DDC3_M100,Y2F_DDC3_M103,Y2F_DDC3_M104,Y2F_DDC3_M105,Y2F_DDC3_M106,Y2F_DDC3_M107,Y2F_DDC3_M108,Y2F_DDC3_M1011,Y2F_DDC3_M1012,Y2F_DDC3_M1013,Y2F_DDC3_M1014,Y2F_DDC3_M1015,Y2F_DDC3_M200,Y2F_DDC3_M201,Y2F_DDC3_M2010,Y2F_DDC3_M2011",level:"1",leaf:true,nodePage:"xfjk1_02_03",},
					{nodeTreeId:"0106",ddc: "Y2F_DDC4", pid:"01",text:"Y2F_DDC4A6-2F新风机房",id:"Y2F_DDC4_M100,Y2F_DDC4_M103,Y2F_DDC4_M104,Y2F_DDC4_M105,Y2F_DDC4_M106,Y2F_DDC4_M107,Y2F_DDC4_M108,Y2F_DDC4_M1011,Y2F_DDC4_M1012,Y2F_DDC4_M1013,Y2F_DDC4_M1014,Y2F_DDC4_M1015,Y2F_DDC4_M200,Y2F_DDC4_M201,Y2F_DDC4_M2010,Y2F_DDC4_M2011",level:"1",leaf:true,nodePage:"xfjk1_02_04",},
					{nodeTreeId:"0107",ddc: "Y2F_DDC5", pid:"01",text:"Y2F_DDC5A7-2F新风机房",id:"Y2F_DDC5_M100,Y2F_DDC5_M103,Y2F_DDC5_M104,Y2F_DDC5_M105,Y2F_DDC5_M106,Y2F_DDC5_M107,Y2F_DDC5_M108,Y2F_DDC5_M1011,Y2F_DDC5_M1012,Y2F_DDC5_M1013,Y2F_DDC5_M1014,Y2F_DDC5_M1015,Y2F_DDC5_M200,Y2F_DDC5_M201,Y2F_DDC5_M2010,Y2F_DDC5_M2011",level:"1",leaf:true,nodePage:"xfjk1_02_05",},
					{nodeTreeId:"0108",ddc: "Y2F_DDC6", pid:"01",text:"Y2F_DDC6A8-2F新风机房",id:"Y2F_DDC6_M100,Y2F_DDC6_M103,Y2F_DDC6_M104,Y2F_DDC6_M105,Y2F_DDC6_M106,Y2F_DDC6_M107,Y2F_DDC6_M108,Y2F_DDC6_M1011,Y2F_DDC6_M1012,Y2F_DDC6_M1013,Y2F_DDC6_M1014,Y2F_DDC6_M1015,Y2F_DDC6_M200,Y2F_DDC6_M201,Y2F_DDC6_M2010,Y2F_DDC6_M2011",level:"1",leaf:true,nodePage:"xfjk1_02_06",},
					{nodeTreeId:"0109",ddc: "Y2F_DDC7", pid:"01",text:"Y2F_DDC7A9-2F新风机房",id:"Y2F_DDC7_M100,Y2F_DDC7_M103,Y2F_DDC7_M104,Y2F_DDC7_M105,Y2F_DDC7_M106,Y2F_DDC7_M107,Y2F_DDC7_M108,Y2F_DDC7_M1011,Y2F_DDC7_M1012,Y2F_DDC7_M1013,Y2F_DDC7_M1014,Y2F_DDC7_M1015,Y2F_DDC7_M200,Y2F_DDC7_M201,Y2F_DDC7_M2010,Y2F_DDC7_M2011",level:"1",leaf:true,nodePage:"xfjk1_02_07",}
			);
			var treechildList2 = new Array();
			treechildList2.push(
					{nodeTreeId:"0201",ddc: "EB1_DDC1", pid:"02",text:"西走廊吊顶新风监控",id:"EB1_DDC1_M100,EB1_DDC1_M103,EB1_DDC1_M104,EB1_DDC1_M105,EB1_DDC1_M106,EB1_DDC1_M107,EB1_DDC1_M108,EB1_DDC1_M1011,EB1_DDC1_M1012,EB1_DDC1_M1013,EB1_DDC1_M1014,EB1_DDC1_M1015,EB1_DDC1_M200,EB1_DDC1_M201,EB1_DDC1_M2010,EB1_DDC1_M2011",level:"1",leaf:true,nodePage:"xfjk2_B1_01",},
					{nodeTreeId:"0202",ddc: "E1F_DDC1", pid:"02",text:"新风机房1",id:"E1F_DDC1_M100,E1F_DDC1_M103,E1F_DDC1_M104,E1F_DDC1_M105,E1F_DDC1_M106,E1F_DDC1_M107,E1F_DDC1_M108,E1F_DDC1_M1011,E1F_DDC1_M1012,E1F_DDC1_M1013,E1F_DDC1_M1014,E1F_DDC1_M1015,E1F_DDC1_M200,E1F_DDC1_M201,E1F_DDC1_M2011,E1F_DDC1_M2010",level:"1",leaf:true,nodePage:"xfjk2_01_01",},
					{nodeTreeId:"0203",ddc: "E1F_DDC2", pid:"02",text:"厨房1",id:"E1F_DDC2_M100,E1F_DDC2_M103,E1F_DDC2_M104,E1F_DDC2_M105,E1F_DDC2_M106,E1F_DDC2_M107,E1F_DDC2_M108,E1F_DDC2_M1011,E1F_DDC2_M1012,E1F_DDC2_M1013,E1F_DDC2_M1014,E1F_DDC2_M1015,E1F_DDC2_M200,E1F_DDC2_M201,E1F_DDC2_M2011,E1F_DDC2_M2010",level:"1",leaf:true,nodePage:"xfjk2_01_02",},
					{nodeTreeId:"0204",ddc: "E1F_DDC3", pid:"02",text:"厨房2",id:"E1F_DDC3_M100,E1F_DDC3_M103,E1F_DDC3_M104,E1F_DDC3_M105,E1F_DDC3_M106,E1F_DDC3_M107,E1F_DDC3_M108,E1F_DDC3_M1011,E1F_DDC3_M1012,E1F_DDC3_M1013,E1F_DDC3_M1014,E1F_DDC3_M1015,E1F_DDC3_M200,E1F_DDC3_M201,E1F_DDC3_M2011,E1F_DDC3_M2010",level:"1",leaf:true,nodePage:"xfjk2_01_03",},
					{nodeTreeId:"0205",ddc: "E1F_DDC4", pid:"02",text:"厨房3",id:"E1F_DDC4_M100,E1F_DDC4_M103,E1F_DDC4_M104,E1F_DDC4_M105,E1F_DDC4_M106,E1F_DDC4_M107,E1F_DDC4_M108,E1F_DDC4_M1011,E1F_DDC4_M1012,E1F_DDC4_M1013,E1F_DDC4_M1014,E1F_DDC4_M1015,E1F_DDC4_M200,E1F_DDC4_M201,E1F_DDC4_M2011,E1F_DDC4_M2010",level:"1",leaf:true,nodePage:"xfjk2_01_04",},
					{nodeTreeId:"0206",ddc: "EF5_DDC1", pid:"02",text:"新风机房2",id:"EF5_DDC1_M100,EF5_DDC1_M103,EF5_DDC1_M104,EF5_DDC1_M105,EF5_DDC1_M106,EF5_DDC1_M107,EF5_DDC1_M108,EF5_DDC1_M1011,EF5_DDC1_M1012,EF5_DDC1_M1013,EF5_DDC1_M1014,EF5_DDC1_M1015,EF5_DDC1_M200,EF5_DDC1_M201,EF5_DDC1_M2010,EF5_DDC1_M2011",level:"1",leaf:true,nodePage:"xfjk2_05_01",}//数据问题  还需修改
			);
			var treechildList3 = new Array();
			treechildList3.push(
					{nodeTreeId:"0301",ddc: "SA1F_DDC1", pid:"03",text:"SA1F_DDC1西北新风机房",id:"SA1F_DDC1_M100,SA1F_DDC1_M103,SA1F_DDC1_M104,SA1F_DDC1_M105,SA1F_DDC1_M106,SA1F_DDC1_M107,SA1F_DDC1_M108,SA1F_DDC1_M1011,SA1F_DDC1_M1012,SA1F_DDC1_M1013,SA1F_DDC1_M1014,SA1F_DDC1_M1015,SA1F_DDC1_M200,SA1F_DDC1_M201,SA1F_DDC1_M2010,SA1F_DDC1_M2011",level:"1",leaf:true,nodePage:"xfjk3_01_01",},
					{nodeTreeId:"0302",ddc: "SA1F_DDC2", pid:"03",text:"SA1F_DDC2西南新风机房",id:"SA1F_DDC2_M100,SA1F_DDC2_M103,SA1F_DDC2_M104,SA1F_DDC2_M105,SA1F_DDC2_M106,SA1F_DDC2_M107,SA1F_DDC2_M108,SA1F_DDC2_M1011,SA1F_DDC2_M1012,SA1F_DDC2_M1013,SA1F_DDC2_M1014,SA1F_DDC2_M1015,SA1F_DDC2_M200,SA1F_DDC2_M201,SA1F_DDC2_M2011,SA1F_DDC2_M2010",level:"1",leaf:true,nodePage:"xfjk3_01_02",},
					{nodeTreeId:"0303",ddc: "SA1F_DDC3", pid:"03",text:"SA1F_DDC3东布草间",id:"SA1F_DDC3_M100,SA1F_DDC3_M103,SA1F_DDC3_M104,SA1F_DDC3_M105,SA1F_DDC3_M106,SA1F_DDC3_M107,SA1F_DDC3_M108,SA1F_DDC3_M1011,SA1F_DDC3_M1012,SA1F_DDC3_M1013,SA1F_DDC3_M1014,SA1F_DDC3_M1015,SA1F_DDC3_M200,SA1F_DDC3_M201,SA1F_DDC3_M2011,SA1F_DDC3_M2010",level:"1",leaf:true,nodePage:"xfjk3_01_03",}
			);
			var treechildList4 = new Array();
			treechildList4.push(
					{nodeTreeId:"0401",ddc: "SI1F_DDC1", pid:"04",text:"SI1F_DDC1厕所",id:"SI1F_DDC1_M100,SI1F_DDC1_M103,SI1F_DDC1_M104,SI1F_DDC1_M105,SI1F_DDC1_M106,SI1F_DDC1_M107,SI1F_DDC1_M108,SI1F_DDC1_M1011,SI1F_DDC1_M1012,SI1F_DDC1_M1013,SI1F_DDC1_M1014,SI1F_DDC1_M1015,SI1F_DDC1_M200,SI1F_DDC1_M201,SI1F_DDC1_M2011,SI1F_DDC1_M2010",level:"1",leaf:true,nodePage:"xfjk4_01_01",},
					{nodeTreeId:"0402",ddc: "SI2F_DDC1", pid:"04",text:"SI2F_DDC12F新风机房",id:"SI2F_DDC1_M100,SI2F_DDC1_M103,SI2F_DDC1_M104,SI2F_DDC1_M105,SI2F_DDC1_M106,SI2F_DDC1_M107,SI2F_DDC1_M108,SI2F_DDC1_M1011,SI2F_DDC1_M1012,SI2F_DDC1_M1013,SI2F_DDC1_M1014,SI2F_DDC1_M1015,SI2F_DDC1_M200,SI2F_DDC1_M201,SI2F_DDC1_M2011,SI2F_DDC1_M2010",level:"1",leaf:true,nodePage:"xfjk4_02_01",}
			);
			var treechildList5 = new Array();
			treechildList5.push(
					{nodeTreeId:"0501",ddc: "W1F_DDC1", pid:"05",text:"W1F_DDC1新风机房1-1南",id:"W1F_DDC1_M100,W1F_DDC1_M103,W1F_DDC1_M104,W1F_DDC1_M105,W1F_DDC1_M106,W1F_DDC1_M107,W1F_DDC1_M108,W1F_DDC1_M1011,W1F_DDC1_M1012,W1F_DDC1_M1013,W1F_DDC1_M1014,W1F_DDC1_M1015,W1F_DDC1_M200,W1F_DDC1_M201,W1F_DDC1_M2011,W1F_DDC1_M2010",level:"1",leaf:true,nodePage:"xfjk5_01_01",},
					{nodeTreeId:"0502",ddc: "W1F_DDC2", pid:"05",text:"W1F_DDC2新风机房1-3北",id:"W1F_DDC2_M100,W1F_DDC2_M103,W1F_DDC2_M104,W1F_DDC2_M105,W1F_DDC2_M106,W1F_DDC2_M107,W1F_DDC2_M108,W1F_DDC2_M1011,W1F_DDC2_M1012,W1F_DDC2_M1013,W1F_DDC2_M1014,W1F_DDC2_M1015,W1F_DDC2_M200,W1F_DDC2_M201,W1F_DDC2_M2011,W1F_DDC2_M2010",level:"1",leaf:true,nodePage:"xfjk5_01_02",},
					{nodeTreeId:"0503",ddc: "W2F_DDC1", pid:"05",text:"W2F_DDC1新风机房2-1南",id:"W2F_DDC1_M100,W2F_DDC1_M103,W2F_DDC1_M104,W2F_DDC1_M105,W2F_DDC1_M106,W2F_DDC1_M107,W2F_DDC1_M108,W2F_DDC1_M1011,W2F_DDC1_M1012,W2F_DDC1_M1013,W2F_DDC1_M1014,W2F_DDC1_M1015,W2F_DDC1_M200,W2F_DDC1_M201,W2F_DDC1_M2013,W2F_DDC1_M2014",level:"1",leaf:true,nodePage:"xfjk5_02_01",},
					{nodeTreeId:"0504",ddc: "W2F_DDC2", pid:"05",text:"W2F_DDC2新风机房2-2北",id:"W2F_DDC2_M100,W2F_DDC2_M103,W2F_DDC2_M104,W2F_DDC2_M105,W2F_DDC2_M106,W2F_DDC2_M107,W2F_DDC2_M108,W2F_DDC2_M1011,W2F_DDC2_M1012,W2F_DDC2_M1013,W2F_DDC2_M1014,W2F_DDC2_M1015,W2F_DDC2_M200,W2F_DDC2_M201,W2F_DDC2_M2011,W2F_DDC2_M2010",level:"1",leaf:true,nodePage:"xfjk5_02_02",},
					{nodeTreeId:"0505",ddc: "W2F_DDC3", pid:"05",text:"W2F_DDC3新风机房2-3中",id:"W2F_DDC3_M100,W2F_DDC3_M103,W2F_DDC3_M104,W2F_DDC3_M105,W2F_DDC3_M106,W2F_DDC3_M107,W2F_DDC3_M108,W2F_DDC3_M1011,W2F_DDC3_M1012,W2F_DDC3_M1013,W2F_DDC3_M1014,W2F_DDC3_M1015,W2F_DDC3_M200,W2F_DDC3_M201,W2F_DDC3_M2011,W2F_DDC3_M2010",level:"1",leaf:true,nodePage:"xfjk5_02_03",},
					{nodeTreeId:"0506",ddc: "W3F_DDC1", pid:"05",text:"W3F_DDC1新风机房3-1北",id:"W3F_DDC1_M100,W3F_DDC1_M103,W3F_DDC1_M104,W3F_DDC1_M105,W3F_DDC1_M106,W3F_DDC1_M107,W3F_DDC1_M108,W3F_DDC1_M1011,W3F_DDC1_M1012,W3F_DDC1_M1013,W3F_DDC1_M1014,W3F_DDC1_M1015,W3F_DDC1_M200,W3F_DDC1_M201,W3F_DDC1_M2011,W3F_DDC1_M2010",level:"1",leaf:true,nodePage:"xfjk5_03_01",},
					{nodeTreeId:"0507",ddc: "W3F_DDC2", pid:"05",text:"W3F_DDC2新风机房3-2中",id:"W3F_DDC2_M100,W3F_DDC2_M103,W3F_DDC2_M104,W3F_DDC2_M105,W3F_DDC2_M106,W3F_DDC2_M107,W3F_DDC2_M108,W3F_DDC2_M1011,W3F_DDC2_M1012,W3F_DDC2_M1013,W3F_DDC2_M1014,W3F_DDC2_M1015,W3F_DDC2_M200,W3F_DDC2_M201,W3F_DDC2_M2011,W3F_DDC2_M2010",level:"1",leaf:true,nodePage:"xfjk5_03_02",},
					{nodeTreeId:"0508",ddc: "W3F_DDC3", pid:"05",text:"W3F_DDC3新风机房3-3南",id:"W3F_DDC3_M100,W3F_DDC3_M103,W3F_DDC3_M104,W3F_DDC3_M105,W3F_DDC3_M106,W3F_DDC3_M107,W3F_DDC3_M108,W3F_DDC3_M1011,W3F_DDC3_M1012,W3F_DDC3_M1013,W3F_DDC3_M1014,W3F_DDC3_M1015,W3F_DDC3_M200,W3F_DDC3_M201,W3F_DDC3_M2011,W3F_DDC3_M2010",level:"1",leaf:true,nodePage:"xfjk5_03_03",},
					{nodeTreeId:"0509",ddc: "W4F_DDC1", pid:"05",text:"W4F_DDC1新风机房4-1南",id:"W4F_DDC1_M100,W4F_DDC1_M103,W4F_DDC1_M104,W4F_DDC1_M105,W4F_DDC1_M106,W4F_DDC1_M107,W4F_DDC1_M108,W4F_DDC1_M1011,W4F_DDC1_M1012,W4F_DDC1_M1013,W4F_DDC1_M1014,W4F_DDC1_M1015,W4F_DDC1_M200,W4F_DDC1_M201,W4F_DDC1_M2011,W4F_DDC1_M2010",level:"1",leaf:true,nodePage:"xfjk5_04_01",},
					{nodeTreeId:"0510",ddc: "W4F_DDC2", pid:"05",text:"W4F_DDC2新风机房4-2中",id:"W4F_DDC2_M100,W4F_DDC2_M103,W4F_DDC2_M104,W4F_DDC2_M105,W4F_DDC2_M106,W4F_DDC2_M107,W4F_DDC2_M108,W4F_DDC2_M1011,W4F_DDC2_M1012,W4F_DDC2_M1013,W4F_DDC2_M1014,W4F_DDC2_M1015,W4F_DDC2_M200,W4F_DDC2_M201,W4F_DDC2_M2011,W4F_DDC2_M2010",level:"1",leaf:true,nodePage:"xfjk5_04_02",},
					{nodeTreeId:"0511",ddc: "W4F_DDC3", pid:"05",text:"W4F_DDC3新风机房4-3北",id:"W4F_DDC3_M100,W4F_DDC3_M103,W4F_DDC3_M104,W4F_DDC3_M105,W4F_DDC3_M106,W4F_DDC3_M107,W4F_DDC3_M108,W4F_DDC3_M1011,W4F_DDC3_M1012,W4F_DDC3_M1013,W4F_DDC3_M1014,W4F_DDC3_M1015,W4F_DDC3_M200,W4F_DDC3_M201,W4F_DDC3_M2011,W4F_DDC3_M2010",level:"1",leaf:true,nodePage:"xfjk5_04_03",}
			);
			treeList.push(
					{nodeTreeId:"01", pid:"",text:"1#新风监控",id:"01",rootId:"01",leaf:false,nodes:treechildList1},
					{nodeTreeId:"02", pid:"",text:"2#新风监控",id:"02",rootId:"02",leaf:false,nodes:treechildList2},
					{nodeTreeId:"03", pid:"",text:"3#新风监控",id:"03",rootId:"03",leaf:false,nodes:treechildList3},
					{nodeTreeId:"04", pid:"",text:"4#新风监控",id:"04",rootId:"04",leaf:false,nodes:treechildList4},
					{nodeTreeId:"05", pid:"",text:"5#新风监控",id:"05",rootId:"05",leaf:false,nodes:treechildList5}
			);

			$('#xfjz_sbdy_tree').treeview({
				data : treeList, // 数据源
				highlightSelected : true, //是否高亮选中
				levels : 2,
				enableLinks : true,//必须在节点属性给出href属性
				wrapNodeText : true,
				showImage : false,
				color : "#4a4747",
				onNodeSelected : function(event, nodeData) {//节点选中事件
                    // ddcNames = nodeData.ddc;
					nodePage = nodeData.nodePage;
					ids = nodeData.id;
					//加载节点对应的监控界面
					loadJkPage(nodePage, nodeData.id);
					selected_treeview = $('#xfjz_sbdy_tree').treeview('getSelected');
				}
			});
			if(selected_treeview == null){
				var firstNode = $("#xfjz_sbdy_tree").treeview('findNodes',[treeList[0].nodes[0].id,'id']);
				$("#xfjz_sbdy_tree").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
                // ddcNames = treeList[0].nodes[0].ddc;
                // getRvscanData();
			}
		});

		function loadJkPage(nodePage, id){

            if(!nodePage){
                return;
            }

			var variableUrl = nodePage;
			$.issp_ajax({//加载树节点信息
				isShowLoader : false,
				type : "post",
				url : _ctx + "/view/realtimemonitoring/BESXfjk/" + variableUrl,
				success : function(returnObject) {
					$("#xfjk_page").html(returnObject);
					dataAnalysis_xfjk.refreshIcon(id);
				},
				error : function(returnObject) {
					swal(returnObject.msg, "", "error");
				}
			});
		}

       /* var timer = setInterval(function () {

            var el = $('#xfjk_page');

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

            for (var i = 0; i < data.length; i++) {
                if(data[i].f_type == "10"){//AI点
                    $("#XF" + data[i].f_sys_name).html(data[i].f_init_val);
                }else if(data[i].f_type == "11"){//AO点
                    if(data[i].f_sys_name.indexOf('108')){
                        $("#XF" + data[i].f_sys_name).val(data[i].f_init_val);
                    }
                }else if(data[i].f_type == "12"){//DI点
                    if(data[i].f_sys_name.indexOf('106')>-1||data[i].f_sys_name.indexOf('1015')>-1){
                        $("#XF" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'未报警':(data[i].f_init_val != '0'?'报警':'未报警'));
                    }else if(data[i].f_sys_name.indexOf('2010')>-1||data[i].f_sys_name.indexOf('2011')>-1 ){
                        $("#XF" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'无':(data[i].f_init_val != '0'?'到位':'无'));
                    }else if(data[i].f_sys_name.indexOf('104')>-1){ //运行状态
                        $("#XF" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'运行':(data[i].f_init_val != '0'?'运行':'停止'));
                    }else if(data[i].f_sys_name.indexOf('105')>-1){//故障状态
                        $("#XF" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val != '0'?'故障':'正常'));
                    }else if(data[i].f_sys_name.indexOf('107')>-1){
                        $("#XF" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'无':(data[i].f_init_val != '0'?'有':'无'));
                    }else{
                        $("#XF" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'远程':(data[i].f_init_val != '0'?'就地':'远程'));
                    }
                }else if(data[i].f_type == "13"){//DO点
                    $("#XF" + data[i].f_sys_name).val(data[i].f_init_val);
                }
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
					// url : _ctx + "/view/basedatamanage/eqmanage/getAllPointInfoBySysName",
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
					success : function(returnObject) {
						// if(returnObject.status == '1'){
						// 	var updateInfo = returnObject.data;
                        var updateInfo = returnObject.list;

                        updatePageData(updateInfo);
							/*if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){
								for (var i = 0; i < updateInfo.length; i++) {
									if(updateInfo[i].f_type == "10"){//AI点
										$("#XF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val);
									}else if(updateInfo[i].f_type == "11"){//AO点
										if(updateInfo[i].f_sys_name.indexOf('108')){
											$("#XF" + updateInfo[i].f_sys_name).val(updateInfo[i].f_init_val);
										}
									}else if(updateInfo[i].f_type == "12"){//DI点
										if(updateInfo[i].f_sys_name.indexOf('106')>-1||updateInfo[i].f_sys_name.indexOf('1015')>-1){
											$("#XF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'未报警':(updateInfo[i].f_init_val != '0'?'报警':'未报警'));
										}else if(updateInfo[i].f_sys_name.indexOf('2010')>-1||updateInfo[i].f_sys_name.indexOf('2011')>-1 ){
											$("#XF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'无':(updateInfo[i].f_init_val != '0'?'到位':'无'));
										}else if(updateInfo[i].f_sys_name.indexOf('104')>-1){ //运行状态
										    $("#XF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'运行':(updateInfo[i].f_init_val != '0'?'运行':'停止'));
										}else if(updateInfo[i].f_sys_name.indexOf('105')>-1){//故障状态
										    $("#XF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'正常':(updateInfo[i].f_init_val != '0'?'故障':'正常'));
										}else if(updateInfo[i].f_sys_name.indexOf('107')>-1){
										    $("#XF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'无':(updateInfo[i].f_init_val != '0'?'有':'无'));
										}else{
											$("#XF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'远程':(updateInfo[i].f_init_val != '0'?'就地':'远程'));
										}
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

                var el = $('#xfjk_page');
                if(el.length <= 0){
                    clearInterval(clock);
                    return;
                }

                if(judgeActive("xfjk_page")){//页面活动时
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
				var pointID = obj.id.replace("XF","");

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
							$("#XF"+ pointID ).val(obj.value);
						}else{
							$("#XF"+ pointID ).val(f_init_val);
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
				f_init_val = $("#"+ids).val();
				var f_sys_name = ids.replace('XF',"");
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
			}/*,
			pageInit : function(){
				dataAnalysis_xfjk.refreshIcon('EB1_DDC1_M1,EB1_DDC1_M2');
			}*/
		}

	})(jQuery, window, document);

	//dataAnalysis_xfjk.RefreshNew();
</script>