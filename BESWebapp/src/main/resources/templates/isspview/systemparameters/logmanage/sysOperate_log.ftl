
<!-- 
	describe:操作日志界面具体实现，增删改等功能
	author:liuhoujun
	editTime:2018/12/13
 -->
<div class="information_size">
    <div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;系统操作日志列表>>>
		</span>
        <!-- 增加按钮 -->
        <!-- <a id="addPRODUCER" href="javascript:void(-1);" onclick="isspview_besProducterlistManage.prodcer_show_addmodal()" class="btn btn-primary toLeft"> 增加 <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>
        </a>  -->
        <!-- 业务名称框 -->

        <div id="operate_name" style="display: flex;align-items: center;margin-left:2%;white-space: nowrap;text-overflow: ellipsis;">
            用户名：
            <input type="text" class=" log_input" id="sysOperatelogname" name="sysOperatelogname" placeholder="请输入用户名">
        </div>
        <div id="operate_type" style="display: flex;align-items: center;margin-left:2%;white-space: nowrap;text-overflow: ellipsis;">
            操作类型：
            <select class="selector" id="sysOperatelogftype">
                <option value="" selected="selected">不限</option>
                <option value="0">增加</option>
                <option value="1">删除</option>
                <option value="2">修改</option>
            </select>
        </div>
        <div id ="operate_comment"  style="display: flex;align-items: center;margin-left:2%;white-space: nowrap;text-overflow: ellipsis;">
            业务名称：
            <input type="text" style="" class="log_input" id="sysOperatelogcomment" name="sysOperatelogcomment" placeholder="请输入业务名称">
        </div>
        <div class="battery_style">
            时间：
            <input  id="operate_query_start_time" type="text"  name="start"  onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss', maxDate:'#F{$dp.$D(\'operate_query_end_time\')||\'%y-%M\'}'})"  class="input-datecheck"  onchange="view_sysmanage_logmanage_sysOperate_log.s_check()"/>
            &nbsp;至 &nbsp;
            <input  id="operate_query_end_time" type="text"  name="end" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss', minDate:'#F{$dp.$D(\'operate_query_start_time\')}',maxDate:'%y-%M' })" class="input-datecheck" onchange="view_sysmanage_logmanage_sysOperate_log.e_check()">
        </div>
        <div class="battery_style">
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"  onclick="view_sysmanage_logmanage_sysOperate_log.searchOperationLog_query()">
                <i class="fa fa-filter"></i>&nbsp;筛选</button>
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft" onclick="view_sysmanage_logmanage_sysOperate_log.reset()">
                <i class="fa fa-wrench"></i>&nbsp;重置</button>
        </div>
    </div>

    <!---分页列表----->
    <div class="ibox" id="SystemOperatelog_ibox1" style="height:92%"> <!-- id保证唯一性 systemOperatelog_addInfo  -->

    </div>
</div>


<!---查看详情---->
<div class="modal fade"  id="lookUserFromTable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" id="operate_addFormDialog" style="width: 560px;">
        <div class="modal-content" style="width:50vw;height:60vh;"><!-- style="width:60vw;height:50vh;" -->
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 查看操作日志详细信息</h4>
            </div>
            <div class="modal-body" style="width:100%;height:50vh;">
            	<span>业务名称：</span><span id="operationContentName"></span>
            	&nbsp;&nbsp;
            	<span id="operationContentType" style="color:red;"></span>
            
            	<div id= "operationTable" style="width:100%;height:47vh; "></div>
            	
		        <div class="col-sm-6 col-sm-push-3 display_flex" style="padding-top: 2vh;">
		            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"style="margin-left: 45vw">
		            <i class="fa fa-times" aria-hidden="true"></i>&nbsp;关闭</button>
		        </div>
            </div>
        </div>
    </div>
</div>	


