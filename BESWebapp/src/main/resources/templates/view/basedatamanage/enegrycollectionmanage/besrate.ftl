<!-----内容区域---->
<!-- <div class="ibox-content m-b-sm border-bottom"
	style="width: 100%; height: 60px">
	<div class="input-group" style="height: 30px; width: 100%;">
		<div
			style="width: 50%; float: left; position: relative; padding-right: 15px;">
			<a id="addbesrate" href="javascript:void(-1);"
				onclick="enegrycollectionmanage_besrate.role_show_addmodal()"
				class="btn btn-primary"> 增加 <i class="fa fa-plus"></i>
			</a>
			<a id="importbesrate" class="btn btn-primary" data-toggle="modal"  href="#modal-form-Emtadd">
                	导入 <i class="fa fa-mail-reply"></i>
            </a>
            <a id="exportbesrate" class="btn btn-primary" data-toggle="modal"  href="#modal-form-Emtadd">
                	导出 <i class="fa fa-mail-forward"></i>
            </a>
		</div>
		 <div class="zc_search find">
				<div class="zc_search_form">
				<input type="text" id="besratekeywords" name="besratekeywords" placeholder="通信波特率编号、通信波特率...">
				<button  id="besrateKeybtn"></button>
				</div>
		</div> 
	</div>
</div> -->
<style>

    #import_form_besrate_btn .form-control{
        height: 28px!important;
        line-height: 1.8;
    }
    #import_form_besrate_btn .btn-primary{
        width:6.5vw!important;
        height: 3.85vh!important;
    }
</style>

<!-- ------------------------------------------------------------------------------ -->
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;通信波特率定义列表>>>
		</span>
		<!-- 增加按钮 -->
		<a id="addbesrate" href="javascript:void(-1);"onclick="enegrycollectionmanage_besrate.role_show_addmodal()" class="btn btn-primary toLeft"> 
		<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加 
		</a>

		<!-- 导入按钮 -->
		<a id="importbesrate" onclick="enegrycollectionmanage_besrate.impReport()"  href="javascript:void(-1);" class="btn btn-primary toLeft">
			<i class="fa fa-upload" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入
		</a>
		<!-- 导出按钮 -->
		<a id="exportbesrate" onclick="basedatamanage_eqmanage_besrate_page.exp_excel(this)"  href="javascript:void(-1);" class="btn btn-primary toLeft">
			<i class="fa fa-download"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导出
		</a>

<#--        <button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;margin-left: 5px"-->
<#--                onclick="enegrycollectionmanage_besrate.impReport()">-->
<#--            <i class="fa fa-upload"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;导入-->
<#--        </button>-->
<#--        <button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;margin-left: 5px"-->
<#--                onclick="basedatamanage_eqmanage_besrate_page.exp_excel(this)">-->
<#--            <i class="fa fa-download"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;导出-->
<#--        </button>-->
		<!-- 搜索框 -->
		<div class="zc_search find">
				 <input type="text" class="find-style"  id="besratekeywords" name="besratekeywords" placeholder="通信波特率编号、通信波特率...">
				 <button id="besrateKeybtn"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
<!---分页列表----->	        
    <div class="ibox" id="besrate_ibox" style="height:92%">
         	<#include "view/basedatamanage/enegrycollectionmanage/besrate_page.ftl"/>
    </div>


<!---添加通信波特率信息开始----->
<div class="modal fade" id="modal-form-addbesrate"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title addIcon">&nbsp; 添加通信波特率信息</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="addbesModulePointType"
					name="addbesModulePointType" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label">通信波特率编号<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<!-- <input type="hidden" id="edite_fId" name="edite_fId" required
								class="form-control" readonly="readonly"> --> <input
								type="text" id="fRateBh" name="fRateBh"
								placeholder="请输入通信波特率编号" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">通信波特率<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="fCommRate" name="fCommRate"
								placeholder="请输入通信波特率" required class="form-control">
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


