   <div style="height:95%">
		<div id="DatecenterTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/energydataReport/basedatecentermanager/getDatecenterList" id="DatecenterPageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<#assign formId = "DatecenterPageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "Datecenter_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
;
var basedatecenter_basedatecentermanager_datecenterType_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';	
	var _curRow = null;
	var i = 0;
	var j = 1;
	var pageNum = '${page.pageNum}';
    	var optIconFunction = function(cell, formatterParams){ //plain text value
    		var DatecenterID = cell.getRow().getData().f_DATACENTER_ID;
    		return "<div class='btn-group '>"
    			+"<button class='btn btn-white btn-sm edit' data-id="+ DatecenterID + " data-toggle='modal' data-target='#modal-form-editDatecenterType'><i class='fa fa-pencil' ></i> 编辑</button>"
    			+"<button class='btn btn-white btn-sm delete' data-id=" + DatecenterID + "><i class='fa fa-trash'></i>  删除</button></div>"
    	};

    	//创建并设置table属性
    	$("#DatecenterTable").tabulator({
    		height:"100%",
    		layout:"fitColumns",//fitColumns  fitDataFill
    		columnVertAlign:"bottom", //align header contents to bottom of cell
    		tooltips:false,
    		//selectable:true,
    		movableColumns:true,
    		columns:[
    			{title:"序号",field:"f_id",align:"center",width:70,formatter:"rownum",frozen:false,sorter:"number"},
    			{title:"数据中心代码", field:"f_DATACENTER_CODE", sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"数据中心名称", field:"f_DATACENTER_NAME",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"数据中心IP地址", field:"f_DATACENTER_IP",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"数据中心端口号", field:"f_DATACENTER_PORT",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"数据中心类型", field:"lXMC",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"主管单位", field:"f_GOVERNING_BODY",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"联系人", field:"f_CONTACTPERSON",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"联系电话", field:"f_PHONE",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"创建时间", field:"f_CRDATE",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"操作", field:"opt",width:200,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
    		],
    		rowClick:function(e, row){
            	$("#DatecenterTable").tabulator("selectRow", 1);
            	_curRow = row;
        	},
    	});
    	$("#DatecenterTable").tabulator("setData",'${pageList}');
    	return {
    		get_curRow : function(){
    			return _curRow;
    		}
    	}    	
})(jQuery, window, document);
</script>