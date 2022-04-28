<style>
/* 左侧树结构 */
.left_tree {
	position: fixed;
	width: 35%;
	z-index: 980;
	transition: all .5s ease;
	user-select: none;
	transition: all .3s ease;
	overflow-y: auto;
	overflow-x: auto;
	height: 61%;
	border-bottom: 1px solid #366c90;
}

.monitoringhome_zzjgtree {
	width: 30px;
	height: 8em;
	padding-top: 17px;
	cursor: pointer;
	position: absolute;
	top: 52%;
	margin-top: -66px;
	z-index: 980;
	font-size: 14px;
	color: #fff;
	-webkit-border-radius: 15px 0px 0px 15px;
	-moz-border-radius: 15px 0px 0px 15px;
	-o-border-radius: 15px 0px 0px 15px;
	border-radius: 0px 10px 10px 0px;
}

.monitoringhome_zzjgtree>span {
	display: block;
	padding-left: 4px;
}
/* li */
.first_li {
	text-align: center;
	padding: 10px;
	font-size: 1vw;
	font-weight: 600;
}

.left_div {
	padding: 10px 0 0 0;
	width: 46%;
	float: left;
	text-align: right;
}

.div_bottom_left_div {
	padding: 10px 0 0 0;
	margin-left: -2px;
	width: 58%;
	float: left;
	text-align: right;
}

.right_div {
	padding: 10px 0 0 2px;
	width: 54%;
	float: left;
	text-align: left;
}

.left_div_span {
	display: block;
	margin: 0 0 -12px 0;
}
.left_div_span_nd{
    display: block;
    margin: 0 0 -7px 0;
    
}

.main-index-back {
	position: absolute;
	right: 2vh;
	top: 1vh;
	background: #337ab7;
	border: 1px solid #b19223;
	color: white;
	height: 3.8vh;
	width: 13vh;
	box-shadow: -3px 4px 10px #000000;
}

.gzt_backmapcharacters {
	font-size: 14px;
	font-family: "Inkfree";
	font-weight: bold;
}
</style>

