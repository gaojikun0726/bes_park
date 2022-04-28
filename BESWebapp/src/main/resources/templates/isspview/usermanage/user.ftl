

<!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择组织机构>>>
		</span>
	</div>
	<div id="tree_euser" class="Information_area"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;用户信息列表>>>
			</span>
			<!-- 增加按钮 -->
			<a   href="javascript:void(-1);" onclick="usermanage_user.user_show_addmodal()" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
			</a>
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="euserkeywords" name="euserkeywords" placeholder="登录账号、用户姓名、公司名称">
				  <button id="queryuserBtn"onclick="usermanage_user.search_euser()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="euserTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->

<!---添加组织机构信息开始----->
<div class="modal fade" id="modal-form-addeuser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 注册用户</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addEuser" name="add" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录账号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="user_add_f_yhbh" name="user_add_f_yhbh" placeholder="请输入登录账号"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户姓名<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="user_add_f_name" name="user_add_f_name" placeholder="请输入用户姓名" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登陆密码<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="password" data-toggle="password" id="user_add_f_pass" name="user_add_f_pass" placeholder="请输入密码,3-20位数字英文字符" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">确认密码<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="password" data-toggle="password" id="user_add_f_pass_check" name="user_add_f_pass_check" placeholder="请输入密码,3-20位数字英文字符"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">组织机构编码<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="user_add_f_zzjgbh" name="user_add_f_zzjgbh" readonly="readonly" placeholder="请输入组织机构编号" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">组织机构名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="user_add_f_company" readonly="readonly" name="user_add_f_company" placeholder="请输入组织机构名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号</label>
                        <div class="col-sm-8">
                        	<input type="text" id="user_add_f_phone" name="user_add_f_phone" placeholder="请输入11位手机号" class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱</label>
                        <div class="col-sm-8">
                        	<input type="text" id="user_add_f_email" name="user_add_f_email" placeholder="请输入邮箱" class="form-control">
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

<!----编辑用户--->
<div class="modal fade" id="editUserFormTable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑用户信息</h4>
            </div>
            <div class="modal-body">
            	<form id="edituserForm" name="edituserForm" class="form-horizontal">
            	 <div class="form-group">
        <label class="col-sm-3 control-label">登录账号<span class="text-danger">*</span></label>
        <div class="col-sm-8">
            <input type="text" id="user_edit_f_yhbh" readonly="readonly" name="user_edit_f_yhbh"   required class="form-control">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">用户姓名<span class="text-danger">*</span></label>
        <div class="col-sm-8">
        	<input type="text" id="user_edit_f_name" name="user_edit_f_name"  class="form-control">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">公司名称<span class="text-danger">*</span></label>
        <div class="col-sm-8">
        	<input type="hidden" id="user_edit_f_zzjgbh"  name="user_edit_f_zzjgbh" class="form-control">
        	<input type="text" id="user_edit_f_company" readonly="readonly" name="user_edit_f_company"  class="form-control">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label">手机号</label>
        <div class="col-sm-8">
        	<input type="text" id="user_edit_f_phone" name="user_edit_f_phone" class="form-control">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">邮箱</label>
        <div class="col-sm-8">
        	<input type="text" id="user_edit_f_email" name="user_edit_f_email" class="form-control">
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




