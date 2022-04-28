<!-- 左侧设备树start -->
<div id="branch_energymonitoring_div" class="leftarea information_left">
	<div class="information-model">
		<span class="tree_branch_energymonitoring"> <i class="fa fa-share-alt"
			aria-hidden="true"></i>&nbsp;请选择支路>>>
		</span>
	</div>
	<div id="branch_energymonitoring_tree" class="Information_area"></div>
</div>
<!-- 左侧设备树end -->
<!-- 右侧监控界面Start -->
<div class="information_right">
<!-- 	<div class="information_size"> -->
<!-- 		<div class="information-model"> -->
<!-- 			<span class="Subtitle"> <i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;监控界面>>> </span> -->
<!-- 		</div> -->
<!-- 		<div id="ammeterdataTable" class="Information_area"></div> -->
	<div class="information_size"  style="height: 49%;margin-bottom: 3px;border-bottom: solid 2px #366c90;">
	   <div class="information-model">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;统计分析>>>
			</span>
		</div>
		<div id="ammeter_data_echars" style="height: 90%;width: 95%;" ></div>
   </div>
   <!---table---->  
   <div class="information_size" style="height: 48%;border-bottom: solid 0px #366c90;"><!-- 列表展示 -->
     	<div class="information-model">
     		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;数据展示>>>
			</span>
     	</div>
		<div id="ammeterdataTable" class="Information_area"></div>
	</div>
<!-- 	</div> -->
</div>
<!-- 右侧监控界面End -->
<style>

</style>
<script type="text/javascript">
;
var dataAnalysis_energymonitoring = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var selectedzlbh="";//选中的支路编号
	
	//创建并设置table属性
	$("#ammeterdataTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:70,formatter:"rownum",frozen:false,align:"center",sorter:"string"}, 
		{title:"支路名称", field:"F_ZLMC",sorter:"string",editor:false,align:"center",headerSort:false}, 
		{title:"电表名称", field:"F_NICK_NAME", sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"采集参数", field:"F_DNMC", sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"参数值", field:"F_DN_DATA",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"采集时间", field:"F_CJSJ",sorter:"string",editor:false,align:"center",headerSort:false},
		],
    	rowClick:function(e, row){
        	       	
    	},
	});
    var clock = '';//定时任务返回id
    clock = window.setInterval("dataAnalysis_energymonitoring.refreshData()",300000);
	//根据支路加载对应的电表数据
	function queryAmmeterData(selectedzlbh,isShowLoader){
		$.issp_ajax({
			type: "post",
            url: _ctx + "/view/realtimemonitoring/BESEnergyMonitoring/queryAmmeterData",
            data:{
                "f_zlbh":selectedzlbh,
            },
	        dataType: "json",
            isShowLoader:isShowLoader,
	        success: function (result) {
	        	if (result != undefined) {
                    $("#ammeterdataTable").tabulator("setData", []);
					$("#ammeterdataTable").tabulator("setData", result.list);
					
				}
	        }
		})
		
	}
	function loadlinechart(selectedzlbh,isShowLoader){
		$.issp_ajax({
			type: "post",
            url: _ctx + "/view/realtimemonitoring/BESEnergyMonitoring/loadAmmeterlinechart",
            data:{
                "f_zlbh":selectedzlbh,
            },
	        dataType: "json",
            isShowLoader:isShowLoader,
	        success: function (data) {
                showLineChart(data);
	        }
		})
		
	}

    //加载折线图
    function showLineChart(data){
        var dom = document.getElementById("ammeter_data_echars");
        var myChart = echarts.init(dom, "light");
        myChart.clear();
        if(data.hasOwnProperty("map")){
            data.data=data.map.data;
        }else{
            data.data=[];
        }
        if(data.hasOwnProperty("list") && data.list.length > 0)
		{
            for(var i =0;i<data.list.length;i++)
            {
                if(data.list[i].data!=null && data.map.data != null)
                    for(var j =0;j<data.list[i].data.length;j++)
                    {
                        var num1 =data.map.data[j];
                        var num2 =data.list[i].data[j];
                        if(j== data.map.data.length-1)
                        {
                            num2 =" ";
                        }

                        data.list[i].data[j]=[parseInt(num1),parseFloat(num2)];
                    }
            }
		}



        $('#ammeter_data_echars').setLineData({
            'title': "趋势分析",//data.data[0].fDcmc+"趋势分析",
            'title_fontSize':16,
            'title_color':'#8fe3f7',
            //'xData': 'fcjst',
            //'legendMap': m,
            // 'barGap' : 0,
            'title_x' : 'left',
            'trigger' : 'axis',
            'areaStyle' : null,//区域填充
            'showSeriesLable' : false,//折点上是否显示数据
            'boundaryGap' : false,
            'gridRight' : '8%',//右边距离
            'gridLeft' : '8%',//左边距离
			'xType':'time',
            'splitNumber':'24',
            'xAxis_interval' : 'auto',
            'line_color' : ['#fe5958','#ff80fd','#fdfe1f'] ,//折线颜色
            'dataZoom_disabled' : false,//鼠标滚轮
            'yaxisLine_color' : '#8fe3f7',//y轴线条颜色
            'xaxisLine_color' : '#8fe3f7',//x轴线条颜色
        },data);
    }



	
	return {
         refreshData:function()
         {
			queryAmmeterData(selectedzlbh,false);
			loadlinechart(selectedzlbh,false);
         },
		//加载树
		gettree_branch : function () {
            if($("#branch_energymonitoring_div").length==0){//页面关闭后
                clearInterval(clock); //清除js定时器
                return;
            };
		    $.issp_ajax({
		        type: "post",
	            url: _ctx + "/view/basedatamanage/energyinformation/branchalert_tree",
	            data:{
	                "fYqbh":"",
	                "fNybh":"01000"
	            },
		        dataType: "json",
		        success: function (result) {
		        	if(result.list == undefined){
		        		$('#branch_energymonitoring_tree').treeview('remove');
		        	}else{
		            $('#branch_energymonitoring_tree').treeview({
		                data: result.list,         // 数据源
		                highlightSelected: true,    //是否高亮选中
		                levels : 2,
		                enableLinks : true,//必须在节点属性给出href属性
		                color: "#4a4747",
		               onNodeSelected: function (event, nodeData) {
		               $('#branch_energymonitoring_tree').treeview('clearSearch');//清除搜索选中高亮
	 	               		selectedzlbh = nodeData.id;
                           queryAmmeterData(selectedzlbh,null);
                           loadlinechart(selectedzlbh,null);

// 		               		Selected_branch = $('#branch_energymonitoring_tree').treeview('getSelected');
		               		
		                }

		            });

                        if(result.hasOwnProperty("list")&&result.list.length>0)
                        {
                            var firstNode = $("#branch_energymonitoring_tree").treeview('findNodes',[result.list[0].nodes[0].id,'id']);
                            $("#branch_energymonitoring_tree").treeview("selectNode", firstNode);
							queryAmmeterData(result.list[0].nodes[0].id,null);
							loadlinechart(result.list[0].nodes[0].id,null);

                        }
		        	};
		        },
		        error: function (nodeData) {
		            swal( nodeData.msg,"", "error");
		        }
		    });
		},
		
		pageInit : function(){
			dataAnalysis_energymonitoring.gettree_branch();
			
		}
	}
	})(jQuery, window, document);
dataAnalysis_energymonitoring.pageInit();
</script>