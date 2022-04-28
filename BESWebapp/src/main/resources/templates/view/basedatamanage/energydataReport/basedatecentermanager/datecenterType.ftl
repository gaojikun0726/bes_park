<#--
	数据中心基本信息
-->
	<div class="information-model">
		<span class="Subtitle">
			<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;数据中心基本信息列表>>>
		</span>
		<!-- 增加按钮 -->
		<a id="addenergyType" data-toggle="modal"  href="#modal-form-adddatecenterType" class="btn btn-primary toLeft"> <i class="fa fa-plus"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>增加 
		</a> 
		<!-- 导入 -->
		<a data-toggle="modal" href="#import-adddatecenterType" class="btn btn-primary toLeft"> <i class="fa fa-upload"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;导入</a>
		<!-- 导出 -->
		<a class="btn btn-primary toLeft" onclick="energydataReport_basedatecentermanager_datecenterType.exportReport()"> <i class="fa fa-download"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>&nbsp;导出</a>
		<!-- 搜索框 -->
		<div class="zc_search find">
				 <input type="text" class="find-style"  id="datecenterinfo" name="datecenterinfo" placeholder="数据中心代码、名称">
				 <button id="queryDatecenterType"><i class="fa fa-search" aria-hidden="true"></i></button>
		</div>
	</div>
<!---分页列表----->	        
    <div class="ibox" id="Datecenter_ibox" style="height:92%">
         	<#include "/view/basedatamanage/energydataReport/basedatecentermanager/datecenterType_page.ftl"/>
    </div>


<!---添加开始-----> 
<div class="modal fade" id="modal-form-adddatecenterType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加数据中心信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addenergyTypeform" name="addenergyTypeform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">代码<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="CODE" name="F_DATACENTER_CODE" placeholder="请输入数据中心代码"  required class="form-control">
                        </div>
                    </div>                                        
                    <div class="form-group">
                          <label class="col-sm-3 control-label">名称<span class="text-danger">*</span></label>
                          <div class="col-sm-8">
                          	<input type="text" id="NAME" name="F_DATACENTER_NAME" placeholder="请输入数据中心名称"  required class="form-control">
                          </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">IP地址<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="IP" name="F_DATACENTER_IP" placeholder="请输入数据中心IP地址"  required class="form-control ip">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">端口号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="PORT" name="F_DATACENTER_PORT" placeholder="请输入数据中心端口号" required  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                       <label class="col-sm-3 control-label">类型<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                           <select id="F_DATACENTER_TYPE" name="F_DATACENTER_TYPE"  class="form-control selectpicker" >
						   </select>
                       </div>
                    </div>
                    <div class="form-group">
                       <label class="col-sm-3 control-label">主管单位<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                            <input type="text" id="BODY" name="F_GOVERNING_BODY" placeholder="请输入数据中心主管单位"   class="form-control">
                       </div>
                    </div> 
                    <div class="form-group">
                       <label class="col-sm-3 control-label">数据中心描述<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                            <input type="text" id="DESCRIBE" name="F_DATACENTER_DESCRIBE" placeholder="请输入数据中心描述"   class="form-control">
                       </div>
                    </div> 
                    <div class="form-group">
                       <label class="col-sm-3 control-label">联系人<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                            <input type="text" id="CONTACTPERSON" name="F_CONTACTPERSON" placeholder="请输入联系人"   class="form-control">
                       </div>
                    </div>
                    <div class="form-group">
                       <label class="col-sm-3 control-label">联系电话<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                            <input type="text" id="PHONE" name="F_PHONE" placeholder="请输入联系电话"   class="form-control isTel">
                       </div>
                    </div>


									<div class="form-group">
										<label class="col-sm-3 control-label">建筑群名称<span class="text-danger">*</span></label>
										<div class="col-sm-8">
											<input type="text" id="F_BUILDGROUPNAME" name="F_BUILDGROUPNAME" placeholder="请输入建筑群名称"   class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label">建筑群别名<span class="text-danger">*</span></label>
										<div class="col-sm-8">
											<input type="text" id="F_GROUPALIASNAME" name="F_GROUPALIASNAME" placeholder="请输入建筑群别名"   class="form-control">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label">建筑群描述<span class="text-danger">*</span></label>
										<div class="col-sm-8">
											<input type="text" id="F_GROUPDESC" name="F_GROUPDESC" placeholder="请输入建筑群描述"   class="form-control">
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
<!-- 添加结束 -->


