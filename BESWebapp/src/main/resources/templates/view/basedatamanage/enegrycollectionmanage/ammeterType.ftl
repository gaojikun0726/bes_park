<!-----内容区域---->

<!-- <div class="ibox-content m-b-sm border-bottom" style="width:100%;height:7%" id="AmmeterType"> 
   <div class="input-group"  style="height:30px;width:100%;">
        <div style="width: 50%;float: left;position: relative;padding-right: 15px;" >
           <a id="addAmmeterType" class="btn btn-primary" data-toggle="modal"  href="#modal-form-addAmmeterType">
                	增加 <i class="fa fa-plus"></i>
            </a>
            <a id="importAmmeterType" class="btn btn-primary" data-toggle="modal"  href="#modal-form-importAmmeterType" >
                	导入 <i class="fa fa-mail-reply"></i>
            </a>
            <a id="exportAmmeterType" class="btn btn-primary" data-toggle="modal"  href="#modal-form-exportAmmeterType">
                	导出 <i class="fa fa-mail-forward"></i>
            </a>
    	</div>
        <div style="width: 25%;float: right;position: relative;padding-right: 0px;padding-left: 15px;">
            <input type="text" class="input-sm form-control" style="width: calc(100% - 60px);" id="AmmeterTypeInfo" name="AmmeterTypeInfo" value="" placeholder="能源名称、编号"> 
			<span class="input-group-btn"  style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryAmmeterType" onclick="dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType()">
                	<i class="fa fa-search"></i> 搜索
                </button>
            </span>
       	</div>
    	<div class="zc_search find">
				<div class="zc_search_form">
				<input type="text" id="AmmeterTypeInfo" name="AmmeterTypeInfo" placeholder="通讯协议类型、编号...">
				<button  id="queryAmmeterType" onclick="dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType()" ></button>
				</div>
		</div> 
   </div>
</div> -->
<style>

    #import_form_ammeter_btn .form-control{
        height: 28px!important;
        line-height: 1.8;
    }
    #import_form_ammeter_btn .btn-primary{
        width:6.5vw!important;
        height: 3.85vh!important;
    }
</style>
<!--------------------------------------------------------------------------------------------------->
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;电表类型定义列表>>>
		</span>
		<!-- 增加按钮 -->
		<a id="addAmmeterType" data-toggle="modal"  href="#modal-form-addAmmeterType" class="btn btn-primary toLeft"> 
		<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加 
		</a>
        <!-- 导入按钮 -->
        <a id="importbesrate"  onclick="dataAnalysis_batteryOperations_batteryAlarm.impReport()"  href="javascript:void(-1);" class="btn btn-primary toLeft">
            <i class="fa fa-upload" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入
        </a>
        <!-- 导出按钮 -->
        <a id="exportbesrate"   onclick="dataAnalysis_ammeterType_page.exp_excel(this)"  href="javascript:void(-1);" class="btn btn-primary toLeft">
            <i class="fa fa-download"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导出
        </a>
<#--        <!-- 导出excel按钮 &ndash;&gt;-->
<#--        <button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;margin-left: 5px"-->
<#--                onclick="dataAnalysis_batteryOperations_batteryAlarm.impReport()">-->
<#--            <i class="fa fa-upload" aria-hidden="true"></i>&nbsp;导入-->
<#--        </button>-->
<#--        <!-- 导出excel按钮 &ndash;&gt;-->
<#--        <button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;margin-left: 5px"-->
<#--                onclick="dataAnalysis_ammeterType_page.exp_excel(this)">-->
<#--            <i class="fa fa-download" aria-hidden="true"></i>&nbsp;导出-->
<#--        </button>-->
		<!-- 搜索框 -->
		<div class="zc_search find">
				 <input type="text" class="find-style"  id="AmmeterTypeInfo" name="AmmeterTypeInfo" placeholder="电表类型、编号...">
				 <button id="queryAmmeterType" onclick="dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
<!---分页列表----->	        
    <div class="ibox" id="AmmeterType_ibox" style="height:92%">
         	<#include "/view/basedatamanage/enegrycollectionmanage/ammeterType_page.ftl"/>
    </div>


<!---添加能源类型开始-----> 
<div class="modal fade" id="modal-form-addAmmeterType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加电表类型</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addAmmeterTypeform" name="addAmmeterTypeform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">类型编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fLxbh" name="fLxbh" placeholder="请输入类型编号"  required class="form-control">
                        </div>
                    </div>                                        
                    <div class="form-group">
                          <label class="col-sm-3 control-label">类型名称</label>
                          <div class="col-sm-8">
                          	<input type="text" id="fLxmc" name="fLxmc" placeholder="请输入类型名称"  required class="form-control">
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
<div class="modal fade" id="modal-form-editAmmeterType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑电表类型</h4>
            </div>
            <div class="modal-body">
            	<form id="editAmmeterType" name="editAmmeterType" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-4 control-label" for="edit_fLxbh">类型编号  <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fLxbh" name="edit_fLxbh"   class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="edit_fLxmc">类型名称<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fLxmc" name="edit_fLxmc"  required class="form-control">
					</div>
				</div>
				<div class="form-group m-t-sm">
					<div class="col-sm-6 col-sm-push-4 display_flex">
						<button class="btn btn-md btn-primary " type="submit">
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

