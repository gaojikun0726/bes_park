
<!-- 
作者：gongfanfei
描述：角色自定义
 -->


<!-- 信息表格模块 -->
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;用户角色信息列表>>>
			</span>
			<!-- 增加按钮 -->
			<a   href="javascript:void(-1);" onclick="usermanage_userrole.role_show_addmodal()" class="btn btn-primary toLeft">  <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
			</a>
			<#--打印按钮-->
            <a   href="javascript:void(-1);" onclick="usermanage_userrole.role_print_addmodal()" class="btn btn-primary toLeft">  <i class="fa fa-print"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;打印
            </a>
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="euserrolekeywords" name="euserrolekeywords" placeholder="角色编号、名称">
				  <button onclick="usermanage_userrole.search_euserRole()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="eroleTable" class="Information_area"style="height:92%"></div>
<!-- 信息表格模块end -->

<!---添加角色信息开始-----> 
<div class="modal fade" id="modal-form-adduserrole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 添加角色信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addEsRole" name="addEsRole" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">角色名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="addrole_f_rolemc" name="addrole_f_rolemc" placeholder="请输入角色名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                          <label class="col-sm-3 control-label">备注</label>
                          <div class="col-sm-8">
                          	<input type="text" id="addrole_f_remark" name="addrole_f_remark" class="form-control">
                          </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!----编辑role--->
<div class="modal fade" id="editUserRoleForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑角色信息</h4>
            </div>
            <div class="modal-body">
            	<form id="role_edit_RoleForm" name="role_edit_RoleForm" class="form-horizontal">
            	<div class="form-group">
	<label class="col-sm-4 control-label" for="role_edit_f_zbh">角色编号  <span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="hidden" id="role_edit_f_guid" name="role_edit_f_guid"  required class="form-control" readonly="readonly">
		<input type="text" id="role_edit_f_rolebh" name="role_edit_f_rolebh"  required class="form-control" readonly="readonly">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label" for="role_edit_f_zmc">角色名称<span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="text" id="role_edit_f_rolemc" name="role_edit_f_rolemc"  required class="form-control">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label" for="role_edit_f_roleremark">备注</label>
	<div class="col-sm-8">
		<input type="text" id="role_edit_f_roleremark" name="role_edit_f_roleremark" class="form-control">
	</div>
</div>
<div class="form-group m-t-sm">
	<div class="col-sm-6 col-sm-push-4 display_flex">
		<button class="btn btn-md btn-primary " type="submit">
			<strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong>
		</button>
		<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
	</div>
</div>
            	</form>
            </div>
        </div>
    </div>
</div>

<#--数据权限-->
<div class="modal fade" id="dataPermissionModalUserRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 数据权限</h4>
            </div>

            <div class="modal-body">

                <form id="scope_data_form_user_role" name="scope_data_form_user_role" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="role_edit_f_zbh">权限范围</label>
                        <div class="col-sm-9">
                            <select id="scope_user_role" name="scope_user_role" class="form-control" style="width: 30vh">
                                <option value="">请选择</option>
								<option value="1">全部数据权限</option>
								<option value="2">自定数据权限</option>
								<option value="3">本组织及以下数据权限</option>
								<option value="4">本组织数据权限</option>
								<option value="5">仅本人数据权限</option>
                            </select>
                        </div>
                    </div>

					<div class="form-group">
                        <label class="col-sm-3"></label>
                        <div id="tree_scope_user_role" class="col-sm-9" style="width: 33vh"></div>
					</div>

                    <div class="form-group m-t-sm">
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary " type="submit">
                                <strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong>
                            </button>
                            <button type="button" class="btn btn-white m-l-sm" data-dismiss="modal"><i
                                    class="fa fa-times" aria-hidden="true"></i>&nbsp;取消
                            </button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>

