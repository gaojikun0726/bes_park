<!-----内容区域---->
<style type="text/css">
.col-sm-2 {
	width: 8.666667%;
}

.col-sm-3 {
	width: 16%;
}

.form-horizontal .control-label {
	padding-top: 2px;
}
</style>
<!-- 左侧树 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle"> <i class="fa fa-share-alt"
			aria-hidden="true"></i>&nbsp;工作台组织结构维护>>>
		</span>
	</div>
	<div id="workBench_tree" class="Information_area"></div>
</div>
<!-- 右侧表格 -->
<div class="information_right">
	<div class="information_size" style="height: 100%;">
		<div class="information-model">
			<span class="Subtitle"> <i class="fa fa-th-list"
				aria-hidden="true"></i>&nbsp;工作台组织结构维护>>>
			</span>
			<!-- 增加按钮 -->
			<a id="workBnechTree_add" data-toggle="modal"
				href="#modal-form-workBnechTree_add" class="btn btn-primary toLeft">
				<i class="fa fa-plus" style="margin-top: 2.5px; margin-left: 2px;"
				aria-hidden="true"></i>增加
			</a>
			<!-- 搜索框 -->
			<div class="zc_search find">
				<input type="text" class="find-style" id="budinginfo"
					name="budinginfo" placeholder="组织结构名称...">
				<button id="queryBudingType">
					<i class="fa fa-search" aria-hidden="true"></i>
				</button>
			</div>
		</div>
		<div id="workBenchTree_Table" class="Information_area"></div>
	</div>
</div>
<!---添加开始----->
<div class="modal fade" id="modal-form-workBnechTree_add" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog" style="width: 32%;">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title addIcon">&nbsp;添加组织机构</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="addworkbenchTreeTypeform"
					name="addworkbenchTreeTypeform" class="form-horizontal">
					<input type="hidden" name="fPid" id="tree_fpid" value="">
					<div class="form-group">
						<label class="col-sm-4 control-label">组织机构名称</label>
						<div class="col-sm-8">
							<input type="text" id="F_NAME" name="fName" class="form-control"
								required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">imgurl地址</label>
						<div class="col-sm-8">
							<input type="text" id="F_IMGURL" name="fImgurl"
								class="form-control" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">前台布局类型</label>
						<div class="col-sm-8">
							<input type="text" id="F_LX" name="fLx" class="form-control"
								required>
						</div>
					</div>
					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-3 display_flex">
							<button class="btn btn-md btn-primary" type="submit">
								<strong>确定</strong>
							</button>
							<button class="btn btn-white m-l-sm" type="button"
								data-dismiss="modal">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 添加类型结束 -->

<!---编辑开始----->
<div class="modal fade" id="modal-form-workBenchTree_edit" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog" style="width: 32%;">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title addIcon">&nbsp;编辑组织机构</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="editworkbenchTreeTypeform"
					name="editworkbenchTreeTypeform" class="form-horizontal">
					<input type="hidden" id="f_worktreeid" name="fId">
					<div class="form-group">
						<label class="col-sm-4 control-label">组织机构名称</label>
						<div class="col-sm-8">
							<input type="text" id="F_NAMEedit" name="fName"
								class="form-control" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">imgurl地址</label>
						<div class="col-sm-8">
							<input type="text" id="F_IMGURLedit" name="fImgurl"
								class="form-control" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">前台布局类型</label>
						<div class="col-sm-8">
							<input type="text" id="F_LXedit" name="fLx" class="form-control"
								required>
						</div>
					</div>
					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-3 display_flex">
							<button class="btn btn-md btn-primary" type="submit">
								<strong>更新</strong>
							</button>
							<button class="btn btn-white m-l-sm" type="button"
								data-dismiss="modal">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- 编辑类型结束 -->


