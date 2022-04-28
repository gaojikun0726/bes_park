

<!-- 组织机构树模块 -->
<div class="leftarea information_left">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择组织机构>>>
		</span>
	</div>
	<div id="tree_sbb" class="Information_area"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;设备信息列表>>>
			</span>
			<!-- 增加按钮 -->
			<a   href="javascript:void(-1);" onclick="sbbmanage_sbb.sbb_show_addmodal()" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;增加
			</a>
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="sbbkeywords" name="sbbkeywords" placeholder="设备编号/id/名称/类别/公司">
				  <button id="querysbbBtn"onclick="sbbmanage_sbb.search_sbb()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="sbbTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->

<!---添加组织机构信息开始----->
<div class="modal fade" id="modal-form-addsbb" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp; 注册用户</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addsbb" name="add" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="sbbh_add" name="sbbh_add" placeholder="请输入设备编号"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="sbmc_add" name="sbmc_add" placeholder="请输入设备名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备类别<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text"  id="sblb_add" name="sblb_add" placeholder="请输入设备类别" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备生产方<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text"  id="gldw_add" name="gldw_add" placeholder="请输入设备公司" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">组织机构编码<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="sbb_add_f_zzjgbh" name="sbb_add_f_zzjgbh" readonly="readonly" placeholder="请输入组织机构编号" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">组织机构名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                        	<input type="text" id="sbb_add_f_company" readonly="readonly" name="sbb_add_f_company" placeholder="请输入组织机构名称" class="form-control">
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
<div class="modal fade" id="editsbbFormTable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp; 编辑设备信息</h4>
            </div>
            <div class="modal-body">
            	<form id="editsbbForm" name="editsbbForm" class="form-horizontal">
                    <input type="hidden" id="id_edit" readonly="readonly" name="id_edit"   required class="form-control">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="sbbh_edit" name="sbbh_edit" placeholder="请输入设备编号"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="sbmc_edit" name="sbmc_edit" placeholder="请输入设备名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备类别<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text"  id="sblb_edit" name="sblb_edit" placeholder="请输入设备类别" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">设备生产方<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text"  id="gldw_edit" name="gldw_edit" placeholder="请输入设备公司" class="form-control">
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
 var sbbmanage_sbb =  (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var sbb_idyhbh = cell.getRow().getData().id;
		return "<div class='btn-group '><button class='btn btn-white btn-sm edit' data-id="+ sbb_idyhbh + " data-toggle='modal' data-target='#editsbbFormTable'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + sbb_idyhbh + "><i class='fa fa-trash'></i>  删除</button></div>"

	};

	//创建并设置table属性
	$("#sbbTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		selectable:1,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",frozen:false,align:"center",headerSort:false,tooltip:false,tooltipsHeader:false},
		{title:"设备编号", field:"sbbh", sorter:"string",align:"center",editor:false,headerSort:false}, //never hide this column
		{title:"设备名称", field:"sbmc",sorter:"string",align:"center",editor:false,headerSort:false}, //hide this column first
		//{title:"公司编码", field:"f_zzjgbh", width:120,sorter:"string",editor:false,headerSort:false},
		{title:"设备类别", field:"sblb",sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"生产方", field:"gldw", sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"创建时间", field:"fCrdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"fChdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",width:250,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
        	var choiseNode = $('#tree_sbb').treeview('findNodes', [ _curRow.getData().f_zzjgbh, 'id']);
        	$('#tree_sbb').treeview('searchByNode', choiseNode);//搜索设置高亮
    	},
	});

	$(window).resize(function(){
		$("#sbbTable").tabulator("redraw");
	});


	//Clear table on "Empty the table" button click
	$("#clearZzjg").click(function(){
	    $("#sbbTable").tabulator("clearData");
	});

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
		            $('#tree_sbb').treeview({
		                data: result.list,         // 数据源
		                highlightSelected: true,    //是否高亮选中
		                levels : 4,
		                enableLinks : true,//必须在节点属性给出href属性
		                color: "#4a4747",
		               onNodeSelected: function (event, nodeData) {
		               $('#tree_sbb').treeview('clearSearch');//清除搜索选中高亮
		               		_zzjgbh = nodeData.id;
		               		_zzjgJs = nodeData.js;
		                    $.ajax({
		                	    url: _ctx + "/view/EsSbbController/selectByPrimarykeywords",
		                	    contentType: "application/json; charset=utf-8",
		                	    type: "get",
		                	    data: {
		                	    	zzjgbh:nodeData.id
		                	    },
		                	    beforeSend: function () {
		            				showLoad();
		            			},
								success: function(result) {
									if(result.hasOwnProperty("list")){
							            $("#sbbTable").tabulator("setData", result.list);
										}else{
							            $("#sbbTable").tabulator("setData", []);
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
		            $("#tree_sbb").treeview('collapseAll');
		             var firstNode = $("#tree_sbb").treeview('findNodes',[result.list[0].id,'id']);
		            $("#tree_sbb").treeview('expandNode',firstNode);
		        	$("#tree_sbb").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
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
$("#sbbkeywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  sbbmanage_sbb.search_sbb();//触发该事件
					    }
					  })
					});
	//添加表单验证
    var sbbValidator = $("#addsbb").validate({
    	rules: {
            sbbh_add: {
                required: true,
                isNormal:true,
                rangelength: [2, 20],
            },
            sbmc_add: {
                required: true,
                isNormal:true,
                rangelength: [2, 20],
            },
            sblb_add: {
                required: true,
                isNormal:true,
                rangelength: [2, 20],
            },
            gldw_add: {
                required: true,
                isNormal:true,
                rangelength: [2, 20],
            }

        },
        messages: {
            sbbh_add: {
               required: "请填写设备编号",
               minlength: jQuery.validator.format("Enter at least {0} characters")
           },
            sbmc_add: {
                required: "请填写设备名称",
                minlength: jQuery.validator.format("Enter at least {0} characters")
            },
            sblb_add: {
                required: "请填写设备类别",
                minlength: jQuery.validator.format("Enter at least {0} characters")
            },
            gldw_add: {
                required: "请填写设备生产方",
                minlength: jQuery.validator.format("Enter at least {0} characters")
            }

       },
	     submitHandler: function (form) {
	         addform_sbb(form);
	     }
 	});

    //新增保存
	function addform_sbb(form) {
	       $.ajax({
		      url: _ctx + "/view/EsSbbController/insertSelective",
		      type: "post",
		      contentType: "application/json; charset=utf-8",
		      data:JSON.stringify({
                  sbbh:$("#sbbh_add").val(),
                  sbmc:$("#sbmc_add").val(),
                  sblb:$("#sblb_add").val(),
                  gldw:$("#gldw_add").val(),
                  fZzjgbh:$("#sbb_add_f_zzjgbh").val(),
                  fYqbh:$("#sbb_add_f_company").val(),
		 	}),
		      beforeSend: function () {
  				showLoad();
  			},
			  success: function(result) {
		         if (result.status == '1') {
		            $('#modal-form-addsbb').modal('hide');//关闭编辑窗口
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
			            $('#sbbTable').tabulator("addRow", {
                            sbbh:result.data.sbbh,
                            sbmc:result.data.sbmc,
                            sblb:result.data.sblb,
                            gldw:result.data.gldw,
                            fZzjgbh:result.data.fZzjgbh,
                            fYqbh:result.data.fYqbh,
                            fCrdate:result.data.fCrdate,
                            fChdate:result.data.fChdate

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
 	$('#modal-form-addsbb').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
 		//模态框拖动********************
		$(this).draggable({
			handle:".modal-header"
		});
		$(this).css("overflow","hidden");
		//*************************************
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#modal-form-addsbb .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
 	//居中显示（编辑）
 	$('#editsbbFormTable').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#editsbbFormTable .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
	//关闭模态框清空表单值
    $("#modal-form-addsbb").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        sbbValidator.resetForm();
    });

    //删除数据
    $(document).on('click','#sbbTable button.delete', function () {
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
			          url: _ctx + "/view/EsSbbController/deleteByPrimaryKey",
			          contentType: "application/json; charset=utf-8",
			          type: "post",
			          data:JSON.stringify({
			        			id:id
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
		              		$("#sbbTable").tabulator("deleteRow", _curRow);
		              		var getsbbTable = $("#sbbTable").tabulator("getData");
		              		$("#sbbTable").tabulator("setData", getsbbTable);
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
    $("#editsbbFormTable").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        sbbValidatoredit.resetForm();
    });
  //编辑用户信息表单验证
  var sbbValidatoredit = $("#editsbbForm").validate({
	 	rules: {
	 		sbb_edit_f_name: {
                required: true,
                isNormal:true,
                rangelength: [2, 20],
            },
            sbb_edit_f_phone: {
          		isPhone:true,
			rangelength:[11,11]
            },
            sbb_edit_f_email: {
               isEmail: true
            }
        },
        messages: {
        	sbb_edit_f_name: {
               required: "请填写您的真实姓名",
               minlength: jQuery.validator.format("Enter at least {0} characters")
           },
           sbb_edit_f_phone: {
               		rangelength : "必须输入11位手机号码，请重试！",
               		isPhone: "请输入正确的手机号码"
            },
            sbb_edit_f_email: {
               	isEmail: "请输入正确的邮箱"

           }
       },
    submitHandler: function(form) {
      editsbbForm(form);
    }
  });

  function editsbbForm(form) {
    $.ajax({
      url: _ctx + "/view/EsSbbController/updateByPrimaryKey",
      type: "post",
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify ({
      		id: $("#id_edit").val(),
          sbbh: $("#sbbh_edit").val(),
          sbmc: $("#sbmc_edit").val(),
          sblb: $("#sblb_edit").val(),
          gldw: $("#gldw_edit").val()
      }),
      beforeSend: function () {
			showLoad();
		},
      success: function(result) {
			if (result.status == '1') {
              $('#editsbbFormTable').modal('hide');//关闭编辑窗口
              swal({
	 	        	title : result.msg,// 展示的标题
	 	   			text : "",// 内容
	 	   			type: "success",
	 	   			showCloseButton : false, // 展示关闭按钮
	 	   			allowOutsideClick : false,
	 	   			showConfirmButton : false,
	 	   			timer: 1000
	 	   		});
	         $('#sbbTable').tabulator("updateRow",_curRow, {
                 id:$("#id_edit").val(),
                 sbmc:$("#sbmc_edit").val(),
                 sblb:$("#sblb_edit").val(),
                 gldw:$("#gldw_edit").val(),
                 fChdate: $("#fChdate_edit").val()
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
  $("#editsbbFormTable").on('show.bs.modal', function(event) {
    var id = _curRow.getData().id;
  //模态框拖动********************
	$(this).draggable({
		handle:".modal-header"
	});
	$(this).css("overflow","hidden");
	//*************************************
    $.ajax({
	        url: _ctx + "/view/EsSbbController/findsbblId",
	        type: "post",
            contentType: "application/json; charset=utf-8",
	        data:JSON.stringify({
	 			id:id
	 		}),
	 		beforeSend: function () {
 				showLoad();
 			},
	       success: function(result) {
	         $("#id_edit").val(result.id);
	         $("#sbbh_edit").val(result.sbbh);
	         $("#sbmc_edit").val(result.sbmc);
	         $("#sblb_edit").val(result.sblb);
	         $("#gldw_edit").val(result.gldw);
	         },
		    complete: function () {
				hiddenLoad();
			},
    });
  });
  return {
		//搜索
		 search_sbb: function(){
			var sbbkeywords = $("#sbbkeywords").val();
			$("#sbbTable").tabulator("setData", _ctx+'/view/EsSbbController/selectbuyjiji?sbbkeywords='+sbbkeywords);
		},
		//验证增加模态框是否弹出
		 sbb_show_addmodal: function() {
	       var node = $('#tree_sbb').treeview('getSelected');
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
					$('#modal-form-addsbb').modal('show');
                    $("#sbb_add_f_zzjgbh").val(node[0].id);
                    $("#sbb_add_f_company").val(node[0].text);
					}
			    }
		  }
 })(jQuery, window, document);
 </script>
