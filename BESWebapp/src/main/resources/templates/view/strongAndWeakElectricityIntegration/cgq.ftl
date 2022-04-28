<link href="${ctx}/static/css/integration.css" rel="stylesheet">
<input id = "cgqHiddenInput" type="text" value="" style="display:none"></input>
<div id="cgqDiv" >
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;传感器监控信息>>>
   	 </span>
		<!-- 搜索框 -->
		<div class="zc_search find">
			<input type="text" class="find-style"  id="CgqTypeInfo" name="CgqTypeInfo" placeholder="传感器类型">
			<button id="queryAmmeterType" onclick="cgq.searchCgq()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
	<div class="strongAndWeakElectricityIntegrationCommon" style="margin:0px 15px;">
		<table class="layui-hide" id="cgqTable" lay-filter="cgqTaskTools"></table>
		<script type="text/html" id="cgqTableOperateTools">
    		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="view">查看</a>
		</script>
	</div>
</div>
<!-- 下方的script作用是序号自增 -->
<script type="text/html" id="cgqTimer">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript">
;
var cgq = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var table;
	var layer;
	var form;
	var flag = new Date().getTime();
	var sysNames =new Set();
	var sysNameAndField = {};

	init();


	$(function () {
		queryCgqUnit(null, function (result) {
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
				for(let i = 0; i < list.length; i++) {

					var sysName = list[i].f_sys_name;
					var value = list[i].f_init_val;
					var field = sysNameAndField[sysName];
					var unit = list[i].unit;

					refreshTableData(field, sysName, value,unit);

				}

                form.render('checkbox');
			})
		})

	})
	//触发搜索的回车时间
	$("#cgqInfo").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				cgq.searchCgq();//触发该事件
			}
		})
	});
	function init() {
		layui.use(['layer','table','element', 'form'],function(){
			table = layui.table;
			layer = layui.layer;
			form = layui.form;
		});
		$('#cgqDiv').attr('flag',flag);
	}


	// 添加订阅消息
	function addSubMsg() {
		for (let item of sysNames){

			PubSub.subscribe(item,  cgq.subCallback);

		}
	}
	function subCallback(data)
	{
		var field = sysNameAndField[data.name];
		refreshTableData(field, data.name, data.value, data.unit);

		form.render('checkbox');
	}

	// 查询设备信息
	function queryCgqUnit(obj, callback) {

		if(typeof callback !== 'function'){
			return;
		}

		obj = obj || {};

		$.ajax({
			type    : "POST",
			url     : _ctx + "/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig/query",
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
		    table.render({
		    	elem :'#cgqTable',
				data: data,
				done: function(res, curr, count){

					addSubMsg();
					// 首次加载数据
					getRealTimeData(function (list) {

						for(var i = 0; i < list.length; i++) {

							var sysName = list[i].f_sys_name;
							var value = list[i].f_init_val;
							var field = sysNameAndField[sysName];
							var unit = list[i].unit;

							refreshTableData(field, sysName, value, unit);

							form.render('checkbox');

						}
					})
				},
		    	page :true,//开启分页
				width: $('#cgqDiv').width() - 30,
		    	cols:[[    //表头
					{
						title:"序号",
						templet:'#cgqTimer'
					},
					{
						title:"名称",
						field:"name"
					},
					{
						title:"传感器类型",
						field:"cgqType"
					},
					{
						title:"当前值展示",
						field:"cgqDqz",
						templet:function (item) {
							var value = item.cgqDqz;
							sysNames.add(value);
							sysNameAndField[value] = 'cgqDqz';
							return '<span class="' + value + flag + '"></span>';

						}
					}

					// 暂时取消操作列，没有确定具体查看的页面展示 wzx2020-8-15 11:12:37
					// ,
					// {
					// 	title:"操作",
					// 	width:200,
					// 	align:"center",
					// 	toolbar:'#cgqTableOperateTools'
					// }
				]]
		   	 }
		    );
	}

	function refreshTableData(field, sysName, value, unit) {
		if (field == null || sysName == null || value == null) {
			return;
		}

		switch(field)
		{
			case 'cgqType':

				var text = '';



				$('.' + sysName + flag).text(value+unit);

				break;
			case 'cgqDqz':

				var text = '';

				$('.' + sysName + flag).text(value+unit);

				break;


		}
	}


	return{
		pageInit:function(){
			initTable();
		},
		//查询
		searchCgq : function () {

			var f_type = $("#CgqTypeInfo").val();
			$.ajax({
				url : _ctx + '/view/strongAndWeakElectricityIntegration/deviceConfig/cgqConfig/queryByType',
				type : "post",
				data : ({
					"cgqType":f_type
				}),
				success: function (result) {
					var data = result.data;
					initTable(data);
					getRealTimeData(function (list) {

						for(let i = 0; i < list.length; i++) {
							var sysName = list[i].f_sys_name;
							var value = list[i].f_init_val;
							var field = sysNameAndField[sysName];

							refreshTableData(field, sysName, value);
						}

                        form.render('checkbox');
					})

				},
				error : function(XMLHttpRequest,textStatus, errorThrown) {
					toastr.error('', '查询失败');
				}
			});
		},
		subCallback
	}



})(jQuery, window, document);
cgq.pageInit();
</script>


