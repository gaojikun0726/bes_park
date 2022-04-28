<style>

    #import_form_ameterInfo_btn .form-control{
        height: 28px!important;
        line-height: 1.8;
    }
    #import_form_ameterInfo_btn .btn-primary{
        width:6.5vw!important;
        height: 3.85vh!important;
    }
</style>

<!-- 组织机构树模块 -->
<div class="leftarea information_left" id="leftElectricPconf">
	<div class="information-model">
		<span class="tree_Subtitle">
			<i class="fa fa-share-alt" aria-hidden="true"></i>&nbsp;请选择组织机构>>>
		</span>
	</div>
	<div id="tree_static_ElectricP" class="Information_area"></div>
</div>
<!-- 组织机构树模块end -->

<!-- 信息表格模块 -->
<div class="information_right">
	<div class="information_size">
		<div class="information-model">
			<span class="Subtitle">
				<i class="fa fa-th-list" aria-hidden="true"></i>&nbsp;信息列表>>>
			</span>
			<!-- 增加按钮 -->
			 <a id="addMeterInformation" href="javascript:void(-1);"  class="btn btn-primary toLeft" >
                	<i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;"></i>增加 
            </a>
            <a id="editMeterInformation" href="javascript:void(-1);" class="btn btn-primary toLeft" >
                	<i class="fa fa-edit" style="margin-top: 2.5px;margin-left: 2px;"></i>修改
            </a>
            <a id="delMeterInformation" href="javascript:void(-1);" class="btn btn-primary toLeft" >
                	<i class="fa fa-trash" style="margin-top: 2.5px;margin-left: 2px;"></i>删除
            </a>
            <a id="importmeterinformation" href="javascript:void(-1);"  class="btn btn-primary toLeft" >
                	<i class="fa fa-plus" style="margin-top: 2.5px;margin-left: 2px;"></i>录入 
            </a>
            <!-- 导入按钮 -->
            <a id="importbesrate"  onclick="basedatamanage_energydataRecord_entrymanual.impReport()"  href="javascript:void(-1);" class="btn btn-primary toLeft">
                <i class="fa fa-upload" style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导入
            </a>
            <!-- 导出按钮 -->
            <a id="exportbesrate"  onclick="basedatamanage_energydataRecord_entrymanual.exportReport()"  href="javascript:void(-1);" class="btn btn-primary toLeft">
                <i class="fa fa-download"style="margin-top: 2.5px;margin-left: 2px;" aria-hidden="true"></i>导出
            </a>
<#--            <!-- 导出excel按钮 &ndash;&gt;-->
<#--            <button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;"-->
<#--                    onclick="basedatamanage_energydataRecord_entrymanual.impReport()">-->
<#--                <i class="fa fa-upload" aria-hidden="true"></i>&nbsp;导入-->
<#--            </button>-->

<#--            <!-- 导出excel按钮 &ndash;&gt;-->
<#--            <button type="button" class="btn btn-sm btn-primary no-margins toLeft" style="width:5.5vw;"-->
<#--                    onclick="basedatamanage_energydataRecord_entrymanual.exportReport()">-->
<#--                <i class="fa fa-download" aria-hidden="true"></i>&nbsp;导出-->
<#--            </button>-->
			<!-- 搜索框 -->
			<div class="zc_search find">
				  <input type="text" class="find-style"  id="MeterInformation" name="MeterInformation" placeholder="电表序列号、名称">
				  <button id="queryElectricP" onclick="basedatamanage_energydataRecord_entrymanual.searchElectricP()"><i class="fa fa-search" aria-hidden="true"></i></button>
			</div>
		</div>
		<div id="ElectricPTable" class="Information_area"></div>
	</div>
</div>
<!-- 信息表格模块end -->

