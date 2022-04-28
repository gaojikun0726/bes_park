<!-----内容区域---->
<div class="ibox-content m-b-sm border-bottom"
	style="width: 100%; height: 60px">
	<div class="input-group" style="height: 30px; width: 100%;">
		<div
			style="width: 50%; float: left; position: relative; padding-right: 15px;">
			<a id="addADAPTERMC" href="javascript:void(-1);"
				onclick="applicationmanage_battery_spq.role_show_addmodal()"
				class="btn btn-primary"> 添加适配器 <i class="fa fa-plus"></i>
			</a>
		</div>
		<div
			style="width: 25%; float: right; position: relative; padding-right: 0px; padding-left: 15px;">
			<input type="text" class="input-sm form-control"
				style="width: calc(100% - 60px);" id="adapterkeywords"
				name="adapterkeywords" value="" placeholder="设备名称、服务提供厂商"> <span
				class="input-group-btn" style="width: 60px;">
				<button class="btn btn-primary btn-sm m-b-none" id="queryKeybtn">
					<i class="fa fa-search"></i> 搜索
				</button>
			</span>
		</div>
	</div>
</div>

<!---分页列表开始----->
<div class="ibox" id="spq_ibox" style="height: 88%"><#include
	"view/sysmanage/applicationmanage/battery_spq_page.ftl"/></div>

<!---添加适配器信息开始----->
<div class="modal fade" id="modal-form-addISSPEquipmentadapter"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title addIcon">&nbsp; 添加适配器信息</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="addEquipmentadapter"
					name="addEquipmentadapter" class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label">适配器名称<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="hidden" id="edite_fGuid" name="edite_fGuid" required
								class="form-control" readonly="readonly"> <input
								type="text" id="fAdaptermc" name="fAdaptermc"
								placeholder="请输入适配器名称" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">设备名称<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<select id='fSbmc' name ='fSbmc' class="form-control selectpicker"
								data-live-search="false">
								<!-- <option value="电池">电池</option>
								<option value="DDC">DDC</option>
								<option value="ip路由">ip路由</option> -->
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">服务提供厂商<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="fManufacturer" name="fManufacturer"
								placeholder="请输入服务提供商" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">服务IP地址</label>
						<div class="col-sm-8">
							<input type="text" id="fIp" name="fIp" placeholder="请输入IP地址"
								class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">服务端口</label>
						<div class="col-sm-8">
							<input type="text" id="fPort" name="fPort" placeholder="请输入服务端口"
								class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">适配器处理类</label>
						<div class="col-sm-8">
							<input type="text" id="fClasspath" name="fClasspath"
								placeholder="请输入适配器处理类" class="form-control">
						</div>
					</div>
					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-3">
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
<div class="modal fade" id="editAdapterForm" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" data-backdrop="static"
	data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header bg-primary">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title editIcon">&nbsp;编辑适配器信息</h4>
			</div>
			<div class="modal-body">
				<form id="editAdapterFormFunc" name="editAdapterFormFunc"
					class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="fSbmc">设备名称 <span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
						<select  type="text" id='edit_fSbmc' name="edit_fSbmc" required  class="form-control selectpicker"
								data-live-search="false">
								<!-- <option value="电池">电池</option>
								<option value="DDC">DDC</option>
								<option value="ip路由">ip路由</option> -->
							</select>
							<!-- <input type="text" id="edit_f_sbmc" name="edit_f_sbmc" required
								class="form-control""> -->
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="fManufacturer">适配器厂商
							<span class="text-danger">*</span>
						</label>
						<div class="col-sm-8">
							<input type="text" id="edite_fManufacturer"
								name="edite_fManufacturer" required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="fIp">服务IP地址 <span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="edite_fIp" name="edite_fIp" required
								class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="efPort">服务端口 <span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="edite_fPort" name="edite_fPort" required
								class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="fClass">适配器处理类<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="edite_fClasspath" name="edite_fClasspath"
								required class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="fAdaptermc">适配器名称<span
							class="text-danger">*</span></label>
						<div class="col-sm-8">
							<input type="text" id="edite_fAdaptermc" name="edite_fAdaptermc"
								required class="form-control">
						</div>
					</div>

					<div class="form-group m-t-sm">
						<div class="col-sm-6 col-sm-push-4">
							<button class="btn btn-md btn-primary " type="submit">
								<strong>更新适配器</strong>
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

