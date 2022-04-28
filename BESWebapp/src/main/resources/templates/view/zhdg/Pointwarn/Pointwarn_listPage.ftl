<#--
  描述:点位报警维护
  作者:YangChao
  时间:2020-09-16 10:20:05
-->
<div style="height:95%">
    <div id="Pointwarn_iboxTable"></div>
</div>
<#---分页表单----->
<div style="height:30px">
    <form action="${ctx}/zhdg/pointwarn/getSearch" id="PointwarnModulePageForm">
        <input type="hidden" value="${pointid! }" name="pointid" />
        <input type="hidden" value="${warnid! }" name="warnid" />
        <!-- 查询条件，用隐藏表单域 -->
        <#assign formId = "PointwarnModulePageForm"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "Pointwarn_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
    </form>
</div>

<script type="text/javascript">
;var zhdg_Pointwarn_listModuleClosurePage = (function($, window, document, undefined){

    //创建并设置table属性
    $("#Pointwarn_iboxTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns，表格将调整列的大小，以使其完全适合容器的宽度
            columnVertAlign:"bottom",
            tooltips:true,
            selectable : 1,
            movableColumns:true,
            columns:[
                {title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false},
                {title:"点位id",field:"pointid",sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"点位名称",field:"f_point_name",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"报警类型",field:"warntype",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"报警阈值类型",field:"vtype",sorter:"string",editor:false,align:"center",headerSort:false,
                    formatter:function (cell, formatterParams) {
                        var vtype = cell.getRow().getData().vtype;
                        if(vtype=='0'){
                            return "<div>定值</div>";
                        }else{
                            return "<div>浮动</div>";
                        }
                    }
                },
	            {title:"定值",field:"vtype0",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"浮动阈值下限",field:"vtype1Min",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"浮动阈值上限",field:"vtype1Max",sorter:"string",editor:false,align:"center",headerSort:false},
                {title:"操作", field:"opt",width:150,align:"left",tooltip:false,tooltipsHeader:false,
                formatter:function(cell, formatterParams){
                    var guid = cell.getRow().getData().id;
                        return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ guid + " data-toggle='modal' data-target='#PointwarnformModel'><i class='fa fa-pencil' ></i> 编辑</button>"
                                +"<button class='btn btn-white btn-sm delete' data-id="+ guid + "><i class='fa fa-trash'></i>  删除</button></div>"
                    },headerSort:false},
                ],
            rowClick:function(e, row){
                _curRow = row;
            },
        });
    $("#Pointwarn_iboxTable").tabulator("setData",'${pageList}');
    return {
        get_curRow : function(){
            return _curRow;
        }
	 }
 })(jQuery, window, document);
 </script>