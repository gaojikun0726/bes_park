<#-----
描述：组织机构
作者：gongfanfei
---->


<!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择组织机构>>>
		</span>
	</div>
	<div id="tree_zzjg" class="Information_area"></div>
</div>
<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;机构列表>>>
			</span>
			<!-- 增加按钮 -->
			<a id="addZzjg_zzjg" href="javascript:void(-1);" onclick="organizationmanage_zzjg.show_addmodal();" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加 
			</a> 
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="zzjginfo" name="zzjginfo" placeholder="组织机构编号、名称">
				  <button id="queryuserBtn"onclick="organizationmanage_zzjg.searchzzjg()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="zzjgTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->

<!---添加组织机构信息开始-----> 
<div class="modal fade" id="modal-form-addzzjg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加组织机构信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addZzjg" name="addZzjg" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">组织机构名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="zzjg_add_f_zzjgmc" name="zzjg_add_f_zzjgmc" placeholder="请输入组织机构名称"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                          <label class="col-sm-3 control-label">备注</label>
                          <div class="col-sm-8">
                          	<input type="text" id="zzjg_add_f_remark" name="zzjg_add_f_remark" class="form-control">
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
<div class="modal fade" id="editZzjgForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑组织机构信息</h4>
            </div>
            <div class="modal-body">
            	<form id="editzzjgForm" name="editzzjgForm" class="form-horizontal">
            	<div class="form-group">
	<label class="col-sm-4 control-label" for="zzjg_edit_f_zzjgbh">组织机构编号  <span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="text" id="zzjg_edit_f_zzjgbh" name="zzjg_edit_f_zzjgbh"  required class="form-control" readonly="readonly">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label" for="zzjg_edit_f_zzjgmc">组织机构名称<span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="text" id="zzjg_edit_f_zzjgmc" name="zzjg_edit_f_zzjgmc"  required class="form-control">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label" for="zzjg_edit_f_remark">备注</label>
	<div class="col-sm-8">
		<input type="text" id="zzjg_edit_f_remark" name="zzjg_edit_f_remark" class="form-control">
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




<!---添加组织机构信息结束----->
 
 <script type="text/javascript">
