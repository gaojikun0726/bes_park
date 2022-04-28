<!-----内容区域---->
<!-- <div class="ibox-content m-b-sm border-bottom"
	style="width: 100%; height: 6.5%">
	<div class="input-group" style="height: 100%; width: 100%;">
		<div
			class="divStyle_size">
			<a id="addBMJGMC" href="javascript:void(-1);"
				onclick="applicationmanage_esBmjg.role_show_addmodal()"
				class="btn btn-primary"> 增加  <i class="fa fa-plus"></i>
			</a>
		</div>
		<div
			style="width: 25%; float: right; position: relative; padding-right: 0px; padding-left: 15px;">
			<input type="text" class="input-sm form-control"
				style="width: calc(100% - 60px);" id="bmjgkeywords"
				name="bmjgkeywords" value="" placeholder="表名、表结构"> <span
				class="input-group-btn" style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryKeybtn">
					<i class="fa fa-search"></i> 搜索
				</button>
			</span>
		</div>
		<div class="zc_search find">
				<div class="zc_search_form">
				<input type="text" id="bmjgkeywords" name="bmjgkeywords" placeholder="表名、表结构...">
				<button  id="queryKeybtn"></button>
				</div>
		</div>
	   </div>
</div> -->
<!-- 内容区域 -->
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;数据编码规则列表>>>
		</span>
		<!-- 增加按钮 -->
		<a id="addBMJGMC" href="javascript:void(-1);" onclick="applicationmanage_esBmjg.role_show_addmodal()" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加 
		</a> 
		<!-- 搜索框 -->
		<div class="zc_search find">
				 <input type="text" class="find-style"  id="bmjgkeywords" name="bmjgkeywords" placeholder="表名、表结构...">
				 <button id="queryKeybtn"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
<!---分页列表开始----->
	<div class="ibox" id="bmjg_ibox" style="height:92%">
		<#include "isspview/systemparameters/esBmjg_page.ftl"/>
	</div>

<!---添加适配器信息开始----->
<div class="modal fade" id="modal-form-addBmjg"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title addIcon">&nbsp; 添加编码结构信息</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="addESBmjg"
					name="addESBmjg" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label">字典表名<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="hidden" id="esBmjg_add_tableName" name="esBmjg_add_tableName" required
								class="form-control" readonly="readonly"> <input
								type="text" id="tableName" name="tableName"
								placeholder="请输入字典表名" required class="form-control">
						</div>
					</div>
					<!-- <div class="form-group">
						<label class="col-sm-3 control-label">编码结构<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<select id='fSbmc' name='fSbmc' class="form-control selectpicker"
								data-live-search="false">
							</select>
						</div>
					</div> -->
					<div class="form-group">
						<label class="col-sm-3 control-label">编码结构<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="bmjg" name="bmjg"
								placeholder="请输入编码结构" required class="form-control">
						</div>
					</div>
					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-3 display_flex">
							<button class="btn btn-md btn-primary" type="submit">
								<strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong>
							</button>
							<button class="btn btn-white m-l-sm" type="button"
								data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<!----编辑--->
