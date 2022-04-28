
   <!-- <div style="height:calc(100% -60px)"> -->
   <div style="height:95%">
		<div id="wkqConfigTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:5%">
        <form action="${ctx }/view/app/wkqConfig/getPaging" id="wkq_paging_form">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="wkq_pageSize" name="wkq_pageSize" />
				<input type="hidden" value="${pageNum! }" id="wkq_pageNum" name="wkq_pageNum" />
				<#assign formId = "wkq_paging_form"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "wkq_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>

<script type="text/javascript">

var wkqConfigPage = (function($, window, document, undefined) {

		//设置操作格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var id = cell.getRow().getData().id;
			return "<div class='btn-group '>"
					+"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#wkq_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		var wkqConfigTable = new Tabulator("#wkqConfigTable", {
			height : "100%",
			layout : "fitColumns",
			columnVertAlign : "bottom",
			tooltips : true,
			selectable : 1,
			movableColumns : false,
			columns : [
			    {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
				{title : "温控器名称 ",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "温控器位置 ",field : "wkqAddress",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "温控器模式 ",field : "wkqMode",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "温控器风速 ",field : "wkqWindspeed",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "温控器开关 ",field : "wkqSwitch",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "温控器设定温度 ",field : "wkqTemperature",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "温控器室内显示温度 ",field : "wkqxsTemperature",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
				],
			rowClick : function(e, row) {

			}
		});

    wkqConfigTable.setData('${dataset}'==""?[]:'${dataset}');


})(jQuery, window, document);

</script>
