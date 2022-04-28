<!-----内容区域---->
<!-- <div class="ibox-content m-b-sm border-bottom" style="width: 100%; height: 6.5%"> 
	<div class="input-group"   style="height: 100%; width: 100%;">
        <div class="divStyle_size" >
            <a id="TimerManageadd" class="btn btn-primary" data-toggle="modal"  href="#modal-form-addTimerManage">
                	增加 <i class="fa fa-plus"></i>
            </a>        
            <a id="TimerManageimport"  class="btn btn-primary" onclick="isspview_scheduledtask_timermanage.startTimer()" style = "margin-right: 10px;width: 4.5vw;">
                	启动定时器 <i class="fa fa-play"></i>
            </a>
            <a id="TimerManageexport"  class="btn btn-primary" onclick="isspview_scheduledtask_timermanage.stopTimer()" style = "width: 4.5vw;">
                	停止定时器 <i class="fa fa-stop"></i>
            </a>
        </div>
        <div style="width: 25%;float: right;position: relative;padding-right: 0px;padding-left: 15px;">
            <input type="text" class="input-sm form-control" style="width: calc(100% - 60px);" id="TimerManageKeywords" name="TimerManageKeywords" value="" placeholder="定时器编号、"> 
			<span class="input-group-btn" style="width: 60px;"><i class="fa fa-search"></i> 
				<button class="btn btn-primary btn-sm m-b-none" id="queryTimerManageBtn" >
                        <i class="fa fa-search"></i>搜索
                </button>
            </span>
       	</div>  
		<div class="zc_search find"> 
			<div class="zc_search_form">
				<input id="TimerManageKeywords" name="TimerManageKeywords"
					value="" placeholder="定时器名称">
				<button id="queryTimerManageBtn" onclick="isspview_scheduledtask_timermanage.reLoadTimerManage()"></button>
			</div>
		</div>      
	</div>
</div> -->



<!-- 信息表格模块 -->
		<div class="information-model">
			<span class="Subtitle" style="width:10vw">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;定时器信息列表>>>
			</span>

			<button id="TimerManageimport" type="button"onclick="isspview_scheduledtask_timermanage.startTimer()"class="btn btn-primary"
			style="margin-left: 0px ! important; width: 7vw; height: 2.9vh;font-size: 0.8vw;">
				启动定时器&nbsp;<i class="fa fa-play"></i>
			</button>
			<button id="TimerManageexport" type="button"onclick="isspview_scheduledtask_timermanage.stopTimer()"class="btn btn-primary"
			style="margin-left: 0px ! important; width: 7vw; height: 2.9vh;font-size: 0.8vw;">
				停止定时器&nbsp;<i class="fa fa-stop"></i>
			</button>
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="TimerManageKeywords" name="TimerManageKeywords" placeholder="定时器名称" >
				  <button  id="queryTimerManageBtn" onclick="isspview_scheduledtask_timermanage.reLoadTimerManage()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<!---分页列表----->	        
    <div class="ibox" id="TimerManage_ibox" style="height:92%">
         	<#include "/isspview/scheduledtask/timermanage_page.ftl"/>
    </div>
<!-- 信息表格模块end -->		

            

<!-----内容区域结束---->	