<script type="text/javascript">
;
	var applicationmanage_battery_spq  = (function($, window, document, undefined) {
 	var _ctx = '${ctx}';
	var _curRow = null;
	var _froleguid = "00";
	var _curRow = null;//对应行
	var _includecurRow = null;//“已选择”table对应行
	var _notincludecurRow = null;//“未选择”table对应行
	
	$(window).resize(function(){
		$("#spqTable").tabulator("redraw");
	});
	
	
	//触发搜索的回车时间
   $("#adapterkeywords").focus(function(){
					  $(this).keydown(function (e){
					  if(e.which == "13"){
						  applicationmanage_battery_spq.reLoadEquipmentType({
			"keywords" : $("#adapterkeywords").val()
			});//触发该事件
					    } 
					  })
					});

	//搜索
	/* function search_adapter(){

		var adapterkeywords = $("#adapterkeywords").val();
		$("#spqTable").tabulator("setData", _ctx+'/view/basedatamanage/eqmanage/equipmentType_page?keywords='+adapterkeywords);
	} */
	$("#queryKeybtn").click(function() {
		applicationmanage_battery_spq.reLoadEquipmentType({
				"keywords" : $("#adapterkeywords").val()
		});
		});
	
	//添加适配器表单验证
    var euserValidator = $("#addEquipmentadapter").validate({
	     rules: {
            fAdaptermc: {
                required: true,
                rangelength: [1, 20],
            }
        },
        /* messages: {
        fAdaptermc: {
          required: "请输适配器名称",
                    minlength : jQuery.validator
								.format("Enter at least {0} characters")
            }
         
        }, */
        rules: {
            fManufacturer: {
                required: true,
                rangelength: [1, 20],
            }
        },
         messages: {
             fManufacturer: {
               required: "服务提供厂商",
                    minlength : jQuery.validator
								.format("Enter at least {0} characters")
            }
         
        },
          rules: {
            fSbmc: {
                required: true,
                rangelength: [1, 20],
            }
        },
         messages: {
             fSbmc: {
               required: "此字段不能为空，请按要求输入内容！",
                    minlength : jQuery.validator
								.format("Enter at least {0} characters")
            }
         
        },
	     submitHandler: function (form) {

	         addform_EsRole(form);
	     }
 	});
    
 	//居中显示（添加）
 	$('#modal-form-addISSPEquipmentadapter').on('show.bs.modal', function () {
 		// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#modal-form-addISSPEquipmentadapter .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
 	//居中显示（编辑）
 	$('#editAdapterForm').on('show.bs.modal', function () {
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 2 - $('#editAdapterForm .modal-dialog').height() / 2;
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        }); 
	})
	//关闭模态框清空表单值
    $("#modal-form-addISSPEquipmentadapter").on('hidden.bs.modal', function (event) {
        $(this).find("input").val("");
        $("#fSbmc").empty();
        euserValidator.resetForm();  
    })
    
	//添加和查询的下拉框
	function fNysbmcbranch(keywords){

 		$.ajax({
        	type: "get", 
        	url: _ctx + '/adapter/getallbesequipment', 
	        success: function(data){
			 	var ops="<option value=''>请选择适配器名称</option>";
			 	for(var i=0;i<data.list.length;i++){
				 	var obj=data.list[i];
				 	ops+='<option value="'+obj.f_type+'">';
				 	ops+=obj.f_sbmc;
				 	ops+='</option>';
			 	}
			 	
			 	if(keywords == 'add'){
			 		$("#fSbmc").append(ops);
			 	}else{
			 		$("#edit_fSbmc").append(ops);	
			 	}
	        }, 
	        error:function(msg){
	        	alert( "select列表查询失败!" );  
	        } 
	        
    	});
 	}
	
    //删除数据
    $(document).on('click','#spqTable button.delete', function () {

    	var id=$(this).data("id");
        //var id= sysmanage_applicationmanage_battery_spq_page.getCurRow().getData().fGuid
        swal({ 
	            title: "您确定要删除吗?",
	            text: "信息删除后将不可恢复!",
	            type: "warning",
	            showCancelButton: true,
	            confirmButtonColor: "#1ab394",
	            confirmButtonText: "确定",
	            closeOnConfirm: false
	        }, function () {
	        	$.ajax({
		          url: _ctx + "/adapter/deleteEquipmentadapter",
		          type: "post",
		          data:{     
		        	"fGuid":id
		    	  },
		        beforeSend: function () { 
	        		showLoad();	             
	            },
	          	  success: function(data) {
	              	if (data.status == '1') {
	              		swal(data.msg, "", "success");
	              		//$("#spqTable").tabulator("deleteRow", _curRow);    
	              		var getroleTable = $("#spqTable").tabulator("getData"); 
	              		$("#spqTable").tabulator("setData", getroleTable);
	              		applicationmanage_battery_spq.reLoadEquipmentType();
		            } else{
		                swal(data.msg,"", "error");
		            }
		          },complete: function () {
	            	hiddenLoad();
	            },
		          error: function(data) {
		          	 swal( data.msg,"", "error");
		          }
		        });
        });
    });
       
   //表单验证
  $("#editAdapterFormFunc").validate({
    submitHandler: function(form) {
      editAdapterFormFunc(form);
   }
  });
    //新增添加
	function addform_EsRole(form) {
	       $.ajax({
		      url: _ctx + "/adapter/addISSPEquipmentadapter",
		      type: "post",
		      contentType: "application/json; charset=utf-8",
		      data:JSON.stringify({     
		 			fAdaptermc:$("#fAdaptermc").val(),
		 			fSblx:$("#fSbmc").val(),
					fManufacturer:$("#fManufacturer").val(),
					fSbmc:$("#fSbmc option:selected").text(),
					fIp:$("#fIp").val(),
					fPort:$("#fPort").val(),
					fClasspath:$("#fClasspath").val(),
		 	}),
		success: function(data) {
	         if (data.status == '1') {
	           swal( data.msg,"", "success");
	           setTimeout(function(){
		            $('#modal-form-addISSPEquipmentadapter').modal('hide');//关闭编辑窗口
		            //在表格中添加数据
		            $('#spqTable').tabulator("addRow", {fGuid:data.data.fGuid,fManufacturer:data.data.fManufacturer,fAdaptermc:data.data.fAdaptermc,fSblx:data.data.fSblx, fSbmc:data.data.fSbmc, fIp:data.data.fIp,fPort:data.data.fPort,
		            fClasspath:data.data.fClasspath,fCrdate:data.data.fCrdate,fChdate:data.data.fChdate});
	           },1000)
	         } else{
	           swal( data.msg, "", "error");
	         }
	       },
	       error: function(data) {
	       	 swal( data.msg,"", "error");
	       }
	     });
	     return false;
 	}
 	//编辑
  function editAdapterFormFunc(form) {

    $.ajax({
      url: _ctx + "/adapter/updateEquipmentadapter",
      type: "post",
      data: ({
      		fGuid: $("#edite_fGuid").val(),
      		fSblx:$("#edit_fSbmc").val(),
	        fSbmc: $("#edit_fSbmc option:selected").text(),
      		fManufacturer: $("#edite_fManufacturer").val(),
	        fAdaptermc: $("#edite_fAdaptermc").val(),
	        fIp:$("#edite_fIp").val(),
	        fPort:$("#edite_fPort").val(),
	        fClasspath:$("#edite_fClasspath").val(),
	        fCrdate:$("#edite_fCrdate").val(),
      }),
      success: function(data) {

			if (data.status == '1') {
              swal("修改成功!", data.msg, "success");
              applicationmanage_battery_spq.reLoadEquipmentType();
	          //$('#spqTable').tabulator("updateRow",_curRow, {fSbmc:data.data.fSbmc, fAdaptermc:data.data.fAdaptermc, fManufacturer:data.data.fManufacturer,fCrdate:data.data.fCrdate, fIp:data.data.fIp, fPort:data.data.fPort, fClasspath:data.data.fClasspath});
              setTimeout(function(){ 
	              $('#editAdapterForm').modal('hide');//关闭编辑窗口
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
  
 
  //验证在模态框出现前加载
  $("#editAdapterForm").on('show.bs.modal', function(event) {

  	$("#edit_fSbmc").empty();
  	fNysbmcbranch('edit');
  	var button = $(event.relatedTarget);
	var id = button.data("id");
  	//var id = sysmanage_applicationmanage_battery_spq_page.getCurRow().getData().fGuid
    //var id = _curRow.getData().fGuid;//获取guid
    $.ajax({
	       url: _ctx + "/adapter/queryISSPE",
	       type: "post",
	       data:{     
	          "fGuid":id
	    	},
	       success: function(result) {

		         $("#edite_fGuid").val(result.data.fGuid);
		         $("#edite_fAdaptermc").val(result.data.fAdaptermc);
		         $("#edite_fManufacturer").val(result.data.fManufacturer);
		         $("#edit_fSbmc").val(result.data.fSbmc);
		         $("#edite_fIp").val(result.data.fIp);
		         $("#edite_fPort").val(result.data.fPort);
		         $("#edite_fClasspath").val(result.data.fClasspath);
	         }
    });  
  });
	/* //创建并设置table属性
	$("#spqTable").tabulator({
		height:"100%",
		layout:"fitColumns",//fitColumns  fitDataFill
		columnVertAlign:"bottom", //align header contents to bottom of cell
		tooltips:true,
		//selectable:true,
		movableColumns:true,
		columns:[
		{title:"序号",field:"id",width:50,formatter:"rownum",align:"center",frozen:false,headerSort:false},
		{title:"设备名称", field:"fSbmc", width:150,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"服务提供厂商", field:"fManufacturer",width:150,sorter:"string",align:"center",editor:true,headerSort:false},
		{title:"服务IP地址", field:"fIp", width:150,sorter:"string",editor:false,align:"center",headerSort:false},
		{title:"服务端口", field:"fPort",width:150,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"适配器处理类", field:"fClasspath",width:150,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"适配器名称", field:"fAdaptermc",width:150,sorter:"string",align:"center",editor:false,headerSort:false},
		{title:"创建时间", field:"fCrdate",sorter:"date",align:"center",editable:false,headerSort:false},
		{title:"修改时间", field:"fChdate",sorter:"date",align:"center",editor:false,headerSort:false},
		{title:"操作", field:"opt",tooltip:false,tooltipsHeader:false,align:"center",formatter:optIconFunction,headerSort:false},
		],
		rowClick:function(e, row){
        	_curRow = row;
    	},
	});
	
	//填充数据
	$("#spqTable").tabulator("setData", _ctx+'/adapter/getAllAdapter'); */
	
	return {
		//验证增加模态框是否弹出
		role_show_addmodal : function() {
			$('#modal-form-addISSPEquipmentadapter').modal('show'); 
			fNysbmcbranch('add');
			//$("#fZzjgbh_branch").val(_zzjgbhbran);
	    },
	// 分页查询
	reLoadEquipmentType : function (datas) {
		$.ajax({
			url : _ctx + '/adapter/battery_spq_page',
			type : "post",
			data : datas,
		        beforeSend: function () { 
		        	showLoad();	             
		        },
			success : function(data) {

				$('#spq_ibox').html(data);
			},
			complete: function () {
	            	hiddenLoad();
	            },
			error : function(XMLHttpRequest,textStatus, errorThrown) {
				toastr.error('', '查询失败');
			}
		});
	},
	 pageInit : function(){
		applicationmanage_battery_spq.reLoadEquipmentType();
	} 
    }
	})(jQuery, window, document);
	applicationmanage_battery_spq.pageInit();
 </script>