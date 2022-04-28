
<!-- <div style="height:calc(100% -60px)"> -->
<div style="height:95%">
    <div id="chilledWaterTable">	</div>
</div>

<!---分页表单----->
<div style="height:5%">
    <form action="${ctx }/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/getPaging" id="chilled_water_page_form">
        <!-- 查询条件，用隐藏表单域 -->
        <input type="hidden" value="${keywords! }" name="keywords" />
        <input type="hidden" value="${pageSize! }" id="chilled_water_page_pageSize" name="chilled_water_page_pageSize" />
        <input type="hidden" value="${pageNum! }" id="chilled_water_page_pageNum" name="chilled_water_page_pageNum" />
        <#assign formId = "chilled_water_page_form"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "chilled_water_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/><!-- 分页控键 -->
    </form>
</div>

<script type="text/javascript">

    var chilledWaterUnitPage = (function($, window, document, undefined) {

        //设置操作格式
        var optIconFunction = function(cell, formatterParams){ //plain text value
            var id = cell.getRow().getData().f_ID;
            return "<div class='btn-group '>"
                +"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#chilled_water_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
                +"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
        };

        var chilledWaterTable = new Tabulator("#chilledWaterTable", {
            height : "100%",
            layout : "fitColumns",
            columnVertAlign : "bottom",
            tooltips : true,
            selectable : 1,
            movableColumns : false,
            columns : [
                {title : "序号",field : "f_ID",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
                {title : "名称 ",field : "f_NAME",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "能耗累计 ",field : "f_NHLJ_alias",sorter : "string",align : "center",editor : false,visible : true,headerSort : false},
                //{title : "瞬时功率 ",field : "f_SSGL_alias",sorter : "string",align : "center",editor : false,headerSort : false},
                // {title : "A相电流",field : "f_AXDL_alias",sorter : "string",align : "center",visible : true,editor : false,headerSort : false},
                // {title : "B相电流",field : "f_BXDL_alias",sorter : "string",align : "center",visible : true,editor : false,headerSort : false},
                // {title : "C相电流",field : "f_CXDL_alias",sorter : "string",align : "center",visible : true,editor : false,headerSort : false},
                // {title : "冷冻设备工作模式",field : "f_SBGZMS_alias",sorter : "string",align : "center",visible : true,editor : false,headerSort : false},
                {title : "冷冻设备故障状态",field : "f_SBGZZT_alias",sorter : "string",align : "center",visible : true,editor : false,headerSort : false},
                {title : "冷冻设备请求信号",field : "f_SBQQXH_alias",sorter : "string",align : "center",visible : true,editor : false,headerSort : false},
                {title : "设备运行时间",field : "f_SBYXSJ_alias",sorter : "string",align : "center",visible : true,editor : false,headerSort : false},
                {title : "水阀开到位",field : "f_SFKDW_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                //{title : "水阀关到位",field : "f_SFGDW_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                {title : "变频器运行状态",field : "f_BPYXZT_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                //{title : "变频器故障状态",field : "f_BPGZZT_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                {title : "频率反馈",field : "f_BPQPLFK_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                {title : "水流状态",field : "f_SLZT_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                //{title : "水阀故障状态",field : "f_SFGZZT_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                {title : "水泵运行状态",field : "f_SBYXZT_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                {title : "运行指示灯",field : "f_YXZSD_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                {title : "故障指示灯",field : "f_GZZSD_alias",sorter : "string",align : "center",editable : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
            ],
            rowClick : function(e, row) {

            }
        });

        chilledWaterTable.setData('${dataset}'==""?[]:'${dataset}');


    })(jQuery, window, document);

</script>