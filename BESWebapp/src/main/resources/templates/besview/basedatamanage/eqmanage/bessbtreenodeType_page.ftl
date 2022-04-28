<div style="height: 95%">
	<div id="bessbtreenodetypeTable"></div>
</div>

<!---分页表单----->
<div style="height: 30px">
	<form action="${ctx }/view/basedatamanage/eqmanage/getbessbtreenodeType" id="bntPageForm">
		<!-- 查询条件，用隐藏表单域 -->
		<input type="hidden" value="${keywords! }" name="keywords" />
		<#assign formId = "bntPageForm">
		<!-- formId: 分页控件表单ID -->
		<#assign showPageId = "bessbtreenodetype_ibox">
		<!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
		<#include "/common/page.ftl"/>
		<!-- 分页控键 -->
	</form>
</div>

<script type="text/javascript">
	;
	var basedatamanage_eqmanage_bessbtreenodeType_page = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var _curRow = null;

		//设置格式
		var optIconFunction = function(cell, formatterParams) { //plain text value
			var roleid = cell.getRow().getData().f_node_type;
			return "<div class='btn-group '>"
					+ "<button class='btn btn-white btn-sm edit' data-id="+ roleid + " data-toggle='modal' data-target='#editBessbTreeNodeTypeForm'><i class='fa fa-pencil' ></i> 编辑</button>"
					+ "<button class='btn btn-white btn-sm delete' data-id=" + roleid + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		//创建并设置table属性
		$("#bessbtreenodetypeTable").tabulator({
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
				title : "节点类型",
				field : "f_node_type",
				sorter : "string",
				align : "right",
				editor : false,
				headerSort : false
			},{
				title : "节点名称",
				field : "f_node_name",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			},{
				title : "节点功能名称(以”，“隔开)",
				field : "f_fun_mcs",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			},{
				title : "属性url",
				field : "f_edit_url",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			}, {
				title : "节点数量上限",
				field : "f_max_count",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			}, {
				title : "实体表名",
				field : "f_node_table",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			}, {
				title : "在线图片路径",
				field : "f_online_img",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			}, {
				title : "离线图片路径",
				field : "f_offline_img",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			}, {
				title : "备注",
				field : "f_remark",
				sorter : "string",
				align : "left",
				editor : false,
				headerSort : false
			}, {
				title : "创建时间",
				field : "f_crdate",
				sorter : "date",
				align : "center",
				editable : false,
				headerSort : false
			}, {
				title : "修改时间",
				field : "f_chdate",
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
				$("#bessbtreenodetypeTable").tabulator("selectRow", 1);
				_curRow = row;
			}
		});
		$("#bessbtreenodetypeTable").tabulator("setData", '${pageList}');
		
		return {
			//导出
            exp_excel : function(Object){
                var alias = new Array();
                // excel的列头
                alias.push('节点类型');
                alias.push('节点名称');
                alias.push('节点功能名称');
                alias.push('属性节点类型');
                alias.push('属性url');
                alias.push('节点数量上限');
                alias.push('实体表名');
                alias.push('在线图片地址');
                alias.push('离线图片路径');
                alias.push('备注');
                // 数据List中的Map的key值.
                var names = new Array();
                names.push('F_NODE_TYPE');
                names.push('F_NODE_NAME');
                names.push('F_FUN_MCS');
                names.push('F_FUN_NODETYPE');
                names.push('F_EDIT_URL');
                names.push('F_MAX_COUNT');
                names.push('F_NODE_TABLE');
                names.push('F_ONLINE_IMG');
                names.push('F_OFFLINE_IMG');
                names.push('F_REMARK');
                //报表名称
                var exportName = "设备树节点定义";
                //表名
				var tablename = "bes_sbtree_nodetype";
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