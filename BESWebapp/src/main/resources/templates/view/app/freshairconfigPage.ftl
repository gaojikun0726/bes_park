
<!-- <div style="height:calc(100% -60px)"> -->
<div style="height:95%">
    <div id="freshConfigTable">	</div>
</div>

<!---分页表单----->
<div style="height:5%">
    <form action="${ctx }/view/BESFreshairconfigController/selectFreshair" id="freshair_paging_form">
        <!-- 查询条件，用隐藏表单域 -->
        <input type="hidden" value="${keywords! }" name="keywords" />
        <input type="hidden" value="${pageSize! }" id="freshair_pageSize" name="freshair_pageSize" />
        <input type="hidden" value="${pageNum! }" id="freshair_pageNum" name="freshair_pageNum" />
        <#assign formId = "freshair_paging_form"><!-- formId: 分页控件表单ID -->
        <#assign showPageId = "fres_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <#include "/common/page.ftl"/><!-- 分页控键 -->
    </form>
</div>

<script type="text/javascript">

    var ammeterConfigPage = (function($, window, document, undefined) {

        //设置操作格式
        var optIconFunction = function(cell, formatterParams){ //plain text value
            var id = cell.getRow().getData().id;
            return "<div class='btn-group '>"
                +"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#fres_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
                +"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
        };

        var freshConfigTable = new Tabulator("#freshConfigTable", {
            height : "100%",
            layout : "fitColumns",
            columnVertAlign : "bottom",
            tooltips : true,
            selectable : 1,
            movableColumns : false,
            columns : [
                {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
                {title : "名称",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风位置",field : "freshair_address",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "机组启停",field : "unit_start_and_stop_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "机组运行状态（运行、停止）",field : "unit_operating_status_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "机组手自动状态",field : "crew_manual_status_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "机组故障状态（正常、故障）",field : "unit_fault_status_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "风阀开度控制",field : "damper_opening_control_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "风阀开度反馈",field : "damper_opening_feedback_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "风阀开（开启、关闭）",field : "damper_open_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "风阀关（开启、关闭）",field : "damper_off_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "风阀开到位（正常、异常）",field : "air_valve_opens_in_place_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "风阀关到位（正常、异常）",field : "air_valve_closed_in_place_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "水阀开度控制",field : "water_valve_opening_control_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "水阀开度反馈",field : "water_valve_opening_feedback_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "送风温度（℃）",field : "supply_air_temperature_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "送风湿度（%）",field : "supply_air_humidity_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风温度（℃）",field : "fresh_air_temperature_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风湿度（%）",field : "fresh_air_humidity_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿开度控制",field : "humidification_opening_control_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿开度反馈",field : "humidification_opening_feedback_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿开（开启、关闭）",field : "humidification_on_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿关（开启、关闭）",field : "humidification_off_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "二氧化碳（ppm）",field : "carbon_dioxide_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "防冻报警（正常、异常）",field : "antifreeze_alarm_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "过滤网压差报警（正常、异常）",field : "filter_screen_pressure_difference_alarm_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "风机压差（正常、异常）",field : "fan_pressure_difference_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
            ],
            rowClick : function(e, row) {

            }
        });

        freshConfigTable.setData('${dataset}'==""?[]:'${dataset}');


    })(jQuery, window, document);

</script>
