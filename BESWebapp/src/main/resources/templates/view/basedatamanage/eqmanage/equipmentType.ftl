<!-----内容区域---->
<!-- <div class="ibox-content m-b-sm border-bottom" style="width:100%;height:7%"> 
	<div class="input-group"  style="height:40px;width:100%">
        <div style="width: 50%;float: left;position: relative;padding-right: 15px;padding-left: 0px;">
            <a id="Emtadd" class="btn btn-primary" data-toggle="modal"  href="#modal-form-addequipmenttype">
                	增加 <i class="fa fa-plus"></i>
            </a>        
            <a id="importEmt" class="btn btn-primary" data-toggle="modal"  href="#modal-form-Emtadd">
                	导入 <i class="fa fa-mail-reply"></i>
            </a>
            <a id="exportEmt" class="btn btn-primary" data-toggle="modal"  href="#modal-form-Emtadd">
                	导出 <i class="fa fa-mail-forward"></i>
            </a>
        </div>
        <div class="zc_search find">
        	<div class="zc_search_form">
            <input id="equipmenttypeKeywords" name="equipmenttypeKeywords" value="" placeholder="设备型号、设备名称"> 
				<button id="queryEquipmentTypeBtn"></button>
            </div>
       	</div>       
	</div>
</div> -->

<!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择设备类型>>>
		</span>
	</div>
	<div id="tree_Sblx" class="Information_area"></div>
</div>
<!-- 信息表格模块 -->
	<div class="information_right">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;设备型号列表>>>
			</span>
			<!-- 增加按钮 -->
			<a id="Emtadd" href="#modal-form-addequipmenttype"data-toggle="modal" onclick="basedatamanage_eqmanage_equipmentType.role_show_addmodal()" class="btn btn-primary toLeft"> 
			<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加 
			</a> 
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="equipmenttypeKeywords" name="equipmenttypeKeywords" placeholder="设备型号、设备名称">
				  <button id="queryEquipmentTypeBtn"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<!---分页列表----->	        
	    <div class="ibox" id="equipmentType_ibox"style="height: 85%;" >
	         	<#include "/view/basedatamanage/eqmanage/equipmentType_page.ftl"/>
	    </div>
	</div>
<!-- 信息表格模块end -->

<!---添加设备型号信息开始----->
<div class="modal fade" id="modal-form-addequipmenttype" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加设备型号信息</h4>
            </div>
            
            <div class="modal-body">
                <form role="form" id="addEquipmenttype" name="addEquipmenttype" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备类型<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_type" name="f_type" placeholder="请输入1-12位中文字符"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="f_sbmc" name="f_sbmc" placeholder="请输入1-12位中文字符" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备类型编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="f_sblxbh" name="f_sblxbh" placeholder="" class="form-control" readonly = true>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">品牌id<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="f_brandid" name="f_brandid" placeholder="" class="form-control" readonly = true>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="f_remark" name="f_remark" placeholder="" class="form-control">
                        </div>
                    </div>
                   <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
            
        </div>
    </div>
</div>
<!---添加设备型号信息结束----->

<!----编辑设备型号信息--->
<div class="modal fade" id="editEmtForm1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑设备型号信息</h4>
            </div>
            <div class="modal-body">
            	<form id="editEmtForm" name="editEmtForm" class="form-horizontal">
            	    <div class="form-group">
        				<label class="col-sm-4 control-label">设备型号</label>
        				<div class="col-sm-8">
            				<input type="text" id="edit_f_type" readonly="readonly" name="edit_f_type" value=""  required class="form-control">
        				</div>
    				</div>
    				<div class="form-group">
        				<label class="col-sm-4 control-label">设备名称</label>
        				<div class="col-sm-8">
        					<input type="text" id="edit_f_sbmc" name="edit_f_sbmc" value="" class="form-control">
        				</div>
    				</div>    
    				<div class="form-group">
        				<label class="col-sm-4 control-label">设备类型编号</label>
        				<div class="col-sm-8">
        					<input type="text" id="edit_f_sblxbh" name="edit_f_sblxbh" value="" class="form-control" readonly = true>
        				</div>
    				</div>
    				<div class="form-group">
        				<label class="col-sm-4 control-label">品牌id</label>
        				<div class="col-sm-8">
        					<input type="text" id="edit_f_brandid" name="edit_f_brandid" value="" class="form-control" readonly = true>
        				</div>
    				</div>
    				<div class="form-group">
        				<label class="col-sm-4 control-label">备注</label>
        				<div class="col-sm-8">
        					<input type="text" id="edit_f_remark" name="edit_f_remark" value="" class="form-control">
        				</div>
    				</div>   
    				<div class="form-group m-t-sm" >
        				<div class="col-sm-6 col-sm-push-3 display_flex">
            			<button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
            			<button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
        				</div>
   					 </div>
            	</form>
            </div>
            
        </div>
    </div>
