  
   <!-- <div style="height:calc(100% -60px)"> -->

    <div style="height:95%">
        <table id="lightConfigTable">	</table>
    </div>

    <!---分页表单----->
    <div style="height:5%">
        <form action="${ctx }/view/app/light/getPaging" id="light_paging_form">
            <!-- 查询条件，用隐藏表单域 -->
            <input type="hidden" value="${keywords! }" name="keywords" />
            <input type="hidden" value="${pageSize! }" id="light_pageSize" name="pageSize" />
            <input type="hidden" value="${pageNum! }" id="light_pageNum" name="pageNum" />
            <#--action是获取分页数据html的form的id-->
				<#assign formId = "light_paging_form"><!-- formId: 分页控件表单ID -->
            <#--showPageId要设置成对应页面的列表html展示div的id-->
				<#assign showPageId = "light_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置   -->
				<#include "/common/issp_page_edit.ftl"/><!-- 分页控键 -->
        </form>
    </div>

   
<script type="text/javascript">
    var lightConfigPage = (function($, window, document, undefined) {

        //设置操作格式
        var optIconFunction = function(cell, formatterParams){ //plain text value
            var id = cell.getRow().getData().id;
            return "<div class='btn-group '>"
                    +"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#light_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
                    +"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
        };

        var lightConfigTable = new Tabulator("#lightConfigTable", {
            height : "100%",
            layout : "fitColumns",
            columnVertAlign : "bottom",
            tooltips : true,
            selectable : 1,
            movableColumns : false,
            columns : [
                {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
                {title : "灯光名称 ",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "灯光地址 ",field : "lightAddress",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "灯光开关 ",field : "lightSwitch",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "开关状态 ",field : "lightStatus",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
            ],
            rowClick : function(e, row) {

            }
        });

        lightConfigTable.setData('${dataset}'==""?[]:'${dataset}');


    })(jQuery, window, document);


</script>