<!----包含用户--->
<div class="modal fade" id="includeUserRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:785px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title"><i class="fa fa-id-card-o" aria-hidden="true"></i>&nbsp;</span><span id="role_name"></span>包含用户</h4>
            </div>
            <div class="modal-body" style="height:600px;">
            	<div style="float:left;width:56.8%"><button class="btn btn-md" style="cursor:default;padding: 4px 12px !important;"><span>未选择</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default;padding: 4px 12px !important;"><span>已选择</span></button></div>
            	<!----未选择table+搜索）--->
           	<div class="notIncludeCss" >
		         <div style="padding:2px 2px 2px 10px;height:6%;">
				    <!--  <div style="float:left;">
				     <input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="notincludeUserKeywords" name="notincludeUserKeywords" value="" placeholder="用户编号、名称"> 
				     </div>
				     <div style="float:left;">
				     <span class="input-group-btn"  style="width: 60px;">
						<button class="btn btn-primary btn-sm m-b-none" id="queryNotIncludeBtn" onclick="usermanage_userrole.searchNotIncludeUser()">
		                	<i class="fa fa-search"></i> 搜索
		                </button>
		            </span>
				     </div> -->
				     <!-- 搜索框 -->
					<div class="zc_search find" style="position: absolute;left: 7%; height: 3vh;width: 13vw;">
						<input type="text" class="find-style" id="notincludeUserKeywords"name="notincludeUserKeywords" placeholder="用户编号、名称">
						<button id="queryNotIncludeBtn" onclick="usermanage_userrole.searchNotIncludeUser()">
							<i class="fa fa-search" aria-hidden="true"></i>
						</button>
					</div>
		       	</div>
            	<div id="userrole_noInclude" style="margin-top:10px;overflow: auto;" >
            	</div>
           	</div>
            	<!----未选择用户结束--->
            	
            	
            	<!----操作开始--->
            	<div style="width:100px;height:400px;float:left">
            	<div id="rightMove" style="margin-top:200px;margin-left:16px;"><button id="user_role_right" type="button" onclick="usermanage_userrole.rightMove()" class="btn btn-primary">>></button></div>
            	<div id="leftMove" style="margin-top:20px;margin-left:16px;"><button id="user_role_left" type="button"  onclick="usermanage_userrole.leftMove()" class="btn btn-primary"><<</button></div>
            	</div>
            	<!----操作结束--->
            	
            	
            	<!----包含用户开始--->
            	<div class="notIncludeCss">
			        <div style="position: relative;padding:2px 2px 2px 10px;height:6%;">
			        	<!-- <div style="float:left;">
					     <input type="text" class="input-sm form-control" style="width: calc(100%);padding: 4px 30px;" id="includeUserKeywords" name="includeUserKeywords" value="" placeholder="用户编号、名称"> 
					     </div>
					     <div style="float:left;">
					     <span class="input-group-btn"  style="width: 60px;">
							<button class="btn btn-primary btn-sm m-b-none" id="queryIncludeBtn" onclick="usermanage_userrole.searchIncludeUser()">
			                	<i class="fa fa-search"></i> 搜索
			                </button>
			            </span>
					     </div> -->
			       		 <!-- 搜索框 -->
						<div class="zc_search find" style="position: absolute;left: 7%; height: 3vh;width: 13vw;">
							<input type="text" class="find-style" id="includeUserKeywords"name="includeUserKeywords" placeholder="用户编号、名称">
							<button id="queryIncludeBtn" onclick="usermanage_userrole.searchIncludeUser()">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
						</div>
			       	</div>
	            	<div id="userrole_includeuser" style="overflow: auto;margin-top:10px;" >
	            	</div>
	            	<div>
	            	</div>
            	</div>
            	<!----包含用户结束--->
        <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;关闭</button>
        </div>
            </div>
        </div>
    </div>
</div>

