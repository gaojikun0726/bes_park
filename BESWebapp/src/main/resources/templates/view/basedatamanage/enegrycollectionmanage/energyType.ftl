<!-----内容区域---->

<!-- -------------------------------------------------------------------------- -->
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;能耗类型定义列表>>>
		</span>
		<!-- 增加按钮 -->
		<a id="addenergyType" data-toggle="modal"  href="#modal-form-addenergyType" class="btn btn-primary toLeft"> 
		<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加 
		</a>
		<!-- 导入按钮 -->
    <#--没有对应的方法，隐藏按钮-->
        <#--<a id="impenergyType" class="btn btn-primary toLeft" > -->
        <#--<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入 -->
        <#--</a> -->
        <!-- 导出按钮 -->
        <a id="expenergyType"  class="btn btn-primary toLeft" onclick="basedatamanage_enegrycollectionmanage_energyType_page.exp_excel(this)">
        <i class="fa fa-download" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导出
        </a>
		<!-- 搜索框 -->
		<div class="zc_search find">
				 <input type="text" class="find-style"  id="energyTypeInfo" name="energyTypeInfo" placeholder="能源名称、编号">
				 <button id="queryEnergyType" onclick="basedatamanage_enegrycollectionmanage_energyType.searchEnergyType()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
<!---分页列表----->	        
    <div class="ibox" id="EnergyType_ibox" style="height:92%">
         	<#include "/view/basedatamanage/enegrycollectionmanage/energyType_page.ftl"/>
    </div>

<!---分页列表结束----->
<!-----内容区域结束---->


<!---添加能源类型开始-----> 
<div class="modal fade" id="modal-form-addenergyType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加能源类型</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addenergyTypeform" name="addenergyTypeform" class="form-horizontal">
                	<div class="form-group">
                        <label class="col-sm-3 control-label">能源编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fNybh" name="fNybh" placeholder="请输入能源编号"  required class="form-control" onkeypress="return event.keyCode>=48&&event.keyCode<=57" pattern="/[^a-zA-Z]/">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">能源名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fNymc" name="fNymc" placeholder="请输入能源名称"  required class="form-control">
                        </div>
                    </div>                                        
                    <div class="form-group">
                          <label class="col-sm-3 control-label">单价<span class="text-danger">*</span></label>
                          <div class="col-sm-8">
                          	<input type="text" id="fPrice" name="fPrice" placeholder="请输入单价"  required class="form-control">
                          </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">耗煤量<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fCoalAmount" name="fCoalAmount" placeholder="请输入耗煤量"  required class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">二氧化碳<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fCo2" name="fCo2" placeholder="请输入二氧化碳"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">单位<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fUnit" name="fUnit" placeholder="请输入单位"  required class="form-control">
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
<!-- 添加能源类型结束 -->


<!----编辑能源类型--->
<div class="modal fade" id="modal-form-editEnergyType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑能源类型信息</h4>
            </div>
            <div class="modal-body">
            	<form id="editEnergyType" name="editEnergyType" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-4 control-label" for="edit_fNybh">能源编号  <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="hidden" id="edit_fGuid" name="edit_fGuid"  required class="form-control">
						<input type="text" disabled id="edit_fNybh" name="edit_fNybh"  required class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="edit_fNymc">能源名称<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fNymc" name="edit_fNymc"  required class="form-control" onchange="basedatamanage_enegrycollectionmanage_energyType.checkEditRepeat()">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="edit_fPrice">单价<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fPrice" name="edit_fPrice" required class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="edit_fCoalAmount">耗煤量<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fCoalAmount" name="edit_fCoalAmount" required class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="edit_fCo2">二氧化碳<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fCo2" name="edit_fCo2" required class="form-control">
					</div>
				</div>
                <div class="form-group">
                    <label class="col-sm-4 control-label" for="edit_fUnit">单位<span class="text-danger">*</span></label>
                    <div class="col-sm-8">
                        <input type="text" id="edit_fUnit" name="edit_fUnit" required class="form-control">
                    </div>
                </div>
				<div class="form-group m-t-sm">
					<div class="col-sm-6 col-sm-push-4 display_flex">
						<button id="energy_edit_button" class="btn btn-md btn-primary " type="submit">
						<strong>确定</strong>
						</button>
						<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
					</div>
				</div>
            	</form>
            </div>
            
        </div>
    </div>
