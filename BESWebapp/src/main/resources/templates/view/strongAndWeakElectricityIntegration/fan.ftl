<link href="${ctx}/static/css/integration.css" rel="stylesheet">
<input id ="fanHiddenInput" type="text" value="" style="display:none"></input>
<div id="fanDiv">
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;风机监控信息>>>
    	</span> 
	</div>
	<div  class="strongAndWeakElectricityIntegrationCommon" style="margin:0px 15px;">
		<table class="layui-hide" id="fanTable" lay-filter="fanTaskTools"></table>
		<script type="text/html" id="fanTableOperateTools">    	
			<a class="layui-btn layui-btn-sm" lay-event="start">启动</a>
    		<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="stop">停止</a>
    		<a class="layui-btn layui-btn-sm layui-btn-normal" lay-event="view">查看</a>
		</script> 
	</div>
</div>
<!-- 下方的script作用是序号自增 -->
<script type="text/html" id="fanTimer">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript">
;
var view_essentialdata_equipmentmanagement_task = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var table;
	var layer;
	var form;
	var index = 0;
	var isAdd = 'add';
	var eqName;
	
	/**
	 *  初始化数据表格
	 */
	function initTable(){
		layui.use(['layer','table','element', 'form'],function(){
			table = layui.table;
		    layer = layui.layer;
		    form = layui.form;
		    table.render({
		    	elem :'#fanTable',
		    	url :_ctx + '/view/strongAndWeakElectricityIntegration/fan/inquireTask',
		    	page :true,//开启分页
		    	cols:[[    //表头
		    		//{fixed : 'left',type : 'checkbox'},
		    		{title:"序号", templet:'#fanTimer'},
					{title:"名称", field:"f_name"}, 
					{title:"当前状态", field:"f_dqzt"}, 
					{title:"当前功率", field:"f_dqgl"}, 
					{title:"累计能耗", field:"f_ljnh"},
					{title:"累计运行时间", field:"f_ljyxsj"},
					{title:"操作",width:200, align:"center",toolbar:'#fanTableOperateTools' }
				]]
		   	}
		    );
		    table.on('tool(fanTaskTools)', function(obj){ //注：tool是工具条事件名，fanTaskTools是table原始容器的属性 lay-filter="对应的值"

		    	console.log("22222222222222");
		    	var data = obj.data //获得当前行数据
			    ,layEvent = obj.event; //获得 lay-event 对应的值
			    if(layEvent === 'start'){
			    	//viewFun(data);
			      	//layer.msg('查看操作');
			    } else if(layEvent === 'stop'){
			    	//layer.msg('删除操作');
			    	deleteFun(data.fTimerBh);
			    } else if(layEvent === 'view'){
			      	//layer.msg('编辑操作');
			      	viewFun(data);
			    }
			  });
		});
	}
	
	function viewFun(data){
		eqName = data.f_name;
		eqName="555555";//注意：此id只是因数据不全，暂时这样写测试用
		$("#fanHiddenInput").val(eqName);
		
		$("#fanDiv").load(_ctx+'/view/strongAndWeakElectricityIntegration/fan/testView',
				{
					"name":data.f_name
				});
	}
	
	return{
		pageInit:function(){
			initTable();
		}
	}
	       
})(jQuery, window, document);
view_essentialdata_equipmentmanagement_task.pageInit();
//view_essentialdata_equipmentmanagement_task.getAdapterName();
//view_essentialdata_equipmentmanagement_task.getService();
//view_essentialdata_equipmentmanagement_task.getTask();
</script>


