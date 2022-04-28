<!-----内容区域---->

<!-- <div class="ibox-content m-b-sm border-bottom" style="width: 100%; height: 6.5%"> 
   <div class="input-group"  style="height: 100%; width: 100%;">
        <div class="divStyle_size"  >
           <a id="addgroup" href="javascript:void(-1);" onclick="usermanage_usergroup.group_show_addmodal();" class="btn btn-primary" >
                	增加 <i class="fa fa-plus"></i>
            </a>
    	</div>
       	<div class="zc_search find">
			<div class="zc_search_form">
			  <input type="text"  id="ugroupInfo" name="ugroupInfo" placeholder="用户组编号、名称">
			  <button onclick="usermanage_usergroup.group_searchUserGroup()"></button>
			</div>
		</div>
   </div>
 </div> -->

<!-- <div class="leftarea"  style="margin-top:-10px;height:93%">
	<div id="tree_ugroup">
	</div>
</div> -->

<!-- <div style=" float: right;height:93%;width:85%;position:relative;padding: 5px 5px 0px 5px;margin-top:-10px;">
   <div style="height:calc(100%);overflow:auto;">
		<div id="ugroupTable">	</div>
   </div>
</div> -->


<!-- 用户组树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择用户组>>>
		</span>
	</div>
	<div id="tree_ugroup" class="Information_area"></div>
</div>
<!-- 用户组树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;用户组信息列表>>>
			</span>
			<!-- 增加按钮 -->
			<a   href="javascript:void(-1);" onclick="usermanage_usergroup.group_show_addmodal()" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加 
			</a> 
			<!-- 搜索框 -->
			<div class="zc_search find">
				<!-- <div class="zc_search_form"> -->
				  <input type="text" class="find-style"  id="ugroupInfo" name="ugroupInfo" placeholder="用户组编号、名称">
				  <button onclick="usermanage_usergroup.group_searchUserGroup()"><i class="fa fa-search" aria-hidden="true"></i></button>
				<!-- </div> -->
			</div>
		</div>
		<div id="ugroupTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->
<!---添加用户组信息开始-----> 
<div class="modal fade" id="modal-form-addusergroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加用户组信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addUgroup" name="addUgroup" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户组名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="f_zmc" name="f_zmc" placeholder="请输入用户组名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                          <label class="col-sm-3 control-label">备注</label>
                          <div class="col-sm-8">
                          	<input type="text" id="f_remark" name="f_remark" class="form-control">
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

<!----编辑组织结构--->
<div class="modal fade" id="editUserGroupForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑用户组信息</h4>
            </div>
            <div class="modal-body">
            	<form id="group_edit_UserGroupForm" name="group_edit_UserGroupForm" class="form-horizontal">
	            	<div class="form-group">
						<label class="col-sm-4 control-label" for="group_edit_f_zbh">用户组编号  <span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="group_edit_f_zbh" name="group_edit_f_zbh"  required class="form-control" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="group_edit_f_zmc">用户组名称<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="group_edit_f_zmc" name="group_edit_f_zmc"  required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="group_edit_f_groupremark">备注 <span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="group_edit_f_groupremark" name="group_edit_f_groupremark" required class="form-control">
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
<!----包含用户--->
<div class="modal fade" id="includeUserGroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content"  style="width:785px;" >
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">包含用户</h4>
            </div>
            <div class="modal-body" style="height:600px;">
            	<div style="float:left;width:56.8%"><button class="btn btn-md" style="cursor:default;padding: 4px 12px !important;"><span>未选择</span></button></div>
            	<div style="float:left;width:40%"><button class="btn btn-md" style="cursor:default;padding: 4px 12px !important;"><span>已选择</span></button></div>
            	<!----未选择table+搜索）--->
            	<div class="notIncludeCss" >
			       	 <!-- 搜索框 -->
				     <div class="information-model">
						<div class="zc_search find" style="margin-right: 62%;">
							<input type="text" class="find-style"  id="notincludeUserKeywords" name="notincludeUserKeywords" placeholder="用户编号、名称">
							<button id="queryNotIncludeBtn"onclick="usermanage_usergroup.group_searchNotIncludeUser()"><i class="fa fa-search" aria-hidden="true"></i></button>
						</div>
					</div>
	            	<div id="usergroup_noInclude" style="margin-top:10px;overflow: auto;" ></div>
            	</div>
            	<!----未选择用户结束--->
            	
            	
            	<!----操作开始--->
            	<div style="width:100px;height:400px;float:left">
            		<div id="rightMove" style="margin-top:200px;margin-left:8px;"><button type="button" id="user_group_right" onclick="usermanage_usergroup.group_rightMove()" class="btn btn-primary">>></button></div>
            		<div id="leftMove" style="margin-top:20px;margin-left:8px;"><button type="button" id="user_group_left"  onclick="usermanage_usergroup.group_leftMove()" class="btn btn-primary"><<</button></div>
            	</div>
            	<!----操作结束--->
            	
            	<!----包含用户开始--->
            	<div class="includeCss">
			       	<!-- 搜索框 -->
			       	<div class="information-model">
						<div class="zc_search find" style="margin-right: 10%;">
							<input type="text" class="find-style"  id="includeUserKeywords" name="includeUserKeywords" placeholder="用户编号、名称">
							<button id="queryIncludeBtn"onclick="usermanage_usergroup.group_searchIncludeUser()"><i class="fa fa-search" aria-hidden="true"></i></button>
						</div>
					</div>
            		<div id="usergroup_includeuser" style="overflow: auto;margin-top:10px;" ></div>
            	</div>
            	<!----包含用户结束--->
		        <div class="modal-footer">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        </div>
            </div>
        </div>
    </div>
