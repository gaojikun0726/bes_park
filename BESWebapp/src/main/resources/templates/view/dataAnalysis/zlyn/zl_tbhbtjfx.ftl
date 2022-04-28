<link href="${ctx}/static/css/sjfx_tabs.css" rel="stylesheet">
<style type="text/css">
.zl_tbhbfx{
    display: flex;
    align-items: center;
    margin-right: 3vh;
    float:right;
    white-space: nowrap;
    text-overflow: ellipsis;
    padding: 7px 0px 1px 0px;
}
.treeview span.icon {
    width: 5px !important;
    margin-right: 10px !important;
}
/* tab */
.form-control{padding:0px!important;background-color: rgb(216, 239, 255);}
.zl_tbhbfx input{border-radius: 4px;}
.czright{margin-right:0px!important;}
.jzxs{text-align: center;}
.czjz{vertical-align: middle!important;text-align: /*center;background-color: #0f627b42 !important;*/}
.zl_sxtj{width:100%;height:96%;position: relative;}
</style>
<ul id="tbhb_tab" class="nav tabs tsys">
</ul>
<!-- 组织机构树模块 -->
<div class="leftarea information_left" style="width:18.4%!important;height:96% !important;">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;加载数据条件>>>
		</span>
	</div>
	<div class="zl_sxtj">
		<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">对比形式：</span>
			<div id="zl_tbhbfx_dbxs"></div>
		</div>
		<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">日期范围：</span>
			<div id="zl_tbhbfx_rqfw"></div>
			</div>
			<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">本期起始： </span>
				<input id="zl_tbhbfx_bq_start_time" disabled="disabled" type="text" name="bq_start" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',readOnly:true,onpicked:view_dataAnalysis_zlyn_zl_tbhbfx.bqtime_change})" class="input-datecheck" >
			</div>
			<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">本期终止： </span>
				<input id="zl_tbhbfx_bq_end_time"  disabled="disabled" type="text" name="bq_end"  onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',readOnly:true,onpicked:view_dataAnalysis_zlyn_zl_tbhbfx.bqtime_change})" class="input-datecheck" >
			</div>
			<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">同期起始： </span>
				<input id="zl_tbhbfx_tq_start_time" disabled="disabled" type="text" name="tq_start" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})" class="input-datecheck" >
			</div>
			<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">同期终止： </span>
				<input id="zl_tbhbfx_tq_end_time"  disabled="disabled" type="text" name="tq_end" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'})"class="input-datecheck"  >
			</div>
			<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">图表类型： </span>
				<div id="zl_tbhbfx_tblx"></div>
			</div>
			<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">时间颗粒度：</span>
				<div id="zl_tbhbfx_sjkld"></div>
			</div>
			<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">单位换算：</span>
				<div id="zl_tbhbfx_dwhs"></div>
			</div>
			<div class="zl_tbhbfx zl_tbhbfx_jsgd"><span class="zl_sxtj_span">请选择支路>>> </span>
				<input type="text" style="visibility: hidden;" class="input-datecheck" >
				<label style="cursor: pointer;right: 3vh;display: flex;position: absolute;">
                <input id="zl_thbfxCascade" type="checkbox" checked value="1">是否级联
                </label>
			</div>
	
	<div id="tree_zl_tbhbfx"  style="overflow-y: auto;overflow-x: auto;width: 100%;border-top: 1px solid #00adffa6;"></div>
	<div class="thb_bottontj" style="background: rgba(22, 142, 169, 0.39);height:5%;position: absolute;width:100%;bottom: 0;">
	<div style="float: right;padding-top: 0.6vh;padding-right: 2vh;">
		<button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft" onclick="view_dataAnalysis_zlyn_zl_tbhbfx.sub()">
			<i class="fa fa-filter"></i>&nbsp;加载数据
		</button>
		<button type="button" style="width:5vw;" class="btn btn-sm btn-primary no-margins toLeft" onclick="view_dataAnalysis_zlyn_zl_tbhbfx.exp()">
            <i class="fa fa-filter"></i>&nbsp;报表生成
        </button>
		<button type="button" class="btn btn-sm btn-primary no-margins toLeft" onclick="view_dataAnalysis_zlyn_zl_tbhbfx.reset()">
			<i class="fa fa-filter"></i>&nbsp;重置
		</button>
	</div>
	</div>
	</div>
</div>
 <!---右侧收费站区域---->
