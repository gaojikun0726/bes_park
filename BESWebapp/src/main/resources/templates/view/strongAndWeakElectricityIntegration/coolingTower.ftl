<link href="${ctx}/static/css/integration.css" rel="stylesheet">
<input id ="coolingTowerHiddenInput" type="text" value="" style="display:none"></input>
<div id="coolingTowerDiv">
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;冷却塔监控信息>>>
    	</span>
	</div>
	<div class="strongAndWeakElectricityIntegrationCommon" style="margin:0px 15px;">
		<table class="layui-hide" id="coolingTowerTable" lay-filter="coolingTowerTaskTools"></table>
		<script type="text/html" id="coolingTowerTableOperateTools">
    		<a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="view">查看</a >
		</script>
	</div>
</div>
<!-- 下方的script作用是序号自增 -->
<script type="text/html" id="coolingTowerTimer">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type='text/html' id='toolDemo3'>
	<a class='layui-btn  layui-btn-primary layui-btn-xs' lay-event='openAll'>展开全部</a>
	<a class='layui-btn  layui-btn-primary layui-btn-xs' lay-event='closeAll'>关闭全部</a>
	<a class='layui-btn  layui-btn-primary layui-btn-xs' lay-event='opOrientation'>(水平|垂直)排列</a>
</script>
<script type="text/javascript">

