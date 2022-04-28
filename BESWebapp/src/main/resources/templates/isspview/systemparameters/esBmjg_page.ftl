<div style="height: 95%">
	<div id="bmjgTable"></div>
</div>

<!---分页表单----->
<div style="height: 30px">
	<form action="${ctx }/isspview/applicationmanage/esBmjg_page" id="es_bmig_PageForm">
		<!-- 查询条件，用隐藏表单域 -->
		<input type="hidden" value="${keywords! }" name="keywords" />
		<input type="hidden" value="${pageSize! }" id="esBmjg_pageSize" name="esBmjg_pageSize" />
		<#assign formId = "es_bmig_PageForm">
		<!-- formId: 分页控件表单ID -->
		<#assign showPageId = "bmjg_ibox">
		<!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
		<#include "/common/page.ftl"/>
		<!-- 分页控键 -->
	</form>
</div>

<script type="text/javascript">
	;
	var isspview_applicationmanage_esBmjg_page = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var _curRow = null;
		var _includecurRow = null;//“已选择”table对应行

		//设置格式
		var optIconFunction = function(cell, formatterParams) { //plain text value
			var roleid = cell.getRow().getData().tableName;
			return "<div class='btn-group '>"
					//+ "<button class='btn btn-white btn-sm serch' data-id="+ roleid + " data-toggle='modal' data-target='#searchInterface'><i class='fa fa-search'></i> 查询服务接口</button>"			
					+ "<button class='btn btn-white btn-sm edit' data-id="+ roleid + " data-toggle='modal' data-target='#editBmjgForm'><i class='fa fa-pencil' ></i> 编辑</button>"
					+ "<button class='btn btn-white btn-sm delete' data-id=" + roleid + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		//创建并设置table属性
		$("#bmjgTable").tabulator({
			height : "100%",
			layout : "fitColumns",//fitColumns  fitDataFill
			columnVertAlign : "bottom", //align header contents to bottom of cell
			tooltips : true,
			//selectable:true,
			movableColumns : true,
			columns : [ {
				title : "序号",
				field : "id",
				width : 50,
				formatter : "rownum",
				align : "center",
				frozen : false,
				headerSort : false
			}, {
				title : "表名",
				field : "tableName",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			}, {
				title : "表编码结构",
				field : "bmjg",
				sorter : "string",
				align : "right",
				editor : false,
				headerSort : false
			}, {
				title : "创建时间",
				field : "crdate",
				sorter : "date",
				align : "center",
				editable : false,
				headerSort : false
			}, {
				title : "修改时间",
				field : "chdate",
				sorter : "date",
				align : "center",
				editor : false,
				headerSort : false
			}, {
				title : "操作",
				field : "opt",
				tooltip : false,
				tooltipsHeader : false,
				align : "left",
				width:150,
				formatter : optIconFunction,
				headerSort : false
			}, ],
			rowClick : function(e, row) {
				$("#bmjgTable").tabulator("selectRow", 1);
				_curRow = row;
			}
		});
		$("#bmjgTable").tabulator("setData", '${pageList}');
		
		return {
			//验证增加模态框是否弹出
			getCurrentRow : function() {
				return _curRow;
			}
		}
		
	})(jQuery, window, document);
</script>