<link href="${ctx}/static/css/integration.css" rel="stylesheet">
<input id = "ammeterHiddenInput" type="text" value="" style="display:none"></input>
<div id="ammeterDiv" >
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;电表监控信息>>>
   	 </span>
	</div>
	<div class="strongAndWeakElectricityIntegrationCommon" style="margin:0px 15px;">
		<table class="layui-hide" id="ammeterTable" lay-filter="ammeterTaskTools"></table>
<#--		<script type="text/html" id="ammeterTableOperateTools">-->
<#--			&lt;#&ndash;<a class="layui-btn layui-btn-sm" lay-event="start">校对</a>-->
<#--    		<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="stop">清空</a>&ndash;&gt;-->
<#--    		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="view">查看</a>-->
<#--		</script>-->
	</div>
</div>
<!-- 下方的script作用是序号自增 -->
<script type="text/html" id="ammeterTimer">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript">
;
<!--wanghongjie -->
var ammeter = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var table;
	var layer;
	var form;
	var flag = new Date().getTime();
	var sysNames =new Set();
	var sysNameAndField = {};

	init();
	//操作查看列表功能取消，该方法暂时无用，后期根据需求适当调整。2020-8-19 16:06:38  wzx
	tableEvent();

	$(function () {
		queryAmmeterUnit(null, function (result) {
			var status = result && result.status;

			if (status !== '1'){
				return;
			}

			var data = result.data;

			if (!Array.isArray(data)) {
				return;
			}

			// 添加订阅消息
			initTable(data);

			addSubMsg();



			// 首次加载数据
			getRealTimeData(function (list) {

				for(let i = 0; i < list.length; i++) {

					var sysName = list[i].f_sys_name;
					var value = list[i].f_init_val;
					var field = sysNameAndField[sysName];

					refreshTableData(field, sysName, value);

				}

                form.render('checkbox');
			})
		})

	})

	function init() {
		layui.use(['layer','table','element', 'form'],function(){
			table = layui.table;
			layer = layui.layer;
			form = layui.form;
		});
		$('#ammeterDiv').attr('flag',flag);
	}
	//操作查看列表功能取消，该方法暂时无用，后期根据需求适当调整。2020-8-19 16:06:38  wzx
	function tableEvent() {
		table.on('tool(ammeterTaskTools)', function(obj){ //注：tool是工具条事件名，ammeterTaskTools是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data //获得当前行数据
			layer.msg('查看操作');
			viewFun(data);
		});
		form.on('switch', function(data){

			var sysName = data.elem.getAttribute('sys-name');
			var checked = data.elem.checked;
			var val = '255';

			if (!checked) {
				val = '0';
			}

			startStopControl(sysName, val);
		});
	}
	function subCallback(data)
	{
		var field = sysNameAndField[data.name];

		refreshTableData(field, data.name, data.value);

		form.render('checkbox');
	}
	// 添加订阅消息
	function addSubMsg() {
		for (let item of sysNames){
			PubSub.subscribe(item, ammeter.subCallback);

		}
	}

	// 查询设备信息
	function queryAmmeterUnit(obj, callback) {

		if(typeof callback !== 'function'){
			return;
		}

		obj = obj || {};

		$.ajax({
			type    : "POST",
			url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/ammeterConfig/query",
			dataType: "json",
			data    :  obj,
			success: function (result) {

				callback(result);

			},
			error: function (result) {

				console.warn(result)
			}
		});
	}

	function getRealTimeData(callback) {

		if (typeof callback !== "function"){
			return;
		}


		if (sysNames.size < 1) {
			return;
		}

		$.ajax({
			type    : "POST",
			url     : _ctx + "/view/basedatamanage/eqmanage/sbdy_treeIconForJK",
			dataType: "json",
			data:{
				f_sys_name: Array.from(sysNames).join()
			},
			success: function (result) {

				var ddcList = result.list || [];
				if(ddcList)

					callback(ddcList);

			},
			error: function (result) {

				console.warn(result)
			}
		});
	}


	/**
	 *  初始化数据表格
	 */
	function initTable(data){
		    table.render({
		    	elem :'#ammeterTable',
				data: data,
				done: function(res, curr, count){

					addSubMsg();
					// 首次加载数据
					getRealTimeData(function (list) {

						for(var i = 0; i < list.length; i++) {

							var sysName = list[i].f_sys_name;
							var value = list[i].f_init_val;
							var field = sysNameAndField[sysName];

							refreshTableData(field, sysName, value);

							form.render('checkbox');

						}
					})
				},
                width: $('#ammeterDiv').width() - 30,
		    	page :true,//开启分页
		    	cols:[[    //表头
					{
						title:"序号",
						templet:'#ammeterTimer'
					},
					{
						title:"名称",
						field:"name"
					},
					{
						title:"所属机柜",
						field:"cabinetName"
					},
					{
						title:"瞬时能耗",
						field:"instantEnergy",
						templet:function (item) {
							var value = item.instantEnergy;
							sysNames.add(value);
							sysNameAndField[value] = 'instantEnergy';
							return '<span id="' + value + flag + '"></span>';

						}
					},
					{
						title:"累计能耗",
						field:"totalEnergy",
						templet:function (item) {
							var value = item.totalEnergy;
							sysNames.add(value);
							sysNameAndField[value] = 'totalEnergy';
							return '<span id="' + value + flag + '"></span>';

						}
					},
					{
						title:"A相电压",
						field:"aPhaseVoltage",
						templet:function (item) {
							var value = item.aPhaseVoltage;
							sysNames.add(value);
							sysNameAndField[value] = 'aPhaseVoltage';
							return '<span id="' + value + flag + '"></span>';

						}
					},
					{
						title:"B相电压",
						field:"bPhaseVoltage",
						templet:function (item) {
							var value = item.bPhaseVoltage;
							sysNames.add(value);
							sysNameAndField[value] = 'bPhaseVoltage';
							return '<span id="' + value + flag + '"></span>';

						}
					},
					{
						title:"C相电压",
						field:"cPhaseVoltage",
						templet:function (item) {
							var value = item.cPhaseVoltage;
							sysNames.add(value);
							sysNameAndField[value] = 'cPhaseVoltage';
							return '<span id="' + value + flag + '"></span>';

						}
					},
					{
						title:"A相电流",
						field:"aPhaseCurrent",
						templet:function (item) {
							var value = item.aPhaseCurrent;
							sysNames.add(value);
							sysNameAndField[value] = 'aPhaseCurrent';
							return '<span id="' + value + flag + '"></span>';

						}
					},
					{
						title:"B相电流",
						field:"bPhaseCurrent",
						templet:function (item) {
							var value = item.bPhaseCurrent;
							sysNames.add(value);
							sysNameAndField[value] = 'bPhaseCurrent';
							return '<span id="' + value + flag + '"></span>';

						}
					},
					{
						title:"C相电流",
						field:"cPhaseCurrent",
						templet:function (item) {
							var value = item.cPhaseCurrent;
							sysNames.add(value);
							sysNameAndField[value] = 'cPhaseCurrent';
							return '<span id="' + value + flag + '"></span>';
						}
					}
					// ,
					// {
					// 	title:"操作",
					// 	width:200,
					// 	align:"center",
					// 	toolbar:'#ammeterTableOperateTools'
					// }
				]]
		   	 }
		    );
	}

	function refreshTableData(field, sysName, value) {

		if (field == null || sysName == null || value == null) {
			return;
		}

		switch(field)
		{
			case 'instantEnergy':

				var text = '';



				$('#' + sysName + flag).text(value+"  kw");

				break;
			case 'totalEnergy':

				var text = '';

				$('#' + sysName + flag).text(value+"  kwh");

				break;
			case 'aPhaseVoltage':

				var text = '';

				$('#' + sysName + flag).text(value+"  V");

				break;
			case 'bPhaseVoltage':

				var text = '';

				$('#' + sysName + flag).text(value+"  V");

				break;
			case 'cPhaseVoltage':

				var text = '';

				$('#' + sysName + flag).text(value+"  V");

				break;
			case 'aPhaseCurrent':

				var text = '';

				$('#' + sysName + flag).text(value+"  A");

				break;
			case 'bPhaseCurrent':

				var text = '';

				$('#' + sysName + flag).text(value+"  A");

				break;
			case 'cPhaseCurrent':

				var text = '';

				$('#' + sysName + flag).text(value+"  A");

				break;

		}
	}
	//操作查看列表功能取消，该方法暂时无用，后期根据需求适当调整。2020-8-19 16:06:38  wzx
	function viewFun(data){
		$("#ammeterDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/ammeter/testView',
				{
					"name":data.f_name
				});
	}

	return{
		pageInit:function(){
			initTable();
		},
		subCallback
	}

})(jQuery, window, document);
ammeter.pageInit();
</script>