<!----编辑能源类型--->
<div class="modal fade" id="modal-form-editDatecenterType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑数据中心信息</h4>
            </div>
            <div class="modal-body">
            	<form id="editEnergyType" name="editEnergyType" class="form-horizontal">
            	<input type="hidden" id="edit_ID" name="F_DATACENTER_ID"  class="form-control">
            	<div class="form-group">
                        <label class="col-sm-3 control-label" for="edit_CODE" >代码<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_CODE" name="F_DATACENTER_CODE"   required class="form-control">
                        </div>
                    </div>                                        
                    <div class="form-group">
                          <label class="col-sm-3 control-label" for="edit_NAME">名称<span class="text-danger">*</span></label>
                          <div class="col-sm-8">
                          	<input type="text" id="edit_NAME" name="F_DATACENTER_NAME"   class="form-control">
                          </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="edit_IP" >IP地址<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_IP" name="F_DATACENTER_IP" required  class="form-control ip">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="edit_PORT">端口号<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_PORT" name="F_DATACENTER_PORT"  required class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                       <label class="col-sm-3 control-label" for="edit_TYPE">类型<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                           <select id="edit_TYPE" name="F_DATACENTER_TYPE"  class="form-control selectpicker" >
						   </select>
                       </div>
                    </div>
                    <div class="form-group">
                       <label class="col-sm-3 control-label" for="edit_BODY">主管单位<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                            <input type="text" id="edit_BODY" name="F_GOVERNING_BODY"   class="form-control">
                       </div>
                    </div> 
                    <div class="form-group">
                       <label class="col-sm-3 control-label" for="edit_DESCRIBE">数据中心描述<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                            <input type="text" id="edit_DESCRIBE" name="F_DATACENTER_DESCRIBE"  class="form-control">
                       </div>
                    </div> 
                    <div class="form-group">
                       <label class="col-sm-3 control-label" for="edit_CONTACTPERSON">联系人<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                            <input type="text" id="edit_CONTACTPERSON" name="F_CONTACTPERSON"   class="form-control">
                       </div>
                    </div>
                    <div class="form-group">
                       <label class="col-sm-3 control-label" for="edit_PHONE">联系电话<span class="text-danger">*</span></label>
                       <div class="col-sm-8">
                            <input type="text" id="edit_PHONE" name="F_PHONE"    class="form-control isTel">
                       </div>
                    </div>

										<div class="form-group">
											<label class="col-sm-3 control-label">建筑群名称<span class="text-danger">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="edit_BUILDGROUPNAME" name="F_BUILDGROUPNAME" placeholder="请输入建筑群名称"   class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">建筑群别名<span class="text-danger">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="edit_GROUPALIASNAME" name="F_GROUPALIASNAME" placeholder="请输入建筑群别名"   class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">建筑群描述<span class="text-danger">*</span></label>
											<div class="col-sm-8">
												<input type="text" id="edit_GROUPDESC" name="F_GROUPDESC" placeholder="请输入建筑群描述"   class="form-control">
											</div>
										</div>


										<div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-3 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong><i class="fa fa-check" aria-hidden="true"></i>&nbsp;更新</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i>&nbsp;取消</button>
                        </div>
                    </div>
            	</form>
            </div>
            
        </div>
    </div>
</div>
<!----编辑能源类型结束--->
<!-- 上传模态框 -->
<div class="modal fade" id="import-adddatecenterType" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" style="width: 35%;margin: 0 auto;">
        <div class="modal-content">        
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;导入</h4>
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
		                         <a href="javascript:void(0);" id="btn_exp" onclick="energydataReport_basedatecentermanager_datecenterType.btn_exp()">下载模板</a>
		                         </span>
		                     </div>
		                </div>
		             </div>                    
		        </div>				
				<div class="row" id="import-form-control-btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="reportImpExcel" >
                            <input id="exportInputFile"  type="file" name="file"  class="file-loading">
                        </form>
                    </div>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="energydataReport_basedatecentermanager_datecenterType.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
            </div>            
        </div>
    </div>
