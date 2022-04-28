  
   <!-- <div style="height:calc(100% -60px)"> -->
   <div style="height:95%">
		<div id="coolingTowerConfigTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:5%">
        <form action="${ctx }/view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfig/getPaging" id="cooling_tower_config_page_form">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="cooling_tower_config_pageSize" name="cooling_tower_config_pageSize" />
				<input type="hidden" value="${pageNum! }" id="cooling_tower_config_pageNum" name="cooling_tower_config_pageNum" />
				<#assign formId = "cooling_tower_config_page_form"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "cooling_tower_config_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div> 
   
<script type="text/javascript">

var coolingTowerConfigPage = (function($, window, document, undefined) {

		//设置操作格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var id = cell.getRow().getData().id;
			return "<div class='btn-group '>"
					+"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#cooling_tower_config_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		var coolingTowerConfigTable = new Tabulator("#coolingTowerConfigTable", {
			height : "100%",
			layout : "fitColumns",
			columnVertAlign : "bottom",
			tooltips : true,
			selectable : 1,
			movableColumns : false,
			columns : [
			    {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
				{title : "冷却塔名称",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "冷却塔工作模式",field : "operatingModeDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "冷却塔故障状态",field : "faultStateDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "冷却塔运行时间",field : "operationHoursDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "进水阀开到位",field : "inletValveOpenArrivesDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "进水阀关到位",field : "inletValveCloseArrivesDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "出水阀开到位",field : "outletValveOpenArrivesDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "出水阀关到位",field : "outletValveCloseArrivesDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "进水阀故障",field : "inletValveFaultDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "出水阀故障",field : "outletValveFaultDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "风机故障",field : "fanFaultDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "运行状态",field : "runningStatusDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
				],
			rowClick : function(e, row) {

			}
		});

    coolingTowerConfigTable.setData('${dataset}'==""?[]:'${dataset}');
			

})(jQuery, window, document);

</script>