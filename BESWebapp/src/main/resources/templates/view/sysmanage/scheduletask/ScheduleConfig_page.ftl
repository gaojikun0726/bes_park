   <div style="height:calc(100% + 10px)">
		<div id="scheduleTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/sysmanage/getScheduleList" id="schedulePageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<#assign formId = "schedulePageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "schedule_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
;
var sysmanage_scheduletask_scheduleConfig_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	var _curRow = null;

    	var optIconFunction = function(cell, formatterParams){ //plain text value
    		var fSysName = cell.getRow().getData().fSysName;
    		return "<div class='btn-group '>"
    			// +"<button class='btn btn-white btn-sm edit' data-id="+ fSysName + " data-toggle='modal' data-target='#modal-form-editscheduleTask'><i class='fa fa-pencil' ></i> 编辑</button>"
    			+"<button class='btn btn-white btn-sm syn' data-id="+ fSysName + " ><i class='fa fa-pencil' ></i> 同步</button>"
    			+"<button class='btn btn-white btn-sm compare' data-id="+ fSysName + " data-toggle='modal' data-target='#comparescheduleTask'><i class='fa fa-pencil' ></i> 对比</button>"
    			+"<button class='btn btn-white btn-sm delete' data-id=" + fSysName + "><i class='fa fa-trash'></i>  删除</button></div>"
    	};
		var optfEnabled = function(cell, formatterParams){ //plain text value
			var f = cell.getRow().getData().fEnabled;
            if(f =='1'){
                return "是";
            }else{
                return "否";
            }
		};

    	//创建并设置table属性
    	$("#scheduleTable").tabulator({
    		height:"100%",
    		layout:"fitColumns",//fitColumns  fitDataFill
    		columnVertAlign:"bottom", //align header contents to bottom of cell
    		tooltips:false,
    		//selectable:true,
    		movableColumns:true,
    		columns:[
    			{title:"序号",field:"f_id",align:"center",width:70,formatter:"rownum",frozen:false,sorter:"number"},
    			// {title:"计划名称", field:"fSysName", sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"计划名称", field:"fNickName",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"开始日期", field:"fStartDate",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"结束日期", field:"fEndDate",sorter:"string",editor:false,align:"center",headerSort:false},
    			// {title:"替代日", field:"fIsholidays",sorter:"date",editor:false,align:"center",headerSort:false},
    			// {title:"场景类型", field:"fZoneType",sorter:"date",editor:false,align:"center",headerSort:false},
    			// {title:"场景名称", field:"fCrdate",sorter:"date",editor:false,align:"center",headerSort:false},
    			{title:"是否启用", field:"fEnabled",sorter:"date",editor:false,align:"center",headerSort:false,formatter:optfEnabled},
    			// {title:"执行方式", field:"fIsspan",sorter:"date",editor:false,align:"center",headerSort:false},
    			{title:"操作", field:"opt",width:300	,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
    		],
    		rowClick:function(e, row){
            	$("#scheduleTable").tabulator("selectRow", 1);
            	_curRow = row;
        	},
    	});
    	$("#scheduleTable").tabulator("setData",'${dataset}');
    	return {
    		get_curRow : function(){
    			return _curRow;
    		}
    	}    	
})(jQuery, window, document);
</script>