<script type="text/javascript">
;var view_sysmanage_logmanage_sysOperate_log = (function($, window, document, undefined) {
	//设置格式
	var _curRow = null;
	var _ctx = '${ctx}';
	var keywords='';  //存放搜索内容
	var a_sTime = '';//开始时间 
	var a_eTime = '';//结束时间 
	
	$("#operationTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[		
		{title:"序号",field:"id",formatter:"rownum",frozen:false,align:"center",headerSort:false}, //frozen column
		{title:"字段", field:"fTbKey",sorter:"string",align:"left",editor:false,headerSort:false}, //hide this column first 
		{title:"名称", field:"fComment",align:"left",headerSort:false},
		{title:"旧值", field:"fTbValue", sorter:"string",align:"left",editor:false,headerSort:false},
		{title:"新值", field:"fCurrenttbValue",align:"left",editor : false,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
    	},
	});
	
	
	/*实现动态模态框，实现查看详细信息的操作  */
	$("#lookUserFromTable").on('show.bs.modal', function(event) {
		//垂直居中显示
		$(this).css('display', 'block');  
		var modalHeight=($(window).height()/2) - ($('#operate_addFormDialog').height()/2);  
        $("#operate_addFormDialog").css('margin-top', modalHeight); 
        
		//模态拖动
        $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
        var button = $(event.relatedTarget);
		var id = button.data("id");
		var f_comment = view_sysmanage_logmanage_sysOperate_log_page.getRow().getData().f_comment;//业务名称
		var f_type = view_sysmanage_logmanage_sysOperate_log_page.getRow().getData().f_type;//操作类型
		if(f_type == 0){
			f_type = "增加";
		}else if(f_type == 1){
			f_type = "删除";
		}else{
			f_type = "修改";
		}
		$("#operationContentName").empty(f_comment);
		$("#operationContentType").empty(f_type);
		$("#operationContentName").append(f_comment);
		$("#operationContentType").append(f_type);
	  	//重置表单及验证信息
	    fId = id;
		//获取信息
		fullData(f_type,fId);
	});
	
	//获取操作日志表数据
	function fullData(f_type,fid) {
		if (f_type =="删除" || f_type =="增加") {
			//$("#lookUserFromTable").tabulator("clearData");
			var  newColumns = [
				{title:"序号",field:"id",formatter:"rownum",frozen:false,align:"center",headerSort:false}, //frozen column
				{title:"字段", field:"fTbKey",sorter:"string",align:"left",editor:false,headerSort:false}, //hide this column first 
				{title:"名称", field:"fComment",align:"left",headerSort:false},
				{title:"字段值", field:"fTbValue", sorter:"string",align:"left",editor:false,headerSort:false},
				]
			$("#operationTable").tabulator("setColumns",newColumns);//用新列定义数组覆盖现有列 */
		}else{
			var newColumns1 = [
				{title:"序号",field:"id",formatter:"rownum",frozen:false,align:"center",headerSort:false}, //frozen column
				{title:"字段", field:"fTbKey",sorter:"string",align:"left",editor:false,headerSort:false}, //hide this column first 
				{title:"名称", field:"fComment",align:"left",headerSort:false},
				{title:"旧值", field:"fTbValue", sorter:"string",align:"left",editor:false,headerSort:false},
				{title:"新值", field:"fCurrenttbValue",align:"left",editor : false,headerSort:false},
			]
			$("#operationTable").tabulator("setColumns",newColumns1);//用新列定义数组覆盖现有列 */
		}
		$.ajax({
			url : _ctx + "/view/sysmanage/logmanage/loadsysOperatelogContentlist",
			type : "post",
			data:{
				f_id : fid
			},
			beforeSend : function() {
				showLoad();
			},
			success : function(result) {
				
				if(result.hasOwnProperty('list')){
					$("#operationTable").tabulator("setData", result.list);
				}else{
					$("#operationTable").tabulator("setData", []);
				}
			},
			complete : function() {
				hiddenLoad();
			},
			error : function(result) {
				swal("获取信息失败，请重试！", "", "error");
			}
		});
	}

	
	//触发搜索的回车事件
	$("#sysOperatelogkeywords").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				view_sysmanage_logmanage_sysOperate_log.loadsysOperatelog();
			} 
		})
	});
	
    return{
    	//分页查询
    	loadsysOperatelog:function(){
    		$.ajax({
				url : _ctx + '/view/sysmanage/logmanage/loadsysOperateloglistss',
				type : "post",
				data : {
					keywords:$("#sysOperatelogkeywords").val(),
					 bars:$("#sysOperate_log_pageSize").val(),
				},
				  beforeSend: function () { 
	 		        	showLoad();	             
	 		        },
				success : function(data) {
					$('#SystemOperatelog_ibox1').html(data);
					
				},
				complete: function () {
 	            	hiddenLoad();
 	            },
				error : function(XMLHttpRequest,textStatus, errorThrown) {
					toastr.error('', '查询失败');
				}
			});
    	},
    	s_check:function (){
			a_sTime = $("#operate_query_start_time").val();
		},
		e_check:function (){
			a_eTime = $("#operate_query_end_time").val();
		},
		//条件查询操作日志
		searchOperationLog_query:function(){
			var fcomment = $("#sysOperatelogcomment").val();//业务名称
			var fname = $("#sysOperatelogname").val();//操作人
			var ftype = $("#sysOperatelogftype option:selected").val();
		
			var start = $('#operate_query_start_time').val();
			var end = $('#operate_query_end_time').val();
			$.ajax({
				url : _ctx+'/view/sysmanage/logmanage/loadOperationByKey',
				type : "post",
				data : {
					f_comment : fcomment,
					f_name : fname,
					f_type : ftype,
					f_sTime : a_sTime,
					f_eTime : a_eTime
				},
				success : function(data) {
					$("#ESSystemoperateTable").tabulator("setData", []);
					$('#SystemOperatelog_ibox1').html(data);
				},
				error : function(XMLHttpRequest,textStatus, errorThrown) {
					toastr.error('', '查询失败');
				}
			});
		},
		//重置 条件+日志列表
		reset:function(){
			$("#sysOperatelogcomment").val("");
			$("#sysOperatelogname").val("");
			$("#sysOperatelogftype option:first").prop("selected", 'selected');
			$('#operate_query_start_time').val("");
			$('#operate_query_end_time').val("");
			view_sysmanage_logmanage_sysOperate_log.s_check();
			view_sysmanage_logmanage_sysOperate_log.e_check();
			view_sysmanage_logmanage_sysOperate_log.searchOperationLog_query();//重置后刷新表单
		},
		
		
		//初始化页面加载函数
    	pageInit : function(){
		view_sysmanage_logmanage_sysOperate_log.loadsysOperatelog();
		view_sysmanage_logmanage_sysOperate_log.s_check();
		view_sysmanage_logmanage_sysOperate_log.e_check();
	}
    }
})(jQuery, window, document);
//调用初始化方法
view_sysmanage_logmanage_sysOperate_log.pageInit();
</script>
