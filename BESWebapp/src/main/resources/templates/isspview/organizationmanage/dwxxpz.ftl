<#-----
描述：单位配置信息
作者：wangzhixiang
---->


<!-- 单位树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;单位信息树>>>
		</span>
	</div>
	<div id="tree_dwxx" class="Information_area"></div>
</div>
<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;单位列表>>>
			</span>
			<!-- 增加按钮 -->
			<a id="addDwxx_dwxx" href="javascript:void(-1);" onclick="organizationmanage_dwxx.show_addmodal();" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
			</a>
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="dwxxinfo" name="dwxxinfo" placeholder="单位编号、名称">
				  <button id="queryuserBtn"onclick="organizationmanage_dwxx.searchdwxx()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="dwxxTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->

<!---添加单位信息开始----->
<div class="modal fade" id="modal-form-adddwxx" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加单位信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addDwxx" name="addDwxx" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">单位名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="dwxx_add_f_dwxxmc" name="dwxx_add_f_dwxxmc" placeholder="请输入单位名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                          <label class="col-sm-3 control-label">备注</label>
                          <div class="col-sm-8">
                          	<input type="text" id="dwxx_add_f_remark" name="dwxx_add_f_remark" class="form-control">
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
<div class="modal fade" id="editDwxxForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑单位信息</h4>
            </div>
            <div class="modal-body">
            	<form id="editdwxxForm" name="editdwxxForm" class="form-horizontal">
            	<div class="form-group">
	<label class="col-sm-4 control-label" for="dwxx_edit_f_dwxxbh">单位编号  <span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="text" id="dwxx_edit_f_dwxxbh" name="dwxx_edit_f_dwxxbh"  required class="form-control" readonly="readonly">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label" for="dwxx_edit_f_dwxxmc">单位名称<span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="text" id="dwxx_edit_f_dwxxmc" name="dwxx_edit_f_dwxxmc"  required class="form-control">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label" for="dwxx_edit_f_remark">备注</label>
	<div class="col-sm-8">
		<input type="text" id="dwxx_edit_f_remark" name="dwxx_edit_f_remark" class="form-control">
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




