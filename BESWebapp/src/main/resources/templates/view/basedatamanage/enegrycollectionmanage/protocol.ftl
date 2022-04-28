<!-----内容区域---->

<!-- <div class="ibox-content m-b-sm border-bottom" style="width:100%;height:7%" id="Protocol"> 
   <div class="input-group"  style="height:30px;width:100%;">
        <div style="width: 50%;float: left;position: relative;padding-right: 15px;" >
           <a id="addProtocol" class="btn btn-primary" data-toggle="modal"  href="#modal-form-addProtocol">
                	增加 <i class="fa fa-plus"></i>
            </a>
            <a id="importProtocol" class="btn btn-primary" data-toggle="modal"  href="#modal-form-importProtocol" >
                	导入 <i class="fa fa-mail-reply"></i>
            </a>
            <a id="exportProtocol" class="btn btn-primary" data-toggle="modal"  href="#modal-form-exportProtocol">
                	导出 <i class="fa fa-mail-forward"></i>
            </a>
    	</div>
        <div style="width: 25%;float: right;position: relative;padding-right: 0px;padding-left: 15px;">
            <input type="text" class="input-sm form-control" style="width: calc(100% - 60px);" id="ProtocolInfo" name="ProtocolInfo" value="" placeholder="通讯协议类型、编号"> 
			<span class="input-group-btn"  style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryProtocol" onclick="searchProtocol()">
                	<i class="fa fa-search"></i> 搜索
                </button>
            </span>
       	</div>
       	<div class="zc_search find">
				<div class="zc_search_form">
				<input type="text" id="ProtocolInfo" name="ProtocolInfo" placeholder="通讯协议类型、编号...">
				<button  id="queryProtocol" onclick="dataAnalysis_protocol.searchProtocol()"></button>
				</div>
		</div> 
   </div>
</div>
 -->
<style>

    #import_form_protocol_btn .form-control{
        height: 28px!important;
        line-height: 1.8;
    }
    #import_form_protocol_btn .btn-primary{
        width:6.5vw!important;
        height: 3.85vh!important;
    }
</style>
<!-- ---------------------------------------------------------------------------------------- -->
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;通讯协议定义列表>>>
		</span>
		<!-- 增加按钮 -->
		<a id="addProtocol" data-toggle="modal"  href="#modal-form-addProtocol" class="btn btn-primary toLeft"> 
		<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加 
		</a>
        <!-- 导入按钮 -->
        <a id="importbesrate" onclick="dataAnalysis_protocol.impReport()"  href="javascript:void(-1);" class="btn btn-primary toLeft">
            <i class="fa fa-upload" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入
        </a>
        <!-- 导出按钮 -->
        <a id="exportbesrate"  onclick="dataAnalysis_protocol.exportReport()"  href="javascript:void(-1);" class="btn btn-primary toLeft">
            <i class="fa fa-download"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导出
        </a>
<#--        <!-- 导出excel按钮 &ndash;&gt;-->
<#--        <button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;margin-left: 5px"-->
<#--                onclick="dataAnalysis_protocol.impReport()">-->
<#--            <i class="fa fa-upload" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入-->
<#--        </button>-->
<#--        <!-- 导出excel按钮 &ndash;&gt;-->
<#--        <button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;margin-left: 5px"-->
<#--                onclick="dataAnalysis_protocol.exportReport()">-->
<#--            <i class="fa fa-download"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导出-->
<#--        </button>-->
		<!-- 搜索框 -->
		<div class="zc_search find">
				 <input type="text" class="find-style"  id="ProtocolInfo" name="ProtocolInfo" placeholder="通讯协议类型、编号...">
				 <button id="queryProtocol" onclick="dataAnalysis_protocol.searchProtocol()"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
<!---分页列表----->	        
    <div class="ibox" id="Protocol_ibox" style="height:92%">
         	<#include "/view/basedatamanage/enegrycollectionmanage/protocol_page.ftl"/>
    </div>
<!---添加通讯协议开始-----> 
<div class="modal fade" id="modal-form-addProtocol" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加通讯协议</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addProtocolform" name="addProtocolform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">通讯协议编号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fTxxybh" name="fTxxybh" placeholder="请输入通讯协议编号"  required class="form-control">
                        </div>
                    </div>                                        
                    <div class="form-group">
                          <label class="col-sm-3 control-label">通讯协议类型<span class="text-danger">*</span></label>
                          <div class="col-sm-8">
                          	<input type="text" id="fType" name="fType" placeholder="请输入通讯协议类型"  required class="form-control">
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
<!-- 添加通讯协议结束 -->


