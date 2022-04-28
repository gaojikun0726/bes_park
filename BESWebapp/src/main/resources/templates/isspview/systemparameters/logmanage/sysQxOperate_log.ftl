
<!-- 
	describe:权限日志具体实现，增删改等操作
	author:liuhoujun
 -->
<div class="information_size">
    <div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;系统权限日志列表>>>
		</span>
        <!-- 增加按钮 -->
        <!-- <a id="addPRODUCER" href="javascript:void(-1);" onclick="isspview_besProducterlistManage.prodcer_show_addmodal()" class="btn btn-primary toLeft"> 增加 <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>
        </a>  -->
        <!-- 业务名称框 -->
        <div id="Qxoperate_name" style="display: flex;align-items: center;margin-left:2%;white-space: nowrap;text-overflow: ellipsis;">
            操作人：
            <input type="text" class="log_input" id="sysQxOperatelogname" name="sysQxOperatelogname" placeholder="请输入操作人名称">
        </div>


        <div id="Qxoperate_type" style="display: flex;align-items: center;margin-left:2%;white-space: nowrap;text-overflow: ellipsis;">
            操作类型：
            <select class="selector" id="sysQxOperatelogftype">
                <option value="" selected="selected">不限</option>
                <option value="11">功能权限分配</option>
                <option value="12">功能权限审核</option>
                <option value="13">功能权限取消分配</option>
            </select>
        </div>
        <div id ="Qxoperate_content"  style="display: flex;align-items: center;margin-left:2%;white-space: nowrap;text-overflow: ellipsis;">
            操作内容：
            <input type="text" class="log_input" id="sysQxOperatelogcontent" name="sysQxOperatelogcontent" placeholder="请输入业务名称">
        </div>
        <div class="battery_style">
            时间：
            <input  id="qxoperate_query_start_time" type="text"  name="start"  onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss', maxDate:'#F{$dp.$D(\'qxoperate_query_end_time\')||\'%y-%M\'}'})"  class="input-datecheck"  onchange="view_sysmanage_logmanage_sysQxOperate_log.s_check()"/>
            &nbsp;至 &nbsp;
            <input  id="qxoperate_query_end_time" type="text"  name="end" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss', minDate:'#F{$dp.$D(\'qxoperate_query_start_time\')}',maxDate:'%y-%M' })" class="input-datecheck" onchange="view_sysmanage_logmanage_sysQxOperate_log.e_check()">
        </div>

        <div class="battery_style">
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft"  onclick="view_sysmanage_logmanage_sysQxOperate_log.searchOperationLog_query()">
                <i class="fa fa-filter"></i>&nbsp;筛选</button>
            <button type="button" class="btn btn-sm btn-primary no-margins toLeft" onclick="view_sysmanage_logmanage_sysQxOperate_log.reset()">
                <i class="fa fa-filter"></i>&nbsp;重置</button>
        </div>
    </div>

    <!---分页列表----->
    <div class="ibox" id="SystemQxOperatelog_ibox" style="height:92%"> <!-- id保证唯一性 systemOperatelog_addInfo  -->

    </div>
</div>


<!---查看详情---->
 <div class="modal fade"  id="lookQxOperateFromTable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" id="qxoperate_addFormDialog" style="width: 560px;">
        <div class="modal-content" style="width:30vw;height:40vh;">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 查看详情</h4>
            </div>
            <div class="modal-body" style="width:100%;height:20vh;">
           
           	<!-- 	<span id = "lookqxoplog" > </span> -->
           		<div style="margin-top: -1vh">
           			操作类型：
           			<input class="form-control" id="qxoperationLog_type" readonly="readonly"/>
           		</div>
				<div>
					操作内容：
					<textarea class="form-control" id="qxoperationLog_content" rows="5" cols="30" style="width:27vw;height:20vh "readonly="readonly"></textarea>
				</div>
            
		        <div class="col-sm-6 col-sm-push-3 display_flex" ">
		            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal" style="margin-top: 6vh" >
		            <i class="fa fa-times" aria-hidden="true"></i>&nbsp;关闭</button>
		        </div>
            </div>
            
        </div>
    </div>
</div>	  
		


