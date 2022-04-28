<#--
  描述:设备列表
  作者:YangChao
  时间:2020-09-18 18:36:45
-->
<div style="height:95%">
    <div id="PointWarnList_iboxTable"></div>
</div>
<#---分页表单----->
<div style="height:30px;position:relative;">
    <form action="${ctx}/zhdg/point/getSearch" id="PointWarnListModulePageForm" style="padding-right: 5vw;">
        <!-- 查询条件，用隐藏表单域 -->
        <#assign formId = "PointWarnListModulePageForm"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "PointwarnList"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/>
    </form>
</div>

 <script type="text/javascript">
;var zhdg_Pointwarnpage_listModuleClosure = (function($, window, document, undefined){

    //创建并设置table属性
    $("#PointWarnList_iboxTable").tabulator({
            height:"100%",
            layout:"fitColumns",//fitColumns，表格将调整列的大小，以使其完全适合容器的宽度
            columnVertAlign:"bottom",
            tooltips:true,
            selectable : 1,
            movableColumns:true,
            selectable:true,
            selectableRangeMode:"click",
            columns:[
                {title:"序号",field:"id",width:70,frozen:false, editor:false,formatter : "rownum",align : "center",headerSort:false,tooltip:false,tooltipsHeader:false},
                {title:"唯一编码",field:"fCode",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"设备名称",field:"fPointName",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"设备IP",field:"fPointIp",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"所属区域",field:"fRegionName",sorter:"string",editor:false,align:"center",headerSort:false},
	            {title:"详细地址",field:"fRemark",sorter:"string",editor:false,align:"center",headerSort:false},
            ],
            rowClick:function(e, row){
                _curRow = row;
            },
        });
    $("#PointWarnList_iboxTable").tabulator("setData",'${pageList}');

    function getSelectedData() {
        var obj = $("#PointWarnList_iboxTable").tabulator("getSelectedData");
        var list = [];
        for(var i =0;i<obj.length;i++){
            var fCode = obj[i].fCode;
            var fPointName = obj[i].fPointName;
            list.push(fCode);
        }
        return list;
    }

    return {
        get_curRow : function(){
            return _curRow;
        },
        getSelectedData : function () {
            return getSelectedData();
        }

	 }
 })(jQuery, window, document);
 </script>