<!-- -------------------------------------------------------------------------------------- -->
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;模块点类型列表>>>
		</span>
		<!-- 增加按钮 -->
		<a id="addModulePointTypeMC" href="javascript:void(-1);"onclick="eqmanage_besmodulepointtype.role_show_addmodal()" class="btn btn-primary toLeft">
			<i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加
		</a>
		<#--导入按钮-->
        <a id="impModulePointTypeMC" href="javascript:void(-1);"onclick="eqmanage_besmodulepointtype.imp_report(this)" class="btn btn-primary toLeft">
			<i class="fa fa-upload" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入
		</a>
		<#--导出按钮-->
        <a id="expModulePointTypeMC" exptype="nkdlxdy" href="javascript:void(-1);"onclick="basedatamanage_eqmanage_modulepointType_page.exp_excel(this)" class="btn btn-primary toLeft">
			<i class="fa fa-download" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导出
		</a>
        <!-- 搜索框 -->
		<div class="zc_search find">
				 <input type="text" class="find-style"  id="modulepointtypekeywords" name="modulepointtypekeywords" placeholder="模块点类型">
				 <button id="modulepointqueryKeybtn"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
<!---分页列表----->	        
    <div class="ibox" id="modulepointtype_ibox" style="height:92%">
         	<#include "besview/basedatamanage/eqmanage/modulepointType_page.ftl"/>
    </div>