<div class="modal fade" id="editBmjgForm" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title editIcon">&nbsp;编辑编码结构信息</h4>
			</div>
			<div class="modal-body">
				<form id="editBmjgFormFunc" name="editBmjgFormFunc"
					class="form-horizontal">
					<!-- <div class="form-group">
						<label class="col-sm-4 control-label" for="fSbmc">设备名称 <span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<select type="text" id='edit_fSbmc' name="edit_fSbmc" required
								class="form-control selectpicker" >
							</select>
						</div>
					</div> -->
					<div class="form-group">
						<label class="col-sm-4 control-label" for="tableName">字典表明
						 <span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="edite_tableName" name="edite_tableName" required
								class="form-control" readonly >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label" for="bmjg">编码结构
							<span class="text-danger">*</span>
						</label>
						<div class="col-sm-8">
							<input type="text" id="edite_bmjg"
								name="edite_bmjg" required class="form-control">
						</div>
					</div>

					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-4 display_flex">
							<button class="btn btn-md btn-primary " type="submit">
								<strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong>
							</button>
							<button type="button" class="btn btn-white m-l-sm"
								data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	;
	var applicationmanage_esBmjg = (function($, window, document, undefined) {
		var _ctx = '${ctx}';
		var _includecurRow = null;//“已选择”table对应行
		
		$(window).resize(function() {
			$("#bmjgTable").tabulator("redraw");
		});
		

		//触发搜索的回车时间
		$("#bmjgkeywords").focus(function() {
			$(this).keydown(function(e) {
				if (e.which == "13") {
					applicationmanage_esBmjg.reLoadEquipmentType({
						"keywords" : $("#bmjgkeywords").val(),
						bars:$("#esBmjg_pageSize").val(),
					});//触发该事件
				}
			})
		});

		$("#queryKeybtn").click(function() {
			applicationmanage_esBmjg.reLoadEquipmentType({
				"keywords" : $("#bmjgkeywords").val(),
				bars:$("#esBmjg_pageSize").val(),
			});
		});

		//添加适配器表单验证
		var euserValidator = $("#addESBmjg").validate({
			rules : {
				tableName : {
					required : true,
					rangelength : [ 1, 20 ],
					isNormal:true
				},
				bmjg : {
					required : true,
					rangelength : [ 1, 20 ],
					isNormal:true
				},
			},
			messages : {
				tableName: {
			  		required: "请输入字典表名！",
			            minlength : jQuery.validator
									.format("Enter at least {0} characters")
			    },
				bmjg : {
					required : "请输入编码结构！",
					minlength : jQuery.validator
							.format("Enter at least {0} characters")
				},
				
			},
			submitHandler : function(form) {
				;
				addform_EsbmjgRole(form);
			}
		});

		//居中显示（添加）
		$('#modal-form-addBmjg')
				.on(
						'show.bs.modal',
						function() {
							//模态框拖动********************
							$(this).draggable({
								handle:".modal-header"
							});
							$(this).css("overflow","hidden");
							//*************************************
							// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
							$(this).css('display', 'block');
							var modalHeight = $(window).height()
									/ 2
									- $(
											'#modal-form-addBmjg .modal-dialog')
											.height() / 2;
							$(this).find('.modal-dialog').css({
								'margin-top' : modalHeight
							});
						})
		//居中显示（编辑）
		$('#editBmjgForm').on(
				'show.bs.modal',
				function() {
					$(this).css('display', 'block');
					var modalHeight = $(window).height() / 2
							- $('#editBmjgForm .modal-dialog').height() / 2;
					$(this).find('.modal-dialog').css({
						'margin-top' : modalHeight
					});
				})
				
		//居中显示（包含用户）
 	$('#searchBmjg').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#searchBmjg .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})		
		//关闭模态框清空表单值
		$("#modal-form-addBmjg").on('hidden.bs.modal',
				function(event) {
					$(this).find("input").val("");
					$("#fSbmc").empty();
					euserValidator.resetForm();
				})


		//删除数据
		$(document).on(
				'click',
				'#bmjgTable button.delete',
				function() {
					;
					var id = $(this).data("id").toString();
					//var id= isspview_applicationmanage_esBmjg_page.getCurRow().getData().tableName
					swal({
						title : "您确定要删除吗?",
						text : "信息删除后将不可恢复!",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#1ab394",
						confirmButtonText : "确定",
						closeOnConfirm : false
					}, function() {
						$.ajax({
							url : _ctx + "/isspview/applicationmanage/delBmjg",
							type : "post",
							data : {
								"tableName" : id
							},
							beforeSend : function() {
								showLoad();
							},
							success : function(data) {
								if (data.status === '1') {
									
									//swal(data.msg, "", "success");
								    swal({ 
							            title: data.msg,
							            text: "",
							            type: "success",
							            showCloseButton:false,
							            allowOutsideClick:false,
							            showConfirmButton: false,
							            timer:1000
							        });
								    $("#bmjgTable").tabulator("deleteRow",
											isspview_applicationmanage_esBmjg_page.getCurrentRow()); 
								    var getTable = $("#bmjgTable").tabulator("getData");
				              		$("#bmjgTable").tabulator("setData", getTable);
								} else {
									swal(data.msg, "", "error");
								}
							},
							complete : function() {
								hiddenLoad();
							},
							error : function(data) {
								swal(data.msg, "", "error");
							}
						});
					});
				});

		
		//关闭模态框清空表单值
	    $("#editBmjgForm").on('hidden.bs.modal', function (event) {
	        $(this).find("input").val("");
	        euserValidatoredit.resetForm();  
	    });
		//表单验证
	var euserValidatoredit =	$("#editBmjgFormFunc").validate({
		
		rules : {
			edite_tableName : {
				required : true,
				rangelength : [ 1, 20 ],
				isNormal:true
			},
			edite_bmjg : {
				required : true,
				rangelength : [ 1, 20 ],
				isNormal:true
			},
		},
		messages : {
			edite_tableName: {
		  		required: "请输入字典表名！",
		            minlength : jQuery.validator
								.format("Enter at least {0} characters")
		    },
		    edite_bmjg : {
				required : "请输入编码结构！",
				minlength : jQuery.validator
						.format("Enter at least {0} characters")
			},
			
		},
			submitHandler : function(form) {
				editBmjgFormFunc(form);
			}
		});
		//新增添加
		function addform_EsbmjgRole(form) {
			$.ajax({
				url : _ctx + "/isspview/applicationmanage/insBmjg",
				type : "post",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify({
					tableName :  $("#tableName").val(),
					bmjg : $("#bmjg").val(),
				}),
				success : function(data) {
					if (data.status === '1') {
						//swal(data.msg, "", "success");
						swal({ 
					            title: data.msg,
					            text: "",
					            type: "success",
					            showCloseButton:false,
					            allowOutsideClick:false,
					            showConfirmButton: false,
					            timer:1000
					        });
							$('#modal-form-addBmjg').modal('hide');//关闭编辑窗口
							//在表格中添加数据
							$('#bmjgTable').tabulator("addRow", {
								tableName : data.data.tableName,
								bmjg : data.data.bmjg,
								//fSbmc : data.data.fSbmc,
								crdate : data.data.crdate,
								chdate : data.data.chdate
							});
					} else {
						swal(data.msg, "添加失败", "error");
					}
				},
				error : function(data) {
					swal(data.msg, "添加失败", "error");
				}
			});
			return false;
		}
		//编辑
		function editBmjgFormFunc(form) {
			;
			$.ajax({
				url : _ctx + "/isspview/applicationmanage/updBmjg",
				type : "post",
				data : ({
					tableName : $("#edite_tableName").val(),
					//fSblx : $("#edit_fSbmc").val(),
					//fSbmc : $("#edit_fSbmc option:selected").text(),
					bmjg : $("#edite_bmjg").val(),
					crdate : $("#edite_crdate").val(),
				}),
				success : function(data) {
					;
					if (data.status === '1') {
						;
						swal({ 
				            title: data.msg,
				            text: "",
				            type: "success",
				            showCloseButton:false,
				            allowOutsideClick:false,
				            showConfirmButton: false,
				            timer:1000
				        });
						$('#bmjgTable').tabulator("updateRow", isspview_applicationmanage_esBmjg_page.getCurrentRow(), {
							//fSbmc : data.data.fSbmc,
							tableName : data.data.tableName,
							bmjg : data.data.bmjg,
							chdate : data.data.chdate,
						});
						setTimeout(function() {
							$('#editBmjgForm').modal('hide');//关闭编辑窗口
						}, 1000)
					} else {
						swal("修改失败!", data.msg, "error");
					}
				},
				error : function(data) {
					swal("修改失败!", data.msg, "error");
				}
			});
		}

		//验证在模态框出现前加载
		$("#editBmjgForm").on('show.bs.modal', function(event) {
			;
			$("#edit_fSbmc").empty();
			var button = $(event.relatedTarget);
			var id = button.data("id").toString();
			//模态框拖动********************
			$(this).draggable({
				handle:".modal-header"
			});
			$(this).css("overflow","hidden");
			//*************************************
			/*  $.ajax({
				type : "get",
				url : _ctx + '/adapter/getallbesequipment',
				success : function(data) {
					;
					var ops = "<option value=''>请选择适配器名称</option>";
					for (var i = 0; i < data.list.length; i++) {
						var obj = data.list[i];
						ops += '<option value="'+obj.f_type+'">';
						ops += obj.f_sbmc;
						ops += '</option>';
					} */
						//$("#edit_fSbmc").append(ops); 
			$.ajax({
				url : _ctx + "/isspview/applicationmanage/selectBmjg",
				type : "post",
				data : {
					"tableName" : id
				},
				success : function(result) {
					;
					$("#edite_tableName").val(result.data.tableName);
					$("#edite_bmjg").val(result.data.bmjg);
					/* $("#edit_fSbmc").val(result.data.fSblx);
					$("#edite_fAdaptermc").val(result.data.fAdaptermc);
					$("#edite_fIp").val(result.data.fIp);
					$("#edite_fPort").val(result.data.fPort);
					$("#edite_fClasspath").val(result.data.fClasspath); */
				}
			});
		//},
			 /* error : function(msg) {
				alert("select列表查询失败!");
			} 
		}); */
	});
		
		  /*  //验证在模态框出现前加载包含用户(可拖动)
		  $("#searchBmjg").on('show.bs.modal', function(event) {
		    var button = $(event.relatedTarget);
		    var id = button.data("id");			//获取guid
		    $(this).draggable({
		        handle: ".modal-header"     	// 只能点击头部拖动
		    });
		    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
			role_loadIncludeInteface(id);
			_froleguid = id;
			
		});  */
		  
		return {
			//验证增加模态框是否弹出
			 role_show_addmodal : function() {
				$('#modal-form-addBmjg').modal('show');
				/* $.ajax({
					type : "get",
					url : _ctx + '/adapter/getallbesequipment',
					success : function(data) {
						var ops = "<option value=''>请选择适配器名称</option>";
						for (var i = 0; i < data.list.length; i++) {
							var obj = data.list[i];
							ops += '<option value="'+obj.f_type+'">';
							ops += obj.f_sbmc;
							ops += '</option>';
						}

							$("#fSbmc").append(ops);
					},
					error : function(msg) {
						alert("select列表查询失败!");
					}

				}); */
			
			}, 
			
			// 分页查询
			reLoadEquipmentType : function(datas) {
				$.ajax({
					url : _ctx + '/isspview/applicationmanage/esBmjg_page',
					type : "post",
					data : datas,
					beforeSend : function() {
						showLoad();
					},
					success : function(data) {
						
						$('#bmjg_ibox').html(data);
					},
					complete : function() {
						hiddenLoad();
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error('', '查询失败');
					}
				});
			},
			pageInit : function() {
				applicationmanage_esBmjg.reLoadEquipmentType();
			}
		}
	})(jQuery, window, document);
	applicationmanage_esBmjg.pageInit();
</script>