<!-- 右侧内容模块模块end -->
<!-- 上传模态框 -->
<div class="modal fade import-Model" id="ammeterType_import_model" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="importAmmeterModalLabel">导入报表</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="alert alert-danger" style="font-size:13px;" role="alert">
                            <strong>注 意：</strong><br>
                            &emsp;&emsp;为保证您的数据正确导入，请先下载模板并在模板上输入所需导入数据（示例数据可删除）
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="input-group">
                            <span class="input-group-addon">模板下载</span>
                            <div class="input-radius" style="border: none;box-shadow: inherit;">
		                         <span class="input-group-addon">
		                         <a href="javascript:void(0);" id="btn_exp" onclick="dataAnalysis_batteryOperations_batteryAlarm.btn_exp()">下载模板</a>
		                         </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" id="import_form_ammeter_btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="ammeterTypeImpExcel" >
                            <input id="ammeterTypeInputFile"  type="file" name="file"  class="file-loading">
                        </form>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="dataAnalysis_batteryOperations_batteryAlarm.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
var dataAnalysis_batteryOperations_batteryAlarm = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	//触发搜索的回车事件
	$("#AmmeterTypeInfo").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType();
			} 
		})
	});
	//居中显示
	$('#modal-form-addAmmeterType').on('show.bs.modal', function () {
	// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
		$(this).css('display', 'block');  
		var modalHeight=$(window).height() / 2 - $('#modal-form-addAmmeterType .modal-dialog').height() / 2;  
		$(this).find('.modal-dialog').css({  
       		'margin-top': modalHeight  
		});
	})

	//居中显示（编辑）
	$('#modal-form-editAmmeterType').on('show.bs.modal', function () {
    	$(this).css('display', 'block');  
    	var modalHeight=$(window).height() / 2 - $('#modal-form-editAmmeterType .modal-dialog').height() / 2;  
    	$(this).find('.modal-dialog').css({  
        	'margin-top': modalHeight  
    	}); 
	})

	//关闭模态框清空表单值
	$("#modal-form-addAmmeterType").on('hidden.bs.modal',function(event) {
		$(this).find("input").val("");
		AmmeterTypeValidator.resetForm();
	});

	//删除数据
	$(document).on('click','#AmmeterTypeTable button.delete',function() {
		var row = $(this).parents("tr")[0];
		var id = $(this).data("id");
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
		// row.className="animated bounceOut";
		
		$.ajax({
			url : _ctx + "/view/basedatamanage/enegrycollectionmanage/del_AmmeterType",
			type : "post",
			data : {"fLxbh" : id},
			success : function(data) {
				if (data.status == '1') {
				swal( {title: data.msg,
			            text: "",
			            type: "success",
			            showCloseButton:false,
			            allowOutsideClick:false,
			            showConfirmButton: false,
			            timer:1000});
				// window.location.href="${ctx }/view/user/user"; 
				//重新加载列表及分页
	            dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType()
			} else {
				swal("删除失败!",data.msg,"error");
			}
			},
				error : function(data) {
				swal("删除失败!",data.msg,"error");
			}
			});
			
			});
	});

	//表单验证
	$("#editAmmeterType").validate({
	 submitHandler: function(form) {
		 editAmmeterType(form);
	 }
	});
	//验证在模态框出现前加载编辑
 	$("#modal-form-editAmmeterType").on('show.bs.modal', function(event) {
   		var button = $(event.relatedTarget);
   		var id = button.data("id");//获取能源类型编号
   		$.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/getAmmeterType",
	       type: "post", 
	       data:{     
	 			"fLxbh":id
	 		},
	       success: function(result) {
	         $("#edit_fLxbh").val(result.data.fLxbh);
	         $("#edit_fLxmc").val(result.data.fLxmc);
	         }
   		});  
 	});
	
