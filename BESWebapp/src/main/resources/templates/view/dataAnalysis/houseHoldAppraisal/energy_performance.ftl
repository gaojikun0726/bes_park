<#--
描述： 分户绩效考核能源计划分配
作者： liuzhen
-->
<style>

	thead th
	  {
	  text-align:center;
	  }
	 
	  tbody td
	  {
	  text-align:center;
	  vertical-align: middle;
	  }

    .search_energy_div_style {
        display: flex;
        align-items: center;
        margin-right: 3vh;
        float: right;
        white-space: nowrap;
        text-overflow: ellipsis;
        padding: 7px 0px 1px 0px;
    }
 .search_energy_style{
	 width:100%;
     position: relative;

 }
 
</style>
<!-- tabs样式 -->
<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
  <ul id="energy_performance_tab" class="nav tabs"></ul>
<!-- 分项树模块start -->
<div id="performance_div" class="leftarea information_left">
<div id="performance_con" class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
			aria-hidden="true"></i>&nbsp;筛选条件>>>
		</span>
	</div>
<div class="search_energy_style">

<div class="search_energy_div_style">
	<span class="zl_sxtj_span">起始时间:</span>
	<input id="energy_performance_start_time"
		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',firstDayOfWeek:1,readOnly:true})" class="input-datecheck">
</div>

<div class="search_energy_div_style">
	<span class="zl_sxtj_span">终止时间:</span>
	<input id="energy_performance_end_time" 
		onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM',firstDayOfWeek:1,readOnly:true})" class="input-datecheck">
</div>
    <div  class="search_energy_div_style" ><span class="zl_sxtj_span">请选择支路>>> </span>
        <input type="text" style="visibility: hidden;" class="input-datecheck" >
        <label style="cursor: pointer;right: 3vh;display: flex;position: absolute;">
            <input id="energyPerformanceCascade" type="checkbox" checked="checked" value="1">是否级联
        </label>
    </div>

</div>


	<div id="tree_energy_performance" style="overflow-y: auto;overflow-x: auto;width: 100%;border-top:1px solid #007ABA;"></div>
    <div id="energy_performance_bottom" style="height:5%!important;position: absolute;width:100%;bottom: 0;" >
        <div style="float: right;padding-top: 0.6vh;padding-right: 2vh;" >
            <button type="button" style="width:5vw" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_fh_energy_performance.getEnergyData()">
                <i class="fa fa-spinner"></i>&nbsp;加载数据
            </button>
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"
                    onclick="dataannlysis_fh_energy_performance.reset()">
                <i class="fa fa-refresh"></i>&nbsp;重置
            </button>

        </div>
	</div>

	</div>

<!-- 分项树模块end -->

<!-- 右侧内容模块start -->
<div class="information_right">
  <div  class="information_size" style="height:50%">
	        <!-- echarts图表 -->
	         <div class="information-model">
			<span class="tree_Subtitle">统计分析>>>
			</span>
			</div>
			<div id="energy_performance_chart" style="width: 95%; height: 90%;"></div>
   </div>
   
   <div  class="information_size" style="height:49%">
   		<div class="information-model">
			<span class="tree_Subtitle">数据展示>>>
			</span>
		<#--打印按钮-->
            <a href="javascript:void(-1);" onclick="dataannlysis_fh_energy_performance.print()" class="btn btn-primary toLeft">
                <i class="fa fa-print"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
            </a>
	    </div>
	    <div id="energy_performance_table"></div>
   </div>
