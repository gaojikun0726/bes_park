<!-- 
	describe:操作日志分页
	author:liuhoujun
	editTime:2018/12/13
	
 -->
<div style="height: calc(100% - 60px)">
	<div id="ESSystemoperateTable"></div>
</div>


<!---分页表单----->
<div style="height: 30px">
	<form action="${ctx }/view/sysmanage/logmanage/loadsysOperateloglistss"
		id="SystemoperatePageForm1">
		<!-- 查询条件，用隐藏表单域 -->
		<input type="hidden" value="${keywords!}" name="keywords" /> 
		<input type="hidden" value="${pageSize! }" id="sysOperate_log_pageSize" name="sysOperate_log_pageSize" />
		<input type="hidden" value="${pageNum! }" id="pageNum" name="pageNum" />
		 <input type="hidden" value="${f_comment! }" id="f_comment" name="f_comment" />
		<input type="hidden" value="${f_yhbh! }" id="f_yhbh" name="f_yhbh" />
		<input type="hidden" value="${f_type! }" id="f_type" name="f_type" />
		<input type="hidden" value="${f_sTime! }" id="f_sTime" name="f_sTime" />
		<input type="hidden" value="${f_eTime! }" id="f_eTime" name="f_eTime" />
		<#assign formId = "SystemoperatePageForm1">
		<!-- formId: 分页控件表单ID -->
		<#assign showPageId = "SystemOperatelog_ibox1">
		<!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
		<#include "/common/page.ftl"/>
		<!-- 分页控键 -->
	</form>
</div>


<script type="text/javascript">
	;

	var view_sysmanage_logmanage_sysOperate_log_page = (function($, window,document, undefined) {
		var _ctx = '${ctx}';
		var i = 0;
		var j = 1;
		var _curRow = null;
		var pageNum = '${page.pageNum}';
		var optIconFunction = function(cell, formatterParams){ //plain text value

			var operate_fId = cell.getRow().getData().f_id;
			return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ operate_fId + 
						" data-toggle='modal' data-target='#lookUserFromTable'><i class='fa fa-pencil' ></i> 查看详情</button></div>"
		};
		//创建并设置table属性
		$("#ESSystemoperateTable").tabulator({
			height : "100%",
			layout : "fitColumns",//fitColumns  fitDataFill
			columnVertAlign : "bottom", //align header contents to bottom of cell
			tooltips : false,
			selectable:1,
			movableColumns : false,
			columns : [ {title : "序号",field : "id",formatter : "rownum",frozen : false,align : "center",sorter : "string",headerSort : false,tooltip : false,tooltipsHeader : false}, //frozen column
						{title : "操作人",field : "f_name",sorter : "string",align : "center",editor : false,headerSort : false}, //never hide this column
						{title : "操作类型",field : "f_type",sorter : "string",align : "center",editor : false,headerSort : false,formatter: 
							function(cell,formatterParams){
								var f_type = cell.getRow().getData().f_type;
								if(f_type ==0){
									return "增加";
								}
								if(f_type ==1){
									return "删除";
								}
								if(f_type ==2){
									return "修改";
								}
								if(f_type ==3){
									return "查询";
								}
							}
						}, 
					{title : "表名",field : "f_table_name",sorter : "string",align : "left",editor : false,headerSort : false}, 
					{title : "业务名称",field : "f_comment",sorter : "string",align : "left",editor : false,headerSort : false}, 
					{title : "主键值",field : "f_table_id",sorter : "string",align : "right",editor : false,headerSort : false}, 
					{title : "操作时间",field : "f_operation_time",sorter : "string",align : "left",editor : false,headerSort : false},  
					{title : "操作",field : "opt",width : 250,tooltip : false,tooltipsHeader : false,align : "left",formatter : optIconFunction,headerSort : false}, ],
			rowClick : function(e, row) {
				//$("#ESSystemoperateTable").tabulator("selectRow", 1);
				_curRow = row;
			},
		});
		$("#ESSystemoperateTable").tabulator("setData", '${dataset}');

		return{
			getRow:function(){
				return _curRow;
			},
		}
	})(jQuery, window, document);
</script>