<div style="width: 100%; height: 100%; background-image: url(static/images/backImg5.jpg); background-size: 100% 100%;">
	<div style="width: 100%; height: 100%; padding-right: 20px">
		<!-----左侧区域---->
		<div style="width: 80%; height: 100%; float: left;">
			<!-----左侧上半部区域---->
			<div style="width: 100%; height: 70%; float: left;">
				<div style="width: 20%; height: calc(100% - 20px); float: left; padding-right: 20px;">
					<div class="div-border" id="leftTop1" style="width: 100%; height: 55%;">
						<div class="div_top" style="width: 100%; height: 70%; border-bottom: 1px solid #02dabc80;">
							<li class="first_li ">实验用例</li>
							<div class="left_div" style="width:50%;">
								<span class="left_div_span">节点编码 : </span></br> <span class="left_div_span">建筑物年代 : </span></br> <span class="left_div_span">建筑物人口 : </span></br> <span class="left_div_span">建筑物面积
									: </span></br> <span style="display: block;">系统已运行 : </span></br>
							</div>
							<div class="right_div" style="width:50%;">
								<span id="" class="left_div_span" style="color: #E0D674"> 370103B016</span></br> <span id="" class="left_div_span" style="color: #E0D674"> 2016 <span style="color: #8fe3f7">年</span></span></br>
								<span id="" class="left_div_span" style="color: #E0D674"> 1500 <span style="color: #8fe3f7">人</span>
								</span></br> <span id="" class="left_div_span" style="color: #E0D674"> 22万 <span style="color: #8fe3f7">平米</span>
								</span></br> <span style="display: block; color: #E0D674;" id="gzt_runingTime"> ${day}<span style="color: #8fe3f7">天</span> <span style="color: #E0D674;">${hour} <span
										style="color: #8fe3f7">小时</span></span>
								</span>
							</div>
						</div>
						<div class="div_bottom" style="width: 100%;">
							<div class="div_bottom_left_div">
								<span class="left_div_span">今日单位面积能耗 : </span></br> <span class="left_div_span">今日人均能耗 : </span>
							</div>
							<div class="right_div" style="width: 42%">
								<span class="left_div_span" style="color: #E0D674;" id="gzt_fUnitareaData"><span style="color: #8fe3f7"></span></span></br> <span class="left_div_span" style="color: #E0D674;"
									id="gzt_fPermanData"><span style="color: #8fe3f7"></span></span>
							</div>
						</div>
					</div>
					<!-- 填充能耗 -->
					<div class="div-border" style="width: 100%; height: 45%;">
						<div class="div_top" style="width: 100%;">
							<ul>
								<li class="first_li">年度总耗能</li>
							</ul>
						</div>
						<div class="left_div" style="padding: 3px 0 0 10px;">
                            <span class="left_div_span_nd">总耗能 : </span></br> <span class="left_div_span_nd">照明总耗能 : </span></br> <span class="left_div_span_nd">空调总耗能 : </span></br> <span class="left_div_span_nd">动力总耗能:
                            </span></br> <span class="left_div_span_nd">特殊总耗能 : </span></br>
                        </div>
                        <div class="right_div" style="padding: 3px 0 0 10px;">
                            <span class="left_div_span_nd" style="color: #39E6C9;" id="gzt_year_alldata">0.00 Kw </span></br> <span class="left_div_span_nd" style="color: #E0D674;" id="gzt_year_zmdata">0.00
                                Kw </span></br> <span class="left_div_span_nd" style="color: #37a2da;">0.00 Kwh </span></br> <span class="left_div_span_nd" style="color: #39E6C9;">0.00 Kwh </span></br> <span
                                class="left_div_span_nd" style="color: #E0D674;">0.00 Kwh </span></br>
                        </div>
					</div>
				</div>

				<div class="map-border" style="width: calc(80% - 20px); height: calc(100% - 10px); float: left; position: relative;">
					<!--左侧树结构 -->
					<div class="monitoringhome_zzjgtree">
						<span>楼</span><span>宇</span><span>结</span><span>构</span>
					</div>
					<div class="left_tree">
						<div id="tree_zzjg_CM" class="tree_zzjg_CM"></div>
					</div>

					<div id="allmap" style="width: 100%; height: 100%; padding-left: 20px;"></div>
					<div id="gzt_ndznh" style="height: 52vh;box-shadow: -5px 7px 8px black;background-color: #104e84;position: absolute; top: 5vh; right: 1vh; width: 35vh; border: 1px solid #366c90;">
						<div class="div-border1" style="width: 100%; height: 50%;">
							<div class="div_top" style="width: 100%;">
								<ul>
									<li class="first_li">能耗分析</li>
									<div class="left_div" style="width:50;">
                                    <span class="left_div_span">今日耗能 : </span></br> <span class="left_div_span">瞬时功率 : </span></br> <span class="left_div_span">峰值能耗 : </span></br> <span class="left_div_span">上年能耗对比
                                        : </span></br> <span class="left_div_span">上月能耗对比 : </span></br> <span class="left_div_span">昨日能耗对比 : </span></br>
	                                </div>
	                                <div class="right_div" style="width:50%;">
	                                    <span class="left_div_span" style="color: #39E6C9;" id="gzt_jrhn"></span></br>
	                                    <span class="left_div_span" style="color: #E0D674;" id="gzt_instantaneous"></span></br>
	                                    <span class="left_div_span" style="color: #37a2da;" id="gzt_peak"></span></br>
	                                    <span class="left_div_span" style="color: #39E6C9;" id="gzt_LastYearData"></span></br>
	                                    <span class="left_div_span" style="color: #E0D674;" id="gzt_LastMonthData"></span></br>
	                                    <span class="left_div_span" style="color: #37a2da;" id="gzt_yesterdayData"></span></br>
	                                </div>
								</ul>
							</div>
						</div>
						<#--今日趋势图 -->
                        <div id="today_tendency" class="div-border1" style="width: 100%; height: 50%;">
                        </div>
					</div>
				</div>
			</div>

			<!-----左侧下半部区域---->
			<div style="width: 100%; height: calc(30% - 20px); float: left;">

				<div class="div-border" id="leftTop" style="width: calc(20% - 20px); height: 100%; float: left; padding-left: 10px;"></div>
				<div class="div-border" id="chart1" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
				<div class="div-border" id="chart2" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
				<div class="div-border" id="chart3" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
				<div class="div-border" id="chart4" style="width: calc(20% - 20px); height: 100%; float: left;"></div>
			</div>

		</div>

		<!-----右侧区域---->
		<div style="width: 20%; height: calc(100% - 50px); float: left;">
			<div id="sbzt1" class="div-border" style="width: 100%; height: 25%;"></div>
			<div id="sbzt2" class="div-border" style="width: 100%; height: 25%;"></div>
			<div id="sbzt3" class="div-border" style="width: 100%; height: 25%;"></div>
			<div id="sbzt4" class="div-border" style="width: 100%; height: 25%;"></div>
		</div>


	</div>
