<!-----内容区域---->
<!-- 部门定义表
	创建人：sunhao
 -->
<!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择组织机构>>>
		</span>
	</div>
	<div id="tree_department" class="Information_area"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;部门信息列表>>>
			</span>
			<!-- 增加按钮 -->
			<a id="addDepartment" href="javascript:void(-1);" onclick="organizationmanage_bmdy.department_show_addmodal()" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加 
			</a> 
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="bmdy_keywords" name="bmdy_keywords" placeholder="输入部门编号/部门名称查询">
				  <button id="queryBmzdBtn"onclick="organizationmanage_bmdy.search_department()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="departmentTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->

<!---添加组织机构信息开始----->
<div class="modal fade" id="modal-form-addDepartment" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title addIcon">&nbsp; 添加部门信息</h4>
			</div>
			
			<div class="modal-body">
				<form role="form" id="addEdepartment" name="add" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label">部门名称
						<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="bmdy_f_bmmc" 
								name="bmdy_f_bmmc" placeholder="请输入部门名称" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">组织机构名称<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="bmdy_f_zzjgbh" readonly="readonly" name="bmdy_f_zzjgbh" 
								placeholder="请输入组织机构编号" class="form-control">
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

<!----编辑用户--->
<div class="modal fade" id="editDepartmentFormTable" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title editIcon">&nbsp; 编辑部门信息</h4>
			</div>
			<div class="modal-body">
				<form id="editDepartmentForm" name="editDepartmentForm" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label">部门编号</label>
						<div class="col-sm-8">
							<input type="text" id="edit_bmdy_f_bmbh" readonly="readonly"name="edit_bmdy_f_bmbh" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">部门名称<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="edit_bmdy_f_bmmc" name="edit_bmdy_f_bmmc"class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">组织机构名称<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="edit_bmdy_f_zzjgbh" name="edit_bmdy_f_zzjgbh" readonly="readonly"class="form-control"> 
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
<!---添加组织机构信息结束----->