<!----编辑通讯协议--->
<div class="modal fade" id="modal-form-editProtocol" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑通信协议信息</h4>
            </div>
            <div class="modal-body">
            	<form id="editProtocol" name="editProtocol" class="form-horizontal">
            	<div class="form-group">
					<label class="col-sm-4 control-label" for="edit_fTxxybh">通讯协议编号  <span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fTxxybh" name="edit_fTxxybh"  required class="form-control" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="edit_fType">通讯协议类型<span class="text-danger">*</span></label>
					<div class="col-sm-8">
						<input type="text" id="edit_fType" name="edit_fType"  required class="form-control">
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
<!----编辑通讯协议结束--->


<!-- 右侧内容模块模块end -->
<!-- 上传模态框 -->
<div class="modal fade import-Model" id="protocol_import_model" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ImportProtocolModalLabel">导入报表</h4>
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
		                         <a href="javascript:void(0);" id="btn_exp" onclick="dataAnalysis_protocol.btn_exp()">下载模板</a>
		                         </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" id="import_form_protocol_btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="reportProtocolImpExcel" >
                            <input id="exportProtocolInputFile"  type="file" name="file"  class="file-loading">
                        </form>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="dataAnalysis_protocol.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
		;
var dataAnalysis_protocol = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	//触发搜索的回车事件
 	$("#ProtocolInfo").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){

						  dataAnalysis_protocol.searchProtocol({
						  })
						  } 
						})
					});
	
	//添加能源类型验证
	var ProtocolValidator =$("#addProtocolform").validate({
		rules : {
			fTxxybh : {
				required : true,
				rangelength : [ 1, 8 ],
			},

			fType : {
				required : true,
				rangelength : [ 1, 20 ],
			}
		},
		messages : {
			fTxxybh : {
				required : "请输入通讯协议编号",
				rangelength : jQuery.validator.format("请输入1-8位"),
			},
			fType : {
				required : "请输入通讯协议名称",
				minlength : jQuery.validator.format("Enter at least {0} characters")
			},
		},
		submitHandler : function(form) {
			addformProtocol(form);
		}
	});

    //fileinput 上传插件初始化
    function initFileinput(){
        $("#exportProtocolInputFile").fileinput({
            language: 'zh', //设置语言
            uploadUrl: '${ctx}/view/basedatamanage/enegrycollectionmanage/fileUpload',//上传请求路径
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
                return {"fold":"importExcel"};
            },//区分不同的模块：fold：文件夹
            //uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取
            deleteUrl: "${ctx}/file/fileDelete?id="//删除文件的路径
        }).on("fileuploaded",function(event, data, previewId, index){
            dataAnalysis_protocol.searchProtocol();
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
                    var exportName = "通信协议导入错误报告";
                    doExp(_url,exportName,"${ctx}",data);//导出excel并下载

                }

			}

        }).on("filebatchuploadsuccess",function(){
            clearForm();//清空form表单
        });
    }

    //清空上传文件表单form 关闭模态框 并提示
    function clearForm(){
        $("#exportProtocolInputFile").fileinput("destroy");//销毁fileinput删除上传文件缓存
        $("#protocol_import_model").modal("hide");//关闭模态框

    }
    //新增保存
	function addformProtocol(form) {

	     $.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/insProtocol",
	       type: "post",
	       contentType : "application/json; charset=utf-8",
	       data : JSON.stringify({
	    	   fTxxybh : $("#fTxxybh").val(),
	    	   fType : $("#fType").val(),
			}),
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
		            $('#modal-form-addProtocol').modal('hide');//关闭编辑窗口
		            //在表格中添加数据
		            $('#ProtocolTable').tabulator("addRow", {
		            	fTxxybh : data.data.fTxxybh,
		            	fType : data.data.fType,
						fCrdate : data.data.fCrdate,
						fChdate : data.data.fChdate
					});
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
 	$('#protocol_import_model').on('show.bs.modal', function () {
 	// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
    $(this).css('display', 'block');  
    var modalHeight=$(window).height() / 2 - $('#protocol_import_model .modal-dialog').height() / 2;
    $(this).find('.modal-dialog').css({  
           'margin-top': modalHeight  
    });
    })
    //居中显示
    $('#modal-form-addProtocol').on('show.bs.modal', function () {
        // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#modal-form-addProtocol .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
    })
    
    	//居中显示（编辑）
 	$('#modal-form-editProtocol').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-editProtocol .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
    
    //关闭模态框清空表单值
	$("#modal-form-addProtocol").on('hidden.bs.modal',function(event) {
			$(this).find("input").val("");
			ProtocolValidator.resetForm();
	});
	
	
	//删除数据
	$(document).on('click','#ProtocolTable button.delete',function() {
		var id = $(this).data("id").toString();
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
			$.ajax({
				url : _ctx + "/view/basedatamanage/enegrycollectionmanage/delProtocol",
				type : "post",
				data : {"fTxxybh" : id},
				beforeSend : function() {
					showLoad();
				},
				success : function(data) {
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
					//重新加载列表及分页
						$("#ProtocolTable").tabulator(
								"deleteRow",
								dataAnalysis_protocol.searchProtocol());
						var getTable = $("#ProtocolTable").tabulator("getData");
	              		$("#ProtocolTable").tabulator("setData", getTable);
					} else {
						swal(data.msg, "", "error");
					}
				},
				complete : function() {
					hiddenLoad();
				},
				error : function(data) {
					swal(data.msg, "", "error");
				}
			});
		});
	});
	
 	//表单验证
	$("#editProtocol").validate({
  	 submitHandler: function(form) {
  		editProtocol(form);
  	 }
 	});

 	//编辑能源类型
	function editProtocol(form) {
   		$.ajax({
     		url: _ctx + "/view/basedatamanage/enegrycollectionmanage/updProtocol",
     		type: "post",
     		data:({
     			fTxxybh: $("#edit_fTxxybh").val(),
     			fType: $("#edit_fType").val(),
     			fCrdate : $("#edite_fCrdate").val(),
     		}),
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
	         		$('#ProtocolTable').tabulator(
	         				"updateRow",
	         				dataAnalysis_protocol.searchProtocol(),{
	         					fTxxybh:data.data.fTxxybh,
	         					fType:data.data.fType,
	         					fChdate:data.data.fChdate});
	              	$('#modal-form-editProtocol').modal('hide');//关闭编辑窗口
         		} else{
             		swal("", data.msg, "error");
         		} 
    		},
    		error: function(data) {
         	 	swal("", data.msg, "error");
    		}
   		});
	}

 	//验证在模态框出现前加载编辑
 	$("#modal-form-editProtocol").on('show.bs.modal', function(event) {
   		var button = $(event.relatedTarget);
   		var id = button.data("id").toString();//获取能源类型编号
   		$.ajax({
	       url: _ctx + "/view/basedatamanage/enegrycollectionmanage/getProtocol",
	       type: "post", 
	       data:{     
	 			"fTxxybh":id
	 		},
	       success: function(result) {
	         $("#edit_fTxxybh").val(result.data.fTxxybh);
	         $("#edit_fType").val(result.data.fType);
	         }
   		});  
 	});
 	return{
 		searchProtocol: function (datas) {

 		var keywords = $("#ProtocolInfo").val();
		$.ajax({
			url : _ctx + '/view/basedatamanage/enegrycollectionmanage/getProtocolList',
			type : "post",
			data :({     
					"keywords":keywords
		 		}),  
				success : function(data) {

					$('#Protocol_ibox').html(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					toastr.error('', '查询失败');
				}
			});
		},
        impReport :function(){
            //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
            $("#protocol_import_model").modal("show");
            initFileinput();//初始化fileinput
        },
        //导出excel
        exportReport:function(){
            var keywords = $("#ProtocolInfo").val();//关键字
            var data = {
                keywords : keywords,
            };
            var _url = "${ctx}/view/basedatamanage/enegrycollectionmanage/exportProtocol";
            var exportName = "通信协议数据报表";
            doExp(_url,exportName,"${ctx}",data);//导出excel并下载
        },
        //下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
        btn_exp : function(){
            var fname = "通信协议数据报表.xls";
            var path = "file\\expExcel\\通信协议数据报表.xls";
	    //FileDownload("${ctx}/file/newFileDownload",fname,path);
            FileDownload(_ctx + filePath.loadPath,fname,path);
        },
        //导入数据按钮
        btn_import:function(){
            var filepath = $("#exportProtocolInputFile").val();
            if(filepath!=""){
                $("#exportProtocolInputFile").fileinput("upload");//上传方法
            }else{
                swal({title: "请上传数据文件!",type: "warning",showCloseButton: true});
            }

        },
	pageInit : function() {
		dataAnalysis_protocol.searchProtocol();
		}
 	}
})(jQuery, window, document);
dataAnalysis_protocol.pageInit(); 
</script>