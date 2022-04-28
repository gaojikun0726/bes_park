<link href="${ctx}/static/css/pfjk.css" rel="stylesheet">
<!-- 左侧设备树start -->
<div id="pfjk_div" class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
										aria-hidden="true"></i>&nbsp;请选择监控位置>>>
		</span>
	</div>
	<div id="pfjz_sbdy_tree" class="Information_area"></div>
</div>
<!-- 左侧设备树end -->
<!-- 右侧监控界面Start -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle"> <i class="fa fa-th-list"
									   aria-hidden="true"></i>&nbsp;监控界面>>>
			</span>
			<#--<a id="addControlInfo1" href="javascript:void(-1);" onclick="dataAnalysis_pfjk.getControlInfo()" class="btn btn-primary toLeft">
				<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>自动导入
			</a>-->
		</div>
		<div id="pfjk_page" style="height:96%;width:100%"></div>

	</div>
</div>
<!-- 右侧监控界面End -->


<script type="text/javascript">
	;
	var dataAnalysis_pfjk = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var clock = '';//定时任务返回id
		var selected_treeview = null;//treeview上选中的节点
		var tabId = "";//当前tab页的ID
		var nodePage = "";//节点对应的页面
		clock = window.setInterval("dataAnalysis_pfjk.refresh()",4000);
		var treeId = "";
		var guid = "189";
		var sbid = "3";
   /*     var pageIsHide = false; // 记录页面是否是隐藏状态
        var ddcNames = "";
        var runState = true;*/

		$(function() {
			//配置楼宇tree
			var treeList = new Array();
			var treechildList1 = new Array();
			treechildList1.push(
					{nodeTreeId:"0101",ddc: "YWA1_DDC1", pid:"01",text:"屋顶A1",id:"YWA1_DDC1_M100,YWA1_DDC1_M101,YWA1_DDC1_M102,YWA1_DDC1_M103,YWA1_DDC1_M104,YWA1_DDC1_M105,YWA1_DDC1_M106,YWA1_DDC1_M107,YWA1_DDC1_M1011,YWA1_DDC1_M1012,YWA1_DDC1_M1013,YWA1_DDC1_M1014",level:"1",leaf:true,nodePage:"pfjk1_01_01",},
                    {nodeTreeId:"0102",ddc: "YWA2_DDC3", pid:"01",text:"屋顶A2",id:"YWA2_DDC3_M100,YWA2_DDC3_M101,YWA2_DDC3_M102,YWA2_DDC3_M103,YWA2_DDC3_M104,YWA2_DDC3_M105,YWA2_DDC3_M106,YWA2_DDC3_M1011,YWA2_DDC3_M1012,YWA2_DDC3_M1013,YWA2_DDC3_M1014,YWA2_DDC3_M1015",level:"1",leaf:true,nodePage:"pfjk1_01_02",},
                    {nodeTreeId:"0103",ddc: "YWA3_DDC1", pid:"01",text:"屋顶A3",id:"YWA3_DDC1_M100,YWA3_DDC1_M101,YWA3_DDC1_M102,YWA3_DDC1_M103,YWA3_DDC1_M104,YWA3_DDC1_M105,YWA3_DDC1_M106,YWA3_DDC1_M107,YWA3_DDC1_M1011,YWA3_DDC1_M1012,YWA3_DDC1_M1013,YWA3_DDC1_M1014,YWA3_DDC1_M1015",level:"1",leaf:true,nodePage:"pfjk1_01_03",},
                    {nodeTreeId:"0104",ddc: "YWA4_DDC1", pid:"01",text:"屋顶A4",id:"YWA4_DDC1_M100,YWA4_DDC1_M101,YWA4_DDC1_M102,YWA4_DDC1_M103,YWA4_DDC1_M104,YWA4_DDC1_M105,YWA4_DDC1_M106,YWA4_DDC1_M1011,YWA4_DDC1_M1012,YWA4_DDC1_M1013,YWA4_DDC1_M1014,YWA4_DDC1_M1015,YWA4_DDC1_M200,YWA4_DDC1_M208,YWA4_DDC1_M209,YWA4_DDC1_M2010",level:"1",leaf:true,nodePage:"pfjk1_01_04",},
                    {nodeTreeId:"0105",ddc: "YWA5_DDC3", pid:"01",text:"屋顶A5",id:"YWA5_DDC3_M100,YWA5_DDC3_M101,YWA5_DDC3_M102,YWA5_DDC3_M103,YWA5_DDC3_M104,YWA5_DDC3_M105,YWA5_DDC3_M106,YWA5_DDC3_M1011,YWA5_DDC3_M1012,YWA5_DDC3_M1013,YWA5_DDC3_M1014,YWA5_DDC3_M1015",level:"1",leaf:true,nodePage:"pfjk1_01_05",},
					{nodeTreeId:"0106",ddc: "YWA6_DDC1", pid:"01",text:"屋顶A6",id:"YWA6_DDC1_M100,YWA6_DDC1_M101,YWA6_DDC1_M102,YWA6_DDC1_M103,YWA6_DDC1_M104,YWA6_DDC1_M105,YWA6_DDC1_M106,YWA6_DDC1_M1011,YWA6_DDC1_M1012,YWA6_DDC1_M1013,YWA6_DDC1_M1014,YWA6_DDC1_M1015",level:"1",leaf:true,nodePage:"pfjk1_01_06"} ,
					{nodeTreeId:"0107",ddc: "YWA7_DDC3", pid:"01",text:"屋顶A7",id:"YWA7_DDC3_M100,YWA7_DDC3_M101,YWA7_DDC3_M102,YWA7_DDC3_M103,YWA7_DDC3_M104,YWA7_DDC3_M105,YWA7_DDC3_M106,YWA7_DDC3_M1011,YWA7_DDC3_M1012,YWA7_DDC3_M1013,YWA7_DDC3_M1014,YWA7_DDC3_M1015,YWA7_DDC3_M200,YWA7_DDC3_M201,YWA7_DDC3_M202,YWA7_DDC3_M203,YWA7_DDC3_M204,YWA7_DDC3_M205,YWA7_DDC3_M206,YWA7_DDC3_M2011,YWA7_DDC3_M2012,YWA7_DDC3_M2013,YWA7_DDC3_M2014,YWA7_DDC3_M2015",level:"1",leaf:true,nodePage:"pfjk1_01_07",},
					{nodeTreeId:"0108",ddc: "YWA9_DDC1", pid:"01",text:"屋顶A9",id:"YWA9_DDC1_M100,YWA9_DDC1_M101,YWA9_DDC1_M102,YWA9_DDC1_M103,YWA9_DDC1_M104,YWA9_DDC1_M105,YWA9_DDC1_M106,YWA9_DDC1_M1011,YWA9_DDC1_M1012,YWA9_DDC1_M1013,YWA9_DDC1_M1014,YWA9_DDC1_M1015,YWA9_DDC1_M200,YWA9_DDC1_M208,YWA9_DDC1_M209,YWA9_DDC1_M2010",level:"1",leaf:true,nodePage:"pfjk1_01_08",}
			);
	/*		 var treechildList2 = new Array();
			treechildList2.push(
					{nodeTreeId:"0201",ddc: "", pid:"02",text:"西走廊吊顶排风监控",id:"EB1_DDC1_M100,EB1_DDC1_M103,EB1_DDC1_M104,EB1_DDC1_M105,EB1_DDC1_M106,EB1_DDC1_M107,EB1_DDC1_M108,EB1_DDC1_M1011,EB1_DDC1_M1012,EB1_DDC1_M1013,EB1_DDC1_M1014,EB1_DDC1_M1015,EB1_DDC1_M200,EB1_DDC1_M201,EB1_DDC1_M209,EB1_DDC1_M2010",level:"1",leaf:true,nodePage:"pfjk2_B1_01",},
					{nodeTreeId:"0202",ddc: "", pid:"02",text:"排风机房1",id:"E1F_DDC1_M100,E1F_DDC1_M103,E1F_DDC1_M104,E1F_DDC1_M105,E1F_DDC1_M106,E1F_DDC1_M107,E1F_DDC1_M108,E1F_DDC1_M1011,E1F_DDC1_M1012,E1F_DDC1_M1013,E1F_DDC1_M1014,E1F_DDC1_M1015,E1F_DDC1_M200,E1F_DDC1_M201,E1F_DDC1_M209,E1F_DDC1_M2010",level:"1",leaf:true,nodePage:"pfjk2_01_01",},
					{nodeTreeId:"0203",ddc: "", pid:"02",text:"厨房1",id:"E1F_DDC2_M100,E1F_DDC2_M103,E1F_DDC2_M104,E1F_DDC2_M105,E1F_DDC2_M106,E1F_DDC2_M107,E1F_DDC2_M108,E1F_DDC2_M1011,E1F_DDC2_M1012,E1F_DDC2_M1013,E1F_DDC2_M1014,E1F_DDC2_M1015,E1F_DDC2_M200,E1F_DDC2_M201,E1F_DDC2_M209,E1F_DDC2_M2010",level:"1",leaf:true,nodePage:"pfjk2_01_02",},
					{nodeTreeId:"0204",ddc: "", pid:"02",text:"厨房2",id:"E1F_DDC3_M100,E1F_DDC3_M103,E1F_DDC3_M104,E1F_DDC3_M105,E1F_DDC3_M106,E1F_DDC3_M107,E1F_DDC3_M108,E1F_DDC3_M1011,E1F_DDC3_M1012,E1F_DDC3_M1013,E1F_DDC3_M1014,E1F_DDC3_M1015,E1F_DDC3_M200,E1F_DDC3_M201,E1F_DDC3_M209,E1F_DDC3_M2010",level:"1",leaf:true,nodePage:"pfjk2_01_03",},
					{nodeTreeId:"0205",ddc: "", pid:"02",text:"厨房3",id:"E1F_DDC4_M100,E1F_DDC4_M103,E1F_DDC4_M104,E1F_DDC4_M105,E1F_DDC4_M106,E1F_DDC4_M107,E1F_DDC4_M108,E1F_DDC4_M1011,E1F_DDC4_M1012,E1F_DDC4_M1013,E1F_DDC4_M1014,E1F_DDC4_M1015,E1F_DDC4_M200,E1F_DDC4_M201,E1F_DDC4_M209,E1F_DDC4_M2010",level:"1",leaf:true,nodePage:"pfjk2_01_04",},
					{nodeTreeId:"0206",ddc: "", pid:"02",text:"排风机房2",id:"EF5_DDC1_M100,EF5_DDC1_M103,EF5_DDC1_M104,EF5_DDC1_M105,EF5_DDC1_M106,EF5_DDC1_M107,EF5_DDC1_M108,EF5_DDC1_M1011,EF5_DDC1_M1012,EF5_DDC1_M1013,EF5_DDC1_M1014,EF5_DDC1_M1015",level:"1",leaf:true,nodePage:"pfjk2_05_01",}//数据问题  还需修改
			);*/
			var treechildList3 = new Array();
			treechildList3.push(
					{nodeTreeId:"0301",ddc: "SA1F_DDC5", pid:"03",text:"1F南设备间",id:"SA1F_DDC5_M100,SA1F_DDC5_M108,SA1F_DDC5_M109,SA1F_DDC5_M1010",level:"1",leaf:true,nodePage:"pfjk3_01_01",},  //无数据
					{nodeTreeId:"0302",ddc: "SA1F_DDC6", pid:"03",text:"2F东布草间",id:"SA1F_DDC6_M100,SA1F_DDC6_M103,SA1F_DDC6_M104,SA1F_DDC6_M105",level:"1",leaf:true,nodePage:"pfjk3_01_02",},//无数据
					{nodeTreeId:"0303",ddc: "SA1F_DDC7", pid:"03",text:"2F南设备间",id:"SA1F_DDC7_M100,SA1F_DDC7_M1011,SA1F_DDC7_M1012,SA1F_DDC7_M1013",level:"1",leaf:true,nodePage:"pfjk3_01_03",},
					{nodeTreeId:"0304",ddc: "SA1F_DDC8", pid:"03",text:"3F东布草间",id:"SA1F_DDC8_M100,SA1F_DDC8_M1011,SA1F_DDC8_M1012,SA1F_DDC8_M1013",level:"1",leaf:true,nodePage:"pfjk3_01_04",},
					{nodeTreeId:"0305",ddc: "SA1F_DDC9", pid:"03",text:"3F南设备间",id:"SA1F_DDC9_M100,SA1F_DDC9_M108,SA1F_DDC9_M109,SA1F_DDC9_M1010",level:"1",leaf:true,nodePage:"pfjk3_01_05",},
					{nodeTreeId:"0306",ddc: "SA1F_DDC10", pid:"03",text:"4F东布草间",id:"SA1F_DDC10_M100,SA1F_DDC10_M108,SA1F_DDC10_M109,SA1F_DDC10_M1010",level:"1",leaf:true,nodePage:"pfjk3_01_06",},
					{nodeTreeId:"0307",ddc: "SA1F_DDC11", pid:"03",text:"4F南设备间",id:"SA1F_DDC11_M100,SA1F_DDC11_M108,SA1F_DDC11_M109,SA1F_DDC11_M1010",level:"1",leaf:true,nodePage:"pfjk3_01_07",}
			);
			var treechildList4 = new Array();
			treechildList4.push(
					{nodeTreeId:"0401",ddc: "SI1F_DDC2", pid:"04",text:"1F设备间",id:"SI1F_DDC2_M100,SI1F_DDC2_M1011,SI1F_DDC2_M1012,SI1F_DDC2_M1013",level:"1",leaf:true,nodePage:"pfjk4_01_01",},
					{nodeTreeId:"0402",ddc: "SI1F_DDC3", pid:"04",text:"1F南厨房排风",id:"SI1F_DDC3_M100,SI1F_DDC3_M1011,SI1F_DDC3_M1012,SI1F_DDC3_M1013",level:"1",leaf:true,nodePage:"pfjk4_01_02",},//无数据
					{nodeTreeId:"0403",ddc: "SI1F_DDC4", pid:"04",text:"1F东侧厨房",id:"SI1F_DDC4_M100,SI1F_DDC4_M101,SI1F_DDC4_M102,SI1F_DDC4_M103,SI1F_DDC4_M104,SI1F_DDC4_M105,SI1F_DDC4_M106,SI1F_DDC4_M1011,SI1F_DDC4_M1012,SI1F_DDC4_M1013,SI1F_DDC4_M1014,SI1F_DDC4_M1015",level:"1",leaf:true,nodePage:"pfjk4_01_03",},
					{nodeTreeId:"0404",ddc: "SI2F_DDC5", pid:"04",text:"2F南排风机房",id:"SI2F_DDC5_M100,SI2F_DDC5_M108,SI2F_DDC5_M109,SI2F_DDC5_M1010",level:"1",leaf:true,nodePage:"pfjk4_01_9",},// 重复
					//{nodeTreeId:"0402",ddc: "", pid:"04",text:"2F南排风机房",id:"SI1F_DDC3_M100,SI1F_DDC3_M1011,SI1F_DDC3_M1012,SI1F_DDC3_M1013",level:"1",leaf:true,nodePage:"pfjk4_01_04",},//无数据
					{nodeTreeId:"0405",ddc: "SIW_DDC7", pid:"04",text:"屋顶西南角",id:"SIW_DDC7_M100,SIW_DDC7_M101,SIW_DDC7_M103,SIW_DDC7_M104,SIW_DDC7_M105,SIW_DDC7_M106,SIW_DDC7_M1011,SIW_DDC7_M1012",level:"1",leaf:true,nodePage:"pfjk4_01_05",}, //数据有问题
					{nodeTreeId:"0406",ddc: "SIW_DDC6", pid:"04",text:"屋顶南部中间",id:"SIW_DDC6_M100,SIW_DDC6_M101,SIW_DDC6_M102,SIW_DDC6_M103,SIW_DDC6_M104,SIW_DDC6_M105,SIW_DDC6_M106,SIW_DDC6_M1011,SIW_DDC6_M1012,SIW_DDC6_M1013,SIW_DDC6_M1014,SIW_DDC6_M1015,SIW_DDC6_M200,SIW_DDC6_M201,SIW_DDC6_M203,SIW_DDC6_M204,SIW_DDC6_M205,SIW_DDC6_M206,SIW_DDC6_M2011,SIW_DDC6_M2012",level:"1",leaf:true,nodePage:"pfjk4_01_08",},
					{nodeTreeId:"0407",ddc: "SIW_DDC5", pid:"04",text:"屋顶",id:"SIW_DDC5_M100,SIW_DDC5_M101,SIW_DDC5_M102,SIW_DDC5_M103,SIW_DDC5_M104,SIW_DDC5_M105,SIW_DDC5_M106,SIW_DDC5_M1011,SIW_DDC5_M1012,SIW_DDC5_M1013,SIW_DDC5_M1014,SIW_DDC5_M1015",level:"1",leaf:true,nodePage:"pfjk4_01_06",}
					// {nodeTreeId:"0402",ddc: "", pid:"04",text:"屋顶南部中间",id:"SIW_DDC1_M103,SIW_DDC1_M104,SIW_DDC1_M105,SIW_DDC1_M106,SIW_DDC1_M107,SIW_DDC1_M108,SIW_DDC1_M1011,SIW_DDC1_M1012,SIW_DDC1_M1013,SIW_DDC1_M1014,SIW_DDC1_M208,SIW_DDC1_M209,SIW_DDC1_M2010,SIW_DDC1_M2011,SIW_DDC1_M2012,SIW_DDC1_M2013",level:"1",leaf:true,nodePage:"pfjk4_01_07",}//无数据 上边已存在
			);


			var treechildList5 = new Array();
			treechildList5.push(
					{nodeTreeId:"0501",ddc: "WW_DDC10", pid:"05",text:"屋顶DDC-E14-RF-XC5",id:"WW_DDC10_M100,WW_DDC10_M101,WW_DDC10_M102,WW_DDC10_M103,WW_DDC10_M104,WW_DDC10_M105,WW_DDC10_M106,WW_DDC10_M1011,WW_DDC10_M1012,WW_DDC10_M1013,WW_DDC10_M1014,WW_DDC10_M1015",level:"1",leaf:true,nodePage:"pfjk5_01_01",},
					{nodeTreeId:"0502",ddc: "WW_DDC6", pid:"05",text:"屋顶DDC-E14-RF-XC6",id:"WW_DDC6_M100,WW_DDC6_M101,WW_DDC6_M102,WW_DDC6_M103,WW_DDC6_M104,WW_DDC6_M105,WW_DDC6_M106,WW_DDC6_M1011,WW_DDC6_M1012,WW_DDC6_M1013,WW_DDC6_M1014,WW_DDC6_M1015",level:"1",leaf:true,nodePage:"pfjk5_01_02",},
					{nodeTreeId:"0503",ddc: "WW_DDC11", pid:"05",text:"屋顶DDC-E14-RF-XC10",id:"WW_DDC11_M100,WW_DDC11_M101,WW_DDC11_M102,WW_DDC11_M103,WW_DDC11_M104,WW_DDC11_M105,WW_DDC11_M106,WW_DDC11_M1011,WW_DDC11_M1012,WW_DDC11_M1013,WW_DDC11_M1014,WW_DDC11_M1015",level:"1",leaf:true,nodePage:"pfjk5_01_03",}//无数据
			);


			treeList.push(
					{nodeTreeId:"01",pid:"",text:"1#排风监控",id:"01",rootId:"01",leaf:false,nodes:treechildList1}/* ,
					{nodeTreeId:"02",pid:"",text:"2#排风监控",id:"02",rootId:"02",leaf:false,nodes:treechildList2}*/,
					{nodeTreeId:"03",pid:"",text:"3#排风监控",id:"03",rootId:"03",leaf:false,nodes:treechildList3},
					{nodeTreeId:"04",pid:"",text:"4#排风监控",id:"04",rootId:"04",leaf:false,nodes:treechildList4},
					{nodeTreeId:"05",pid:"",text:"5#排风监控",id:"05",rootId:"05",leaf:false,nodes:treechildList5}
			);

			$('#pfjz_sbdy_tree').treeview({
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
					treeId = nodeData.id;
					guid = nodeData.guid;
					sbid = nodeData.sbId;
					//加载节点对应的监控界面
					loadJkPage(nodePage, nodeData.id);
					selected_treeview = $('#pfjz_sbdy_tree').treeview('getSelected');
				}
			});
			if(selected_treeview == null){
				var firstNode = $("#pfjz_sbdy_tree").treeview('findNodes',[treeList[0].nodes[0].id,'id']);
				$("#pfjz_sbdy_tree").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
                //ddcNames = treeList[0].nodes[0].ddc;
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
				type : "post",
				url : _ctx + "/view/realtimemonitoring/BESPfjk/" + variableUrl,
				success : function(returnObject) {
					$("#pfjk_page").html(returnObject);
					dataAnalysis_pfjk.refreshIcon(id);
				},
				error : function(returnObject) {
					swal(returnObject.msg, "", "error");
				}
			});
		}

    /*    var timer = setInterval(function () {

            var el = $('#pfjk_page');

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
                    $("#PF" + data[i].f_sys_name).html(data[i].f_init_val);
                }else if(data[i].f_type == "11"){//AO点
                    $("#szPF" + data[i].f_sys_name).html(data[i].f_init_val);
                }else if(data[i].f_type == "12"){//DI点
                    if(data[i].f_sys_name.indexOf("103")>-1||data[i].f_sys_name.indexOf("106")>-1||data[i].f_sys_name.indexOf("1013")>-1||data[i].f_sys_name.indexOf("208")>-1||data[i].f_sys_name.indexOf("203")>-1||data[i].f_sys_name.indexOf("206")>-1||data[i].f_sys_name.indexOf("2013")>-1){
                        $("#PF" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'远程':(data[i].f_init_val !='0'?'就地':'远程'));
                    }else if(data[i].f_sys_name.indexOf("104")>-1||data[i].f_sys_name.indexOf("1011")>-1||data[i].f_sys_name.indexOf("1014")>-1||data[i].f_sys_name.indexOf("209")>-1||data[i].f_sys_name.indexOf("204")>-1||data[i].f_sys_name.indexOf("2011")>-1||data[i].f_sys_name.indexOf("2014")>-1){
                        $("#PF" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'停止':(data[i].f_init_val !='0'?'运行':'停止'));
                    }else{
                        $("#PF" + data[i].f_sys_name).html(data[i].f_init_val== undefined?'正常':(data[i].f_init_val !='0'?'故障':'正常'));
                    }


                }else if(data[i].f_type == "13"){//DO点
                    $("#PF" + data[i].f_sys_name).val(data[i].f_init_val);

                }
            }

        }

        return {
			refreshIcon: function(id){
				treeId = id ;
				$.issp_ajax({//加载树节点信息
					type : "post",
					data:{
						sysName: id
					},
					// url : _ctx + "/view/basedatamanage/eqmanage/getAllPointInfoBySysName",
					url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
					success : function(returnObject) {

                        var updateInfo = returnObject.list;

                        console.log(updateInfo)

                        updatePageData(updateInfo);
						// if(returnObject.status == '1'){
						// 	var updateInfo = returnObject.data;
					/*		if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){
								for (var i = 0; i < updateInfo.length; i++) {
									if(updateInfo[i].f_node_type == "10"){//AI点
                                        $("#PF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_node_type == "11"){//AO点
                                        $("#szPF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_node_type == "12"){//DI点
									    if(updateInfo[i].f_sys_name.indexOf("103")>-1||updateInfo[i].f_sys_name.indexOf("106")>-1||updateInfo[i].f_sys_name.indexOf("1013")>-1||updateInfo[i].f_sys_name.indexOf("208")>-1||updateInfo[i].f_sys_name.indexOf("203")>-1||updateInfo[i].f_sys_name.indexOf("206")>-1||updateInfo[i].f_sys_name.indexOf("2013")>-1){ 
											$("#PF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'远程':(updateInfo[i].f_init_val !='0'?'就地':'远程'));
										}else if(updateInfo[i].f_sys_name.indexOf("104")>-1||updateInfo[i].f_sys_name.indexOf("1011")>-1||updateInfo[i].f_sys_name.indexOf("1014")>-1||updateInfo[i].f_sys_name.indexOf("209")>-1||updateInfo[i].f_sys_name.indexOf("204")>-1||updateInfo[i].f_sys_name.indexOf("2011")>-1||updateInfo[i].f_sys_name.indexOf("2014")>-1){
											$("#PF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'停止':(updateInfo[i].f_init_val !='0'?'运行':'停止'));
										}else{
											$("#PF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'正常':(updateInfo[i].f_init_val !='0'?'故障':'正常'));
										}
                                    }else if(updateInfo[i].f_node_type == "13"){//DO点
                                        $("#PF" + updateInfo[i].f_sys_name).val(updateInfo[i].f_init_val);

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

                var el = $('#pfjk_page');
                if(el.length <= 0){
                    clearInterval(clock);
                    return;
                }

                $.issp_ajax({//加载树节点信息
                    type : "post",
                    data:{
                        sysName: treeId
                    },
					isShowLoader : false,
                    // url : _ctx + "/view/basedatamanage/eqmanage/getAllPointInfoBySysName",
                    url : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJKById",
                    success : function(returnObject) {
                        if(returnObject.status == '1'){

                            var updateInfo = returnObject.data;
                            if(updateInfo != null && (updateInfo.length != null && updateInfo.length != "")){
                                updatePageData(updateInfo);

                           /*     for (var i = 0; i < updateInfo.length; i++) {
                                    if(updateInfo[i].f_node_type == "10"){//AI点
                                        $("#PF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_node_type == "11"){//AO点
                                        $("#szPF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val);
                                    }else if(updateInfo[i].f_node_type == "12"){//DI点
									    if(updateInfo[i].f_sys_name.indexOf("103")>-1||updateInfo[i].f_sys_name.indexOf("106")>-1||updateInfo[i].f_sys_name.indexOf("1013")>-1||updateInfo[i].f_sys_name.indexOf("208")>-1||updateInfo[i].f_sys_name.indexOf("203")>-1||updateInfo[i].f_sys_name.indexOf("206")>-1||updateInfo[i].f_sys_name.indexOf("2013")>-1){ 
											$("#PF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'远程':(updateInfo[i].f_init_val !='0'?'就地':'远程'));
										}else if(updateInfo[i].f_sys_name.indexOf("104")>-1||updateInfo[i].f_sys_name.indexOf("1011")>-1||updateInfo[i].f_sys_name.indexOf("1014")>-1||updateInfo[i].f_sys_name.indexOf("209")>-1||updateInfo[i].f_sys_name.indexOf("204")>-1||updateInfo[i].f_sys_name.indexOf("2011")>-1||updateInfo[i].f_sys_name.indexOf("2014")>-1){
											$("#PF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'停止':(updateInfo[i].f_init_val !='0'?'运行':'停止'));
										}else{
											$("#PF" + updateInfo[i].f_sys_name).html(updateInfo[i].f_init_val== undefined?'正常':(updateInfo[i].f_init_val !='0'?'故障':'正常'));
										}
								
										
                                    }else if(updateInfo[i].f_node_type == "13"){//DO点
                                        $("#PF" + updateInfo[i].f_sys_name).val(updateInfo[i].f_init_val);

                                    }
                                }*/
                            }
                        }
                    },
                    error : function(returnObject) {
                        swal(returnObject.msg, "", "error");
                    },
                });
			},
			setLampPoint: function(obj){
				var f_init_val = "";
				var pointID = obj.id.replace('PF',"");
				if(obj.value != "0"){
					f_init_val = "255";
				}else{
					f_init_val = "0";
				}
				$.ajax({
					url : _ctx + "/api/debugPointInfo",
	                type : "post",
	                contentType : "application/json; charset=utf-8",
	                dataType : "json",
                    data : JSON.stringify({
						f_sys_name : pointID,
						f_work_mode : "1",//模式“0”自动
						f_init_val : f_init_val,
						tabname : "bes_digit_ouput",
						nodeLevel : "7",//用来判定属于哪个系统(楼控系统为7，照明系统为5)
						f_node_attribution : "1",
                    }),
					success : function(result) {
						swal("操作成功", "", "success");
					},
					error : function(result) {
						swal("操作失败", "", "error");
					}
				});
			},
			setWaterPoint: function(obj){
				var ids = obj.attributes[2].nodeValue;
                var f_init_val = "";
                var f_work_mode =  "1";
                var f_sys_name = $("#"+ids).val();
                if(f_sys_name>100||f_sys_name<0){
                	swal("水阀输入区间错误(1-100)", "", "error");
                	$("#"+ids).val("");
                	return false;
                }
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
                        swal("操作成功", "", "success");
                    },
                    error : function(result) {
                        swal("操作失败", "", "error");
                    }
                });
            }
		}

	})(jQuery, window, document);
	//dataAnalysis_pfjk.pageInit();
</script>