<link href="${ctx}/static/css/integration.css" rel="stylesheet">
<input id = "waterCoolantHiddenInput" type="text" value="" style="display:none"></input>
<div id="waterCoolantDiv">
	<div class="information-model">
		<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;冷却水监控信息>>>
	</div>
	<div class="strongAndWeakElectricityIntegrationCommon" style="margin:0px 15px;">
		<table class="layui-hide" id="waterCoolantTable_monitoring" lay-filter="waterCoolantTaskTools"></table>
		<script type="text/html" id="waterCoolantTableOperateTools">
			<a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="view" οnclick="testClick(this)">查看</a>
		</script>
	</div>
</div>

<!-- 下方的script作用是序号自增 -->
<script type="text/html" id="waterCoolantTimer">
	{{d.LAY_TABLE_INDEX+1}}
</script>
<script type='text/html' id='toolDemo2'>
	<a class='layui-btn  layui-btn-primary layui-btn-xs' lay-event='openAll'>展开全部</a>
	<a class='layui-btn  layui-btn-primary layui-btn-xs' lay-event='closeAll'>关闭全部</a>
	<a class='layui-btn  layui-btn-primary layui-btn-xs' lay-event='opOrientation'>(水平|垂直)排列</a>
</script>
<!--wanghongjie -->
<script type="text/javascript">
	;
	var coolingWater = (function($, window, document, undefined) {
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

		/*function init() {
			layui.use(['layer','table','element', 'form'],function(){
				table = layui.table;
				layer = layui.layer;
				form = layui.form;
			});
			$('#waterCoolantDiv').attr('flag', flag);
		}*/

		function tableEvent() {
			layui.table.on('tool(waterCoolantTaskTools)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data //获得当前行数据
				layui.layer.msg('查看操作');
				viewFun(data);
			});
			layui.form.on('switch(lqs)', function(data){

				var sysName = data.elem.getAttribute('sys-name');
				var checked = data.elem.checked;
				var val = '255';

				if (!checked) {
					val = '0';
				}

				startStopControl(sysName, val);
			});
		}

		$(function () {

			queryColdWarmUnit_lq({}, function (result) {

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
			})
		});

		function init(data) {
			layui.use(['opTable'],function () {
			var opTablec = layui.opTable.render({
				elem :'#waterCoolantTable_monitoring',
				id: '#waterCoolantTable_monitoring',
				toolbar: '#toolDemo2',
				defaultToolbar:  ['print'],
				page :false,//开启分页
				width: $('#waterCoolantDiv').width() - 30,
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
				cols:[[    //表头
					{
						title:"序号",
						templet:'#waterCoolantTimer',
						type:"numbers"
					},
					{
						title:"名称",
						field:"f_NAME"
					},
					{
						title:"能耗累计",
						field:"f_NHLJ",
						align: "center",
						templet:function (item) {
							var value = item.f_NHLJ;
							sysNames.add(value);
							sysNames.add(item.f_SBYXSJ);
							sysNames.add(item.f_SFKDW);
							sysNames.add(item.f_SFGDW);
							sysNames.add(item.f_BPYXZT);
							sysNames.add(item.f_BPGZZT);
							sysNames.add(item.f_BPQPLFK);
							sysNames.add(item.f_SLZT);
							sysNames.add(item.f_SFGZZT);
							sysNames.add(item.f_SBYXZT);
							sysNames.add(item.f_YXZSD);
							sysNames.add(item.f_GZZSD);
							sysNameAndField[value] = 'f_NHLJ';
							return '<div id="' + value + flag + '"></div>'
						}
					},
					{
						title:"瞬时功率",
						field:"f_SSGL",
						align: "center",
						templet:function (item) {
							var value = item.f_SSGL;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SSGL';
							return '<div id="' + value + flag + '"></div>'
						}
					},
					{
						title:"A相电流",
						field:"f_AXDL",
						align: "center",
						templet:function (item) {
							var value = item.f_AXDL;
							sysNames.add(value);
							sysNameAndField[value] = 'f_AXDL';
							return '<div id="' + value + flag + '"></div>'
						}
					},
					{
						title:"B相电流",
						field:"f_BXDL",
						align: "center",
						templet:function (item) {
							var value = item.f_BXDL;
							sysNames.add(value);
							sysNameAndField[value] = 'f_BXDL';
							return '<div id="' + value + flag + '"></div>'
						}
					},
					{
						title:"C相电流",// DO点 0和255的控制点
						field:"f_CXDL",
						align: "center",
						templet:function (item) {
							var value = item.f_CXDL;
							sysNames.add(value);
							sysNameAndField[value] = 'f_CXDL';
							return '<div id="' + value + flag + '"></div>'
						}
					},
					{
						title:"冷冻设备工作模式",// DO点 0和255的控制点
						field:"f_SBGZMS",
						align: "center",
						templet:function (item) {
							var value = item.f_SBGZMS;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SBGZMS';
							return '<div id="' + value + flag + '"></div>'
						}
					},
					{
						title:"冷冻设备故障状态",//DI  开或关
						field:"f_SBGZZT",
						align: "center",
						templet:function (item) {
							var value = item.f_SBGZZT;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SBGZZT';
							return '<span id="' + value + flag + '"></span>';
						}
					},
					{
						title:"冷冻设备请求信号",//DI 运行或故障
						field:"f_SBQQXH",
						align: "center",
						templet:function (item) {
							var value = item.f_SBQQXH;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SBQQXH';
							return '<span id="' + value + flag + '"></span>';
						}
					},
					{
						title:"操作",
						width:200,
						align:"center",
						toolbar:'#waterCoolantTableOperateTools'
					}
				]]
				//  展开的列配置
				, openCols: [
					{
						title:"设备运行时间",//DI 运行或故障
						field:"f_SBYXSJ",
						templet:function (item) {
							var value = item.f_SBYXSJ;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SBYXSJ';
							return '<span class="opTable-item-title">设备运行时间:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},
					{
						title:"水阀开到位",//DI 运行或故障
						field:"f_SFKDW",
						templet:function (item) {
							var value = item.f_SFKDW;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SFKDW';
							return '<span class="opTable-item-title">水阀开到位:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"水阀关到位",//DI 运行或故障
						field:"f_SFGDW",
						templet:function (item) {
							var value = item.f_SFGDW;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SFGDW';
							return '<span class="opTable-item-title">水阀关到位:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"变频器运行状态",//DI 运行或故障
						field:"f_BPYXZT",
						templet:function (item) {
							var value = item.f_BPYXZT;
							sysNames.add(value);
							sysNameAndField[value] = 'f_BPYXZT';
							return '<span class="opTable-item-title">变频器运行状态:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"变频器故障状态",//DI 运行或故障
						field:"f_BPGZZT",
						templet:function (item) {
							var value = item.f_BPGZZT;
							sysNames.add(value);
							sysNameAndField[value] = 'f_BPGZZT';
							return '<span class="opTable-item-title">变频器故障状态:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"变频器频率反馈",//DI 运行或故障
						field:"f_BPQPLFK",
						templet:function (item) {
							var value = item.f_BPQPLFK;
							sysNames.add(value);
							sysNameAndField[value] = 'f_BPQPLFK';
							return '<span class="opTable-item-title">变频器频率反馈:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"水流状态",//DI 运行或故障
						field:"f_SLZT",
						templet:function (item) {
							var value = item.f_SLZT;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SLZT';
							return '<span class="opTable-item-title">水流状态:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"水阀故障状态",//DI 运行或故障
						field:"f_SFGZZT",
						templet:function (item) {
							var value = item.f_SFGZZT;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SFGZZT';
							return '<span class="opTable-item-title">水阀故障状态:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"水泵运行状态",//DI 运行或故障
						field:"f_SBYXZT",
						templet:function (item) {
							var value = item.f_SBYXZT;
							sysNames.add(value);
							sysNameAndField[value] = 'f_SBYXZT';
							return '<span class="opTable-item-title">水泵运行状态:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"运行指示",//DI 运行或故障
						field:"f_YXZSD",
						templet:function (item) {
							var value = item.f_YXZSD;
							sysNames.add(value);
							sysNameAndField[value] = 'f_YXZSD';
							return '<span class="opTable-item-title">运行指示:</span>' +
									'<span class="opTable-exp-value" id="' + value + flag + '"></span>';
						}
					},{
						title:"故障指示",//DI 运行或故障
						field:"f_GZZSD",
						templet:function (item) {
							var value = item.f_GZZSD;
							sysNames.add(value);
							sysNameAndField[value] = 'f_GZZSD';
							return '<span class="opTable-item-title">故障指示:</span>' +
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
				],openType: 1
			});

				layui.table.on('toolbar(waterCoolantTaskTools)', function (obj) {
					if (obj.event === 'openAll') {
						opTablec.openAll();
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
						opTablec.closeAll();
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
						opTablec.reload({opOrientation: opTablec.config.opOrientation === "v" ? "h" : "v"});
						setTimeout(function () {
							// 展开第一行
							opTablec.openIndex(0);

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
			$('#waterCoolantDiv').attr('flag', flag);
		}

		// 查询设备信息
		function queryColdWarmUnit_lq(obj, callback) {

			if(typeof callback !== 'function'){
				return;
			}
			obj.F_TYPE_ID="2";
			obj = obj || {};


			$.ajax({
				type    : "POST",
				url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/chilledWater/query",
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
		function subCallback(data)
		{
			var field = sysNameAndField[data.name];

			refreshTableData(field, data.name, data.value);

			layui.form.render('checkbox');
		}
		// 添加订阅消息
		function addSubMsg() {
            for (let item of sysNames){
                PubSub.subscribe(item, coolingWater.subCallback);
            }
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
					if(ddcList){
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
				case 'f_NHLJ':

					$('#' + sysName + flag).text(value+"Kwh");

					break;
				case 'f_SSGL':

					$('#' + sysName + flag).text(value+"Kwh");

					break;
				case 'f_AXDL':

					$('#' + sysName + flag).text(value+"A");

					break;
				case 'f_BXDL':

					$('#' + sysName + flag).text(value+"A");

					break;
				case 'f_CXDL':

					$('#' + sysName + flag).text(value+"A");

					break;
				case 'f_SBGZMS':

					var text = '';

					if(value === '255') {
						text = '远程';
					}else if (value === '0') {
						text = '就地';
					}

					$('#' + sysName + flag).text(text);


					break;
				case 'f_SBGZZT':

					var text = '';

					if(value === '1') {
						text = '正常';
					}else  {
						text = '故障';
					}

					$('#' + sysName + flag).text(text);


					break;
				case 'f_SBQQXH':

					var text = '';

					if(value === '0') {
						text = '无请求';
					}else  {
						text = '请求中';
					}

					$('#' + sysName + flag).text(text);
					break;


				case 'f_SBYXSJ':
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
				case 'f_SFKDW':

					var text = '';

					if(value === '0') {
						text = '未关闭';
					}else  if (value === "255"){
						text = '已开启';
					} else {
						text = '已开启';
					}

					$('#' + sysName + flag).text(text);
					break;
				case 'f_SFGDW':

					var text = '';

					if(value === '0') {
						text = '未关闭';
					}else if (value === '255') {
						text = '已关闭';
					}else {
						text = '已关闭';
					}

					$('#' + sysName + flag).text(text);
					break;
				case 'f_BPYXZT':

					var text = '';

					if(value === '0') {
						text = '停止';
					}else if (value === '255') {
						text = '运行';
					}else {
						text = '运行';
					}

					$('#' + sysName + flag).text(text);
					break;
				case 'f_BPGZZT':

					var text = '';

					if(value === '0') {
						text = '正常';
					}else if (value === '255') {
						text = '故障';
					}else {
						text = '故障';
					}

					$('#' + sysName + flag).text(text);
					break;
				case 'f_BPQPLFK':

					$('#' + sysName + flag).text(value + 'Hz');
					break;
				case 'f_SLZT':

					var text = '';

					if(value === '0') {
						text = '静止';
					}else if (value === '255') {
						text = '流动';
					} else {
						text = '流动';
					}

					$('#' + sysName + flag).text(text);
					break;
				case 'f_SFGZZT':

					var text = '';

					if(value === '0') {
						text = '正常';
					}else if (value === '255') {
						text = '故障';
					}else {
						text = '故障';
					}

					$('#' + sysName + flag).text(text);
					break;
				case 'f_SBYXZT':

					var text = '';

					if(value === '0') {
						text = '停止';
					}else if (value === '255') {
						text = '运行';
					}

					$('#' + sysName + flag).text(text);
					break;
				case 'f_YXZSD':

					var text = '';

					if(value === '0') {
						text = '停止';
					}else if (value === '255') {
						text = '运行';
					}else {
						text = '运行';
					}

					$('#' + sysName + flag).text(text);
					break;
				case 'f_GZZSD':

					var text = '';

					if(value === '0') {
						text = '正常';
					}else if (value === '255') {
						text = '故障';
					}else {
						text = '故障';
					}

					$('#' + sysName + flag).text(text);

					break;

			}
		}

		function startStopControl(sysName, val) {

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
                            html = '<input type="checkbox" sys-name="'+ sysName +'" lay-filter="lqs" lay-skin="switch" lay-text="开启|关闭" checked>';
                        }else if (val === '255') {
                            html = '<input type="checkbox" sys-name="'+ sysName +'" lay-filter="lqs" lay-skin="switch" lay-text="开启|关闭">';
                        }

                        $('#' + sysName + flag).html(html);

						layui.form.render('checkbox');
                    }
				},
				error : function(result) {
					layui.layer.msg('操作失败');
				}
			});
		}

		function viewFun(data){
			//eqName = data.f_NAME;
			eqName=data.f_ID;//注意：此id只是因数据不全，暂时这样写测试用
			$("#waterCoolantHiddenInput").val(eqName);
			$("#waterCoolantDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/coolingWater/testView',//冷却水查看页面
					{
                        "f_NAME": data.f_NAME,
                        "f_ID": data.f_ID,
                        "f_Electric_meter_number": data.f_Electric_meter_number,
                        "f_SBQD": $('#' + data.f_SBQD + flag + ' em').text()
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