<style>
</style>




 <script type="text/javascript">
 ;
 var usermanage_userrole = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var _froleguid = "00";
	var _curRow = null;//对应行
	var _includecurRow = null;//“已选择”table对应行
	var _notincludecurRow = null;//“未选择”table对应行
	 var groupTreeData = [];
	 var groups = new Set();
	 var scopeTypeConst = {
         FULL: 1, // 全部数据权限
         CUSTOMIZED: 2, // 自定数据权限
		 INCLUDE_LOCAL_BELOW: 3, // 本组织及以下数据权限
		 LOCAL: 4, // 本组织数据权限
		 SELF: 5 // 仅本人数据权限
	 };

	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var roleGuid = cell.getRow().getData().f_rolebh;
		var rolenameStr = cell.getRow().getData().f_rolemc;
		return "<div class='btn-group '>"
		+"<button class='btn btn-white btn-sm roleInclude' data-rolename="+rolenameStr+"  data-id=" + roleGuid + " data-toggle='modal' data-target='#includeUserRole'><i class='fa fa-user'></i>  包含用户</button>"
		+"<button class='btn btn-white btn-sm edit' data-id="+ roleGuid + " data-toggle='modal' data-target='#dataPermissionModalUserRole'><i class='fa fa-unlock-alt' ></i> 数据权限</button>"
		+"<button class='btn btn-white btn-sm edit' data-id="+ roleGuid + " data-toggle='modal' data-target='#editUserRoleForm'><i class='fa fa-pencil' ></i> 编辑</button>"
		+"<button class='btn btn-white btn-sm delete' data-id=" + roleGuid + "><i class='fa fa-trash'></i>  删除</button></div>"
	};

	$(function ()
    {
        getGroupTree(function (data)
        {
            groupTreeData = data && data.list;
        })
    });

	//创建并设置table属性
	$("#eroleTable").tabulator({
		height:"96%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		//{title:"序号",field:"id",width:50,formatter:"rownum",align:"center",frozen:false,headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"角色编号", field:"f_rolebh", sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"角色名称", field:"f_rolemc",sorter:"string",align:"left",editor:false,headerSort:false},
		{title:"备注", field:"f_remark", sorter:"string",editor:false,align:"left",headerSort:false},
		{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:300,tooltip:false,tooltipsHeader:false,align:"left",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
    	},
	});
	
	//填充数据
	//$("#eroleTable").tabulator("setData", _ctx+'/view/userrole/loadAllrole');
	

	$(window).resize(function(){
		$("#eroleTable").tabulator("redraw");
	});
	
	//触发搜索的回车时间
