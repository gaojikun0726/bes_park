<#--
  描述:报警历史记录表
  作者:YangChao
  时间:2020-09-14 11:54:54
-->
<div style="height:95%">
    <div id="WarnInfo_iboxTable"></div>
</div>
<#---分页表单----->
<div style="height:30px">
    <form action="${ctx}/zhdg/warnrecord/getSearch" id="WarnRecordModulePageForm">
        <!-- 查询条件，用隐藏表单域 -->
        <input type="hidden" value="${warnid! }" name="warnid" />
        <input type="hidden" value="${beginTime! }" name="beginTime" />
        <input type="hidden" value="${endTime! }" name="endTime" />
        <#assign formId = "WarnRecordModulePageForm"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "WarnInfo_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
    </form>
</div>

 <script type="text/javascript">
var WarnInfo_listModulePage = (function($, window, document, undefined){

    //创建并设置table属性
    $("#WarnInfo_iboxTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns，表格将调整列的大小，以使其完全适合容器的宽度
            columnVertAlign:"bottom",
            tooltips:true,
            selectable : 1,
            movableColumns:true,
            columns:[
                {title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false},
                {title:"设备id",field:"coverid",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"报警类型",field:"warntype",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"报警说明",field:"warnmsg",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"创建时间",field:"creattime",sorter:"string",editor:false,align:"center",headerSort:false}
            ],
            rowClick:function(e, row){
                _curRow = row;
            },
        });
    $("#WarnInfo_iboxTable").tabulator("setData",'${pageList}');
    return {
        get_curRow : function(){
            return _curRow;
        }
	 }
 })(jQuery, window, document);
 </script>