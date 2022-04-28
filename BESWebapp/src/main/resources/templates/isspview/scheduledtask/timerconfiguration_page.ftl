   <div style="height:95%">
		<div id="TimerConfigurationTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/TimerConfiguration/getTimerConfigurationList" id="TimerConfigurationPageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="timerconfiguration_pageSize" name="timerconfiguration_pageSize" />
				<#assign formId = "TimerConfigurationPageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "TimerConfiguration_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
;
var isspview_scheduledtask_timermanagepage = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	var _curRow = null;//当前选中的行	
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
	var F_TIMER_BH = cell.getRow().getData().F_TIMER_BH;	
		return "<div class='btn-group '>"
				+"<button class='btn btn-white btn-sm edit' data-id="+ F_TIMER_BH + " data-toggle='modal' data-target='#modal-form-editTimerConfiguration'> <i class='fa fa-pencil'></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id="+ F_TIMER_BH + " ><i class='fa fa-trash'></i> 删除</button></div>"
	};

	//创建并设置table属性
	$("#TimerConfigurationTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		movableColumns:true,
		columns:[
		{title:"序号",field:"f_id",width:70,frozen:false,formatter:"rownum",align:"center",headerSort:false}, 
		{title:"定时器名称", field:"F_TIMER_NAME",align:"left",sorter:"string",editor:false,headerSort:false},
		{title:"适配器名称", field:"F_ADAPTERMC",align:"left",sorter:"string",editor:false,headerSort:false},
		{title:"服务接口", field:"F_SERVICE_MC",align:"left",sorter:"string",editor:false,headerSort:false},
		{title:"定时器类型", field:"F_NAME",align:"left",sorter:"string",editor:false,headerSort:false},
		{title:"执行时间", field:"F_FIXED_TIME",align:"left",sorter:"string",editor:false,headerSort:false},
		{title:"循环间隔", field:"F_LOOP_TIME",align:"rigjt",sorter:"string",editor:false,headerSort:false},
		{title:"星期/日期", field:"F_VAR_TIME",align:"rigjt",sorter:"string",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:150,align:"center",tooltipsHeader:false,formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	$("#TimerConfigurationTable").tabulator("selectRow", 1);
        	_curRow = row;
    	},
	});
	$("#TimerConfigurationTable").tabulator("setData",'${dataset}');

	return {
		get_curRow : function () {
		return _curRow;
		}
	}
})(jQuery, window, document);
</script>