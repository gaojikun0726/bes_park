
   <div style="height:100%">
		<div id="emtTable" style="width:100%;overflow:auto;">	</div>
   </div>

   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/basedatamanage/beseqmanage/equipmentType_page" id="emtPageForm">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<#assign formId = "emtPageForm"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "equipmentType_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>

<script type="text/javascript">
;
var basedatamanage_eqmanage_equipmentType_page = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var _curRow = null;
	var i = 0;
	var j = 1;
	var pageNum = '${page.pageNum}';
	//alert(pageNum);

	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
	var f_type = cell.getRow().getData().f_type;
		return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ f_type + " data-toggle='modal' data-target='#editEmtForm1'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id="+ f_type + "><i class='fa fa-trash'></i>  删除</button></div>"
	};

		//创建并设置table属性
	$("#emtTable").tabulator({
		//ajaxURL:_ctx+"/view/basedatamanage/eqmanage/equipmentType?pgnum="+pgnum,
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		movableColumns:true,
		//date:'${dataset}',

		columns:[
		{title:"序号",field:"f_id",align:"center",width:70,frozen:false,headerSort:false,formatter:"rownum"},
		{title:"设备型号", field:"f_type",align:"center",sorter:"string",headerSort:false,editor:false},
		{title:"设备名称", field:"f_sbmc",align:"center",sorter:"string",headerSort:false,editor:false},
		{title:"设备类型编号", field:"f_sblxbh",align:"center",sorter:"string",headerSort:false,editor:false},
		{title:"品牌id", field:"f_brandid",align:"center",sorter:"string",headerSort:false,editor:false},
		{title:"备注", field:"f_remark",align:"center",sorter:"string",headerSort:false,editor:false},
		{title:"创建时间", field:"f_crdate",align:"center",sorter:"string",headerSort:false,editor:false},
		{title:"修改时间", field:"f_chdate",align:"center",sorter:"string",headerSort:false,editor:false},
		{title:"操作", field:"opt",width:200,align:"center",tooltip:false,headerSort:false,tooltipsHeader:false,formatter:optIconFunction},
		],
		rowClick:function(e, row){
        	$("#emtTable").tabulator("selectRow", 1);
        	_curRow = row;
    	},
	});
	$("#emtTable").tabulator("setData",'${dataset}');

	return {
			get_curRow : function() {
				return _curRow;
			}
		}
	})(jQuery, window, document);
</script>
