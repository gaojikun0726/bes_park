<!--
作者：gongfanfei
描述：岗位自定义
-->

<!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择组织机构>>>
		</span>
	</div>
	<div id="tree_epost" class="Information_area"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-user" aria-hidden="true"></i>&nbsp;岗位信息列表>>>
			</span>
			<!-- 增加按钮 -->
			<a   href="javascript:void(-1);" onclick="usermanage_userpost.post_show_addmodal()" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
			</a>
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="euserpostkeywords" name="euserpostkeywords" placeholder="岗位编号、名称">
				  <button onclick="usermanage_userpost.search_epost()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="epostTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->

<!---添加岗位信息开始----->
   <div class="modal fade" id="modal-form-adduserpost" tabindex="-1" post="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加岗位信息</h4>
            </div>
            <div class="modal-body">
                <form post="form" id="addEsPost" name="addEsPost" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">岗位名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="post_add_f_gwmc" name="post_add_f_gwmc" placeholder="请输入岗位名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">组织机构<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" readonly="readonly" id="post_add_f_zzjgmc" name="post_add_f_zzjgmc" placeholder="请输入组织机构名称"  required class="form-control">
                            <input type="hidden"  id="post_add_f_zzjgbh" name="post_add_f_zzjgbh" placeholder="请输入组织机构"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                          <label class="col-sm-3 control-label">备注</label>
                          <div class="col-sm-8">
                          	<input type="text" id="post_add_f_remark" name="post_add_f_remark" class="form-control">
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
<!----编辑post--->
<div class="modal fade" id="editUserPostForm" tabindex="-1" post="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑岗位信息</h4>
            </div>
            <div class="modal-body">
            	<form id="post_edit_PostForm" name="post_edit_PostForm" class="form-horizontal">
	            	<div class="form-group">
						<label class="col-sm-4 control-label" for="post_edit_f_zbh">岗位编号  <span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="hidden" id="post_edit_f_guid" name="post_edit_f_guid"  required class="form-control" readonly="readonly">
							<input type="text" id="post_edit_f_gwbh" name="post_edit_f_gwbh"  required class="form-control" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="post_edit_f_zmc">岗位名称<span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="post_edit_f_gwmc" name="post_edit_f_gwmc"  required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="post_edit_f_guremark">备注 <span class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="post_edit_f_guremark" name="post_edit_f_guremark" required class="form-control">
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
<div class="modal fade" id="includeUserPost" tabindex="-1" post="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
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
			         <div style="padding:2px 2px 2px 10px;height:6%;">
			  			<!-- 搜索框 -->
						<div class="zc_search find" style="position: absolute;left: 7%; height: 3vh;background: #000000;width: 13vw;">
							<input type="text" class="find-style" id="notincludeUserKeywords"name="notincludeUserKeywords" placeholder="用户编号、名称">
							<button id="queryNotIncludeBtn" onclick="usermanage_userpost.post_searchNotIncludeUser()">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
						</div>
			       	</div>
            		<div id="userpost_noInclude" style="margin-top:10px;overflow: auto;" ></div>
            	</div>
            	<!----未选择用户结束--->

            	<!----操作开始--->
            	<div style="width:100px;height:400px;float:left">
	            	<div id="rightMove" style="margin-top:200px;margin-left:8px;"><button id="user_post_right" type="button" onclick="usermanage_userpost.post_rightMove()" class="btn btn-primary">>></button></div>
	            	<div id="leftMove" style="margin-top:20px;margin-left:8px;"><button id="user_post_left" type="button"  onclick="usermanage_userpost.post_leftMove()" class="btn btn-primary"><<</button></div>
            	</div>
            	<!----操作结束--->

            	<!----包含用户开始--->
            	<div class="includeCss">
			        <div style="position: relative;padding:2px 2px 2px 10px;height:6%;">
			       		<!-- 搜索框 -->
						<div class="zc_search find" style="position: absolute;left: 7%; height: 3vh;background: #000000;width: 13vw;">
							<input type="text" class="find-style" id="includeUserKeywords" name="includeUserKeywords" placeholder="用户编号、名称">
							<button id="queryIncludeBtn" onclick="usermanage_userpost.post_searchIncludeUser()">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
						</div>
			       	</div>
	            	<div id="userpost_includeuser" style="overflow: auto;margin-top:10px;" ></div>
            	</div>
            	<!----包含用户结束--->
		        <div class="modal-footer">
		        	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        </div>
            </div>
        </div>
    </div>