</div>
<!----编辑用户结束--->


<script type="text/javascript">
;
var basedatamanage_eqmanage_equipmentType = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	$(window).resize(function() {
		$("#emtTable").tabulator("redraw");
	});
	$(function(){
		zzjg_tree();
	})
	//生成设备类型树
	function zzjg_tree(){
	    $.ajax({
	        type: "post",
	        url:"${ctx}/view/basedatamanage/beseqmanage/sblx_tree",
	        dataType: "json",
	        beforeSend: function () { 
	        	showLoad();	             
	        },
	        success: function (result) {
	            //初始加载根节点
	            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
	            	if(result.list.length >0){//如果包含判断是否长度大于0
	            $('#tree_Sblx').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	               onNodeSelected: function (event, nodeData) {
	               $('#tree_Sblx').treeview('clearSearch');//清除搜索选中高亮
	               		_treeId = nodeData.id;
					   $('#f_sblxbh').val(nodeData.id);
	               		basedatamanage_eqmanage_equipmentType.reLoadEquipmentType({
	            			"treeId" :_treeId
	            		});
	                }
	            });
			            var firstNode = $("#tree_Sblx").treeview('findNodes',[result.list[0].id,'id']);
			        	$("#tree_Sblx").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
	            	}
	            }
	        },
	        complete: function () {
	        	hiddenLoad();
	        },
	        error: function (nodeData) {
	            swal( nodeData.msg,"", "error");
	        }
	    });
	}
	//添加设备信息表单验证
	var equipmentValidator =$("#addEquipmenttype").validate({
			rules : {
			f_type : {
			required : true,
			rangelength : [ 1, 20 ],
			},

			f_sbmc : {
			required : true,
			rangelength : [ 1, 20 ],
			}
			},
			messages : {
			f_type : {
			required : "请输入设备型号",
			rangelength : jQuery.validator.format("密码应为3-20位的英文字母、数字字符"),
			remote : jQuery.validator.format("{0} is already in use")
			},
			f_sbmc : {
			required : "请输入设备名称",
			minlength : jQuery.validator.format("Enter at least {0} characters")
			},
			},
			submitHandler : function(form) {
			addform(form);
			}
			});

	function addform(form) {
		$("#f_brandid").val("00");//因目前设备品牌暂未维护，设备品牌设为00固定（暂时）
		$.ajax({
			url : _ctx + "/view/basedatamanage/beseqmanage/equipmentType_add",
			type : "post",
			data : $(form).serialize(),
			//async: false,
			beforeSend: function () { 
				showLoad();	             
			},
			success : function(data) {
				//暂时使用重新加载的方式,解决新增或删除后分页不更新的问题
				basedatamanage_eqmanage_equipmentType.reLoadEquipmentType();
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
						$('#modal-form-addequipmenttype').modal('hide');//关闭编辑窗口
						$("#emtTable").tabulator("addRow",data.data);
					} else {
							swal("添加失败!", data.msg, "error");
							}
			},
			complete: function () {
				hiddenLoad();
			},
			error : function(data) {
				swal("添加失败!", data.msg, "error");
			}
		});
	}

	 	//居中显示（添加）
 	$('#modal-form-addequipmenttype').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-addequipmenttype .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	
	 	//居中显示（编辑）
 	$('#editEmtForm1').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editEmtForm1 .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	
	//关闭模态框清空表单值
	$("#modal-form-addequipmenttype").on('hidden.bs.modal',function(event) {
			$(this).find("input").val("");
			equipmentValidator.resetForm();
			});

	//触发搜索的回车事件
 	$("#equipmenttypeKeywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
					  basedatamanage_eqmanage_equipmentType.reLoadEquipmentType({
			"keywords" : $("#equipmenttypeKeywords").val()
			});//触发该事件
					    } 
					  })
					});
					
	//查询
	$("#queryEquipmentTypeBtn").click(function() {
	basedatamanage_eqmanage_equipmentType.reLoadEquipmentType({
			"keywords" : $("#equipmenttypeKeywords").val()
	});
	});



	//编辑设备型号信息
	//表单验证
	$("#editEmtForm").validate({
	//debug: true,
	submitHandler : function(form) {
			editEmtForm(form);
	}
	});

	function editEmtForm(form) {
		$.ajax({
			url : _ctx + "/view/basedatamanage/beseqmanage/equipmentType_update",
			type : "post",
			data : ({
				f_type: $("#edit_f_type").val(),
				f_sbmc: $("#edit_f_sbmc").val(),
				f_sblxbh: $("#edit_f_sblxbh").val(),
				f_brandid: $("#edit_f_brandid").val(),
				f_remark: $("#edit_f_remark").val(),
     		}),
		beforeSend: function () { 
			showLoad();	             
		},
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
				$("#emtTable").tabulator("updateRow", basedatamanage_eqmanage_equipmentType_page.get_curRow(),data.data);
				$('#editEmtForm1').modal('hide');//关闭编辑窗口
			} else {
				swal("修改失败!", data.msg, "error");
			}
			},
		complete: function () {
			hiddenLoad();
		},
		error : function(data) {
				swal("修改失败!", data.msg, "error");
			}
			});
	}
	
	//验证码在模态框出现前加载
	$("#editEmtForm1").on('show.bs.modal',function(event) {
			var button = $(event.relatedTarget);
			var f_type = button.data("id");
	   	$.ajax({
	 	       url: _ctx + "/view/basedatamanage/beseqmanage/equipmentType_edit",
	 	       type: "post", 
	 	       data:{     
	 	 			"f_type":f_type
	 	 		},
	 	 	beforeSend: function () { 
	 	 		showLoad();	             
	 	 	},
	 	success: function(result) {
	 	         $("#edit_f_type").val(result.data.f_type);
	 	         $("#edit_f_sbmc").val(result.data.f_sbmc);
	 	         $("#edit_f_sblxbh").val(result.data.f_sblxbh);
	 	         $("#edit_f_brandid").val(result.data.f_brandid);
	 	         $("#edit_f_remark").val(result.data.f_remark);
	 	         },
	 	 	complete: function () {
	 	 		hiddenLoad();
	 	 	},
	 	 error : function(data) {
				swal("查找失败!", data.msg, "error");
			}
	    }); 
	});

	//删除数据
	$(document).on('click','#emtTable button.delete',function() {
			var row = $(this).parents("tr")[0];
			var f_type = $(this).data("id");
			swal(
			{
			title : "您确定要删除吗?",
			text : "设备型号信息删除后将不可恢复!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "确定",
			closeOnConfirm : false
			},
		function() {
			$.ajax({
				url : _ctx + "/view/basedatamanage/beseqmanage/equipmentType_del",
				type : "post",
				data : {"f_type" : f_type
				},
				beforeSend: function () { 
		 	 		showLoad();	             
		 	 	},
				success : function(data) {
					//暂时使用重新加载的方式,解决新增或删除后分页不更新的问题
					basedatamanage_eqmanage_equipmentType.reLoadEquipmentType();
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
					$("#emtTable").tabulator("deleteRow", basedatamanage_eqmanage_equipmentType_page.get_curRow());    
              		var getemtTable = $("#emtTable").tabulator("getData");
              		$("#emtTable").tabulator("setData", getemtTable);
				} else {
					swal("删除失败!",data.msg,"error");
				}
				},
				complete: function () {
		 	 		hiddenLoad();
		 	 	},
				error : function(data) {
					swal("删除失败!",data.msg,"error");
				}
				});
				});
		});
	return {
		// 分页查询
		reLoadEquipmentType : function (datas) {
			$.ajax({
				url : _ctx + '/view/basedatamanage/beseqmanage/equipmentType_page',
				type : "post",
				data : datas,
 		        beforeSend: function () { 
 		        	showLoad();	             
 		        },
				success : function(data) {
					$('#equipmentType_ibox').html(data);
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
			basedatamanage_eqmanage_equipmentType.reLoadEquipmentType();
		}
	}
})(jQuery, window, document);	
	basedatamanage_eqmanage_equipmentType.pageInit();
</script>