<!---添加模块点类型信息开始----->
<div class="modal fade" id="modal-form-addBESModulePointType"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title addIcon">&nbsp; 添加模块点类型信息</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="addbesModulePointType"
					name="addbesModulePointType" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label">模块点类型<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="hidden" id="edite_fId" name="edite_fId" required
								class="form-control" readonly="readonly"> <input
								type="text" id="fModulepointType" name="fModulepointType"
								placeholder="请输入模块点类型" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">模块点类型描述<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="fDescription" name="fDescription"
								placeholder="请输入模块点类型描述" required class="form-control">
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
<div class="modal fade" id="editbesModulePointTypeForm" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title editIcon">&nbsp;编辑模块点类型信息</h4>
			</div>
			<div class="modal-body">
				<form id="editbesModulePointTypeFormFunc"
					name="editbesModulePointTypeFormFunc" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="fDescription">模块点类型描述
							<span class="text-danger">*</span>
						</label>
						<div class="col-sm-8">
							<input type="text" id="edite_fDescription"
								name="edite_fDescription" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="fModulepointType">模块点类型<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="edite_fModulepointType" name="edite_fModulepointType"
								required class="form-control">
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
<!-- 上传模态框 -->
<div class="modal fade import-Model" id="import-Model-modulepoint" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">导入报表</h4>
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
		                         <a href="javascript:void(0);"  onclick="eqmanage_besmodulepointtype.btn_exp()">下载模板</a>
		                         </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" id="import-form-control-btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <input id="modulepointImport"  type="file" name="file"  class="file-loading">
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="eqmanage_besmodulepointtype.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	;
	var eqmanage_besmodulepointtype = (function($, window, document,
			undefined) {
		var _ctx = '${ctx}';

		$(window).resize(function() {
			$("#modulepointtypeTable").tabulator("redraw");
		});

		//触发搜索的回车时间
		$("#modulepointtypekeywords").focus(function() {
			$(this).keydown(function(e) {
				if (e.which == "13") {
					eqmanage_besmodulepointtype.reLoadEquipmentType({
						"keywords" : $("#modulepointtypekeywords").val()
					});//触发该事件
				}
			})
		});

		$("#modulepointqueryKeybtn").click(function() {
			eqmanage_besmodulepointtype.reLoadEquipmentType({
				"keywords" : $("#modulepointtypekeywords").val()
			});
		});

		//添加服务接口表单验证
		var euserValidator = $("#addbesModulePointType").validate(
				{
					rules : {
						fModulepointType : {
							required : true,
							rangelength : [ 1, 20 ],
						},
				
					fDescription : {
						required : true,
						rangelength : [ 1, 20 ],
					},
					},
					messages : {
						fModulepointType : {
							required : "此字段不能为空，请按要求输入内容！",
							minlength : jQuery.validator
									.format("Enter at least {0} characters")
						},
					
					fDescription : {
						required : "此字段不能为空，请按要求输入内容！",
						minlength : jQuery.validator
								.format("Enter at least {0} characters")
					},

					},
					submitHandler : function(form) {

						addmodulepointform_EsRole(form);
					}
				});

		//居中显示（添加）
		$('#modal-form-addBESModulePointType').on('show.bs.modal',function() {
			// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
			$(this).css('display', 'block');
			var modalHeight = $(window).height()/ 2	- $('#modal-form-addBESModulePointType .modal-dialog').height() / 2;
			$(this).find('.modal-dialog').css({
				'margin-top' : modalHeight
			});
		})
		//居中显示（编辑）
		$('#editbesModulePointTypeForm').on('show.bs.modal',function() {
					$(this).css('display', 'block');
					var modalHeight = $(window).height()/ 2	- $('#editbesModulePointTypeForm .modal-dialog').height() / 2;
					$(this).find('.modal-dialog').css({
						'margin-top' : modalHeight
					});
				})
		//关闭模态框清空表单值
		$("#modal-form-addBESModulePointType").on('hidden.bs.modal',
			function(event) {
				$(this).find("input").val("");
				euserValidator.resetForm();
			})

		//删除数据
		$(document).on(
				'click',
				'#modulepointtypeTable button.delete',
				function() {

					var id = $(this).data("id").toString();
					//var id= sysmanage_eqmanage_besmodulepointtype_page.getCurRow().getData().fId
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
							url : _ctx
									+ "/view/basedatamanage/eqmanage/deletebesModulePointType",
							type : "post",
							data : {
								"fId" : id
							},
							beforeSend : function() {
								showLoad();
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
									$("#modulepointtypeTable").tabulator(
											"deleteRow",
											basedatamanage_eqmanage_modulepointType_page
													.getCurrentRow());
									var getTable = $("#modulepointtypeTable").tabulator("getData");
				              		$("#modulepointtypeTable").tabulator("setData", getTable);
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
		$("#editbesModulePointTypeFormFunc").validate({
			submitHandler : function(form) {
				editbesModulePointTypeFormFunc(form);
			}
		});
		//新增添加
		function addmodulepointform_EsRole(form) {
			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/addbesModulePointType",
				type : "post",
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify({
					fModulepointType : $("#fModulepointType").val(),
					fDescription : $("#fDescription").val(),
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
							$('#modal-form-addBESModulePointType').modal(
									'hide');//关闭添加窗口
							//在表格中添加数据
							$('#modulepointtypeTable').tabulator("addRow", {
								fId : data.data.fId,
								fDescription : data.data.fDescription,
								fModulepointType : data.data.fModulepointType,
								fMethod : data.data.fMethod,
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
		//编辑
		function editbesModulePointTypeFormFunc(form) {

			$.ajax({
				url : _ctx + "/view/basedatamanage/eqmanage/updatebesModulePointType",
				type : "post",
				data : ({
					fId : $("#edite_fId").val(),
					fDescription : $("#edite_fDescription").val(),
					fModulepointType : $("#edite_fModulepointType").val(),
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
						$('#modulepointtypeTable').tabulator(
								"updateRow",
								basedatamanage_eqmanage_modulepointType_page
										.getCurrentRow(), {
									fModulepointType : data.data.fModulepointType,
									fDescription : data.data.fDescription,
									fChdate : data.data.fChdate,
									fMethod : data.data.fMethod,
								});
							$('#editbesModulePointTypeForm').modal('hide');//关闭编辑窗口
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
		$("#editbesModulePointTypeForm").on('show.bs.modal', function(event) {

			var button = $(event.relatedTarget);
			var id = button.data("id").toString();
					
					$.ajax({
						url : _ctx + "/view/basedatamanage/eqmanage/querybesModulePointType",
						type : "post",
						data : {
							"fId" : id
						},
						success : function(result) {

							$("#edite_fId").val(result.data.fId);
							$("#edite_fModulepointType").val(result.data.fModulepointType);
							$("#edite_fDescription").val(result.data.fDescription);
						}
					});
				
			});

        //fileinput 上传插件初始化
        function initFileinput(){
            $("#modulepointImport").fileinput({
                language: 'zh', //设置语言
                uploadUrl: '${ctx}/view/basedatamanage/eqmanage/fileUpload',//上传请求路径
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
                var res = data.response;
                //status=='1' 成功
                if(res.status== '1'){
                    clearForm();//清空form表单
                }else{//失败导出错误信息
                    downErrorExcel(res);//失败下载错误报告模板
                }
            }).on("filebatchuploadsuccess",function(){
                clearForm();//清空form表单
            });
        }

        //清空上传文件表单form 关闭模态框 并提示
        function clearForm(){
            //刷新table
            eqmanage_besmodulepointtype.reLoadEquipmentType();
            //关闭遮罩层
            hiddenLoad();
            //销毁fileinput删除上传文件缓存
            $("#modulepointImport").fileinput("destroy");
            //关闭模态框
            $("#import-Model-modulepoint").modal("hide");
            swal({title: "文件上传成功！",type: "success",showCloseButton: true});
        }

        //失败下载错误报告模板
		function downErrorExcel(res){
            let jsonList = JSON.stringify(res.list);
            //关闭遮罩层
            hiddenLoad();
            //销毁fileinput删除上传文件缓存
            $("#modulepointImport").fileinput("destroy");
            //关闭模态框
            $("#import-Model-modulepoint").modal("hide");
            swal({title: res.msg,type: "error",showCloseButton: true});
            //status恒等于2才导出错误模板
            if(res.status!='2'){
                return false;
            }
            var alias = new Array();
            // excel的列头
            alias.push('行数');
            alias.push('错误信息');
            // 数据List中的Map的key值.
            var names = new Array();
            names.push('row');
            names.push('errorMsg');
            //报表名称
            var exportName = "模块点类型定义错误报告";
            //表名
            var tablename = "bes_sbtree_nodetype";
            //数据json内容
            var data = {
                alias : JSON.stringify(alias),
                names : JSON.stringify(names),
                jsonList,jsonList,
            };
            //统一导出excel接口
            var _url = "${ctx}/expExcel/exportExcel";
            doExp(_url,exportName,"${ctx}",data);//导出excel并下载
		}

        //模态框居中显示
        $('#import-Model-modulepoint').on('show.bs.modal', function () {
            // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
            $(this).css('display', 'block');
            var modalHeight=$(window).height() / 2 - $('#import-Model-modulepoint .modal-dialog').height() / 2;
            $(this).find('.modal-dialog').css({'margin-top': modalHeight});
        })

		return {
			//验证增加模态框是否弹出
			role_show_addmodal : function() {
				$('#modal-form-addBESModulePointType').modal('show');
			},
			// 分页查询
			reLoadEquipmentType : function(datas) {
				$.ajax({
					url : _ctx + '/view/basedatamanage/eqmanage/modulepointType_page',
					type : "post",
					data : datas,
					beforeSend : function() {
						showLoad();
					},
					success : function(data) {
						$('#modulepointtype_ibox').html(data);
					},
					complete : function() {
						hiddenLoad();
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						toastr.error('', '查询失败');
					}
				});
			},
            //下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
            btn_exp : function(){
			    //1.下载固定模板
                var fname = "模块点类型定义.xls";
                var path = "file/expExcel/模块点类型定义.xls";
		//FileDownload("${ctx}/file/newFileDownload",fname,path);
                FileDownload(_ctx + filePath.loadPath,fname,path);
            },
            imp_report :function(){
                //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
                $("#import-Model-modulepoint").modal("show");
                //初始化fileinput
                initFileinput();
            },
            //导入数据按钮
            btn_import:function(){
                var filepath = $("#modulepointImport").val();
                if(filepath!=""){
                    //开启遮罩层
                    showLoad();
                    //上传方法
                    $("#modulepointImport").fileinput("upload");
                }else{
                    swal({title: "请上传数据文件!",type: "warning",showCloseButton: true});
                }
            },
			pageInit : function() {
				eqmanage_besmodulepointtype.reLoadEquipmentType();
			}
		}
	})(jQuery, window, document);
	eqmanage_besmodulepointtype.pageInit();
</script>