</div>
<!-- 右侧内容模块模块end -->
<!-- 时间范围工具 -->
<script src="${ctx}/static/js/time_range.js"></script>
<script type="text/javascript">
;
var dataannlysis_fh_energy_performance = (function($, window, document, undefined) {
	var _fnybh='';//能源类型	
	var _ctx = '${ctx}';
    var cascadeType = true;//是否级联
    var isFistSelect = true;
    var checkedGnbh = [];//单选框选中的节点

	var energyType = "";//能源类型编号
	
	$(function(){
		
		//获取当前时间
		var date = getCurrentDate();
		//起始时间和默认时间
		$('#energy_performance_start_time').val(getFormatDate(date).substring(0,7));
		$('#energy_performance_end_time').val(getFormatDate(date).substring(0,7));  
		getTabs();
		energy_performance_tree(cascadeType,isFistSelect,checkedGnbh);
        getHeight();
	})

    function getHeight(){
        //获取左侧高度
        var allheight = $("#performance_div").height();//总高度
        var tbhbfx_con = $("#performance_con").height();
        var num = $(".search_energy_div_style").length;
        var bottom =$("#energy_performance_bottom").height();
        var tj = $(".search_energy_div_style").outerHeight()-1.2;
        var s = allheight - (num*tj) - bottom-tbhbfx_con;
        $("#tree_energy_performance").height(s);
    }
    //是否级联 点击事件
    $("#energyPerformanceCascade").click(function(){
        if($('#energyPerformanceCascade').is(':checked')) {
            cascadeType = true;
            isFistSelect = false;
            //处理点击是否级联复选框时，先获取所有被选的复选框
            checkedGnbh= [];
            checkedGnbh = $('#tree_energy_performance').treeview('getChecked');
            energy_performance_tree(cascadeType,isFistSelect,checkedGnbh);
        }else{
            cascadeType=false;
            isFistSelect = false;
            //处理点击是否级联复选框时，先获取所有被选的复选框
            checkedGnbh= [];
            checkedGnbh = $('#tree_energy_performance').treeview('getChecked');
            energy_performance_tree(cascadeType,isFistSelect,checkedGnbh);
        }
    });


	//获取tab页
	function getTabs(){
	    $.ajax({
	        type: "post",
	        url:"${ctx}/view/basedatamanage/energyinformation/getenrrgylist",
	        dataType: "json",
	        async: false,
	        data:({     
	        	f_yqbh:'0000'
			}),
	        success: function (result) {
	        	if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
	            	if(result.list.length >0){//如果包含判断是否长度大于0
                    _fnybh = result.list[0].fnybh;//默认能源类型

									energyType = _fnybh;
	            	for(var i = 0; i<result.list.length; i++){
	            		var treeNode = result.list[i];
	            		if(i == 0){ 
	            			$("#energy_performance_tab").append("<li class='active nocancel' ><a  data-toggle='tab' href='#'  data='"+treeNode.fnybh+"'>"+treeNode.fnymc+"</a></li>");
	            		}else{
	            			$("#energy_performance_tab").append("<li ><a  data-toggle='tab' href='#'   data='"+treeNode.fnybh+"'>"+treeNode.fnymc+"</a></li>");
	            		}
	            			
	            	}	            		
	            }
	            //点击tab,支路树变化 
            	$("#energy_performance_tab a").click(function(){
                    $(this.parentNode).addClass("nocancel").siblings("li").removeClass("nocancel");
            		_fnybh = $(this).attr("data");
								energyType = _fnybh;
            		 energy_performance_tree(true,false,checkedGnbh);
                    $('#tree_energy_performance').treeview('uncheckAll', { silent:true });

                });
	            }
	        },
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
	}
	
	//创建并设置table属性
	$("#energy_performance_table").tabulator({
		height:"93%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		movableColumns:true,
		columns:[
		{title:"分户名称", field:"ffhmc",width:120,sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
		{title:"节能百分比", field:"energyPercentage",width:120,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"实际总能耗", field:"fallEnegry",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"计划总能耗", field:"fplanAllEnegry",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"实际总金额(元)", field:"fallMoney",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"计划总金额(元)", field:"fplanAllMoney",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"实际人均能耗", field:"fpermanData",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"计划人均能耗", field:"fplanPermanData",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"实际人均金额(元)", field:"fpermanMoney",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"计划人均金额(元)", field:"fplanPermanMoney",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"实际二氧化碳(ppm)", field:"fco2",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"计划二氧化碳(ppm)", field:"fplanCo2",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"实际单位面积能耗", field:"funitareaData",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"计划单位面积能耗", field:"fplanUnitareaData",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"实际单位面积金额(元)", field:"funitareaMoney",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"计划单位面积金额(元)", field:"fplanUnitareaMoney",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"实际耗煤量(吨)", field:"fcoalAmount",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"计划耗煤量(吨)", field:"fplanCoalAmount",sorter:"string",editor:false,align:"center",headerSort:false},
		],
    	rowClick:function(e, row){
        	
    	},
	});
	$(window).resize(function() {
		$("#energy_performance_table").tabulator("redraw");
	});

	function getRealUnit(callback) {
		if (typeof callback !== "function"){
			return;
		}
		$.ajax({
			type    : "POST",
			url     : _ctx + "/view/dataAnalysis/eneryCollection/getUnitByEnergyType",
			dataType: "json",
			data:{
				energyType: energyType
			},
			success: function (result) {

				if (result.status == "0") {
					swal( result.msg,"", "warning");
				} else if (result.status == "1") {
					callback(result.data);
				}
			},
			error: function (result) {

				console.warn(result)
			}
		});
	}

	function pin_data(time_start, time_end, nodetreeIds,unit) {
		$.ajax({
			type: "post",
			url:"${ctx}/view/dataAnalysises/houseHoldAppraisal/getEnergyPerformanceData",
			dataType: "json",
			traditional: true,
			beforeSend: function () {
				showLoad();
			},
			data:{
				"fFhbhs":nodetreeIds,
				"fCjsj_start":time_start,
				"fCjsj_end":time_end
			},
			success: function (result)
			{
				if(result.hasOwnProperty("list"))
				{
					if(result.list.length>0)
					{
						var xdata =[];
						var series =[];



						let list = result.list;
						for (let a = 0; a < list.length; a++) {

							if (list[a].fallEnegry != null) {
								list[a].fallEnegry = list[a].fallEnegry + unit;
							} else {
								list[a].fallEnegry = '-';
							}
							if (list[a].fplanAllEnegry != null) {
								list[a].fplanAllEnegry = list[a].fplanAllEnegry	+ unit;
							} else {
								list[a].fplanAllEnegry = '-';
							}
							if (list[a].fpermanData != null) {
								list[a].fpermanData = list[a].fpermanData	+ unit;
							} else {
								list[a].fpermanData = '-';
							}
							if (list[a].fplanPermanData != null) {
								list[a].fplanPermanData = list[a].fplanPermanData	+ unit;
							} else {
								list[a].fplanPermanData = '-';
							}
							if (list[a].funitareaData != null) {
								list[a].funitareaData = list[a].funitareaData	+ unit;
							} else {
								list[a].funitareaData = '-';
							}
							if (list[a].fplanUnitareaData != null) {
								list[a].fplanUnitareaData = list[a].fplanUnitareaData	+ unit;
							} else {
								list[a].fplanUnitareaData = '-';
							}

							if (list[a].fallMoney != null) {
								list[a].fallMoney = list[a].fallMoney	;
							} else {
								list[a].fallMoney = '-';
							}

							if (list[a].fplanAllMoney != null) {
								list[a].fplanAllMoney = list[a].fplanAllMoney;
							} else {
								list[a].fplanAllMoney = '-';
							}
							if (list[a].fpermanMoney != null) {
								list[a].fpermanMoney = list[a].fpermanMoney;
							} else {
								list[a].fpermanMoney = '-';
							}
							if (list[a].fplanPermanMoney != null) {
								list[a].fplanPermanMoney = list[a].fplanPermanMoney;
							} else {
								list[a].fplanPermanMoney = '-';
							}
							if (list[a].fco2 != null) {
								list[a].fco2 = list[a].fco2;
							} else {
								list[a].fco2 = '-';
							}
							if (list[a].fplanCo2 != null) {
								list[a].fplanCo2 = list[a].fplanCo2;
							} else {
								list[a].fplanCo2 = '-';
							}
							if (list[a].funitareaMoney != null) {
								list[a].funitareaMoney = list[a].funitareaMoney;
							} else {
								list[a].funitareaMoney = '-';
							}
							if (list[a].fplanUnitareaMoney != null) {
								list[a].fplanUnitareaMoney = list[a].fplanUnitareaMoney;
							} else {
								list[a].fplanUnitareaMoney = '-';
							}
							if (list[a].fcoalAmount != null) {
								list[a].fcoalAmount = list[a].fcoalAmount;
							} else {
								list[a].fcoalAmount = '-';
							}

							if (list[a].fplanCoalAmount != null) {
								list[a].fplanCoalAmount = list[a].fplanCoalAmount;
							} else {
								list[a].fplanCoalAmount = '-';
							}
						}
						$("#energy_performance_table").tabulator('setData',list);
						//组装echarts数据
						for(var i =0;i< result.list.length;i++){
							xdata.push(result.list[i].ffhmc);
							var seriesMap = {};
							var seriesData =[];
							seriesMap['name'] ="实际总能耗";
							seriesMap['type'] = "bar";
							if(result.list[i].fallEnegry != undefined)
							{
								seriesData.push(result.list[i].fallEnegry);
							}
							seriesMap['data'] = seriesData;
							series.push(seriesMap);
							seriesData =[];
							seriesMap = {}
							seriesMap['name'] ="计划总能耗";
							seriesMap['type'] = "bar";
							seriesMap['data'] = seriesData;
							if(result.list[i].fplanAllEnegry != undefined)
							{
								seriesData.push(result.list[i].fplanAllEnegry);
							}
							series.push(seriesMap);
						}
						console.log(series);
						var newSeries =[{'name':'实际总能耗','type':'bar','data':[]},{'name':'计划总能耗','type':'bar','data':[]}];
						for(var i=0;i<series.length;i++)
						{
							if(series[i]['name']=='实际总能耗' )
							{
								var serData = series[i]['data'][0];
								newSeries[0]['data'].push(serData);
							}

							if(series[i]['name']=='计划总能耗' )
							{
								var serData = series[i]['data'][0];
								newSeries[1]['data'].push(serData);
							}

						}
						chartShow(xdata, newSeries,unit)
					}
				}
				else
				{
					swal( "没有搜索到要查找的内容","", "warning");
				}
			},
			complete: function () {
				hiddenLoad();
			},
			error: function(result) {
				swal( result.msg,"", "error");
			}
		});
	}
	return{
		//获取能源绩效数据
		getEnergyData:function()
		{
			$("#energy_performance_table").tabulator('setData',[]);	
			var time_start = $('#energy_performance_start_time').val();
			var time_end = $('#energy_performance_end_time').val();
			var nodetreeIds=[];
			var checkedGnbh = $('#tree_energy_performance').treeview('getChecked');
			//判断空和合法性处理
			if(!timeFormat())
			{
				return;	
			}
			for (var i = 0; i < checkedGnbh.length; i++) 
			{
			     var nodetreeId = checkedGnbh[i].nodeTreeId;
			     nodetreeIds.push(nodetreeId);
			    
			}
			if(nodetreeIds.length == 0)
			{
				swal( "当前未选择支路","", "warning");
			    return;
			}

			getRealUnit(function (unit) {
				//1.根据条件拼装table 2.根据条件拼装echars
				pin_data(time_start, time_end, nodetreeIds,unit);
			});

		},
		//重置功能
		reset: function()
		{
			$("#energy_performance_table").tabulator('setData',[]);	
			$('#energy_performance_start_time').val("");
			$('#energy_performance_end_time').val("");
			var dom = document.getElementById("energy_performance_chart");
			var myChart = echarts.init(dom, "light");
			myChart.clear();
            $('#tree_energy_performance').treeview('uncheckAll', { silent:true });
		},
        //打印按钮
        print :function() {
            $("#energy_performance_table").printThis({});
        }
		
	}
	
	
	
	//时间js----判断时间条件
	function timeFormat(){
		var startTime = $('#energy_performance_start_time').val();
		var endTime = $('#energy_performance_end_time').val();
		if(startTime=='' || endTime==''){
			swal( "请输入查询时间段","", "warning");
			return false; 
		}
		var date1 = new Date(startTime.replace(/-/g,"/"));
		var date2 = new Date(endTime.replace(/-/g,"/"));
		if(date2.getTime()<date1.getTime()){
			swal( "开始时间不能大于结束时间！","", "warning");
			return false;
		}
		return true;
	}
	
	
	
	//生成分户名称树
	function energy_performance_tree(cascadeType,isFistSelect,checkedGnbh){
	    $.ajax({
	        type: "post",
	        url:"${ctx}/view/basedatamanage/energyinformation/houseHold_treegrid",
	        dataType: "json",
	        async: false,
	        data:({     
	        	fNybh:_fnybh,
	        	fYqbh:""
			}),
	        success: function (result) {
	            //初始加载根节点
	            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
	            	if(result.list.length >0){//如果包含判断是否长度大于0
	            $('#tree_energy_performance').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	                showCheckbox : true,
                    hierarchicalCheck:cascadeType,//级联勾选
                    onNodeSelected: function (event, nodeData) {
	               $('#tree_energy_performance').treeview('clearSearch');//清除搜索选中高亮

	                }
	            });
					if(isFistSelect == true){
                            var firstNode = $("#tree_energy_performance").treeview('findNodes', [result.list[0].id, 'id']);
                            var node = $("#tree_energy_performance").treeview('findNodes', [firstNode[0].id, 'id']);
                            $('#tree_energy_performance').treeview('checkNode', [node, {silent: false}]);
                            for (var i = 2; i < node.length; i++) {
                                var uncheckNode = $("#tree_energy_performance").treeview('findNodes', [node[i].id, 'id']);
                                $('#tree_energy_performance').treeview('uncheckNode', [uncheckNode, {silent: false}]);
                            }
                        }
                        //点击不级联时，父级取消勾选
                        if(cascadeType == false)
                        {
                            var chechedNodes = [];
                            for (var i = 0; i <checkedGnbh.length; i++) {
                                if (checkedGnbh[i].level != 1 ) {
                                    var cNode = $('#tree_energy_performance').treeview('findNodes', [checkedGnbh[i].id, 'id']);
                                    var parentNode = $('#tree_energy_performance').treeview('findNodes',[checkedGnbh[i].pid, 'id']);
                                    chechedNodes.push(cNode);
                                    $("#tree_energy_performance").treeview('uncheckNode', [ parentNode, {silent: true}]);
                                }
                            }
                            for(var i =0;i<chechedNodes.length;i++)
                            {
                                $("#tree_energy_performance").treeview('checkNode', [ chechedNodes[i], {silent: true}]);
                            }


                        }

                        //点击级联时，父级恢复勾选
                        if(cascadeType == true  && isFistSelect == false)
                        {
                            for (var i = 0; i <checkedGnbh.length; i++) {
                                if (checkedGnbh[i].level != 1) {
                                    $("#tree_energy_performance").treeview('checkNode', [ checkedGnbh[i], {silent: true}]);
                                    var parentNode = $('#tree_energy_performance').treeview('findNodes',[checkedGnbh[i].pid, 'id']);
                                    $("#tree_energy_performance").treeview('checkNode', [ parentNode, {silent: true}]);
                                }
                            }
                            var nodes=[];
                            //去除联动勾选的数据
                            $('#tree_energy_performance').treeview('checkAll', { silent:true });
                            var cancleChecks = $('#tree_energy_performance').treeview('getChecked');

                            for(var i = 0; i < cancleChecks.length; i++){
                                var obj = cancleChecks[i];
                                var oid = obj.id;
                                var isExist = false;
                                for(var j = 0; j < checkedGnbh.length; j++){
                                    var aj = checkedGnbh[j];
                                    var nid = aj.id;
                                    if(nid == oid || obj.level ==1 ){
                                        isExist = true;
                                        break;
                                    }
                                }
                                if(!isExist){
                                    nodes.push(obj);
                                }
                            }

                            for(var i =0;i<nodes.length;i++)
                            {
                                $("#tree_energy_performance").treeview('uncheckNode', [ nodes[i], {silent: true}]);
                            }

                        }
	              }
	            }else{
	            	swal( "当前能源下暂无支路配置","", "warning");
	            	$('#tree_energy_performance').treeview({
		                data:"[]" ,         // 数据源		                
		            });
	            }
	        },
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
	}
	
	//echarts数据
	function chartShow(xdata, series,unit) {
		var dom = document.getElementById("energy_performance_chart");
		var myChart11 = echarts.init(dom, "light");
		var option = {
				color: ['#EE9201','#29AAE3','#B74AE5','#0AAF9F','#E89589','#16A085','#4A235A','#C39BD3'],
            title : {
                text:'逐时统计分析',
                left:'left',
                top:"2%",
                textStyle : {
                    color : '#8fe3f7',
                    fontSize: '14'
                },
            },
				tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
            legend: {
                type:'scroll',
                left: "10%",
                top:"2%",
                right:"3%",
                type: 'scroll',
                data:['实际总能耗','计划总能耗'],
                pageIconColor:"rgb(42, 123, 193)",
                pageFormatter: '',//隐藏翻页的数字
                pageButtonItemGap: -6,//翻页按钮的两个之间的间距
                textStyle : {
                    color : '#8fe3f7'
                }
            },
            dataZoom:[{
                type : 'inside',
                disabled : false
            }],
			    grid: {
			        left: '9%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			        	nameTextStyle : {
			        		color: '#8fe3f7',
				        },
			            type : 'category',
			            data : xdata,
			            axisLine : {
							lineStyle : {
								color : '#8fe3f7'
							}
						},
				        axisLabel: {
		                    show: true,
		                    textStyle: {
		                        color: '#8fe3f7'
		                    }
		                }
			        }
			    ],
			    yAxis : [
			        {	
			        	name: '单位:' + unit,
			        	nameTextStyle : {
			        		color: '#8fe3f7'
				        },
			            type : 'value',
			            axisLine : {
							lineStyle : {
								color : '#8fe3f7'
							}
						},
						splitLine:{
							lineStyle:{
				                 color: 'rgba(40, 76, 117, 0.2)',
				            }
						},
				        axisLabel: {
		                    show: true,
		                    textStyle: {
		                        color: '#8fe3f7'
		                    }
		                }
			        }
			    ],
			    series : series
		};
		
		if (option && typeof option === "object") {
			myChart11.setOption(option, true);
            myChart11.resize();
		}
		$(window).resize(function(){
			myChart11.resize();
            setTimeout(function(){ getHeight() },1);
		});
		
	
	}
	
		
})(jQuery, window, document);
dataannlysis_fh_energy_performance.getEnergyData();
	
				
</script>