<script type="text/javascript">
;
var isspview_scheduledtask_timermanage = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	
	$(window).resize(function() {
		$("#TimerManageTable").tabulator("redraw");
	});


	//触发搜索的回车事件
 	$("#TimerManageKeywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  isspview_scheduledtask_timermanage.reLoadTimerManage({
			"keywords" : $("#TimerManageKeywords").val(),
			bars:$("#timermanage_pageSize").val(),
			});//触发该事件
					    } 
					  })
					});
					
	//查询
	$("#queryTimerManageBtn").click(function() {
	isspview_scheduledtask_timermanage.reLoadTimerManage({
			"keywords" : $("#TimerManageKeywords").val(),
			bars:$("#timermanage_pageSize").val(),
	});
	});
	
	//启动定时任务
	$(document).on('click','#TimerManageTable button.start',function() {
		
		var fTimerBh = $(this).data("id");

		$.ajax({
			url : _ctx + "/view/TimerManage/startTimerTask",
			type : "post",
			data : {
				"fTimerBh" : fTimerBh
			},
		beforeSend: function () { 
	 	 		showLoad();	             
	 	 },
		success : function(data) {
			if (data.status == '1') {
				$("#TimerManageTable").tabulator("updateRow",isspview_scheduledtask_timermanagepage.get_curRow(),
						{fTimerState : data.data.fTimerState,fStartTime : data.data.fStartTime});
				$("#start"+fTimerBh+"").attr('disabled',true);
				$("#startnow"+fTimerBh+"").attr('disabled',false);
				$("#stop"+fTimerBh+"").attr('disabled',false);
			} else {
				swal("启动失败!",data.msg,"error");
			}
			},
		complete: function () {
	 	 		hiddenLoad();
	 	 	},
		error : function(data) {
				swal("启动失败!",data.msg,"error");
			}
			});			
	});
	//停止定时任务
	$(document).on('click','#TimerManageTable button.stop',function() {
		var fTimerBh = $(this).data("id");

		$.ajax({
			url : _ctx + "/view/TimerManage/stopTimerTask",
			type : "post",
			data : {
				"fTimerBh" : fTimerBh
			},
		beforeSend: function () { 
	 	 		showLoad();	             
	 	 },
		success : function(data) {
			if (data.status == '1') {
				$("#TimerManageTable").tabulator("updateRow",isspview_scheduledtask_timermanagepage.get_curRow(),
						{fTimerState : data.data.fTimerState,fStopTime : data.data.fStopTime});
				$("#start"+fTimerBh+"").attr('disabled',false);
				$("#startnow"+fTimerBh+"").attr('disabled',false);
				$("#stop"+fTimerBh+"").attr('disabled',true);
			} else {
				swal("停止失败!",data.msg,"error");
			}
			},
		complete: function () {
	 	 		hiddenLoad();
	 	 	},
		error : function(data) {
				swal("停止失败!",data.msg,"error");
			}
			});			
	});
	//立即启动定时任务
	$(document).on('click','#TimerManageTable button.startnow',function() {
		var fTimerBh = $(this).data("id");

		$.ajax({
			url : _ctx + "/view/TimerManage/startTimerTaskNow",
			type : "post",
			data : {
				"fTimerBh" : fTimerBh
			},
// 		beforeSend: function () { 
// 	 	 		showLoad();	             
// 	 	 },
		success : function(data) {
			if (data.status == '1') {
				$("#TimerManageTable").tabulator("updateRow",isspview_scheduledtask_timermanagepage.get_curRow(),
						{fTimerState : data.data.fTimerState,fStartTime : data.data.fStartTime});
				$("#start"+fTimerBh+"").attr('disabled',true);
				$("#startnow"+fTimerBh+"").attr('disabled',true);
				$("#stop"+fTimerBh+"").attr('disabled',false);
			} else {
				swal("立即启动失败!",data.msg,"error");
			}
			},
// 		complete: function () {
// 	 	 		hiddenLoad();
// 	 	 	},
		error : function(data) {
				swal("立即启动失败!",data.msg,"error");
			}
			});			
	});
	return {
		// 查询,分页
		reLoadTimerManage : function (datas) {
			$.ajax({
				url : _ctx + '/view/TimerManage/getTimerManageList',
				type : "post",
				data : datas,
 		        beforeSend: function () { 
 		        	showLoad();	             
 		        },
				success : function(data) {
					$('#TimerManage_ibox').html(data);
				},
				complete: function () {
 	            	hiddenLoad();
 	            },
				error : function(XMLHttpRequest,textStatus, errorThrown) {
					toastr.error('', '查询失败');
				}
			});
		},
		startTimer : function (){
			$.ajax({
				url : _ctx + '/view/TimerManage/startTimer',
				type : "get",
				success : function(data) {
					if (data.status == '1') {
						swal({
				        	title : data.msg,// 展示的标题
				   			text : "",// 内容
				   			type: "success",
				   			showCloseButton : false, // 展示关闭按钮
				   			allowOutsideClick : false,
				   			showConfirmButton : false,
				   			timer: 1000
				   		});
						$("#TimerManageexport").attr('disabled',false);
						$("#TimerManageimport").attr('disabled',true);
					}
				},
				error : function(data) {
					swal("启动定时器失败!",data.msg,"error");
				}
			});
		},
		stopTimer : function(){
			$.ajax({
				url : _ctx + '/view/TimerManage/stopTimer',
				type : "get",
				success : function(data) {
					if (data.status == '1') {
						swal({
				        	title : data.msg,// 展示的标题
				   			text : "",// 内容
				   			type: "success",
				   			showCloseButton : false, // 展示关闭按钮
				   			allowOutsideClick : false,
				   			showConfirmButton : false,
				   			timer: 1000
				   		});
						$("#TimerManageexport").attr('disabled',true);
						$("#TimerManageimport").attr('disabled',false);
					}
				},
				error : function(data) {
					swal("停止定时器失败!",data.msg,"error");
				}
			});
		},
		getTimerState : function(){
			$.ajax({
				url : _ctx + '/view/TimerManage/getTimerState',
				type : "get",
				success : function(data) {
					if (data.status == '0') {
						$("#TimerManageimport").attr('disabled',true);
					} else {
						$("#TimerManageexport").attr('disabled',true);
					}
				},
				error : function(data) {
					swal("获取定时器状态失败!",data.msg,"error");
				}
			});
		},
		pageInit : function(){
			isspview_scheduledtask_timermanage.reLoadTimerManage("");
			isspview_scheduledtask_timermanage.getTimerState();
		}
	}
})(jQuery, window, document);	
isspview_scheduledtask_timermanage.pageInit();
</script>