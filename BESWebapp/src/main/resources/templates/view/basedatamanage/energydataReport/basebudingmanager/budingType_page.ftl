   <div style="height:95%">
		<div id="BudingTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/energydataReport/basebudingmanager/getBudingList" id="BudingPageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<#assign formId = "BudingPageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "Buding_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
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
    		var fBudingId = cell.getRow().getData().fBudingId;
    		return "<div class='btn-group '>"
    			+"<button class='btn btn-white btn-sm edit' data-id="+ fBudingId + " data-toggle='modal' data-target='#modal-form-editBudingType'><i class='fa fa-pencil' ></i> 编辑</button>"
    			+"<button class='btn btn-white btn-sm delete' data-id=" + fBudingId + "><i class='fa fa-trash'></i>  删除</button></div>"
    	};

    	//创建并设置table属性
    	$("#BudingTable").tabulator({
    		height:"100%",
    		layout:"fitColumns",//fitColumns  fitDataFill
    		columnVertAlign:"bottom", //align header contents to bottom of cell
    		tooltips:false,
    		//selectable:true,
    		movableColumns:true,
    		columns:[
    			{title:"序号",field:"f_id",align:"center",width:70,formatter:"rownum",frozen:false,sorter:"number"},
    			{title:"节点代码", field:"fBudingCode", sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"建筑物名称", field:"fBudingName",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"建筑物别名", field:"fBudingLetter",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"建筑物业主", field:"fBudingOwner",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"建筑检测状态", field:"jCZTMC",sorter:"string",editor:false,align:"center",headerSort:false},
    			/*{title:"建筑年代", field:"fBudingDate",sorter:"string",editor:false,align:"center",headerSort:false},*/
          {title:"行政区代码", field:"fBudingNode",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"建筑地址", field:"fBudingAddress",sorter:"string",editor:false,align:"center",headerSort:false},
    			/*{title:"录入时间", field:"fEntryTime",sorter:"string",editor:false,align:"center",headerSort:false},*/
          {title:"监测方案设计单位", field:"fSchemeDesign",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"监测工程实施单位", field:"fEngineeringConstruction",sorter:"string",editor:false,align:"center",headerSort:false},
    			{title:"操作", field:"opt",width:200,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
    		],
    		rowClick:function(e, row){
            	$("#BudingTable").tabulator("selectRow", 1);
            	_curRow = row;
        	},
    	});
    	$("#BudingTable").tabulator("setData",'${pageList}');
    	return {
    		get_curRow : function(){
    			return _curRow;
    		}
    	}    	
})(jQuery, window, document);
</script>