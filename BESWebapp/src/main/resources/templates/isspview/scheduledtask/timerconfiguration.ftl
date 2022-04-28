<!-----内容区域---->	
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;定时任务配置列表>>>
		</span>
		<!-- 增加按钮 -->
		<a id="addTimerConf"  data-toggle="modal" href="#modal-form-addTimerConfiguration"  class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加 
		</a> 
		<!-- 搜索框 -->
		<div class="zc_search find">
				 <input type="text" class="find-style"  id="TimerConfigurationKeywords" name="TimerConfigurationKeywords" placeholder="定时器名称">
				 <button id="queryTimerConfigurationBtn"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
<!---分页列表----->	        
    <div class="ibox" id="TimerConfiguration_ibox" style="height:92%">
         	<#include "/isspview/scheduledtask/timerconfiguration_page.ftl"/>
    </div>

<!---添加定时任务开始----->
<div class="modal fade" id="modal-form-addTimerConfiguration" style="top:20%;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加定时任务</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addTimerConfiguration" name="addTimerConfiguration" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">任务名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="F_TIMER_NAME" name="F_TIMER_NAME" placeholder="任务名称" class="form-control">
                        </div>
                    </div>                                        
                    <div class="form-group">
                          <label class="col-sm-3 control-label">设备适配器<span class="text-danger">*</span></label>
                          <div class="col-sm-8">
							<select id='F_ADAPTERMC' name='F_ADAPTERMC' required
								class="form-control selectpicker"
								onchange="isspview_scheduledtask_timerconfiguration.getIntefaceadapter()"
								data-live-search="false">
							</select>
						</div>
                    </div>
                    <div class="form-group">
                          <label class="col-sm-3 control-label">服务接口<span class="text-danger">*</span></label>
                          <div class="col-sm-8">
                          	<select id='F_SERVICE_MC' name='F_SERVICE_MC' required class="form-control selectpicker" data-live-search="false">
							</select> 
                          </div>
                    </div>       
                    <div class="form-group">
                        <label class="col-sm-3 control-label">任务类型<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
							<select id='F_TIMER_TYPEBH' name='F_TIMER_TYPEBH' required
								class="form-control selectpicker"
								onchange="isspview_scheduledtask_timerconfiguration.typeTime()"
								data-live-search="false">
							</select>
						</div>
                    </div> 
                    <div class="form-group" id="F_LOOP_TIME_DIV">
                        <label class="col-sm-3 control-label">循环时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="F_LOOP_TIME" name="F_LOOP_TIME" placeholder="循环分钟间隔，例：10" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group" id="F_FIXED_TIME_DIV">
                        <label class="col-sm-3 control-label">固定时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="F_FIXED_TIME" name="F_FIXED_TIME" placeholder="请输入时间，例：12:30" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group" id="F_VAR_TIME_DIV">
                        <label class="col-sm-3 control-label">星期/日期<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="F_VAR_TIME" name="F_VAR_TIME" placeholder="请输入星期/日期，例：7或30" class="form-control">
                        </div>
                    </div>              
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!---编辑定时任务开始----->
<div class="modal fade" id="modal-form-editTimerConfiguration" style="top:20%;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑定时任务</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="editTimerConfiguration" name="editTimerConfiguration" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">任务名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_F_TIMER_NAME" name="edit_F_TIMER_NAME" placeholder="任务名称"  required class="form-control">
                        </div>
                    </div>                                        
                    <div class="form-group">
                          <label class="col-sm-3 control-label">设备适配器<span class="text-danger">*</span></label>
                          <div class="col-sm-8">
							<select id='edit_F_ADAPTERMC' name='edit_F_ADAPTERMC' required
								class="form-control selectpicker"
								onchange="isspview_scheduledtask_timerconfiguration.edit_getIntefaceadapter()"
								data-live-search="false">
							</select>
						</div>
                    </div>
                    <div class="form-group">
                          <label class="col-sm-3 control-label">服务接口<span class="text-danger">*</span></label>
                          <div class="col-sm-8">
                          	<select id='edit_F_SERVICE_MC' name='edit_F_SERVICE_MC' required class="form-control selectpicker" data-live-search="false">
							</select> 
                          </div>
                    </div>       
                    <div class="form-group">
                        <label class="col-sm-3 control-label">任务类型<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
							<select id='edit_F_TIMER_TYPEBH' name='edit_F_TIMER_TYPEBH'
								required class="form-control selectpicker"
								onchange="isspview_scheduledtask_timerconfiguration.edit_typeTime()"
								data-live-search="false">
							</select>
						</div>
                    </div> 
                    <div class="form-group" id="edit_F_LOOP_TIME_DIV">
                        <label class="col-sm-3 control-label">循环时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_F_LOOP_TIME" name="edit_F_LOOP_TIME" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group" id="edit_F_FIXED_TIME_DIV">
                        <label class="col-sm-3 control-label">固定时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_F_FIXED_TIME" name="edit_F_FIXED_TIME" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group" id="edit_F_VAR_TIME_DIV">
                        <label class="col-sm-3 control-label">星期/日期<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_F_VAR_TIME" name="edit_F_VAR_TIME" class="form-control">
                        </div>
                    </div>              
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-----内容区域结束---->	


