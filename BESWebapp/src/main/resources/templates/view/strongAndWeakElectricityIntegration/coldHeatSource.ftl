<link href="${ctx}/static/css/integration.css" rel="stylesheet">
<input id = "coldHeatSourceHiddenInput" type="text" value="" style="display:none"></input>
<div id="coldHeatSourceDiv">
	<div class="information-model">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;冷热机组监控信息>>>
	</div>
	<div class="strongAndWeakElectricityIntegrationCommon" style="margin:0px 15px;">
		<table class="layui-hide" id="coldHeatSourceTable" lay-filter="coldHeatSourceTaskTools"></table>
		<script type="text/html" id="coldHeatSourceTableOperateTools">
			<#--<a class="layui-btn layui-btn-sm" lay-event="start" >启动</a>
    		<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="stop">停止</a>-->
    		<a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="view" οnclick="testClick(this)">查看</a>
		</script>
	</div>
</div>

<!-- 下方的script作用是序号自增 -->
<script type="text/html" id="coldHeatSourceTimer">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type='text/html' id='toolDemo'>
	<a class='layui-btn  layui-btn-primary layui-btn-xs' lay-event='openAll'>展开全部</a>
	<a class='layui-btn  layui-btn-primary layui-btn-xs' lay-event='closeAll'>关闭全部</a>
	<a class='layui-btn  layui-btn-primary layui-btn-xs' lay-event='opOrientation'>(水平|垂直)排列</a>
