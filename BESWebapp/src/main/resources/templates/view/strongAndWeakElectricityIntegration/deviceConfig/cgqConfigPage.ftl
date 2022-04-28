
   <!-- <div style="height:calc(100% -60px)"> -->
   <div style="height:95%">
		<div id="cgqConfigTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:5%">
        <form action="${ctx }/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig/getPaging" id="cgq_paging_form">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="cgq_pageSize" name="cgq_pageSize" />
				<input type="hidden" value="${pageNum! }" id="cgq_pageNum" name="cgq_pageNum" />
				<#assign formId = "cgq_paging_form"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "cgq_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>

<script type="text/javascript">

var cgqConfigPage = (function($, window, document, undefined) {

		//设置操作格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var id = cell.getRow().getData().id;
			return "<div class='btn-group '>"
					+"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#cgq_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		var cgqConfigTable = new Tabulator("#cgqConfigTable", {
			height : "100%",
			layout : "fitColumns",
			columnVertAlign : "bottom",
			tooltips : true,
			selectable : 1,
			movableColumns : false,
			columns : [
			    {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
				{title : "名称 ",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "传感器类型 ",field : "cgqType",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "当前值点位信息 ",field : "cgqDqzDisplay",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
				],
			rowClick : function(e, row) {

			}
		});

    cgqConfigTable.setData('${dataset}'==""?[]:'${dataset}');


})(jQuery, window, document);

</script>