</div>
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
var _ctx = '${ctx}';
var nodeId = "";//组织结构树ID
//点击组织结构 展开/收起
$(function(){
	var left_cols_mark = 0 ;
	zzjg_tree();
	$(".left_tree").hide();
	$(".monitoringhome_zzjgtree").click(function(){
		if(left_cols_mark == 1){
			left_cols_mark = 0;
			$(".left_tree").hide();
			$(".monitoringhome_zzjgtree").css("margin-left","");
		}else{
			left_cols_mark = 1;
			$(".left_tree").show();
			$(".monitoringhome_zzjgtree").css("margin-left","24.3%");

		}
	});
})
//加载树	组织机构
function zzjg_tree(){
	    //电的分户 id="0001"
	$.ajax({
	    type: "post",
	    url: "${ctx}/view/basedatamanage/energyinformation/houseHold_treegrid",
	    dataType: "json",
	    async: false,
	    data: ({
	        fNybh: "0001",
	        fYqbh: ""
	    }),
	    success: function(result) {
	        //初始加载根节点
	        if (result.hasOwnProperty("list")) {
	            if (result.list.length > 0) {
	                $('#tree_zzjg_CM').treeview({
	                    data: result.list,
	                    highlightSelected: true,
	                    levels: 4,
	                    enableLinks: true,
	                    allowReselect : true,//允许重复点击
	                    color: "#4a4747",
	                    onNodeSelected(event, nodeData) { //节点点击事件
	                        nodeId = nodeData.id;
	                        SearchData(nodeId);//月度能耗
	                        TodayTendency(nodeId);//今日总趋势图
	                        illumination(nodeId,nodeData.text);//照明环比
	                        illuminationReal(nodeId);//照明实时数据
	                        energyAnalyze(nodeId);//能耗分析
	                        leftRefresh(nodeId);//左侧数据对接
	                    },
	                });
	                var firstNode = $("#tree_zzjg_CM").treeview('findNodes', [result.list[0].id, 'id']);
	                $("#tree_zzjg_CM").treeview("selectNode", firstNode); //将第一个节点设置为选中状态
	            }
	        } else {
	            $('#tree_zzjg_CM').treeview({
	                data: "[]",
	                // 数据源
	            });
	        }
	    },
	    error: function(nodeData) {
	        swal(nodeData.msg, "", "error");
	    }
	});
	}
	
	//根据组织机构选中节点查询能耗数据
	function SearchData(nodeId){
		$.issp_ajax({
		    type: "post",
		    url: "${ctx}/view/dataAnalysises/getHouseHoldData",
		    dataType: "json",
		    async: true,
		    traditional: true,//组织深度序列化 
		    data: {
		        "fType": "2",//月
		        "fCjsj_start":getFormatDate(getCurrentYear()[0]),
		        "fCjsj_end": getFormatDate(getCurrentYear()[1]),
		        "fFhbhs": nodeId
		    },
		    success: function(result) {
		    	var res = result.map;
		    	var arr = res.time;
		    	//echars所需数据
		    	var fCjsj=[];
		    	var fData=[];
		    	//填充左侧展示数据
		    	var todayData = "";
		    	//循环
		    	for(var a=0;a<arr.length;a++){
		    		var sj = arr[a].fCjsj.substr(5,2);
		    		fCjsj.push(sj+"月");
		    		fData.push(arr[a].fData || 0);
		    	}
		    	leftTop(fCjsj,fData);//总耗能
    	    }
		});
	}
	//今日总趋势图
	function TodayTendency(nodeId){
        var today = getFormatDate(getCurrentDate());
        $.issp_ajax({
            type: "post",
            url: "${ctx}/view/dataAnalysises/getHouseHoldData",
            dataType: "json",
            async: true,
            traditional: true,
            data: {
                "fType": "0",//时
                "fCjsj_start": today,
                "fCjsj_end": today,
                "fFhbhs": nodeId
            },
            success: function(result) {
                var res = result.map;
                var arr = res.time;
                //echars所需数据
                var fCjsj=[];
                var fData=[];
                //填充左侧展示数据
                var todayData = "";
                //循环
                for(var a=0;a<arr.length;a++){
                    var sj = arr[a].fCjsj.substr(11,2);
                    fCjsj.push(sj);
                    fData.push(arr[a].fData || 0);
                }
                today_tendency(fCjsj,fData);
            },
        });
    }
	function change_tqtime(start,end){
		var res = new Array();
        if (start != null && end != null) {
            var t = 2 * start.getTime() - end.getTime();
            if (start.getTime() == end.getTime()) {
                start.setTime(start.getTime() - 24 * 60 * 60 * 1000);
                end.setTime(start.getTime());
            } else {
                end.setTime(start.getTime());
                start.setTime(t);
            }
            res.push(getFormatDate(start));
            res.push(getFormatDate(end));
        }
        return res;
    }
	//照明 环比
	function  illumination(nodeId,nodeText) {
		//环比--本月--月--月数据对比
		var time = getCurrentMonth();
        var fCjsj_start = getFormatDate(time[0]);
        var fCjsj_end = getFormatDate(time[1]);
        var ftbhb_sametime_start= "";
        var ftbhb_sametime_end = "";
        var res = change_tqtime(time[0],time[1]);
        if(res.lenght!=0){
        	ftbhb_sametime_start = res[0];
        	ftbhb_sametime_end = res[1];
        }
		$.issp_ajax({
		    type: "post",
		    url: "${ctx}/view/basedatamanage/workbench/illumination",
		    dataType: "json",
		    async: true,
		    traditional: true,
		    data: ({
		        "fType": "2",//月
		        "fCjsj_start": fCjsj_start,
		        "fCjsj_end": fCjsj_end,
		        "ftbhb_sametime_start": ftbhb_sametime_start,
		        "ftbhb_sametime_end": ftbhb_sametime_end,
		        "fId": nodeId,
		        "fFhbhmc":nodeText
		    }),
		    success: function(result) {
		    	var legend = result.data[0].legend;
		    	var seriesData = result.data[0].seriesData;
		    	var timeData = result.data[0].timeData;
		    	nxfx1(legend,seriesData,timeData);
		    }
		});
	}
	//刷新左侧数据--和地图中间数据
	function leftRefresh(nodeId){
		//上年 上月  昨日--当天--本年
		var taday = getFormatDate(getCurrentDate());//今天
		var yesterday = getFormatDate(getPreviousDate());//昨天
		var year = getFormatDate(getCurrentDate()).substr(0,4);//本年
		var lastyear = getFormatDate(getPreviousYear()[0]).substr(0,4);//上年
		var month = getFormatDate(getCurrentMonth()[0]).substr(5,2);//本月
        var lastmonth = getFormatDate(getPreviousMonth()[0]).substr(5,2);//上月
		$.issp_ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/gztLeftRefresh",
            dataType: "json",
            async: true,
            traditional: true,//组织深度序列化 
            data: {
                "fType": "1",//天
                "fCjsj_start":taday,
                "fCjsj_end": getFormatDate(getCurrentDate()).substr(0,4),//传本年时间 ---2018 
                "fId": nodeId,
                //----分割 request
                "taday":taday,
                "yesterday":yesterday,
                "year":year,
                "lastyear":lastyear,
                "month":month,
                "lastmonth":lastmonth,
                "isControl":"1",//默认开启查询瞬时功率  0关闭 1开启
                "nhbh":"0001002"//能耗编号 写死 功率
            },
            success: function(result) {
            	if(result.hasOwnProperty("list")){
            		var obj = result.list[0];
            	    var fUnitareaData=obj.fUnitareaData;
            	    var fPermanData=obj.fPermanData;
            	    var fAlldata = obj.fAlldata;
            	    $("#gzt_fUnitareaData").empty().text(fUnitareaData+" Kw/平");
            	    $("#gzt_fPermanData").empty().text(fPermanData+" Kw/人");
            	    $("#gzt_year_alldata").empty().text(fAlldata+" Kwh");//总能耗
            	    $("#gzt_year_zmdata").empty().text(fAlldata+" Kwh");//照明
            	}
            	if(result.hasOwnProperty("data")){
            		var data = result.data[0];
            		var LastMonthData = data.LastMonthData;
            		var lastMonthContrast = data.lastMonthContrast;
            		var LastYearData = data.LastYearData;
            		var LastYearContrast = data.LastYearContrast;
            		var yesterdayData = data.yesterdayData;
            		var yesterdayDataContrast = data.yesterdayDataContrast;
            		var peakEnergy = data.peakEnergy;
            		var InstantPower = data.InstantPower;
            		$("#gzt_instantaneous").empty().append("<span style='color: #E0D674;'>"+InstantPower+" Kw</span>");//瞬时功率
            		$("#gzt_peak").empty().append("<span style='color: #E0D674;'>"+peakEnergy+" Kw</span>");//峰值
            		$("#gzt_LastYearData").empty().append("<span style='color: #E0D674;'>"+LastYearData+" Kwh</span>");//上月
            		$("#gzt_LastMonthData").empty().append("<span style='color: #E0D674;'>"+LastMonthData+" Kwh</span>");//上月
            		$("#gzt_yesterdayData").empty().append("<span style='color: #E0D674;'>"+yesterdayData+" Kwh</span>");//昨天
            	}
            }
        });
	}
	//照明实时数据
	function illuminationReal(nodeId){
        var today = getFormatDate(getCurrentDate());
        $.issp_ajax({
            type: "post",
            url: "${ctx}/view/dataAnalysises/getHouseHoldData",
            dataType: "json",
            async: true,
            traditional: true,
            data: {
                "fType": "0",//时
                "fCjsj_start": today,
                "fCjsj_end": today,
                "fFhbhs": nodeId
            },
            success: function(result) {
                var res = result.map;
                var arr = res.time;
                //echars所需数据
                var fCjsj=[];
                var fData=[];
                //填充左侧展示数据
                var todayData = "";
                //循环
                for(var a=0;a<arr.length;a++){
                    var sj = arr[a].fCjsj.substr(11,2);
                    fCjsj.push(sj);
                    fData.push(arr[a].fData || 0);
                }
                sbzt1(fCjsj,fData);//总耗能
            }
        });
    }
	//能耗分析
	function energyAnalyze(nodeId){
		var today = getFormatDate(getCurrentDate());
        $.issp_ajax({
            type: "post",
            url: "${ctx}/view/basedatamanage/workbench/energyAnalyze",
            dataType: "json",
            async: true,
            traditional: true,
            data: {
                "fType": "1",//天
                "fCjsj_start": today,
                "fCjsj_end": today,
                "fId": nodeId
            },
            success: function(result) {
            	var obj = result.list[0];
            	var data = obj.data;
            	$("#gzt_jrhn").text(data+" Kwh");
            }
        });
	}
	
	
	
	initBaiduMap();
	
	nxfx2();
	nxfx3();
	nxfx4();
	
	sbzt2();
	sbzt3();
	sbzt4();
	
</script>