<!----编辑--->
<div class="modal fade" id="editbesrateForm" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title editIcon">&nbsp;编辑通信波特率信息</h4>
			</div>
			<div class="modal-body">
				<form id="editbesrateFormFunc"
					name="editbesrateFormFunc" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="fRateBh">通信波特率编号<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text"  readonly="readonly" id="edite_fRateBh" name="edite_fRateBh"
								required class="form-control">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label" for="fCommRate">通信波特率
							<span class="text-danger">*</span>
						</label>
						<div class="col-sm-8">
							<input type="text" id="edite_fCommRate"
								name="edite_fCommRate" required class="form-control">
						</div>
					</div>

					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-4 display_flex">
							<button class="btn btn-md btn-primary " type="submit">
								<strong>确定</strong>
							</button>
							<button type="button" class="btn btn-white m-l-sm"
								data-dismiss="modal">取消</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<!-- 右侧内容模块模块end -->
<!-- 上传模态框 -->
<div class="modal fade import-Model" id="besrate_import_model" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ImportbesrateModalLabel">导入报表</h4>
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
		                         <a href="javascript:void(0);" id="btn_exp" onclick="enegrycollectionmanage_besrate.btn_exp()">下载模板</a>
		                         </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" id="import_form_besrate_btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="reportbesrateImpExcel" >
                            <input id="exportBesrateInputFile"  type="file" name="file"  class="file-loading">
                        </form>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="enegrycollectionmanage_besrate.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	;
	var enegrycollectionmanage_besrate = (function($, window, document,
			undefined) {
		var _ctx = '${ctx}';

		$(window).resize(function() {
			$("#besrateTable").tabulator("redraw");
		});

		//触发搜索的回车时间
		$("#besratekeywords").focus(function() {
			$(this).keydown(function(e) {
				if (e.which == "13") {
					enegrycollectionmanage_besrate.reLoadEquipmentType({
						"keywords" : $("#besratekeywords").val()
					});//触发该事件
				}
			})
		});

		$("#besrateKeybtn").click(function() {
			enegrycollectionmanage_besrate.reLoadEquipmentType({
				"keywords" : $("#besratekeywords").val()
			});
		});

		//添加服务接口表单验证
		var euserValidator = $("#addbesModulePointType").validate(
				{
					rules : {
						fRateBh : {
							digits:true,
							required : true,
							rangelength : [ 1, 20 ],
						},
						fCommRate : {
							digits:true,
							required : true,
							rangelength : [ 1, 20 ],
						},
					},
					messages : {
						fRateBh : {
							required : "此字段不能为空，请按要求输入内容！",
							minlength : jQuery.validator
									.format("Enter at least {0} characters")
						},
							fCommRate : {
								required : "此字段不能为空，请按要求输入内容！",
								minlength : jQuery.validator
										.format("Enter at least {0} characters")
						},

					},
					submitHandler : function(form) {
						checkRepeat(form);
					}
				});

		//居中显示（添加）
		$('#modal-form-addbesrate')
				.on(
						'show.bs.modal',
						function() {
							// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
							$(this).css('display', 'block');
							var modalHeight = $(window).height()
									/ 2
									- $(
											'#modal-form-addbesrate .modal-dialog')
											.height() / 2;
							$(this).find('.modal-dialog').css({
								'margin-top' : modalHeight
							});
						})
		//居中显示（编辑）
		$('#editbesrateForm').on(
				'show.bs.modal',
				function() {
					$(this).css('display', 'block');
					var modalHeight = $(window).height()
							/ 2
							- $('#editbesrateForm .modal-dialog')
									.height() / 2;
					$(this).find('.modal-dialog').css({
						'margin-top' : modalHeight
					});
				})
		//关闭模态框清空表单值
		$("#modal-form-addbesrate").on('hidden.bs.modal',
			function(event) {
				$(this).find("input").val("");
				euserValidator.resetForm();
			})

		//删除数据
		$(document).on(
				'click',
				'#besrateTable button.delete',
				function() {

					var id = $(this).data("id").toString();
					//var id= sysmanage_enegrycollectionmanage_besrate_page.getCurRow().getData().fId
					swal({
						title : "您确定要删除吗?",
						text : "信息删除后将不可恢复!",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#1ab394",
						confirmButtonText : "确定",
						closeOnConfirm : false
					}, function() {

						$.ajax({
							url : _ctx + "/view/basedatamanage/enegrycollectionmanage/delBesRate",
							type : "post",
							data : {
								"fRateBh" : id
							},
							success : function(data) {

								if (data.status === '1') {
									//swal(data.msg, "", "success");
									swal({ 
								            title: data.msg,
								            text: "",
								            type: "success",
								            showCloseButton:false,
								            allowOutsideClick:false,
								            showConfirmButton: false,
								            timer:1000
					        			});
									$("#besrateTable").tabulator(
											"deleteRow",
											basedatamanage_eqmanage_besrate_page
													.getCurrentRow());
									var getTable = $("#besrateTable").tabulator("getData");
				              		$("#besrateTable").tabulator("setData", getTable);
								} else {
									swal(data.msg, "", "error");
								}
							},
							error : function(data) {
								swal(data.msg, "", "error");
							}
						});
					});
				});

		//表单验证
		$("#editbesrateFormFunc").validate({
			rules : {
				edite_fCommRate : {
					digits:true,
					required : true,
					rangelength : [ 1, 20 ],
				}
			},
			messages : {
				edite_fCommRate : {
					required : "此字段不能为空，请按要求输入内容！",
					minlength : jQuery.validator
							.format("Enter at least {0} characters")
				},

			},
			submitHandler : function(form) {
				checkEditRepeat(form);
			}
		});

		function checkRepeat(form){
			var  fRateBh = document.getElementById("fRateBh").value;

			var  fCommRate = document.getElementById("fCommRate").value;

			$.ajax({
				url: _ctx + "/view/basedatamanage/enegrycollectionmanage/checkRateRepeat",
				type: "post",
				data:({
					fRateBh:fRateBh,
					fCommRate:fCommRate
				}),
				success: function(result) {
					if (result.msg === "0" && result.data === "0"){
						addbesrateform_EsRole(form);
					}else{
						swal({
							title : "错误",// 展示的标题
							text : "通信波特率编号和通信波特率不允许有重复字段！",// 内容
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


		function checkEditRepeat(form){

			var  edite_fCommRate = document.getElementById("edite_fCommRate").value;

			$.ajax({
				url: _ctx + "/view/basedatamanage/enegrycollectionmanage/checkEditRepeat",
				type: "post",
				data:({
					fCommRate:edite_fCommRate
				}),
				success: function(result) {
					if (result.data === "0"){
						editbesrateFormFunc(form);
					}else{
						swal({
							title : "错误",// 展示的标题
							text : "通信波特率不允许有重复字段！",// 内容
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

		//新增添加
		function addbesrateform_EsRole(form) {
			$.ajax({
				url : _ctx + "/view/basedatamanage/enegrycollectionmanage/insBesRate",
				type : "post",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify({
					fRateBh : $("#fRateBh").val(),
					fCommRate : $("#fCommRate").val(),
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
						//setTimeout(function() {
							$('#modal-form-addbesrate').modal(
									'hide');//关闭添加窗口
							//在表格中添加数据
							$('#besrateTable').tabulator("addRow", {
								fCommRate : data.data.fCommRate,
								fRateBh : data.data.fRateBh,
								fCrdate : data.data.fCrdate,
								fChdate : data.data.fChdate
							});
					} else {
						swal(data.msg, "", "error");
					}
				},
				error : function(data) {
					swal(data.msg, "", "error");
				}
			});
			return false;
		}

        //清空上传文件表单form 关闭模态框 并提示
        function clearForm(){
            $("#exportBesrateInputFile").fileinput("destroy");//销毁fileinput删除上传文件缓存
            $("#besrate_import_model").modal("hide");//关闭模态框
        }
        //居中显示
        $('#besrate_import_model').on('show.bs.modal', function () {
            // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
            $(this).css('display', 'block');
            var modalHeight=$(window).height() / 2 - $('#besrate_import_model .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight
            });
        })
        //fileinput 上传插件初始化
        function initFileinput(){
            $("#exportBesrateInputFile").fileinput({
                language: 'zh', //设置语言
                uploadUrl: '${ctx}/view/basedatamanage/enegrycollectionmanage/besRateFileUpload',//上传请求路径
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
                    return {"fold":"besrateImportExcel"};
                },//区分不同的模块：fold：文件夹
                //uploasExtraData:是把页面你想要往后退传的东西放(return)到域里面然后去后台去取
                deleteUrl: "${ctx}/file/fileDelete?id="//删除文件的路径
            }).on("fileuploaded",function(event, data, previewId, index){
                enegrycollectionmanage_besrate.reLoadEquipmentType();
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
                        var exportName = "通信波特率导入错误报告";
                        doExp(_url,exportName,"${ctx}",data);//导出excel并下载

                    }

                }

            }).on("filebatchuploadsuccess",function(){
                clearForm();//清空form表单
            });
        }

		//编辑
		function editbesrateFormFunc(form) {

			$.ajax({
				url : _ctx + "/view/basedatamanage/enegrycollectionmanage/updBesRate",
				type : "post",
				data : ({
					fCommRate : $("#edite_fCommRate").val(),
					fRateBh : $("#edite_fRateBh").val(),
					fCrdate : $("#edite_fCrdate").val(),
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
						$('#besrateTable').tabulator(
								"updateRow",
								basedatamanage_eqmanage_besrate_page
										.getCurrentRow(), {
									fRateBh : data.data.fRateBh,
									fCommRate : data.data.fCommRate,
									fChdate : data.data.fChdate,
								});
							$('#editbesrateForm').modal('hide');//关闭编辑窗口
					} else {
						swal("", data.msg, "error");
					}
				},
				error : function(data) {
					swal("", data.msg, "error");
				}
			});
		}

		//验证在模态框出现前加载
		$("#editbesrateForm").on('show.bs.modal', function(event) {

			var button = $(event.relatedTarget);
			var id = button.data("id").toString();
					$.ajax({
						url : _ctx + "/view/basedatamanage/enegrycollectionmanage/selBesRate",
						type : "post",
						data : {
							"fRateBh" : id
						},
						success : function(result) {

							$("#edite_fRateBh").val(result.data.fRateBh);
							$("#edite_fCommRate").val(result.data.fCommRate);
						}
					});
				
			});

		return {
			//验证增加模态框是否弹出
			role_show_addmodal : function() {
				$('#modal-form-addbesrate').modal('show');
			},
			// 分页查询
			reLoadEquipmentType : function(datas) {

				$.ajax({
					url : _ctx + '/view/basedatamanage/enegrycollectionmanage/besRatePage',
					type : "post",
					data : datas,
					beforeSend : function() {
						showLoad();
					},
					success : function(data) {

						$('#besrate_ibox').html(data);
					},
					complete : function() {
						hiddenLoad();
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error('', '查询失败');
					}
				});
			},
            impReport :function(){
                //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
                $("#besrate_import_model").modal("show");
                initFileinput();//初始化fileinput
            },
            //下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
            btn_exp : function(){
                var fname = "通信波特率数据报表模板.xls";
                var path = "file\\expExcel\\通信波特率数据报表模板.xls";
		//FileDownload("${ctx}/file/newFileDownload",fname,path);
                FileDownload(_ctx + filePath.loadPath,fname,path);
            },
            //导入数据按钮
            btn_import:function(){
                var filepath = $("#exportBesrateInputFile").val();
                if(filepath != ""){
                    $("#exportBesrateInputFile").fileinput("upload");//上传方法
                }else{
                    swal({title: "请上传数据文件!",type: "warning",showCloseButton: true});
                }

            },

			pageInit : function() {
				enegrycollectionmanage_besrate.reLoadEquipmentType();
			}
		}
	})(jQuery, window, document);
	enegrycollectionmanage_besrate.pageInit();
</script>