</script>
<script type="text/javascript">
;
<!--wanghongjie -->
var coldHeatSource = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var table;
	var layer;
	var form;
	var eqName;
	var flag = new Date().getTime();
    var sysNames = new Set();
	var sysNameAndField = {};

	$(function () {
		// init();

		tableEvent();

        queryCoolingHeatingUnit(null, function (result) {

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

                layui.form.render('checkbox');
			})
		});
    });

	function subCallback(data)
    {
        var field = sysNameAndField[data.name];

        refreshTableData(field, data.name, data.value);

        layui.form.render('checkbox');
    }

	// 添加订阅消息
	function addSubMsg() {
        for (let item of sysNames){
            PubSub.subscribe(item, coldHeatSource.subCallback);
        }
	}

	function init(data) {
		/*layui.use(['layer','table','element', 'form'],function(){
			table = layui.table;
			layer = layui.layer;
			form = layui.form;
		});*/
		layui.use(['opTable'],function () {

			/*setTimeout(function () {
				layui.layer.msg("Hello Word . Welcome to OPTable ! ");
			}, 400);*/

			var opTablea = layui.opTable.render({
				elem :'#coldHeatSourceTable',
				id: '#coldHeatSourceTable',
				toolbar: '#toolDemo',
				defaultToolbar:  ['print'],
				page :false,//开启分页
				data: data,
				limit: data.length,
				done: function(res, curr, count){

					addSubMsg();
					// 首次加载数据
					getRealTimeData(function (list) {

						for(var i = 0; i < list.length; i++) {

							var sysName = list[i].f_sys_name;
							var value = list[i].f_init_val;
							var field = sysNameAndField[sysName];

							refreshTableData(field, sysName, value);

						}

						layui.form.render('checkbox');
					})
				},
				width: $('#coldHeatSourceDiv').width() - 30,
				cols:[[    //表头
					{
						title:"序号",
						templet:'#coldHeatSourceTimer'
					},
					{
						title:"名称",
						field:"name"
					},
					{
						title:"运行状态",
						field:"currentState",
						align: "center",
						templet:function (item) {
							var value = item.currentState;
							sysNames.add(value);
							sysNameAndField[value] = 'currentState';
							return '<div id="'+ value + flag +'"></div>'

						}
					},
					{
						title:"总故障状态",
						field:"currentFlow",
						align: "center",
						templet:function (item) {
							var value = item.currentFlow;
							sysNames.add(value);
							sysNameAndField[value] = 'currentFlow';
							return '<span id="' + value + flag + '"></span>';

						}
					},
					{
						title:"操作",
						width:200,
						align:"center",
						toolbar:'#coldHeatSourceTableOperateTools'
					}
				]]
				//  展开的列配置
				, openCols: [
					{
						title:"冷冻水供水温度",
						field:"freezeSupplyWaterTemperature",
						align: "center",
						templet:function (item) {
							var value = item.freezeSupplyWaterTemperature;
							sysNames.add(value);
							sysNameAndField[value] = 'freezeSupplyWaterTemperature';
							return '<span class="opTable-item-title">冷冻水供水温度:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';

						}
					},
					{
						title:"冷冻水回水温度",
						field:"freezeReturnWaterTemperature",
						align: "center",
						templet:function (item) {
							var value = item.freezeReturnWaterTemperature;
							sysNames.add(value);
							sysNameAndField[value] = 'freezeReturnWaterTemperature';
							return '<span class="opTable-item-title">冷冻水回水温度:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';

						}
					},
					{
						title:"冷却水供水温度",
						field:"coolingSupplyWaterTemperature",
						align: "center",
						templet:function (item) {
							var value = item.coolingSupplyWaterTemperature;
							sysNames.add(value);
							sysNameAndField[value] = 'coolingSupplyWaterTemperature';
							return '<span class="opTable-item-title">冷却水供水温度:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';

						}
					},
					{
						title:"冷却水回水温度",
						field:"coolingReturnWaterTemperature",
						align: "center",
						templet:function (item) {
							var value = item.coolingReturnWaterTemperature;
							sysNames.add(value);
							sysNameAndField[value] = 'coolingReturnWaterTemperature';
							return '<span class="opTable-item-title">冷却水回水温度:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';

						}
					},{

						templet : function(){
							addSubMsg();
							// 首次加载数据
							getRealTimeData(function (list) {
								for(var i = 0; i < list.length; i++) {

									var sysName = list[i].f_sys_name;
									var value = list[i].f_init_val;
									var field = sysNameAndField[sysName];

									refreshTableData(field, sysName, value);

								}

								layui.form.render('checkbox');
							})
						}

					}
				]
				, openType: 1
			});

			layui.table.on('toolbar(coldHeatSourceTaskTools)', function (obj) {
				if (obj.event === 'openAll') {
					opTablea.openAll();
					// 首次加载数据
					getRealTimeData(function (list) {
						for(var i = 0; i < list.length; i++) {

							var sysName = list[i].f_sys_name;
							var value = list[i].f_init_val;
							var field = sysNameAndField[sysName];

							refreshTableData(field, sysName, value);

						}

						layui.form.render('checkbox');
					})
					// layui.layer.msg("展开全部 isOpenAll: " + opTablea.isOpenAll());
				} else if (obj.event === 'closeAll') {
					opTablea.closeAll();
					// 首次加载数据
					getRealTimeData(function (list) {
						for(var i = 0; i < list.length; i++) {

							var sysName = list[i].f_sys_name;
							var value = list[i].f_init_val;
							var field = sysNameAndField[sysName];

							refreshTableData(field, sysName, value);

						}

						layui.form.render('checkbox');
					})
					// layui.layer.msg("关闭全部 isOpenAll: " + opTablea.isOpenAll())
				}
				//水平或垂直排列内容
				else if (obj.event === "opOrientation") {
					opTablea.reload({opOrientation: opTablea.config.opOrientation === "v" ? "h" : "v"});
					setTimeout(function () {
						// 展开第一行
						opTablea.openIndex(0);
						getRealTimeData(function (list) {
							for(var i = 0; i < list.length; i++) {

								var sysName = list[i].f_sys_name;
								var value = list[i].f_init_val;
								var field = sysNameAndField[sysName];

								refreshTableData(field, sysName, value);

							}

							layui.form.render('checkbox');
						})
					}, 40)
				}
			});

		});
		$('#coldHeatSourceDiv').attr('flag', flag);
	}

	function tableEvent() {
		layui.table.on('tool(coldHeatSourceTaskTools)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
				layui.layer.msg('查看操作');
				viewFun(data);
			});
		/*form.on('switch(lrjz)', function(data){

			var sysName = data.elem.getAttribute('sys-name');
			var checked = data.elem.checked;
			var val = '255';

			if (!checked) {
				val = '0';
			}

			startStopControl(sysName, val);
		});*/
	}

    // 查询设备信息
    function queryCoolingHeatingUnit(obj, callback) {

        if(typeof callback !== 'function'){
            return;
        }

        obj = obj || {};

        $.ajax({
            type    : "POST",
			url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingHeatingUnit/query",
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
				{
                    callback(ddcList);
				}

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
		init(data);
	}

	function refreshTableData(field, sysName, value) {

		if (field == null || sysName == null || value == null) {
			return;
		}

		switch(field)
		{
			case 'currentState':

				var html = '';

				/*if(value === '255') {
					html = '<input type="checkbox" sys-name="'+ sysName +'" lay-filter="lrjz" lay-skin="switch" lay-text="开启|关闭" checked>';
				}else if (value === '0') {
					html = '<input type="checkbox" sys-name="'+ sysName +'" lay-filter="lrjz" lay-skin="switch" lay-text="开启|关闭">';
				}*/

				if(value === '255') {
					html = '运行';
				}else if (value === '0') {
					html = '停止';
				}

				$('#' + sysName + flag).html(html);

				break;
			case 'freezeSupplyWaterTemperature':

				$('#' + sysName + flag).html(value+"℃");

				break;
			case 'freezeReturnWaterTemperature':


				$('#' + sysName + flag).html(value+"℃");

				break;
			case 'coolingSupplyWaterTemperature':


				$('#' + sysName + flag).text(value+"℃");

				break;
			case 'coolingReturnWaterTemperature':


				$('#' + sysName + flag).text(value+"℃");

				break;
			case 'currentFlow':

				if(value === '0') {
					html = '正常';
				}else if (value !== '0') {
					html = '故障';
				}
				// $('#' + sysName + flag).text(value);
				$('#' + sysName + flag).html(html);
				break;

		}
	}


	function viewFun(data){
		eqName = data.id;
		$("#coldHeatSourceHiddenInput").val(eqName);
		$("#coldHeatSourceDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/coldHeatSource/testView',
				{
					"name": data.name,
					"id": data.id,
					"electric_meter_number": data.electric_meter_number,
					"currentState": $('#' + data.currentState + flag + ' em').text()
				});

	}

	return {
        subCallback,
		// 当展开全部时回调
		onOpenAll: function () {
			console.log("onOpenAll")
		},
		// 当关闭全部时回调
		onCloseAll: function () {
			console.log("onCloseAll")
		},
	}


})(jQuery, window, document);
</script>