<!---添加静态电表开始-----> 
<div class="modal fade" id="modal-form-addMeter" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加静态电表</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addElectricPform" name="addElectricPform" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电表名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fSysName" name="fSysName" required class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电表别名<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fNickName" name="fNickName" required class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电表类型<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="fType" name="fType"  class="form-control selectpicker" >
						     </select>
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">安装位置<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fAzwz" required name="fAzwz" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电表描述<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="fDescription"  required name="fDescription" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">使能<span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-4" style="padding-top: 6px;">						      	 	
				       		 <input type="radio"   name="fEnabled" id="add_fEnabled_y"  checked value='1'>是
				        	 <input type="radio"   name="fEnabled" id="add_fEnabled_f"  value='0'>否
				    	</div>
                    </div>                                                                                                                                       
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!----编辑静态电表开始--->
<div class="modal fade" id="modal-form-editMeter" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title editIcon">&nbsp;编辑静态电表</h4>
            </div>
            <div class="modal-body">
            	<form id="editElectricPForm" name="editElectricPForm" class="form-horizontal">
            	<input type="hidden" id="edit_fGuid" name="fGuid" value="">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电表名称<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_fSysName" name="fSysName" required class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电表别名<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_fNickName" name="fNickName" required class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电表类型<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <select id="edit_fType" name="fType"  class="form-control selectpicker" >
						     </select>
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">安装位置<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_fAzwz" required name="fAzwz" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">电表描述<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="edit_fDescription"  required name="fDescription" class="form-control">
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-sm-3 control-label">使能<span class="text-danger">&nbsp;</span></label>
                        <div class="col-sm-4" style="padding-top: 6px;">						      	 	
				       		 <input type="radio"  name="fEnabled" id="fEnabled_y" value="1">是
				        	 <input type="radio"  name="fEnabled" id="fEnabled_f" value="0">否
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

<!-- 录入数据	模态框 -->
<div class="modal fade" id="modal-form-addMeterInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;添加静态电表数据</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="addMeterInfoform" name="addMeterInfoform" class="form-horizontal">
                <input type="hidden" name="fGuid" id="modal-form-addMeterInfo-fGuid" value="">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">数据(Kwh):<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="sj_fData" name="fData" required class="form-control isDigits">
                        </div>
                    </div> 
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4 display_flex">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 编辑数据模态框 -->
<div class="modal fade" id="modal-form-editMeterInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-primary">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title addIcon">&nbsp;编辑静态电表数据</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="editMeterInfoform" name="editMeterInfoform" class="form-horizontal">
                <input type="hidden" name="fJtguid" id="jtdbsjid" >
                    <div class="form-group">
                        <label class="col-sm-3 control-label">录入时间<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="jt_edit_fCrdate" disabled="disabled" name="fCrdate"  class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">数据(Kwh):<span class="text-danger">*</span></label>
                        <div class="col-sm-8">
                            <input type="text" id="jt_edit_fData"  name="fData"  class="form-control isDigits">
                        </div>
                    </div>
                    <div class="form-group m-t-sm" >
                        <div class="col-sm-6 col-sm-push-4">
                            <button class="btn btn-md btn-primary" type="submit"><strong>确定</strong></button>
                            <button class="btn btn-white m-l-sm"  type="button" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- 右侧内容模块模块end -->
<!-- 上传模态框 -->
<div class="modal fade import-Model" id="meterInfo_import_model" tabindex="-1" role="dialog" data-backdrop="false" aria-labelledby="ImportmyModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 41%;margin: 0 auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ImportMeterInfoModalLabel">导入报表</h4>
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
		                         <a href="javascript:void(0);"  onclick="basedatamanage_energydataRecord_entrymanual.btn_exp()">下载模板</a>
		                         </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" id="import_form_ameterInfo_btn">
                    <div class="col-md-12" style="padding-top: 2vh;">
                        <form id="ameterInfoImpExcel" >
                            <input id="exportameterInfoInputFile"  type="file" name="file"  class="file-loading">
                        </form>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="basedatamanage_energydataRecord_entrymanual.btn_import()">导入数据</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
