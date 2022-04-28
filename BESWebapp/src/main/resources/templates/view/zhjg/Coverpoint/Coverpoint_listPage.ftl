<#--
  描述:智慧井盖设备维护
  作者:YangChao
  时间:2020-10-09 14:28:58
-->
<style type="text/css">
    #Coverpoint_iboxTable .btn {
        line-height: 50%;
    }
</style>
<div style="height:95%">
    <div id="Coverpoint_iboxTable"></div>
</div>
<#---分页表单----->
<div style="height:30px">
    <form action="${ctx}/zhjg/coverpoint/getSearch" id="CoverpointModulePageForm">
        <!-- 查询条件，用隐藏表单域 -->
        <#assign formId = "CoverpointModulePageForm"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "Coverpoint_ibox"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
    </form>
</div>

 <script type="text/javascript">
;var zhjg_Coverpoint_listModuleClosurePage = (function($, window, document, undefined){

    //创建并设置table属性
    $("#Coverpoint_iboxTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns，表格将调整列的大小，以使其完全适合容器的宽度
            columnVertAlign:"bottom",
            tooltips:true,
            selectable : 1,
            movableColumns:true,
            columns:[
            {title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false},
            {title:"设备卡号",field:"fCode",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"设备名称",field:"fPointName",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"设备IP",field:"fPointIp",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"经度",field:"fLongitude",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"纬度",field:"fLatitude",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"详细地址",field:"fRemark",sorter:"string",editor:false,align:"center",headerSort:false},
            {title:"设备状态",field:"fStatus",sorter:"string",editor:false,align:"center",headerSort:false,formatter:function(cell, formatterParams){
                var status = cell.getRow().getData().fStatus;
                if(status=='0'){
                    return "<span style='color: #00FF00'>在线</span>";
                }else if(status == '1'){
                    return "<span style='color: #FFFF00'>告警</span>";
                }else{
                    return "<span style='color: #C0C0C0'>离线</span>";
                }
            }},
            {title:"操作", field:"opt",width:150,align:"left",tooltip:false,tooltipsHeader:false,
            formatter:function(cell, formatterParams){
                var guid = cell.getRow().getData().fGuid;
                    return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ guid + " data-toggle='modal' data-target='#CoverpointformModel'><i class='fa fa-pencil' ></i> 编辑</button>"
                            +"<button class='btn btn-white btn-sm delete' data-id="+ guid + "><i class='fa fa-trash'></i>  删除</button></div>"
                },headerSort:false},
            ],
            rowClick:function(e, row){
                _curRow = row;
            },
        });
    $("#Coverpoint_iboxTable").tabulator("setData",'${pageList}');
    return {
        get_curRow : function(){
            return _curRow;
        }
	 }
 })(jQuery, window, document);
 </script>