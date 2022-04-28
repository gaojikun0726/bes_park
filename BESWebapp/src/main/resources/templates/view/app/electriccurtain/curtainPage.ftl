
   <!-- <div style="height:calc(100% -60px)"> -->
   <div style="height:95%">
		<div id="curtainConfigTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:5%">
        <form action="${ctx }/view/app/electriccurtain/curtain/getPage" id="curtain_paging_form">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="curtain_pageSize" name="curtain_pageSize" />
				<input type="hidden" value="${pageNum! }" id="curtain_pageNum" name="curtain_pageNum" />
				<#assign formId = "curtain_paging_form"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "curtain_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>

<script type="text/javascript">

var curtainConfigPage = (function($, window, document, undefined) {

		//设置操作格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var id = cell.getRow().getData().id;
			return "<div class='btn-group '>"
					+"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#curtain_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		var curtainConfigTable = new Tabulator("#curtainConfigTable", {
			height : "100%",
			layout : "fitColumns",
			columnVertAlign : "bottom",
			tooltips : true,
			selectable : 1,
			movableColumns : false,
			columns : [
			    {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
				{title : "电动窗帘名称 ",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "电动窗帘位置 ",field : "electriccurtain_address",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "电动窗帘开停控制 ",field : "electriccurtain_switch",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "电动窗帘行程 ",field : "electriccurtain_kdkz",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "电动窗帘关停控制 ",field : "electriccurtain_stop",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
				],
			rowClick : function(e, row) {

			}
		});

    curtainConfigTable.setData('${dataset}'==""?[]:'${dataset}');


})(jQuery, window, document);

</script>
