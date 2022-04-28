
   <!-- <div style="height:calc(100% -60px)"> -->
   <div style="height:95%">
		<div id="airconditioningUnitConfigTable">	</div>
   </div>

   <!---分页表单----->
   <div style="height:5%">
        <form action="${ctx }/view/app/airconditioningUnitConfig/getPaging" id="airconditioningunit_paging_form">
				<!-- 查询条件，用隐藏表单域 -->
				<input type="hidden" value="${keywords! }" name="keywords" />
				<input type="hidden" value="${pageSize! }" id="airconditioningunit_pageSize" name="airconditioningunit_pageSize" />
				<input type="hidden" value="${pageNum! }" id="airconditioningunit_pageNum" name="airconditioningunit_pageNum" />
				<#assign formId = "airconditioningunit_paging_form"><!-- formId: 分页控件表单ID -->
				<#assign showPageId = "airconditioningunit_page"><!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
				<#include "/common/page.ftl"/><!-- 分页控键 -->
		</form>
   </div>

<script type="text/javascript">

var airconditioningUnitConfigPage = (function($, window, document, undefined) {

		//设置操作格式
		var optIconFunction = function(cell, formatterParams){ //plain text value
			var id = cell.getRow().getData().id;
			return "<div class='btn-group '>"
					+"<button class='btn btn-white btn-sm edit' data-id="+ id + " data-toggle='modal' data-target='#airconditioningunit_modal_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
					+"<button class='btn btn-white btn-sm delete' data-id=" + id + "><i class='fa fa-trash'></i>  删除</button></div>"
		};

		var airconditioningUnitConfigTable = new Tabulator("#airconditioningUnitConfigTable", {
			height : "100%",
			layout : "fitColumns",
			columnVertAlign : "bottom",
			tooltips : true,
			selectable : 1,
			movableColumns : false,
			columns : [
			    {title : "序号",field : "id",formatter : "rownum", width: 70, frozen : false,align : "center", headerSort : false,tooltipsHeader : false,tooltip : false},
				{title : "空调名称",field : "name",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "空调位置",field : "addresss",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "新风机启停",field : "new_fan_start_and_stop_display",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "送风机运行状态（运行、停止）",field : "operation_status_of_blower_display",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "送风机手自动状态",field : "manual_automatic_state_of_blower_display",sorter : "string",align : "center",editor : false,headerSort : false},
				{title : "送风机故障状态（正常、故障）",field : "blower_failure_status_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风阀开度控制",field : "fresh_air_valve_opening_control_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风阀开度反馈",field : "fresh_air_valve_opening_feedback_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风阀开（开启、关闭）",field : "fresh_air_valve_open_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风阀关（开启、关闭）",field : "fresh_air_valve_closed_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风阀开到位（正常、异常）",field : "fresh_air_valve_open_in_place_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风阀关到位（正常、异常）",field : "fresh_air_valve_closed_in_place_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "回风机启停",field : "return_fan_start_and_stop_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "回风机运行状态（运行、停止）",field : "operation_status_of_return_fan_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "回风机手自动状态",field : "manual_automatic_state_of_the_return_fan_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "回风机故障状态（正常、故障）",field : "return_fan_failure_status_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "排风阀开度控制",field : "exhaust_valve_opening_control_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "排风阀开度反馈",field : "exhaust_valve_opening_feedback_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "排风阀开（开启、关闭）",field : "exhaust_valve_open_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "排风阀关（开启、关闭）",field : "exhaust_valve_closed_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "排风阀开到位（正常、异常）",field : "the_exhaust_valve_is_open_in_place_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "排风阀关到位（正常、异常）",field : "the_exhaust_valve_is_closed_in_place_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "送风温度（℃）",field : "supply_air_temperature_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "送风湿度（%）",field : "supply_air_humidity_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风温度（℃）",field : "fresh_air_temperature_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "新风湿度（%）",field : "fresh_air_humidity_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "回风温度（℃）",field : "return_air_temperature_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "回风湿度（%）",field : "return_air_humidity_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "二氧化碳(CO2)（ppm）",field : "carbon_dioxide_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿开度控制",field : "humidification_opening_control_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿开度反馈",field : "humidification_opening_feedback_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿开（开启、关闭）",field : "humidification_on_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿关（开启、关闭）",field : "humidification_off_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿开到位（正常、异常）",field : "humidification_valve_open_in_place_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "加湿关到位（正常、异常）",field : "humidification_valve_closed_in_place_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "防冻报警（正常、异常）",field : "antifreeze_alarm_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "初效过滤网压差报警（正常、异常）",field : "initial_filter_screen_pressure_difference_alarm_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "中效过滤网压差报警（正常、异常）",field : "mediumefficiency_filter_pressure_difference_alarm_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "回风过滤网压差报警（正常、异常）",field : "return_air_filter_pressure_difference_alarm_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "送风机压差（正常、异常）",field : "pressure_difference_of_blower_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "回风机压差（正常、异常）",field : "return_fan_pressure_difference_display",sorter : "string",align : "center",editor : false,headerSort : false},
                {title : "操作", field:"opt",width:150,align:"center",tooltip:false,tooltipsHeader:false,formatter:optIconFunction,headerSort:false}
				],
			rowClick : function(e, row) {

			}
		});

    airconditioningUnitConfigTable.setData('${dataset}'==""?[]:'${dataset}');


})(jQuery, window, document);

</script>