<!---添加组织机构信息结束----->

 <script type="text/javascript">
 ;
 var usermanage_user =  (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var user_idyhbh = cell.getRow().getData().f_yhbh;
		return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ user_idyhbh + " data-toggle='modal' data-target='#editUserFormTable'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + user_idyhbh + "><i class='fa fa-trash'></i>  删除</button>"
				+"<button class='btn btn-white btn-sm reset' data-id=" + user_idyhbh + "><i class='fa fa-cog'></i>  重置密码</button></div>"
	};

	//创建并设置table属性
	$("#euserTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		selectable:1,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,align:"center",headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"登录账号", field:"f_yhbh", sorter:"string",align:"left",editor:false,headerSort:false}, //never hide this column
		{title:"用户姓名", field:"f_name",sorter:"string",align:"left",editor:false,headerSort:false}, //hide this column first
		//{title:"公司编码", field:"f_zzjgbh", width:120,sorter:"string",editor:false,headerSort:false},
		{title:"组织机构名称", field:"f_company",sorter:"string",align:"left",editor:false,headerSort:false},
		{title:"手机号", field:"f_phone", sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"邮箱", field:"f_email", sorter:"string",align:"left",editor:false,headerSort:false},
		{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:250,tooltip:false,tooltipsHeader:false,align:"left",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	var choiseNode = $('#tree_euser').treeview('findNodes', [ _curRow.getData().f_zzjgbh, 'id']);
        	$('#tree_euser').treeview('searchByNode', choiseNode);//搜索设置高亮
    	},
	});

	$(window).resize(function(){
		$("#euserTable").tabulator("redraw");
	});


	//Clear table on "Empty the table" button click
	$("#clearZzjg").click(function(){
	    $("#euserTable").tabulator("clearData");
	});

	$(function () {
	    $.ajax({
	        type: "post",
	        url: _ctx + "/view/user/zzjg_tree",
	        dataType: "json",
	        beforeSend: function () {
				showLoad();
			},
	        success: function (result) {

	        	if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
	             	if(result.list.length >0){//如果包含判断是否长度大于0
		            $('#tree_euser').treeview({
		                data: result.list,         // 数据源
		                highlightSelected: true,    //是否高亮选中
		                levels : 4,
		                enableLinks : true,//必须在节点属性给出href属性
		                color: "#4a4747",
		               onNodeSelected: function (event, nodeData) {
		               $('#tree_euser').treeview('clearSearch');//清除搜索选中高亮
		               		_zzjgbh = nodeData.id;
		               		_zzjgJs = nodeData.js;

                           //获得选中节点的所有子节点
                           var array = getChildrenNode(nodeData);
                           array.push(nodeData.id);

		                    $.ajax({
		                	    url: _ctx + "/view/user/zzjg_userNew",
		                	    // contentType: "application/json; charset=utf-8",
		                	    type: "post",
		                	    data: {
		                	    	zzjgbh:JSON.stringify(array)
		                	    },
		                	    beforeSend: function () {
		            				showLoad();
		            			},
								success: function(result) {
									if(result.hasOwnProperty("list")){
							            $("#euserTable").tabulator("setData", result.list);
										}else{
							            $("#euserTable").tabulator("setData", []);
										}
					            },
					            complete: function () {
									hiddenLoad();
								},
					            error: function(result) {
					          	    swal( "查询失败","", "error");
					            }
	                	   });
		                }
		            });
		            $("#tree_euser").treeview('collapseAll');
		             var firstNode = $("#tree_euser").treeview('findNodes',[result.list[0].id,'id']);
		            $("#tree_euser").treeview('expandNode',firstNode);
		        	$("#tree_euser").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
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
	});

	//获得选中节点的所有子节点
     function getChildrenNode(nodeData){
         var array = [];
         var children = nodeData.nodes;
         if(children){
             children.forEach( function(item){
                 array.push(item.id);
                 array = array.concat(getChildrenNode(item));
             } );
         }
         return array;
     }

	//触发搜索的回车时间
$("#euserkeywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  usermanage_user.search_euser();//触发该事件
					    }
					  })
					});
	//添加表单验证
    var euserValidator = $("#addEuser").validate({
    	rules: {
    		user_add_f_yhbh: {
                required: true,
                rangelength: [1, 20],
            },

            user_add_f_name: {
                required: true,
                isNormal:true,
                rangelength: [2, 20],
            },
            user_add_f_pass: {
                required: true,
                rangelength: [1, 20],
            },
            user_add_f_pass_check: {
                required: true,
                rangelength: [1, 20],
                equalTo: "#user_add_f_pass"
            } ,
            user_add_f_phone: {
          		isPhone:true,
			rangelength:[11,11]
            },
            user_add_f_email: {
               isEmail: true
            }
        },
        messages: {
        	user_add_f_yhbh: {
              required: "请输登录账户",
                   rangelength: jQuery.validator.format("登录账户应为1-20位的英文字母、数字字符"),
                   remote: jQuery.validator.format("{0} is already in use")
           },
           user_add_f_name: {
               required: "请填写您的真实姓名",
               minlength: jQuery.validator.format("Enter at least {0} characters")
           },
           user_add_f_pass: {
               	required: "请输密码",
                   rangelength: jQuery.validator.format("密码应为1-20位的英文字母、数字字符")
           },
           user_add_f_pass_check: {
               	required: "请确认输密码",
                   rangelength: jQuery.validator.format("密码应为1-20位的英文字母、数字字符"),

                   equalTo: "两次密码输入不一致"
           } ,
           user_add_f_phone: {
               		rangelength : "必须输入11位手机号码，请重试！",
               		isPhone: "请输入正确的手机号码"
            },
            user_add_f_email: {
               	isEmail: "请输入正确的邮箱"

           }
       },
	     submitHandler: function (form) {
	         addform_Euser(form);
	     }
 	});

    //新增保存
	function addform_Euser(form) {
	       $.ajax({
		      url: _ctx + "/view/user/user_add",
		      type: "post",
		      contentType: "application/json; charset=utf-8",
		      data:JSON.stringify({
		 			f_yhbh:$("#user_add_f_yhbh").val(),
		 			f_name:$("#user_add_f_name").val(),
		 			f_pass:$("#user_add_f_pass").val(),
		 			f_zzjgbh:$("#user_add_f_zzjgbh").val(),
		 			f_company:$("#user_add_f_company").val(),
		 			f_phone:$("#user_add_f_phone").val(),
		 			f_email:$("#user_add_f_email").val()
		 	}),
		      beforeSend: function () {
  				showLoad();
  			},
			  success: function(result) {
		         if (result.status == '1') {
		            $('#modal-form-addeuser').modal('hide');//关闭编辑窗口
		        	 swal({
			 	        	title : result.msg,// 展示的标题
			 	   			text : "",// 内容
			 	   			type: "success",
			 	   			showCloseButton : false, // 展示关闭按钮
			 	   			allowOutsideClick : false,
			 	   			showConfirmButton : false,
			 	   			timer: 1000
			 	   		});
			            //在表格中添加数据
			            $('#euserTable').tabulator("addRow", {
			            	f_yhbh:result.data.f_yhbh,
			            	f_name:result.data.f_name,
			            	f_zzjgbh:result.data.f_zzjgbh,
			            	f_company:result.data.f_company,
			            	f_phone:result.data.f_phone,
			            	f_email:result.data.f_email,
			            	f_crdate:result.data.f_crdate,
			            	f_chdate:result.data.f_chdate
			            	});
		         } else{
		           swal(result.msg, "", "error");
		         }
	       },
	       complete: function () {
				hiddenLoad();
			},
	       error: function(result) {
	       	 swal( result.msg,"", "error");
	       }
	     });
 	}
 	//居中显示（添加）
 	$('#modal-form-addeuser').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
		//*************************************
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#modal-form-addeuser .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
 	//居中显示（编辑）
 	$('#editUserFormTable').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#editUserFormTable .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
	//关闭模态框清空表单值
    $("#modal-form-addeuser").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        euserValidator.resetForm();
    });

    //删除数据
    $(document).on('click','#euserTable button.delete', function () {
        var id=$(this).data("id");
	    swal({
	            title: "您确定要删除吗?",
	            text: "信息删除后将不可恢复!",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#1ab394",
	            confirmButtonText: "确定",
	            closeOnConfirm: false
	        }, function () {
            	setTimeout(function(){
	            	$.ajax({
			          url: _ctx + "/view/user/user_del",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({
			        			f_yhbh:id
			    	  }),
			    	  beforeSend: function () {
          				showLoad();
          			},
		          	  success: function(data) {
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
		              		//在表格中删除该条数据
		              		$("#euserTable").tabulator("deleteRow", _curRow);
		              		var getuserTable = $("#euserTable").tabulator("getData");
		              		$("#euserTable").tabulator("setData", getuserTable);
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
     //重置密码
    $(document).on('click','#euserTable button.reset', function () {
        var id=$(this).data("id");
	    swal({
	            title: "您确定要重置密码吗?",
	            text: "密码重置后将不可恢复!",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#1ab394",
	            confirmButtonText: "确定",
	            closeOnConfirm: false
	        }, function () {
            	setTimeout(function(){
	            	$.ajax({
			          url: _ctx + "/view/user/user_updatepwd",
			          type: "post",
			          data:({
			        			f_yhbh:id,
			        			f_pass:'123456'
			    	  }),
			    	  beforeSend: function () {
          				showLoad();
          			},
		          	  success: function(data) {
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
    $("#editUserFormTable").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        euserValidatoredit.resetForm();
    });
  //编辑用户信息表单验证
  var euserValidatoredit = $("#edituserForm").validate({
	 	rules: {
	 		user_edit_f_name: {
                required: true,
                isNormal:true,
                rangelength: [2, 20],
            },
            user_edit_f_phone: {
          		isPhone:true,
			rangelength:[11,11]
            },
            user_edit_f_email: {
               isEmail: true
            }
        },
        messages: {
        	user_edit_f_name: {
               required: "请填写您的真实姓名",
               minlength: jQuery.validator.format("Enter at least {0} characters")
           },
           user_edit_f_phone: {
               		rangelength : "必须输入11位手机号码，请重试！",
               		isPhone: "请输入正确的手机号码"
            },
            user_edit_f_email: {
               	isEmail: "请输入正确的邮箱"

           }
       },
    submitHandler: function(form) {
      edituserForm(form);
    }
  });

  function edituserForm(form) {
    $.ajax({
      url: _ctx + "/view/user/user_update",
      type: "post",
      data: ({
      		f_yhbh: $("#user_edit_f_yhbh").val(),
	        f_name: $("#user_edit_f_name").val(),
	        f_company: $("#user_edit_f_company").val(),
	        f_zzjgbh: $("#user_edit_f_zzjgbh").val(),
	        f_phone: $("#user_edit_f_phone").val(),
	        f_email: $("#user_edit_f_email").val()
      }),
      beforeSend: function () {
			showLoad();
		},
      success: function(result) {
			if (result.status == '1') {
              $('#editUserFormTable').modal('hide');//关闭编辑窗口
              swal({
	 	        	title : result.msg,// 展示的标题
	 	   			text : "",// 内容
	 	   			type: "success",
	 	   			showCloseButton : false, // 展示关闭按钮
	 	   			allowOutsideClick : false,
	 	   			showConfirmButton : false,
	 	   			timer: 1000
	 	   		});
	         $('#euserTable').tabulator("updateRow",_curRow, {
			        	 f_yhbh:$("#user_edit_f_yhbh").val(),
			        	 f_name:$("#user_edit_f_name").val(),
			        	 f_zzjgbh:$("#user_edit_f_zzjgbh").val(),
			        	 f_company:$("#user_edit_f_company").val(),
			        	 f_phone: $("#user_edit_f_phone").val(),
			 	        f_email: $("#user_edit_f_email").val(),
			        	 f_chdate:result.data.chdate
			        	 });
            } else{
              swal("修改失败!", result.msg, "error");
            }
          },
          complete: function () {
				hiddenLoad();
			},
          error: function(result) {
          	 swal("修改失败!", result.msg, "error");
          }
    });
  }

  //验证在模态框出现前加载
  $("#editUserFormTable").on('show.bs.modal', function(event) {
    var id = _curRow.getData().f_yhbh;
  //模态框拖动********************
	$(this).draggable({
		handle:".modal-header"
	});
	$(this).css("overflow","hidden");
	//*************************************
    $.ajax({
	        url: _ctx + "/view/user/loadeditobj",
	        type: "post",
            contentType: "application/json; charset=utf-8",
	        data:JSON.stringify({
	 			f_yhbh:id
	 		}),
	 		beforeSend: function () {
 				showLoad();
 			},
	       success: function(result) {
	         $("#user_edit_f_yhbh").val(result.f_yhbh);
	         $("#user_edit_f_name").val(result.f_name);
	         $("#user_edit_f_company").val(result.f_company);
	         $("#user_edit_f_zzjgbh").val(result.f_zzjgbh);
	         $("#user_edit_f_phone").val(result.f_phone);
	         $("#user_edit_f_email").val(result.f_email);
	         },
		    complete: function () {
				hiddenLoad();
			},
    });
  });
  return {
		//搜索
		 search_euser: function(){
			var euserkeywords = $("#euserkeywords").val();
			$("#euserTable").tabulator("setData", _ctx+'/view/user/user_bykey?euserkeywords='+euserkeywords);
		},
		//验证增加模态框是否弹出
		 user_show_addmodal: function() {
	       var node = $('#tree_euser').treeview('getSelected');
		   if (node.length == 0) {//凡是节点，note.length都为1；无选择节点，为0；
				swal({
		            title: "请选择节点",
		            text: "经检测，您要未选择组织机构节点!",
		            type: "warning",
		            showCancelButton: false,
		            confirmButtonColor: "#1ab394",
		            confirmButtonText: "关闭",
		            closeOnConfirm: false
		        });
					}else{
					$('#modal-form-addeuser').modal('show');
					$("#user_add_f_zzjgbh").val(node[0].id);
					$("#user_add_f_company").val(node[0].text);
					}
			    }
		  }
 })(jQuery, window, document);
 </script>