</div>
<!----编辑能源类型结束--->
<style>

</style>
<script type="text/javascript">
;
var basedatamanage_enegrycollectionmanage_energyType = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	//触发搜索的回车事件
 	$("#energyTypeInfo").focus(function(){
	  $(this).keydown(function (e){
	  if(e.which == "13"){
		  basedatamanage_enegrycollectionmanage_energyType.searchEnergyType();
	    } 
	  })
	});

	//添加能源类型验证
	var energyTypeValidator =$("#addenergyTypeform").validate({
		rules : {
            fNybh : {
				required : true,
				rangelength : [ 1, 8 ],
			},
			fNymc : {
                // isChinese:true,
				required : true,
				rangelength : [ 1, 10 ],
			},
            fPrice : {
                number:true,
                required : true,
                rangelength : [ 1, 10 ],
            },
            fCoalAmount : {
                number:true,
                required : true,
                rangelength : [ 1, 10 ],
            },
            fCo2 : {
                number:true,
                required : true,
                rangelength : [ 1, 10 ],
            },
            fUnit: {
                required : true,
                rangelength : [ 1, 8 ],
            },
		},
		messages : {
			fNybh : {
				required : "请输入能源编号",
				rangelength : jQuery.validator.format("请输入1-8位"),
			},
			fNymc : {
				required : "请输入能源名称",
				minlength : jQuery.validator.format("Enter at least {0} characters")
			},
            fPrice : {
                required : "请输入能源单价",
                minlength : jQuery.validator.format("Enter at least {0} characters")
            },
            fCoalAmount : {
                required : "请输入该能源消耗耗煤量",
                minlength : jQuery.validator.format("Enter at least {0} characters")
            },
            fCo2 : {
                required : "请输入该能源消耗二氧化碳",
                minlength : jQuery.validator.format("Enter at least {0} characters")
            },
            fUnit : {
                required : "请输入能源单位",
                minlength : jQuery.validator.format("Enter at least {0} characters")
            },
		},
		submitHandler : function(form) {
		    checkRepeat(form);
		}
	});


    function checkRepeat (form) {
        var  fNybh = document.getElementById("fNybh").value;

        var  fNymc = document.getElementById("fNymc").value;

        $.ajax({
            url: _ctx + "/view/basedatamanage/enegrycollectionmanage/checkRepeat",
            type: "post",
            data:({
                fNybh:fNybh,
                fNymc:fNymc
            }),
            success: function(result) {
                if (result.msg === "0" && result.data === "0"){
                    addformEnergyType(form);
                }else{
                    swal({
                        title : "错误",// 展示的标题
                        text : "能源编号和能源名称中不允许有重复字段！",// 内容
                        type: "error",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer: 1000
                    });
                }
            },
            error: function() {
                swal( "错误", "error");
            }
        });
    }



    function checkRepeat (form) {
        var  fNybh = document.getElementById("fNybh").value;

        var  fNymc = document.getElementById("fNymc").value;

        $.ajax({
            url: _ctx + "/view/basedatamanage/enegrycollectionmanage/checkRepeat",
            type: "post",
            data:({
                fNybh:fNybh,
                fNymc:fNymc
            }),
            success: function(result) {
                if (result.msg === "0" && result.data === "0"){
                    addformEnergyType(form);
                }else{
                    swal({
                        title : "错误",// 展示的标题
                        text : "能源编号和能源名称中不允许重复！",// 内容
                        type: "error",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer: 1000
                    });
                }
            },
            error: function() {
                swal( "错误", "error");
            }
        });
    }

    function checkEditRepeat (form) {

        var  fNymc = document.getElementById("edit_fNymc").value;

        $.ajax({
            url: _ctx + "/view/basedatamanage/enegrycollectionmanage/checkEnergyEditRepeat",
            type: "post",
            data:({
                fNymc:fNymc
            }),
            success: function(result) {
                if (result.data === "1"){
                    swal({
                        title : "错误",// 展示的标题
                        text : "能源名称中不允许有重复字段！",// 内容
                        type: "error",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer: 1000
                    });

                    // $("#energy_edit_button").attr("disabled", true);
                    // document.getElementById("energy_edit_button").disabled=true;
                }else {
                    // document.getElementById("energy_edit_button").disabled=false;
                    // $("#energy_edit_button").removeAttrs("disabled")
                }
            },
            error: function() {
                swal( "错误", "error");
            }
        });
    }


    //新增保存
	function addformEnergyType(form) {
	     $.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/add_EnergyType",
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
		            $('#modal-form-addenergyType').modal('hide');//关闭编辑窗口
		            $("#EnergyTypeTable").tabulator("addRow",data.data);
		            //重新加载列表及分页
		            basedatamanage_enegrycollectionmanage_energyType.searchEnergyType()
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
 	}
 
	//居中显示
 	$('#modal-form-addenergyType').on('show.bs.modal', function () {
 	// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
    $(this).css('display', 'block');  
    var modalHeight=$(window).height() / 2 - $('#modal-form-addenergyType .modal-dialog').height() / 2;  
    $(this).find('.modal-dialog').css({  
           'margin-top': modalHeight  
    });
    })
    
    	//居中显示（编辑）
 	$('#modal-form-editEnergyType').on('show.bs.modal', function () {
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#modal-form-editEnergyType .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
	})
    
    //关闭模态框清空表单值
	$("#modal-form-addenergyType").on('hidden.bs.modal',function(event) {
			$(this).find("input").val("");
			energyTypeValidator.resetForm();

	});
	
	
	//删除数据
	$(document).on('click','#EnergyTypeTable button.delete',function() {
			var row = $(this).parents("tr")[0];
			var id = $(this).data("id");
			swal(
			{
			title : "您确定要删除吗?",
			text : "能源类型信息删除后将不可恢复!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "确定",
			closeOnConfirm : false
			},
		function() {
			// row.className="animated bounceOut";
			setTimeout(function() {
			$.ajax({
				url : _ctx + "/view/basedatamanage/enegrycollectionmanage/energyType_del",
				type : "post",
				data : {"fGuid" : id
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
					// window.location.href="${ctx }/view/user/user"; 
					//重新加载列表及分页
		            basedatamanage_enegrycollectionmanage_energyType.searchEnergyType()
				} else {
					swal({
			        	title : data.msg,// 展示的标题
			   			text : "",// 内容
			   			type: "error",
			   			showCloseButton : false, // 展示关闭按钮
			   			allowOutsideClick : false,
			   			showConfirmButton : false,
			   			timer: 1000
			   		});
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
	
 	//表单验证
	$("#editEnergyType").validate({
        rules : {
            edit_fNybh : {
                required : true,
                rangelength : [ 1, 8 ],
            },
            edit_fNymc : {
                isChinese:true,
                required : true,
                rangelength : [ 1, 10 ],
            },
            edit_fPrice : {
                number:true,
                required : true,
                rangelength : [ 1, 10 ],
            },
            edit_fCoalAmount : {
                number:true,
                required : true,
                rangelength : [ 1, 10 ],
            },
            edit_fCo2 : {
                number:true,
                required : true,
                rangelength : [ 1, 10 ],
            },
            edit_fUnit: {
                required : true,
                rangelength : [ 1, 8 ],
            },
        },
        messages : {
            edit_fNybh : {
                required : "请输入能源编号",
                rangelength : jQuery.validator.format("请输入1-8位"),
            },
            edit_fNymc : {
                required : "请输入能源名称",
                minlength : jQuery.validator.format("Enter at least {0} characters")
            },
            edit_fPrice : {
                required : "请输入能源单价",
                minlength : jQuery.validator.format("Enter at least {0} characters")
            },
            edit_fCoalAmount : {
                required : "请输入该能源消耗耗煤量",
                minlength : jQuery.validator.format("Enter at least {0} characters")
            },
            edit_fCo2 : {
                required : "请输入该能源消耗二氧化碳",
                minlength : jQuery.validator.format("Enter at least {0} characters")
            },
            edit_fUnit : {
                required : "请输入能源单位",
                minlength : jQuery.validator.format("Enter at least {0} characters")
            },
        },

  	 submitHandler: function(form) {
         // checkEditRepeat(form);
         editEnergyType(form);
     }
 	});

 	//编辑能源类型
	function editEnergyType(form) {
        var  fNymc = document.getElementById("edit_fNymc").value;
        var  fGuid = document.getElementById("edit_fGuid").value;
        $.ajax({
            url: _ctx + "/view/basedatamanage/enegrycollectionmanage/checkEnergyEditRepeat",
            type: "post",
            data:({
                fNymc:fNymc,
                fGuid:fGuid
            }),
            success: function(result) {
                if (result.data === "1"){
                    swal({
                        title : "错误",// 展示的标题
                        text : "能源名称中不允许有重复字段！",// 内容
                        type: "error",
                        showCloseButton : false, // 展示关闭按钮
                        allowOutsideClick : false,
                        showConfirmButton : false,
                        timer: 1000
                    });
                }else {
                    $.ajax({
                        url: _ctx + "/view/basedatamanage/enegrycollectionmanage/edit_EnergyType",
                        type: "post",
                        data:({
                            fGuid: $("#edit_fGuid").val(),
                            fNybh: $("#edit_fNybh").val(),
                            fNymc: $("#edit_fNymc").val(),
                            fPrice: $("#edit_fPrice").val(),
                            fCoalAmount: $("#edit_fCoalAmount").val(),
                            fCo2: $("#edit_fCo2").val(),
                            fUnit : $("#edit_fUnit").val(),
                        }),
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
                                $('#EnergyTypeTable').tabulator("updateRow",basedatamanage_enegrycollectionmanage_energyType_page.get_curRow(),data.data);
                                setTimeout(function(){
                                    $('#modal-form-editEnergyType').modal('hide');//关闭编辑窗口
                                },1000)
                            } else{
                                swal("修改失败!", data.msg, "error");
                            }
                        },
                        error: function(data) {
                            swal("修改失败!", data.msg, "error");
                        }
                    });
                }
            },
            error: function() {
                swal( "错误", "error");
            }
        });
	}
 	
 	//验证在模态框出现前加载编辑
 	$("#modal-form-editEnergyType").on('show.bs.modal', function(event) {
   		var button = $(event.relatedTarget);
   		var id = button.data("id");//获取能源类型编号
   		$.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/queryEnergyType",
	       type: "post", 
	       data:{     
	 			"fGuid":id
	 		},
	       success: function(result) {
	           console.log(result)
	    	 $("#edit_fGuid").val(result.data.fGuid);
	         $("#edit_fNybh").val(result.data.fNybh);
	         $("#edit_fNymc").val(result.data.fNymc);
	         $("#edit_fPrice").val(result.data.fPrice);
	         $("#edit_fCoalAmount").val(result.data.fCoalAmount);
	         $("#edit_fCo2").val(result.data.fCo2);
	         $("#edit_fUnit").val(result.data.fUnit);
	         }
   		});  
 	});
 	return {
 		//查询				
 		searchEnergyType : function (datas) {
 			var keywords = $("#energyTypeInfo").val();
 			$.ajax({
 				url : _ctx + '/view/basedatamanage/enegrycollectionmanage/getEnergyTypeList',
 				type : "post",
 				data : ({     
 					"keywords":keywords
 			 		}),
 		        beforeSend: function () { 
 		        	showLoad();	             
 		            },
 				success : function(data) {
 					$('#EnergyType_ibox').html(data);
 				},
 	            complete: function () {
 	            	hiddenLoad();
 	            },
 				error : function(data) {
 					toastr.error('', '查询失败');
 				}
 				});
 		},
 		pageInit : function(){
 			basedatamanage_enegrycollectionmanage_energyType.searchEnergyType();
 		},
        checkEditRepeat: function () {
            editEnergyType();
        }

 	}
})(jQuery, window, document);
basedatamanage_enegrycollectionmanage_energyType.pageInit();
</script>