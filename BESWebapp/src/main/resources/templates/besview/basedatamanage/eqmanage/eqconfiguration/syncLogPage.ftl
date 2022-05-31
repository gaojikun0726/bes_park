
   <!-- <div style="height:calc(100% -60px)"> -->

    <div style="height:90%">
        <table id="syncLogTable">	</table>
    </div>

    <!---分页表单----->
    <div style="height:10%">
        <form action="${ctx }/view/basedatamanage/eqmanage/besTimeTaskSync/getSyncLogPage" id="sync_log_form">
            <!-- 查询条件，用隐藏表单域 -->
            <input type="hidden" value="${keywords! }" name="keywords" />
            <input type="hidden" value="${pageSize! }" id="sync_log_pageSize" name="pageSize" />
            <input type="hidden" value="${pageNum! }" id="sync_log_pageNum" name="pageNum" />
            <#--action是获取分页数据html的form的id-->
				<#assign formId = "sync_log_form"><!-- formId: 分页控件表单ID -->
            <#--showPageId要设置成对应页面的列表html展示div的id-->
				<#assign showPageId = "sync_log_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置   -->
				<#include "/common/issp_page_edit.ftl"/><!-- 分页控键 -->
        </form>
    </div>


<script type="text/javascript">
    var syncLogPage = (function($, window, document, undefined) {

        //设置操作格式
        var optIconFunction = function(cell, formatterParams){ //plain text value
            var id = cell.getRow().getData().id;
            return "<div class='btn-group '>"
                    +"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#tglight_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
                    +"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
        };

        var syncLogTable = new Tabulator("#syncLogTable", {
            height : "100%",
            layout : "fitColumns",
            columnVertAlign : "bottom",
            tooltips : true,
            selectable : 1,
            movableColumns : false,
            columns:[
                {title:"任务名称", field:"f_sync_name",sorter:"string",editor:false,align:"center",headerSort:false,width:200}, //never hide this column
                {title:"点位名称", field:"f_point_name",sorter:"string",editor:false,align:"center",headerSort:false,width:120},
                {title:"IP", field:"f_point_ip",sorter:"string",editor:false,align:"center",headerSort:false,width:120},
                {title:"下发状态", field:"f_sync_status",sorter:"string",align:"center",editor:false,headerSort:false,width:100,formatter:function(cell, formatterParams){
                        if(cell.getValue() == "0"){
                            return "<span style='color:red; font-weight:bold;'>下发失败</span>";
                        }else if(cell.getValue() == "1"){
                            return "<span style='color:green; font-weight:bold;'>下发成功</span>";
                        }
                    }},
                {title:"下发时间", field:"f_sync_time",sorter:"string",editor:false,align:"center",headerSort:false,width:160},
                {title:"回调状态", field:"f_callback_status",sorter:"string",editor:false,align:"center",headerSort:false,width:100,formatter:function(cell, formatterParams){
                        if(cell.getValue() == "0"){
                            return "<span style='color:red; font-weight:bold;'>下发失败</span>";
                        }else if(cell.getValue() == "1"){
                            return "<span style='color:green; font-weight:bold;'>下发成功</span>";
                        }
                    }},
                {title:"回调时间", field:"f_callback_time",sorter:"string",editor:false,align:"center",headerSort:false,width:180},
            ],
            rowClick : function(e, row) {

            }
        });

        syncLogTable.setData('${dataset}'==""?[]:'${dataset}');


    })(jQuery, window, document);


</script>
