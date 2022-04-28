<#--
  描述:动态协议配置
  作者:YangChao
  时间:2020-09-03 09:40:12
-->
<div style="height:95%">
    <div id="Dynamicagree_iboxTable"></div>
</div>
<#---分页表单----->
<div style="height:30px">
    <form action="${ctx}/zhdg/dynamicagree/getSearch" id="DynamicagreeModulePageForm">
        <!-- 查询条件，用隐藏表单域 -->
        <input type="hidden" value="${keywords! }" name="keywords" />
        <input type="hidden" value="${pageNum! }" id="Dynamicagree_pageNum" name="Dynamicagree_pageNum" />
        <#assign formId = "DynamicagreeModulePageForm"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "Dynamicagree_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
    </form>
</div>

 <script type="text/javascript">
;var zhdg_Dynamicagree_listModuleClosurePage = (function($, window, document, undefined){

    //创建并设置table属性
    $("#Dynamicagree_iboxTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns，表格将调整列的大小，以使其完全适合容器的宽度
            columnVertAlign:"bottom",
            tooltips:true,
            selectable : 1,
            movableColumns:true,
            columns:[
            {title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false},
                {title:"数据类型",field:"ordercode",sorter:"string",editor:false,align:"center",headerSort:false,formatter:function(cell, formatterParams){
                    var ordercode = cell.getRow().getData().ordercode;
                    return ordercode=='171'?"<span>数据包</span>":"<span>配置包</span>";
                }},
	            // {title:"协议类型id",field:"agreeid",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"名称",field:"name",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"对象名称",field:"oname",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"协议起始位",field:"start",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"协议终止位",field:"end",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"是否解析字节位",field:"analysisposition",sorter:"string",editor:false,align:"center",headerSort:false,formatter:function(cell, formatterParams){
                    var analysisposition = cell.getRow().getData().analysisposition;
                    return analysisposition=='0'?"<span>是</span>":"<span>否</span>";
                }},
	            {title:"字节位置",field:"position",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"报警类型",field:"warnName",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"协议处理类型",field:"handleidname",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"启用状态",field:"state",sorter:"string",editor:false,align:"center",headerSort:false,formatter:function(cell, formatterParams){
                    var state = cell.getRow().getData().state;
                    return state=='0'?"<span>启用</span>":"<span>停用</span>";
                }},
            {title:"操作", field:"opt",width:150,align:"left",tooltip:false,tooltipsHeader:false,
            formatter:function(cell, formatterParams){
                var guid = cell.getRow().getData().id;
                    return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ guid + " data-toggle='modal' data-target='#DynamicagreeformModel'><i class='fa fa-pencil' ></i> 编辑</button>"
                            +"<button class='btn btn-white btn-sm delete' data-id="+ guid + "><i class='fa fa-trash'></i>  删除</button></div>"
                },headerSort:false},
            ],
            rowClick:function(e, row){
                _curRow = row;
            },
        });
    $("#Dynamicagree_iboxTable").tabulator("setData",'${pageList}');
    return {
        get_curRow : function(){
            return _curRow;
        }
	 }
 })(jQuery, window, document);
 </script>