</div>

<style>
#searchclear {
		position: absolute;
		right: 17%;
		top: 0;
		bottom: 0;
		height: 14px;
		margin: auto;
		font-size: 14px;
		cursor: pointer;
		color: #ccc;
		}
</style>


<!---添加岗位信息结束----->

 <script type="text/javascript">
 ;
 var usermanage_userpost = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var zzjgbh='';
	var _fpostguid = "00";
	var _includecurRow = null;//“已选择”table对应行
	var _notincludecurRow = null;//“未选择”table对应行
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var postGuid = cell.getRow().getData().f_guid;
		return "<div class='btn-group '>"
		+"<button class='btn btn-white btn-sm postInclude' data-id=" + postGuid + " data-toggle='modal' data-target='#includeUserPost'><i class='fa fa-user'></i>  包含用户</button>"
		+"<button class='btn btn-white btn-sm edit' data-id="+ postGuid + " data-toggle='modal' data-target='#editUserPostForm'><i class='fa fa-pencil' ></i> 编辑</button>"
		+"<button class='btn btn-white btn-sm delete' data-id=" + postGuid + "><i class='fa fa-trash'></i>  删除</button></div>"
	};

	//创建并设置table属性
	$("#epostTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,align:"center",headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"GUID", field:"f_guid", sorter:"string",visible: false,editor:false,align:"center",headerSort:false},
		{title:"岗位编号", field:"f_gwbh", sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"岗位名称", field:"f_gwmc",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"组织机构", field:"f_zzjgmc",sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"备注", field:"f_remark", sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:250,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	var choiseNode = $('#tree_epost').treeview('findNodes', [ _curRow.getData().f_zzjgbh, 'id']);
        	$('#tree_epost').treeview('searchByNode', choiseNode);//搜索设置高亮
    	},
	});

	//填充数据
	//$("#epostTable").tabulator("setData", _ctx+'/view/userpost/loadAllPost');


	$(window).resize(function(){
		$("#epostTable").tabulator("redraw");
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
	            $('#tree_epost').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 4,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	               onNodeSelected: function (event, nodeData) {
	               $('#tree_epost').treeview('clearSearch');//清除搜索选中高亮
	               		_zzjgbh = nodeData.id;
	               		_zzjgJs = nodeData.level;

	               		//获得选中节点的所有子节点
	               		var array = getChildrenNode(nodeData);
	               		array.push(nodeData.id);
	               		// var ssdw = usermanage_userpost.getSydwNodes(nodeData.id);
	                    $.ajax({
	                	    url: _ctx + "/view/userpost/zzjg_post",
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
					            	$("#epostTable").tabulator("setData", result.list);
								}else{
					            	$("#epostTable").tabulator("setData", []);
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
	        	$("#tree_epost").treeview('collapseAll');
	            var firstNode = $("#tree_epost").treeview('findNodes',[result.list[0].id,'id']);
	            $("#tree_epost").treeview('expandNode',firstNode);
	        	$("#tree_epost").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
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
$("#euserpostkeywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  usermanage_userpost.search_epost();//触发该事件
					    }
					  })
					});

	//添加岗位表单验证
    var euserValidator = $("#addEsPost").validate({
	     rules: {
	    	 post_add_f_gwmc: {
                required: true,
                rangelength: [2, 20],
            }
        },
        messages: {
        	post_add_f_gwmc: {
               required: "请输岗位名称",
                    rangelength: jQuery.validator.format("名称应为2-20位的字符"),
                    remote: jQuery.validator.format("{0} is already in use")
            }
        },
	     submitHandler: function (form) {
	         addform_EsPost(form);
	     }
 	});


    //新增保存
	function addform_EsPost(form) {
	       $.ajax({
		      url: _ctx + "/view/userpost/user_post_add",
		      type: "post",
		      contentType: "application/json; charset=utf-8",
		      data:JSON.stringify({
		 			f_zzjgbh:$("#post_add_f_zzjgbh").val(),
		 			f_gwmc:$("#post_add_f_gwmc").val(),
					f_remark:$("#post_add_f_remark").val()
		 	}),
		 	beforeSend: function () {
 				showLoad();
 			},
			success: function(result) {

		         if (result.status == '1') {
		            $('#modal-form-adduserpost').modal('hide');//关闭编辑窗口
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
			            $('#epostTable').tabulator("addRow", {
			            	f_guid:result.map[0].f_guid,
			            	f_gwbh:result.map[0].f_gwbh,
			            	f_gwmc:result.map[0].f_gwmc,
			                f_zzjgmc:result.map[1].f_zzjgmc,
			            	f_zzjgbh:result.map[1].f_zzjgbh,
			            	f_remark:result.map[0].f_remark,
			            	f_crdate:result.map[0].f_crdate,
			            	f_chdate:result.map[0].f_chdate
			            	});
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
 	$('#modal-form-adduserpost').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
 //*************************************
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#modal-form-adduserpost .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
 	//居中显示（编辑）
 	$('#editUserPostForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#editUserPostForm .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
	//居中显示（包含用户）
 	$('#includeUserPost').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#includeUserPost .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
	//关闭模态框清空表单值
    $("#modal-form-adduserpost").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        euserValidator.resetForm();
    });

    //删除数据
    $(document).on('click','#epostTable button.delete', function () {
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
			          url: _ctx + "/view/userpost/user_post_del",
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
		              		swal({
				 	        	title : data.msg,// 展示的标题
				 	   			text : "",// 内容
				 	   			type: "success",
				 	   			showCloseButton : false, // 展示关闭按钮
				 	   			allowOutsideClick : false,
				 	   			showConfirmButton : false,
				 	   			timer: 1000
				 	   		});
		              		$("#epostTable").tabulator("deleteRow", _curRow);
		              		var getpostTable = $("#epostTable").tabulator("getData");
		              		$("#epostTable").tabulator("setData", getpostTable);
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
   //编辑岗位——表单验证
  $("#post_edit_PostForm").validate({
    submitHandler: function(form) {
      editUserPostFormFunc(form);
    }
  });

  function editUserPostFormFunc(form) {
    $.ajax({
      url: _ctx + "/view/userpost/userpost_edit",
      type: "post",
      data: ({
      		f_guid: $("#post_edit_f_guid").val(),
      		f_gwbh: $("#post_edit_f_gwbh").val(),
	        f_gwmc: $("#post_edit_f_gwmc").val(),
	        f_remark: $("#post_edit_f_guremark").val(),
      }),
      beforeSend: function () {
			showLoad();
		},
      success: function(result) {
			if (result.status == '1') {
              $('#editUserPostForm').modal('hide');//关闭编辑窗口
              swal({
	 	        	title : result.msg,// 展示的标题
	 	   			text : "",// 内容
	 	   			type: "success",
	 	   			showCloseButton : false, // 展示关闭按钮
	 	   			allowOutsideClick : false,
	 	   			showConfirmButton : false,
	 	   			timer: 1000
	 	   		});
	         $('#epostTable').tabulator("updateRow",_curRow, {
	        	 f_gwmc:result.data.f_gwmc,
	        	 f_remark:result.data.f_remark,
	        	 f_chdate:result.data.f_chdate
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
  $("#editUserPostForm").on('show.bs.modal', function(event) {
    var id = _curRow.getData().f_guid;
  //模态框拖动********************
	$(this).draggable({
		handle:".modal-header"
	});
	$(this).css("overflow","hidden");
//*************************************
   	$.ajax({
	       url: _ctx + "/view/userpost/loadeditobj",
	        type: "post",
           contentType: "application/json; charset=utf-8",
	       data:JSON.stringify({
	 			f_guid:id
	 		}),
	 		beforeSend: function () {
 				showLoad();
 			},
	       success: function(result) {
	         $("#post_edit_f_guremark").val(result.f_remark);
	         $("#post_edit_f_guid").val(result.f_guid);
	         $("#post_edit_f_gwbh").val(result.f_gwbh);
	         $("#post_edit_f_gwmc").val(result.f_gwmc);
	         },
	         complete: function () {
					hiddenLoad();
				},
    });
  });
//验证码在模态框出现前加载包含用户(可拖动)
	  $("#includeUserPost").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	  	post_loadNoIncludeUser(id);
		post_loadIncludeUser(id);
		_fpostguid = id;
	});
	//添加用户至用户组
    $(document).on('click','#userpost_noInclude button.insertUser', function () {
        var f_yhbh = $(this).data("id");
        $("#user_post_right").attr('disabled',false);
		$("#user_post_left").attr('disabled',false);
	            	$.ajax({
			          url: _ctx + "/view/userpost/userpost_insertuser",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({
			         			f_gwguid : _fpostguid,
			        			f_yhbh : f_yhbh,
			        			f_crdate : '000'

			    	  }),
			    	  beforeSend: function () {
			 				showLoad();
			 			},
		          	  success: function(result) {
		              	if (result.status == '1') {
		              		//swal(data.msg, "", "success");
		              		//在未选择表格中删除该条数据
		              		$("#userpost_noInclude").tabulator("deleteRow", _notincludecurRow);
				            //在已选择表格中添加该条数据
				             $('#userpost_includeuser').tabulator("addRow", { f_yhbh:result.data.f_yhbh, f_name:result.data.f_name});

				             //未包含用户表格：
						       var noinclude_post_data = $("#userpost_noInclude").tabulator("getData");
						       $("#userpost_noInclude").tabulator("setData", noinclude_post_data);
						       //已包含用户表格：
						       var include_post_data = $("#userpost_includeuser").tabulator("getData");
						       $("#userpost_includeuser").tabulator("setData", include_post_data);
						       if(noinclude_post_data.length == 0){
					            	 $("#user_post_right").attr('disabled',true);
					            	 $("#user_post_left").attr('disabled',false);
					             }
		              	} else{
			                swal(result.msg,"", "error");
			            }
			          },
			          complete: function () {
							hiddenLoad();
						},
			          error: function(result) {
			          	 swal(result.msg,"", "error");
			          }
			        });
   				 });
	//移除用户组中包含用户
    $(document).on('click','#userpost_includeuser button.deleteUser', function () {
        			var f_yhbh = $(this).data("id");
        			$("#user_post_right").attr('disabled',false);
        			$("#user_post_left").attr('disabled',false);
	            	$.ajax({
			          url: _ctx + "/view/userpost/userpost_deleteuser",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({
			        			f_yhbh:f_yhbh
			    	  }),
			    	  beforeSend: function () {
			 				showLoad();
			 			},
		          	  success: function(result) {
		              	if (result.status == '1') {
		              		//swal(data.msg, "", "success");
		              		//在已选择表格中删除该条数据
		              		$("#userpost_includeuser").tabulator("deleteRow", _includecurRow);
				            //在未选择表格中添加该条数据
				             $('#userpost_noInclude').tabulator("addRow", { f_yhbh:result.data.f_yhbh, f_name:result.data.f_name});

				           //未包含用户表格：
					       var noinclude_post_data = $("#userpost_noInclude").tabulator("getData");
					       $("#userpost_noInclude").tabulator("setData", noinclude_post_data);
					       //已包含用户表格：
					       var include_post_data = $("#userpost_includeuser").tabulator("getData");
					       $("#userpost_includeuser").tabulator("setData", include_post_data);
					       if(include_post_data.length == 0){
				            	 $("#user_post_right").attr('disabled',false);
				            	 $("#user_post_left").attr('disabled',true);
				             }
		              	} else{
			                swal(result.msg,"", "error");
			            }
			          },
			          complete: function () {
							hiddenLoad();
						},
			          error: function(result) {
			          	 swal(result.msg,"", "error");
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
	$("#userpost_noInclude").tabulator({
		height:"93.3%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,align:"center",sorter:"string",headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"用户编号", field:"f_yhbh", width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"用户名称", field:"f_name",width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconNoIncludeFunc,headerSort:false},
		],
		rowClick:function(e, not_row){
        	_notincludecurRow = not_row;
    	},
	});

	//创建并设置“已选择”table属性
	$("#userpost_includeuser").tabulator({
		height:"93.3%",
		layout:"fitColumns",
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		//placeholder:"无数据，请添加用户",
		//movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,align:"center",sorter:"string",headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"用户编号", field:"f_yhbh", width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"用户名称", field:"f_name",width:80,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"操作", field:"opt",width:80,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconInclude,headerSort:false},
		],
		rowClick:function(e, in_row){
        	_includecurRow = in_row;
    	},
	});
	//加载未选择用户表f_gwguid
	function post_loadNoIncludeUser(id){
	//填充“未选择”数据
	$("#userpost_noInclude").tabulator("setData", _ctx+'/view/userpost/loadNoIncludeUser?f_gwguid='+id);
	}
	//加载已选择用户表
	function post_loadIncludeUser(id){
	//填充“已选择”数据
	$("#userpost_includeuser").tabulator("setData", _ctx+'/view/userpost/loadPostRlglUser?f_gwguid='+id);
	}
	return {

		getSydwNodes:function(fSydw){
			var sydw = "";//定义使用单位变量
	 		if(fSydw !="" && fSydw.length>0){
		        var Nodes = $("#tree_epost").treeview('findNodes',[fSydw,'id']);
				$.each(Nodes,function(i,item){
					sydw= sydw + ","+item.id;
				});
			};
			sydw = sydw.substring(1,sydw.length);
			return sydw;
	 	},

		//搜索
		search_epost:function(){
			var euserpostkeywords = $("#euserpostkeywords").val();
			$("#epostTable").tabulator("setData", _ctx+'/view/userpost/user_post_search?keywords='+euserpostkeywords);
		},
		//验证增加模态框是否弹出
		 post_show_addmodal:function() {
	       var node = $('#tree_epost').treeview('getSelected');
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
					$('#modal-form-adduserpost').modal('show');
					$("#post_add_f_zzjgbh").val(node[0].id);
					$("#post_add_f_zzjgmc").val(node[0].text);
					}
	    },
	  //搜索已包含用户
		post_searchIncludeUser:function(){
			var includeUserKeywords = $("#includeUserKeywords").val();
			$("#userpost_includeuser").tabulator("setData", _ctx+'/view/userpost/loadPostRlglUser?f_gwguid='+_fpostguid+'&keywords='+includeUserKeywords);
		},
		//搜索未包含用户
		post_searchNotIncludeUser:function(){
			var notincludeUserKeywords = $("#notincludeUserKeywords").val();
			$("#userpost_noInclude").tabulator("setData", _ctx+'/view/userpost/loadNoIncludeUser?f_gwguid='+_fpostguid+'&keywords='+notincludeUserKeywords);
		},

		//全部右移
		post_rightMove:function(){
			var post_tem = $("#userpost_noInclude").tabulator("getData");
			if(post_tem.length<1){
				$("#user_post_right").attr('disabled',true);
			}else{
			$.ajax({
				url: _ctx + "/view/userpost/userpost_rightmoveuser",
				contentType: "application/json; charset=utf-8",
				type: "post",
				data:JSON.stringify({
				      f_gwguid:_fpostguid
				}),
				beforeSend: function () {
	 				showLoad();
	 			},
			    success: function(data) {
			              	$("#userpost_includeuser").tabulator("setData", _ctx+'/view/userpost/loadPostRlglUser?f_gwguid='+_fpostguid);
							$("#userpost_noInclude").tabulator("setData", _ctx+'/view/userpost/loadNoIncludeUser?f_gwguid='+_fpostguid);
							$("#user_post_right").attr('disabled',true);
							$("#user_post_left").attr('disabled',false);
			    		},
	 			complete: function () {
					hiddenLoad();
				},
			 });
			}
		},
		//全部左移
		post_leftMove:function(){
			var post_tem = $("#userpost_includeuser").tabulator("getData");
			if(post_tem.length<1){
				$("#user_post_left").attr('disabled',true);
			}else{
				$.ajax({
					url: _ctx + "/view/userpost/userpost_leftmoveuser",
					contentType: "application/json; charset=utf-8",
					type: "post",
					data:JSON.stringify({
					      f_gwguid:_fpostguid
					}),
					beforeSend: function () {
		 				showLoad();
		 			},
				    success: function(data) {
			            if (data.status == '1') {
			              	//swal(data.msg, "", "success");
			              	$("#userpost_includeuser").tabulator("setData", _ctx+'/view/userpost/loadPostRlglUser?f_gwguid='+_fpostguid);
							$("#userpost_noInclude").tabulator("setData", _ctx+'/view/userpost/loadNoIncludeUser?f_gwguid='+_fpostguid);
							$("#user_post_left").attr('disabled',true);
							$("#user_post_right").attr('disabled',false);
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
		//初始加载函数
		pageInit:function(){
			$.ajax({
		        type: "get",
		        url: _ctx+'/view/userpost/loadAllPost',
		        dataType: "json",
		        beforeSend: function () {
      				showLoad();
      			},
		        success: function (result) {
		        	 $("#epostTable").tabulator("setData", result.list);
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
 usermanage_userpost.pageInit();
 </script>