</div>
<!---添加用户组信息结束----->
 
 <script type="text/javascript">
 ;
 var usermanage_usergroup = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
 	var _ugroupZbh = "00";//用户组编码
	var _ugroupJs = "0";//用户组对应的级数
	var _fzbh = "00";
	var _curRow = null;//对应行
	var _includecurRow = null;//“已选择”table对应行
	var _notincludecurRow = null;//“未选择”table对应行
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var ugroupid = cell.getRow().getData().f_zbh;
		return "<div class='btn-group '>"
				+"<button class='btn btn-white btn-sm include' data-id=" + ugroupid + " data-toggle='modal' data-target='#includeUserGroup'><i class='fa fa-user'></i>  包含用户</button>"
				+"<button class='btn btn-white btn-sm edit' data-id="+ ugroupid + " data-toggle='modal' data-target='#editUserGroupForm'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + ugroupid + "><i class='fa fa-trash'></i>  删除</button></div>"
	};

	//创建并设置table属性
	$("#ugroupTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:80,formatter:"rownum",frozen:false,align:"center",sorter:"string",headerSort:false,tooltip:false,tooltipsHeader:false}, //frozen column
		{title:"用户组编号", field:"f_zbh", sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
		{title:"用户组名称", field:"f_zmc",sorter:"string",align:"center",editor:false,headerSort:false}, //hide this column first 
		{title:"备注", field:"f_remark", sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:250,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	var id = _curRow.getData().f_zbh;
        	var choiseNode = $('#tree_ugroup').treeview('findNodes', [ _curRow.getData().f_zbh, 'id']);
        	if(choiseNode.length>1){
    	    	for (var i = 0; i < choiseNode.length; i++) {
    				if(choiseNode[i].id == id){
    					$('#tree_ugroup').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
    				}
    			}
    	    }else{
        	$('#tree_ugroup').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
    	    }
    	},
	});

	$(window).resize(function(){
		$("#ugroupTable").tabulator("redraw");
	});
	//Clear table on "Empty the table" button click
	$("#clearyhz").click(function(){
	    $("#ugroupTable").tabulator("clearData");
	});
	//加载树
	$(function () {
	    $.ajax({
	        type: "post",
	        url: _ctx + "/view/usergroup/ugroup_tree",
	        dataType: "json",
	        beforeSend: function () { 
 				showLoad();	             
 			},
	        success: function (result) {
        	 //初始加载根节点
            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
            	if(result.list.length >0){//如果包含判断是否长度大于0
	            $('#tree_ugroup').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 2,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	                onNodeSelected: function (event, nodeData) {
	               $('#tree_ugroup').treeview('clearSearch');//清除搜索选中高亮
	               		_ugroupZbh = nodeData.id;
	               		_ugroupJs = nodeData.level;

	                    $.ajax({
	                   
	                	    url: _ctx + "/view/usergroup/group_chlildtreegrid",
	                	    contentType: "application/json; charset=utf-8",
	                	    type: "get",
	                	    beforeSend: function () { 
	             				showLoad();	             
	             			},
	                	    data: {
	                	    	f_zbh:nodeData.id,
	                	    },
	                	    
							success: function(result) {
									if(result.hasOwnProperty("list")){
						            	$("#ugroupTable").tabulator("setData", result.list);
									}else{
						            	$("#ugroupTable").tabulator("setData", []);
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
	            var firstNode = $("#tree_ugroup").treeview('findNodes',[result.list[0].id,'id']);
	        	$("#tree_ugroup").treeview("selectNode", firstNode);//将第一个节点设置为选中状态 
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
	//触发搜索的回车时间
	$("#ugroupInfo").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  usermanage_usergroup.group_searchUserGroup();//触发该事件
					    } 
					  })
					});
//添加用户组表单验证
    var groupValidator = $("#addUgroup").validate({
	     rules: {
	         f_zmc: {
	             required: true,
	             rangelength: [1, 40]
	         }
	     },
	     messages: {
	         
	         f_zmc: {
	             required: "请填写用户组名称",
	             minlength: jQuery.validator.format("Enter at least {0} characters")
	         }
	     },
	     submitHandler: function (nodeData) {
	         addformUserGroup(nodeData);
	     }
 	});
	
    //新增保存
	function addformUserGroup(nodeData) {
	     $.ajax({
	       url: _ctx + "/view/usergroup/add_usergroup",
	       type: "post",
           contentType: "application/json; charset=utf-8",
	       data:JSON.stringify({     
	 			f_zmc:$("#f_zmc").val(),
				f_remark:$("#f_remark").val(),
				f_pzbh:_ugroupZbh,
				f_js:_ugroupJs
	 		}),
	 		beforeSend: function () { 
 				showLoad();	             
 			},
	       success: function(result) {
	         if (result.status == '1') {
	           $('#modal-form-addusergroup').modal('hide');//关闭编辑窗口
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
		            $('#ugroupTable').tabulator("addRow", { f_zbh:result.data.f_zbh, f_zmc:result.data.f_zmc, f_remark:result.data.f_remark, f_crdate:result.data.f_crdate, f_chdate:result.data.f_chdate});
		            //在树上添加
		            var pNode = $("#tree_ugroup").treeview("getSelected");
		            $("#tree_ugroup").treeview("addNode", [{ id:result.data.f_zbh, text:result.data.f_zmc, pid:pNode[0].id}, pNode]);  
	         } else{
	           swal( result.msg, "", "error");
	         }
	       },
	       complete: function () {
				hiddenLoad();
			},
	       error: function(result) {
	       	 swal( result.msg,"", "error");
	       }
	     });
	     return false;
 	}
 	//居中显示（添加）
 	$('#modal-form-addusergroup').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
		//*************************************
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-addusergroup .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
 	//居中显示（编辑）
 	$('#editUserGroupForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editUserGroupForm .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
 	//居中显示（包含用户）
 	$('#includeUserGroup').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#includeUserGroup .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	//关闭模态框清空表单值
    $("#modal-form-addusergroup").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        groupValidator.resetForm();  
    });
    
    //删除数据
    $(document).on('click','#ugroupTable button.delete', function () {
        var id=$(this).data("id");
        var dNode = $('#tree_ugroup').treeview('findNodes', [ id, 'id']);
	    if(dNode.length>1){
	    
	    for (var i = 0; i < dNode.length; i++) {
			if(dNode[i].id == id){
				var booljuge = dNode[i].hasOwnProperty('nodes');
				if(booljuge == true){
					swal({ 
			            title: "请您先删除用户组下的子组！",
			            text: "经检测，您要删除的用户组包含子组!",
			            type: "warning",
			            showCancelButton: false,
			            confirmButtonColor: "#1ab394",
			            confirmButtonText: "关闭",
			            closeOnConfirm: false
			        });
				}else{
					deleteGroup(id,dNode[i]);//删除
				}
			}
		}
	    }else{
	    	deleteGroup(id,dNode[0]);
        }
    });
    
      function deleteGroup(id,node){
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
			          url: _ctx + "/view/usergroup/user_group_del",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({     
			        			f_zbh:id
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

		              		$("#ugroupTable").tabulator("deleteRow", _curRow);
		              		var getGroupTable = $("#ugroupTable").tabulator("getData");
		              		$("#ugroupTable").tabulator("setData", getGroupTable);
				            //在树上删除该条数据
				            //var dNode = $('#tree_ugroup').treeview('findNodes', [ id, 'id']);
				             $("#tree_ugroup").treeview("removeNode", node, { silent: true } );    
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
      }
   //表单验证
  $("#group_edit_UserGroupForm").validate({
    submitHandler: function(form) {
      editUserGpForm(form);
    }
  });
  
  //编辑用户组
  function editUserGpForm(form) {
    $.ajax({
      url: _ctx + "/view/usergroup/usergroup_edit",
      type: "post",
      data: ({
            f_zbh: $("#group_edit_f_zbh").val(),
	        f_zmc: $("#group_edit_f_zmc").val(),
	        f_remark: $("#group_edit_f_groupremark").val()
      }),
      beforeSend: function () { 
			showLoad();	             
		},
      success: function(result) {
			if (result.status == '1') {
              $('#editUserGroupForm').modal('hide');//关闭编辑窗口
              swal({
		        	title : result.msg,// 展示的标题
		   			text : "",// 内容
		   			type: "success",
		   			showCloseButton : false, // 展示关闭按钮
		   			allowOutsideClick : false,
		   			showConfirmButton : false,
		   			timer: 1000
		   		});
	         $('#ugroupTable').tabulator("updateRow",_curRow, { f_zmc:result.data.f_zmc, f_remark:result.data.f_remark, f_chdate:result.data.f_chdate});
	         //在树上修改   
				   var parentNode = $('#tree_ugroup').treeview('findNodes', [ _curRow.getData().f_zbh, 'id'])
				   if(parentNode.length>1){
		    	    	for (var i = 0; i < parentNode.length; i++) {
		    				if(parentNode[i].id == _curRow.getData().f_zbh){
		    					parentNode = parentNode[i];
		    				}
		    			}
		    	    }else{
		    	    	parentNode = parentNode[0];
		    	    }
	            	var newNode={
						text: _curRow.getData().f_zmc
					};
					$('#tree_ugroup').treeview('updateNode', [ parentNode, newNode]);
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
  
  //验证在模态框出现前加载编辑组织机构
  $("#editUserGroupForm").on('show.bs.modal', function(event) {
    var button = $(event.relatedTarget);
    var id = button.data("id");//获取用户组编号
  //模态框拖动********************
	$(this).draggable({
		handle:".modal-header"
	});
	$(this).css("overflow","hidden");
	//*************************************
    $.ajax({
	       url: _ctx + "/view/usergroup/loadeditobj",
	        type: "post", 
           contentType: "application/json; charset=utf-8",
	       data:JSON.stringify({     
	 			f_zbh:id
	 		}),
	 		beforeSend: function () { 
 				showLoad();	             
 			},
	       success: function(result) {
	         $("#group_edit_f_zbh").val(result.f_zbh);
	         $("#group_edit_f_zmc").val(result.f_zmc);
	         $("#group_edit_f_groupremark").val(result.f_remark);
	         },
	       complete: function () {
					hiddenLoad();
			},
    });  
  });
  
	   //验证码在模态框出现前加载包含用户(可拖动)
	  $("#includeUserGroup").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	  	group_loadNoIncludeUser(id);
		group_loadIncludeUser(id);
		_fzbh = id;
	});
	//添加用户至用户组
    $(document).on('click','#usergroup_noInclude button.insertUser', function () {
        var f_yhbh = $(this).data("id");
        $("#user_group_right").attr('disabled',false);	
		$("#user_group_left").attr('disabled',false);
	            	$.ajax({
			          url: _ctx + "/view/usergroup/usergroup_insertuser",
			          type: "post",
			          data:({     
			        			f_zbh:_fzbh,
			        			f_yhbh:f_yhbh
			    	  }),
			    	  beforeSend: function () { 
			 				showLoad();	             
			 			},
		          	  success: function(data) {
		              	if (data.status == '1') {
		              		//swal(data.msg, "", "success");
		              		//在未选择表格中删除该条数据
		              		$("#usergroup_noInclude").tabulator("deleteRow", _notincludecurRow);
				            //在已选择表格中添加该条数据
				             $('#usergroup_includeuser').tabulator("addRow", { f_yhbh:data.data.f_yhbh, f_name:data.data.f_name});
				           		//未包含用户表格：
					            var noinclude_group_data = $("#usergroup_noInclude").tabulator("getData");
					             $("#usergroup_noInclude").tabulator("setData", noinclude_group_data);
					            //已包含用户表格：
					            var include_group_data = $("#usergroup_includeuser").tabulator("getData");
					             $("#usergroup_includeuser").tabulator("setData", include_group_data);
					             if(noinclude_group_data.length == 0){					            	 
					            	 $("#user_group_right").attr('disabled',true);
					            	 $("#user_group_left").attr('disabled',false);
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
	//移除用户组中包含用户
    $(document).on('click','#usergroup_includeuser button.deleteUser', function () {
        			var f_yhbh = $(this).data("id");
        			$("#user_post_right").attr('disabled',false);	
        			$("#user_post_left").attr('disabled',false);
	            	$.ajax({
			          url: _ctx + "/view/usergroup/usergroup_deleteuser",
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
		              		$("#usergroup_includeuser").tabulator("deleteRow", _includecurRow);
				            //在未选择表格中添加该条数据
				             $('#usergroup_noInclude').tabulator("addRow", { f_yhbh:data.data.f_yhbh, f_name:data.data.f_name});
				            
				            //未包含用户表格：
				            var noinclude_group_data = $("#usergroup_noInclude").tabulator("getData");
				             $("#usergroup_noInclude").tabulator("setData", noinclude_group_data);
				            //已包含用户表格：
				            var include_group_data = $("#usergroup_includeuser").tabulator("getData");
				             $("#usergroup_includeuser").tabulator("setData", include_group_data);
				             if(include_group_data.length == 0){					            	 
				            	 $("#user_group_right").attr('disabled',false);
				            	 $("#user_group_left").attr('disabled',true);
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
		return "<button class='btn btn-white btn-sm insertUser' data-id=" + idyhbh + "><i class='fa fa-arrow-circle-right'></i></button>"
	};
	//设置“已选择”格式
	var optIconInclude = function(cell, formatterParams){
		var idyhbh = cell.getRow().getData().f_yhbh;
		return "<button class='btn btn-white btn-sm deleteUser' data-id=" + idyhbh + "><i class='fa fa-trash'></i></button>"
	};
	//创建并设置“未选择”table属性
	$("#usergroup_noInclude").tabulator({
		height:"93.3%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		movableColumns:true,
		columns:[
		{title:"序号",width:50,formatter:"rownum",frozen:false,align:"center",sorter:"number",headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"用户编号", field:"f_yhbh", width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"用户名称", field:"f_name",width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconNoIncludeFunc,headerSort:false},
		],
		rowClick:function(e, not_row){
        	_notincludecurRow = not_row;
    	},
	});
	
	//创建并设置“已选择”table属性
	$("#usergroup_includeuser").tabulator({
		height:"93.3%",
		layout:"fitColumns",
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		//movableColumns:true,
		columns:[
		{title:"序号",width:50,formatter:"rownum",frozen:false,align:"center",sorter:"number",headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"用户编号", field:"f_yhbh", width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"用户名称", field:"f_name",width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconInclude,headerSort:false},
		],
		rowClick:function(e, in_row){
        	_includecurRow = in_row;
    	},
	});
	//加载未选择用户表
	function group_loadNoIncludeUser(id){
	//填充“未选择”数据
	$("#usergroup_noInclude").tabulator("setData", _ctx+'/view/usergroup/loadNoIncludeUser?f_zbh='+id);
	}
	//加载已选择用户表
	function group_loadIncludeUser(id){
	//填充“已选择”数据
	$("#usergroup_includeuser").tabulator("setData", _ctx+'/view/usergroup/loadGroupRlglUser?f_zbh='+id);
	}
	return {
		getZbhNodes:function(fZbh){

			var bh = "";//定义使用单位变量
	 		if(fZbh !="" && fZbh.length>0){
		        var Nodes = $("#tree_ugroup").treeview('findNodes',[fZbh,'id']);
				$.each(Nodes,function(i,item){
					bh= bh + ","+item.id;
				});
			};
			bh = bh.substring(1,bh.length);
			return bh;
	 	},
		//搜索
		group_searchUserGroup:function(){
			var ugroupInfo = $("#ugroupInfo").val();
			$("#ugroupTable").tabulator("setData", _ctx+'/view/usergroup/user_group_treegrid?f_zmc='+ugroupInfo);
		},
		//搜索已包含用户
		group_searchIncludeUser:function(){
			var includeUserKeywords = $("#includeUserKeywords").val();
			$("#usergroup_includeuser").tabulator("setData", _ctx+'/view/usergroup/loadGroupRlglUser?f_zbh='+_fzbh+'&keywords='+includeUserKeywords);
		},
		//搜索未包含用户
		group_searchNotIncludeUser:function(){
			var notincludeUserKeywords = $("#notincludeUserKeywords").val();
			$("#usergroup_noInclude").tabulator("setData", _ctx+'/view/usergroup/loadNoIncludeUser?f_zbh='+_fzbh+'&keywords='+notincludeUserKeywords);
		},
		//全部右移
		group_rightMove:function(){
			var group_tem = $("#usergroup_noInclude").tabulator("getData");
			if(group_tem.length<1){
				$("#user_group_right").attr('disabled',true);	
			}else{
			$.ajax({
				url: _ctx + "/view/usergroup/usergroup_rightmoveuser",
				contentType: "application/json; charset=utf-8",
				type: "post",
				data:JSON.stringify({     
				      f_zbh:_fzbh
				}),
				beforeSend: function () { 
	 				showLoad();	             
	 			},
			    success: function(data) {
			              	$("#usergroup_includeuser").tabulator("setData", _ctx+'/view/usergroup/loadGroupRlglUser?f_zbh='+_fzbh);
							$("#usergroup_noInclude").tabulator("setData", _ctx+'/view/usergroup/loadNoIncludeUser?f_zbh='+_fzbh);
							$("#user_group_right").attr('disabled',true);
							$("#user_group_left").attr('disabled',false);			
			   			 },
	 			complete: function () {
					hiddenLoad();
				},
			 });	
			}
		},
		//全部左移
		group_leftMove:function(){
			var group_tem = $("#usergroup_includeuser").tabulator("getData");
			if(group_tem.length<1){
				$("#user_group_left").attr('disabled',true);	
			}else{
				$.ajax({
					url: _ctx + "/view/usergroup/usergroup_leftmoveuser",
					contentType: "application/json; charset=utf-8",
					type: "post",
					data:JSON.stringify({     
					      f_zbh:_fzbh
					}),
					beforeSend: function () { 
		 				showLoad();	             
		 			},
				    success: function(data) {
			            if (data.status == '1') {
			              	//swal(data.msg, "", "success");
			              	$("#usergroup_includeuser").tabulator("setData", _ctx+'/view/usergroup/loadGroupRlglUser?f_zbh='+_fzbh);
							$("#usergroup_noInclude").tabulator("setData", _ctx+'/view/usergroup/loadNoIncludeUser?f_zbh='+_fzbh);
							$("#user_group_left").attr('disabled',true);          
							$("#user_group_right").attr('disabled',false);     
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
					}
				},
		//验证增加模态框是否弹出
		group_show_addmodal:function() {
		    var node = $('#tree_ugroup').treeview('getSelected');
			if (node.length == 0) {//凡是节点，note.length都为1；无选择节点，为0；
				swal({ 
	           title: "请选择节点",
	           text: "经检测，您要未选择用户组节点!",
	           type: "warning",
	           showCancelButton: false,
	           confirmButtonColor: "#1ab394",
	           confirmButtonText: "关闭",
	           closeOnConfirm: false
	       		});
			}else{
				$('#modal-form-addusergroup').modal('show'); 
		    }
		}
	}
 })(jQuery, window, document);
 </script>