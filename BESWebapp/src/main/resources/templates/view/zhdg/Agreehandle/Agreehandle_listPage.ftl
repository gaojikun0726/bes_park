<#--
  描述:协议处理类型维护
  作者:YangChao
  时间:2020-08-31 17:41:28
-->
<div style="height:95%">
    <div id="Agreehandle_iboxTable"></div>
</div>
<#---分页表单----->
<div style="height:30px">
    <form action="${ctx}/zhdg/agreehandle/getSearch" id="AgreehandleModulePageForm">
        <!-- 查询条件，用隐藏表单域 -->
        <#assign formId = "AgreehandleModulePageForm"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "Agreehandle_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
    </form>
</div>

 <script type="text/javascript">
;var zhdg_Agreehandle_listModuleClosurePage = (function($, window, document, undefined){

    //创建并设置table属性
    $("#Agreehandle_iboxTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns，表格将调整列的大小，以使其完全适合容器的宽度
            columnVertAlign:"bottom",
            tooltips:true,
            selectable : 1,
            movableColumns:true,
            columns:[
            {title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false},
		            {title:"处理类型名称",field:"name",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"处理类型说明",field:"remarks",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"操作", field:"opt",width:150,align:"left",tooltip:false,tooltipsHeader:false,
            formatter:function(cell, formatterParams){
                var guid = cell.getRow().getData().id;
                    return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ guid + " data-toggle='modal' data-target='#AgreehandleformModel'><i class='fa fa-pencil' ></i> 编辑</button>"
                            +"<button class='btn btn-white btn-sm delete' data-id="+ guid + "><i class='fa fa-trash'></i>  删除</button></div>"
                },headerSort:false},
            ],
            rowClick:function(e, row){
                _curRow = row;
            },
        });
    $("#Agreehandle_iboxTable").tabulator("setData",'${pageList}');
    return {
        get_curRow : function(){
            return _curRow;
        }
	 }
 })(jQuery, window, document);
 </script>