<div style="height: 95%">
	<div id="modulepointtypeTable"></div>
</div>

<!---分页表单----->
<div style="height: 30px">
	<form action="${ctx }/view/basedatamanage/eqmanage/modulepointType_page" id="module_point_PageForm">
		<!-- 查询条件，用隐藏表单域 -->
		<input type="hidden" value="${keywords! }" name="keywords" />
		<#assign formId = "module_point_PageForm">
		<!-- formId: 分页控件表单ID -->
		<#assign showPageId = "modulepointtype_ibox">
		<!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
		<#include "/common/page.ftl"/>
		<!-- 分页控键 -->
	</form>
</div>

<script type="text/javascript">
	;
	var basedatamanage_eqmanage_modulepointType_page = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var _curRow = null;

		//设置格式
		var optIconFunction = function(cell, formatterParams) { //plain text value
			var roleGuid = cell.getRow().getData().fId;
			return "<div class='btn-group '>"
					+ "<button class='btn btn-white btn-sm edit' data-id="+ roleGuid + " data-toggle='modal' data-target='#editbesModulePointTypeForm'><i class='fa fa-pencil' ></i> 编辑</button>"
					+ "<button class='btn btn-white btn-sm delete' data-id=" + roleGuid + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		//创建并设置table属性
		$("#modulepointtypeTable").tabulator({
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
				title : "模块点类型",
				field : "fModulepointType",
				sorter : "string",
				align : "center",
				editor : false,
				headerSort : false
			}, {
				title : "模块点类型描述",
				field : "fDescription",
				sorter : "string",
				align : "center",
				editor : false,
				headerSort : false
			},  {
				title : "创建时间",
				field : "fCrdate",
				sorter : "date",
				align : "center",
				editable : false,
				headerSort : false
			}, {
				title : "修改时间",
				field : "fChdate",
				sorter : "date",
				align : "center",
				editor : false,
				headerSort : false
			}, {
				title : "操作",
				field : "opt",
				width : 200,
				tooltip : false,
				tooltipsHeader : false,
				align : "center",
				formatter : optIconFunction,
				headerSort : false
			}, ],
			rowClick : function(e, row) {
				$("#modulepointtypeTable").tabulator("selectRow", 1);
				_curRow = row;
			}
		});
		$("#modulepointtypeTable").tabulator("setData", '${pageList}');
		
		return {
            //导出
            exp_excel : function(Object){
                var alias = new Array();
                // excel的列头
                alias.push('模块点类型');
                alias.push('模块点类型描述');
                // 数据List中的Map的key值.--对应数据库表字段
                var names = new Array();
                names.push('F_MODULEPOINT_TYPE');
                names.push('F_DESCRIPTION');
                //报表名称
                var exportName = "模块点类型定义";
                //表名
                var tablename = "bes_modulepoint_type";
                //数据json内容
                var data = {
                    alias : JSON.stringify(alias),
                    names : JSON.stringify(names),
                    tablename,tablename,
                };
                //统一导出excel接口
                var _url = "${ctx}/expExcel/exportExcel";
                doExp(_url,exportName,"${ctx}",data);//导出excel并下载
            },
			getCurrentRow : function() {
				return _curRow;
			}
		}

	})(jQuery, window, document);
</script>