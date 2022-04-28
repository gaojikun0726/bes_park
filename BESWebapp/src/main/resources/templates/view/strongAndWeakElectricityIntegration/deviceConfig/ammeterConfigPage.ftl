  
   <!-- <div style="height:calc(100% -60px)"> -->
   <div style="height:95%">
		<div id="ammeterConfigTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:5%">
        <form action="${ctx }/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig/getPaging" id="ammeter_paging_form">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="ammeter_pageSize" name="ammeter_pageSize" />
				<input type="hidden" value="${pageNum! }" id="ammeter_pageNum" name="ammeter_pageNum" />
				<#assign formId = "ammeter_paging_form"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "ammeter_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div> 
   
<script type="text/javascript">

var ammeterConfigPage = (function($, window, document, undefined) {

		//设置操作格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var id = cell.getRow().getData().id;
			return "<div class='btn-group '>"
					+"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#ammeter_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		var ammeterConfigTable = new Tabulator("#ammeterConfigTable", {
			height : "100%",
			layout : "fitColumns",
			columnVertAlign : "bottom",
			tooltips : true,
			selectable : 1,
			movableColumns : false,
			columns : [
			    {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
				{title : "名称 ",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "所属机柜 ",field : "cabinetName",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "瞬时能耗 ",field : "instantEnergyDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "累计能耗 ",field : "totalEnergyDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "A相电压 ",field : "aPhaseVoltageDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "B相电压 ",field : "bPhaseVoltageDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "C相电压 ",field : "cPhaseVoltageDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "A相电流 ",field : "aPhaseCurrentDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "B相电流 ",field : "bPhaseCurrentDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "C相电流 ",field : "cPhaseCurrentDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
				],
			rowClick : function(e, row) {

			}
		});

    ammeterConfigTable.setData('${dataset}'==""?[]:'${dataset}');
			

})(jQuery, window, document);

</script>