<script type="text/javascript">
;
var view_basedatamanage_workbench_workbenchtree = (function($, window, document, undefined) {
    var _ctx = '${ctx}';
    var treeID = "";
    //创建并设置table属性
    var optIconFunction = function(cell, formatterParams){ //plain text value
        var F_tabulator_ID = cell.getRow().getData().F_ID;
        return "<div class='btn-group '>"
            +"<button class='btn btn-white btn-sm edit' data-id="+ F_tabulator_ID + " data-toggle='modal' data-target='#modal-form-workBenchTree_edit'><i class='fa fa-pencil' ></i> 编辑</button>"
            +"<button class='btn btn-white btn-sm delete' data-id=" + F_tabulator_ID + "><i class='fa fa-trash'></i>  删除</button></div>"
    };
    $("#workBenchTree_Table").tabulator({
        height:"96%",
        layout:"fitColumns",//fitColumns  fitDataFill
        columnVertAlign:"bottom", //align header contents to bottom of cell
        tooltips:false,
        movableColumns:true,
        columns:[
       	{title:"序号",field:"F_ID",align:"center",width:90,formatter:"rownum",frozen:false,sorter:"number"},
        {title:"组织结构名称", field:"F_NAME" ,sorter:"string",editor:false,align:"center",headerSort:false}, //hide this column first 
        {title:"创建时间", field:"fCrdate" ,sorter:"date",editor:false,align:"center",headerSort:false},
        {title:"修改时间", field:"fChdate",sorter:"date",align:"center",editable:false,headerSort:false},
        {title:"操作", fieid:"qssj", width:200,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
        ],
    });
    
    //居中显示添加
    $("#modal-form-workBnechTree_add").on("show.bs.modal",function(){
    	$("#modal-form-workBnechTree_add").validate().resetForm();
    	$("#modal-form-workBnechTree_add").find("input").val("");
    	$(this).css("display","block");
    	var modalHeight = $(window).height() / 2 - $("#modal-form-workBnechTree_add .modal-dialog").height() / 2;
    	$(this).find(".modal-dialog").css({
    		"margin-top":modalHeight
    	});
    });
    
    //居中显示编辑
    $("#modal-form-workBenchTree_edit").on("show.bs.modal", function(){
    	$("#modal-form-workBenchTree_edit").validate().resetForm();
    	$("#modal-form-workBenchTree_edit").find("input").val("");
    	$(this).css("display", "block");
    	var modalHeignt = $(window).height() / 2 - $("#modal-form-workBenchTree_edit .modal-dialog").height() / 2;
    	$(this).find(".modal-dialog").css({
    		"margin-top":modalHeignt
    	});
    });
    
    //验证(添加)
    var workbenchTreeValidate = $("#addworkbenchTreeTypeform").validate({
    	rules:{
    		F_NAME : {
    			required : true,
    		},
    		F_IMGURL : {
    			required : true,
    		},
    		F_LX : {
    			required : true,
    		}
    	},
    	messages : {
    		F_NAME : {
    			required : "请输入组织机构名称"
    		},
    		F_IMGURL : {
    			required : "请输入imgurl地址"
    		},
    		F_LX : {
    			required : "请输入前台布局类型"
    		}
    	},
    	submitHandler : function(form){
    		addformWorkbenchTreeType(form);
    	}
    });
    
    //新增保存
    function addformWorkbenchTreeType(form){
    	$("#tree_fpid").val(treeID);
    	$.ajax({
    		url: _ctx + "/view/basedatamanage/workbench/add_workbenchTree",
    		type: "post",
    		data:$(form).serialize(),
    		success: function(data){
    			if(data.status == "1"){
    				swal({
    					title : data.msg,
			   			text : "添加成功",
			   			type: "success",
			   			showCloseButton : false, 
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
    			});
    			$("#modal-form-workBnechTree_add").modal("hide");
    			 workBench_tree();
    			}else{
    				swal(data.mag,"","error");
    			}
    		},
    		error: function(data){
    			swal(data.mag,"","error");
    		}
    	});
    }
    
    
    
     //验证(编辑)
    var workbenchTreeValidate = $("#editworkbenchTreeTypeform").validate({
    	rules:{
    		F_NAMEedit : {
    			required : true,
    		},
    		F_IMGURLedit : {
    			required : true,
    		},
    		F_LXedit : {
    			required : true,
    		}
    	},
    	messages : {
    		F_NAMEedit : {
    			required : "请输入组织机构名称"
    		},
    		F_IMGURLedit : {
    			required : "请输入imgurl地址"
    		},
    		F_LXedit : {
    			required : "请输入前台布局类型"
    		}
    	},
    	submitHandler : function(form){
    		editforWorkbenchTreeType(form);
    	}
    });
    
    //编辑回显
    $(document).on("click","#workBenchTree_Table button.edit", function(){
    	var id = $(this).data("id");
    	$("#f_worktreeid").val(id);
    	$.ajax({
    		url: _ctx+"/view/basedatamanage/workbench/serch_workdenchTree",
    		type: "post",
    		data:{
    			"bh":id
    		},
    		success:function(result){
    			$("#f_worktreeid").val(result.list[0].fId);
    			$("#F_NAMEedit").val(result.list[0].fName);
    			$("#F_IMGURLedit").val(result.list[0].fImgurl);
    			$("#F_LXedit").val(result.list[0].fLx);
    		}
    	});
    });
    
    //编辑
    function editforWorkbenchTreeType(form){
    	$.ajax({
    		url: _ctx + "/view/basedatamanage/workbench/edit_workbenchTree",
    		type:"post",
    		data:$(form).serialize(),
    		success: function(data){

    			if(data.status == "1"){
    				swal({
    					title : data.msg,
    					text : "",
    					type : "success",
    					showClossButton : false,
    					allowOutsideClick : false,
    					showConfirmButton : false,
    					timer :1000 
    				});
    				$("#modal-form-workBenchTree_edit").modal("hide");
    				workBench_tree();
    			}else{
    				swal("修改失败!", data.msg, "error");
    			}
    		},
    		error: function(data){
    			swal("修改失败！", data.msg, "error");
    		}
    	});
    }
    
    //删除
    $(document).on('click','#workBenchTree_Table button.delete',function() {
            var row = $(this).parents("tr")[0];
            var id = $(this).data("id");
            swal({
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
                url : _ctx + "/view/basedatamanage/workbench/del_WorkbenchTree",
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
                    workBench_tree();
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
                      $('#workBench_tree').treeview({
                          data: result.list,
                          // 数据源
                          highlightSelected: true,
                          //是否高亮选中
                          levels: 4,
                          enableLinks: true,
                          //必须在节点属性给出href属性
                          color: "#4a4747",
                          onNodeSelected: function(event, nodeData) {
                        	  //刷新右侧table
                        	  treeID = nodeData.id;
                        	  RefreshTable(nodeData.id);
                          }
                      });
                      var firstNode = $("#workBench_tree").treeview('findNodes', [result.list[0].id, 'id']);
                      $("#workBench_tree").treeview("selectNode", firstNode); //将第一个节点设置为选中状态
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
  //刷新右侧table
  function RefreshTable(nodeId){
	  $.ajax({
          type: "post",
          url: "${ctx}/view/basedatamanage/workbench/RefreshTable",
          data:{
        	  "nodeId":nodeId,
        	  },
          dataType: "json",
          beforeSend: function() {
              showLoad();
          },
          success: function(result) {
        	  if(result.hasOwnProperty("list")){
	        	  $("#workBenchTree_Table").tabulator("setData",result.list);
        	  }else{
        		  $("#workBenchTree_Table").tabulator("setData",[]);
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
    return {
        //查询                
        pageInit : function(){
            workBench_tree();//加载树
        }
    }
})(jQuery, window, document);
view_basedatamanage_workbench_workbenchtree.pageInit();
</script>