<script type="text/javascript">
;
var isspview_scheduledtask_timerconfiguration = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var timerbh = "";//定时任务编号
	$(window).resize(function() {
		$("#TimerConfigurationTable").tabulator("redraw");
	});
	//触发搜索的回车事件
 	$("#TimerConfigurationKeywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  isspview_scheduledtask_timerconfiguration.reLoadTimerConfiguration({
			"keywords" : $("#TimerConfigurationKeywords").val(),
			bars:$("#timerconfiguration_pageSize").val(),
			});//触发该事件
					    } 
					  })
					});
					
	//查询
	$("#queryTimerConfigurationBtn").click(function() {
	isspview_scheduledtask_timerconfiguration.reLoadTimerConfiguration({
			"keywords" : $("#TimerConfigurationKeywords").val(),
			bars:$("#timerconfiguration_pageSize").val(),
	});
	});
	// 自定义method 验证F_FIXED_TIME
	jQuery.validator.addMethod("method",  function(value, element) {
		return this.optional(element) || /^(([0-1][0-9])|([1-2][0-3])):([0-5][0-9])$/.test(value);
	}, "");  
	//添加定时任务表单验证
	var TimerConfigurationValidator = $("#addTimerConfiguration").validate({
		rules : {
			F_TIMER_NAME : {
				required : true,
				rangelength : [ 1, 20 ],
			},
			F_TIMER_TYPEBH : {
				required : true,
				rangelength : [ 1, 20 ],
			},
			F_LOOP_TIME : {
				required : true,
				digits : true,
				rangelength : [ 1, 20 ],
			},
			F_FIXED_TIME : {
				method : true,
				required : true,
				rangelength : [ 1, 20 ],
			},
			F_VAR_TIME : {
				required : true,
				digits : true,
				range : [1,31],
			},						
		},
		messages : {
			F_TIMER_NAME: {
		  		required: "此字段不能为空，请按要求输入内容！",
		            minlength : jQuery.validator
								.format("Enter at least {0} characters")
		    },
		    F_TIMER_TYPEBH : {
				required : "此字段不能为空，请按要求输入内容！",
				minlength : jQuery.validator
						.format("Enter at least {0} characters")
			},
			F_LOOP_TIME : {
				required : "请输入合法整数！",
				minlength : jQuery.validator
						.format("Enter at least {0} characters")
			},
			F_FIXED_TIME : {
				method : "请输入合法时间,例 12:30",
				required : "请输入时间",
				minlength : jQuery.validator
						.format("Enter at least {0} characters")
			},
			F_VAR_TIME : {
				required : "请输入介于1和31之间的整数！",
				minlength : jQuery.validator
						.format("Enter at least {0} characters")
			}
		},
		submitHandler : function(form) {
			addTimerConfiguration(form);
		}
	});
	//编辑定时任务表单验证
	var editTimerConfigurationValidator = $("#editTimerConfiguration").validate({
		rules : {
			edit_F_TIMER_NAME : {
				required : true,
				rangelength : [ 1, 20 ],
			},
			edit_F_TIMER_TYPEBH : {
				required : true,
				rangelength : [ 1, 20 ],
			},
			edit_F_LOOP_TIME : {
				required : true,
				digits : true,
				rangelength : [ 1, 20 ],
			},
			edit_F_FIXED_TIME : {
				method : true,
				required : true,
				rangelength : [ 1, 20 ],
			},
			edit_F_VAR_TIME : {
				required : true,
				digits : true,
				range : [1,31],
			},						
		},
		messages : {
			edit_F_TIMER_NAME: {
		  		required: "此字段不能为空，请按要求输入内容！",
		            minlength : jQuery.validator
								.format("Enter at least {0} characters")
		    },
		    edit_F_TIMER_TYPEBH : {
				required : "此字段不能为空，请按要求输入内容！",
				minlength : jQuery.validator
						.format("Enter at least {0} characters")
			},
			edit_F_LOOP_TIME : {
				required : "请输入合法整数！",
				minlength : jQuery.validator
						.format("Enter at least {0} characters")
			},
			edit_F_FIXED_TIME : {
				method : "请输入合法时间,例 12:30",
				required : "请输入时间",
				minlength : jQuery.validator
						.format("Enter at least {0} characters")
			},
			edit_F_VAR_TIME : {
				required : "请输入介于1和31之间的整数！",
				minlength : jQuery.validator
						.format("Enter at least {0} characters")
			}
		},
		submitHandler : function(form) {
			editTimerConfiguration();
		}
	});
	//新增添加
	function addTimerConfiguration(form) {
		$.ajax({
			url : _ctx + "/view/TimerConfiguration/addTimerConfiguration",
			type : "post",
			data : ({
				F_TIMER_NAME : $("#F_TIMER_NAME").val(),
				F_EQADAPTER_GUID : $("#F_ADAPTERMC").val(),
				F_INTERFACE_GUID : $("#F_SERVICE_MC").val(),
				F_TIMER_TYPEBH : $("#F_TIMER_TYPEBH").val(),
				F_LOOP_TIME : $("#F_LOOP_TIME").val(),
				F_FIXED_TIME : $("#F_FIXED_TIME").val(),
				F_VAR_TIME : $("#F_VAR_TIME").text(),
			}),
			success : function(data) {
				if (data.status === '1') {
					swal({ 
				            title: data.msg,
				            text: "",
				            type: "success",
				            showCloseButton:false,
				            allowOutsideClick:false,
				            showConfirmButton: false,
				            timer:1000
				        });
						$('#modal-form-addTimerConfiguration').modal('hide');//关闭添加窗口
						//在表格中添加数据
			            $('#TimerConfigurationTable').tabulator("addRow", data.data);
				} else {
					swal(data.msg, "", "error");
				}
			},
			error : function(data) {
				swal(data.msg, "", "error");
			}
		});
	}
	//在增加模态框出现前加载
 	$("#modal-form-addTimerConfiguration").on('shown.bs.modal', function(event) {
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
		//*************************************
 		//设备适配器
		 $.ajax({
				type : "get",
				url : _ctx + '/view/TimerConfiguration/getEquipmentadapter',
				success : function(data) {
					var ops = "<option value=''>请选择设备适配器</option>";
					if(data.list != null){
					for (var i = 0; i < data.list.length; i++) {
						var obj = data.list[i];
						ops += '<option value="'+obj.F_GUID+'">';
						ops += obj.F_ADAPTERMC;
						ops += '</option>';
					}
					}
						$("#F_ADAPTERMC").append(ops);						
				},
				error : function(msg) {
					alert("适配器列表查询失败!");
				}

			}); 
 		//服务接口 @Modifier suhang
         $.ajax({
				type : "get",
				url : _ctx + '/view/spqmanager/getAllIntefaceadapter',
				success : function(data) {
					var ops = "<option value=''>请选择服务接口</option>";
					for (var i = 0; i < data.list.length; i++) {
						var obj = data.list[i];
						ops += '<option value="'+obj.fGuid+'">';
						ops += obj.fServiceMc;
						ops += '</option>';
					}

						$("#F_SERVICE_MC").append(ops);						
				},
				error : function(msg) {
					alert("服务接口列表查询失败!");
				}

			}); 
	 	//定时任务类型
		 $.ajax({
				type : "get",
				url : _ctx + '/view/TimerConfiguration/getTimertype',
				success : function(data) {
					var ops = "<option value=''>请选择任务类型</option>";
					for (var i = 0; i < data.list.length; i++) {
						var obj = data.list[i];
						ops += '<option value="'+obj.F_TYPE+'">';
						ops += obj.F_NAME;
						ops += '</option>';
					}

						$("#F_TIMER_TYPEBH").append(ops);
				},
				error : function(msg) {
					alert("任务类型列表查询失败!");
				}

			});
 	});

	//在编辑模态框出现前加载
 	$("#modal-form-editTimerConfiguration").on('show.bs.modal', function(event) {
 		var button = $(event.relatedTarget);
		var id = button.data("id");
		timerbh = id;
		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
		//*************************************
 		//设备适配器
		 $.ajax({
				type : "get",
				url : _ctx + '/view/TimerConfiguration/getEquipmentadapter',
				success : function(data) {					
					var ops = "<option value=''>请选择设备适配器</option>";
					if(data.list != null){
					for (var i = 0; i < data.list.length; i++) {
						var obj = data.list[i];
						ops += '<option value="'+obj.F_GUID+'">';
						ops += obj.F_ADAPTERMC;
						ops += '</option>';
					}
					}
						$("#edit_F_ADAPTERMC").append(ops);
				 		//服务接口
						 $.ajax({
								type : "post",
								url : _ctx + '/view/TimerConfiguration/getIntefaceadapter',
								data : {
									"F_ADAPTERGUID" : ""
								},
								success : function(data) {
									var ops = "<option value=''>请选择服务接口</option>";
									if(data.list != null){
									for (var i = 0; i < data.list.length; i++) {
										var obj = data.list[i];
										ops += '<option value="'+obj.F_GUID+'">';
										ops += obj.F_SERVICE_MC;
										ops += '</option>';
									}
									}
										$("#edit_F_SERVICE_MC").append(ops);
									 	//定时任务类型
										 $.ajax({
												type : "get",
												url : _ctx + '/view/TimerConfiguration/getTimertype',
												success : function(data) {
													var ops = "<option value=''>请选择任务类型</option>";
													for (var i = 0; i < data.list.length; i++) {
														var obj = data.list[i];
														ops += '<option value="'+obj.F_TYPE+'">';
														ops += obj.F_NAME;
														ops += '</option>';
													}

														$("#edit_F_TIMER_TYPEBH").append(ops);
														edit_valuation(id);
												},
												error : function(msg) {
													alert("任务类型列表查询失败!");
												}

											});
								},
								error : function(msg) {
									alert("服务接口列表查询失败!");
								}

							});
				},
				error : function(msg) {
					alert("适配器列表查询失败!");
				}

			}); 


		 	 	
 	});
	//编辑前加载，填充数据
	function edit_valuation(id){
		$.ajax({
			url : _ctx + "/view/TimerConfiguration/getTimerConfigurationById",
			type : "post",
			data : {
				"F_TIMER_BH" : id
			},
			success : function(result) {
				$("#edit_F_TIMER_NAME").val(result.data[0].F_TIMER_NAME);
				$("#edit_F_ADAPTERMC").val(result.data[0].F_ADAPTERMC_ID);
				$("#edit_F_SERVICE_MC").val(result.data[0].F_SERVICE_ID);
				$("#edit_F_TIMER_TYPEBH").val(result.data[0].F_TIMER_TYPEBH);
				$("#edit_F_LOOP_TIME").val(result.data[0].F_LOOP_TIME);
				$("#edit_F_FIXED_TIME").val(result.data[0].F_FIXED_TIME);
				$("#edit_F_VAR_TIME").val(result.data[0].F_VAR_TIME);
				isspview_scheduledtask_timerconfiguration.edit_typeTime();
			}
		});
	}	
	//编辑
	function editTimerConfiguration() {
		$.ajax({
			url : _ctx + "/view/TimerConfiguration/updateTimerConfiguration",
			type : "post",
			data : ({		
				F_TIMER_BH : timerbh,
				F_TIMER_NAME : $("#edit_F_TIMER_NAME").val(),
				F_EQADAPTER_GUID : $("#edit_F_ADAPTERMC").val(),
				F_INTERFACE_GUID : $("#edit_F_SERVICE_MC").val(),
				F_TIMER_TYPEBH : $("#edit_F_TIMER_TYPEBH").val(),
				F_LOOP_TIME : $("#edit_F_LOOP_TIME").val(),
				F_FIXED_TIME : $("#edit_F_FIXED_TIME").val(),
				F_VAR_TIME : $("#edit_F_VAR_TIME").text(),
			}),
			success : function(data) {
				if (data.status === '1') {
					swal({ 
				            title: data.msg,
				            text: "",
				            type: "success",
				            showCloseButton:false,
				            allowOutsideClick:false,
				            showConfirmButton: false,
				            timer:1000
	        			});
					$('#modal-form-editTimerConfiguration').modal('hide');
					$('#TimerConfigurationTable').tabulator("updateRow",isspview_scheduledtask_timermanagepage.get_curRow(), data.data[0]);
				} else {
					swal("", data.msg, "error");
				}
			},
			error : function(data) {
				swal("", data.msg, "error");
			}
		});
	}
	//关闭添加模态框清空表单值
    $("#modal-form-addTimerConfiguration").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        $("#F_ADAPTERMC").empty();
        $("#F_SERVICE_MC").empty();
        $("#F_TIMER_TYPEBH").empty();
        TimerConfigurationValidator.resetForm();  
    });
	//关闭模态框清空表单值
    $("#modal-form-editTimerConfiguration").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        $("#edit_F_ADAPTERMC").empty();
        $("#edit_F_SERVICE_MC").empty();
        $("#edit_F_TIMER_TYPEBH").empty();
        editTimerConfigurationValidator.resetForm();  
    });	
	//删除数据
	$(document).on('click','#TimerConfigurationTable button.delete',function() {
		var row = $(this).parents("tr")[0];
		var id = $(this).data("id");
		swal(
		{
			title : "您确定要删除吗?",
			text : "信息删除后将不可恢复!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "确定",
			closeOnConfirm : false
		},
	function() {		
		$.ajax({
			url : _ctx + "/view/TimerConfiguration/del_TimerConfiguration",
			type : "post",
			data : {"F_TIMER_BH" : id},
			success : function(data) {
				if (data.status == '1') {
				swal( {title: data.msg,
			            text: "",
			            type: "success",
			            showCloseButton:false,
			            allowOutsideClick:false,
			            showConfirmButton: false,
			            timer:1000});
				// window.location.href="${ctx }/view/user/user"; 
				//重新加载列表及分页
				$("#TimerConfigurationTable").tabulator("deleteRow", isspview_scheduledtask_timermanagepage.get_curRow());
			} else {
				swal("删除失败!",data.msg,"error");
			}
			},
				error : function(data) {
				swal("删除失败!",data.msg,"error");
			}
			});
			
			});
	});
	return {
		//根据定时器类型判断触发时间类型（新增时）
		typeTime : function(){
			var F_TIMER_TYPEBH = $("#F_TIMER_TYPEBH").val();
			if(F_TIMER_TYPEBH == "LOOP"){
				$("#F_FIXED_TIME_DIV").attr("style", "display: none;"); 
				$("#F_VAR_TIME_DIV").attr("style", "display: none;"); 
				$("#F_LOOP_TIME_DIV").removeAttr("style");
			}else if(F_TIMER_TYPEBH == "ONCE"){
				$("#F_FIXED_TIME_DIV").attr("style", "display: none;"); 
				$("#F_VAR_TIME_DIV").attr("style", "display: none;");
				$("#F_LOOP_TIME_DIV").attr("style", "display: none;");
			}else if(F_TIMER_TYPEBH == "DAY"){
				$("#F_LOOP_TIME_DIV").attr("style", "display: none;");
				$("#F_VAR_TIME_DIV").attr("style", "display: none;");
				$("#F_FIXED_TIME_DIV").removeAttr("style");
			}else{
				$("#F_LOOP_TIME_DIV").attr("style", "display: none;");
				$("#F_FIXED_TIME_DIV").removeAttr("style");
				$("#F_VAR_TIME_DIV").removeAttr("style");
			}
		},
		//根据定时器类型判断触发时间类型（编辑时）
		edit_typeTime : function(){
			var F_TIMER_TYPEBH = $("#edit_F_TIMER_TYPEBH").val();
			if(F_TIMER_TYPEBH == "LOOP"){
				$("#edit_F_FIXED_TIME_DIV").attr("style", "display: none;");
				$("#edit_F_VAR_TIME_DIV").attr("style", "display: none;"); 
				$("#edit_F_LOOP_TIME_DIV").removeAttr("style");
			}else if(F_TIMER_TYPEBH == "ONCE"){
				$("#edit_F_FIXED_TIME_DIV").attr("style", "display: none;");
				$("#edit_F_VAR_TIME_DIV").attr("style", "display: none;");
				$("#edit_F_LOOP_TIME_DIV").attr("style", "display: none;");
			}else if(F_TIMER_TYPEBH == "DAY"){
				$("#edit_F_LOOP_TIME_DIV").attr("style", "display: none;");
				$("#edit_F_VAR_TIME_DIV").attr("style", "display: none;");
				$("#edit_F_FIXED_TIME_DIV").removeAttr("style");
			}else{
				$("#edit_F_LOOP_TIME_DIV").attr("style", "display: none;");
				$("#edit_F_FIXED_TIME_DIV").removeAttr("style");
				$("#edit_F_VAR_TIME_DIV").removeAttr("style");
			}
		},
		//获取服务接口列表（新增时）
		getIntefaceadapter : function (){
			$("#F_SERVICE_MC").empty();
			var id = $("#F_ADAPTERMC").val();
			 $.ajax({
					type : "post",
					url : _ctx + '/view/TimerConfiguration/getIntefaceadapter',
					data : {
						"F_ADAPTERGUID" : id
					},
					success : function(data) {
						var ops = "<option value=''>请选择服务接口</option>";
						if(data.list != null){
						for (var i = 0; i < data.list.length; i++) {
							var obj = data.list[i];
							ops += '<option value="'+obj.F_GUID+'">';
							ops += obj.F_SERVICE_MC;
							ops += '</option>';
						}
						}
							$("#F_SERVICE_MC").append(ops);
					},
					error : function(msg) {
						alert("服务接口列表查询失败!");
					}

				});
		},
		//获取服务接口列表（编辑时）
		edit_getIntefaceadapter : function (){
			$("#edit_F_SERVICE_MC").empty();
			var id = $("#edit_F_ADAPTERMC").val();
			 $.ajax({
					type : "post",
					url : _ctx + '/view/TimerConfiguration/getIntefaceadapter',
					data : {
						"F_ADAPTERGUID" : id
					},
					success : function(data) {
						var ops = "<option value=''>请选择服务接口</option>";
						for (var i = 0; i < data.list.length; i++) {
							var obj = data.list[i];
							ops += '<option value="'+obj.F_GUID+'">';
							ops += obj.F_SERVICE_MC;
							ops += '</option>';
						}

							$("#edit_F_SERVICE_MC").append(ops);
					},
					error : function(msg) {
						alert("服务接口列表查询失败!");
					}

				});
		},
		// 查询,分页
		reLoadTimerConfiguration : function (datas) {
			$.ajax({
				url : _ctx + '/view/TimerConfiguration/getTimerConfigurationList',
				type : "post",
				data : datas,
 		        beforeSend: function () { 
 		        	showLoad();	             
 		        },
				success : function(data) {
					$('#TimerConfiguration_ibox').html(data);
				},
				complete: function () {
 	            	hiddenLoad();
 	            },
				error : function(XMLHttpRequest,textStatus, errorThrown) {
					toastr.error('', '查询失败');
				}
			});
		},

		pageInit : function(){
			isspview_scheduledtask_timerconfiguration.reLoadTimerConfiguration("");
		}
	}
})(jQuery, window, document);	
isspview_scheduledtask_timerconfiguration.pageInit();
</script>