$("#euserrolekeywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  usermanage_userrole.search_euserRole();//触发该事件
					    } 
					  })
					});

	//添加组织机构表单验证
    var euserValidator = $("#addEsRole").validate({
	     rules: {
	    	 addrole_f_rolemc: {
                required: true,
                rangelength: [1, 20],
                isNormal:true
            },
            addrole_f_remark:{
                 rangelength: [1, 20],
                 isNormal:true
            }
        },
        messages: {
        	addrole_f_remark: {
               required: "请输入备注",
                    rangelength: jQuery.validator.format("密码应为3-20位的英文字母、数字字符"),
                    remote: jQuery.validator.format("{0} is already in use")
            },
            addrole_f_rolemc:{
            	required : "请输入角色名称",
				minlength : jQuery.validator.format("Enter at least {0} characters")
           }
        },
	     submitHandler: function (form) {
	         addform_EsRole(form);
	     }
 	});
    //新增保存
	function addform_EsRole(form) {
	       $.ajax({
		      url: _ctx + "/view/userrole/user_role_add",
		      type: "post",
		      contentType: "application/json; charset=utf-8",
		      data:JSON.stringify({     
		 			f_rolemc:$("#addrole_f_rolemc").val(),
					f_remark:$("#addrole_f_remark").val()
		 	}),
		 	 beforeSend: function () { 
 				showLoad();	             
 			},
			success: function(data) {
	         if (data.status == '1') {
	          /*  swal( data.msg,"", "success"); */
	           swal({
	        	title : data.msg,// 展示的标题
	   			text : "",// 内容
	   			type: "success",
	   			showCloseButton : false, // 展示关闭按钮
	   			allowOutsideClick : false,
	   			showConfirmButton : false,
	   			timer: 1000
	   		});
	            $('#modal-form-adduserrole').modal('hide');//关闭编辑窗口
	            //在表格中添加数据
	            $('#eroleTable').tabulator("addRow", { f_guid:data.data.f_guid,f_rolebh:data.data.f_rolebh, f_rolemc:data.data.f_rolemc, f_remark:data.data.f_remark,f_crdate:data.data.f_crdate, f_chdate:data.data.f_chdate});
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       complete: function () {
				hiddenLoad();
			},
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
	     return false;
 	}
 	//居中显示（添加）
 	$('#modal-form-adduserrole').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
		//*************************************
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-adduserrole .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
 	//居中显示（编辑）
 	$('#editUserRoleForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editUserRoleForm .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
 	//居中显示（数据权限）
 	$('#dataPermissionModalUserRole').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#dataPermissionModalUserRole .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });

        // 赋值数据权限表单
        assignScopeDataTree(_curRow.getData());

        $('#scope_user_role').val(_curRow.getData().scopeType);
	});

     //关闭模态框清空表单值（数据权限）
     $("#dataPermissionModalUserRole").on('hidden.bs.modal', function (event) {

         $('#scope_user_role').val('');
         $('#tree_scope_user_role').empty(); // 清空
     });

	//关闭模态框清空表单值
    $("#modal-form-adduserrole").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        euserValidator.resetForm();  
    });
    //居中显示（包含用户）
 	$('#includeUserRole').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#includeUserRole .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
    //删除数据
    $(document).on('click','#eroleTable button.delete', function () {
        //var id=$(this).data("id");
        var id=_curRow.getData().f_guid;
        
	    swal({ 
	            title: "您确定要删除吗?",
	            text: "信息删除后将不可恢复!",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#1ab394",
	            confirmButtonText: "确定",
	            cancelButtonText: "取消",
	            closeOnConfirm: true,
	            closeOnCancel:true
	        }, function () {
            	setTimeout(function(){
	            	$.ajax({
			          url: _ctx + "/view/userrole/user_role_del",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({     
			        			f_guid:id
			    	  }),
			    	  beforeSend: function () { 
          				showLoad();	             
          			},
		          	  success: function(data) {
		              	if (data.status == '1') {
		              		/* swal(data.msg, "", "success"); */
		              		swal({
					        	title : data.msg,// 展示的标题
					   			text : "",// 内容
					   			type: "success",
					   			showCloseButton : false, // 展示关闭按钮
					   			allowOutsideClick : false,
					   			showConfirmButton : false,
					   			timer: 1000
					   		});
		              		$("#eroleTable").tabulator("deleteRow", _curRow);    
		              		var getroleTable = $("#eroleTable").tabulator("getData");
		              		$("#eroleTable").tabulator("setData", getroleTable);
			            } else{
			                swal(data.msg,"", "error");
			            }
			          },
			          complete: function () {
							hiddenLoad();
						},
			          error: function(data) {
			          	 swal( data.msg,"", "error");
			          }
			        });
                },100
            )
        });
    });
     
 	//关闭模态框清空表单值
    $("#editUserRoleForm").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        euserValidatoredit.resetForm();  
    });
    //编辑角色
   //表单验证
 var euserValidatoredit =  $("#role_edit_RoleForm").validate({
	  
	    rules: {
	    	role_edit_f_rolemc:{
	    		required : true,
				rangelength : [ 1, 20 ],
	    	},
	    	
	    	role_edit_f_roleremark:{
				rangelength : [ 1, 20 ],
	    	}
        },
        messages: {
			role_edit_f_rolemc:{
				required : "请输入角色名称",
				minlength : jQuery.validator.format("Enter at least {0} characters")
			
	    	},
	    	
	    	role_edit_f_roleremark:{
	    		required : "请输入备注",
				minlength : jQuery.validator.format("Enter at least {0} characters")
			
	    	}
        },
    submitHandler: function(form) {
      editUserRoleFormFunc(form);
    }
  });