var coolingTower  = (function($, window, document, undefined) {
    var _ctx = '${ctx}';
    var table;
    var layer;
    var form;
    var eqName;
    var flag = new Date().getTime();
    var sysNames = new Set();
    var sysNameAndField = {};


	// init();

	tableEvent();
	$(function () {

		queryCoolingHeatingUnit(null, function (result) {

			var status = result && result.status;

			if (status !== '1'){
				return;
			}

			var data = result.data;

			if (!Array.isArray(data)) {
				return;
			}


			initTable(data);
			// 添加订阅消息
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
            PubSub.subscribe(item, coolingTower.subCallback);
        }
    }


    function init(data) {
        /*layui.use(['layer','table','element', 'form'],function(){
            table = layui.table;
            layer = layui.layer;
            form = layui.form;
        });*/

		layui.use(['opTable'],function () {
			var opTabled = layui.opTable.render({
			elem :'#coolingTowerTable',
			id: '#coolingTowerTable',
			toolbar: '#toolDemo3',
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
			width: $('#coolingTowerDiv').width() - 30,
			cols:[[    //表头
				{
					title:"序号",
					templet:'#coolingTowerTimer'
				},
				{
					title:"名称",
					field:"name"
				},
				{
					title:"冷却塔故障状态",
					field:"faultState",
					align: "center",
					templet:function (item) {

						var value = item.faultState;
						sysNames.add(value);
						sysNames.add(item.operationHours);
						sysNames.add(item.inletValveOpenArrives);
						sysNames.add(item.inletValveCloseArrives);
						sysNames.add(item.outletValveOpenArrives);
						sysNames.add(item.outletValveCloseArrives);
						sysNames.add(item.inletValveFault);
						sysNames.add(item.outletValveFault);
						sysNames.add(item.fanFault);
						sysNames.add(item.operatingMode);
						sysNameAndField[value] = 'faultState';
						return '<div id="' + value + flag + '"></div>'
					}
				},
				{
					title:"风机运行状态",//DI 运行或故障
					field:"runningStatus",
					templet:function (item) {
						var value = item.runningStatus;
						sysNames.add(value);
						sysNameAndField[value] = 'runningStatus';
						return '<div id="' + value + flag + '"></div>'
					}
				},
				{
					title:"操作",
					width:200,
					align:"center",
					toolbar:'#coolingTowerTableOperateTools'
				}
			]]
				//  展开的列配置
				, openCols: [
					{
						title:"冷却塔运行时间",
						field:"operationHours",
						align: "center",
						templet:function (item) {

							var value = item.operationHours;
							sysNames.add(value);
							sysNameAndField[value] = 'operationHours';
							return '<span class="opTable-item-title">冷却塔运行时间:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';

						}
					},
					{
						title:"进水阀开到位",//DI 运行或故障
						field:"inletValveOpenArrives",
						templet:function (item) {
							var value = item.inletValveOpenArrives;
							sysNames.add(value);
							sysNameAndField[value] = 'inletValveOpenArrives';
							return '<span class="opTable-item-title">进水阀开到位:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"进水阀关到位",//DI 运行或故障
						field:"inletValveCloseArrives",
						templet:function (item) {
							var value = item.inletValveCloseArrives;
							sysNames.add(value);
							sysNameAndField[value] = 'inletValveCloseArrives';
							return '<span class="opTable-item-title">进水阀关到位:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"进水阀故障",//DI 运行或故障
						field:"inletValveFault",
						templet:function (item) {
							var value = item.inletValveFault;
							sysNames.add(value);
							sysNameAndField[value] = 'inletValveFault';
							return '<span class="opTable-item-title">进水阀故障:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"出水阀开到位",//DI 运行或故障
						field:"outletValveOpenArrives",
						templet:function (item) {
							var value = item.outletValveOpenArrives;
							sysNames.add(value);
							sysNameAndField[value] = 'outletValveOpenArrives';
							return '<span class="opTable-item-title">出水阀开到位:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"出水阀关到位",//DI 运行或故障
						field:"outletValveCloseArrives",
						templet:function (item) {
							var value = item.outletValveCloseArrives;
							sysNames.add(value);
							sysNameAndField[value] = 'outletValveCloseArrives';
							return '<span class="opTable-item-title">出水阀关到位:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"出水阀故障",//DI 运行或故障
						field:"outletValveFault",
						templet:function (item) {
							var value = item.outletValveFault;
							sysNames.add(value);
							sysNameAndField[value] = 'outletValveFault';
							return '<span class="opTable-item-title">出水阀故障:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"风机故障",//DI 运行或故障
						field:"fanFault",
						templet:function (item) {
							var value = item.fanFault;
							sysNames.add(value);
							sysNameAndField[value] = 'fanFault';
							return '<span class="opTable-item-title">风机故障:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"冷却塔工作模式",
						field:"operatingMode",
						align: "center",
						templet:function (item) {
							var value = item.operatingMode;
							sysNames.add(value);
							sysNameAndField[value] = 'operatingMode';
							return '<span class="opTable-item-title">冷却塔工作模式:</span>' +
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

							})
						}

					}
				],openType: 1
		});
			layui.table.on('toolbar(coolingTowerTaskTools)', function (obj) {
				if (obj.event === 'openAll') {
					opTabled.openAll();
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
					opTabled.closeAll();
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
					opTabled.reload({opOrientation: opTabled.config.opOrientation === "v" ? "h" : "v"});
					setTimeout(function () {
						// 展开第一行
						opTabled.openIndex(0);
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
        $('#coolingTowerDiv').attr('flag', flag);
    }


    /**
     *  初始化数据表格
     */
    function initTable(data){

		init(data);

    }


    function tableEvent() {
		layui.table.on('tool(coolingTowerTaskTools)', function(obj){
            var data = obj.data; //获得当前行数据
			layui.layer.msg('查看操作');
            viewFun(data);
        });

		layui.form.on('switch(lqt)', function(data){

            var sysName = data.elem.getAttribute('sys-name');
            var checked = data.elem.checked;
            var val = '255';

            if (!checked) {
                val = '0';
            }

            startStopControl(sysName, val);
        });
    }


    // 查询设备信息
    function queryCoolingHeatingUnit(obj, callback) {

        if(typeof callback !== 'function'){
            return;
        }

        obj = obj || {};

        $.ajax({
            type    : "POST",
            url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/coolingTowerConfig/query",
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


    function startStopControl(sysName, val) {

        if (!sysName || !val) {
            return;
        }

        $.ajax({
            url : _ctx + "/api/debugPointInfo",
            type : "post",
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            data : JSON.stringify({
                f_sys_name : sysName,
                f_init_val : val,
                f_work_mode: "1"
            }),
            success : function(result) {

				layui.layer.msg(result.msg);

				if(result.status !== '1'){
					var html = '';

					if(val === '0') {
						html = '<input type="checkbox" sys-name="'+ sysName +'" lay-filter="lqt" lay-skin="switch" lay-text="开启|关闭" checked>';
					}else if (val === '255') {
						html = '<input type="checkbox" sys-name="'+ sysName +'" lay-filter="lqt" lay-skin="switch" lay-text="开启|关闭">';
					}

					$('#' + sysName + flag).html(html);

					layui.form.render('checkbox');
				}
            },
            error : function(result) {
				layui.layer.msg(result.msg);
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

                callback(ddcList);

            },
            error: function (result) {

                console.warn(result)
            }
        });
    }

    function refreshTableData(field, sysName, value) {
        if (field == null || sysName == null || value == null) {
            return;
        }

        switch(field)
        {
			case 'operatingMode':

				var text = '';

				if(value === '255') {
					text = '远程';
				}else if (value === '0') {
					text = '就地';
				}

				else {
					text = '测试';
				}

				$('#' + sysName + flag).text(text);


				break;
			case 'faultState':

				var text = '';

				if (value === '0') {
					text = '正常';
				}else {
					text = '故障';
				}

				$('#' + sysName + flag).text(text);


				break;
			case 'operationHours':

				var month = parseInt(value / 30 / 60 / 24);
				var day = parseInt(value / 60 / 24);
				var hour = parseInt(value / 60 % 24);
				var min = parseInt(value % 60);
				var  StatusMinute = "";
				if (month > 0) {
					StatusMinute = month + "个月";
				}
				if (day > 0) {
					StatusMinute = day + "天";
				}
				if (hour > 0) {
					StatusMinute += hour + "小时";
				}
				if (min > 0) {
					StatusMinute += parseFloat(min) + "分钟";
				}
				//三元运算符 传入的分钟数不够一分钟 默认为0分钟，else return 运算后的StatusMinute
				StatusMinute == "" ? "0分钟": StatusMinute;

				$('#' + sysName + flag).text(StatusMinute);
				break;
			case 'inletValveOpenArrives':

				var text = '';

				if(value === '255') {
					text = '已开启';
				}else if (value === '0') {
					text = '未开启';
				}

				else {
					text = '测试';
				}

				$('#' + sysName + flag).text(text);


				break;
			case 'inletValveCloseArrives':

				var text = '';

				if(value === '255') {
					text = '已关闭';
				}else if (value === '0') {
					text = '未关闭';
				}

				else {
					text = '测试';
				}

				$('#' + sysName + flag).text(text);


				break;
			case 'outletValveOpenArrives':

				var text = '';

				if(value === '255') {
					text = '已开启';
				}else if (value === '0') {
					text = '未开启';
				}

				else {
					text = '测试';
				}

				$('#' + sysName + flag).text(text);


				break;
			case 'outletValveCloseArrives':

				var text = '';

				if(value === '255') {
					text = '已关闭';
				}else if (value === '0') {
					text = '未关闭';
				}

				else {
					text = '测试';
				}

				$('#' + sysName + flag).text(text);


				break;
			case 'inletValveFault':

				var text = '';

				if(value === '255') {
					text = '故障';
				}else if (value === '0') {
					text = '正常';
				}

				else {
					text = '测试';
				}

				$('#' + sysName + flag).text(text);


				break;
			case 'outletValveFault':

				var text = '';

				if(value === '255') {
					text = '故障';
				}else if (value === '0') {
					text = '正常';
				}
				else {
					text = '测试';
				}


				$('#' + sysName + flag).text(text);


				break;
			case 'fanFault':

				var text = '';

				if(value === '255') {
					text = '故障';
				}else if (value === '0') {
					text = '正常';
				}

				else {
					text = '测试';
				}

				$('#' + sysName + flag).text(text);


				break;
			case 'runningStatus':

				var text = '';

				if(value === '255') {
					text = '运行';
				}else if (value === '0') {
					text = '停止';
				}
				else {
					text = '测试';
				}


				$('#' + sysName + flag).text(text);


				break;

        }
    }


    function viewFun(data){
    	eqName = data.id;
        $("#coolingTowerHiddenInput").val(eqName);
        $("#coolingTowerDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/coolingTower/testView',
                {
                    "name": data.name,
                    "id": data.id,
                    "electric_meter_number": data.electric_meter_number,
                    "fanSwitch": $('#' + data.fanSwitch + flag + ' em').text()
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


