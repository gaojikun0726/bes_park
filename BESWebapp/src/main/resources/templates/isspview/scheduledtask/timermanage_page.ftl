
   <div style="height:95%">
		<div id="TimerManageTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/TimerManage/getTimerManageList" id="TimerManagePageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="timermanage_pageSize" name="timermanage_pageSize" />
				<#assign formId = "TimerManagePageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "TimerManage_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
;
var isspview_scheduledtask_timermanagepage = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	var _curRow = null;

	var pageNum = '${page.pageNum}';
	//alert(pageNum); 

	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
	var fTimerBh = cell.getRow().getData().fTimerBh;	
	var fTimerState = cell.getRow().getData().fTimerState;
	//根据运行状态设置操作按钮
	if(fTimerState == 0){
		return "<div class='btn-group '><button class='btn btn-white btn-sm start' id='start"+fTimerBh+"' data-id="+ fTimerBh + "><i class='fa fa-plus-circle' ></i> 执行</button>"
		+"<button class='btn btn-white btn-sm stop' disabled id='stop"+fTimerBh+"' data-id="+ fTimerBh + "><i class='fa fa-minus-circle'></i>  停止</button>"
		+"<button class='btn btn-white btn-sm startnow' id='startnow"+fTimerBh+"' data-id="+ fTimerBh + "><i class='fa fa-flash'></i>  立即执行</button></div>";
	}else{
		return "<div class='btn-group '><button class='btn btn-white btn-sm start' disabled id='start"+fTimerBh+"' data-id="+ fTimerBh + "><i class='fa fa-plus-circle' ></i> 执行</button>"
		+"<button class='btn btn-white btn-sm stop' id='stop"+fTimerBh+"' data-id="+ fTimerBh + "><i class='fa fa-minus-circle'></i>  停止</button>"
		+"<button class='btn btn-white btn-sm startnow' disabled id='startnow"+fTimerBh+"' data-id="+ fTimerBh + "><i class='fa fa-flash'></i>  立即执行</button></div>";
	}
		
	};

		//创建并设置table属性
	$("#TimerManageTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		movableColumns:true,
		
		columns:[
		{title:"序号",field:"f_id",width:70,frozen:false,formatter:"rownum",align:"center"}, //frozen column
		//{title:"定时器编号", field:"fTimerBh",width:280,sorter:"string",editor:false,headerSort:false}, //never hide this column
		{title:"定时器名称", field:"fTimerName",align:"left",sorter:"string",editor:false,headerSort:false}, //hide this column first 
		{title:"启动时间", field:"fStartTime",align:"center",sorter:"string",editor:false,headerSort:false},
		{title:"停止时间", field:"fStopTime",align:"center",sorter:"string",editor:false,headerSort:false},
		{title:"运行状态", field:"fTimerState",align:"right",sorter:"string",editor:false,headerSort:false},
		{title:"创建时间", field:"fCrdate",align:"center",sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"修改时间", field:"fChdate",align:"center",sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:300,align:"center",tooltipsHeader:false,formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	$("#TimerManageTable").tabulator("selectRow", 1);
        	_curRow = row;
    	},
	});
	$("#TimerManageTable").tabulator("setData",'${dataset}');

	return {
		get_curRow : function () {
		return _curRow;
		}
	}
})(jQuery, window, document);
</script>