<script type="text/javascript">
;var view_sysmanage_logmanage_sysQxOperate_log = (function($, window, document, undefined) {
	//设置格式
	var _curRow = null;
	var _ctx = '${ctx}';
	var keywords='';  //存放搜索内容
	var a_sTime = '';//开始时间 
	var a_eTime = '';//结束时间 
	
	
	 /*实现动态模态框，实现查看详细信息的操作  */
	 $("#lookQxOperateFromTable").on('show.bs.modal', function(event) {
		//垂直居中显示
		$(this).css('display', 'block');  
		var modalHeight=($(window).height()/2) - ($('#qxoperate_addFormDialog').height()/2);  
        $("#qxoperate_addFormDialog").css('margin-top', modalHeight); 
        
		//模态拖动
        $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
        var button = $(event.relatedTarget);
		var id = button.data("id");
		
	  	//重置表单及验证信息
	    fId = id;
		//获取信息
		fullData(fId);
	});
	 
	//获取操作日志关系表数据通过ID获取到content的值
	 function fullData(fId) {
		$.ajax({
			url : _ctx + "/view/sysmanage/logmanage/getlookqxopcontent",
			type : "post",
			data:{
				fid : fId
			},
			beforeSend : function() {
				showLoad();
			},
			success : function(result) {

				//$("#lookqxoplog").text(result.data.fOpcontent);
				$("#qxoperationLog_content").val(result.data.fOpcontent);
				$("#qxoperationLog_type").val(result.data.fOptype1);
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
	$("#sysQxOperatelogkeywords").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				view_sysmanage_logmanage_sysQxOperate_log.loadsysQxOperatelog();
			} 
		})
	});
	
    return{
    	//分页查询
    	loadsysQxOperatelog:function(){
    		$.ajax({
				url : _ctx + '/view/sysmanage/logmanage/loadsysQxOperateloglists',
				type : "post",
				data : {
					keywords:$("#sysQxOperatelogkeywords").val(),
					 bars:$("#sysQxOperate_log_pageSize").val(),
				},
				  beforeSend: function () { 
	 		        	showLoad();	             
	 		        },
				success : function(data) {
					$('#SystemQxOperatelog_ibox').html(data);
					
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
			a_sTime = $("#qxoperate_query_start_time").val();
		},
		e_check:function (){
			a_eTime = $("#qxoperate_query_end_time").val();
		},
		//条件查询权限日志
		searchOperationLog_query:function(){

			var fopcontent = $("#sysQxOperatelogcontent").val();//业务名称
			var fopname = $("#sysQxOperatelogname").val();//操作人
			var foptype = $("#sysQxOperatelogftype option:selected").val();
		
			var start = $('#qxoperate_query_start_time').val();
			var end = $('#qxoperate_query_end_time').val();
			$.ajax({
				url : _ctx+'/view/sysmanage/logmanage/loadQxOperationByKey',
				type : "post",
				data : {
					fOpcontent : fopcontent,
					fOpname : fopname,
					fOptype : foptype,
					f_sTime : a_sTime,
					f_eTime : a_eTime
				},
				success : function(data) {
					$("#ESSystemqxoperateTable").tabulator("setData", []);
					$('#SystemQxOperatelog_ibox').html(data);
				},
				error : function(XMLHttpRequest,textStatus, errorThrown) {
					toastr.error('', '查询失败');
				}
			});
		},
		//重置 条件+日志列表
		reset:function(){
			$("#sysQxOperatelogcontent").val("");
			$("#sysQxOperatelogname").val("");
			$("#sysQxOperatelogftype option:first").prop("selected", 'selected');
			$('#qxoperate_query_start_time').val("");
			$('#qxoperate_query_end_time').val("");
			view_sysmanage_logmanage_sysQxOperate_log.s_check();
			view_sysmanage_logmanage_sysQxOperate_log.e_check();
			view_sysmanage_logmanage_sysQxOperate_log.searchOperationLog_query();//重置后刷新表单
		},
		
		
		//初始化页面加载函数
    	pageInit : function(){
		view_sysmanage_logmanage_sysQxOperate_log.loadsysQxOperatelog();
		view_sysmanage_logmanage_sysQxOperate_log.s_check();
		view_sysmanage_logmanage_sysQxOperate_log.e_check();
	}
    }
})(jQuery, window, document);
//调用初始化方法
view_sysmanage_logmanage_sysQxOperate_log.pageInit();
</script>
