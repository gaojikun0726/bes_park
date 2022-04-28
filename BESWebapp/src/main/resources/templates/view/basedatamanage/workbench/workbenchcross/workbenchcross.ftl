<!-- 左侧树 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
			aria-hidden="true"></i>&nbsp;设备维护>>>
		</span>
	</div>
	<div id="workBenchCross_tree" class="Information_area"></div>
</div>
<!-- 右侧表格 -->
<div class="information_right">
	<div class="information_size" style="height: 100%;">
		<div class="information-model">
			<span class="Subtitle"> <i class="fa-fa-th-list"
				aria-hidden="ture"></i>&nbsp;设备配置列表>>>
			</span>
			<!-- 增加按钮 -->
			<a id="addCross" data-toggle="modal"
				href="#modal-form-addworkbenchType" class="btn btn-primary toLeft">
				<i class="fa fa-plus" style="margin-top: 2.5px; margin-left: 2px;"
				aria-hidden="true"></i>增加
			</a>
			<!-- 搜索框 -->
			<div class="zc_search find">
				<input type="text" class="find-style" id="workbenchinfo"
					name="workbenchinfo" placeholder="设备名称、类型">
				<button id="queryWorkbenchCrossType">
					<i class="fa fa-search" aria-hidden="true"></i>
				</button>
			</div>
		</div>
		<div class="ibox" id="WorkbenchCross_ibox" ></div>
	</div>
</div>

<!-- 添加添加modal框 -->
<div class="modal fade" id="modal-form-addworkbenchType" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title editIcon">&nbsp;添加设备信息</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="addworkbenchTypeform"
					name="addworkbenchTypeform" class="form-horizontal">
					<input type="hidden"  id="treenodeId" name="f_gztzzjg_id" value="" >
					<div class="form-group">
						<label class="col-sm-3 control-label">设备名称<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="f_name" name="f_name"
								placeholder="请输入工作台名称" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">设备类型<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="f_type" name="f_type"
								placeholder="请输入工作台类型" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">设备描述<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="f_description" name="f_description"
								placeholder="请描述工作台" required class="form-control">
						</div>
					</div>
					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-3 display_flex">
							<button class="btn btn-md btn-primary" type="submit">
								<strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;确定</strong>
							</button>
							<button class="btn btn-white m-l-sm" type="button"
								data-dismiss="modal">
								<i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 添加编辑modal框 -->
<div class="modal fade" id="modal-form-editworkbenchType" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title editIcon">&nbsp;编辑设备信息</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="editworkbenchTypeform"
					name="editworkbenchTypeform" class="form-horizontal">
					<input type="hidden" name="f_id" id="gzt_cross_fid">
					<div class="form-group">
						<label class="col-sm-3 control-label">设备名称<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="f_edit_name" name="f_name"
								placeholder="请输入工作台名称" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">设备类型<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="f_edit_type" name="f_type"
								placeholder="请输入工作台类型" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">设备描述<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="f_edit_description" name="f_description"
								placeholder="请描述工作台" required class="form-control">
						</div>
					</div>
					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-3 display_flex">
							<button class="btn btn-md btn-primary" type="submit">
								<strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;更新</strong>
							</button>
							<button class="btn btn-white m-l-sm" type="button"
								data-dismiss="modal">
								<i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
