  
   <!-- <div style="height:calc(100% -60px)"> -->
   <div style="height:95%">
		<div id="airconditioningConfigTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:5%">
        <form action="${ctx }/view/app/airconditioningConfig/getPaging" id="airconditioning_paging_form">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="airconditioning_pageSize" name="airconditioning_pageSize" />
				<input type="hidden" value="${pageNum! }" id="airconditioning_pageNum" name="airconditioning_pageNum" />
				<#assign formId = "airconditioning_paging_form"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "airconditioning_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div> 
   
<script type="text/javascript">

var airconditioningConfigPage = (function($, window, document, undefined) {

		//设置操作格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var id = cell.getRow().getData().id;
			return "<div class='btn-group '>"
					+"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#airconditioning_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		var airconditioningConfigTable = new Tabulator("#airconditioningConfigTable", {
			height : "100%",
			layout : "fitColumns",
			columnVertAlign : "bottom",
			tooltips : true,
			selectable : 1,
			movableColumns : false,
			columns : [
			    {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
				{title : "空调名称 ",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "空调位置 ",field : "ktAddress",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "空调模式 ",field : "ktMode",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "空调风速 ",field : "ktWindspeed",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "空调开关 ",field : "ktSwitch",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "空调温度 ",field : "ktTemperature",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "空调显示温度 ",field : "ktxsTemperature",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
				],
			rowClick : function(e, row) {

			}
		});

    airconditioningConfigTable.setData('${dataset}'==""?[]:'${dataset}');
			

})(jQuery, window, document);

</script>