$("#scope_data_form_user_role").validate({
     submitHandler: function (form)
     {
         editScopeData(form);
     }
  });

 $('#scope_user_role').change(function (obj)
 {

     var value = obj.target.value;

     if (value == scopeTypeConst.CUSTOMIZED)
	 {
         buildTreeGroup();
         assignScopeDataTree(_curRow.getData());
	 }else
	 {
		 $('#tree_scope_user_role').empty();
	 }
 });
	 
 
 function editScopeData(form)
 {
     //获取表单数据
     var formData = form2js(form ,null, null, null, null, true);

     updateScopeData({
         scope: formData.scope_user_role,
         roleId: _curRow.getData().f_rolebh,
         groupIds: Array.from(groups.values())
	 }, function (data)
     {

         if (!data)
         {
             return;
         }

         if (data.status === '1') {

             $('#dataPermissionModalUserRole').modal('hide');//关闭编辑窗口

             _curRow.getData().scopeType = formData.scope_user_role;

             swal({
                 title : data.msg,// 展示的标题
                 text : "",// 内容
                 type: "success",
                 showCloseButton : false, // 展示关闭按钮
                 allowOutsideClick : false,
                 showConfirmButton : false,
                 timer: 1000
             });

         } else{
             swal( data.msg, "", "error");
         }
     });
 }

  function editUserRoleFormFunc(form) {
    $.ajax({
      url: _ctx + "/view/userrole/userrole_edit",
      type: "post",
      data: ({
      		f_guid: $("#role_edit_f_guid").val(),
      		f_rolebh: $("#role_edit_f_rolebh").val(),
	        f_rolemc: $("#role_edit_f_rolemc").val(),
	        f_remark: $("#role_edit_f_roleremark").val(),
      }),
      beforeSend: function () { 
			showLoad();	             
		},
      success: function(data) {
			if (data.status == '1') {
	          $('#editUserRoleForm').modal('hide');//关闭编辑窗口
              swal({
	        	title : data.msg,// 展示的标题
	   			text : "",// 内容
	   			type: "success",
	   			showCloseButton : false, // 展示关闭按钮
	   			allowOutsideClick : false,
	   			showConfirmButton : false,
	   			timer: 1000
	   		});
	         $('#eroleTable').tabulator("updateRow",_curRow, {
	        	 f_rolebh:data.data.f_rolebh, 
	        	 f_rolemc:data.data.f_rolemc, 
	        	 f_remark:data.data.f_remark,
	        	 f_chdate:data.data.f_chdate
	        	 });
            } else{
              swal("修改失败!", data.msg, "error");
            }
          },
          complete: function () {
				hiddenLoad();
			},
          error: function(data) {
          	 swal("修改失败!", data.msg, "error");
          }
    });
  }
  
  //验证在模态框出现前加载
  $("#editUserRoleForm").on('show.bs.modal', function(event) {
    var id = _curRow.getData().f_guid;
  //模态框拖动********************
	$(this).draggable({
		handle:".modal-header"
	});
	$(this).css("overflow","hidden");
	//*************************************
    $.ajax({
	       url: _ctx + "/view/userrole/loadeditobj",
	        type: "post",
           contentType: "application/json; charset=utf-8",
	       data:JSON.stringify({     
	 			f_guid:id
	 		}),
	 		 beforeSend: function () { 
 				showLoad();	             
 			},
	       success: function(result) {
	         $("#role_edit_f_guid").val(result.f_guid);
	         $("#role_edit_f_rolebh").val(result.f_rolebh);
	         $("#role_edit_f_rolemc").val(result.f_rolemc);
	         $("#role_edit_f_roleremark").val(result.f_remark);
	         },
	         complete: function () {
					hiddenLoad();
				},
    });  
  });
    //验证码在模态框出现前加载包含用户(可拖动)
	  $("#includeUserRole").on('show.bs.modal', function(event) {
		$("#role_name").empty();
		var button = $(event.relatedTarget);
	    var rolename = button.data("rolename");//获取角色名称
		$("#role_name").append(rolename+":");
	    var id = button.data("id");//获取角色编号
	    $(this).draggable({
	        handle: ".modal-header"// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden");// 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	  	role_loadNoIncludeUser(id);
		role_loadIncludeUser(id);
		_froleguid = id;
		
	});
	//移除角色中包含用户
    $(document).on('click','#userrole_includeuser button.deleteUser', function () {
        			var f_yhbh = $(this).data("id");
        			$("#user_role_right").attr('disabled',false);	
					$("#user_role_left").attr('disabled',false);
	            	$.ajax({
			          url: _ctx + "/view/userrole/userrole_deleteuser",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({     
			        			f_yhbh:f_yhbh
			    	  }),
			    	  beforeSend: function () { 
          				showLoad();	             
          			},
		          	  success: function(data) {
		              	if (data.status == '1') {
		              		//swal(data.msg, "", "success");
		              		//在已选择表格中删除该条数据
		              		$("#userrole_includeuser").tabulator("deleteRow", _includecurRow);
				            //在未选择表格中添加该条数据
				            $('#userrole_noInclude').tabulator("addRow", { f_yhbh:data.data.f_yhbh, f_name:data.data.f_name});
				            //未包含用户表格：
						    var noinclude_role_data = $("#userrole_noInclude").tabulator("getData");
						    $("#userrole_noInclude").tabulator("setData", noinclude_role_data);
						    //已包含用户表格：
						    var include_role_data = $("#userrole_includeuser").tabulator("getData");
						    $("#userrole_includeuser").tabulator("setData", include_role_data);
						    if(include_role_data.length == 0){					            	 
					            	$("#user_role_right").attr('disabled',false);
					            	$("#user_role_left").attr('disabled',true);
					        }
			            } else{
			                swal(data.msg,"", "error");
			            }
			          },
			          complete: function () {
							hiddenLoad();
						},
			          error: function(data) {
			          	 swal( data.msg,"", "error");
			          }
			        });
   				 });
    
	//设置“未选择”格式
	var optIconNoIncludeFunc = function(cell, formatterParams){
		var idyhbh = cell.getRow().getData().f_yhbh;
		return "<button onclick=\"usermanage_userrole.insertUser('"+idyhbh+"')\" class='btn btn-white btn-sm insertUser' data-id=" + idyhbh + "><i class='fa fa-arrow-circle-right'></i></button>"
	};
	//设置“已选择”格式
	var optIconInclude = function(cell, formatterParams){
		var idyhbh = cell.getRow().getData().f_yhbh;
		return "<button class='btn btn-white btn-sm deleteUser' data-id=" + idyhbh + "><i class='fa fa-trash'></i></button>"
	};
	//创建并设置“未选择”table属性
	$("#userrole_noInclude").tabulator({
		height:"93.3%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		movableColumns:true,
		columns:[
		{title:"序号",width:50,formatter:"rownum",align:"center",frozen:false,sorter:"number",headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"用户编号", field:"f_yhbh", width:80,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"用户名称", field:"f_name",width:80,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconNoIncludeFunc,headerSort:false},
		],
		rowClick:function(e, row){
        	_notincludecurRow = row;
    	},
	});
	
	//创建并设置“已选择”table属性
	$("#userrole_includeuser").tabulator({
		height:"93.3%",
		layout:"fitColumns",
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		//movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",align:"center",frozen:false,sorter:"string",headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"用户编号", field:"f_yhbh", width:80,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"用户名称", field:"f_name",width:80,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconInclude,headerSort:false},
		],
		rowClick:function(e, in_row){
        	_includecurRow = in_row;
    	},
	});
	//加载未选择用户表f_roleguid
	function role_loadNoIncludeUser(id){
	//填充“未选择”数据
	$("#userrole_noInclude").tabulator("setData", _ctx+'/view/userrole/loadNoIncludeUser?f_roleguid='+id);
	}
	//加载已选择用户表
	function role_loadIncludeUser(id){
	//填充“已选择”数据
	$("#userrole_includeuser").tabulator("setData", _ctx+'/view/userrole/loadRoleRlglUser?f_roleguid='+id);
	}

     /**
	  * 更新数据权限
      */
     function updateScopeData(data, callback)
     {
         var scope = data.scope;
         var roleId = data.roleId;
         var groupIds = data.groupIds;

         if (!scope)
		 {
             scope = null;
		 }

         if (!roleId)
		 {
             return;
		 }

		 if (!Array.isArray(groupIds))
		 {
             groupIds = [];
		 }

         if (!data)
		 {
			 return;
		 }

         $.ajax({
             url: _ctx + "/view/scopeData/update",
             type: "post",
             contentType: "application/json; charset=utf-8",
             data:JSON.stringify({
                 scope: scope,
                 roleId: roleId,
                 groupIds: groupIds
             }),
             success: function(data) {

                 if (typeof callback === 'function')
				 {
                     callback(data);
				 }
             },
             error: function(data) {
                 swal( data.msg,"", "error");
             }
         });
     }

     /**
	  * 查询数据权限
      */
     function queryScopeData(roleId, callback)
     {
         if (!roleId)
		 {
			 return;
		 }

         $.ajax({
             url: _ctx + "/view/scopeData/query",
             type: "get",
             contentType: "application/json; charset=utf-8",
             data:{
                 roleId: roleId
             },
             success: function(data) {

                 if (typeof callback === 'function')
				 {
                     callback(data);
				 }
             },
             error: function(data) {
                 swal( data.msg,"", "error");
             }
         });
     }

     /**
	  * 赋值数据权限
	  */
     function assignScopeDataTree(obj)
     {
         if (!obj)
		 {
		     return;
		 }
         
         var roleId = obj.f_rolebh;
         var scopeType = obj.scopeType;

         if (scopeType)
		 {
			 if (scopeTypeConst.CUSTOMIZED != scopeType)
			 {
				 return;
			 }

             var tree = buildTreeGroup();

             var treeData = tree.treeview('getNodes');

             var treeDataMap = {};

             treeData.forEach(item => {
                 treeDataMap[item.id] = item;
             });

             queryScopeData(roleId, function (result)
             {
                 var data = result && result.data;

                 groups.clear();

                 for (var i = 0; i < data.length; i++)
                 {
                     var groupId = data[i].groupId;

                     groups.add(groupId);

                     tree.treeview('checkNode', [ treeDataMap[groupId], { silent: $('#chk-check-silent').is(':checked') }]);
                 }

             });

		 }

     }


     /**
	  *  获取组织树信息
	  */
     function getGroupTree(callback)
     {
         if (typeof callback !== 'function')
		 {
		     return;
		 }

         $.ajax({
             type: "post",
             url: _ctx + "/view/datacenter/zzjgTree",
             dataType: "json",
             success: function (result) {
                 callback(result);
             },
             complete: function () {
                 hiddenLoad();
             },
             error: function (nodeData) {
                 swal( nodeData.msg,"", "error");
             }
         });
		 
     }

     /**
	  * 构建组织树
      */
     function buildTreeGroup()
     {
         return $('#tree_scope_user_role').treeview({
             data: groupTreeData,         // 数据源
             highlightSelected: false,    //是否高亮选中
             // levels : 3,
             enableLinks : true,//必须在节点属性给出href属性
             wrapNodeText: true,
             color: "#4a4747",
             showBorder: false, // 是否显示边框
             showCheckbox:true,
             hierarchicalCheck: false,//级联勾选
             propagateCheckEvent:true,
             onNodeChecked:function(event,nodeData){//选中方法
                 groups.add(nodeData.id);
             },
             onNodeUnchecked : function(event,nodeData){//取消方法
                 groups.delete(nodeData.id);
             },
         });
		 
     }

	return {
		//搜索
		 search_euserRole:function(){
			var euserrolekeywords = $("#euserrolekeywords").val();
			$("#eroleTable").tabulator("setData", _ctx+'/view/userrole/user_role_search?keywords='+euserrolekeywords);
		},
		//搜索已包含用户
		searchIncludeUser :function(){
			var includeUserKeywords = $("#includeUserKeywords").val();
			$("#userrole_includeuser").tabulator("setData", _ctx+'/view/userrole/loadRoleRlglUser?f_roleguid='+_froleguid+'&keywords='+includeUserKeywords);
		},
		//搜索未包含用户
		searchNotIncludeUser :function(){
			var notincludeUserKeywords = $("#notincludeUserKeywords").val();
			$("#userrole_noInclude").tabulator("setData", _ctx+'/view/userrole/loadNoIncludeUser?f_roleguid='+_froleguid+'&keywords='+notincludeUserKeywords);
		},
		//全部右移
		rightMove :function(){
			var role_tem = $("#userrole_noInclude").tabulator("getData");
			if(role_tem.length<1){
				$("#user_role_right").attr('disabled',true);	
			}else{
			$.ajax({
				url: _ctx + "/view/userrole/userrole_rightmoveuser",
				contentType: "application/json; charset=utf-8",
				type: "post",
				data:JSON.stringify({     
				      f_roleguid:_froleguid
				}),
				 beforeSend: function () { 
	 				showLoad();	             
	 			},
			    success: function(data) {
			              	$("#userrole_includeuser").tabulator("setData", _ctx+'/view/userrole/loadRoleRlglUser?f_roleguid='+_froleguid);
							$("#userrole_noInclude").tabulator("setData", _ctx+'/view/userrole/loadNoIncludeUser?f_roleguid='+_froleguid);
							$("#user_role_right").attr('disabled',true);
							$("#user_role_left").attr('disabled',false);
							},
	 			complete: function () {
					hiddenLoad();
				},
			});	
		}
	},
		//全部左移
		leftMove :function(){
			var role_tem = $("#userrole_includeuser").tabulator("getData");
			if(role_tem.length<1){
				$("#user_role_left").attr('disabled',true);	
			}else{
				$.ajax({
					url: _ctx + "/view/userrole/userrole_leftmoveuser",
					contentType: "application/json; charset=utf-8",
					type: "post",
					data:JSON.stringify({     
					      f_roleguid:_froleguid
					}),
					 beforeSend: function () { 
	     				showLoad();	             
	     			},
				    success: function(data) {
				              	$("#userrole_includeuser").tabulator("setData", _ctx+'/view/userrole/loadRoleRlglUser?f_roleguid='+_froleguid);
								$("#userrole_noInclude").tabulator("setData", _ctx+'/view/userrole/loadNoIncludeUser?f_roleguid='+_froleguid);
								$("#user_role_left").attr('disabled',true);          
								$("#user_role_right").attr('disabled',false);
				    },
				    complete: function () {
						hiddenLoad();
					},
					 error: function(data) {
					       swal( data.msg,"", "error");
					}
					});
				}	
			},
		//验证增加模态框是否弹出
		role_show_addmodal :function() {
			$('#modal-form-adduserrole').modal('show');
	    },
		//打印按钮
        role_print_addmodal :function() {
            $("#eroleTable").printThis({

			});
        },
	  	//添加用户至角色
		insertUser :function(yhbh){
			var f_yhbh = yhbh;
	        $("#user_role_right").attr('disabled',false);	
			$("#user_role_left").attr('disabled',false);
		            	$.ajax({
				          url: _ctx + "/view/userrole/userrole_insertuser",
				          contentType: "application/json; charset=utf-8",
				          type: "post",
				          data:JSON.stringify({      
				         			f_roleguid : _froleguid,
				        			f_yhbh : f_yhbh
				    	  }),
				    	  beforeSend: function () { 
	          				showLoad();	             
	          			},
			          	  success: function(data) {
			              	if (data.status == '1') {
			              		//swal(data.msg, "", "success");
			              		//在未选择表格中删除该条数据
			              		$("#userrole_noInclude").tabulator("deleteRow", _notincludecurRow);
					            //在已选择表格中添加该条数据
					             $('#userrole_includeuser').tabulator("addRow", { f_yhbh:data.data.f_yhbh, f_name:data.data.f_name});
					           //未包含用户表格：
							       var noinclude_role_data = $("#userrole_noInclude").tabulator("getData");
							       $("#userrole_noInclude").tabulator("setData", noinclude_role_data);
							       //已包含用户表格：
							       var include_role_data = $("#userrole_includeuser").tabulator("getData");
							       $("#userrole_includeuser").tabulator("setData", include_role_data);
							       if(noinclude_role_data.length == 0){					            	 
						            	 $("#user_role_right").attr('disabled',true);
						            	 $("#user_role_left").attr('disabled',false);
						             }
				            } else{
				                swal(data.msg,"", "error");
				            }
				          },
				          complete: function () {
								hiddenLoad();
							},
				          error: function(data) {
				          	 swal( data.msg,"", "error");
				          }
				        });
			},
			//初始加载函数
			pageInit:function(){
				$.ajax({
			        type: "get",
			        url: _ctx+'/view/userrole/loadAllrole',
			        dataType: "json",
			        beforeSend: function () { 
          				showLoad();	             
          			},
			        success: function (result) {
			        	 $("#eroleTable").tabulator("setData", result.list);
			        },
			        complete: function () {
	     				hiddenLoad();
	     			},
			        error: function (nodeData) {
			            swal("查询失败","", "error");
			        } 
	    		});
			},
	}
	
 })(jQuery, window, document);
 usermanage_userrole.pageInit();
 </script>