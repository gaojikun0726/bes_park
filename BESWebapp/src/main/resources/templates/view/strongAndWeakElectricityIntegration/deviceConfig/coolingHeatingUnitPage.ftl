  
   <!-- <div style="height:calc(100% -60px)"> -->
   <div style="height:95%">
		<div id="coolingHeatingUnitTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:5%">
        <form action="${ctx }/view/strongAndWeakElectricityIntegration/deviceConfig/coolingHeatingUnit/getPaging" id="cooling_heating_unit_form">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="cooling_heating_unit_pageSize" name="cooling_heating_unit_pageSize" />
				<input type="hidden" value="${pageNum! }" id="cooling_heating_unit_pageNum" name="cooling_heating_unit_pageNum" />
				<#assign formId = "cooling_heating_unit_form"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "cooling_heating_unit_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div> 
   
<script type="text/javascript">

var coolingHeatingUnitPage = (function($, window, document, undefined) {

		//设置操作格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var id = cell.getRow().getData().id;
			return "<div class='btn-group '>"
					+"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#cooling_heating_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		var coolingHeatingUnitTable = new Tabulator("#coolingHeatingUnitTable", {
			height : "100%",
			layout : "fitColumns",
			columnVertAlign : "bottom",
			tooltips : true,
			selectable : 1,
			movableColumns : false,
			columns : [
			    {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
				{title : "名称 ",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "当前状态 ",field : "currentStateAlias",sorter : "string",align : "center",editor : false,visible : true,headerSort : false},
                {title : "冷冻水供水温度 ",field : "freezeSupplyWaterTemperatureAlias",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "冷冻水回水温度",field : "freezeReturnWaterTemperatureAlias",sorter : "string",align : "center",visible : true,editor : false,headerSort : false},
				{title : "冷却水供水温度",field : "coolingSupplyWaterTemperatureAlias",sorter : "string",align : "center",editable : false,headerSort : false},
				{title : "冷却水回水温度",field : "coolingReturnWaterTemperatureAlias",sorter : "string",align : "center",editable : false,headerSort : false},
				{title : "总故障状态",field : "currentFlowAlias",sorter : "string",align : "center",editable : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
				],
			rowClick : function(e, row) {

			}
		});

    coolingHeatingUnitTable.setData('${dataset}'==""?[]:'${dataset}');
			

})(jQuery, window, document);

</script>