<!---添加单位信息结束----->

 <script type="text/javascript">
 ;
 var organizationmanage_dwxx = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
 	var _dwxxbh = "00";
	var _dwxxJs = "0";
	var _curRow = null;
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var idDwxx = cell.getRow().getData().f_id;
		var Dwxxbh = cell.getRow().getData().f_dwxxbh;
		return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ idDwxx + " data-toggle='modal' data-target='#editDwxxForm'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + Dwxxbh + "><i class='fa fa-trash'></i>  删除</button></div>"
	};

	//创建并设置table属性
	$("#dwxxTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:80,formatter:"rownum",frozen:false,sorter:"string",headerSort:false,align:"center"}, //frozen column
		{title:"单位编号", field:"f_dwxxbh", width:150,sorter:"string",editor:false,headerSort:false,align:"left"}, //never hide this column
		{title:"单位id", field:"f_id", width:150,sorter:"string",visible: false,editor:false,headerSort:false,align:"left"}, //never hide this column
		{title:"单位名称", field:"f_dwxxmc",width:150,sorter:"string",editor:false,headerSort:false,align:"left"}, //hide this column first
		{title:"备注", field:"f_remark", width:120,sorter:"string",editor:false,headerSort:false,align:"left"},
		{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	var id = _curRow.getData().f_dwxxbh;
        	var choiseNode = $('#tree_dwxx').treeview('findNodes', [ _curRow.getData().f_dwxxbh, 'id']);
        	if(choiseNode.length>1){
    	    	for (var i = 0; i < choiseNode.length; i++) {
    				if(choiseNode[i].id == id){
    					$('#tree_dwxx').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
    				}
    			}
    	    }else{
        	$('#tree_dwxx').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
    	    }
    	},
	});

	$(window).resize(function(){
		$("#dwxxTable").tabulator("redraw");
	});


	//Clear table on "Empty the table" button click
	$("#clearDwxx").click(function(){
	    $("#dwxxTable").tabulator("clearData");
	});
	//加载树
	$(function () {
	    $.ajax({
	        type: "post",
	        url: _ctx + "/view/datacenterdemo/dwxxTree",
	        dataType: "json",
	        beforeSend: function () {
 				showLoad();
 			},
	        success: function (result) {
        	if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
             	if(result.list.length >0){//如果包含判断是否长度大于0
	            $('#tree_dwxx').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 2,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	                onNodeSelected: function (event, nodeData) {
	                	$('#tree_dwxx').treeview('clearSearch');//清除搜索选中高亮
	               		_dwxxbh = nodeData.id;
	               		_dwxxJs = nodeData.level;
	                    $.ajax({
	                	    url: _ctx + "/view/datacenterdemo/dwxx_chlildtreegrid",
	                	    contentType: "application/json; charset=utf-8",
	                	    type: "get",
	                	    beforeSend: function () {
	        	 				showLoad();
	        	 			},
	                	    data: {
								f_dwxxbh:nodeData.id,
	                	    	f_js : nodeData.js
	                	    },
							success: function(result) {
								if(result.hasOwnProperty("list")){
					            $("#dwxxTable").tabulator("setData", result.list);
								}else{
					            $("#dwxxTable").tabulator("setData", []);
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
	            var firstNode = $("#tree_dwxx").treeview('findNodes',[result.list[0].id,'id']);
	        	$("#tree_dwxx").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
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
	$("#dwxxinfo").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  organizationmanage_dwxx.searchdwxx();//触发该事件
					    }
					  })
					});

	//添加单位表单验证
    var dwxxValidator = $("#addDwxx").validate({
	     rules: {
	    	 dwxx_add_f_dwxxmc: {
	    			required:true,
					isNormal:true,
	         },
	         dwxx_add_f_remark:{
				isNormal:true,
	         }
	     },
	     messages: {

	    	 dwxx_add_f_dwxxmc: {
	    		 required:"请输入单位名称",
					isNormal:"请输入合法字符"
	         },
	         dwxx_add_f_remark:{
					isNormal:"请输入合法字符"
	         }
	     },
	     submitHandler: function (nodeData) {
	         addformDwxx(nodeData);
	     }
 	});
  //新增保存
	function addformDwxx(nodeData) {

		     $.ajax({
		       url: _ctx + "/view/datacenterdemo/dwxx_add",
		       type: "post",
	           contentType: "application/json; charset=utf-8",
		       data:JSON.stringify({
		 			f_dwxxmc:$("#dwxx_add_f_dwxxmc").val(),
					f_remark:$("#dwxx_add_f_remark").val(),
					f_pdwxxbh:_dwxxbh,
					f_js:_dwxxJs
		 		}),
		 		beforeSend: function () {
	 				showLoad();
	 			},
		       success: function(result) {
		         if (result.status == '1') {
		            $('#modal-form-adddwxx').modal('hide');//关闭编辑窗口
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
			            $('#dwxxTable').tabulator("addRow", {
			            	f_id:result.data.f_id,
			            	f_dwxxbh:result.data.f_dwxxbh,
			            	f_dwxxmc:result.data.f_dwxxmc,
			            	f_remark:result.data.f_remark,
			            	f_crdate:result.data.f_crdate,
			            	f_chdate:result.data.f_chdate
			            	});
			            //在树上添加
			            var pNode = $("#tree_dwxx").treeview("getSelected");
			            $("#tree_dwxx").treeview("addNode", [{ id:result.data.f_dwxxbh, text:result.data.f_dwxxmc, pid:pNode[0].id, js:result.js}, pNode]);
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
 	$('#modal-form-adddwxx').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
 		//*************************************
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#modal-form-adddwxx .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
 	//居中显示（编辑）
 	$('#editDwxxForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#editDwxxForm .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
	//关闭模态框清空表单值
    $("#modal-form-adddwxx").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        dwxxValidator.resetForm();
    });

    //删除数据
    $(document).on('click','#dwxxTable button.delete', function () {
        var id=$(this).data("id");
        var f_id=organizationmanage_dwxx.getCurRow().getData().f_id;
        var dNode = $('#tree_dwxx').treeview('findNodes', [ id, 'id']);
	    if(dNode.length>1){
	    	for (var i = 0; i < dNode.length; i++) {
				if(dNode[i].id == id){
					var booljuge = dNode[i].hasOwnProperty('nodes');
					if(booljuge == true){
						swal({
				            title: "请您先删除子单位！",
				            text: "经检测，您要删除的单位包含子单位!",
				            type: "warning",
				            showCancelButton: false,
				            confirmButtonColor: "#1ab394",
				            confirmButtonText: "关闭",
				            closeOnConfirm: false
				        });
					}else{
						deletedwxx(f_id,dNode[i]);//删除
					}
				}
			}


	    }else{
	    	deletedwxx(f_id,dNode[0]);
        }
    });
	//关闭模态框清空表单值
    $("#editDwxxForm").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        dwxxValidatoredit.resetForm();
    });
   //表单验证
 var dwxxValidatoredit =  $("#editdwxxForm").validate({
	     rules: {
	         dwxx_edit_f_dwxxmc:{
	        	 required:true,
				isNormal:true,
	         },
	         dwxx_edit_f_remark:{
				 isNormal:true,
	         }
	     },
	     messages: {
	         dwxx_edit_f_dwxxmc:{
	        	 required:"请输入单位名称",
					isNormal:"请输入合法字符"
	         },
	         dwxx_edit_f_remark:{
					isNormal:"请输入合法字符"
	         }
	     },
    submitHandler: function(form) {
      editdwxxForm(form);
    }
  });

  //验证码在模态框出现前加载
  $("#editDwxxForm").on('show.bs.modal', function(event) {
      var fid = _curRow.getData().f_id;
    //模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
	//*************************************
    $.ajax({
	       url: _ctx + "/view/datacenterdemo/loadeditobj",
	        type: "post",
           contentType: "application/json; charset=utf-8",
	       data:JSON.stringify({
	 			f_id:fid
	 		}),
	 		beforeSend: function () {
 				showLoad();
 			},
	       success: function(result) {
	    	 console.log(result);
	         $("#dwxx_edit_f_dwxxbh").val(result.f_dwxxbh);
	         $("#dwxx_edit_f_dwxxmc").val(result.f_dwxxmc);
	         $("#dwxx_edit_f_remark").val(result.f_remark);
	         },
           complete: function () {
				hiddenLoad();
		},
    });
  });
//编辑单位
	function editdwxxForm(form) {
		var fid=_curRow.getData().f_id;
	    $.ajax({
	      url: _ctx + "/view/datacenterdemo/dwxx_edit",
	      type: "post",
	      data: ({
	    	    f_id:fid,
	            f_dwxxbh: $("#dwxx_edit_f_dwxxbh").val(),
		        f_dwxxmc: $("#dwxx_edit_f_dwxxmc").val(),
		        f_remark: $("#dwxx_edit_f_remark").val()
	      }),
	      beforeSend: function () {
				showLoad();
			},
	      success: function(result) {
				if (result.status == '1') {
	              $('#editDwxxForm').modal('hide');//关闭编辑窗口
	              swal({
			        	title : result.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
		         $('#dwxxTable').tabulator("updateRow",_curRow, {
		        	 f_dwxxmc:result.data.f_dwxxmc,
		        	 f_remark:result.data.f_remark,
		        	 f_chdate:result.data.f_chdate
		        	 });
		         		//在树上修改
					   var parentNode = $('#tree_dwxx').treeview('findNodes', [ _curRow.getData().f_id, 'fid']);
					   if(parentNode.length>1){
			    	    	for (var i = 0; i < parentNode.length; i++) {
			    				if(parentNode[i].id == _curRow.getData().f_id){
			    					parentNode = parentNode[i];
			    				}
			    			}
			    	    }else{
			    	    	parentNode = parentNode[0];
			    	    }
		            	var newNode={text: _curRow.getData().f_dwxxmc};
						$('#tree_dwxx').treeview('updateNode', [ parentNode, newNode]);
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
	function deletedwxx(id,node){
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
			          url: _ctx + "/view/datacenterdemo/dwxx_del",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({
			        	  f_id:id
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
		              		$("#dwxxTable").tabulator("deleteRow", _curRow);
				            //在树上删除该条数据
				             $("#tree_dwxx").treeview("removeNode", node, { silent: true } );
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
  return{
	    //搜索
	  	 searchdwxx:function(){
	  		var dwxxinfo = $("#dwxxinfo").val();
	  		$("#dwxxTable").tabulator("setData", _ctx+'/view/datacenterdemo/dwxx_treegrid?dwxx_in='+dwxxinfo);
	  	},
	  	getCurRow : function(){
			return _curRow;
		},
	  	//验证增加模态框是否弹出
	  	 show_addmodal:function() {
	         var node = $('#tree_dwxx').treeview('getSelected');
	  				if (node.length == 0) {//凡是节点，note.length都为1；无选择节点，为0；
	  					swal({
	  	            title: "请选择节点",
	  	            text: "经检测，您要未选择单位节点!",
	  	            type: "warning",
	  	            showCancelButton: false,
	  	            confirmButtonColor: "#1ab394",
	  	            confirmButtonText: "关闭",
	  	            closeOnConfirm: false
	  	        });
	  				}else
	  				$('#modal-form-adddwxx').modal('show');
	      },
  }



 })(jQuery, window, document);
 </script>
