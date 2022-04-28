
   <div style="height:calc(100% - 60px)">
		<div id="GljlTable">	</div>
   </div>


<!---分页表单----->
<div style="height: 30px">
	<form action="${ctx }/view/strongAndWeakElectricityIntegration/gljl/loadGljlxx" id="GljlPageForm">
		<!-- 查询条件，用隐藏表单域 -->
		<input type="hidden" value="${keywords!}" name="keywords" />
		<input type="hidden" value="${pageSize! }" id="gljl_log_pageSize" name="gljl_log_pageSize" />
		<#assign formId = "GljlPageFgljl_log_pageSizeorm">
		<!-- formId: 分页控件表单ID -->
		<#assign showPageId = "gljlxx_ibox">
		<!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
		<#include "/common/page.ftl"/>
		<!-- 分页控键 -->
	</form>
</div>
<script type="text/javascript">
;
var view_gljl_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var i = 0;
	var j = 1;
	var _curRow = null;
	var pageNum = '${page.pageNum}';
	//alert(pageNum);

	//设置格式
/* 	var optIconFunction = function(cell, formatterParams){ //plain text value
	var fId = cell.getRow().getData().fId;
	return "<div class='btn-group '>"
	+"<button class='btn btn-white btn-sm edit' data-id="+ fId + " data-toggle='modal' data-target='#editProducer'><i class='fa fa-pencil' ></i> 编辑</button>"
	+"<button class='btn btn-white btn-sm delete' data-id=" + fId + "><i class='fa fa-trash'></i>  删除</button></div>"
    }; */
    //创建并设置table属性
	$("#GljlTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		//selectable:true,//,headerSort:false排序属性
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",formatter:"rownum",frozen:false,align:"center",sorter:"string",headerSort:false,tooltip:false,tooltipsHeader:false}, //frozen column
		{title:"用户名", field:"f_username",sorter:"string",align:"center",editor:false,headerSort:false}, //never hide this column
		{title:"操作说明", field:"f_remark",sorter:"string",align:"center",editor:false,headerSort:false}, //never hide this column
		{title:"操作类型", field:"f_type_name",sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"操作时间", field:"f_gltime",sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"创建日期", field:"f_crdate",sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"修改日期", field:"f_chdate",sorter:"string",align:"center",editor:false,headerSort:false},
		],

		rowClick:function(e, row){
        	$("#GljlTable").tabulator("selectRow", 1);
        	_curRow = row;
    	},
	});
	$("#GljlTable").tabulator("setData",'${dataset}');
})(jQuery, window, document);
</script>