<script type="text/javascript">
var organizationmanage_bmdy = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var _curRow = null;
	//设置格式
	var optIconFunction = function(cell, formatterParams) { //plain text value
		var department_idbmbh = cell.getRow().getData().f_bmbh;
		return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ department_idbmbh + " data-toggle='modal' data-target='#editDepartmentFormTable'><i class='fa fa-pencil' ></i> 编辑</button>"
				+ "<button class='btn btn-white btn-sm delete' data-id=" + department_idbmbh + "><i class='fa fa-trash'></i>  删除</button>"
	};
	//创建并设置table属性
	$("#departmentTable").tabulator({
		height : "100%",
		layout : "fitColumns",//fitColumns  fitDataFill
		columnVertAlign : "bottom", //align header contents to bottom of cell
		tooltips : true,
		selectable : 1,
		movableColumns : true,
		columns : [ 
		{title : "序号",field : "id",formatter : "rownum",frozen : false,align : "center",headerSort : false}, //frozen 
		{title : "部门编号",field : "f_bmbh",sorter : "string",align : "center",editor : false,headerSort : false},//never hide this column
		{title : "部门名称",field : "f_bmmc",sorter : "string",align : "left",editor : false,headerSort : false},
		{title : "组织机构编号",field : "f_zzjgbh",sorter : "string",align : "center",editor : false,headerSort : false}, 
		{title : "创建时间",field : "f_crdate",sorter : "date",align : "center",editable : false,headerSort : false}, 
		{title : "修改时间",field : "f_chdate",sorter : "date",align : "center",editor : false,headerSort : false}, 
		{title : "操作",field : "opt",width : 150,tooltip : false,tooltipsHeader : false,align : "center",formatter : optIconFunction,headerSort : false}, 
		],
		rowClick : function(e, row) {
			_curRow = row;
			var choiseNode = $('#tree_department').treeview('findNodes',[ _curRow.getData().f_zzjgbh, 'nodeTreeId' ]);
			$('#tree_department').treeview('searchByNode', choiseNode);//搜索设置高亮
		},
	});

	//填充数据
	//$("#departmentTable").tabulator("setData", _ctx + '/view/department/department');

	$(window).resize(function() {
		$("#departmentTable").tabulator("redraw");
	});
	//Clear table on "Empty the table" button click
	$("#clearZzjg").click(function() {
		$("#departmentTable").tabulator("clearData");
	});
	$(function() {
		$.ajax({
		 	type : "post",
			url : _ctx + "/view/user/zzjg_tree",
			dataType : "json",
			beforeSend: function () { 
 				showLoad();	             
 			},
			success : function(result) {
				$('#tree_department').treeview({
						data : result.list, // 数据源
						highlightSelected : true, //是否高亮选中
						levels : 2,
						enableLinks : true,//必须在节点属性给出href属性
						color : "#4a4747",
						onNodeSelected : function(event,nodeData) {
						$('#tree_department').treeview('clearSearch');//清除搜索选中高亮
						_zzjgbh = nodeData.nodeTreeId;
						_zzjgJs = nodeData.js;
						$.ajax({
							url : _ctx+ "/view/department/findByZzjg",
							contentType : "application/json; charset=utf-8",
							type : "get",
							data : {
								f_zzjgbh : nodeData.id
							},
							success : function(result) {
								if(result.hasOwnProperty("list")){
						            $("#departmentTable").tabulator("setData", result.list);
									}else{
						            $("#departmentTable").tabulator("setData", []);
									}
							},
							error : function(result) {
								swal("查询失败","","error");
							}
						});
							}
						});
				 if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
		            	if(result.list.length >0){//如果包含判断是否长度大于0
				            var firstNode = $("#tree_department").treeview('findNodes',[result.list[0].id,'id']);
				        	$("#tree_department").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
		             	}
		          }
			},
				complete: function () {
					hiddenLoad();
				},
				error : function(nodeData) {
					swal(nodeData.msg, "", "error");
				}
			});
	});
	//触发搜索的回车时间
	$("#bmdy_keywords").focus(function() {
		$(this).keydown(function(e) {
			if (e.which == "13") {
				organizationmanage_bmdy.search_department();//触发该事件
			}
		})
	});
	//添加组织机构表单验证
	var euserValidator = $("#addEdepartment").validate({
		rules : {
			bmdy_f_bmmc : {
			required : true,
			rangelength : [ 3, 20 ],
			},
		},
		messages : {
			bmdy_f_bmmc : {
			required : "请输入部门名称",
			minlength : jQuery.validator.format("Enter at least {0} characters")
			},
		},
		submitHandler : function(form) {
			organizationmanage_bmdy.addform_Department(form);
		}
	});

	
	//居中显示（添加）
	$('#modal-form-addDepartment').on('show.bs.modal',function() {
		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
		/* **********模态框拖动********** */
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
		/* ************************************* */
		$(this).css('display', 'block');
		var modalHeight = $(window).height() / 2- $('#modal-form-addDepartment .modal-dialog').height() / 2;
		$(this).find('.modal-dialog').css({'margin-top' : modalHeight
		});
	})
	//居中显示（编辑）
	$('#editDepartmentFormTable').on('show.bs.modal',function() {
		$(this).css('display', 'block');
		var modalHeight = $(window).height() / 2- $('#editDepartmentFormTable .modal-dialog').height() / 2;
		$(this).find('.modal-dialog').css({'margin-top' : modalHeight
		});
	})
	//关闭模态框清空表单值
	$("#modal-form-addDepartment").on('hidden.bs.modal', function(event) {
		$(this).find("input").val("");
		euserValidator.resetForm();
	});
	//关闭编辑模态框清空表单值
	$("#editDepartmentFormTable").on('hidden.bs.modal', function(event) {
		$(this).find("input").val("");
		edit_Validator.resetForm();
	});

	//删除数据
	$(document).on('click','#departmentTable button.delete',function() {
		var id = $(this).data("id");
		swal({
			title : "您确定要删除吗?",
			text : "信息删除后将不可恢复!",
			type : "warning",
			showCancelButton : true,
			/* confirmButtonColor : "#1ab394", */
			confirmButtonText : "确定",
			closeOnConfirm : false
		}, function() {
				$.ajax({
				url : _ctx + "/view/department/deleteESDepartment",
				contentType : "application/json; charset=utf-8",
				type : "post",
				data : JSON.stringify({
					f_bmbh : id
				}),
					success : function(data) {
						if (data.status == '1') {
							swal({
							    title : data.msg,// 展示的标题
							   	text : "", // 内容
							   	type: "success",
							   	showCloseButton : false, // 展示关闭按钮
							   	allowOutsideClick : false,
							   	showConfirmButton : false,
							   	timer: 1000
							});
							//在表格中删除该条数据
							$("#departmentTable").tabulator("deleteRow",_curRow);
							var getdepartmentTable = $("#departmentTable").tabulator("getData");
							$("#departmentTable").tabulator("setData",getdepartmentTable);
						} else {
									swal(data.msg, "", "error");
							}
					},
							error : function(data) {
								swal(data.msg, "", "error");
							}
				});
			});
		});

	//编辑组织机构
	//表单验证
	var edit_Validator=$("#editDepartmentForm").validate({
		rules : {
			edit_bmdy_f_bmmc : {
			required : true,
			rangelength : [ 3, 20 ],
			},
		},
		messages : {
			edit_bmdy_f_bmmc : {
			required : "请输入部门名称",
			minlength : jQuery.validator.format("Enter at least {0} characters")
			},
		},
		submitHandler : function(form) {
			organizationmanage_bmdy.editDepartmentForm(form);
		}
	});
	//验证在模态框出现前加载
	$("#editDepartmentFormTable").on('show.bs.modal', function(event) {
		var id = _curRow.getData().f_bmbh;
		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
		//*************************************
		$.ajax({
			url : _ctx + "/view/department/loadeditobj",
			type : "post",
			contentType : "application/json; charset=utf-8",
			data : JSON.stringify({
				f_bmbh : id
			}),
			success : function(result) {
				$("#edit_bmdy_f_bmbh").val(result.f_bmbh);
				$("#edit_bmdy_f_bmmc").val(result.f_bmmc);
				$("#edit_bmdy_f_zzjgbh").val(result.f_zzjgbh);
			}
		});
	});
	return{
		//验证增加模态框是否弹出
		department_show_addmodal:function () {
			var node = $('#tree_department').treeview('getSelected');
			if (node.length == 0) {//凡是节点，note.length都为1；无选择节点，为0；
				swal({
					title : "请选择节点",
					text : "经检测，您要未选择组织机构节点!",
					type : "warning",
					showCancelButton : false,
					/* confirmButtonColor : "#1ab394", */
					confirmButtonText : "关闭",
					closeOnConfirm : false
				});
			} else {
				$('#modal-form-addDepartment').modal('show');
				$("#bmdy_f_zzjgbh").val(node[0].nodeTreeId);
				$("#").val(node[0].text);
			}
		},
		//新增保存
		addform_Department:function(form) {
			var n=$("#bmdy_f_bmmc").val();
			$.ajax({
				url : _ctx + "/view/department/addESDepartment",
				type : "post",
				contentType: "application/json; charset=utf-8",
				 data:JSON.stringify({     
			 			/* f_bmbh:$("#bmdy_f_bmbh").val(), */
			 			f_bmmc:$("#bmdy_f_bmmc").val(),
			 			f_zzjgbh:$("#bmdy_f_zzjgbh").val()
			 	}),
				success : function(result) {
					if (result.status == '1') {
						 swal({
				        	title : result.msg,// 展示的标题
				   			text : "", // 内容
				   			type: "success",
				   			showCloseButton : false, // 展示关闭按钮
				   			allowOutsideClick : false,
				   			showConfirmButton : false,
				   			timer: 1000
				   		});
						//setTimeout(function() {
							$('#modal-form-addDepartment').modal('hide');//关闭编辑窗口
							//在表格中添加数据
							$('#departmentTable').tabulator("addRow", {
								f_bmbh : result.data.f_bmbh,
								f_bmmc : result.data.f_bmmc,
								f_zzjgbh : result.data.f_zzjgbh,
								f_crdate : result.data.f_crdate,
								f_chdate : result.data.f_chdate
							});
						//}, 1000)
					} else {
						swal(result.msg, "", "error");
					}
				},
				complete: function () {
					hiddenLoad();
				},
				error : function(result) {
					swal(result.msg, "", "error");
				}
			});
			return false;
		},
		//搜索
		search_department:function () {
			var bmdy_keywords = $("#bmdy_keywords").val();
			$("#departmentTable").tabulator("setData",_ctx + '/view/department/findByKeywords?keywords=' + bmdy_keywords);
		},
		//编辑
		editDepartmentForm:function(form) {
			$.ajax({
				url : _ctx + "/view/department/updateESDepartment",
				type : "post",
				data : ({
					f_bmbh : $("#edit_bmdy_f_bmbh").val(),
					f_bmmc : $("#edit_bmdy_f_bmmc").val(),
					f_zzjgbh : $("#edit_bmdy_f_zzjgbh").val(),
				}),
				success : function(result) {
					if (result.status == '1') {
						 swal({
					        title : result.msg,// 展示的标题
					   		text : "", // 内容
					   		type: "success",
					   		showCloseButton : false, // 展示关闭按钮
					   		allowOutsideClick : false,
					   		showConfirmButton : false,
					   		timer: 1000
					   	});
						$('#editDepartmentFormTable').modal('hide');//关闭编辑窗口
						$('#departmentTable').tabulator("updateRow", _curRow, {
							f_bmbh : result.data.f_bmbh,
							f_bmmc : result.data.f_bmmc,
							f_zzjgbh : result.data.f_zzjgbh,
							f_chdate : result.data.f_chdate
						});
						//setTimeout(function() {
						//}, 1000)
					} else {
						swal("修改失败!", result.msg, "error");
					}
				},
				error : function(result) {
					swal("修改失败!", result.msg, "error");
				}
			});
		}
	}
})(jQuery, window, document);
</script>
