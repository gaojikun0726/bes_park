<!-- 园区页面  
	分页与表格模块 
	sunhao
-->
   <div style="height:95%">
		<div id="yqb_ParkTable"></div>
   </div>
   <!---分页表单----->
   <div style="height:30px">
        <form action="${ctx }/view/park/BESPark_page" id="yqb_emtPageForm">
			<!-- 查询条件，用隐藏表单域 -->
			<input type="hidden" value="${keywords! }" name="keywords" />
			<#assign formId = "yqb_emtPageForm"><!-- formId: 分页控件表单ID -->
			<#assign showPageId = "park_besparkType_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
			<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>        
   
<script type="text/javascript">
;

var basedatamanage_energyinformation_bespark_page = (function($, window, document, undefined) {
  var _curRow = null;
	var _ctx = '${ctx}';	
	var i = 0;
	var j = 1;
	var pageNum = '${page.pageNum}';

	//设置格式
	var yqb_optIconFunction = function(cell, formatterParams) {  //plain text value
		var park_f_yqbh = cell.getRow().getData().f_yqbh;
		return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ park_f_yqbh + " data-toggle='modal' data-target='#editBESParkTable'><i class='fa fa-pencil' ></i> 编辑</button>"
		+ "<button class='btn btn-white btn-sm delete' data-id=" + park_f_yqbh + "><i class='fa fa-trash'></i>  删除</button>"
	};

	//创建并设置table属性
	$("#yqb_ParkTable").tabulator({
		height : "100%",
		layout : "fitColumns",//fitColumns  fitDataFill
		columnVertAlign : "bottom", //align header contents to bottom of cell
		tooltips : true,
		selectable : 1,
		movableColumns : true,
		columns : [ 
		{title : "园区编号",field : "f_yqbh",formatter : "string",frozen : false,align : "center"}, //frozen 
		{title : "园区名称",field : "f_yqmc",sorter : "string",align : "center",editor : false},//never hide this column
		{title : "节点名称",field : "f_node_code",sorter : "string",align : "center",editor : false},
		{title : "总面积",field : "f_all_area",sorter : "string",align : "center",editor : false},
		{title : "监测面积",field : "f_monitor_area",sorter : "string",align : "center",editor : false},
		{title : "供暖面积",field : "f_heat_area",sorter : "string",align : "center",editor : false},
		{title : "总人口",field : "f_person_nums",sorter : "string",align : "center",editor : false},
		{title : "建筑时间",field : "f_build_time",sorter : "string",align : "center",editor : false}, 
		{title : "联系人",field : "f_contact_name",sorter : "string",align : "center",editor : false},
		{title : "联系人电话",field : "f_contact_phone",sorter : "string",align : "center",editor : false}, 
		{title : "联系人邮箱",field : "f_contact_email",sorter : "string",align : "center",editor : false},
		{title : "经度",field : "f_longitude",sorter : "string",align : "center",editor : false},
		{title : "纬度",field : "f_latitude",sorter : "string",align : "center",editor : false},
		{title : "创建时间",field : "f_crdate",sorter : "string",align : "center",editable : false},
		{title : "修改时间",field : "f_chdate",sorter : "string",align : "center",editor : false}, 
		{title : "操作",field : "opt",width:150,tooltip : false,tooltipsHeader : false,align : "center",formatter : yqb_optIconFunction,headerSort : false}, 
		],
		rowClick : function(e, row) {
			_curRow = row;
		}, 
	});
	//填充数据
	$("#yqb_ParkTable").tabulator("setData", '${dataset}');
	return {
		//导出
        exp_excel : function(Object){
            var alias = new Array();
            // excel的列头
            alias.push('园区编号');
            alias.push('园区名称');
            alias.push('总面积');
            alias.push('监测面积');
            alias.push('供暖面积');
            alias.push('总人口');
            alias.push('建筑时间');
            alias.push('联系人');
            alias.push('联系人电话');
            alias.push('联系人邮箱');
            alias.push('经度');
            alias.push('纬度');
            // 数据List中的Map的key值.
            var names = new Array();
            names.push('F_YQBH');
            names.push('F_YQMC');
            names.push('F_ALL_AREA');
            names.push('F_MONITOR_AREA');
            names.push('F_HEAT_AREA');
            names.push('F_PERSON_NUMS');
            names.push('F_BUILD_TIME');
            names.push('F_CONTACT_NAME');
            names.push('F_CONTACT_PHONE');
            names.push('F_CONTACT_EMAIL');
            names.push('F_LONGITUDE');
            names.push('F_LATITUDE');
            //报表名称
            var exportName = "园区基本信息";
            //表名
			var tablename = "bes_park";
            //数据json内容
            var data = {
                alias : JSON.stringify(alias),
                names : JSON.stringify(names),
                tablename : tablename,
            };
            //统一导出excel接口
            var _url = "${ctx}/expExcel/exportExcel";
            doExp(_url,exportName,"${ctx}",data);//导出excel并下载
        }
	}
})(jQuery, window, document);
</script>