<div class="information_right" style="width:81.5%!important;height:96% !important;""><!--Echars -->
	<div class="information_size"  style="height: 50%;border-bottom: solid 2px #366c90;">
	   <div class="information-model" style="border-bottom:0;">
	   		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;统计分析>>>
			</span>
		</div>
		<div id="zl_tbhbfx_echars" class="Information_areaq" ></div>
   </div>
   <!---table---->  
   <div class="information_size" style="height: 50%"><!-- 列表展示 -->
     	<div class="information-model" style="height:3.3vh">
     		<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;数据展示>>>
			</span>
     	</div>
		<table class="table table-bordered tablestyle" id="zl_tbhbfx_table_fix" style="white-space: nowrap;  overflow-x: scroll;height:100%;">
		</table>
	</div>
</div>
<script src="${ctx}/static/js/time_range.js"></script><!-- 时间范围工具 -->
<script type="text/javascript">
;
var view_dataAnalysis_zlyn_zl_tbhbfx = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var cascadeType = false;//是否级联
	var fnybh="";//fnybh 能耗类型
	var dbxs = "";//对比形式
	var tblx = "";//图标类型
	var sjkld = "";//时间颗粒度 
	var dwhs = "";//单位换算
	var dwhsmc = "";//单位换算名称
	var checkid = "";//默认获取第三级第一个id
	//对比形式数组
	var idArray=['0','1'];
	var valArray=['同比','环比'];
	//日期范围
	var rq_idArray = ['bt','bz','by','bj','bn','sr','sz','sy','sj','sn','zdy'];
	var rq_valArray = ['本天','本周','本月','本季','本年','上日','上周','上月','上季','上年','自定义'];
	//图标类型
	var tb_idArray =['bar','line'];
	var tb_valArray=['柱状图','曲线图'];
	//时间颗粒度
	var sjkld_idArray = ['0','1','2','3'];
	var sjkld_valArray = ['时','日','月','年'];
	//单位换算
	var dwhs_idArray = ['f_data','f_all_money','f_coal_amount','f_co2','f_perman_data','f_perman_money','f_unitarea_data','f_unitarea_money'];
	var dwhs_valArray = ['总能耗(Kwh)','总金额(元)','耗能量(吨)','二氧化碳(ppm)','人均能耗(Kwh)','人均金额(元)','单位面积耗能(Kwh)','单位面积金额(元)'];
	
	//对比形式 下拉框
	function dbxs_select(idArray,valArray){
		$("#zl_tbhbfx_dbxs").ISSPSpinnerBox({
			width:'9vw',//下拉列表宽度
			height: '2.9vh',//下拉列表高度
			margLeft:'0%',//margin-left属性
			isHasData:true,
			idArray:idArray,//id
			valArray:valArray,//txt
			liveSearch:false,//关闭搜索框
			isNoSelectedText:false, //是否设置未选中提示文本
			callBack: dbxsChange,  //自定义事件
		});
	}
	//对比形式change事件
	function dbxsChange(sp){
		dbxs = sp.id;
		var bqstart=$("#zl_tbhbfx_bq_start_time").val();
		var bqend=$("#zl_tbhbfx_bq_end_time").val();
		bqstart=parserDate(bqstart);
		bqend=parserDate(bqend);
		change_tqtime(bqstart,bqend);
	}
	
	//日期范围选择事件
	function rqfw_select(rq_idArray,rq_valArray){
		$("#zl_tbhbfx_rqfw").ISSPSpinnerBox({
			width:'9vw',//下拉列表宽度
			height: '2.9vh',//下拉列表高度
			margLeft:'0%',//margin-left属性
			isHasData:true,
			idArray:rq_idArray,//id
			valArray:rq_valArray,//txt
			liveSearch:false,//关闭搜索框
			isNoSelectedText:false, //是否设置未选中提示文本
			selId:'bz',//默认选中id
			callBack: rqChange,  //自定义事件
		});
	}
	//日期范围change事件
	function rqChange(sp){
		var id = sp.id;
		$("#zl_tbhbfx_bq_start_time,#zl_tbhbfx_bq_end_time,#zl_tbhbfx_tq_start_time,#zl_tbhbfx_tq_end_time").removeAttr("disabled").attr("disabled",true);
		$("#zl_tbhbfx_bq_start_time,#zl_tbhbfx_bq_end_time,#zl_tbhbfx_tq_start_time,#zl_tbhbfx_tq_end_time").removeAttr("cursor").css("cursor","not-allowed");
		//联动规则
		switch (id) {
			//0表示今天
			case "bt":
				var time = getCurrentDate();//当前日期
				$('#zl_tbhbfx_bq_start_time,#zl_tbhbfx_bq_end_time').val(getFormatDate(time));
				change_tqtime(time,time);
				break;
			//1本周
			case "bz":
				var time = getCurrentWeek();
				$('#zl_tbhbfx_bq_start_time').val(getFormatDate(time[0]));$('#zl_tbhbfx_bq_end_time').val(getFormatDate(time[1]));
				change_tqtime(time[0],time[1]);
				break;
				
			//2本月
			case "by":
				var time = getCurrentMonth();
				$('#zl_tbhbfx_bq_start_time').val(getFormatDate(time[0]));$('#zl_tbhbfx_bq_end_time').val(getFormatDate(time[1]));
				change_tqtime(time[0],time[1]);
				break;
			//3本季
			case "bj":
				var time = getCurrentSeason();
				$('#zl_tbhbfx_bq_start_time').val(getFormatDate(time[0]));$('#zl_tbhbfx_bq_end_time').val(getFormatDate(time[1]));
				change_tqtime(time[0],time[1]);
				break;
			//4本年
			case "bn":
				var time = getCurrentYear();
				$('#zl_tbhbfx_bq_start_time').val(getFormatDate(time[0]));$('#zl_tbhbfx_bq_end_time').val(getFormatDate(time[1]));
				change_tqtime(time[0],time[1]);
				break;
			//5表示昨天
			case "sr":
				var time = getPreviousDate();
				$('#zl_tbhbfx_bq_start_time').val(getFormatDate(time));
				$('#zl_tbhbfx_bq_end_time').val(getFormatDate(time));
				change_tqtime(time,time);
				break;
			//6上周
			case "sz":
				var time = getPreviousWeek();
				$('#zl_tbhbfx_bq_start_time').val(getFormatDate(time[0]));$('#zl_tbhbfx_bq_end_time').val(getFormatDate(time[1]));
				change_tqtime(time[0],time[1]);
				break;
			//7上月
			case "sy":
				var time = getPreviousMonth();
				$('#zl_tbhbfx_bq_start_time').val(getFormatDate(time[0]));$('#zl_tbhbfx_bq_end_time').val(getFormatDate(time[1]));
				change_tqtime(time[0],time[1]);
				break;
			//8上季
			case "sj":
				var time = getPreviousSeason();
				$('#zl_tbhbfx_bq_start_time').val(getFormatDate(time[0]));$('#zl_tbhbfx_bq_end_time').val(getFormatDate(time[1]));
				change_tqtime(time[0],time[1]);
				break;
			//9上年
			case "sn":
				var time = getPreviousYear();
				$('#zl_tbhbfx_bq_start_time').val(getFormatDate(time[0]));$('#zl_tbhbfx_bq_end_time').val(getFormatDate(time[1]));
				change_tqtime(time[0],time[1]);
				break;
			//自定义
			case "zdy" : 
				$("#zl_tbhbfx_bq_start_time,#zl_tbhbfx_bq_end_time").removeAttr("disabled");
				$("#zl_tbhbfx_bq_start_time,#zl_tbhbfx_bq_end_time").css("cursor","default");
				break;
			default:
				break;
		}
	}
	
	//图标类型selected
	function tblx_select(tb_idArray,tb_valArray){
		$("#zl_tbhbfx_tblx").ISSPSpinnerBox({
			width:'9vw',//下拉列表宽度
			height: '2.9vh',//下拉列表高度
			margLeft:'0%',//margin-left属性
			isHasData:true,
			idArray:tb_idArray,//id
			valArray:tb_valArray,//txt
			liveSearch:false,//关闭搜索框
			isNoSelectedText:false, //是否设置未选中提示文本
			callBack: tbChange,  //自定义事件
		});
	}
	//图表改变事件
	function tbChange(sp){
		tblx=sp.id;//每次改变赋值给tblx 
	}
	
	//时间颗粒度 selected
	function skkld_select(sjkld_idArray,sjkld_valArray){
		$("#zl_tbhbfx_sjkld").ISSPSpinnerBox({
			width:'9vw',//下拉列表宽度
			height: '2.9vh',//下拉列表高度
			margLeft:'0%',//margin-left属性
			isHasData:true,
			idArray:sjkld_idArray,//id
			valArray:sjkld_valArray,//txt
			liveSearch:false,//关闭搜索框
			isNoSelectedText:false, //是否设置未选中提示文本
			selId:'1',
			callBack: sjkldChange,  //自定义事件
		});
	}
	//时间颗粒度 change
	function sjkldChange(sp){
		sjkld = sp.id;
	}
	//单位换算 selected
	function dwhs_select(dwhs_idArray,dwhs_valArray){
		$("#zl_tbhbfx_dwhs").ISSPSpinnerBox({
			width:'9vw',//下拉列表宽度
			height: '2.9vh',//下拉列表高度
			margLeft:'0%',//margin-left属性
			isHasData:true,
			idArray:dwhs_idArray,//id
			valArray:dwhs_valArray,//txt
			liveSearch:false,//关闭搜索框
			isNoSelectedText:false, //是否设置未选中提示文本
			callBack: dwhsChange,  //自定义事件
		});
	}
	//时间颗粒度 change
	function dwhsChange(sp){
		dwhs = sp.id;
		dwhsmc = sp.txt;
	}
	
	//Echars图表展示 基于准备好的dom，初始化echarts实例
     var myChart = echarts.init(document.getElementById('zl_tbhbfx_echars'));
	//创建并设置table属性-车道
	function myCharts(x_dw,dwhsmc,zlmc,zlid,tblx,x_list,z_list,data,echarsColor){
	     // 指定图表的配置项和数据
	     var option = {
    		 color: ['#EE9201','#29AAE3','#B74AE5','#0AAF9F','#E89589','#16A085','#4A235A','#C39BD3'],
    		 title: {
	             text: '逐时统计分析',
	             left: 'left',
				 top:"2%",
	             textStyle : {
	    			color : echarsColor,
	    			fontSize: '14'
	    		 },
	         },
	         grid: {
		        left: '7%',
		        right: '9%',
		        bottom: '5%',
		        top: '25%',
		        containLabel: true
		     },
		     tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
	         legend: {
          	    type: 'scroll',
        		align: 'left',
        		left:"10%",
        		right:"3%",
        		top:"2%",
        		pageIconColor:"rgb(42, 123, 193)",
     			pageFormatter: '',//隐藏翻页的数字
     			pageButtonItemGap: -6,//翻页按钮的两个之间的间距
          	    textStyle : {
   		 		color : echarsColor
   				},
         	    data: (function (){
         	       var list = [];
         	       for (var i = 0; i <=z_list.length; i++) {
         	           list.push(z_list[i]);
         	       }
         	       return list;
         	    })()
         	 },
	         toolbox : {
	 			show : false,
	 			feature : {
	 				magicType : {
	 					show : true,
	 					type : [ 'line', 'bar' ]
	 				},
	 				restore : {
	 					show : true
	 				},
	 				saveAsImage : {
	 					show : true,
	 					title : '保存为图片',
	 					type : 'png',
	 					lang : [ '点击保存' ]
	 				}
	 			}
	 		},
             dataZoom:[{
                 type : 'inside',
                 disabled : false
             }],
	         xAxis: {
	        	 name:"单位:"+x_dw,
	        	 axisLine : {
					lineStyle : {
						color : echarsColor
					}
				 },
				 data : x_list
	         },
	         yAxis: {
	        	 name:"单位:"+dwhsmc,
	        	 axisLine : {
					lineStyle : {
						color : echarsColor
					}
				 },
				 splitLine: {
				    lineStyle: {
				        //分割线颜色
				        color: ['rgba(40, 76, 117, 0.4)']
				    }
				 },
	         },
	         series : data,
	     };
	     // 使用刚指定的配置项和数据显示图表。
	     myChart.setOption(option, true);
	     myChart.resize();
	}
	$(window).resize(function(){
		myChart.resize();
		setTimeout(function(){ getHeight() },1);
// 		getHeight();
	});
	function getHeight(){
		//获取左侧高度
		var allheight = $(".zl_sxtj").height();//总高度
		var botton = $(".thb_bottontj").height();//底部
		var num = $(".zl_tbhbfx_jsgd").length;
		var tj = $(".zl_tbhbfx_jsgd").outerHeight()-1.3;
		var s = allheight - (num*tj) - botton;
		$("#tree_zl_tbhbfx").height(s);
	}
	//加载tab-list
	$(function(){
		tab_load();
	});
	//初始化时间
	function time_csh(){
		//填加默认时间  先判断对比形式
		var time=getCurrentDate();//获取当前时间
		var qntime=getPreviouDate();//去年当前时间
		if(dbxs=='0'){//同比
			var week = getCurrentWeek();
            $('#zl_tbhbfx_bq_start_time').val(getFormatDate(week[0]));$('#zl_tbhbfx_bq_end_time').val(getFormatDate(week[1]));
            change_tqtime(time[0],time[1]);
			$("#zl_tbhbfx_bq_start_time,#zl_tbhbfx_bq_end_time").removeAttr("cursor").css("cursor","not-allowed");
		}
	}
	//获取级联方法
    function Cascade(){
        var cascade = "";//定义级联数组
        $("#zl_thbfxCascade[type=checkbox]:checked").each(function(i){//each循环所有:checkbox
            cascade = $(this).val();
        });
        if(cascade==''||cascade=='undefined'||cascade==null){//没获取到 则为0  不级联
            cascadeType = false;
        }else{
            cascadeType = true;
        }
    }
	//动态拼装tab
	function tab_load(){
		Cascade();//获取级联方法
		$.ajax({
	        type: "post",
	        url: _ctx + "/view/dataAnalysis/zl_tablist",
	        beforeSend: function () { 
	    		showLoad();
	    	},
	        success: function (result) {
	            if(result.hasOwnProperty("list")){
	            	var opt="";
	            	for(var i=0;i<result.list.length;i++){
						var obj=result.list[i];
						opt +="<li><a href='#home' value='"+obj.ID+"' data-toggle='tab' onclick='view_dataAnalysis_zlyn_zl_tbhbfx.tabclick(this)'>"+obj.NAME+"</a></li>";
					}
	            	$("#tbhb_tab").append(opt);
	            	$("#tbhb_tab").find("li").eq(0).addClass("active nocancel");
		            fnybh=$("#tbhb_tab").find("li>a").eq(0).attr("value");
	            }
	            left_tree(fnybh,true,[]);
	        },
	        complete: function () {
	        	hiddenLoad();
	        },
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
	}
	//加载左侧树
	function left_tree(fnybh,refreshType,AllChecked){
		$.ajax({
	        type: "post",
	        url: _ctx + "/view/dataAnalysis/branch_tree",
	        data:{"fnybh":fnybh},
	        dataType: "json",
	        success: function (result) {
	        	if(result.hasOwnProperty("list")){//返回tree成功
	        		$('#tree_zl_tbhbfx').treeview({
		                data: result.list,         // 数据源
		                highlightSelected: true,    //是否高亮选中
		                levels : 4,
		                enableLinks : true,//必须在节点属性给出href属性
		                wrapNodeText: true,
		                color: "#4a4747",
		                showCheckbox:true,
		                hierarchicalCheck:cascadeType,//级联勾选
		                propagateCheckEvent:true,
		                onNodeChecked:function(event,nodeData){//选中方法
		                },
		                onNodeUnchecked : function(event,nodeData){//取消方法
		                }
		            });
	        		var firstNode = $("#tree_zl_tbhbfx").treeview('findNodes',[result.list[0].id,'id']);//一级
		            var node =$("#tree_zl_tbhbfx").treeview('findNodes',[firstNode[0].id,'id']);//二级
		            var three_node =$("#tree_zl_tbhbfx").treeview('findNodes',[node[1].id,'id']);//三级
		            checkid = node[1].id;
	        		first_check(checkid,refreshType,AllChecked);//默认勾选
	        	}else{//树查询失败
	        		// swal( "当前能源下暂无支路配置","", "warning");
							swal({
								title : '当前能源下暂无支路配置!',// 展示的标题
								text : "",// 内容
								type : "warning",
								showCloseButton : false, // 展示关闭按钮
								allowOutsideClick : false,
								showConfirmButton : false,
								timer : 1000
							});
	        		$('#tree_zl_tbhbfx').treeview('remove');//移除列表树容器。
	        		$('#tree_zl_tbhbfx').treeview('uncheckAll',{ silent: true });//清空所有check
	        	}
	        },
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    }); 
	}
	//是否级联 点击事件
    $("#zl_thbfxCascade").click(function(){
        //只有当点击是否级联的时候不刷新数据
        var refreshType = false;
        var AllChecked = $("#tree_zl_tbhbfx").treeview('getChecked');
        if($('#zl_thbfxCascade').is(':checked')) {
            cascadeType = true;
            left_tree(fnybh,refreshType,AllChecked);
        }else{
            cascadeType = false;
            left_tree(fnybh,refreshType,AllChecked);
        }
    });
	//默认勾选
	function first_check(checkid,refreshType,AllChecked){
		//如果是点击级联事件 则结束 不刷新数据
        if(!refreshType){
            var arr = [];//支路id数组    AllChecked--梭有选中id
            for (var i = 0; i < AllChecked.length; i++) {
                var nodetreeId = AllChecked[i].nodeTreeId;
                if(AllChecked[i].nodeStatus=='3'){
                    arr.push(nodetreeId);
                }
            }
          //判断是否级联 如果级联 则选中父级 如果不级联 则只选择默认级
           if($('#zl_thbfxCascade').is(':checked')) {
        	   for (var j = 0; j < arr.length; j++) {
                   CheckNode(arr[j]);
               }
           }else{
        	   for(var j=0;j<arr.length;j++){
                   var node = $("#tree_zl_tbhbfx").treeview('findNodes', [arr[j], 'id']);
                   $('#tree_zl_tbhbfx').treeview('checkNode', [node, {silent: false}]);
               }
           }
            return false;
        }
        CheckNode(checkid);
		//触发加载数据事件
		view_dataAnalysis_zlyn_zl_tbhbfx.sub();
	}
	//循环勾选符合条件的节点
    function CheckNode(checkid){
        var li = $("#tree_zl_tbhbfx").find("li");
        //循环勾选符合条件的节点
        for(var i=0;i<li.length;i++){
            var id = $("#tree_zl_tbhbfx").find("li").eq(i).attr("id");
            if(id==checkid){
                $("#tree_zl_tbhbfx").find("li").eq(i).find(".check-icon").click();
            }
        }
    }
	//时间js----判断时间条件
	function timeFormat(){
		var startTime = $('#zl_tbhbfx_bq_start_time').val();
		var endTime = $('#zl_tbhbfx_bq_end_time').val();
		var tqstartTime = $('#zl_tbhbfx_tq_start_time').val();
		var tqendTime = $('#zl_tbhbfx_tq_end_time').val();
		if(startTime=='' || endTime==''||tqstartTime==''||tqendTime==''){
			swal( "请输入查询时间段","", "warning");
			return false; 
		}
		var date1 = new Date(startTime.replace(/-/g,"/"));
		var date2 = new Date(endTime.replace(/-/g,"/"));
		var date3 = new Date(tqstartTime.replace(/-/g,"/"));
		var date4 = new Date(tqendTime.replace(/-/g,"/"));
		if(date2.getTime()<date1.getTime()){
			swal( "本期开始时间不能大于结束时间！","", "warning");
			return false;
		}
		if(date4.getTime()<date3.getTime()){
			swal( "同期开始时间不能大于结束时间！","", "warning");
			return false;
		}
		var day = parseInt(((date2.getTime()-date1.getTime())/ 1000)/(24*60*60));
		return true;
	}
	 
	//改变同期时间
	function change_tqtime(start,end){
		//1. 同比状态
		if(dbxs=='0'){//同比
			if (start != null) {
				start.setFullYear(start.getFullYear() - 1);
				$("#zl_tbhbfx_tq_start_time").val(getFormatDate(start));
			}
			if (end != null) {
				end.setFullYear(end.getFullYear() - 1);
				$("#zl_tbhbfx_tq_end_time").val(getFormatDate(end));
			}
		}else if(dbxs=='1'){//环比
			if (start != null && end != null) {
				var t = 2 * start.getTime() - end.getTime();
				if (start.getTime() == end.getTime()) {
					start.setTime(start.getTime() - 24 * 60 * 60 * 1000);
					end.setTime(start.getTime());
				} else {
					end.setTime(start.getTime());
					start.setTime(t);
				}
				$("#zl_tbhbfx_tq_start_time").val(getFormatDate(start));
				$("#zl_tbhbfx_tq_end_time").val(getFormatDate(end));
			}
		}else {//自定义
			var startCustom = $("#zl_tbhbfx_tq_start_time").val();
			if (start != null && end != null && startCustom != null) {
				var t = startCustom.getTime() + end.getTime() - start.getTime();
				$("#zl_tbhbfx_tq_end_time").val(t);
				$("#zl_tbhbfx_tq_end_time").val(getFormatDate(end));
			}
		}
	}
	//根据条件查询动态table拼装数据和动态echars拼装数据
	function pin_data(time_start,time_end,tqtime_start,tqtime_end,sjkld,dwhs,nhlx,zlid,dwhsmc,zlmc,tblx,zlname){
		$.ajax({
	        type: "post",
	        url: _ctx + "/view/dataAnalysis/thb_pin_table",
	        beforeSend: function () { 
        		showLoad();	             
        	},
	        data:{
	        	  "time_start":time_start,
	        	  "time_end":time_end,
	        	  "tqtime_start":tqtime_start,
	        	  "tqtime_end":tqtime_end,
	        	  "sjkld":sjkld,
	        	  "dwhs":dwhs,
	        	  "nhlx":nhlx,
	        	  "zlid":zlid,
	        	  "zlname":zlname,
	        	  "tblx":tblx,
	        },
	        dataType: "json",
	        success: function (result) {
	        	if(result.status=='1'){
	        		//1. pin--table
	                var etempMap = result.map;//
	                var table_one="";
	                for ( var key in etempMap) {
	                    table_one = etempMap[key];
	                };      
	                $("#zl_tbhbfx_table_fix").empty().append(table_one);//每次填充先清空table
	                //不同页面传不同参数 解决样式冲突
	                $('#zl_tbhbfx_table_fix').fixedThead({side:'87.5%',body:'87.5%',thead:'93%',row:1,col:1,});//固定表头和第一列
	                //2. pin--echars
	                var x_dw = "";//X轴单位
	                if(sjkld=='0'){
	                    x_dw = "时";
	                }else if(sjkld=='1'){
	                    x_dw = "天";
	                }else if(sjkld=='2'){
	                    x_dw = "月";
	                }else if(sjkld=='3'){
	                    x_dw = "天";
	                }
	                var x_list=[];
	                var z_list=[];
	                var data=[];
	                if(result.hasOwnProperty("list")){
	                    x_list=result.list;
	                }
	                if(result.hasOwnProperty("data")){
	                    for(var i=0;i<result.data.length;i++){
	                        var obj=result.data[i];
	                        z_list.push(obj.name);
	                        data.push(result.data[i]);
	                    }
	                }
	                //初始化echars的时候先查询cokkie系统肤色
	                var complexion = $.cookie('skin_color')
	                var echarsColor="";
	                if(complexion=='white'){
	                    echarsColor="rgb(48, 140, 227)";
	                }else if(complexion=='blue'){
	                    echarsColor="#8fe3f7";
	                }else{
	                    echarsColor="#8fe3f7";
	                }
	                myCharts(x_dw,dwhsmc,zlmc,zlid,tblx,x_list,z_list,data,echarsColor);//初始化echars
	        	}else{
							swal({
								title : '没有搜索到要查找的内容!',// 展示的标题
								text : "",// 内容
								type : "warning",
								showCloseButton : false, // 展示关闭按钮
								allowOutsideClick : false,
								showConfirmButton : false,
								timer : 1000
							});
                }
	        },
	        complete: function () {
	        	hiddenLoad();
	        },
	        error: function (nodeData) {
	            swal( "图表初始化失败","", "error");
	        }
	    }); 
	}
	//将字符串格式时间转换成date类型
	function parserDate(date) {
		var t = Date.parse(date);  
	    if (!isNaN(t)) {  
	        return new Date(Date.parse(date.replace(/-/g, "/")));  
	    } else {  
	        return new Date();  
	    }  
	}
 	return{
 		//清空条件
 		reset : function(){
 			//清空table
 			$("#zl_tbhbfx_table_fix").empty();
 			$('#zl_tbhbfx_table_fix').fixedThead('destroy');//销毁
 			myChart.clear();//清空echars
 			$('#tree_zl_tbhbfx').treeview('uncheckAll',{ silent: true });//清空所有check
 			view_dataAnalysis_zlyn_zl_tbhbfx.pageInit();
 		},
 	    // 报表生成
        exp : function(){
            // excel的列头
            var alias = new Array();
            // 数据List中的Map的key值.
            var names = new Array();
            // 数据存取list
            var ALLlist = new Array();
            // jquery获取Excel表头数据
            let thead = $("#clone_zl_qstjfxdw_table_fix_head>thead>tr>th").eq(0).text();
            alias.push(thead);
            names.push("a0");
            let thead1 = $("#clone_zl_qstjfxdw_table_fix_head>thead>tr>th").eq(1).text();
            let thead2 = $("#clone_zl_qstjfxdw_table_fix_head>thead>tr").eq(1).find("th");
            let map = {};
            $(thead2).each(function(i){
                alias.push(thead1+$(this).html());
                names.push("a"+(i+1));
            });
            let tbodyTr = $("#clone_zl_qstjfxdw_table_fix_head>tbody>tr");
            $(tbodyTr).each(function(i){
                let map = {};
                let tbodyTh =$(this).find("th");
                $(tbodyTh).each(function(j){
                    let text = $(this).html();
                    map['a'+j]=text;
                });
                ALLlist.push(map);
            });
            //导出--传表名和传list---jsonList
            var exportName = "用能统计分析";
            //数据json内容
            var data = {
                alias : JSON.stringify(alias),
                names : JSON.stringify(names),
                jsonList : JSON.stringify(ALLlist),
            };
            //统一导出excel接口
            var _url = "${ctx}/view/dataAnalysis/eneryCollection/expExcel";
            doExp(_url,exportName,"${ctx}",data);//导出excel并下载
        },
 		//加载数据
 		sub : function(){
			var checkmc=$("#tree_zl_tbhbfx").treeview('getChecked');
			/*if(checkmc.length>0){
	 			if(checkmc[0].nodeTreeId=='01'){
	 				checkmc.splice(0, 1);//从第0个元素开始,移除1个元素
	 			}
			}*/
			var zlmc=[];//支路名称
			var arr=[];//支路id数组
			for (var i = 0; i < checkmc.length; i++) {
				var nodetreeId = checkmc[i].nodeTreeId;
				var nodetext = checkmc[i].text;
				//判断是否为三级 如果为三级 则找到2级id填加进去 然后统一去重--并且级联的时候
				 if($('#zl_thbfxCascade').is(':checked')) {
					 if(checkmc[i].nodeStatus=='3'){
                         arr.push(checkmc[i].pid);
                         zlmc.push(checkmc[i].nodeType);
	                }
				 }
				zlmc.push(nodetext);
				arr.push(nodetreeId);
			}
			//定义去重数组
            var new_arr=[];
            for (var i = 0; i < arr.length; i++) {　　
                var items = arr[i];　　 //判断元素是否存在于new_arr中，如果不存在则插入到new_arr的最后
                　　
                if ($.inArray(items, new_arr) == -1) {
                    new_arr.push(items);
                }
            }
            var new_zlmc = [];
            for (var j = 0; j < zlmc.length; j++) {　　
                var items = zlmc[j];　　
                if ($.inArray(items, new_zlmc) == -1) {　　　　new_zlmc.push(items);　　
                }
            }
	            
 			if(new_arr==''||new_arr=='undefined'||new_arr==null){
				swal({
					title : '当前未选择支路!',// 展示的标题
					text : "",// 内容
					type : "warning",
					showCloseButton : false, // 展示关闭按钮
					allowOutsideClick : false,
					showConfirmButton : false,
					timer : 1000
				});
 			}else{
 				var zlid=new_arr.join(",");//将数组转换成字符串，逗号隔开
 				var zlname=new_zlmc.join(",");
 				var flag=timeFormat();//时间验证
 				if(flag){//验证通过
 					//获取条件值
 					var time_start=$("#zl_tbhbfx_bq_start_time").val();//本期起始时间
 					var time_end=$("#zl_tbhbfx_bq_end_time").val();//本期结束时间
 					var tqtime_start=$("#zl_tbhbfx_tq_start_time").val();//同期起始时间
 					var tqtime_end=$("#zl_tbhbfx_tq_end_time").val();//同期结束时间
 					var nhlx=$("#tbhb_tab>.active>a").attr("value");//能耗类型
 					//1.根据条件拼装table 2.根据条件拼装echars
 					pin_data(time_start,time_end,tqtime_start,tqtime_end,sjkld,dwhs,nhlx,zlid,dwhsmc,zlmc,tblx,zlname);
 				}
 			}
 		},
 		//tab点击事件
 		tabclick : function(object){
 			$(object.parentNode).addClass("nocancel").siblings("li").removeClass("nocancel");
 			var val = object.getAttribute("value");
 			fnybh = val ; 
 			left_tree(val,true,[]);
 			//清空table
 			$("#zl_tbhbfx_table_fix").empty();
 			$('#zl_tbhbfx_table_fix').fixedThead('destroy');//销毁
 			myChart.clear();//清空echars
 			view_dataAnalysis_zlyn_zl_tbhbfx.pageInit();
 		},
 		//自定义本期时间改变事件
 		bqtime_change: function(){
 			var bqstart=$("#zl_tbhbfx_bq_start_time").val();
 			var bqend=$("#zl_tbhbfx_bq_end_time").val();
 			bqstart=parserDate(bqstart);
 			bqend=parserDate(bqend);
 			if(dbxs=='0'){//同比
 				bqstart.setFullYear(bqstart.getFullYear() - 1);
 	 			bqend.setFullYear(bqend.getFullYear() - 1);
 			}else if(dbxs=='1'){//环比
 				bqstart.setFullYear(bqstart.getFullYear() - 1);
 	 			bqend.setFullYear(bqend.getFullYear() - 1);
 			}
 			$("#zl_tbhbfx_tq_start_time").val(getFormatDate(bqstart));
 			$("#zl_tbhbfx_tq_end_time").val(getFormatDate(bqend));
 		},
 		pageInit : function(){
 			//初始化条件
 			dbxs_select(idArray,valArray);
 			rqfw_select(rq_idArray,rq_valArray);
 			tblx_select(tb_idArray,tb_valArray);
 			skkld_select(sjkld_idArray,sjkld_valArray);
 			dwhs_select(dwhs_idArray,dwhs_valArray);
 			time_csh();//初始化时间
 			getHeight();//重新计算高度
 		},
 	}
 	})(jQuery, window, document);
   view_dataAnalysis_zlyn_zl_tbhbfx.pageInit();
	
	