;

	//添加能源类型验证
	var AmmeterTypeValidator =$("#addAmmeterTypeform").validate({
		rules : {
			fLxbh : {
				required : true,
				rangelength : [ 1, 8 ],
			},

			fLxmc : {
				required : true,
				rangelength : [ 1, 20 ],
			}
		},
		messages : {
			fLxbh : {
				required : "请输入能源编号",
				rangelength : jQuery.validator.format("请输入1-8位"),
			},
			fLxmc : {
				required : "请输入能源名称",
				minlength : jQuery.validator.format("Enter at least {0} characters")
			},
		},
		submitHandler : function(form) {
			addformAmmeterType(form);
		}
	});

    //fileinput 上传插件初始化
    function initFileinput(){
        $("#ammeterTypeInputFile").fileinput({
            language: 'zh', //设置语言
            uploadUrl: '${ctx}/view/basedatamanage/enegrycollectionmanage/besAmmeterTypeFileUpload',//上传请求路径
            allowedFileExtensions : ['xls', 'xlsx'],//接收的文件后缀,
            showUpload: true, //是否显示上传按钮
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            maxFileSize:3072,//最大单个文件上传大小
            maxFileCount:1,//最大上传个数
            showUpload:false,//是否显示上传按钮
            showBrowse:true,//是否显示浏览按钮
            showPreview:false,//是否显示预览区域
            autoReplace:true,//是否自动替换
            showRemove:true,//是否显示移除按钮
            uploadExtraData:function(id,index){
                return {"fold":"ammeterTypeImportExcel"};
            },//区分不同的模块：fold：文件夹
            //uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取
            deleteUrl: "${ctx}/file/fileDelete?id="//删除文件的路径
        }).on("fileuploaded",function(event, data, previewId, index){
            dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType();
            clearForm();//清空form表单
            var res = data.response;
            if(res.status== '1')
            {
                swal({title: res.msg,type: "success",showCloseButton: true});
            }
            else
            {
                swal({title: res.msg,type: "error",showCloseButton: true});
                if(res.hasOwnProperty("list"))
                {
                    var errorList = JSON.stringify(res.list);
                    var data = {
                        errorString : errorList,
                    };
                    var _url = "${ctx}/view/basedatamanage/enegrycollectionmanage/exportErrorExcel";
                    var exportName = "电表类型定义导入错误报告";
                    doExp(_url,exportName,"${ctx}",data);//导出excel并下载

                }

            }

        }).on("filebatchuploadsuccess",function(){
            clearForm();//清空form表单
        });
    }


    //清空上传文件表单form 关闭模态框 并提示
    function clearForm(){
        $("#ammeterTypeInputFile").fileinput("destroy");//销毁fileinput删除上传文件缓存
        $("#ammeterType_import_model").modal("hide");//关闭模态框
    }
	
    //新增保存
	function addformAmmeterType(form) {
	     $.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/add_AmmeterType",
	       type: "post",
	       data:$(form).serialize(),
	       success: function(data) {
	         if (data.status == '1') {
	        	 swal({ 
			            title: data.msg,
			            text: "",
			            type: "success",
			            showCloseButton:false,
			            allowOutsideClick:false,
			            showConfirmButton: false,
			            timer:1000
			        });
	        	   console.log(data)
		            $('#modal-form-addAmmeterType').modal('hide');//关闭编辑窗口
		            //重新加载列表及分页
		            dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType()
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
 	}
    //编辑
	function editAmmeterType(form) {

		$.ajax({
			url : _ctx + "/view/basedatamanage/enegrycollectionmanage/edit_AmmeterType",
			type : "post",
			data:({
	     			fLxbh: $("#edit_fLxbh").val(),
	     			fLxmc: $("#edit_fLxmc").val(),
	     		}),
			success : function(data) {

				if (data.status === '1') {

					swal({ 
			            title: data.msg,
			            text: "",
			            type: "success",
			            showCloseButton:false,
			            allowOutsideClick:false,
			            showConfirmButton: false,
			            timer:1000
			        });
					$('#AmmeterTypeTable').tabulator(
							"updateRow",
							dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType(), {
								fLxbh:data.data.fLxbh,fLxmc:data.data.fLxmc
							});
						$('#modal-form-editAmmeterType').modal('hide');//关闭编辑窗口
				} else {
					swal("", data.msg, "error");
				}
			},
			error : function(data) {
				swal("", data.msg, "error");
			}
		});
	}

    //居中显示
    $('#ammeterType_import_model').on('show.bs.modal', function () {
        // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#ammeterType_import_model .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
    })
 	
 	return {
 		//查询				
 		searchAmmeterType : function (datas) {

 			var keywords = $("#AmmeterTypeInfo").val();
 			//$("#AmmeterType_ibox").load(_ctx + '/view/dataCenter/getAmmeterTypeList?keywords='+keywords);
 			$.ajax({
 				url : _ctx + '/view/basedatamanage/enegrycollectionmanage/getAmmeterTypeList',
 				type : "post",
 				data : ({     
 					"keywords":keywords
 			 		}),
 				success : function(data) {
 					$('#AmmeterType_ibox').html(data);
 				},
 				error : function(XMLHttpRequest,textStatus, errorThrown) {
 					toastr.error('', '查询失败');
 				}
 				});
 		},
        impReport :function(){
            //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
            $("#ammeterType_import_model").modal("show");
            initFileinput();//初始化fileinput
        },
        //下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
        btn_exp : function(){
            var fname = "电表类型定义报表模板.xls";
            var path = "file\\expExcel\\电表类型定义模板.xls";
	    //FileDownload("${ctx}/file/newFileDownload",fname,path);
            FileDownload(_ctx + filePath.loadPath,fname,path);
        },
        //导入数据按钮
        btn_import:function(){
            var filepath = $("#ammeterTypeInputFile").val();
            if(filepath != ""){
                $("#ammeterTypeInputFile").fileinput("upload");//上传方法
            }else{
                swal({title: "请上传数据文件!",type: "warning",showCloseButton: true});
            }

        },
 		
 		pageInit : function(){
 			dataAnalysis_batteryOperations_batteryAlarm.searchAmmeterType();
 		}
 	}
})(jQuery, window, document);
dataAnalysis_batteryOperations_batteryAlarm.pageInit();
</script>