;
var basedatamanage_energydataRecord_entrymanual = (function($, window, document, undefined) {
	var treeId="";//树节点ID
 	var _ctx = '${ctx}';
	var _ElectricPJs = "0";//用户组对应的级数
	var _fzbh = "00";
	var _curRow = null;//对应行
	var _includecurRow = null;//“已选择”table对应行
	var _notincludecurRow = null;//“未选择”table对应行
	
	//设置格式
	var optIconFunction = function(cell, formatterParams){ //plain text value
		var fJtguid = cell.getRow().getData().fJtguid;
		return "<div class='btn-group '>"
				+"<button class='btn btn-white btn-sm edit' data-id="+ fJtguid + " data-toggle='modal' data-target='#modal-form-editMeterInfo'><i class='fa fa-pencil' ></i> 编辑</button>"
				+"<button class='btn btn-white btn-sm delete' data-id=" + fJtguid + "><i class='fa fa-trash'></i>  删除</button></div>"
	};

    //fileinput 上传插件初始化
    function initFileinput(){
        $("#exportameterInfoInputFile").fileinput({
            language: 'zh', //设置语言
            uploadUrl: '${ctx}/view/basedatamanage/energydataRecord/entrymanual/meterFileUpload',//上传请求路径
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
            basedatamanage_energydataRecord_entrymanual.Meter_tree();
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
                    var exportName = "手动录入导入错误报告";
                    doExp(_url,exportName,"${ctx}",data);//导出excel并下载

                }

            }

        }).on("filebatchuploadsuccess",function(){
            clearForm();//清空form表单
        });
    }