;
//创建table
var workbench_workbenchcross_workbenchcross= (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	var _curRow = null;
	var treeId = "";
	var pageNum = '${page.pageNum}';
   	var optIconFunction = function(cell, formatterParams){                              
   		var WorkbenchID = cell.getRow().getData().f_id;
   		return "<div class='btn-group '>"
   			+"<button class='btn btn-white btn-sm edit' data-id="+ WorkbenchID + " data-toggle='modal' data-target='#modal-form-editworkbenchType'><i class='fa fa-pencil' ></i> 编辑</button>"
   			+"<button class='btn btn-white btn-sm delete' data-id=" + WorkbenchID + "><i class='fa fa-trash'></i>  删除</button></div>"
   	};

	$("#WorkbenchCross_ibox").tabulator({
		height:"94%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		selectable:true,
		movableColumns:true,
		columns:[
			{title:"设备名称", field:"f_name",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"设备类型", field:"f_type",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"设备描述", field:"f_description",sorter:"string",editor:false,align:"center",headerSort:false},
			{title:"添加时间", field:"f_CRDATE",sorter:"date",editor:false,align:"center",headerSort:false},
			{title:"修改时间", field:"f_CHDATE",sorter:"date",editor:false,align:"center",headerSort:false},
			{title:"操作", field:"opt",width:200,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	$("#WorkbenchCrossTable").tabulator("selectRow", 1);
        	_curRow = row;
    	},
	});
    
	//加载树
	function workBench_tree(){
		$.ajax({
	        type: "post",
	        url: "${ctx}/view/basedatamanage/beseqmanage/Gztzzjg_tree",
	        dataType: "json",
	        beforeSend: function() {
	            showLoad();
	        },
	        success: function(result) {
	            //初始加载根节点
	            if (result.hasOwnProperty("list")) { //判断result返回结果是否包含list
	                if (result.list.length > 0) { //如果包含判断是否长度大于0
	                    $('#workBenchCross_tree').treeview({
	                        data: result.list,
	                        // 数据源
	                        highlightSelected: true,
	                        //是否高亮选中
	                        levels: 4,
	                        enableLinks: true,
	                        //必须在节点属性给出href属性
	                        color: "#4a4747",
	                        onNodeSelected: function(event, nodeData) {
	                          treeId = nodeData.id;
	                      	  //刷新右侧table
	                      	  RefreshTable(nodeData.id);
	                        }
	                    });
	                    var firstNode = $("#workBenchCross_tree").treeview('findNodes', [result.list[0].id, 'id']);
	                    $("#workBenchCross_tree").treeview("selectNode", firstNode); //将第一个节点设置为选中状态
	                }
	            }
	        },
	        complete: function() {
	            hiddenLoad();
	        },
	        error: function(nodeData) {
	            swal(nodeData.msg, "", "error");
	        }
	    });
	}
	
	//刷新右侧表格获取table值
 	function RefreshTable(f_gztzzjg_id){
 		$.ajax({
 			type:"post",
 			url:_ctx + "/view/basedatamanage/workbench/getWorkbenchTable",
 			dataType: "json",
 			data: {
		        "f_gztzzjg_id":f_gztzzjg_id,
		    },
 			success:function(data){
 				if(data.hasOwnProperty("list")){
 					$("#WorkbenchCross_ibox").tabulator("setData",data.list);
 				}else{
 					$("#WorkbenchCross_ibox").tabulator("setData",[]);
 				}
 			}
 		});
	}
	
	//回显
 	$(document).on("click","#WorkbenchCross_ibox button.edit",function() {
		var id = $(this).data("id");
		$.ajax({
			url: _ctx+"/view/basedatamanage/workbench/getWorkbench",
			type: "post",
			data:{
				"bh":id
			},
			success:function(result){
					$("#gzt_cross_fid").val(result.data.f_id);
					$("#f_edit_name").val(result.data.f_name);
	   				$("#f_edit_type").val(result.data.f_type);
	   				$("#f_edit_description").val(result.data.f_description);
			}
		});
 	});
	
    //居中显示(添加)
 	$("#modal-form-addworkbenchType").on('show.bs.modal', function () {
 	$("#modal-form-addworkbenchType").validate().resetForm(); 
	$("#modal-form-addworkbenchType").find("input").val("");
    $(this).css('display', 'block');  
    var modalHeight=$(window).height() / 2 - $('#modal-form-addworkbenchType .modal-dialog').height() / 2;  
    $(this).find('.modal-dialog').css({  
           'margin-top': modalHeight  
    });
    });
	
	//居中显示(编辑)
    $('#modal-form-editworkbenchType').on('show.bs.modal', function () {
     	$("#modal-form-editworkbenchType").validate().resetForm(); 
    	$("#modal-form-editworkbenchType").find("input").val("");
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-editworkbenchType .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	});
	
	//添加验证
	var workbenchTypeValidator =$("#addworkbenchTypeform").validate({
		rules : {
			f_name : {
				required : true,
			},
			f_type : {
				required : true,
			},
			f_description : {
				required : true,
			}
		},
		messages : {
			f_name : {
				required : "请输入工作台名称"
			},
			f_type : {
				required : "请输入工作台类型"
			},
			f_description : {
				required : "请输入工作台描述"
			}
		},
		submitHandler : function(form) {
			addformWorkbenchType(form);
		}
	});
	
	//新增保存
	function addformWorkbenchType(form) {
	  $("#treenodeId").val(treeId);
		$.ajax({
	       url: _ctx + "/view/basedatamanage/workbench/add_WorkbenchCross",
	       type: "post",
	       data:$(form).serialize(),
	       success: function(data) {
	         if (data.status == '1') {
	        	 swal({
			        	title : data.msg,// 展示的标题
			   			text : "添加成功",// 内容
			   			type: "success",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
		            $('#modal-form-addworkbenchType').modal('hide');//关闭编辑窗口
		            RefreshTable(treeId);
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
 	}
	
	//编辑验证
	var workbenchTypeValidator =$("#editworkbenchTypeform").validate({
		rules : {
			f_name : {
				required : true,
			},
			f_type : {
				required : true,
			},
			f_description : {
				required : true,
			}
		},
		messages : {
			f_name : {
				required : "请输入工作台名称"
			},
			f_type : {
				required : "请输入工作台类型"
			},
			f_description : {
				required : "请输入工作台描述"
			}
		},
		submitHandler : function(form) {
			editWorkbrnchType(form);
		}
	});
    
 	//编辑
	function editWorkbrnchType(form) {
		$.ajax({
     		url: _ctx + "/view/basedatamanage/workbench/edit_WorkbenchCross",
     		type: "post",
     		data:$(form).serialize(),
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
	              	$('#modal-form-editworkbenchType').modal('hide');//关闭编辑窗口
	            	RefreshTable(treeId);
         		} else{
             		swal("修改失败!", data.msg, "error");
         		}
    		},
    		error: function(data) {
         	 	swal("修改失败!", data.msg, "error");
    		}
   		});
	}
 	
	//删除数据
	$(document).on("click","#WorkbenchCross_ibox button.delete",function() {      //#DatecenterTable button.delete是啥
			var row = $(this).parents("tr")[0];
			var id = $(this).data("id");
			$("#treenodeId").val(treeId);
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
			setTimeout(function() {
				$.ajax({
				url : _ctx + "/view/basedatamanage/workbench/del_WorkbenchCross",
				type : "post",
				data : {"id" : id
				},
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
						RefreshTable(treeId);
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
				}, 100)
				});
		});
    
	 return {
	 		pageInit : function(){
	 			workBench_tree();
	 		}
	    }
})(jQuery, window, document);
workbench_workbenchcross_workbenchcross.pageInit();
</script>

