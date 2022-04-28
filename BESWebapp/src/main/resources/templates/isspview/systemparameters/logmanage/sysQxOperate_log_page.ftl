<!-- 
	describe:权限日志分页
	author:liuhoujun
 -->
<div style="height: calc(100% - 60px)">
	<div id="ESSystemqxoperateTable"></div>
</div>


<!---分页表单----->
<div style="height: 30px">
	<form action="${ctx }/view/sysmanage/logmanage/loadsysQxOperateloglists" id="SystemqxoperatePageForm1">
		<!-- 查询条件，用隐藏表单域 -->
		<input type="hidden" value="${keywords!}" name="keywords" /> 
		<input type="hidden" value="${pageSize! }" id="sysQxOperate_log_pageSize" name="sysQxOperate_log_pageSize" />
		<input type="hidden" value="${pageNum! }" id="pageNum" name="pageNum" />
		<input type="hidden" value="${fOpcontent! }" id="fOpcontent" name="fOpcontent" />
		<input type="hidden" value="${fOpname!}" id="fOpname name="fOpname"/>
		<input type="hidden" value="${fOptype! }" id="fOptype" name="fOptype" />
		<input type="hidden" value="${f_sTime! }" id="f_sTime" name="f_sTime" />
		<input type="hidden" value="${f_eTime! }" id="f_eTime" name="f_eTime" />
		<#assign formId = "SystemqxoperatePageForm1">
		<!-- formId: 分页控件表单ID -->
		<#assign showPageId = "SystemQxOperatelog_ibox">
		<!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
		<#include "/common/page.ftl"/>
		<!-- 分页控键 -->
	</form>
</div>


<script type="text/javascript">
	;

	var view_sysmanage_logmanage_sysQxOperate_log_page = (function($, window,document, undefined) {
		var _ctx = '${ctx}';
		var i = 0;
		var j = 1;
		var _curRow = null;
		var pageNum = '${page.pageNum}';
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var operate_fId = cell.getRow().getData().id;
			return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ operate_fId + 
						" data-toggle='modal' data-target='#lookQxOperateFromTable'><i class='fa fa-pencil' ></i> 查看详情</button></div>"
		}; 
		
		//创建并设置table属性
		$("#ESSystemqxoperateTable").tabulator({
			height : "100%",
			layout : "fitColumns",//fitColumns  fitDataFill
			columnVertAlign : "bottom", //align header contents to bottom of cell
			tooltips : false,
			//selectable:true,//,headerSort:false排序属性
			movableColumns : true,
			columns : [ {
				title : "序号",
				field : "id",
				formatter : "rownum",
				frozen : false,
				align : "center",
				sorter : "string",
				headerSort : false,
				tooltip : false,
				tooltipsHeader : false
			}, //frozen column
			{
				title : "操作人",
				field : "fOpname",
				sorter : "string",
				align : "center",
				editor : false,
				headerSort : false
			},
			{
				title : "操作类型",
				field : "fOptype",
				sorter : "string",
				align : "center",
				editor : false,
				headerSort : false,
				formatter: function(cell,formatterParams){
					var fOptype = cell.getRow().getData().fOptype;
					if(fOptype ==11){
						return "功能权限分配";
					}
					if(fOptype ==12){
						return "功能权限审核";
					}
					if(fOptype ==13){
						return "功能权限取消分配";
					}
				}
			},{
				title : "操作内容",
				field : "fOpcontent",
				sorter : "string",
				align : "center",
				editor : false,
				headerSort : false
			},
			 //never hide this column
			   {
				title : "操作时间",
				field : "fOptime",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			}, 
			{title:"创建日期", field:"fCrdate",sorter:"string",align:"center",editor:false,headerSort:false},
			{title:"修改日期", field:"fChdate",sorter:"string",align:"center",editor:false,headerSort:false},
			 {
				title : "操作",
				field : "opt",
				width : 250,
				tooltip : false,
				tooltipsHeader : false,
				align : "left",
				formatter : optIconFunction,
				headerSort : false
			},],
			rowClick : function(e, row) {
				$("#ESSystemqxoperateTable").tabulator("selectRow", 1);
				_curRow = row;
			},
		});
		$("#ESSystemqxoperateTable").tabulator("setData", '${dataset}');

	})(jQuery, window, document);
</script>