/*  function itemOnclick(target){
		//找到当前节点id
		var nodeid = $(target).attr('data-nodeid');
		var tree = $('#tree_zzjg');
		//获取当前节点对象
		var node = tree.treeview('getNode', nodeid);
		
		if(node.state.expanded){ 
		    //处于展开状态则折叠
		    tree.treeview('collapseNode', node.nodeId);  
		} else {
		    //展开
		    tree.treeview('expandNode', node.nodeId);
		}
	} */
 ;
 var organizationmanage_zzjg = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
 	var _zzjgbh = "00";
	var _zzjgJs = "0";
	var _curRow = null;
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var idZzjg = cell.getRow().getData().f_id;
		var Zzjgbh = cell.getRow().getData().f_zzjgbh;
		return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ idZzjg + " data-toggle='modal' data-target='#editZzjgForm'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + Zzjgbh + "><i class='fa fa-trash'></i>  删除</button></div>"
	};

	//创建并设置table属性
	$("#zzjgTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:80,formatter:"rownum",frozen:false,sorter:"string",headerSort:false,align:"center"}, //frozen column
		{title:"组织机构编号", field:"f_zzjgbh", width:150,sorter:"string",editor:false,headerSort:false,align:"left"}, //never hide this column
		{title:"组织机构id", field:"f_id", width:150,sorter:"string",visible: false,editor:false,headerSort:false,align:"left"}, //never hide this column
		{title:"组织机构名称", field:"f_zzjgmc",width:150,sorter:"string",editor:false,headerSort:false,align:"left"}, //hide this column first 
		{title:"备注", field:"f_remark", width:120,sorter:"string",editor:false,headerSort:false,align:"left"},
		{title:"创建时间", field:"f_crdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"f_chdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	var id = _curRow.getData().f_zzjgbh;
        	var choiseNode = $('#tree_zzjg').treeview('findNodes', [ _curRow.getData().f_zzjgbh, 'id']);
        	if(choiseNode.length>1){
    	    	for (var i = 0; i < choiseNode.length; i++) {
    				if(choiseNode[i].id == id){
    					$('#tree_zzjg').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
    				}
    			}
    	    }else{
        	$('#tree_zzjg').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
    	    }
    	},
	});
	
	$(window).resize(function(){
		$("#zzjgTable").tabulator("redraw");
	});
	
	
	//Clear table on "Empty the table" button click
	$("#clearZzjg").click(function(){
	    $("#zzjgTable").tabulator("clearData");
	});
	//加载树 
	$(function () {
	    $.ajax({
	        type: "post",
	        url: _ctx + "/view/datacenter/zzjgTree",
	        dataType: "json",
	        beforeSend: function () { 
 				showLoad();	             
 			},
	        success: function (result) {
        	if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
             	if(result.list.length >0){//如果包含判断是否长度大于0 	
	            $('#tree_zzjg').treeview({
	                data: result.list,         // 数据源
	                highlightSelected: true,    //是否高亮选中
	                levels : 2,
	                enableLinks : true,//必须在节点属性给出href属性
	                color: "#4a4747",
	                onNodeSelected: function (event, nodeData) {
	                	$('#tree_zzjg').treeview('clearSearch');//清除搜索选中高亮
	               		_zzjgbh = nodeData.id;
	               		_zzjgJs = nodeData.level;
	                    $.ajax({
	                	    url: _ctx + "/view/datacenter/zzjg_chlildtreegrid",
	                	    contentType: "application/json; charset=utf-8",
	                	    type: "get",
	                	    beforeSend: function () { 
	        	 				showLoad();	             
	        	 			},
	                	    data: {
	                	    	f_zzjgbh:nodeData.id,
	                	    	f_js : nodeData.js
	                	    },
							success: function(result) {
								if(result.hasOwnProperty("list")){
					            $("#zzjgTable").tabulator("setData", result.list);
								}else{
					            $("#zzjgTable").tabulator("setData", []);
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
	            var firstNode = $("#tree_zzjg").treeview('findNodes',[result.list[0].id,'id']);
	        	$("#tree_zzjg").treeview("selectNode", firstNode);//将第一个节点设置为选中状态 
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
	$("#zzjginfo").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  organizationmanage_zzjg.searchzzjg();//触发该事件
					    } 
					  })
					});

	//添加组织机构表单验证
    var zzjgValidator = $("#addZzjg").validate({
	     rules: {
	    	 zzjg_add_f_zzjgmc: {
	    			required:true,
					isNormal:true,
	         },
	         zzjg_add_f_remark:{
				isNormal:true,
	         }
	     },
	     messages: {
	         
	    	 zzjg_add_f_zzjgmc: {
	    		 required:"请输入组织机构名称",
					isNormal:"请输入合法字符"
	         },
	         zzjg_add_f_remark:{
					isNormal:"请输入合法字符"
	         }
	     },
	     submitHandler: function (nodeData) {
	         addformZzjg(nodeData);
	     }
 	});
  //新增保存
	function addformZzjg(nodeData) {
	  
		     $.ajax({
		       url: _ctx + "/view/datacenter/zzjg_add",
		       type: "post",
	           contentType: "application/json; charset=utf-8",
		       data:JSON.stringify({     
		 			f_zzjgmc:$("#zzjg_add_f_zzjgmc").val(),
					f_remark:$("#zzjg_add_f_remark").val(),
					f_pzzjgbh:_zzjgbh,
					f_js:_zzjgJs
		 		}),
		 		beforeSend: function () { 
	 				showLoad();	             
	 			},
		       success: function(result) {
		         if (result.status == '1') {
		            $('#modal-form-addzzjg').modal('hide');//关闭编辑窗口
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
			            $('#zzjgTable').tabulator("addRow", {
			            	f_id:result.data.f_id, 
			            	f_zzjgbh:result.data.f_zzjgbh, 
			            	f_zzjgmc:result.data.f_zzjgmc, 
			            	f_remark:result.data.f_remark, 
			            	f_crdate:result.data.f_crdate, 
			            	f_chdate:result.data.f_chdate
			            	});
			            //在树上添加
			            var pNode = $("#tree_zzjg").treeview("getSelected");
			            $("#tree_zzjg").treeview("addNode", [{ id:result.data.f_zzjgbh, text:result.data.f_zzjgmc, pid:pNode[0].id, js:result.js}, pNode]);  
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
 	$('#modal-form-addzzjg').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
 		//*************************************
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-addzzjg .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
 	//居中显示（编辑）
 	$('#editZzjgForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editZzjgForm .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	//关闭模态框清空表单值
    $("#modal-form-addzzjg").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        zzjgValidator.resetForm();  
    });
    
    //删除数据
    $(document).on('click','#zzjgTable button.delete', function () {
        var id=$(this).data("id");
        var f_id=organizationmanage_zzjg.getCurRow().getData().f_id;
        var dNode = $('#tree_zzjg').treeview('findNodes', [ id, 'id']);
	    if(dNode.length>1){
	    	for (var i = 0; i < dNode.length; i++) {
				if(dNode[i].id == id){
					var booljuge = dNode[i].hasOwnProperty('nodes');
					if(booljuge == true){
						swal({ 
				            title: "请您先删除子机构！",
				            text: "经检测，您要删除的机构包含子机构!",
				            type: "warning",
				            showCancelButton: false,
				            confirmButtonColor: "#1ab394",
				            confirmButtonText: "关闭",
				            closeOnConfirm: false
				        });
					}else{
						deletezzjg(f_id,dNode[i]);//删除
					}
				}
			}
	    	
	    
	    }else{
	    	deletezzjg(f_id,dNode[0]);
        }
    });
	//关闭模态框清空表单值
    $("#editZzjgForm").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        zzjgValidatoredit.resetForm();  
    });
   //表单验证
 var zzjgValidatoredit =  $("#editzzjgForm").validate({
	  //zzjg_edit_f_zzjgbh
	  //zzjg_edit_f_zzjgmc
	  //zzjg_edit_f_remark
	     rules: {
	         zzjg_edit_f_zzjgmc:{
	        	 required:true,
				isNormal:true,
	         },
	         zzjg_edit_f_remark:{
				 isNormal:true,
	         }
	     },
	     messages: {
	         zzjg_edit_f_zzjgmc:{
	        	 required:"请输入组织机构名称",
					isNormal:"请输入合法字符"
	         },
	         zzjg_edit_f_remark:{
					isNormal:"请输入合法字符"
	         }
	     },
    submitHandler: function(form) {
      editzzjgForm(form);
    }
  });
  
  //验证码在模态框出现前加载
  $("#editZzjgForm").on('show.bs.modal', function(event) {
      var fid = _curRow.getData().f_id;
    //模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
	//*************************************
    $.ajax({
	       url: _ctx + "/view/datacenter/loadeditobj",
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
	         $("#zzjg_edit_f_zzjgbh").val(result.f_zzjgbh);
	         $("#zzjg_edit_f_zzjgmc").val(result.f_zzjgmc);
	         $("#zzjg_edit_f_remark").val(result.f_remark);
	         },
           complete: function () {
				hiddenLoad();
		},
    });  
  });
//编辑组织机构
	function editzzjgForm(form) {
		var fid=_curRow.getData().f_id;
	    $.ajax({
	      url: _ctx + "/view/datacenter/zzjg_edit",
	      type: "post",
	      data: ({
	    	    f_id:fid,
	            f_zzjgbh: $("#zzjg_edit_f_zzjgbh").val(),
		        f_zzjgmc: $("#zzjg_edit_f_zzjgmc").val(),
		        f_remark: $("#zzjg_edit_f_remark").val()
	      }),
	      beforeSend: function () { 
				showLoad();	             
			},
	      success: function(result) {
				if (result.status == '1') {
	              $('#editZzjgForm').modal('hide');//关闭编辑窗口
	              swal({
			        	title : result.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
		         $('#zzjgTable').tabulator("updateRow",_curRow, {
		        	 f_zzjgmc:result.data.f_zzjgmc, 
		        	 f_remark:result.data.f_remark, 
		        	 f_chdate:result.data.f_chdate
		        	 });
		         		//在树上修改   
					   var parentNode = $('#tree_zzjg').treeview('findNodes', [ _curRow.getData().f_id, 'fid']);
					   if(parentNode.length>1){
			    	    	for (var i = 0; i < parentNode.length; i++) {
			    				if(parentNode[i].id == _curRow.getData().f_id){
			    					parentNode = parentNode[i];
			    				}
			    			}
			    	    }else{
			    	    	parentNode = parentNode[0];
			    	    }
		            	var newNode={text: _curRow.getData().f_zzjgmc};
						$('#tree_zzjg').treeview('updateNode', [ parentNode, newNode]);
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
	function deletezzjg(id,node){
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
			          url: _ctx + "/view/datacenter/zzjg_del",
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
		              		$("#zzjgTable").tabulator("deleteRow", _curRow);
				            //在树上删除该条数据
				             $("#tree_zzjg").treeview("removeNode", node, { silent: true } );    
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
	  	 searchzzjg:function(){
	  		var zzjginfo = $("#zzjginfo").val();
	  		$("#zzjgTable").tabulator("setData", _ctx+'/view/datacenter/zzjg_treegrid?zzjg_in='+zzjginfo);
	  	},
	  	getCurRow : function(){
			return _curRow;
		},
	  	//验证增加模态框是否弹出
	  	 show_addmodal:function() {
	         var node = $('#tree_zzjg').treeview('getSelected');
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
	  				}else
	  				$('#modal-form-addzzjg').modal('show'); 
	      },
  }
  
  
  
 })(jQuery, window, document);
 </script>