//清空上传文件表单form 关闭模态框 并提示
    function clearForm(){
        $("#exportameterInfoInputFile").fileinput("destroy");//销毁fileinput删除上传文件缓存
        $("#meterInfo_import_model").modal("hide");//关闭模态框

    }

    //创建并设置table属性
	$("#ElectricPTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:false,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id", formatter:"rownum",align:"center",frozen:false,sorter:"string",headerSort:false}, //frozen column
		{title:"电表名称", field:"fSysName" ,sorter:"string",editor:false,align:"center",headerSort:false}, //never hide this column
		{title:"电表别名", field:"fNickName" ,sorter:"string",editor:false,align:"center",headerSort:false}, //hide this column first 
		{title:"数据(Kwh)", field:"fData" ,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"录入时间", field:"fCrdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"fChdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"操作", field:"opt",width:200,tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
    	rowClick:function(e, row){
        	_curRow = row;
        	var id = _curRow.getData().fNybh;
        	var choiseNode = $('#tree_static_ElectricP').treeview('findNodes', [ _curRow.getData().fNybh, 'id']);
        	if(choiseNode.length>1){
    	    	for (var i = 0; i < choiseNode.length; i++) {
    				if(choiseNode[i].id == id){
    					$('#tree_static_ElectricP').treeview('searchByNode', choiseNode[i]);//搜索设置高亮
    				}
    			}
    	    }else{
        		$('#tree_static_ElectricP').treeview('searchByNode', choiseNode[0]);//搜索设置高亮
    	    }        	
    	},
	});
		
	$(window).resize(function(){
		$("#ElectricPTable").tabulator("redraw");
	});	
		
	//触发搜索的回车时间
	$("#MeterInformation").focus(function(){
		$(this).keydown(function (e){
			if(e.which == "13"){
				basedatamanage_energydataRecord_entrymanual.searchElectricP();//触发该事件
			} 
		 })
	});
	
	//添加静态电表表单验证
    var groupValidator = $("#addElectricPform").validate({
	     rules: {
	    	 fType: {
	             required: true,
	         }
	     },
	     messages: {
	    	 fType: {
	             required: "请选择电表类型",
	         }
	     },
	     submitHandler: function (form) {
	         addformElectricP(form);
	     }
 	});
	
	
 	//新增保存
	function addformElectricP(form) {
 		$.ajax({
	       url: _ctx + "/view/basedatamanage/energydataRecord/entrymanual/add_MeterInformation",
	       type: "post",
	       data:$(form).serialize(),
	 		beforeSend: function () { 
	 			showLoad();	             
	 		},
	       success: function(data) {
	         if (data.status == '1') {
	        	 success_swal(data.msg,"","success");
	        	 $('#modal-form-addMeter').modal('hide');//关闭编辑窗口
                 //向表格中添加数据
                 $('#ElectricPTable').tabulator("addRow", { fSysName:data.data.fSysName,fNickName:data.data.fNickName,fChdate:data.data.fChdate,fChdate:data.data.fChdate});

                 //刷新静态电表树
	             basedatamanage_energydataRecord_entrymanual.Meter_tree(treeId);
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       complete: function () {
	    		hiddenLoad();
	    	},
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
	     return false;
 	}

	//添加静态电表数据表单验证
    var sjValidator = $("#addMeterInfoform").validate({
	     rules: {
	    	 fData: {
	             required: true,
	             rangelength: [0,9]
	         }
	     },
	     messages: {
	    	 fData: {
	             required: "请填写静态电表数据",
	             minlength: jQuery.validator.format("Enter at least {0} characters")
	         }
	     },
	     submitHandler: function (form) {
	    	 addformMeterInfo(form);
	     }
 	});
	
	
 	//新增保存  电表数据
	function addformMeterInfo(form) {
 		$("#modal-form-addMeterInfo-fGuid").val(treeId);
 		$.ajax({
	       url: _ctx + "/view/basedatamanage/energydataRecord/entrymanual/add_MeterInfo",
	       type: "post",
	       data:$(form).serialize(),
	 		beforeSend: function () { 
	 			showLoad();	             
	 		},
	       success: function(data) {
	         if (data.status == '1') {
	        	 success_swal(data.msg,"","success");
	        	 $('#modal-form-addMeterInfo').modal('hide');//关闭编辑窗口
	        	 //刷新静态电表树
	             basedatamanage_energydataRecord_entrymanual.Meter_tree(treeId);
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       complete: function () {
	    		hiddenLoad();
	    	},
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
	     return false;
 	}
 	
	//居中显示（添加）
	$('#modal-form-addMeter').on('show.bs.modal', function () {
		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
    	$(this).css('display', 'block');  
    	var modalHeight=$(window).height() / 2 - $('#modal-form-addMeter .modal-dialog').height() / 2;  
    		$(this).find('.modal-dialog').css({  
        		'margin-top': modalHeight  
    		}); 
	})

	//居中显示（编辑）
 	$('#modal-form-editMeter').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-editMeter .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	
	//居中显示（添加）
	$('#modal-form-addMeterInfo').on('show.bs.modal', function () {
		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
    	$(this).css('display', 'block');  
    	var modalHeight=$(window).height() / 2 - $('#modal-form-addMeterInfo .modal-dialog').height() / 2;  
    		$(this).find('.modal-dialog').css({  
        		'margin-top': modalHeight  
    		}); 
	})

	//验证码在模态框出现前加载包含用户(可拖动)
	$("#modal-form-addMeter,#modal-form-addMeterInfo,#modal-form-editMeter,#modal-form-editMeterInfo").on('show.bs.modal', function(event) {
	    var button = $(event.relatedTarget);
	    var id = button.data("id");			//获取用户组编号
	    $(this).draggable({
	        handle: ".modal-header"     	// 只能点击头部拖动
	    });
	    $(this).css("overflow", "hidden"); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
	});
	
	//居中显示（编辑）
 	$('#modal-form-editMeterInfo').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-editMeterInfo .modal-dialog').height() / 2;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	
	//关闭模态框清空表单值
    $("#modal-form-addMeter,#modal-form-addMeterInfo,#modal-form-editMeter,#modal-form-editMeterInfo").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
		$(this).find("#add_fEnabled_y,#fEnabled_y").val("1");//重新给check赋值
		$(this).find("#add_fEnabled_f,#fEnabled_f").val("0");
        groupValidator.resetForm();
    });
	
 	//修改数据 回显
 	$("#modal-form-editMeterInfo").on('show.bs.modal', function(event) {
   		var button = $(event.relatedTarget);
   		var id = button.data("id");//获取能源类型编号
   		$.ajax({
	       url: _ctx + "/view/basedatamanage/energydataRecord/entrymanual/editSelect_MeterInfo",
	       type: "post", 
	       data:{     
	 			"fJtguid":id
	 		},
	 		beforeSend: function () { 
	 			showLoad();	             
	 		},
	       success: function(result) {
	         $("#jt_edit_fCrdate").val(result.data.fCrdate);
	         $("#jt_edit_fData").val(result.data.fData);
	         $("#jtdbsjid").val(result.data.fJtguid);
	         },
	         complete: function () {
	    			hiddenLoad();
	    		},
	    	error: function(data) {
	         	 	swal("加载失败!", data.msg, "error");
	    		}
   		});  
 	});


	
 	//修改数据 保存
	$("#editMeterInfoform").validate({
		rules: {
	    	 fData: {
	             required: true,
	             rangelength: [0,9]
	         }
	     },
	     messages: {
	    	 fData: {
	             required: "请填写静态电表数据",
	             minlength: jQuery.validator.format("Enter at least {0} characters")
	         }
	     },
  	 submitHandler: function(form) {
    	 meterinfo_save(form);
  	 }
 	});
 	
 	//编辑 保存
	function meterinfo_save(form) {
   		$.ajax({
     		url: _ctx + "/view/basedatamanage/energydataRecord/entrymanual/edit_MeterInfo",
     		type: "post",
     		data:$(form).serialize(),
     		beforeSend: function () { 
     			showLoad();	             
     		},
     		success: function(data) {
				if (data.status == '1') {
					success_swal(data.msg,"","success");
	              	$('#modal-form-editMeterInfo').modal('hide');//关闭编辑窗口
	              	var selectId=$("#jtdbsjid").val();
					basedatamanage_energydataRecord_entrymanual.Meter_tree(treeId);
         		} else{
             		swal("修改失败!", data.msg, "error");
         		}
    		},
    		complete: function () {
    			hiddenLoad();
    		},
    		error: function(data) {
         	 	swal("修改失败!", data.msg, "error");
    		}
   		});
	}
 	
	//删除静态电表
	$(document).on('click','#delMeterInformation',function() {
			swal(
			{
			title : "您确定要删除吗?",
			text : "静态电表删除后将不可恢复!（默认删除静态电表下数据）",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "确定",
			closeOnConfirm : false
			},
		function() {
			setTimeout(function() {
			$.ajax({
				url : _ctx + "/view/basedatamanage/energydataRecord/entrymanual/del_MeterInformation",
				type : "post",
				data : {"fGuid" : treeId
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
					basedatamanage_energydataRecord_entrymanual.Meter_tree();

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
	
	//删除静态电表 数据
	$(document).on('click','#ElectricPTable button.delete',function() {
			var row = $(this).parents("tr")[0];
			var id = $(this).data("id");
			swal(
			{
			title : "您确定要删除吗?",
			text : "静态电表数据删除后将不可恢复!",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#1ab394",
			confirmButtonText : "确定",
			closeOnConfirm : false
			},
		function() {
			setTimeout(function() {
			$.ajax({
				url : _ctx + "/view/basedatamanage/energydataRecord/entrymanual/del_MeterInformation",
				type : "post",
				data : {"fJtguid" : id,"lxqf":"sj"
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
              		$("#ElectricPTable").tabulator("deleteRow", _curRow);

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
	
 	//表单验证
	$("#editElectricPForm").validate({
     rules: {
    	 fType: {
             required: true,
         }
     },
     messages: {
    	 fType: {
             required: "请选择电表类型",
         }
     },
  	 submitHandler: function(form) {
    	 editElectricP(form);
  	 }
 	});
 	
 	//编辑 保存
	function editElectricP(form) {
   		$.ajax({
     		url: _ctx + "/view/basedatamanage/energydataRecord/entrymanual/edit_MeterInformation",
     		type: "post",
     		data:$(form).serialize(),
     		beforeSend: function () { 
     			showLoad();	             
     		},
     		success: function(data) {
				if (data.status == '1') {
					success_swal(data.msg,"","success");
	              	$('#modal-form-editMeter').modal('hide');//关闭编辑窗口
					basedatamanage_energydataRecord_entrymanual.Meter_tree(treeId);
         		} else{
             		swal("修改失败!", data.msg, "error");
         		}
    		},
    		complete: function () {
    			hiddenLoad();
    		},
    		error: function(data) {
         	 	swal("修改失败!", data.msg, "error");
    		}
   		});
	}
	//增加电表信息 点击事件
	$("#addMeterInformation").click(function(){
		$("#fType").empty();
		MeterType("add");
		$('#modal-form-addMeter').modal('show');
	});
//修改电表信息 点击事件
	$("#editMeterInformation").click(function(){
		$("#edit_fType").empty();
		MeterType("edit");//获取下拉列表
		var node = $('#tree_static_ElectricP').treeview('getSelected');
		if(node.length==1){
			if(treeId !=''||treeId !='undefined' ||treeId !=null){
				$('#modal-form-editMeter').modal('show');//打开编辑窗口
					$.ajax({
				 	       url:"${ctx}/view/basedatamanage/energydataRecord/entrymanual/editSelect_MeterInformation",
				 	       type: "post", 
				 	       data:{     
				 	 			"fGuid":treeId
				 	 		},
				 	 		beforeSend: function () { 
				 	 			showLoad();
				 	 		},
				 	       success: function(result) {
				 	         $("#edit_fGuid").val(result.data.fGuid);
				 	         $("#edit_fSysName").val(result.data.fSysName);
				 	         $("#edit_fNickName").val(result.data.fNickName);
				 	         $("#edit_fType").val(result.data.fType);
				 	         $("#edit_fAzwz").val(result.data.fAzwz);
				 	         $("#edit_fDescription").val(result.data.fDescription);
				 	         if('1'==result.data.fEnabled){
			 	        		 $("#fEnabled_y").prop("checked",true);
				 	         }else{
				 	        	 $("#fEnabled_f").prop("checked",true);
				 	         }
				 	         },
				 	         complete: function () {
				 	    			hiddenLoad();
				 	    		},
				 	    	error: function(data) {
				 	         	 	swal("加载失败!", data.msg, "error");
				 	    		}
		    		});
			}else{
				swal("未获取到该静态电表数据","", "error")			
			}
		}else{
			swal("请选择一个静态电表","", "error")
		}
	});
//录入数据
	$("#importmeterinformation").click(function(){
		var node = $('#tree_static_ElectricP').treeview('getSelected');
		if(node.length==1){
			$('#modal-form-addMeterInfo').modal('show');
		}else{
			swal("请选择一个静态电表录入数据","", "error")
		}
	});
	//下拉框列表查询  电表类型
	function MeterType(keywords){
		$.ajax({
        	type: "POST", 
        	url: '${ctx}/view/basedatamanage/energydataRecord/entrymanual/selectMeterType', 
        	data:"",
        	beforeSend: function () { 
        		showLoad();	             
        	},
        success: function(data){
		 	var ops="<option value=''>请选择类型</option>";
		 	for(var i=0;i<data.list.length;i++){
			 	var obj=data.list[i];
			 	ops+='<option value="'+obj.ID+'">';
			 	ops+=obj.NAME;
			 	ops+='</option>';
		 	}
		 	if(keywords == 'add'){
		 		$("#fType").append(ops);
		 	}else{
		 		$("#edit_fType").append(ops);
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
	function success_swal(title,text,type){
		swal({
	     	title : title,// 展示的标题
			text : text,// 内容
			type: type,
			showCloseButton : false, // 展示关闭按钮
			allowOutsideClick : false,
			showConfirmButton : false,
			timer: 1000
		});
	}
//居中显示
    $('#meterInfo_import_model').on('show.bs.modal', function () {
        // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
        $(this).css('display', 'block');
        var modalHeight=$(window).height() / 2 - $('#meterInfo_import_model .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({
            'margin-top': modalHeight
        });
    })
 	return {
 		//搜索
 		searchElectricP : function (){
 			var MeterInformation = $("#MeterInformation").val();
 	        $.ajax({
 		    url: _ctx+'/view/basedatamanage/energydataRecord/entrymanual/getTreeTableList',
 		    type: "post",
 		    data: {
 		    	keywords:MeterInformation,
 		    	treeId:treeId,
 		    },
 		   beforeSend: function () { 
 				showLoad();	             
 			},
 			success: function(data) {
 					if(data.hasOwnProperty('list')==false){
 			            $("#ElectricPTable").tabulator("setData", []);
 					}else{
 			            $("#ElectricPTable").tabulator("setData", data.list);
 					}
 	        },
 	       complete: function () {
 	    		hiddenLoad();
 	    	},
 	        error: function(data) {
 	      	    swal( data.msg,"", "error");
 	        }
 		   });
 		},
        //下载模板-- 提供两种思路 1.下载固定模板 可放到工作空间 2.生成模板下载
        btn_exp : function(){
            var fname = "手动录入数据报表模板.xls";
            var path = "file\\expExcel\\手动录入数据报表模板.xls";
	    //FileDownload("${ctx}/file/newFileDownload",fname,path);
            FileDownload(_ctx + filePath.loadPath,fname,path);
        },
 		//加载树
 		Meter_tree : function (selectId) {
 		    $.ajax({
 		        type: "post",
 		        url: _ctx + "/view/basedatamanage/energydataRecord/entrymanual/meter_tree",
 		        dataType: "json",
 		       beforeSend: function () { 
 		    		showLoad();	             
 		    	},
 		        success: function (result) {
 		            //初始加载根节点
 		            if(result.hasOwnProperty("list")){//判断result返回结果是否包含list
 		            	if(result.list.length >0){//如果包含判断是否长度大于0
 		            $('#tree_static_ElectricP').treeview({
 		                data: result.list,         // 数据源
 		                highlightSelected: true,    //是否高亮选中
 		                levels : 4,
 		                enableLinks : true,//必须在节点属性给出href属性
 		                color: "#4a4747",
 		               onNodeSelected: function (event, nodeData) {
 		               $('#tree_static_ElectricP').treeview('clearSearch');//清除搜索选中高亮
 		               		treeId=nodeData.id;//每次加载附给treeId节点ID
 		               		_ElectricPJs = nodeData.level;
 		                    $.ajax({
 		                	    url: _ctx + "/view/basedatamanage/energydataRecord/entrymanual/TreeTable",
 		                	    type: "post",
 		                	    data: {
 		                	    	treeId:nodeData.id,
 		                	    },
 		                	   beforeSend: function () { 
 		                			showLoad();
 		                		},
 								success: function(nodeData2) {
 								if(nodeData2.hasOwnProperty('list')==false){
 						            $("#ElectricPTable").tabulator("setData", []);
 								}else{
 						            $("#ElectricPTable").tabulator("setData", nodeData2.list);
 								}
 					            },
 					           complete: function () {
 					        		hiddenLoad();
 					        	},
 					            error: function(nodeData2) {
 					          	    swal( nodeData2.msg,"", "error");
 					            }
 	                	   });
 		                }
 		            });
 				            var firstNode = $("#tree_static_ElectricP").treeview('findNodes',[result.list[0].id,'id']);
 				            var selectNode= $("#tree_static_ElectricP").treeview('findNodes',[selectId,'id']);
 				            if(selectId !=''){
 				            	$("#tree_static_ElectricP").treeview("selectNode", selectNode);//将传过来的ID的节点设置为选中状态
 				            }else{
	 				        	$("#tree_static_ElectricP").treeview("selectNode", firstNode);//将第一个节点设置为选中状态
 				            }
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
 		},
        impReport :function(){
            //1.先弹出上传文件模态框--2.再讲文件进行上传操作--前端工作完成--先下载导入模板
            $("#meterInfo_import_model").modal("show");
            initFileinput();//初始化fileinput
        },
        //导入数据按钮
        btn_import:function(){
            var filepath = $("#exportameterInfoInputFile").val();
            if(filepath!=""){
                $("#exportameterInfoInputFile").fileinput("upload");//上传方法
            }else{
                swal({title: "请上传数据文件!",type: "warning",showCloseButton: true});
            }

        },
        //导出excel
        exportReport:function(){
            var data = {
                treeId : treeId,
            };
            var _url = "${ctx}/view/basedatamanage/energydataRecord/entrymanual/exportBesMeter";
            var exportName = "手动录入报表";
            doExp(_url,exportName,"${ctx}",data);//导出excel并下载
        },
 		pageInit : function(){
 			basedatamanage_energydataRecord_entrymanual.Meter_tree();
 		}
 		}

})(jQuery, window, document);
	basedatamanage_energydataRecord_entrymanual.pageInit();
</script>