</div>
<script type="text/javascript">
;
var energydataReport_basedatecentermanager_datecenterType = (function($, window, document, undefined) {
	var _ctx = '${ctx}';
	//触发搜索的回车事件
 	$("#queryDatecenterType").focus(function(){
	  $(this).keydown(function (e){
	  if(e.which == "13"){
		 energydataReport_basedatecentermanager_datecenterType.searchEnergyType({
			"keywords" : $("#datecenterinfo").val()
		});
	    } 
	  })
	});
	//点击搜索事件
	$("#queryDatecenterType").click(function() {
		energydataReport_basedatecentermanager_datecenterType.searchEnergyType({
			"keywords" : $("#datecenterinfo").val()
		});
	});
	
	//下拉框列表查询
	function fNybhCollMethod(keywords){
		$.ajax({
        	type: "POST", 
        	url: _ctx + '/view/energydataReport/basedatecentermanager/selectfNybhList', 
        	data:"",
        	beforeSend: function () { 
        		showLoad();	             
        	},
        success: function(data){
		 	var ops="<option value=''>请选择类型</option>";
		 	for(var i=0;i<data.list.length;i++){
			 	var obj=data.list[i];
			 	ops+='<option value="'+obj.F_DATACENTER_TYPEID+'">';
			 	ops+=obj.F_DATACENTER_TYPE;
			 	ops+='</option>';
		 	}
		 	if(keywords == 'add'){
		 		$("#F_DATACENTER_TYPE").append(ops);
		 	}else{
		 		$("#edit_TYPE").append(ops);
		 	}
        }, 
        complete: function () {
        	hiddenLoad();
        },
        error:function(msg){
        	alert( "下拉框列表查询失败!" );  
        } 
    });
 	}
	
    // IP地址验证   
    jQuery.validator.addMethod("ip", function(value, element) {    
      return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);    
    }, "请填写正确的IP地址。");
	
	//添加验证
	var energyTypeValidator =$("#addenergyTypeform").validate({
		rules : {
			F_DATACENTER_CODE : {
				required : true,
			},
			F_DATACENTER_NAME : {
				required : true,
			},
			F_DATACENTER_IP : {
				required : true,
			},
			F_DATACENTER_PORT : {
				required : true,
			},
			F_DATACENTER_TYPE : {
				required : true,
			},
			F_GOVERNING_BODY : {
				required : true,
			},
			F_DATACENTER_DESCRIBE : {
				required : true,
			},
			F_CONTACTPERSON : {
				required : true,
			},
			F_PHONE : {
				required : true,
			},
			F_BUILDGROUPNAME : {
				required : true,
			},
			F_GROUPALIASNAME : {
				required : true,
			},
			F_GROUPDESC : {
				required : true,
			}
		},
		messages : {
			F_DATACENTER_CODE : {
				required : "请输入数据中心代码"
			},
			F_DATACENTER_NAME : {
				required : "请输入数据中心名称"
			},
			F_DATACENTER_IP : {
				required : "请输入数据中心IP地址"
			},
			F_DATACENTER_PORT : {
				required : "请输入数据中心端口号"
			},
			F_DATACENTER_TYPE : {
				required : "请选择数据中心类型"
			},
			F_GOVERNING_BODY : {
				required : "请输入主管单位"
			},
			F_DATACENTER_DESCRIBE : {
				required : "请输入数据中心描述"
			},
			F_CONTACTPERSON : {
				required : "请输入联系人"
			},
			F_PHONE : {
				required : "请输入联系电话"
			},
			F_BUILDGROUPNAME : {
				required : "请输入建筑群名称"
			},
			F_GROUPALIASNAME : {
				required : "请输入建筑群别名"
			},
			F_GROUPDESC : {
				required : "请输入建筑群描述"
			}
		},
		submitHandler : function(form) {
			addformEnergyType(form);
		}
	});
    //fileinput 上传插件初始化
   	function initFileinput(){
   		$("#exportInputFile").fileinput({
               language: 'zh', //设置语言
               uploadUrl: '${ctx}/view/energydataReport/basedatecentermanager/fileUpload',//上传请求路径
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
					console.log("错误信息:"+JSON.stringify(res.list))
					let jsonList = JSON.stringify(res.list);
                   //关闭遮罩层
                   hiddenLoad();
                   //销毁fileinput删除上传文件缓存
                   $("#exportInputFile").fileinput("destroy");
                   //关闭模态框
                   $("#import-adddatecenterType").modal("hide");
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
                   var exportName = "数据中心错误报告";
                   //表名
                   var tablename = "bes_data_center";
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
           }).on("filebatchuploadsuccess",function(){
               clearForm();//清空form表单
           });
   	}
  	//清空上传文件表单form 关闭模态框 并提示
    function clearForm(){
    	energydataReport_basedatecentermanager_datecenterType.searchEnergyType();
        $("#exportInputFile").fileinput("destroy");//销毁fileinput删除上传文件缓存
    	$("#import-adddatecenterType").modal("hide");//关闭模态框
    	swal({title: "文件上传成功！",type: "success",showCloseButton: true});
    }
    //新增保存
	function addformEnergyType(form) {
	     $.ajax({
	       url: _ctx + "/view/energydataReport/basedatecentermanager/add_Datecenter",
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
		            $('#modal-form-adddatecenterType').modal('hide');//关闭编辑窗口
// 		            $("#EnergyTypeTable").tabulator("addRow",data.data);
		            //重新加载列表及分页
		            energydataReport_basedatecentermanager_datecenterType.searchEnergyType()
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
 	$('#modal-form-adddatecenterType').on('show.bs.modal', function () {
	$("#F_DATACENTER_TYPE").empty();
	fNybhCollMethod("add");
 	// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
    $(this).css('display', 'block');  
    var modalHeight=$(window).height() / 2 - $('#modal-form-adddatecenterType .modal-dialog').height() / 2;  
    $(this).find('.modal-dialog').css({  
           'margin-top': modalHeight  
    });
    })
    
    	//居中显示（编辑）
 	$('#modal-form-editDatecenterType').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-editDatecenterType .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})  
	//居中显示（导入）
	$('#import-adddatecenterType').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#import-adddatecenterType .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
        
        initFileinput();
	}) 
    //关闭模态框清空表单值
	$("#modal-form-adddatecenterType").on('hidden.bs.modal',function(event) {
			$(this).find("input").val("");
			energyTypeValidator.resetForm();
	});
	$("#modal-form-editDatecenterType").on('hidden.bs.modal',function(event) {
		$(this).find("input").val("");
		energyTypeValidator.resetForm();
	});
	
	//验证码在模态框出现前加载包含用户(可拖动)
	$("#modal-form-adddatecenterType,#modal-form-editDatecenterType").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	});
	
	//删除数据
	$(document).on('click','#DatecenterTable button.delete',function() {
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
			setTimeout(function() {
			$.ajax({
				url : _ctx + "/view/energydataReport/basedatecentermanager/del_Datecenter",
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
					// window.location.href="${ctx }/view/user/user"; 
					//重新加载列表及分页
		            energydataReport_basedatecentermanager_datecenterType.searchEnergyType()
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
	
 	//修改 表单验证
	$("#editEnergyType").validate({
		rules : {
			F_DATACENTER_CODE : {
				required : true,
			},
			F_DATACENTER_NAME : {
				required : true,
			},
			F_DATACENTER_IP : {
				required : true,
			},
			F_DATACENTER_PORT : {
				required : true,
			},
			F_DATACENTER_TYPE : {
				required : true,
			},
			F_GOVERNING_BODY : {
				required : true,
			},
			F_DATACENTER_DESCRIBE : {
				required : true,
			},
			F_CONTACTPERSON : {
				required : true,
			},
			F_PHONE : {
				required : true,
			},
			F_BUILDGROUPNAME : {
				required : true,
			},
			F_GROUPALIASNAME : {
				required : true,
			},
			F_GROUPDESC : {
				required : true,
			}
		},
		messages : {
			F_DATACENTER_CODE : {
				required : "请输入数据中心代码"
			},
			F_DATACENTER_NAME : {
				required : "请输入数据中心名称"
			},
			F_DATACENTER_IP : {
				required : "请输入数据中心IP地址"
			},
			F_DATACENTER_PORT : {
				required : "请输入数据中心端口号"
			},
			F_DATACENTER_TYPE : {
				required : "请选择数据中心类型"
			},
			F_GOVERNING_BODY : {
				required : "请输入主管单位"
			},
			F_DATACENTER_DESCRIBE : {
				required : "请输入数据中心描述"
			},
			F_CONTACTPERSON : {
				required : "请输入联系人"
			},
			F_PHONE : {
				required : "请输入联系电话"
			},
			F_BUILDGROUPNAME : {
				required : "请输入建筑群名称"
			},
			F_GROUPALIASNAME : {
				required : "请输入建筑群别名"
			},
			F_GROUPDESC : {
				required : "请输入建筑群描述"
			}
		},
  	 submitHandler: function(form) {
  		editEnergyType(form);
  	 }
 	});
 	//编辑
	function editEnergyType(form) {
   		$.ajax({
     		url: _ctx + "/view/energydataReport/basedatecentermanager/edit_Datecenter",
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
             	setTimeout(function(){
	              	$('#modal-form-editDatecenterType').modal('hide');//关闭编辑窗口
	                energydataReport_basedatecentermanager_datecenterType.searchEnergyType();
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
 	
 	//验证在模态框出现前加载编辑
 	$("#modal-form-editDatecenterType").on('show.bs.modal', function(event) {
 		$("#edit_TYPE").empty();
		fNybhCollMethod("edit");
   		var button = $(event.relatedTarget);
   		var id = button.data("id");//获取datecenterID
   		$.ajax({
	       url: _ctx + "/view/energydataReport/basedatecentermanager/getDatecenter",
	       type: "post", 
	       data:{     
	 			"bh":id
	 		},
	       success: function(result) {
	       //edit 回显
	         $("#edit_ID").val(result.data.f_DATACENTER_ID);
	         $("#edit_CODE").val(result.data.f_DATACENTER_CODE);
	         $("#edit_NAME").val(result.data.f_DATACENTER_NAME);
	         $("#edit_IP").val(result.data.f_DATACENTER_IP);
	         $("#edit_PORT").val(result.data.f_DATACENTER_PORT);
	         $("#edit_TYPE").val(result.data.f_DATACENTER_TYPE);
	         $("#edit_BODY").val(result.data.f_GOVERNING_BODY);
	         $("#edit_DESCRIBE").val(result.data.f_DATACENTER_DESCRIBE);
	         $("#edit_CONTACTPERSON").val(result.data.f_CONTACTPERSON);
	         $("#edit_PHONE").val(result.data.f_PHONE);
					 $("#edit_BUILDGROUPNAME").val(result.data.f_BUILDGROUPNAME);
					 $("#edit_GROUPALIASNAME").val(result.data.f_GROUPALIASNAME);
					 $("#edit_GROUPDESC").val(result.data.f_GROUPDESC);
	         }
   		});  
 	});
 	return {
 		//查询				
 		searchEnergyType : function (datas) {
 			var keywords = $("#datecenterinfo").val();
 			$.ajax({
 				url : _ctx + '/view/energydataReport/basedatecentermanager/getDatecenterList',
 				type : "post",
 				data : ({     
 					"keywords":keywords
 			 		}),
 		        beforeSend: function () { 
 		        	showLoad();	             
 		            },
 				success : function(data) {
 					$('#Datecenter_ibox').html(data);
 				},
 	            complete: function () {
 	            	hiddenLoad();
 	            },
 				error : function(XMLHttpRequest,textStatus, errorThrown) {
 					toastr.error('', '查询失败');
 				}
 				});
 		},
 		//下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
        btn_exp : function(){
        	//生成模板
        	var _url = "${ctx}/view/energydataReport/basedatecentermanager/exportExcelModel";
            doExp(_url,"模板","${ctx}","");//导出excel并下载  
        },
        //导入数据按钮
        btn_import:function(){
        	var filepath = $("#exportInputFile").val();
        	if(filepath!=""){
            	$("#exportInputFile").fileinput("upload");//上传方法
        	}else{
        		swal({title: "请上传数据文件!",type: "warning",showCloseButton: true});
        	}
        },
        //导出excel
        exportReport:function(){        	
            var _url = "${ctx}/view/energydataReport/basedatecentermanager/exportExcel";
            doExp(_url,"数据中心基本信息","${ctx}","");//导出excel并下载
        },
 		pageInit : function(){
 			energydataReport_basedatecentermanager_datecenterType.searchEnergyType();
 		}
 	}
})(jQuery, window, document);
energydataReport_basedatecentermanager_datecenterType.pageInit();
</script>